package com.jeu.affichage;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * Cette classe représente une carte. Elle contient un tableau de sprites et
 * gère les événements générés par ces sprites.
 * 
 * @author Philippe Mignot
 */
@SuppressWarnings("serial")
public class Carte extends JPanel
{
	// Nom du dossier contenant les cartes
	private static final String nomDossierCartes = "cartes";

	// Largeur et hauteur de la carte en nombre de sprites
	public static int largeurCarte = 10;
	public static int hauteurCarte = 10;

	// Nombre de niveaux de la carte
	public static int nombreNiveaux = 5;

	// Identifiant de la carte, null si la carte est vide
	private String identifiant;

	// Fichier associé à la carte
	private File fichier;

	// Tableau de sprites
	private Sprite[][][] sprites;

	/**
	 * Ce constructeur lit la carte correspondant à l'identifiant passé en
	 * paramètre et la charge.
	 * 
	 * @param identifiant
	 *        L'identifiant de la carte à charger, null si l'on veut une carte
	 *        vide.
	 */
	public Carte(String identifiant)
	{
		// Instanciation du tableau de sprites
		sprites = new Sprite[largeurCarte][hauteurCarte][nombreNiveaux];

		// Instanciation de chaque sprite et ajout à la grille. Au début, tous
		// les sprites sont des carrés noirs, mais ils seront modifiés par la
		// suite lors de la lecture du fichier.
		for (int i = 0; i < hauteurCarte; i++)
		{
			for (int j = 0; j < largeurCarte; j++)
			{
				for (int k = 0; k < nombreNiveaux; k++)
				{
					sprites[i][j][k] = new Sprite(i, j, k, null);
				}
			}
		}

		// Remplissage de la carte si un identifiant a été fourni
		if (identifiant != null)
		{
			// L'identifiant
			this.identifiant = identifiant;

			// Lecture du fichier contenant les informations concernant la carte
			lireCarte(identifiant);
		}
	}

	private void lireCarte(String identifiant)
	{
		// Récupérer le dossier "cartes"
		File dossierCartes = new File(nomDossierCartes);
		String[] cartes = dossierCartes.list();
		for (int i = 0; i < cartes.length; i++)
		{
			if (cartes[i].startsWith(identifiant))
			{
				// Récupérer le fichier contenant les informations sur la carte
				// à charger
				fichier = new File(nomDossierCartes + "/" + cartes[i]);
				try
				{
					// Créer le lecteur du fichier
					FileReader lecteur = new FileReader(fichier);

					// Créer le tampon
					BufferedReader lecteurTampon = new BufferedReader(lecteur);

					String ligneCourante;
					while ((ligneCourante = lecteurTampon.readLine()) != null)
					{
						
					}
				}
				catch (FileNotFoundException e)
				{
					System.err.println("Le fichier possédant l'identifiant "
					        + identifiant + " n'a pas été trouvé.");
					e.printStackTrace();
				}
				catch (IOException e)
				{
					System.err.println("Le fichier possédant l'identifiant "
					        + identifiant
					        + " a engendré une erreur de lecture.");
					e.printStackTrace();
				}
			}
		}
		if (fichier == null)
		{
			System.err.println("La carte possédant l'identifiant "
			        + identifiant + " n'existe pas.");
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		for (int i = 0; i < hauteurCarte; i++)
		{
			for (int j = 0; j < largeurCarte; j++)
			{
				for (int k = 0; k < nombreNiveaux; k++)
				{
					sprites[i][j][k].dessiner(g);
				}
			}
		}
	}

	/**
	 * @param identifiant
	 *        Le nouvel identifiant.
	 */
	public void setIdentifiant(String identifiant)
	{
		this.identifiant = identifiant;
	}

	/**
	 * @return L'identifiant de la carte, null si la carte est vide.
	 */
	public String getIdentifiant()
	{
		return identifiant;
	}

	/**
	 * @param fichier
	 *        Le nouveau fichier.
	 */
	public void setFichier(File fichier)
	{
		this.fichier = fichier;
	}

	/**
	 * @return Le fichier contenant les informations concernant la carte, null
	 *         si la carte est vide.
	 */
	public File getFichier()
	{
		return fichier;
	}

	/**
	 * @param sprites
	 *        Le nouveau tableau de sprites.
	 */
	public void setSprites(Sprite[][][] sprites)
	{
		this.sprites = sprites;
	}

	/**
	 * @return Le tableau de sprites.
	 */
	public Sprite[][][] getSprites()
	{
		return sprites;
	}
}
