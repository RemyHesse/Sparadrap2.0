/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

/**
 * 
 */
public class Adresse {

	/**
	 * 
	 */
	
	private int adrId;
	private int vilId;
	private String adrNum;
	private String adrLigne1;
	private String adrComplement;
	
	
	
	
	/**
	 * Constructeur complet d'une adresse
	 * @param adrId son identifiant
	 * @param vilId l'identifiant de sa ville
	 * @param adrNum son numéro de type String pour les bis, ter, etc
	 * @param adrLigne1 la rue
	 * @param adrComplement le complément d'information ( bâtiment, étage..)
	 */
	public Adresse(int pAdrId, int pVilId, String pAdrNum, String pAdrLigne1, String pAdrComplement) {
		this.adrId = pAdrId;
		this.vilId = pVilId;
		this.adrNum = pAdrNum;
		this.adrLigne1 = pAdrLigne1;
		this.adrComplement = pAdrComplement;
	}
	
	public Adresse(int pVilId, String pAdrNum, String pAdrLigne1, String pAdrComplement) {
		this.vilId = pVilId;
		this.adrNum = pAdrNum;
		this.adrLigne1 = pAdrLigne1;
		this.adrComplement = pAdrComplement;
	}

	public Adresse() {
		// TODO Auto-generated constructor stub
	}

	public int getAdrId() {
		return adrId;
	}

	public void setAdrId(int pAdrId) {
		this.adrId = pAdrId;
	}

	public int getVilId() {
		return vilId;
	}

	public void setVilId(int pVilId) {
		this.vilId = pVilId;
	}

	public String getAdrNum() {
		return adrNum;
	}

	public void setAdrNum(String pAdrNum) {
		this.adrNum = pAdrNum;
	}

	public String getAdrLigne1() {
		return adrLigne1;
	}

	public void setAdrLigne1(String pAdrLigne1) {
		this.adrLigne1 = pAdrLigne1;
	}

	public String getAdrComplement() {
		return adrComplement;
	}

	public void setAdrComplement(String pAdrComplement) {
		this.adrComplement = pAdrComplement;
	}

	@Override
	public String toString() {
		
		return "Adresse [adrId=" + adrId + ", vilId=" + vilId + ", adrNum=" + adrNum + ", adrLigne1=" + adrLigne1
				+ ", adrComplement=" + adrComplement + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
