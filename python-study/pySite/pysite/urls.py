"""pysite URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
import main.views as main_views
import user.views as user_views
import guestbook.views as guestbook_views
import board.views as board_views

urlpatterns = [
    path('', main_views.index),
    path('admin', admin.site.urls),

    path('user/loginform', user_views.loginform),
    path('user/login', user_views.login),
    path('user/joinform', user_views.joinform),
    path('user/join', user_views.join),
    path('user/joinsuccess', user_views.joinsuccess),
    path('user/logout', user_views.logout),
    path('user/updateform', user_views.updateform),
    path('user/update', user_views.update),
    path('user/api/checkemail', user_views.checkemail),

    path('guestbook/', guestbook_views.index),
    path('guestbook/write', guestbook_views.write),
    path('guestbook/deleteform/<int:id>', guestbook_views.deleteform),
    path('guestbook/delete/<int:id>', guestbook_views.delete),

    path('board/',board_views.index),
    path('board/list/<int:no>',board_views.index),
    path('board/search', board_views.search),
    path('board/search/<int:no>', board_views.search),
    path('board/view/<int:id>',board_views.view),
    path('board/writeform',board_views.writeform),
    path('board/write',board_views.write),
    path('board/modifyform/<int:id>',board_views.modifyform),
    path('board/modify/<int:id>',board_views.modify),
    path('board/delete/<int:id>',board_views.delete),
    path('board/writereplyform/<int:id>',board_views.writereplyform),
    path('board/writereply/<int:id>',board_views.writereply),
]


board_views.init()