package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Enregistrement {
	
	//déclarations
	//objet file pour accéder a l'emplacement du fichier 
	private File f;
	//variable pour renvoyer ancien score
	private int piece;
	//objet scanner pour lire le document texte
	private Scanner sc;
	private FileWriter fw;
	//constructeur
	public Enregistrement() {
		//créer un dossier pour stoker le bloc note
		new File("../RegistreJeu").mkdirs();
		//créer le bloc note
		f = new File("../RegistreJeu/Sauvegarde");
	}
	
	public void setRec(int piece) throws IOException {
		//permet d'écrire sur le bloc note
		fw = new FileWriter(f);
		
		//le try, le catch et le finally permettent de capturer une erreur c'est a dire d'exécuter le programme même si cela peut engendrer des erreurs
		try {
			//écrire le nombre de pièces a sauvegarder
			fw.write(Integer.toString(piece));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//fermer le bloc notes pour ne pas créer d'erreurs
			fw.close();
	}
	
	public int getRec() {
		if (f.exists()) {
			try {
				sc = new Scanner(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//récupérer ce qui est écrit sur la première ligne du bloc note
			piece = sc.nextInt();
			//retouner une valeur entière
		} else piece = 0;
		return piece;
		
	}

}
