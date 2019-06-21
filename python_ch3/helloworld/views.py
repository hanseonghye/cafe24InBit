from django.db.models import Max, F
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render

from helloworld.models import Counter


def hello(request):
    return render(request, 'helloworld/hello.html')


def hello(request, id=0):
    return render(request, '')


def hello2(request, id=0):
    return HttpResponse(f'id:{id}')


def hello3(request):
    jsonresult = {
        "result": "success",
        "data": ['hello', 1, 2, 3, True, ('a', 'b')],
    }

    return JsonResponse(jsonresult)


def counter_max(request):
    value = Counter.objects.aggregate(max_orderno=Max('orderno'))
    max_orderno = 0 if value["max_orderno"] is None else value["max_orderno"]
    return HttpResponse(f'max orderno: {max_orderno}')


def counter_add(request):
    c = Counter()
    c.groupno = 1
    c.depth = 1
    c.orderno = 1
    c.save()

    c = Counter()
    c.groupno = 1
    c.depth = 1
    c.orderno = 2
    c.save()

    c = Counter()
    c.groupno = 1
    c.depth = 1
    c.orderno = 3
    c.save()

    return HttpResponse('ok')


def counter_update(request):
    r = Counter.objects.filter(groupno=1).filter(orderno__gte=2).update(orderno=F('orderno')+1)
    return HttpResponse('ok')