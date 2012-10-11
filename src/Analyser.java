import processing.core.PApplet;


public class Analyser {
	PApplet p;
	int circle = 20;
	int[][] pastValues = new int[circle][2];
	int result = 0;
	int counter = 0; //evlt. spŠter durch globalen counter fŸr alle Klassen ersetzen.
	int pointer1 = 0;
	int pointer2 = 0;
	int screenDiagonal;
	int distance1 = 0;
	
	public Analyser(PApplet parent) {
		p = parent;
		screenDiagonal = (int) Math.sqrt(Math.pow(p.getWidth(),2)+Math.pow(p.getHeight(),2));
	}
	
	public int analyseSpeed(int positionX, int positionY) {
		result = 0;
		pointer1 = counter % circle;
		pointer2 = (counter+1) % circle;
		pastValues[pointer1][0] = positionX;
		pastValues[pointer1][1] = positionY;
		distance1 = (int) Math.sqrt(Math.pow((pastValues[pointer1][0] - pastValues[pointer2][0]),2)
								+ Math.pow((pastValues[pointer1][1] - pastValues[pointer2][1]),2));
		
		// Die erste Stelle des Ergebnisses gibt an, in welche Richtung die Bewegung stattfindet.
		// 0 = Oben Links; 1 = Oben Rechts; 2 = Unten Links; 3 = Unten Rechts
		// Die Beiden nachfolgenden Stellen sind die Geschwindigkeit (in Prozent der Screendiagonale).
		if (pastValues[pointer1][0] >= pastValues[pointer2][0]) {
			result += 100;
		}
		if (pastValues[pointer1][1] >= pastValues[pointer2][1]) {
			result += 200;
		}
		result += ((distance1*100)/screenDiagonal);
		counter++;
		return result;
	}
}