from urllib.parse import urlencode
from urllib.request import urlopen, Request

# get
http_response = urlopen('http://www.example.com?a=10&b=20')
body = http_response.read()
# print(body)

# post
data = {
    'email': 'hans@naver.com',
    'password': 'password',
    'name': '한성혜'
}

data = urlencode(data).encode("utf-8")
# print(data)

request = Request('http://www.example.com', data)
request.add_header('Content-Type', 'text/html')

httpresponse = urlopen(request)
print(http_response.read())
