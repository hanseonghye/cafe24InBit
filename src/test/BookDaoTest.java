package test;

import java.util.List;

import dao.BookDao;
import vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		insertTest("요리잘하는법", 10000, 1L);
		insertTest("요리의세계", 10000, 1L);
		insertTest("수학의정석1",20000,2L);
		insertTest("수학의정석2",23000,2L);
		insertTest("수학의정석3",24000,2L);
		getListTest();
	}
	public static void getListTest() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		
		for(BookVo vo : list) {
			System.out.println(vo.myToString());
		}
	}

	public static void insertTest(String title, int price, Long category_no) {
		BookVo vo =new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategory_no(category_no);
		new BookDao().insert(vo);
	}
}
