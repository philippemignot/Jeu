package com.jeu.affichage;

import javax.swing.JFrame;

/**
 * Cette classe représente la fenêtre principale.
 * 
 * @author Philippe Mignot
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame
{
	// Titre de la fenêtre
	private static final String TITRE = "Jeu";

	/**
	 * Constructeur par défaut.
	 */
	public Fenetre()
	{
		super();

		// La fermeture de la fenêtre entraîne la fermeture de l'application.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Titre de la fenêtre
		setTitle(TITRE);

		// Panneau principal contenant l'affichage du jeu
		Panneau panneau = new Panneau();
		setContentPane(panneau);

		// Mise à jour de la taille de la fenêtre par rapport au panneau
		// principal
		pack();

		// La fenêtre ne peut pas être redimensionnée
		setResizable(false);

		// Affichage de la fenêtre
		setVisible(true);
	}
}
