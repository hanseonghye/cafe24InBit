from urllib.request import Request, urlopen
from bs4 import BeautifulSoup
from collection.crawler import crawling


def ex01():
    request = Request('https://movie.naver.com/movie/sdb/rank/rmovie.nhn')
    response = urlopen(request)
    html = response.read().decode('cp949')
    # print(html)

    bs = BeautifulSoup(html, 'html.parser')
    divs = bs.findAll('div', attrs={'class': 'tit3'})
    # print(divs)

    for i, div in enumerate(divs):
        print(i + 1, div.a.text, div.a['href'], sep=' ')


def ex02():
    crawling(url='https://movie.naver.com/movie/sdb/rank/rmovie.nhn',
             encoding='cp949',
             proc1=proc_naver_movie_rank,
             proc2=lambda data: list(map(lambda data: print(data.a.text, data.a['href'], sep=' '),data))
             )


def proc_naver_movie_rank(data):
    bs = BeautifulSoup(data, 'html.parser')
    results = bs.findAll('div', attrs={'class': 'tit3'})
    return results


def store_naver_movie_rank(datas):
    for i, data in enumerate(datas):
        print(i + 1, data.a.text, data.a['href'], sep=' ')


def error(err):
    pass


# if __name__ == "__main__":
#     ex01()


__name__ == '__main__' and not \
    ex01() and not \
    ex02()
