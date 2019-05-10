package test;

import java.util.List;

import dao.MemberDao;
import vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		insertTest("사나","sana@naver.com","sanapw","010");
		insertTest("모모", "momo@naver.com", "momopw","010");
		getListTest();
	}
	
	public static void getListTest() {
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		
		for(MemberVo vo : list) {
			System.out.println(vo.myToString());
		}
	}

	public static void insertTest(String name, String email, String pw, String phone) {
		MemberVo vo =new MemberVo();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPw(pw);
		vo.setPhone(phone);
		new MemberDao().insert(vo);
	}
	
}
