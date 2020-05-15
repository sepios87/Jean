package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acm.util.SwingTimer;

public class Game extends GraphicsProgram implements MouseMotionListener{
	
	//declarations de variables
	public int tempsEcouleTimer, tempsEcouleReal, delayBombe, delayMiam, score;	
	public short move = 0, verif = 0, tps = 0;
	public byte act, pair, sec, compteur = 5, nbPieces = 0, secReal, n;
	public double vitesseX, vitesseY, vX, vY;
	public boolean actBouclier = false, actBombe = false, game = false, actMiam = false, controlMusique = false, actOpacite = false, achatR = false, achatV = false, achatB = false;
	public char skin = 'n', etat = 'm';
	
	//declaration des arrayList
	ArrayList <GObject> xEnnemis;
	ArrayList <GObject> imageXPieces;
	
	//declaration objet créé dans une autre classe pour récuper la position du personnage
	PosAlea alea;
	
	//déclaration objet pour les  bruitages
	Bruitages bruit = new Bruitages();
	
	//enregistrement fichiers
	Enregistrement rec;
	
	//declaration des timers de vitesse differentes
	public final int tpsChrono = 36, tpsReal = 1000;
	public SwingTimer chrono, timer, hAffiche;
	
	//declaration des objets visibles 
	public GOval perso, ennemis, bouclier, bomber, miam, btOpaque, btscore0, repere;
	
	//declarations pour le cacher le curseur de la souris 
	public Cursor invisibleCursor;
	Toolkit toolkit;
	Image image;
	Point loca;
	
	//déclaration de images
	GImage imagePerso, imageBonus, imagePiece, menu, contourMenu, fondJeu, skin1, skin2, skin3, skin4;
	
	//declaration des rectangles
	 public GRect btplay, btquit, btpara, btskin;
	 
	 //déclaration des textes
	 public GLabel retour, opaque, para, retour1, PasAssezdArgent, monaie, InfoPrixSkin, InfoGainPieces, temps, afficheScore, txtscore0;
	 
	//initialisation
	public void init() {
		//initialisation de l'enregistrement
				rec = new Enregistrement();
				//lire score sauvegardé
				score = rec.getRec();
		//init des objets du menu
		para = new GLabel ("Paramètres", 370, 50);
		menu = new GImage ("menu_1.jpg", 0, 0);
		btOpaque = new GOval (310, 250, 20, 20);
		retour = new GLabel ("Retour", 850, 600);
		btplay = new GRect (380, 250, 170, 50);
		btpara = new GRect (770, 11, 210, 45);
		btquit = new GRect (366, 325, 204, 50);
		btskin = new GRect (40, 19, 100, 40);
		contourMenu = new GImage ("contourMenu.jpg", 0, 0);
		opaque = new GLabel ("Rendre les ennemis opaques", 350, 268);
		btscore0 = new GOval (310, 280, 20, 20);
		btscore0.setFilled(false);
	    btscore0.setColor(Color.GREEN);
	    txtscore0 = new GLabel ("Mettre le score à 0", 350, 298);
	    txtscore0.setFont("Leelawadee-20");
	    txtscore0.setColor(Color.BLACK);
		para.setFont("Impact-40");
        para.setColor(Color.GREEN);
        btOpaque.setFilled(false);
        btOpaque.setColor(Color.RED);
        opaque.setFont("Leelawadee-20");
        opaque.setColor(Color.BLACK);
        retour.setFont("Leelawadee-35");
        retour.setColor(Color.ORANGE);
		
		//init des objets du jeu
		//creation perso
		perso = new GOval(10, 10);
		//creation bonus 
		imageBonus = new GImage("cubeBonus.png");
		//creation du bouclier grace au bonus
		bouclier = new GOval(20, 20);
		//creation de la bombe
		bomber = new GOval(50, 50);
		bomber.setColor(Color.RED);
		//creation du mange tout
		miam = new GOval(15, 15);
		miam.setFilled(true);
		miam.setColor(Color.RED);
		afficheMenu();
	}
	
	public void run() {
		//recupere les actions de la souris
		addMouseListeners();
		addMouseMotionListener(this);
	}
	
	//définit la fenêtre de menu
	 public void afficheMenu(){
		 //donne une taille à la fenêtre
		 	this.setSize(1016,710);
		 	//affiche le score
		 	afficheScore = new GLabel(Integer.toString(tps), 450, 480);
		    score = score + tps;
			tps = 0;
		    getContentPane().setCursor(null);
		    //donne une taille à l'image de fond
	        menu.setSize(1000, 650);
	        add(menu);
	        //affiche le score
	        afficheScore.setFont("Impact-50");
	        add(afficheScore);
	        //définit la couleur des boutons et les ajoutes
	        btplay.setColor(Color.BLACK);
	        btquit.setColor(Color.BLACK);
	        btpara.setColor(Color.BLACK);
	        btskin.setColor(Color.BLACK);
	        add(btplay);
	        add(btquit);
	        add(btpara);
	        add(btskin);
	        if (controlMusique == false)	bruit.menu();
	        controlMusique = true;
	       
	 }
	 
	    public void parametres(){
	    	//donne au fond une taille
            menu.setSize(1000, 650);
            //ajout des composants de la fenêtre paramètres
            add(contourMenu);
            add(btOpaque);
            add(opaque);
            add(para);
            add(btscore0);
            add(txtscore0);
            add(retour);
        }
	    
	    public void quitter() {
	    	try {
				rec.setRec(score);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public void lancementJeu() {
	    //fond
	    fondJeu = new GImage("fondJeu.jpg");
	    fondJeu.setSize(1000, 650);
	    add(fondJeu);
	    compteur = 5;
	    xEnnemis = new ArrayList<GObject>();
	    imageXPieces = new ArrayList<GObject>();
	    curseurInvisible();
		//taille de la fenetre
	    this.setSize(1016,710);
		
		bruit.lancement();
		bruit.musique();
		
		//creation du personnage
		perso.sendToFront();
		
		switch (skin) {
		//conditions pour choisir un skin pour le personnage
		case 'v':
			imagePerso = new GImage("bouleverte.png");
		break;
		case 'r':
			imagePerso = new GImage("boulerose.png");
		break;
		case 'b':
			imagePerso = new GImage("boulebleu.png");
		break;
		case 'n':
			imagePerso = new GImage("boulenoire.png");
		}
		
		//creation des deux timers
		chrono = new SwingTimer(tpsChrono, this);
		timer = new SwingTimer(tpsReal, this);
		hAffiche = new SwingTimer(tpsReal, this);
		//initialisation du timer a 0
		tempsEcouleTimer = 0;
		tempsEcouleReal = 0;
		//depart des timers
		chrono.start();
		timer.start();
		hAffiche.start();
		//creation label pour afficher un des deux timers
		temps = new GLabel(" - : - ");	
		temps.setColor(Color.BLACK);
		temps.setLocation(50, 40);
		temps.setFont("Arial-BOLD-40");
		add(temps);
	    }
	    
	    public void curseurInvisible() {
	    	//permet de rendre le curseur invisible, transparent
		    toolkit = Toolkit.getDefaultToolkit();
	        image = toolkit.getImage("transparent.gif");
	        loca = new Point(0, 0);
	        invisibleCursor = toolkit.createCustomCursor(image, loca, "invisible");
	        getContentPane().setCursor(invisibleCursor);
	    }
	
	//action quand la souris bouge
	public void mouseMoved(MouseEvent e) {
		
		if (game == true) {
			
		//le personnage suit le pointeur de la souris 
		perso.setLocation(e.getX()-5, e.getY()-5);
		imagePerso.setLocation(perso.getX()-3, perso.getY());
		imagePerso.sendToFront();
		add(imagePerso);
		bouclier.setLocation(e.getX()-10.25, e.getY()-10.25);
		}
		
	}
	
	public void skin(){
		//mettre une image de fond (contours)
	        contourMenu.setSize(1000, 650);
	        //definition de toutes les caractéristiques de la page skin
	        retour1 = new GLabel ("Retour", 850, 600);
	        retour1.setFont("Leelawadee-35");
	        retour1.setColor(Color.ORANGE);
	        GLabel skint = new GLabel ("Choix du skin", 370, 50);
	        skint.setFont("Impact-40");
	        skint.setColor(Color.GREEN);
	        skin1 = new GImage ("iboulerose.png", 200, 150);
	        skin1.setSize(155, 90);
	        skin2 = new GImage ("iboulebleu.png", 400, 150);
	        skin2.setSize(155, 90);
	        skin3 = new GImage ("ibouleverte.png", 600, 150);
	        skin3.setSize(155, 90);
	        skin4 = new GImage ("iboulenoire.png", 400, 320);
	        skin4.setSize(155, 90);
	        PasAssezdArgent = new GLabel ("Vous n'avez pas assez d'argent !", 350, 280);
	        PasAssezdArgent.setFont("Leelawadee-20");
	        PasAssezdArgent.setColor(Color.RED);
	        monaie = new GLabel ("--Pièces : " + score + "--", 50, 70);
	        monaie.setFont("Leelawadee-25");
	        monaie.setColor(Color.DARK_GRAY);
	        InfoPrixSkin = new GLabel ("*Skin coloré : 600 pièces*", 50, 85);
	        InfoPrixSkin.setFont("Leelawadee-15");
	        InfoPrixSkin.setColor(Color.BLACK);
	        InfoGainPieces = new GLabel ("Gagnez des pièces en jouant !", 50, 600);
	        InfoGainPieces.setFont("Leelawadee-15");
	        InfoGainPieces.setColor(Color.MAGENTA);
	        repere = new GOval(10, 10);
	        repere.setFilled(true);
	        repere.setFillColor(Color.GREEN);
	        //ajout des composants
	        add(contourMenu);
	        add(skint);
	        add(skin1);
	        add(skin2);
	        add(skin3);
	        add(skin4);
	        add(retour1);
	        add(monaie);
	        add(InfoPrixSkin);
	        add(InfoGainPieces);
	}
	
	//action si il y a appuie sur la souris
	public void mousePressed(MouseEvent epr) {
	        
		if (game == false) {
			
			//récupère les coordonnées de la souris quand clique
	        double x = epr.getX();
	        double y = epr.getY();
	       
	        if(etat == 'm') {
	        	//lancer le jeu
	        	if ( x>380 && x<550 && y>250 && y<300) {
	            game = true;
	            removeAll();
	            bruit.stopMenu();
	            lancementJeu();
	        }
	       
	        	//quitter le jeu
	        if ( x>366 && x<570 && y>325 && y<375) {
	        	quitter();
	        	try {
					rec.setRec(score);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	//fermer la fenêtre
	        	System.exit(0);
	        }
	        
	       //aller dans les paramètres
	        if ( x>770 && x<980 && y>11 && y<56 ) {
	        	etat = 'p';
	            removeAll();
	            parametres();
	        }
	       
	        //aller dans la fenêtre skin
	        if (x>40 && x<140 && y>19 && y<59) {
	        	removeAll();
	        	etat = 's';
	            skin();
	            }
	        }
	       if (etat == 'p') {
	        	//fonction et animation du bouton enemis opaques
	            if (x>310 && x<330 && y>250 && y<270) {
	            	if(pair%2 == 0) {
	            	btOpaque.setColor(Color.GREEN);
	            	actOpacite = true;
	            	}	else {
	            		btOpaque.setColor(Color.RED);
		            	actOpacite = false;
	            	}
	            }
	            
	            pair = (byte) ((pair%2)+1);
	            
	           //retour au menu
	            if ( x>850 && x<950 && y>570 && y<600) {
	                retour.setColor(Color.BLACK);
	                etat = 'm';
	                removeAll();
	                afficheMenu();
	            }  
	          //animation du bouton reset score  
	            if(x>310 && x< 330 && y>280 && y<300 && etat == 'p') {
	                score = 0;
	                btscore0.setColor(Color.BLUE);
	            }
	       }
	}
	            
	            if (game == true) {
		
		//activer la bombe si le perso a obtenu l'autorisation
		if (actBombe == false && act == 1) {
			bomber.setLocation(perso.getX()-20, perso.getY()-22);
			add(bomber);
			delayBombe = sec;
			actBombe = true;
			//bruitage
			bruit.viou();
			}
		act++;
		}
	}
		
	 public void mouseReleased (MouseEvent e) {
	       //récupère les positions de la souris
	        double x = e.getX();
	        double y = e.getY();
	        
	        //animation du bouton reset de score
	        if(x>310 && x< 330 && y>280 && y<300 && etat == 'p') {
	            btscore0.setColor(Color.GREEN);
	        }
	        
	 if(etat == 's') {
	        
		 //achète le skin bleu et annule si le score < 600
	         if ( x>200 && x<355 && y>150 && y<240) { 
	        	 if (score >= 600 || achatR == true) {
	                 skin = 'r';
	                 remove(PasAssezdArgent);
	                 if (achatR == false) score = (short) (score - 600);
	                 achatR = true;
	                 repere.setLocation(270, 250);
	                 add(repere);
	        	 }	else	add(PasAssezdArgent);
	         }
	         
	         //achète le skin bleu  et annule si le score < 600
	         if (x>400 && x<555 && y>150 && y<240 ) { 
	        	 if (score >= 600 || achatB == true) {
	                 skin = 'b';
	                 remove(PasAssezdArgent);
	                 if (achatB == false)score = (short) (score - 600);
	                 achatB = true;
	                 repere.setLocation(470, 250);
	                 add(repere);
	        	 	}	else	add(PasAssezdArgent);
	             }
	          
	        //achète le skin vert et annule si le score < 600
	         if ( x>600 && x<755 && y>150 && y<240) {
	        	 if (score >= 600 || achatV == true) {
	                 skin = 'v';
	                 remove(PasAssezdArgent);
	                 if (achatV == false) score = (short) (score - 600);
	                 achatV = true;
	                 repere.setLocation(670, 250);
	                 add(repere);
	        	 	}	else	add(PasAssezdArgent);
	             }
	         
	         //selectionne le skin de base
	         if (x>400 && x<555 && y>320 && y<410) {
	             skin = 'n';
	             repere.setLocation(470, 420);
                 add(repere);
	             remove(PasAssezdArgent);
	             }
	      
	         //affiche les pieces
	         monaie.setLabel("--Pièces : " + score + "--");
	         add(monaie);
	         
	       //retour au  menu
	        if ( x>850 && x<950 && y>570 && y<600) {
                 removeAll();
                 etat = 'm';
                 afficheMenu();
                 }  
	 		}
		}
	
	//action quand une action interne au programme est detecté
	public void actionPerformed(ActionEvent epe) {
		
		if (game == true) {
			
			//afficher timer vitesse normale 1
			if (epe.getSource() == hAffiche) {
				tempsEcouleReal += tpsReal;
				//convertir en minutes
				int min = tempsEcouleReal/1000/60;
				//convertir en secondes
				secReal = (byte) (tempsEcouleReal/1000%60);
				//affiche le temps
				temps.setLabel(min + " : " + secReal);
				tps = (short) (min*60+secReal);
			}
		
		//condition qui s'effectue quand le programme se confronte au timer2 a afficher en vitesse normale
		if(epe.getSource() == timer) {
			this.setSize(1016,710);
			tempsEcouleTimer += tpsReal;
			//convertir en secondes
			sec = (byte) (tempsEcouleTimer/1000%60);
			
			//faire apparaitre un bonus toutes les 8 secondes
			if ((sec%8)==0 && sec>10) {
				alea = new PosAlea(800, 500);
				imageBonus.setLocation(alea.getRandx(), alea.getRandy());
				imageBonus.setLocation(imageBonus.getX(), imageBonus.getY());
				imageBonus.sendToBack();
				add(imageBonus);
			}
			
			//fait apparaitres des pieces aleatoirment toutes les 5 secondes
			if ((sec%5) == 0 && imageXPieces.size()<=10) {
				alea = new PosAlea(950, 550);
				//creation pièce
				imagePiece = new GImage("piece.png");
				imagePiece.setLocation(alea.getRandx(), alea.getRandy());
				imagePiece.sendBackward();
				imageXPieces.add(imagePiece);
				add(imagePiece);
			}
			
			//enleverla bombe au bout de 5s
			if (actBombe == true && (sec-delayBombe) == 5) {
				remove(bomber);
				actBombe = false;
				}
			
			if (actMiam == true && (sec-delayMiam) == 5) {
				remove(miam);
				actMiam = false;
			}
			
			if (sec%2 == 0 && xEnnemis.size()<2000) {
				
				//boucle qui compte le nombre d'ennemis créés
				for (int i = 0; i<compteur; i++) {
					
				//nombres aleatoires pour la position des ennemis
				alea = new PosAlea(1000, 600);
				
				//condition pour que les ennemis n'apparaissent pas sur notre personnage
				if(!(alea.getRandx()-20<perso.getX() && perso.getX()<alea.getRandx()+20 && alea.getRandy()-20<perso.getY() && perso.getY()<alea.getRandy()+20 && alea.getRandx()-8<xEnnemis.get(i).getX() && xEnnemis.get(i).getY()<alea.getRandy())) {
				//creation de l'ennemi avec positions aleatoires 	
				GOval ennemis = new GOval(alea.getRandx(), alea.getRandy(), 8, 8);
				ennemis.sendToFront();
				//couleur aleatoire des ennemis
				RandomGenerator aleatoire = RandomGenerator.getInstance();
				Color couleurAlea = aleatoire.nextColor();
				
					if (actOpacite == true)	ennemis.setFilled(true);
					else ennemis.setFilled(false);
					
				ennemis.setColor(couleurAlea);
				bouclier.setColor(couleurAlea);
				
				//les caractéritiques des enemis sont associés au numero de l'ennemi
				xEnnemis.add(ennemis);
				add(ennemis);
					}
				}
				//augmenter le nombre d'ennemi au tour suivant d'apparition
				compteur++;
		}
	}
		
		//condition qui s'effectue quand le programme est confronté au chrono a vitesse accelérée
		if (epe.getSource() == chrono) {
			
			//boucle pour appliquer un mouvement independement a chaque ennemis
			for (int i = 0; i<xEnnemis.size()/*Ennemis.size()*/; i++){
			vitesseX = vitesseY = 0.5;
			//retourne le mouvement si le personnage est à l'opposé du mouvement de depart des ennemis
			if (xEnnemis.get(i).getX()>perso.getX())	vitesseX = -vitesseX;
			if (xEnnemis.get(i).getY()>perso.getY()) vitesseY = -vitesseY;
			
			//faire bouger les ennemis
			//((GObject) (Ennemis.get(i))).move(vitesseX, vitesseY);
			xEnnemis.get(i).move(vitesseX, vitesseY);
			
			//suprimer les ennemis qui s'en vont
			if (xEnnemis.get(i).getX()>1050 || xEnnemis.get(i).getX()<-50 || xEnnemis.get(i).getY()>700 || xEnnemis.get(i).getY()<-50) {
				remove(xEnnemis.get(i));
				xEnnemis.remove(i);
			}
			
			//si collision detectée entre un ennemi
			if (perso.getBounds().intersects(xEnnemis.get(i).getBounds())&& actBouclier == false) {
				//bruitage
				bruit.blurp();
				controlMusique = false;
				//remise a zéro
				bruit.stopMusique();
				removeAll();
				actMiam = false;
				chrono.stop();
				timer.stop();
				game = false;
				act = 0;
				//affiche menu
				afficheMenu();
				}	
			
			//si collision entre bouclier et ennemi
			if(bouclier.getBounds().intersects(xEnnemis.get(i).getBounds())&&actBouclier==true) {	//si collision lors du port du bouclier
					remove(xEnnemis.get(i));
					xEnnemis.remove(i);
					remove(bouclier);
					actBouclier = false;
					//bruitage 
					bruit.blurp();
				}
			
			//si collision entre ennemis et bombe
			if (actBombe == true && bomber.getBounds().intersects(xEnnemis.get(i).getBounds())){
				//supprimer ennemis
				remove(xEnnemis.get(i));
				xEnnemis.remove(i);
			}
			
			//diriger le mange tout
			if (actMiam == true) {
				//si les ennemis sont visibles sur le terrain de jeu
				if (xEnnemis.get(verif).isVisible()) {
						vX = vY = 0.15;
						if (xEnnemis.get(verif).getX()< miam.getX()) vX = -vX;
						if (xEnnemis.get(verif).getY()< miam.getY()) vY = -vY;
					miam.move(vX, vY);
					add(miam);
				}	else verif++;
			}
			
			//si collision entre mange tout et ennemis
			if (miam.getBounds().intersects(xEnnemis.get(i).getBounds())) {
				remove(xEnnemis.get(i));
				xEnnemis.remove(i);
			}	
			
		}
			
			//si collision avec un bonus
			if (perso.getBounds().intersects(imageBonus.getBounds()) && sec>10) {
				//bruitage 
				bruit.grui();
				int alea = (int) (Math.random()*2-0.1);
				if (alea == 1) {
				actBouclier = true;
				//ajouter bouclier
				add(bouclier);
				}	else if(alea == 0) {
				//bruitage 
				bruit.viou();
				actMiam = true;
				miam.setLocation(perso.getX(), perso.getY());
				add(miam);
				delayMiam = sec;
				}
				//deplacer le bonus
				imageBonus.setLocation(-50, -50);
				//supprimer bonus
				remove(imageBonus);
				}
			
			//gestion ramassage pieces
			for (int i = 0; i<imageXPieces.size(); i++){
			if (perso.getBounds().intersects(imageXPieces.get(i).getBounds())) {
				//bruitage 
				bruit.grui();
				//supprime la piece
				remove(imageXPieces.get(i));
				imageXPieces.remove(i);
				//ajoute du temps si on intercepte une pièce
				tempsEcouleReal +=4000;
					}
				}
			}
		}
	}
	public void MouseEntered (MouseEvent epr) {
		this.setSize(1016,710);
	}
}
