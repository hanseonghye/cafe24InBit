import psycopg2

# conn = None

try:
    conn = psycopg2.connect(
        user='webdb',
        password='webdb',
        host='192.168.1.45',
        port='5432',
        database='webdb'
    )

    cursor = conn.cursor()
    cursor.execute('select version()')
    record = cursor.fetchone()

    print('connected to - ',record)

except Exception as e:
    print(f'error : {e}')
finally:
    'conn' in locals() and conn and conn.close()
    'cursor' in locals() and cursor and cursor.close()
