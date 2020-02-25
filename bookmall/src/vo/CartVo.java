package vo;

public class CartVo {
	private Long no;
	private Long member_no;
	private int price;
	
	private String title;
	private int count;
	
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String myToString() {
		return "CartVo [price=" + price + ", title=" + title + ", count=" + count + "]";
	}
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", member_no=" + member_no + ", price=" + price + ", title=" + title + ", count="
				+ count + "]";
	}
	
}
