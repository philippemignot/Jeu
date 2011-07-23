package com.jeu.affichage;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Cette classe représente le panneau principal affiché dans la fenêtre
 * principale. Elle peut contenir une carte, un menu, un écran d'accueil, etc.
 * 
 * @author Philippe Mignot
 */
@SuppressWarnings("serial")
public class Panneau extends JPanel
{
	// La carte affichée dans le panneau principal, null s'il n'y a pas de carte
	// à afficher
	private Carte carte;

	/**
	 * Constructeur par défaut.
	 */
	public Panneau()
	{
		super();

		// Instanciation de la carte
		carte = new Carte(null);

		// Taille du panneau en pixels
		setPreferredSize(new Dimension(Carte.largeurCarte
		        * Sprite.largeurSprite, Carte.hauteurCarte
		        * Sprite.hauteurSprite));
	}

	@Override
	public void paintComponent(Graphics g)
	{
		carte.paintComponent(g);
	}

	/**
	 * @param carte
	 *        La nouvelle carte.
	 */
	public void setCarte(Carte carte)
	{
		this.carte = carte;
	}

	/**
	 * @return La carte actuellement en mémoire.
	 */
	public Carte getCarte()
	{
		return carte;
	}
}
