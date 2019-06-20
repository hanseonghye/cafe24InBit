from django.http import HttpResponseRedirect
from django.shortcuts import render

# Create your views here.
from user.models import User


def loginform(request):
    return render(request, "user/loginform.html")


def login(request):
    email = request.POST['email']
    password = request.POST['password']

    user = User.objects.filter(email=email, password=password)

    if user:
        data = {"login": "ok"}
        return render(request, "/main/index.html", data)

    data = {"login": "fail"}
    return render(request, "user/loginform.html", data)


def joinform(request):
    return render(request, "user/joinform.html")


def joinsuccess(request):
    return render(request, "user/joinsuccess.html")


def join(request):
    user = User()
    user.name = request.POST['name']
    user.password = request.POST['password']
    user.email = request.POST['email']
    user.gender = request.POST['gender']
    user.save()

    return HttpResponseRedirect("/user/joinsuccess")


def logout(request):
    return render(request, "main/index.html")


def update(request):
    return render(request, "main/index.html")
