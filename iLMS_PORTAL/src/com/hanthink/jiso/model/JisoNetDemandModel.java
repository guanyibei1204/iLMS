package com.hanthink.jiso.model;

import com.hotent.base.core.model.AbstractModel;

public class JisoNetDemandModel extends AbstractModel<String>{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5987398995864366058L;
	
	protected String id;

	/**
	 * 信息点
     */
	protected String planCode; 
	
	protected String factoryCode;
	
	/**
	 * 零件组代码
     */
	protected String partgroupNo; 
	
	/**
	 * 产品订单号
     */
	protected String orderNo; 
	
	/**
	 * 供应商出货地
     */
	protected String supFactory; 
	
	/**
	 * 零件号
     */
	protected String partNo; 
	
	/**
	 * 简号
	 */
	protected String partShortNo;
	
	/**
	 * 零件名称
	 */
	protected String partNameCn;
	
	/**
	 * 需求数量
     */
	protected String requireNum; 
	
	/**
	 * 零件记号
     */
	protected String partMark; 
	
	/**
	 * 车型
     */
	protected String modelCode; 
	
	/**
	 * 生产阶段
     */
	protected String phase; 
	
	/**
	 * 创建时间
     */
	protected String creationTime;
	
	/**用户id**/
	protected String userId;
	/**用户类型**/
	protected String userType;
	/**供应商代码**/
	protected String supplierNo;
	/**下线时间**/
	protected String offlineTime;
	/**下线时间开始**/
	protected String offlineTimeStart;
	/**下线时间结束**/
	protected String offlineTimeEnd;
	/**到货仓库**/
	protected String arrDepot;

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getPartgroupNo() {
		return partgroupNo;
	}

	public void setPartgroupNo(String partgroupNo) {
		this.partgroupNo = partgroupNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSupFactory() {
		return supFactory;
	}

	public void setSupFactory(String supFactory) {
		this.supFactory = supFactory;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getPartShortNo() {
		return partShortNo;
	}

	public void setPartShortNo(String partShortNo) {
		this.partShortNo = partShortNo;
	}

	public String getPartNameCn() {
		return partNameCn;
	}

	public void setPartNameCn(String partNameCn) {
		this.partNameCn = partNameCn;
	}

	public String getRequireNum() {
		return requireNum;
	}

	public void setRequireNum(String requireNum) {
		this.requireNum = requireNum;
	}

	public String getPartMark() {
		return partMark;
	}

	public void setPartMark(String partMark) {
		this.partMark = partMark;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getOfflineTimeStart() {
		return offlineTimeStart;
	}

	public void setOfflineTimeStart(String offlineTimeStart) {
		this.offlineTimeStart = offlineTimeStart;
	}

	public String getOfflineTimeEnd() {
		return offlineTimeEnd;
	}

	public void setOfflineTimeEnd(String offlineTimeEnd) {
		this.offlineTimeEnd = offlineTimeEnd;
	}

	public String getArrDepot() {
		return arrDepot;
	}

	public void setArrDepot(String arrDepot) {
		this.arrDepot = arrDepot;
	}

	public String getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	
	
}