package test;

import java.util.List;

import dao.AddressDao;
import vo.AddressVo;


public class AddressDaoTest {

	public static void main(String[] args) {
		insertTest(1L, "seoul");
		getListTest();
	}
	public static void getListTest() {
		AddressDao dao = new AddressDao();
		List<AddressVo> list = dao.getList();
		
		for(AddressVo vo : list) {
			System.out.println(vo.myToString());
		}
	}

	public static void insertTest(Long member_no, String address) {
		AddressVo vo =new AddressVo();
		vo.setMember_no(member_no);
		vo.setAddress(address);
		new AddressDao().insert(vo);
	}

}
