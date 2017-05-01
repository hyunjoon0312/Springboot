package org.bigdatacenter.datalink.dto;

public class LinkedData_List_DTO {

	private String IRB;
	private String rName;
	private String requestORG;
	private String linkedORG;
	private int linkData;
	

	public LinkedData_List_DTO(String IRB, String rName, String requestORG, String linkedORG, int linkData){
		
		this.IRB = IRB;
		this.rName = rName;
		this.requestORG = requestORG;
		this.linkedORG = linkedORG;
		this.linkData = linkData;
		
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


	public int getLinkData() {
		return linkData;
	}


	public void setLinkData(int linkData) {
		this.linkData = linkData;
	}


	@Override
	public String toString() {
		return "LinkedData_List_DTO [IRB=" + IRB + ", rName=" + rName + ", requestORG=" + requestORG + ", linkedORG="
				+ linkedORG + ", linkData=" + linkData + "]";
	}
	
	
	
	
}
