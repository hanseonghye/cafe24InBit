package test;

import java.util.List;

import dao.OrderBookDao;
import vo.OrderBookVo;

public class OrderBookDaoTest {
	public static void main(String[] args) {
		insertTest(3,1L,1L);
		getListTest();
	}
	public static void getListTest() {
		OrderBookDao dao = new OrderBookDao();
		List<OrderBookVo> list = dao.getList();
		
		for(OrderBookVo vo : list) {
			System.out.println(vo.myToString());
		}
	}

	public static void insertTest(int count, Long book_no, Long order_no) {
		OrderBookVo vo =new OrderBookVo();
		vo.setCount(count);
		vo.setBook_no(book_no);
		vo.setOrder_no(order_no);
		new OrderBookDao().insert(vo);
	}
}
