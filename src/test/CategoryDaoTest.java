package test;

import java.util.List;

import dao.CategoryDao;
import vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		insertTest("요리");
		insertTest("공부");
		getListTest();
	}

	public static void getListTest() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		
		for(CategoryVo vo : list) {
			System.out.println(vo.myToString());
		}
	}

	public static void insertTest(String name) {
		CategoryVo vo =new CategoryVo();
		vo.setName(name);
		new CategoryDao().insert(vo);
	}
	
}
