package game;

public class PosAlea {
	
	int randx, randy;
	
	public PosAlea(int xmax, int ymax) {
		randx = (int) (Math.random()*xmax)+9;
		randy = (int) (Math.random()*ymax)+9;
	}

	public int getRandx() {
		return randx;
	}

	public int getRandy() {
		return randy;
	}
}

