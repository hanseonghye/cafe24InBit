package vo;

import java.sql.Timestamp;

public class OrderVo {
	private Long no;

	private int price;
	private Timestamp date;

	private Long member_no;
	private Long address_no;

	private String member_name;
	private String member_email;
	private String address;

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Long getMember_no() {
		return member_no;
	}

	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}

	public Long getAddress_no() {
		return address_no;
	}

	public void setAddress_no(Long address_no) {
		this.address_no = address_no;
	}

	public String myToString() {
		return "OrderVo [no=" + no + ", price=" + price + ", member_name=" + member_name + ", member_email="
				+ member_email + ", address=" + address + "]";
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", price=" + price + ", date=" + date + ", member_no=" + member_no
				+ ", address_no=" + address_no + ", member_name=" + member_name + ", member_email=" + member_email
				+ ", address=" + address + "]";
	}

}
