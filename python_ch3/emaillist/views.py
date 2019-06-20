from django.shortcuts import render


def index(request):
    return render(request, 'emaillist/index.html')


def form(request):
    return render(request, 'emaillist/form.html')
