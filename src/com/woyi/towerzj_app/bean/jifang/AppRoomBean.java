package com.woyi.towerzj_app.bean.jifang;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 此类描述的是：机房
 * 
 * @author: 罗然
 * @version: 2015-7-19 下午5:20:26
 * @ClassName: AppRoomBean
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.bean.jifang
 */
public class AppRoomBean implements Serializable {
	private String id; // 流水号
	private String physicCode; // 物理编码==原始基站id
	private String code; // 资产编号
	private String name; // 名称
	private String lx; // 类型
	private String jg; // 结构
	private Double mj; // 面积
	private String lxCheck; // 类型验(图片名称)
	private String jgCheck; // 结构验（图片是否上传成功 1：成功 0：不成功）
	private Double mjCheck; // 面积验
	private String ewm; // 二维码
	private String checkUserId;// 盘查用户
	private Date checkDate; // 盘查日期
	private String status; // 逻辑状态
	private String assetNum; // 资产编号
	private String codeLink;

	private String dbBh;    //电表编号      
	private Double dbDs;        //电表数      
	private String jfYs;        //机房钥匙  1门禁卡   2通用钥匙   3专用钥匙    4物业钥匙   
	private String jfYsJj;    //机房钥匙是否交接   1  是   2  否

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

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public Double getMj() {
		return mj;
	}

	public void setMj(Double mj) {
		this.mj = mj;
	}

	public String getLxCheck() {
		return lxCheck;
	}

	public void setLxCheck(String lxCheck) {
		this.lxCheck = lxCheck;
	}

	public String getJgCheck() {
		return jgCheck;
	}

	public void setJgCheck(String jgCheck) {
		this.jgCheck = jgCheck;
	}

	public Double getMjCheck() {
		return mjCheck;
	}

	public void setMjCheck(Double mjCheck) {
		this.mjCheck = mjCheck;
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

	public String getDbBh() {
		return dbBh;
	}

	public void setDbBh(String dbBh) {
		this.dbBh = dbBh;
	}

	public Double getDbDs() {
		return dbDs;
	}

	public void setDbDs(Double dbDs) {
		this.dbDs = dbDs;
	}

	public String getJfYs() {
		return jfYs;
	}

	public void setJfYs(String jfYs) {
		this.jfYs = jfYs;
	}

	public String getJfYsJj() {
		return jfYsJj;
	}

	public void setJfYsJj(String jfYsJj) {
		this.jfYsJj = jfYsJj;
	}

}
