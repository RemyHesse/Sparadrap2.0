/**
 * 
 */
package fr.afpa.pompey.cda22045.utilitaires;

import java.util.Scanner;
import java.util.regex.*;

/**
 * 
 */
public class Saisie {

	/**
	 * @param args
	 */
	final static String REGEXOUINON = "^(oui|non)$";
	final static String REGEXENTIER = "^-?\\d+$";
	final static String REGEXEMAIL = "^[\\w._-]+@[\\w._-]+\\.[a-z]{2,}$";
	final static String REGEXCODEPOSTAL = "^(\\d{5}|\\d{5}-[a-zA-Z]{2})$";
	final static String REGEXTELFR = "^(0|\\+33|0033)[1-9][0-9]{8}$";
	final static String REGEXNOMPRENOM = "^[a-zA-ZéèêëàâçîôûÉÈÊËÀÂÇÎÔÛ]+$";
	static boolean matchTrouve;
	public static int entierSaisi; // Variable contenant l'entier saisi par l'utilisateur dans les différents exercices
	public static String saisie;
	public static Scanner scanner = new Scanner(System.in); // Instance de Classe de saisie utilisateur.
	// -
	//

	public static void start(String[] args) {
		// TODO Auto-generated method stub

	}

	// -
	// Fonction retournant la chaine de caractères saisie par l'utilisateur.
	public static String lireSaisie() {
		saisie = scanner.nextLine();
		// purge du tampon
		// scanner.nextLine();
		return saisie;
	} // Fin lireSaisie
		// -
		// Fonction retournant l'entier saisi par l'utilisateur.

	public static int lireEntier() {
		saisie = scanner.next();
		// purge du tampon
		scanner.nextLine();
		// Si la saisie ne correspond pas à un entier, on recommence.
		try {
			entierSaisi = Integer.parseInt(saisie);
		} catch (NumberFormatException e) {
			System.out.println("Ce n'était pas un nombre entier, veuillez recommencer.");
			lireEntier();
		}
		return entierSaisi;
	} // Fin lireEntier
		// -
		// Fonction retournant l'entier saisi par l'utilisateur.

	public static int lireEntier2() {
		saisie = scanner.next();
		// purge du tampon
		scanner.nextLine();
		// Si la saisie ne correspond pas à un entier, on recommence.
		Pattern entier = Pattern.compile(REGEXENTIER);
		Matcher matcher = entier.matcher(saisie);
		matchTrouve = matcher.find();
		if (matchTrouve == true) {
			entierSaisi = Integer.parseInt(saisie);
		} else {
			System.out.println("Ce n'était pas un nombre entier, veuillez recommencer.");
			lireEntier2();
		}
		return entierSaisi;
	} // Fin lireEntier
	/**
	* Fonction pour vérifier la validité d'une adresse mail
	*/
    public static String lireEmail() {
        saisie = scanner.next();
        scanner.nextLine();
        
        Pattern emailPattern = Pattern.compile(REGEXEMAIL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(saisie);
        matchTrouve = matcher.find();
        
        if (matchTrouve) {
            return saisie;
        } else {
            System.out.println("Veuillez saisir une adresse e-mail valide.");
            return lireEmail();
        }
    }
    /**
     * Fonction pour vérifier la validité d'un email saisi dans un TextField Swing
     * @return vrai si valide
     */
	public static boolean lireEmailSwing(String pSaisie) {
		
		Pattern emailPattern = Pattern.compile(REGEXEMAIL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(pSaisie);
        matchTrouve = matcher.find();
        
        if (matchTrouve) {
            return true;
        } else {
            return false;
        }
	}
	
	/**
	 * Fonction pour vérifier la validité d'une saisie de code postal
	 */
	public static boolean lireCodePostal(String pSaisie) {
	   
	    Pattern codePostalPattern = Pattern.compile(REGEXCODEPOSTAL);

	    Matcher matcher = codePostalPattern.matcher(pSaisie);
	    // Renvoie True si ça matche
	    return matcher.matches();
	}
	
	/**
	 * Fonction pour vérifier la validité d'une saisie d'un numéro de téléphone français
	 * @param pSaisie la saisie utilisateur
	 * @return true si le format est valide
	 */
	public static boolean lireTelephone(String pSaisie) {

	    Pattern telephonePattern = Pattern.compile(REGEXTELFR);

	    Matcher matcher = telephonePattern.matcher(pSaisie);
	    // Renvoie True si ça matche
	    return matcher.matches();
	}
	
	/**
	 * Méthode pour vérifier la validité d'un nom ou d'un prénom
	 * @param pSaisie la saisi ne doit contenir que des lettres de l'alphabet français
	 * @return true si c'est le cas
	 */
	public static boolean lireNomPrenom(String pSaisie) {

	    Pattern nomPrenomPattern = Pattern.compile(REGEXNOMPRENOM);

	    Matcher matcher = nomPrenomPattern.matcher(pSaisie);
	    // Renvoie true si la saisie ne contient que des lettres de l'alphabet français
	    return matcher.matches();
	}
	
	// Fonction pour forçer à ne répondre que par oui ou par non.
	public static String lireOuiNon() {
		saisie = scanner.next();
		Pattern ouiNon = Pattern.compile(REGEXOUINON, Pattern.CASE_INSENSITIVE);
		Matcher matcher = ouiNon.matcher(saisie);
		matchTrouve = matcher.find();
		if (matchTrouve != true) {
			System.out.println("Veuillez répondre par oui ou par non");
			lireOuiNon();
		}
		return saisie;
	}

	// -
	// Fonction pour renvoyer True si la réponse est oui
	public static boolean oui(String ouiOuNon) {
		Pattern oui = Pattern.compile("oui", Pattern.CASE_INSENSITIVE);
		Matcher matcher = oui.matcher(ouiOuNon);
		matchTrouve = matcher.find();
		if (matchTrouve == true) {
			return true;
		} else {
			return false;
		}
	}

	// Fermeture du scan
	public static void closeScan() {
		scanner.close();
	} // Fin closeScan
}

