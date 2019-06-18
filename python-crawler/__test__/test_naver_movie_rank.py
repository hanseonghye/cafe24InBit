from urllib.request import Request, urlopen
from bs4 import BeautifulSoup

request = Request('https://movie.naver.com/movie/sdb/rank/rmovie.nhn')
response = urlopen(request)
html = response.read().decode('cp949')
# print(html)

bs = BeautifulSoup(html, 'html.parser')
divs = bs.findAll('div', attrs={'class': 'tit3'})
# print(divs)

for i, div in enumerate(divs):
    print(i + 1, div.a.text, div.a['href'], sep=' ')
