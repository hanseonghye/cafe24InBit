from django.forms import model_to_dict
from django.http import HttpResponseRedirect, JsonResponse
from django.shortcuts import render

# Create your views here.
from user.models import User


def loginform(request):
    return render(request, "user/loginform.html")


def login(request):
    email = request.POST['email']
    password = request.POST['password']

    # user = User.objects.filter(email=email).filter(password=password) # 동일
    user = User.objects.filter(email=email, password=password)

    if user:
        request.session['authuser'] = model_to_dict(user[0])
        return render(request, "main/index.html")

    return HttpResponseRedirect("/user/loginform?result=fail")


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
    del request.session['authuser']
    return HttpResponseRedirect("/")


def updateform(request):
    user = User.objects.get(id=request.session['authuser']['id'])
    data = {"user": user}
    return render(request, "user/updateform.html", data)


def update(request):
    user = User.objects.get(id=request.session['authuser']['id'])
    user.name = request.POST['name']
    user.gender = request.POST['gender']
    if request.POST['password'] is not "":
        user.password = request.POST['password']

    user.save()

    request.session['authuser']['name'] = user.name
    return HttpResponseRedirect("/user/updateform?result=success")


def checkemail(request):
    try:
        user = User.objects.get(email=request.GET['email'])
        result = {
            'result': 'success',
            'data': 'exist'
        }
    except Exception as e:
        result = {
            'result': 'success',
            'data': ''
        }
    return JsonResponse(result)
