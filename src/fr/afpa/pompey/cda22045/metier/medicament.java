package fr.afpa.pompey.cda22045.metier;

import java.time.LocalDate;

import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.utilitaires.Saisie;

public class Medicament {

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
	 * @throws MonException 
	 */
	public Medicament(int pCatId, String pMedNom, float pMedPrix, LocalDate pMedMiseEnService) throws MonException {
		this.setCatId(pCatId);
		this.setMedNom(pMedNom);
		this.setMedPrix(pMedPrix);
		this.setMedMiseEnService(pMedMiseEnService);
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
	
	
	
	public void setCatId(int pCatId) {
		if ( pCatId > 0) {
			this.catId = pCatId;
		} else {
			throw new IllegalArgumentException("Le prix doit être non nul et supérieur à zéro.");
		}
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
	 * @throws MonException 
	 */
	public void setMedNom(String pMedNom) throws MonException {
		if(Saisie.lireNomPrenom(pMedNom) && pMedNom!= null && pMedNom !="") {
			this.medNom = pMedNom;
			} else {
				throw new MonException("Nom de médicament invalide");
			}		
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
	public void setMedPrix(float pMedPrix) {
		if ( pMedPrix > 0) {
			this.medPrix = pMedPrix;
		} else {
			throw new IllegalArgumentException("Le prix doit être non nul et supérieur à zéro.");
		}
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
	public void setMedMiseEnService(LocalDate pMedMiseEnService) {
		try {
			if (pMedMiseEnService == null || pMedMiseEnService.isAfter(LocalDate.now())) {
				throw new IllegalArgumentException("La date de mise en service n'est pas valide.");
			}
			this.medMiseEnService = pMedMiseEnService;
		} catch (IllegalArgumentException e) {
			System.err.println("Erreur : " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "medicament [Id =" + medId + ", catégorie =" + catId + ", Nom =" + medNom + ", Prix=" + medPrix
				+ ", Mise En Service =" + medMiseEnService + "]";
	}
	
	
	

}
