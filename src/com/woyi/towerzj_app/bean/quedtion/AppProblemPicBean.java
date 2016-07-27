package com.woyi.towerzj_app.bean.quedtion;

import java.util.Date;

/**
 * 
* 此类描述的是：问题图片
* @author: 罗然
* @version: 2015-7-20 上午10:20:01
* @ClassName: AppProblemPicBean 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.quedtion
 */
public class AppProblemPicBean {
	
	private String 	id;
	private String 	type;	//类型
	private String 	code;	//编码
	private String 	physicCode;//物理站编码
	private String 	pic_id;//图片流水
	private int 	picSeq;//图片序号
	private String 	picAddr;//图片地址
	private String 	checkCode;
	private String 	checkUserId;
	private Date 	checkDate;
	private Date 	createDate;
	private String 	createUserId;
	private String  uploadStatus;//上传状态：上传成功=1，失败=2
	private String 	codeLink;//资产编号_原始
	private String linkCode;	//关联铁塔表的code
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhysicCode() {
		return physicCode;
	}
	public void setPhysicCode(String physicCode) {
		this.physicCode = physicCode;
	}
	public String getPic_id() {
		return pic_id;
	}
	public void setPic_id(String pic_id) {
		this.pic_id = pic_id;
	}
	public String getPicAddr() {
		return picAddr;
	}
	public void setPicAddr(String picAddr) {
		this.picAddr = picAddr;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCodeLink() {
		return codeLink;
	}
	public void setCodeLink(String codeLink) {
		this.codeLink = codeLink;
	}
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}
	public int getPicSeq() {
		return picSeq;
	}
	public void setPicSeq(int picSeq) {
		this.picSeq = picSeq;
	}
	
	
}
