package test;

import java.util.List;

import dao.OrderDao;
import vo.OrderVo;


public class OrderDaoTest {

	public static void main(String[] args) {
//		insertTest(1L,0,1L);
		getListTest();
	}
	
	public static void getListTest() {
		OrderDao dao = new OrderDao();
		List<OrderVo> list = dao.getList();
		
		for(OrderVo vo : list) {
			System.out.println(vo.myToString());
		}
	}

	public static void insertTest(Long member_no, int price, Long addresses_no) {
		OrderVo vo =new OrderVo();
		vo.setMember_no(member_no);
		vo.setPrice(price);
		vo.setAddress_no(addresses_no);
		new OrderDao().insert(vo);
	}

}
