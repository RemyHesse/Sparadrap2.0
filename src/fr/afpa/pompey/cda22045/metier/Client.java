package fr.afpa.pompey.cda22045.metier;

import java.time.LocalDate;

import fr.afpa.pompey.cda22045.dao.ClientDAO;
import fr.afpa.pompey.cda22045.exception.MonException;

public class Client extends Personne {

	/**
	 * 
	 */
	private int mutId;
	private LocalDate cliDateNaiss;
	private long cliNumSecu;
	private int medId;
	
		
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur d'un nouveau client
	 * @param pMutId l'identifiant de la mutuelle
	 * @param pCliDateNaiss sa date de naissance
	 * @param pCliNumSecu son numéro de sécurité sociale
	 */
	public Client(int perId, String pPrenom, String pNom, int pAdresse, String pTelephone, String pEmail, int pMutId, LocalDate pCliDateNaiss, int pMedId) throws MonException {
		super(perId,pPrenom,pNom,pAdresse,pTelephone,pEmail);
		this.setMutId(pMutId);
		this.setCliDateNaiss(pCliDateNaiss);
		this.setMedId(pMedId);
	}


	public Client(String pPrenom, String pNom, int pAdresse, String pTelephone, String pEmail, int pMutId, LocalDate pCliDateNaiss,  int pMedId) throws MonException {
		super(pPrenom,pNom,pAdresse,pTelephone,pEmail);
		this.setMutId(pMutId);
		this.setCliDateNaiss(pCliDateNaiss);
		this.setMedId(pMedId);
	}
	


	public int getMutId() {
		return mutId;
	}

	public void setMutId(int pMutId) {
		try {
			this.mutId = pMutId;
		} catch (NullPointerException e) {
			System.err.println("Erreur : " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("Erreur : " + e.getMessage());
		}
	}

	public LocalDate getCliDateNaiss() {
		return cliDateNaiss;
	}

	public void setCliDateNaiss(LocalDate cliDateNaiss) {
		this.cliDateNaiss = cliDateNaiss;
	}

	public long getCliNumSecu() {
		return cliNumSecu;
	}

	public void setCliNumSecu(long cliNumSecu) {
		this.cliNumSecu = cliNumSecu;
	}

	

	public int getMedId() {
		return medId;
	}

	public void setMedId(int pMedId) {
		this.medId = pMedId;
	}

	@Override
	public String toString() {
		String mutNom = ClientDAO.getMutNom(mutId);
		return "Client [cliId= " + getPerId() + " : " + super.toStringCli() + ", Mutuelle = " + mutNom + ", Date de Naissance " + cliDateNaiss + "]";
	}
	
	

}
