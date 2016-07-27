package com.woyi.towerzj_app.bean;

import java.util.Date;

/**
 * 
* 此类描述的是：机房是否满足需求
* @author: 罗然
* @version: 2015-8-14 下午2:16:02
* @ClassName: RoomItemBean 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean
 */
public class RoomItemBean {
	private String id;
	private String physicCode;
	private String isok;
	private String createUserId;
	private Date createDate;
	private String demo;
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhysicCode() {
		return physicCode;
	}

	public void setPhysicCode(String physicCode) {
		this.physicCode = physicCode;
	}

	public String getIsok() {
		return isok;
	}

	public void setIsok(String isok) {
		this.isok = isok;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
