package test;

import java.util.List;

import dao.CartBookDao;
import vo.CartBookVo;

public class CartBookDaoTest {
	public static void main(String[] args) {
		insertTest(2,2L,1L);
		getListTest();
	}
	public static void getListTest() {
		CartBookDao dao = new CartBookDao();
		List<CartBookVo> list = dao.getList();
		
		for(CartBookVo vo : list) {
			System.out.println(vo.myToString());
		}
	}

	public static void insertTest(int count, Long book_no, Long cart_no) {
		CartBookVo vo =new CartBookVo();
		vo.setCount(count);
		vo.setBook_no(book_no);
		vo.setCart_no(cart_no);
		new CartBookDao().insert(vo);
	}
}
