from http.client import HTTPConnection

url = "www.example.com"
conn = HTTPConnection(url)

conn.request('GET', '/')
resp = conn.getresponse()
print(resp.status, resp.reason)

# 성공
# GET / HTTP/1.1
# 200 ok
if resp.status == 200:
    body = resp.read()
    print(body, type(body))

# 실패
# GET /hello.html HTTP/1.1
# 404 not found
conn.request('GET', '/hello.html')
resp = conn.getresponse()
print(resp.status, resp.reason)

