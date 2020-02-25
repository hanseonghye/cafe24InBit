from django.core.exceptions import ObjectDoesNotExist
from django.db.models import Max, F, Q
from django.http import HttpResponseRedirect
from django.shortcuts import render

from board.models import Board
from user.models import User

totalCount = 0
countPage = 3
jumpPage = 5
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


def getHowManyBoardGet(no, totalCount):
    if no * countPage > totalCount:
        return totalCount - (no - 1) * countPage

    return countPage


def search(request, no=1):
    if request.method == 'POST':
        kwd = request.POST['kwd']
    elif request.method == 'GET':
        kwd = request.GET['kwd']
    countTop = (no - 1) * countPage
    try:
        q = Q()
        q.add(Q(status=True), q.OR)
        q.add(Q(title__icontains=kwd) | Q(content__icontains=kwd), q.AND)
        search_list = Board.objects.filter(q).order_by('-id')
        count = getHowManyBoardGet(no, len(search_list))
        now_big_page = (int((1 - 1) / jumpPage)) * jumpPage + 1
        range_list = range(now_big_page, now_big_page + jumpPage)
        last_page = list(range_list).pop()

        data = {
            "kwd": kwd,
            "search_list": search_list[countTop:countTop + count],
            "nowPage": no,
            "countPage": countPage,
            "pager": getListNumber(len(search_list)),
            "jumppage": jumpPage,
            "nowBigPage": now_big_page,
            "pageList": range_list,
            "last_page": last_page,
        }
    except ObjectDoesNotExist:
        data = dict()
    return render(request, "board/search_list.html", data)


def index(request, no=1):
    if no < 1:
        no = 1

    countTop = (no - 1) * countPage
    count = getHowManyBoardGet(no, totalCount)
    try:
        board_list = Board.objects.all().order_by('-groupno', 'orderno')[countTop:countTop + count]
        now_big_page = (int((no - 1) / (jumpPage))) * (jumpPage) + 1
        range_list = range(now_big_page, now_big_page + jumpPage)
        last_page = list(range_list).pop()

        data = {
            "board_list": board_list,
            "nowPage": no,
            "countPage": countPage,
            "pager": listnumber,
            "jumpPage": jumpPage,
            "nowBigPage": now_big_page,
            "pageList": range_list,
            "last_page": last_page
        }
    except ObjectDoesNotExist:
        data = dict()

    return render(request, "board/list.html", data)


def writeform(request):
    if "authuser" not in request.session:
        return render(request, "user/loginform.html")
    return render(request, "board/write.html")


def write(request):
    if "authuser" not in request.session:
        return render(request, "user/loginform.html")

    board = Board()
    board.title = request.POST["title"]
    board.content = request.POST['content']
    board.depth = 0
    board.orderno = 1
    try:
        value = Board.objects.aggregate(max_groupno=Max('groupno'))
        board.groupno = 1 if value["max_groupno"] is None else value["max_groupno"] + 1
        board.user = User.objects.get(id=request.session["authuser"]["id"])
        board.save()
        global totalCount, listnumber
        totalCount += 1
        listnumber = getListNumber(totalCount)

    except ObjectDoesNotExist:
        return HttpResponseRedirect('/board')

    return HttpResponseRedirect(f'/board/view/{board.pk}')


def view(request, id):
    board = Board.objects.get(id=id)
    board.hit += 1
    board.save()
    data = {"board": board}

    return render(request, "board/view.html", data)


def modifyform(request, id):
    if "authuser" not in request.session:
        return render(request, "user/loginform.html")

    try:
        user = User.objects.get(id=request.session["authuser"]["id"])
        board = Board.objects.get(id=id, user=user)
        data = {"board": board}
    except ObjectDoesNotExist:
        return HttpResponseRedirect("/board")
    return render(request, "board/modify.html", data)


def modify(request, id):
    try:
        board = Board.objects.get(id=id)
        board.title = request.POST['title']
        board.content = request.POST['content']

        board.save()
        data = {"board": board}

    except ObjectDoesNotExist:
        return HttpResponseRedirect("/board")

    return render(request, "board/view.html", data)


def delete(request, id):
    if "authuser" not in request.session:
        return render(request, "user/loginform.html")

    try:
        user = User.objects.get(id=request.session["authuser"]["id"])
        board = Board.objects.get(id=id, user=user)
        board.title = "삭제된 글입니다."
        board.content = "삭제된 글입니다"
        board.status = False
        board.save()
    except ObjectDoesNotExist:
        pass
    return HttpResponseRedirect("/board")


def writereplyform(request, id):
    return HttpResponseRedirect(f"/board/writeform?id={id}")


def writereply(request, id):
    if "authuser" not in request.session:
        return render(request, "user/loginform.html")

    try:
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
    except ObjectDoesNotExist:
        pass
    return HttpResponseRedirect(f'/board/view/{board.pk}')