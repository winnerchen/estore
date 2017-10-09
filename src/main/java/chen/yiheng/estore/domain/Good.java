package chen.yiheng.estore.domain;

public class Good {
	private int id;
	private String name;
	private double marketPrice;
	private double estoreprice; // 商城价格
	private String category;
	private int num;
	private String imgUrl;
	private String description;
	private int buynum;
	private int totalsal;
	
	
	

	public int getTotalsal() {
		return totalsal;
	}

	public void setTotalsal(int totalsal) {
		this.totalsal = totalsal;
	}

	public int getBuynum() {
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public double getEstoreprice() {
		return estoreprice;
	}

	public void setEstoreprice(double estoreprice) {
		this.estoreprice = estoreprice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
