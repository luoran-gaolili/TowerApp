package com.woyi.towerzj_app.bean.kongtiao;

import java.util.Date;

/**
 * 
* �����������ǣ��յ���Ӫ��
* @author: ��Ȼ
* @version: 2015-7-19 ����5:58:52
* @ClassName: AppAirComBean 
* @��Ŀ�� towerzj_app
* @����com.woyi.towerzj_app.bean.kongtiao
 */
public class AppAirComBean{
	private String id;				//��ˮ��
	private String physicCode;		//����2
	private String code;			//����
	private String name;			//����
	private String cjCheck;			//������
	private String cj;				//����
	private String checkUserId;		//�̲��û�
	private Date   checkDate;		//�̲�����
	private String 	type;		//��Ӫ�����
	private String 	checkValue; //�̲���
	
	private String linkCode;	//�����������code
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
