
// Diese Klasse soll die Daten der Kinect in nutzbare Informationen umwandeln

import processing.core.PApplet;
//import SimpleOpenNI.*;

public class Analyser{
	PApplet p;
	int circle = 20;
	int[][] pastValues = new int[circle][2];
	int counter = 0; //evlt. spŠter durch globalen counter fŸr alle Klassen ersetzen.
	int pointer1 = 0;
	int pointer2 = 0;
	int screenDiagonal;
	int distance1 = 0;
	int speed = 0;
	int direction = 0;
	
	public Analyser(PApplet parent) {
		p = parent;
		screenDiagonal = (int) Math.sqrt(Math.pow(p.getWidth(),2)+Math.pow(p.getHeight(),2));
	}
		  
	public void analyseMovement(int positionX, int positionY) {
		pointer1 = counter % circle;
		pointer2 = (counter+1) % circle;
		pastValues[pointer1][0] = positionX;
		pastValues[pointer1][1] = positionY;
		distance1 = (int) Math.sqrt(Math.pow((pastValues[pointer1][0] - pastValues[pointer2][0]),2)
								+ Math.pow((pastValues[pointer1][1] - pastValues[pointer2][1]),2));
		
		// Richtung (Wie auf einer Uhr, die nur 8 statt 12 Stunden mit einer Umdrehung hat)
		direction = 0;
		if (pastValues[pointer1][0] >= pastValues[pointer2][0]) {
			if (pastValues[pointer1][1] >= pastValues[pointer2][1]) {
				if (pastValues[pointer1][0] - pastValues[pointer2][0] >= pastValues[pointer1][1] - pastValues[pointer2][1]) {
					direction = 3;
				} else {
					direction = 4;
				}
			} else {
				if (pastValues[pointer2][0] - pastValues[pointer1][0] >= pastValues[pointer1][1] - pastValues[pointer2][1]) {
					direction = 1;
				} else {
					direction = 2;
				}
			}
		} else {
			if (pastValues[pointer1][1] >= pastValues[pointer2][1]) {
				if (pastValues[pointer2][0] - pastValues[pointer1][0] >= pastValues[pointer1][1] - pastValues[pointer2][1]) {
					direction = 6;
				} else {
					direction = 5;
				}
			} else {
				if (pastValues[pointer2][0] - pastValues[pointer1][0] >= pastValues[pointer2][1] - pastValues[pointer1][1]) {
					direction = 7;
				} else {
					direction = 8;
				}
			}
		}
		
		// Geschwindigkeit (in Doppelte Prozent der Screendiagonale)
		speed = ((distance1*200)/screenDiagonal);
		counter++;
	}
	
	public int getSpeed() {
		return speed;
	}
	public int getDirection() {
		return direction;
	}
	
//	public boolean pointInArea(SimpleOpenNI context, int x, int y, int area){
//		context.update();
//		int min, max;
//		boolean rueck = false;
//		
//		//man kann das sicherlich auch anders machen, indem man direkt den min und max wert uebergibt...
//		if(area == 1){
//			min = 400;
//			max = 800;
//		}else if(area == 2){
//			min = 700;
//			max = 1000;			
//		}
//		else{
//			min = 800;
//			max = 1500;
//		}
//		
//		int[]   depthMap = context.depthMap();
//		//ich weiss nicht, ob die rechnung stimmt... muesste man nochmal ausprobieren
//		int index = y * context.depthWidth() + x;
//	    if(depthMap[index] > min && depthMap[index] < max ){
//	    	rueck = true;
//	    }
//		return rueck;
//	}
}

