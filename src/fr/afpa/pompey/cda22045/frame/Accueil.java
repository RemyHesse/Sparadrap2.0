package fr.afpa.pompey.cda22045.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fr.afpa.pompey.cda22045.dao.MedecinDAO;
import fr.afpa.pompey.cda22045.exception.MonException;
import fr.afpa.pompey.cda22045.metier.Medecin;

public class Accueil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame accueil;

	/**
	 * Lancement de l'application
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Accueil accueil = new Accueil();
//					accueil.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
	}

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

		// Création du BorderLayout Nord et ajout des composants au nord
		JPanel pnlAccueilNord = new JPanel(new BorderLayout());
		pnlAccueilNord.add(lblBienvenue, BorderLayout.NORTH);
		pnlAccueilNord.add(pnlBoutonAccueil, BorderLayout.CENTER);
		// 
		accueil.add(pnlAccueilNord, BorderLayout.NORTH);
		accueil.setVisible(true);
		
		JComboBox<String> cbxMedecin = new JComboBox<>();
		MedecinDAO medecinDAO = new MedecinDAO(); 
		ArrayList<Medecin> medecins = (ArrayList<Medecin>) medecinDAO.findAll();

		for (Medecin medecin : medecins) {
		    cbxMedecin.addItem(medecin.toStringNom());
		}

		// Table pour afficher les détails des ordonnances
		DefaultTableModel mdlTableOrdo = new DefaultTableModel(new Object[] { "Date", "Client", "Médicaments" }, 0);
		JTable tblOrdonnances = new JTable(mdlTableOrdo);
		JScrollPane sclpTable = new JScrollPane(tblOrdonnances);
//		majListeOrdo();
		// -
		// Ajout d'un ActionListener à la JComboBox
		cbxMedecin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				majListeOrdo();
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
//				majListeOrdo();
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
