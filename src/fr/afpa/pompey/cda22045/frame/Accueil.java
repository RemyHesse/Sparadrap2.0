package fr.afpa.pompey.cda22045.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import fr.afpa.pompey.cda22045.dao.MedecinDAO;
import fr.afpa.pompey.cda22045.dao.OrdonnanceDAO;
import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Medecin;
import fr.afpa.pompey.cda22045.metier.Medicament;
import fr.afpa.pompey.cda22045.metier.Ordonnance;

public class Accueil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateTimeFormatter dateFormatFR = DateTimeFormatter.ofPattern("dd MMM yyyy");
	private static JFrame accueil;
	private JComboBox<String> cbxMedecin;
	private DefaultTableModel mdlTableOrdo ;
	/**
	 * Lancement de l'application
	 */


	/**
	 * Création de la fenêtre d'accueil
	 * @throws MonException 
	 */
	public Accueil() throws MonException {

		
		accueil = new JFrame();
		accueil.setBounds(100, 100, 1200, 600);
		accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accueil.setTitle("Sparadrap");
		accueil.setLayout(new BorderLayout());
		


		//---------------------------------------------------------------------------
		// Création des composants pour la zone Nord
		JLabel lblBienvenue = new JLabel("Bienvenue à la pharmacie Sparadrap");
		lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnAchat = new JButton("Achat");
		JButton btnHistoriqueAchat = new JButton("Historique Achat");
		JButton btnHistoriqueOrdonnance = new JButton("Historique Ordonnance");
		JButton btnDetailClient = new JButton("Détail d'un Client");

		JPanel pnlBoutonAccueil = new JPanel(new GridLayout(1, 4));
		pnlBoutonAccueil.add(btnAchat);
		pnlBoutonAccueil.add(btnHistoriqueAchat);
		pnlBoutonAccueil.add(btnHistoriqueOrdonnance);
		pnlBoutonAccueil.add(btnDetailClient);
		
		JButton btnStock = new JButton("Gestion des Stocks");
		JButton btnUtilisateur = new JButton("Gestion des Utilisateurs");
		JButton btnAjoutMedecin = new JButton("Ajouter un Médecin");
		JButton btnAjoutClient = new JButton("Ajouter un Client");

		JPanel pnlBoutonAccueil2 = new JPanel(new GridLayout(1, 4));
		pnlBoutonAccueil2.add(btnStock);
		pnlBoutonAccueil2.add(btnUtilisateur);
		pnlBoutonAccueil2.add(btnAjoutMedecin);
		pnlBoutonAccueil2.add(btnAjoutClient);

		// Création du BorderLayout Nord et ajout des composants au nord
		JPanel pnlAccueilNord = new JPanel(new BorderLayout());
		pnlAccueilNord.add(lblBienvenue, BorderLayout.NORTH);
		pnlAccueilNord.add(pnlBoutonAccueil, BorderLayout.CENTER);
		pnlAccueilNord.add(pnlBoutonAccueil2, BorderLayout.SOUTH);
		// 
		accueil.add(pnlAccueilNord, BorderLayout.NORTH);
		accueil.setVisible(true);
		
		LoginDialog dialog = new LoginDialog(this);
		dialog.setVisible(true);
		
		cbxMedecin = new JComboBox<>();
		MedecinDAO medecinDAO = new MedecinDAO(); 
		ArrayList<Medecin> medecins = medecinDAO.findAll();

		for (Medecin medecin : medecins) {
		    cbxMedecin.addItem(medecin.toStringNom());
		}

		// Table pour afficher les détails des ordonnances
		mdlTableOrdo = new DefaultTableModel(new Object[] { "Date", "Client", "Médicaments" }, 0);
		JTable tblOrdonnances = new JTable(mdlTableOrdo);
		tblOrdonnances.setDefaultEditor(Object.class, null);
		JScrollPane sclpTable = new JScrollPane(tblOrdonnances);

		majListeOrdo();
		// -
		// Ajout d'un ActionListener à la JComboBox
		cbxMedecin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					majListeOrdo();
				} catch (MonException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Panel pour la zone centrale d'historique des ordonnances avec un BorderLayout
		JPanel pnlAccueilOrdo = new JPanel(new BorderLayout());
		pnlAccueilOrdo.add(cbxMedecin, BorderLayout.NORTH);
		pnlAccueilOrdo.add(sclpTable, BorderLayout.CENTER);

		// Ajout à la page d'accueill
		pnlAccueilOrdo.setVisible(false);
		pnlAccueilOrdo.setEnabled(false);
		
		//---------------------------------------------------------------------
		// Ajout des listener aux boutons pour gérer les actions
		btnHistoriqueOrdonnance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accueil.add(pnlAccueilOrdo, BorderLayout.CENTER);
//				pnlAccueilClient.setVisible(false);
				pnlAccueilOrdo.setEnabled(true);
				pnlAccueilOrdo.setVisible(true);
				try {
					majListeOrdo();
				} catch (MonException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});		
		
		
	}
	
	
	
	
	
	public void majListeOrdo() throws MonException {


		String nomMedecin = (String) cbxMedecin.getSelectedItem();
		
		// Reset du tableau
		mdlTableOrdo.setRowCount(0);
		
		OrdonnanceDAO ordonnanceDAO = new OrdonnanceDAO();
		ArrayList<Ordonnance> ordonnances = ordonnanceDAO.findAll();
		

		// Pour chaque ordonnance de mon jeu d'essai
		for (Ordonnance ordonnance : ordonnances) {
			// si le nom du médecin est le même que celui de la comboBox
			if (ordonnanceDAO.getNomMedecin(ordonnance).equals(nomMedecin)) {
				// on concatène les noms des médicaments..
				StringBuilder nomMedicament = new StringBuilder();
				for (Medicament medicament : ordonnanceDAO.getListeMedoc(ordonnance)) {
					if (nomMedicament.length() > 0) {
						nomMedicament.append(", ");
					}
					nomMedicament.append(medicament.getMedNom());
				}

				String formatFR = ordonnance.getOrdDate().format(dateFormatFR);
				
				mdlTableOrdo.addRow(new Object[] { formatFR, ordonnanceDAO.getNomClient(ordonnance),
						// .. pour pouvoir les ajouter dans la même colonne du tableau
						nomMedicament.toString() });
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
