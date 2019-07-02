import os
import ssl
import sys
import time
from itertools import count
from urllib.request import Request, urlopen
import datetime
from collection.crawler import crawling
from selenium import webdriver
# import pandas as pd
from bs4 import BeautifulSoup


def crawling_pelicana():
    results = []
    for page in count(1):

        url = "https://pelicana.co.kr/store/stroe_search.html?amp;branch_name=&amp;gu=&amp;si=&page=" + str(page)
        crawling(url)
        try:
            request = Request(url)
            context = ssl._create_unverified_context()
            response = urlopen(request, context=context)
            receive = response.read()
            html = receive.decode('utf-8', errors='replace')
            print(f'{datetime.datetime.now()} : success for request [{url}]')
        except Exception as e:
            print(f'{datetime.datetime.now()} : {e}', file=sys.stderr)

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
    # table = pd.DataFrame(results, columns=['name', 'address', 'sido', 'gugun'])
    # table.to_csv('__results__/pelicana.csv', encoding="utf-8", mode="w", index=True)


def crawling_nene():
    results = []
    pre_shop_name = ""
    for page in range(1, 5):
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

        if now_shop_name == pre_shop_name:
            print("========end========")
            break

        pre_shop_name = now_shop_name

        for tag_shopinfo in tag_shopinfos:
            name = tag_shopinfo.find('div', attrs={'class': 'shopName'}).text
            address = tag_shopinfo.find('div', attrs={'class': 'shopAdd'}).text
            sidogu = address.split(' ')[:2]
            results.append((name, address) + tuple(sidogu))

    # store
    # table = pd.DataFrame(results, columns=['name', 'address', 'sido', 'gugun'])
    # BASE_DIR = os.path.dirname(os.path.abspath(__file__))
    # # table.to_csv(f'{BASE_DIR}/__results__/nene.csv', encoding="utf-8", mode="w", index=True)
    # table.to_csv(f'/root/crawling-results/nene.csv', encoding="utf-8", mode="w", index=True)


def crawling_kyochon():
    results = []
    for sido1 in range(1, 2):
        for sido2 in count(start=1):
            url = f'http://www.kyochon.com/shop/domestic.asp?sido1={sido1}&sido2={sido2}&txtsearch='
            html = crawling(url)

            if html is None:
                break

            bs = BeautifulSoup(html, 'html.parser')
            tag_ul = bs.find('ul', attrs={'class': 'list'})
            tag_spans = tag_ul.findAll('span', attrs={'class': 'store_item'})

            for tag_span in tag_spans:
                name = tag_span.find('strong').text
                address = (tag_span.find('em').text).strip('\r\n\t')
                sidogu = address.split(' ')[:2]
                results.append((name, address) + tuple(sidogu))

    # store
    # table = pd.DataFrame(results, columns=['name', 'address', 'sido', 'gugun'])
    # table.to_csv('__results__/kyochon.csv', encoding="utf-8", mode="w", index=True)
    # print("======end==========")


def crawling_goobne():
    url = 'http://goobne.co.kr/store/search_store.jsp'
    wd = webdriver.Chrome('chromedriver.exe')
    wd.get(url)
    time.sleep(5)

    results = []
    for page in range(1, 10):
        script = 'store.getList(%d)' % page
        wd.execute_script(script)
        print(f'{datetime.datetime.now()} : success for request [{script}]')
        time.sleep(3)

        html = wd.page_source

        bs = BeautifulSoup(html, 'html.parser')
        tag_tbody = bs.find('tbody', attrs={'id': 'store_list'})
        tags_tr = tag_tbody.findAll('tr')

        if tags_tr[0].get('class') is None:
            print("============end=============")
            break

        for tag_tr in tags_tr:
            strings = list(tag_tr.strings)
            name = strings[1]
            address = strings[6]
            sidogu = address.split()[:2]
            results.append((name, address) + tuple(sidogu))

    wd.quit()
    # store
    # table = pd.DataFrame(results, columns=['name', 'address', 'sido', 'gugun'])
    # table.to_csv('__results__/goobne.csv', encoding="utf-8", mode="w", index=True)


if __name__ == "__main__":
    # crawling_pelicana()
    crawling_nene()
    # crawling_kyochon()
    # crawling_goobne()
