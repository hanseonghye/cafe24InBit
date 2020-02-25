package vo;

public class OrderBookVo {
	private int count;
	private Long book_no;
	private Long order_no;
	private String book_title;
	
	
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}

	public String myToString() {
		return "OrderBookVo [book_no=" + book_no + ", book_title=" + book_title +", count=" + count + "]";
	}
	
	@Override
	public String toString() {
		return "OrderBookVo [count=" + count + ", book_no=" + book_no + ", order_no=" + order_no + ", book_title="
				+ book_title + "]";
	}

	
}
