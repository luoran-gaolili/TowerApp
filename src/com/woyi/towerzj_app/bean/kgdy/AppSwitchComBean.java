package com.woyi.towerzj_app.bean.kgdy;

import java.util.Date;

/**
 * 
* �����������ǣ����ص�Դ��Ӫ��
* @author: ��Ȼ
* @version: 2015-7-20 ����9:25:03
* @ClassName: AppSwitchComBean 
* @��Ŀ�� towerzj_app
* @����com.woyi.towerzj_app.bean.kgdy
 */
public class AppSwitchComBean{
	private String 	id;			//��ˮ��
	private String 	physicCode;	//����2
	private String 	code;		//����
	private String 	name;		//����
	private String 	pp;			//Ʒ��
	private Double 	rl;			//����	DEC(12,2)
	private String 	ppCheck;	//Ʒ����
	private Double 	rlCheck;	//������	DEC(12,2)
	private String 	checkUserId;//�̲��û�
	private Date 	checkDate;	//�̲�����
	private String 	type;		//��Ӫ�����
	private String 	checkValue; //�̲���
	
	private String linkCode;	//������������code
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPp() {
		return pp;
	}
	public void setPp(String pp) {
		this.pp = pp;
	}
	public Double getRl() {
		return rl;
	}
	public void setRl(Double rl) {
		this.rl = rl;
	}
	public String getPpCheck() {
		return ppCheck;
	}
	public void setPpCheck(String ppCheck) {
		this.ppCheck = ppCheck;
	}
	public Double getRlCheck() {
		return rlCheck;
	}
	public void setRlCheck(Double rlCheck) {
		this.rlCheck = rlCheck;
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