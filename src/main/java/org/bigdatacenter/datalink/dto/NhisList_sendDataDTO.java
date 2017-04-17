package org.bigdatacenter.datalink.dto;

public class NhisList_sendDataDTO {

	private String IRB;
	private String rName;
	private String requestORG;
	private String linkedORG;
	private int sendData;
	private int takeLinkNum;
	
	public NhisList_sendDataDTO(String IRB, String rName, String requestORG, String linkedORG, int sendData, int takeLinkNum){
		this.IRB = IRB;
		this.rName = rName;
		this.requestORG = requestORG;
		this.linkedORG = linkedORG;
		this.sendData = sendData;
		this.takeLinkNum = takeLinkNum;
	}
	
	
	
	public int getTakeLinkNum() {
		return takeLinkNum;
	}



	public void setTakeLinkNum(int takeLinkNum) {
		this.takeLinkNum = takeLinkNum;
	}



	public String getIRB() {
		return IRB;
	}
	public void setIRB(String iRB) {
		IRB = iRB;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getRequestORG() {
		return requestORG;
	}
	public void setRequestORG(String requestORG) {
		this.requestORG = requestORG;
	}
	public String getLinkedORG() {
		return linkedORG;
	}
	public void setLinkedORG(String linkedORG) {
		this.linkedORG = linkedORG;
	}
	public int getSendData() {
		return sendData;
	}
	public void setSendData(int sendData) {
		this.sendData = sendData;
	}
	
	
}
