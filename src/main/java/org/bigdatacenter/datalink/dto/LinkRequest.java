package org.bigdatacenter.datalink.dto;

public class LinkRequest {
	
	private String requestTime;
	private String rName;
	private String IRB;
	private String request_ORG;
	private String nhis;
	private String stat;
	private String cdc;
	private int createKey;
	private String secretKey;
	private int sendKey;
	
	public LinkRequest(){
		this("","","","","","","",0,"",0);
	}
	
	public LinkRequest(String requestTime, String rName, String IRB, String request_ORG, String nhis, String stat, String cdc, int createKey, String secretKey, int sendKey){
		this.requestTime = requestTime;
		this.rName = rName;
		this.IRB = IRB;
		this.request_ORG = request_ORG;
		this.nhis = nhis;
		this.stat = stat;
		this.cdc = cdc;
		this.createKey = createKey;
		this.secretKey = secretKey;
		this.sendKey = sendKey;
	}

	
	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getIRB() {
		return IRB;
	}

	public void setIRB(String iRB) {
		IRB = iRB;
	}

	public String getRequest_ORG() {
		return request_ORG;
	}

	public void setRequest_ORG(String request_ORG) {
		this.request_ORG = request_ORG;
	}

	public String getNhis() {
		return nhis;
	}

	public void setNhis(String nhis) {
		this.nhis = nhis;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getCdc() {
		return cdc;
	}

	public void setCdc(String cdc) {
		this.cdc = cdc;
	}

	public int getCreateKey() {
		return createKey;
	}

	public void setCreateKey(int createKey) {
		this.createKey = createKey;
	}
	
	
	public String getSecretKey() {
		return secretKey;
	}

	public void setsecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public int getSendKey() {
		return sendKey;
	}

	public void setSendKey(int sendKey) {
		this.sendKey = sendKey;
	}
	
}
