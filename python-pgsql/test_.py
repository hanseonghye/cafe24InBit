import psycopg2
import config


def test_insert():
    try:
        conn = psycopg2.connect(**config.db)
        cursor = conn.cursor()
        cursor.execute("insert into guestbook values(default,'a','b','c',now())")
    except Exception as e:
        print('error : %s ' % e)
    finally:
        'cursor' in locals() and cursor and cursor.close()
        'conn' in locals() and conn and (conn.commit() or conn.close())


def test_select():
    try:
        conn = psycopg2.connect(**config.db)
        cursor = conn.cursor()
        cursor.execute('select * from guestbook ')
        print(cursor.fetchone())
    except Exception as e:
        print('error : %s ' % e)
    finally:
        'cursor' in locals() and cursor and cursor.close()
        'conn' in locals() and conn and (conn.commit() or conn.close())


def test_delete():
    try:
        conn = psycopg2.connect(**config.db)
        cursor = conn.cursor()
        cursor.execute('delect  from guestbook where ')
    except Exception as e:
        print('error : %s ' % e)
    finally:
        'cursor' in locals() and cursor and cursor.close()
        'conn' in locals() and conn and (conn.commit() or conn.close())


def main():
    test_insert()
    test_select()
    # print("=====================")
    #
    # test_delete()
    # test_select()
    # print("=====================")


if __name__ == "__main__":
    main()
