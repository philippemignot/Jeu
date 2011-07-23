package com.jeu.affichage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Cette classe représente un sprite. Elle contient une image et gère les
 * actions qui peuvent être effectuées sur ce que l'image représente.
 * 
 * @author Philippe Mignot
 */
public class Sprite
{
	// Nom du dossier contenant les images
	protected static final String nomDossierImages = "images";

	// Largeur et hauteur du sprite en pixels
	public static int largeurSprite = 32;
	public static int hauteurSprite = 32;

	// Abscisse et ordonnée du sprite en pixels
	protected int abscisse;
	protected int ordonnee;

	// Niveau du sprite
	protected int niveau;

	// Identifiant de l'image, et délimiteur catégorie / image
	protected String identifiant;
	protected static final int delimiteurIdentifiant = 2;

	// Image contenue dans le sprite
	protected Image image;

	/**
	 * Ce constructeur calcule l'abscisse et l'ordonnée (en pixels) qu'aura le
	 * sprite dans la carte.
	 * 
	 * @param abscisse
	 *        L'abscisse (en nombre de sprites) du sprite dans la carte.
	 * @param ordonnee
	 *        L'ordonnée (en nombre de sprites) du sprite dans la carte.
	 * @param niveau
	 *        Le niveau du sprite dans la carte.
	 * @param identifiant
	 *        L'identifiant correspondant à l'image à charger, null si le sprite
	 *        est vide.
	 */
	public Sprite(int abscisse, int ordonnee, int niveau, String identifiant)
	{
		// Calcul de l'abscisse et de l'ordonnée en pixels
		this.abscisse = abscisse * largeurSprite;
		this.ordonnee = ordonnee * hauteurSprite;

		// Le niveau
		this.niveau = niveau;

		// L'image, si son identifiant est fourni
		this.identifiant = identifiant;
		if (identifiant != null)
		{
			// Récupérer le dossier "images"
			File dossierImages = new File(nomDossierImages);
			String[] dossiers = dossierImages.list();
			for (int i = 0; i < dossiers.length; i++)
			{
				if (dossiers[i].startsWith(identifiant.substring(0,
				        delimiteurIdentifiant)))
				{
					// Récupérer le dossier correspondant à la catégorie de
					// l'image
					File dossier =
					        new File(nomDossierImages + "/" + dossiers[i]);
					String[] fichiers = dossier.list();
					for (int j = 0; j < fichiers.length; j++)
					{
						if (fichiers[j].startsWith(identifiant))
						{
							// Lire l'image correspondant à l'identifiant
							try
							{
								image =
								        ImageIO.read(new File(nomDossierImages
								                + "/" + dossiers[i] + "/"
								                + fichiers[j]));
							}
							catch (IOException e)
							{
								System.err
								        .println("L'image possédant l'identifiant "
								                + identifiant
								                + " n'a pas pu être lue.");
								e.printStackTrace();
							}
						}
					}
				}
			}
			if (image == null)
			{
				System.err.println("L'image possédant l'identifiant "
				        + identifiant + " n'existe pas.");
			}
		}
	}

	/**
	 * Dessine le sprite dans la carte.
	 * 
	 * @param g
	 *        L'environnement grapĥique.
	 */
	public void dessiner(Graphics g)
	{
		// S'il n'y a pas d'image de définie, dessiner un rectangle noir.
		if (image == null)
		{
			g.setColor(Color.BLACK);
			g.fillRect(abscisse, ordonnee, largeurSprite, hauteurSprite);
		}
		else
		{
			g.drawImage(image, abscisse, ordonnee, largeurSprite,
			        hauteurSprite, null);
		}
	}

	/**
	 * @param abscisse
	 *        La nouvelle abscisse en pixels.
	 */
	public void setAbscisse(int abscisse)
	{
		this.abscisse = abscisse;
	}

	/**
	 * @return L'abscisse en pixels.
	 */
	public int getAbscisse()
	{
		return abscisse;
	}

	/**
	 * @param ordonnee
	 *        La nouvelle ordonnée en pixels.
	 */
	public void setOrdonnee(int ordonnee)
	{
		this.ordonnee = ordonnee;
	}

	/**
	 * @return L'ordonnée en pixels.
	 */
	public int getOrdonnee()
	{
		return ordonnee;
	}

	/**
	 * @param niveau
	 *        Le nouveau niveau.
	 */
	public void setNiveau(int niveau)
	{
		this.niveau = niveau;
	}

	/**
	 * @return Le niveau du sprite. Il détermine sa position sur l'axe
	 *         perpendiculaire à l'écran par rapport aux sprites qui se trouvent
	 *         aux mêmes abscisse et ordonnée. Un niveau plus élevé signifie que
	 *         le sprite se situe au-dessus.
	 */
	public int getNiveau()
	{
		return niveau;
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
	 * @return L'identifiant de l'image, null s'il n'y a pas d'image de définie.
	 *         Cet identifiant est composé de deux parties. La première
	 *         représente la catégorie de l'image (personnage, sol, mur, etc.).
	 *         Le seconde représente l'image en elle-même. La limite entre ces
	 *         deux parties est positionnée au second caractère.
	 */
	public String getIdentifiant()
	{
		return identifiant;
	}

	/**
	 * @param image
	 *        La nouvelle image.
	 */
	public void setImage(Image image)
	{
		this.image = image;
	}

	/**
	 * @return L'image contenue dans le sprite, null s'il n'y a pas d'image.
	 */
	public Image getImage()
	{
		return image;
	}

}
