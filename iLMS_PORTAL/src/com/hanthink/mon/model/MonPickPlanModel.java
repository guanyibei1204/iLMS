package com.hanthink.mon.model;


import com.hotent.base.core.model.AbstractModel;



public class MonPickPlanModel extends AbstractModel<String>{

	
	private String factoryCode;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String supplierNo;
	private String supplierName;
	private String wareCode;
	private String warehouseName;
	private String twoToFour;
	private String fourToSix;
	private String sixToEight;
	private String eightToTen;
	private String tenToTwelve;
	private String twelveToFortee;
	private String forteeToSixtee;
	private String sixteeToEightee;
	private String eighteeToTwentity;
	private String twentityToTwentityTwo;
	private String tweneityTwoToZero;
	private String zeroToTwo;
	
	
	private String orderNo;
	private String purchaseNo;
	private String pickTime;
	private String deliveryTime;
	private String partNo;
	private String partName;
	private String partShortNo;
	private String orderQty;
	private String totalRecQty;

	@Override
	public void setId(String id) {
		
	}

	@Override
	public String getId() {
		return null;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getWareCode() {
		return wareCode;
	}

	public void setWareCode(String wareCode) {
		this.wareCode = wareCode;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getTwoToFour() {
		return twoToFour;
	}

	public void setTwoToFour(String twoToFour) {
		this.twoToFour = twoToFour;
	}

	public String getFourToSix() {
		return fourToSix;
	}

	public void setFourToSix(String fourToSix) {
		this.fourToSix = fourToSix;
	}

	public String getSixToEight() {
		return sixToEight;
	}

	public void setSixToEight(String sixToEight) {
		this.sixToEight = sixToEight;
	}

	public String getEightToTen() {
		return eightToTen;
	}

	public void setEightToTen(String eightToTen) {
		this.eightToTen = eightToTen;
	}

	public String getTenToTwelve() {
		return tenToTwelve;
	}

	public void setTenToTwelve(String tenToTwelve) {
		this.tenToTwelve = tenToTwelve;
	}

	public String getTwelveToFortee() {
		return twelveToFortee;
	}

	public void setTwelveToFortee(String twelveToFortee) {
		this.twelveToFortee = twelveToFortee;
	}

	public String getForteeToSixtee() {
		return forteeToSixtee;
	}

	public void setForteeToSixtee(String forteeToSixtee) {
		this.forteeToSixtee = forteeToSixtee;
	}

	public String getSixteeToEightee() {
		return sixteeToEightee;
	}

	public void setSixteeToEightee(String sixteeToEightee) {
		this.sixteeToEightee = sixteeToEightee;
	}

	public String getEighteeToTwentity() {
		return eighteeToTwentity;
	}

	public void setEighteeToTwentity(String eighteeToTwentity) {
		this.eighteeToTwentity = eighteeToTwentity;
	}

	public String getTwentityToTwentityTwo() {
		return twentityToTwentityTwo;
	}

	public void setTwentityToTwentityTwo(String twentityToTwentityTwo) {
		this.twentityToTwentityTwo = twentityToTwentityTwo;
	}

	public String getTweneityTwoToZero() {
		return tweneityTwoToZero;
	}

	public void setTweneityTwoToZero(String tweneityTwoToZero) {
		this.tweneityTwoToZero = tweneityTwoToZero;
	}

	public String getZeroToTwo() {
		return zeroToTwo;
	}

	public void setZeroToTwo(String zeroToTwo) {
		this.zeroToTwo = zeroToTwo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getPickTime() {
		return pickTime;
	}

	public void setPickTime(String pickTime) {
		this.pickTime = pickTime;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getPartShortNo() {
		return partShortNo;
	}

	public void setPartShortNo(String partShortNo) {
		this.partShortNo = partShortNo;
	}

	public String getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}

	public String getTotalRecQty() {
		return totalRecQty;
	}

	public void setTotalRecQty(String totalRecQty) {
		this.totalRecQty = totalRecQty;
	}

	@Override
	public String toString() {
		return "MonReceiptModel [supplierNo=" + supplierNo + ", supplierName=" + supplierName + ", wareCode=" + wareCode
				+ ", warehouseName=" + warehouseName + ", twoToFour=" + twoToFour + ", fourToSix=" + fourToSix
				+ ", sixToEight=" + sixToEight + ", eightToTen=" + eightToTen + ", tenToTwelve=" + tenToTwelve
				+ ", twelveToFortee=" + twelveToFortee + ", forteeToSixtee=" + forteeToSixtee + ", sixteeToEightee="
				+ sixteeToEightee + ", eighteeToTwentity=" + eighteeToTwentity + ", twentityToTwentityTwo="
				+ twentityToTwentityTwo + ", tweneityTwoToZero=" + tweneityTwoToZero + ", zeroToTwo=" + zeroToTwo
				+ ", orderNo=" + orderNo + ", purchaseNo=" + purchaseNo + ", pickTime=" + pickTime + ", deliveryTime="
				+ deliveryTime + ", partNo=" + partNo + ", partName=" + partName + ", partShortNo=" + partShortNo
				+ ", orderQty=" + orderQty + ", totalRecQty=" + totalRecQty + "]";
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}


	

}