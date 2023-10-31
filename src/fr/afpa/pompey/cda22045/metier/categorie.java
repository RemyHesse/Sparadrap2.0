package fr.afpa.pompey.cda22045.metier;

public class categorie {

	private int catId;
	private String catLabel;
	
	/**
	 * Constructeur d'une catégorie de médicament
	 * @param catLabel son libellé
	 */
	public categorie(int cat_id, String cat_label) {
		this.catLabel = cat_label;
	}
	
	/**
	 * Getter de l'identifiant d'une catégorie
	 * @return son id
	 */
	public int getCat_id() {
		return catId;
	}

	/**
	 * Getter du libellé d'une catégorie
	 * @return son libellé
	 */
	public String getCat_label() {
		return catLabel;
	}
	
	/**
	 * Setter d'un libellé d'une catégorie
	 * @param catLabel le nouveau libellé 
	 */
	public void setCat_label(String cat_label) {
		this.catLabel = cat_label;
	}

	@Override
	public String toString() {
		return "identifiant : " + catId + ", libellé = " + catLabel + ".";
	}
	
	
}
