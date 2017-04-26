package org.bigdatacenter.datalink.dto;

public class LinkedDataListDTO {

	private int No;
	private String REPORT_YMD;
	private int ADDRESS;
	private int GENDER;
	private String DEATH_YMD;
	private int DEATH_TIME;
	private int DEATH_PLACE;
	private String DEATH_JOB;
	private int MARRY;
	private int EDU;
	private String DEATH_CAU1;
	private String DEATH_CAU1_Parent;
	private int DEATH_AGE;
	
	private int STND_Y;
	private int YKIHO_ID;
	private int YKIHO_GUBUN_CD;
	private int ORG_TYPE;
	private int YKIHO_SIDO;
	private int SICKBED_CNT;
	private int DR_CNT;
	private int CT_YN;
	private int MRI_YN;
	private int PET_YN;
	
	
	
	public LinkedDataListDTO(int No, String REPORT_YMD, int ADDRESS, int GENDER, String DEATH_YMD, int DEATH_TIME, int DEATH_PLACE, String DEATH_JOB,
			int MARRY, int EDU, String DEATH_CAU1, String DEATH_CAU1_Parent, int DEATH_AGE, int STND_Y, int YKIHO_ID, 
			int YKIHO_GUBUN_CD, int ORG_TYPE, int YKIHO_SIDO, int SICKBED_CNT, int DR_CNT, int CT_YN, int MRI_YN, int PET_YN){
		
		this.No = No;
		this.REPORT_YMD = REPORT_YMD;
		this.ADDRESS = ADDRESS;
		this.GENDER = GENDER;
		this.DEATH_YMD = DEATH_YMD;
		this.DEATH_TIME = DEATH_TIME;
		this.DEATH_PLACE = DEATH_PLACE;
		this.DEATH_JOB = DEATH_JOB;
		this.MARRY = MARRY;
		this.EDU = EDU;
		this.DEATH_CAU1 = DEATH_CAU1;
		this.DEATH_CAU1_Parent = DEATH_CAU1_Parent;
		this.DEATH_AGE = DEATH_AGE;
		this.STND_Y = STND_Y;
		this.YKIHO_ID = YKIHO_ID;
		this.YKIHO_GUBUN_CD = YKIHO_GUBUN_CD;
		this.ORG_TYPE = ORG_TYPE;
		this.YKIHO_SIDO = YKIHO_SIDO;
		this.SICKBED_CNT = SICKBED_CNT;
		this.DR_CNT = DR_CNT;
		this.CT_YN = CT_YN;
		this.MRI_YN = MRI_YN;
		this.PET_YN = PET_YN;
	}
	
	
	
	
	public int getNo() {
		return No;
	}


	public void setNo(int no) {
		No = no;
	}


	public String getREPORT_YMD() {
		return REPORT_YMD;
	}
	public void setREPORT_YMD(String rEPORT_YMD) {
		REPORT_YMD = rEPORT_YMD;
	}
	public int getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(int aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public int getGENDER() {
		return GENDER;
	}
	public void setGENDER(int gENDER) {
		GENDER = gENDER;
	}
	public String getDEATH_YMD() {
		return DEATH_YMD;
	}
	public void setDEATH_YMD(String dEATH_YMD) {
		DEATH_YMD = dEATH_YMD;
	}
	public int getDEATH_TIME() {
		return DEATH_TIME;
	}
	public void setDEATH_TIME(int dEATH_TIME) {
		DEATH_TIME = dEATH_TIME;
	}
	public int getDEATH_PLACE() {
		return DEATH_PLACE;
	}
	public void setDEATH_PLACE(int dEATH_PLACE) {
		DEATH_PLACE = dEATH_PLACE;
	}
	public String getDEATH_JOB() {
		return DEATH_JOB;
	}
	public void setDEATH_JOB(String dEATH_JOB) {
		DEATH_JOB = dEATH_JOB;
	}
	public int getMARRY() {
		return MARRY;
	}
	public void setMARRY(int mARRY) {
		MARRY = mARRY;
	}
	public int getEDU() {
		return EDU;
	}
	public void setEDU(int eDU) {
		EDU = eDU;
	}
	public String getDEATH_CAU1() {
		return DEATH_CAU1;
	}
	public void setDEATH_CAU1(String dEATH_CAU1) {
		DEATH_CAU1 = dEATH_CAU1;
	}
	public String getDEATH_CAU1_Parent() {
		return DEATH_CAU1_Parent;
	}
	public void setDEATH_CAU1_Parent(String dEATH_CAU1_Parent) {
		DEATH_CAU1_Parent = dEATH_CAU1_Parent;
	}
	public int getDEATH_AGE() {
		return DEATH_AGE;
	}
	public void setDEATH_AGE(int dEATH_AGE) {
		DEATH_AGE = dEATH_AGE;
	}
	public int getSTND_Y() {
		return STND_Y;
	}
	public void setSTND_Y(int sTND_Y) {
		STND_Y = sTND_Y;
	}
	public int getYKIHO_ID() {
		return YKIHO_ID;
	}
	public void setYKIHO_ID(int yKIHO_ID) {
		YKIHO_ID = yKIHO_ID;
	}
	public int getYKIHO_GUBUN_CD() {
		return YKIHO_GUBUN_CD;
	}
	public void setYKIHO_GUBUN_CD(int yKIHO_GUBUN_CD) {
		YKIHO_GUBUN_CD = yKIHO_GUBUN_CD;
	}
	public int getORG_TYPE() {
		return ORG_TYPE;
	}
	public void setORG_TYPE(int oRG_TYPE) {
		ORG_TYPE = oRG_TYPE;
	}
	public int getYKIHO_SIDO() {
		return YKIHO_SIDO;
	}
	public void setYKIHO_SIDO(int yKIHO_SIDO) {
		YKIHO_SIDO = yKIHO_SIDO;
	}
	public int getSICKBED_CNT() {
		return SICKBED_CNT;
	}
	public void setSICKBED_CNT(int sICKBED_CNT) {
		SICKBED_CNT = sICKBED_CNT;
	}
	public int getDR_CNT() {
		return DR_CNT;
	}
	public void setDR_CNT(int dR_CNT) {
		DR_CNT = dR_CNT;
	}
	public int getCT_YN() {
		return CT_YN;
	}
	public void setCT_YN(int cT_YN) {
		CT_YN = cT_YN;
	}
	public int getMRI_YN() {
		return MRI_YN;
	}
	public void setMRI_YN(int mRI_YN) {
		MRI_YN = mRI_YN;
	}
	public int getPET_YN() {
		return PET_YN;
	}
	public void setPET_YN(int pET_YN) {
		PET_YN = pET_YN;
	}
	
	
}
