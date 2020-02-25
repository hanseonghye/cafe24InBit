package test;

public class BookMallApp {
	public static void main(String[] args) {

		///////////////////////////////////////////////////
		////////////// 데이터 insert ///////////////////////
		/////////////////////////////////////////////////
		//InsertAll();
		
		///////////////////////////////////////////////////
		////////////// select 결과 출력 /////////////////////
		/////////////////////////////////////////////////
		SelectAll();
	}
	
	private static void InsertAll() {
		//insert member
		MemberDaoTest.insertTest("모모", "momo@naver.com", "momopw", "010-0000-0000");
		MemberDaoTest.insertTest("사나", "sana@naver.com", "sanapw", "010-0000-1111");
		
		// insert category
		CategoryDaoTest.insertTest("참고서");
		CategoryDaoTest.insertTest("IT");
		CategoryDaoTest.insertTest("소설");
		
		//insert book
		BookDaoTest.insertTest("수학의 정석1", 25000, 1L);
		BookDaoTest.insertTest("수학의 정석2", 30000, 1L);
		BookDaoTest.insertTest("power java", 30000, 2L);
		
		//insert address
		AddressDaoTest.insertTest(1L, "부산광역시 북구 구포동");
		AddressDaoTest.insertTest(2L, "서울시 역삼동");
		
		//insert default order
		OrderDaoTest.insertTest(1L, 0, 1L);
		
		//insert order book
		OrderBookDaoTest.insertTest(1, 1L, 1L);
		OrderBookDaoTest.insertTest(2,3L, 1L);
	
		//insert default cart
		CartDaoTest.insertTest(1L, 0);
		
		//insert cart book
		CartBookDaoTest.insertTest(1, 2L, 1L);
	}
	
	private static void SelectAll()
	{
		System.out.println("**** 회원 ****");
		MemberDaoTest.getListTest();
		System.out.println();
		
		System.out.println("**** 카테고리 ****");
		CategoryDaoTest.getListTest();
		System.out.println();

		System.out.println("**** 도서 ****");
		BookDaoTest.getListTest();
		System.out.println();
		
		System.out.println("**** 카트 ****");
		CartDaoTest.getListTest();
		System.out.println();
		
		System.out.println("**** 주문 ****");
		OrderDaoTest.getListTest();
		System.out.println();
		
		System.out.println("**** 주문 도서 ****");
		OrderBookDaoTest.getListTest();
		System.out.println();
	}
	
}
