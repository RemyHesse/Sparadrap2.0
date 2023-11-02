package fr.afpa.pompey.cda22045.metier;

import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.utilitaires.Saisie;

public class Categorie {

	private int catId;
	private String catLabel;
	
	/**
	 * Constructeur d'une catégorie de médicament
	 * @param catLabel son libellé
	 * @throws MonException 
	 */
	public Categorie(String pCatLabel) throws MonException {
		this.setCatLabel(pCatLabel);
	}
	
	/**
	 * Getter de l'identifiant d'une catégorie
	 * @return son id
	 */
	public int getCatId() {
		return catId;
	}

	/**
	 * Getter du libellé d'une catégorie
	 * @return son libellé
	 */
	public String getCatLabel() {
		return catLabel;
	}
	
	/**
	 * Setter d'un libellé d'une catégorie
	 * @param catLabel le nouveau libellé 
	 * @throws MonException 
	 */
	public void setCatLabel(String pCatLabel) throws MonException {
		if(Saisie.lireNomPrenom(pCatLabel) && pCatLabel!= null && pCatLabel !="") {
			this.catLabel = pCatLabel;
			} else {
				throw new MonException("Nom de médicament invalide");
			}	
	}

	@Override
	public String toString() {
		return "identifiant : " + catId + ", libellé = " + catLabel + ".";
	}
	
	
}
