package chen.yiheng.estore.domain;

import java.sql.Timestamp;
import java.util.List;

public class Order {
	private String id;
	private int uid;
	private double totalPrice;
	private String address;
	private int status=0;
	private Timestamp createTime;
	private List<OrderItem> orderItems;
	private String receiver;
	private String telephone;
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", uid=" + uid + ", totalPrice="
				+ totalPrice + ", address=" + address + ", status=" + status
				+ ", createTime=" + createTime + ", orderItems=" + orderItems
				+ ", receiver=" + receiver + ", telephone=" + telephone + "]";
	}
	

}
