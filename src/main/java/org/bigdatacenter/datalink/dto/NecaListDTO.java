package org.bigdatacenter.datalink.dto;


public class NecaListDTO {
	
	private String IRB;
	private String rName;
	private String requestORG;
	private String linkedORG;
	private int sendLinknum;
	private int takePersonID;
	
	public NecaListDTO(String IRB, String rName, String requestORG, String linkedORG, int sendLinknum, int takePersonID){
		this.IRB = IRB;
		this.rName = rName;
		this.requestORG = requestORG;
		this.linkedORG = linkedORG;
		this.sendLinknum = sendLinknum;
		this.takePersonID = takePersonID;
	}

	



	public int getTakePersonID() {
		return takePersonID;
	}





	public void setTakePersonID(int takePersonID) {
		this.takePersonID = takePersonID;
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

	public int getSendLinknum() {
		return sendLinknum;
	}

	public void setSendLinknum(int sendLinknum) {
		this.sendLinknum = sendLinknum;
	}
	
	

}
