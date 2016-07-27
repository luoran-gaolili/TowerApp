package com.woyi.towerzj_app.bean.kongtiao;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* 此类描述的是：空调
* @author: 罗然
* @version: 2015-7-19 下午5:58:31
* @ClassName: AppAirBean 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.kongtiao
 */
public class AppAirBean implements Serializable{
	private String id;				//流水号
	private String physicCode;		//编码2
	private String code;			//编码
	private String name;			//名称
	private String cjCheck;			//厂家验
	private String cj;				//厂家
	private String ewm;				//二维码
	private String checkUserId;		//盘查用户
	private Date   checkDate;		//盘查日期
	private String status; // 逻辑状态
    private String codeLink;
	
	public String getCodeLink() {
		return codeLink;
	}
	public void setCodeLink(String codeLink) {
		this.codeLink = codeLink;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String assetNum;		//资产编号
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCjCheck() {
		return cjCheck;
	}
	public void setCjCheck(String cjCheck) {
		this.cjCheck = cjCheck;
	}
	public String getCj() {
		return cj;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
	public String getEwm() {
		return ewm;
	}
	public void setEwm(String ewm) {
		this.ewm = ewm;
	}
	public String getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getAssetNum() {
		return assetNum;
	}
	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}
	 
	 
}
