package vo;

public class CartBookVo {
	private int count;
	private Long cart_no;
	private Long book_no;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getCart_no() {
		return cart_no;
	}
	public void setCart_no(Long cart_no) {
		this.cart_no = cart_no;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	@Override
	public String toString() {
		return "CartBookVo [count=" + count + ", cart_no=" + cart_no + ", book_no=" + book_no + "]";
	}
	
	public String myToString() {
		return toString();
	}
	
}
