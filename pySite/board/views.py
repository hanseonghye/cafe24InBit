from django.db.models import Max, F
from django.http import HttpResponseRedirect
from django.shortcuts import render

from board.models import Board
from user.models import User

totalCount = 0
countPage = 5
jumpPage = 2
listnumber = 0


def init():
    global totalCount, listnumber
    totalCount = Board.objects.count()
    listnumber = getListNumber(totalCount)


def getListNumber(totalcount):
    if totalcount % countPage == 0:
        return int(totalcount / countPage)
    else:
        return int(totalcount / countPage) + 1


def getHowManyBoardGet(no):
    if no * countPage > totalCount:
        return totalCount - (no - 1) * countPage

    return countPage


def search(request, kwd):
    search_list = Board.objects.filter(body_text__search=kwd)
    data = {
        "search_list": search_list,
        "countPage": countPage,
        "pager": getListNumber(len(search_list)),
        "jumppage": jumpPage
    }
    return render(request, "board/search_list.html", data)


def index(request, no=1):
    countTop = (no - 1) * countPage
    count = getHowManyBoardGet(no)
    board_list = Board.objects.all().order_by('-groupno', 'orderno')[countTop:countTop + count]

    data = {
        "board_list": board_list,
        "nowPage": no,
        "countPage": countPage,
        "pager": listnumber,
        "jumppage": jumpPage
    }

    return render(request, "board/list.html", data)


def writeform(request):
    return render(request, "board/write.html")


def write(request):
    board = Board()
    board.title = request.POST["title"]
    board.content = request.POST['content']
    board.depth = 0
    board.orderno = 1
    value = Board.objects.aggregate(max_groupno=Max('groupno'))
    board.groupno = 1 if value["max_groupno"] is None else value["max_groupno"] + 1
    board.user = User.objects.get(id=request.session["authuser"]["id"])
    board.save()
    global totalCount, listnumber
    totalCount += 1
    listnumber = getListNumber(totalCount)

    return HttpResponseRedirect(f'/board/view/{board.pk}')


def view(request, id):
    board = Board.objects.get(id=id)
    board.hit += 1
    board.save()
    data = {"board": board}

    return render(request, "board/view.html", data)


def modifyform(request, id):
    board = Board.objects.get(id=id)
    data = {"board": board}
    return render(request, "board/modify.html", data)


def modify(request, id):
    board = Board.objects.get(id=id)
    board.title = request.POST['title']
    board.content = request.POST['content']

    board.save()
    data = {"board": board}

    return render(request, "board/view.html", data)


def delete(request, id):
    user = User.objects.get(id=request.session["authuser"]["id"])
    board = Board.objects.get(id=id, user=user)

    if board:
        board.title = "삭제된 글입니다."
        board.content = "삭제된 글입니다"
        board.status = False
        board.save()
    return HttpResponseRedirect("/board")


def writereplyform(request, id):
    return HttpResponseRedirect(f"/board/writeform?id={id}")


def writereply(request, id):
    parent_board = Board.objects.get(id=id)
    board = Board()
    board.groupno = parent_board.groupno
    board.orderno = parent_board.orderno + 1
    board.depth = parent_board.depth + 1
    board.title = request.POST["title"]
    board.content = request.POST["content"]
    board.user = User.objects.get(id=request.session["authuser"]["id"])
    board.save()
    Board.objects.filter(groupno=board.groupno).filter(orderno__gte=board.orderno).update(orderno=F('orderno') + 1)
    global totalCount, listnumber
    totalCount += 1
    listnumber = getListNumber(totalCount)
    return HttpResponseRedirect(f'/board/view/{board.pk}')
