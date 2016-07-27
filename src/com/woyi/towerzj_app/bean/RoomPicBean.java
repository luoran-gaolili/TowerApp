package com.woyi.towerzj_app.bean;

import java.util.Date;

/**
 * 
* 此类描述的是：机房图片表
* @author: 罗然
* @version: 2015-8-14 下午2:16:20
* @ClassName: RoomPicBean 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean
 */
public class RoomPicBean {
	private String id;
	private String physicCode;
	private String picAddr;
	private String createUserId;
	private Date createDate;
	private String demo;
	private String status;//上传状态：上传成功=1，失败=2

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

	public String getPicAddr() {
		return picAddr;
	}

	public void setPicAddr(String picAddr) {
		this.picAddr = picAddr;
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
