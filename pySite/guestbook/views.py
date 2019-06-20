from django.http import HttpResponseRedirect
from django.shortcuts import render

# Create your views here.
from guestbook.models import Guestbook


def index(request):
    guestbook_list = Guestbook.objects.all().order_by('-id')
    print(guestbook_list)
    data = {"guestbook_list": guestbook_list}
    return render(request, "guestbook/list.html", data)


def write(request):
    guestbook = Guestbook()
    guestbook.name = request.POST['name']
    guestbook.password = request.POST['password']
    guestbook.content = request.POST['content']
    guestbook.save()
    return HttpResponseRedirect("/guestbook")


def deleteform(request, id):
    data = {"no": id}
    return render(request, 'guestbook/deleteform.html', data)


def delete(request, id):
    guestbook = Guestbook.objects.filter(id=id, password=request.POST['password'])
    if guestbook:
        guestbook.delete()
    return HttpResponseRedirect("/guestbook")
