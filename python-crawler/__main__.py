import ssl
import sys
from itertools import count
from urllib.request import Request, urlopen
import datetime

import pandas as pd
from bs4 import BeautifulSoup


def crawling_pelicana():
    results = []
    for page in count(1):

        url = "https://pelicana.co.kr/store/stroe_search.html?amp;branch_name=&amp;gu=&amp;si=&page=" + str(page)
        try:
            request = Request(url)
            context = ssl._create_unverified_context()
            response = urlopen(request, context=context)
            receive = response.read()
            html = receive.decode('utf-8', errors='replace')
            print(f'{datetime.datetime.now()} : success for request [{url}]')
        except Exception as e:
            print(f'{datetime.datetime.now()} : {e}', file=sys.stderr)

        # print(html)
        bs = BeautifulSoup(html, 'html.parser')
        tag_table = bs.find('table', attrs={'class': 'table'})
        tag_tbody = tag_table.find('tbody')
        tags_tr = tag_tbody.findAll('tr')

        # 끝 검출
        if len(tags_tr) == 0:
            print("=====end====")
            break

        for tag_tr in tags_tr:
            strings = list(tag_tr.strings)
            name = strings[1]
            address = strings[3]
            sidogu = address.split(' ')[:2]
            results.append((name, address) + tuple(sidogu))

    # store
    table = pd.DataFrame(results, columns=['name', 'address', 'sido', 'gugun'])
    table.to_csv('__results__/pelicana.csv', encoding="utf-8", mode="w", index=True)


def crawling_nene():
    results = []
    pre_shop_name = ""
    for page in count(1):
        url = "https://nenechicken.com/17_new/sub_shop01.asp?IndexSword=&GUBUN=A&page=" + str(page)

        try:
            request = Request(url)
            response = urlopen(request)
            receive = response.read()
            html = receive.decode('utf-8')
            print(f'{datetime.datetime.now()} : success for request [{url}]')
        except Exception as e:
            print(f'{datetime.datetime.now()} : {e}', file=sys.stderr)

        bs = BeautifulSoup(html, 'html.parser')
        tag_table = bs.find('div', attrs={'class': 'shopWrap'})
        tag_shopinfos = tag_table.findAll('div', attrs={'class': 'shop'})

        now_shop_name = tag_shopinfos[0].find('div', attrs={'class': 'shopName'}).text

        if now_shop_name == pre_shop_name :
            print("========end========")
            break

        pre_shop_name = now_shop_name

        for tag_shopinfo in tag_shopinfos:
            name = tag_shopinfo.find('div', attrs={'class': 'shopName'}).text
            address = tag_shopinfo.find('div', attrs={'class': 'shopAdd'}).text
            sidogu = address.split(' ')[:2]
            results.append((name, address) + tuple(sidogu))


    # store
    table = pd.DataFrame(results, columns=['name', 'address', 'sido', 'gugun'])
    table.to_csv('__results__/nene.csv', encoding="utf-8", mode="w", index=True)


if __name__ == "__main__":
    # crawling_pelicana()
    crawling_nene()
