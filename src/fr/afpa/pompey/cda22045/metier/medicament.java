package fr.afpa.pompey.cda22045.metier;

import java.time.LocalDate;

public class medicament {

	private int medId;
	private int catId;
	private String medNom;
	private float medPrix;
	private LocalDate medMiseEnService;
	
		
	/**
	 * Constructeur d'un nouveau médicament
	 * @param catId l'identifiant de sa catégorie
	 * @param medNom son nom
	 * @param medPrix son prix
	 * @param medMiseEnService sa date de mise en service
	 */
	public medicament(int catId, String medNom, float medPrix, LocalDate medMiseEnService) {
		this.catId = catId;
		this.medNom = medNom;
		this.medPrix = medPrix;
		this.medMiseEnService = medMiseEnService;
	}

	/**
	 * Getter de l'identifiant du médicament
	 * @return son identifiant
	 */
	public int getMedId() {
		return medId;
	}

	/**
	 * Getter de l'identifiant de la catégorie du médicament
	 * @return l'identifiant de la catégorie du médicament
	 */
	public int getCatId() {
		return catId;
	}
	
	
	/**
	 * Getter du nom du médicament
	 * @return son nom
	 */
	public String getMedNom() {
		return medNom;
	}

	/**
	 * Setter du nom du médicament
	 * @param medNom le nouveau nom
	 */
	public void setMedNom(String medNom) {
		this.medNom = medNom;
	}

	/**
	 * Getter du prix du médicament
	 * @return le prix du médicament
	 */
	public float getMedPrix() {
		return medPrix;
	}

	/**
	 * Setter du prix du médicament
	 * @param medPrix le nouveau prix
	 */
	public void setMedPrix(float medPrix) {
		this.medPrix = medPrix;
	}

	/**
	 * Getter de la date de mise en service du médicament
	 * @return la date de mise en service
	 */
	public LocalDate getMedMiseEnService() {
		return medMiseEnService;
	}

	/**
	 * Setter de la nouvelle date de mise en service du mdicament 
	 * @param medMiseEnService
	 */
	public void setMedMiseEnService(LocalDate medMiseEnService) {
		this.medMiseEnService = medMiseEnService;
	}

}
