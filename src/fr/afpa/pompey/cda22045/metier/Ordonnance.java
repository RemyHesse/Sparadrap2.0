/**
 * 
 */
package fr.afpa.pompey.cda22045.metier;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 */
public class Ordonnance {

	/**
	 * 
	 */
	private int ordId;
	private LocalDate ordDate;
	public static ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
	
	public Ordonnance() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ordId
	 * @param pOrdDate
	 */
	public Ordonnance(int pOrdId, LocalDate pOrdDate, ArrayList<Prescription> pPrescriptions) {
		this.setOrdId(pOrdId);
		this.setOrdDate(pOrdDate);
		this.setPrescriptions(pPrescriptions);
	}
	
	/**
	 * @param pOrdDate
	 */
	public Ordonnance(LocalDate pOrdDate, ArrayList<Prescription> pPrescriptions) {
		this.setOrdDate(pOrdDate);
		this.setPrescriptions(pPrescriptions);
	}
	
	public Ordonnance(int pOrdId, LocalDate pOrdDate) {
		this.setOrdId(pOrdId);
		this.setOrdDate(pOrdDate);
	}
	

	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int pOrdId) {
		this.ordId = pOrdId;
	}

	public LocalDate getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(LocalDate pOrdDate) {
		this.ordDate = pOrdDate;
	}

	public static ArrayList<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(ArrayList<Prescription> pPrescriptions) {
		Ordonnance.prescriptions = pPrescriptions;
	}
	
	

	/**
	 * @param ordDate
	 */

	
	
	
	
	
	
	
	
	
	
	
	
	
}
