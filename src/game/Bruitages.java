package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Bruitages {
	
	Clip clipBlurp, clipGrui, clipViou, clipMusique, clipLancement, clipMenu;
	AudioInputStream blurp = null;
	AudioInputStream grui = null;
	AudioInputStream viou = null;
	AudioInputStream musique = null;
	AudioInputStream lancement = null;
	AudioInputStream menu = null;
	
	public Bruitages() {
	}

	public void blurp() {
		try {
			clipBlurp = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blurp = AudioSystem.getAudioInputStream(getClass().getResource("blurp.wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clipBlurp.open(blurp);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	clipBlurp.start();
	}
	
	public void grui() {
		try {
			clipGrui = AudioSystem.getClip();
			/*clipViou = AudioSystem.getClip();
			clipMusique = AudioSystem.getClip();
			clipMenu = AudioSystem.getClip();
			clipLancement = AudioSystem.getClip();*/
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			grui = AudioSystem.getAudioInputStream(getClass().getResource("grui.wav"));
			/*viou = AudioSystem.getAudioInputStream(getClass().getResource("viou.wav"));
			musique = AudioSystem.getAudioInputStream(getClass().getResource("musique.wav"));
			lancement = AudioSystem.getAudioInputStream(getClass().getResource("lancement.wav"));
			menu = AudioSystem.getAudioInputStream(getClass().getResource("menu.wav"));*/
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clipGrui.open(grui);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	clipGrui.start();
	}
	
	public void viou() {
		try {
			clipViou = AudioSystem.getClip();
			/*clipMusique = AudioSystem.getClip();
			clipMenu = AudioSystem.getClip();
			clipLancement = AudioSystem.getClip();*/
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			viou = AudioSystem.getAudioInputStream(getClass().getResource("viou.wav"));
			/*musique = AudioSystem.getAudioInputStream(getClass().getResource("musique.wav"));
			lancement = AudioSystem.getAudioInputStream(getClass().getResource("lancement.wav"));
			menu = AudioSystem.getAudioInputStream(getClass().getResource("menu.wav"));*/
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clipViou.open(viou);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	clipViou.start();
	}
	
	public void musique() {
		try {
			clipMusique = AudioSystem.getClip();
			/*clipMenu = AudioSystem.getClip();
			clipLancement = AudioSystem.getClip();*/
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blurp = AudioSystem.getAudioInputStream(getClass().getResource("blurp.wav"));
			musique = AudioSystem.getAudioInputStream(getClass().getResource("musique.wav"));
			/*lancement = AudioSystem.getAudioInputStream(getClass().getResource("lancement.wav"));
			menu = AudioSystem.getAudioInputStream(getClass().getResource("menu.wav"));*/
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clipMusique.open(musique);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	clipMusique.start();
	}
	
	public void lancement() {
		try {
			clipLancement = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lancement = AudioSystem.getAudioInputStream(getClass().getResource("lancement.wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clipLancement.open(lancement);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	clipLancement.start();
	}
	
	public void menu() {
		try {
			clipMenu = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blurp = AudioSystem.getAudioInputStream(getClass().getResource("blurp.wav"));
			menu = AudioSystem.getAudioInputStream(getClass().getResource("menu.wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clipMenu.open(menu);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	clipMenu.start();
	}
	
	public void stopMenu() {
		clipMenu.stop();
	}
	
	public void stopMusique() {
		clipMusique.stop();
	}
	
}
