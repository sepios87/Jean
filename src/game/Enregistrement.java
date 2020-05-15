package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Enregistrement {
	
	//d�clarations
	//objet file pour acc�der a l'emplacement du fichier 
	private File f;
	//variable pour renvoyer ancien score
	private int piece;
	//objet scanner pour lire le document texte
	private Scanner sc;
	private FileWriter fw;
	//constructeur
	public Enregistrement() {
		//cr�er un dossier pour stoker le bloc note
		new File("../RegistreJeu").mkdirs();
		//cr�er le bloc note
		f = new File("../RegistreJeu/Sauvegarde");
	}
	
	public void setRec(int piece) throws IOException {
		//permet d'�crire sur le bloc note
		fw = new FileWriter(f);
		
		//le try, le catch et le finally permettent de capturer une erreur c'est a dire d'ex�cuter le programme m�me si cela peut engendrer des erreurs
		try {
			//�crire le nombre de pi�ces a sauvegarder
			fw.write(Integer.toString(piece));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//fermer le bloc notes pour ne pas cr�er d'erreurs
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
			//r�cup�rer ce qui est �crit sur la premi�re ligne du bloc note
			piece = sc.nextInt();
			//retouner une valeur enti�re
		} else piece = 0;
		return piece;
		
	}

}
