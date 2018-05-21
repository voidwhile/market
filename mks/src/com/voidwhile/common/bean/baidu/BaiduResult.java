package com.voidwhile.common.bean.baidu;

public class BaiduResult {
	private BaiduLocation location;
	private String formatted_address;
	private String business;
	private BaiduAddressComponent addressComponent;
	private int cityCode;

	public BaiduLocation getLocation() {
		return location;
	}

	public void setLocation(BaiduLocation location) {
		this.location = location;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public BaiduAddressComponent getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(BaiduAddressComponent addressComponent) {
		this.addressComponent = addressComponent;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

}
