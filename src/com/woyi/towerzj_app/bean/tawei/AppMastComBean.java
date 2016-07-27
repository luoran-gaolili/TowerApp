package com.woyi.towerzj_app.bean.tawei;

import java.util.Date;

/**
 * 
* 此类描述的是：塔桅-运营商
* @author: 罗然
* @version: 2015-7-19 下午3:06:43
* @ClassName: AppMastComBean 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.tawei
 */
public class AppMastComBean{
	private String 	id;			//流水号
	private String 	physicCode;	//
	private String 	code;		//资产编号
	private String 	xs;			//铁塔形式
	private String 	lx;			//铁塔类型
	private Double 	tg;			//铁塔塔高
	private String 	xsCheck;	//铁塔形式验
	private String 	lxCheck;	//铁塔类型验
	private Double 	tgCheck;	//铁塔塔高验
	private String 	checkUserId;//盘查用户
	private Date 	checkDate;	//盘查日期
	private String 	type;		//运营商类别
	private String 	checkValue; //盘查结果
	
	private String linkCode;	//关联铁塔表的code
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheckValue() {
		return checkValue;
	}
	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}
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
	public String getXs() {
		return xs;
	}
	public void setXs(String xs) {
		this.xs = xs;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public Double getTg() {
		return tg;
	}
	public void setTg(Double tg) {
		this.tg = tg;
	}
	public String getXsCheck() {
		return xsCheck;
	}
	public void setXsCheck(String xsCheck) {
		this.xsCheck = xsCheck;
	}
	public String getLxCheck() {
		return lxCheck;
	}
	public void setLxCheck(String lxCheck) {
		this.lxCheck = lxCheck;
	}
	public Double getTgCheck() {
		return tgCheck;
	}
	public void setTgCheck(Double tgCheck) {
		this.tgCheck = tgCheck;
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
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}
	 
}
