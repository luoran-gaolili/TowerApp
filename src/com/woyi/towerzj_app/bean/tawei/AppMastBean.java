package com.woyi.towerzj_app.bean.tawei;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* �����������ǣ���Φ
* @author: ��Ȼ
* @version: 2015-7-19 ����3:06:29
* @ClassName: AppMastBean 
* @��Ŀ�� towerzj_app
* @����com.woyi.towerzj_app.bean.tawei
 */
public class AppMastBean implements Serializable{
	private String 	id;			//��ˮ��
	private String 	physicCode;	//
	private String name;//����
	private String 	code;		//�ʲ����
	private String 	xs;			//������ʽ
	private String 	lx;			//��������
	private Double 	tg;			//��������
	private String 	xsCheck;	//������ʽ��
	private String 	lxCheck;	//����������
	private Double 	tgCheck;	//����������
	private String 	ewm;		//��ά��
	private String 	checkUserId;//�̲��û�
	private Date 	checkDate;	//�̲�����
	private String status; // �߼�״̬
	private String assetNum;		//�ʲ����
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
}
