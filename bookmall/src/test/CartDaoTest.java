package test;

import java.util.List;


import dao.CartDao;
import vo.CartVo;

public class CartDaoTest {
	public static void main(String[] args) {
//		insertTest(1L,0);
//		insertTest(1L,0);
		getListTest();
	}
	public static void getListTest() {
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList();
		
		for(CartVo vo : list) {
			System.out.println(vo.myToString());
		}
	}

	public static void insertTest(Long member_no, int price) {
		CartVo vo =new CartVo();
		vo.setMember_no(member_no);
		vo.setPrice(price);
		new CartDao().insert(vo);
	}
}
