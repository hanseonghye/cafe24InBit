s = """
<html>
    <body style='background-color:#ffff'>
        <h4>Click</h4>
        <a href='http://www.python.org'>Here</a>
        <p>
            To connect to the most powerful tolls int the world.
        </p>
    </body>
</html>"""

start_index, end_index = 0, 0

while True:
    start_index = s.find("<")

    if start_index == -1:
        break

    end_index = s.find(">", start_index)
    s = s[:start_index] + s[end_index + 1:]

print(s)
