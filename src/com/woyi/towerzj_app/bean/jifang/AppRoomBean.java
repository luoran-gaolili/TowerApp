package com.woyi.towerzj_app.bean.jifang;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * �����������ǣ�����
 * 
 * @author: ��Ȼ
 * @version: 2015-7-19 ����5:20:26
 * @ClassName: AppRoomBean
 * @��Ŀ�� towerzj_app
 * @����com.woyi.towerzj_app.bean.jifang
 */
public class AppRoomBean implements Serializable {
	private String id; // ��ˮ��
	private String physicCode; // �������==ԭʼ��վid
	private String code; // �ʲ����
	private String name; // ����
	private String lx; // ����
	private String jg; // �ṹ
	private Double mj; // ���
	private String lxCheck; // ������(ͼƬ����)
	private String jgCheck; // �ṹ�飨ͼƬ�Ƿ��ϴ��ɹ� 1���ɹ� 0�����ɹ���
	private Double mjCheck; // �����
	private String ewm; // ��ά��
	private String checkUserId;// �̲��û�
	private Date checkDate; // �̲�����
	private String status; // �߼�״̬
	private String assetNum; // �ʲ����
	private String codeLink;

	private String dbBh;    //�����      
	private Double dbDs;        //�����      
	private String jfYs;        //����Կ��  1�Ž���   2ͨ��Կ��   3ר��Կ��    4��ҵԿ��   
	private String jfYsJj;    //����Կ���Ƿ񽻽�   1  ��   2  ��

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
