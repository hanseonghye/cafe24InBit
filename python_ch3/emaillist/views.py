from django.http import HttpResponse, HttpResponseRedirect

from django.shortcuts import render

from emaillist.models import Emaillist


def index(request):
    emaillist = Emaillist.objects.all().order_by('-id')
    data = {"emaillist": emaillist}
    return render(request, 'emaillist/index.html', data)


def form(request):
    return render(request, 'emaillist/form.html')


def add(request):
    emaillist = Emaillist()

    emaillist.first_name = request.POST['fn']
    emaillist.last_name = request.POST['ln']
    emaillist.email = request.POST['email']
    emaillist.save()
    return HttpResponseRedirect('/emaillist')


def hello(request, id=0):
    return render(request, '')


def hello2(request, id=0):
    return HttpResponse(f'id:{id}')
