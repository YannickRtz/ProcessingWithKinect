
// Diese Klasse soll die Daten der Kinect in nutzbare Informationen umwandeln

import processing.core.PApplet;
import processing.core.PVector;
import SimpleOpenNI.*;

public class Analyser{
	PApplet p;
	int circle = 20;
	int[][] pastValues = new int[circle][2];
	int counter = 0; //evlt. spŠter durch globalen counter fuer alle Klassen ersetzen.
	int pointer1 = 0;
	int pointer2 = 0;
	int screenDiagonal;
	int distance1 = 0;
	int speed = 0;
	int direction = 0;
	SimpleOpenNI context;
	
	public Analyser(PApplet parent) {
		p = parent;
		screenDiagonal = (int) Math.sqrt(Math.pow(p.getWidth(),2)+Math.pow(p.getHeight(),2));
	}
	
	public void setContext(SimpleOpenNI cont){
		context = cont;
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
	
	public int getPast(int position, int wert) {
		return pastValues[(pointer1+(20-position))%circle][wert];
	}
	
	public boolean pointInArea(SimpleOpenNI context, int x, int y, int area){
		context.update();
		int min, max;
		boolean rueck = false;
		
		//man kann das sicherlich auch anders machen, indem man direkt den min und max wert uebergibt...
		if(area == 1){
			min = 400;
			max = 800;
		}else if(area == 2){
			min = 700;
			max = 1000;			
		}
		else{
			min = 800;
			max = 1500;
		}
		int[]   depthMap = context.depthMap();
		//ich weiss nicht, ob die rechnung stimmt... muesste man nochmal ausprobieren (stimmt nicht!!!) sobald ein punkt in dem bereich ist, wird auf dieser hoehe alles gebounced
		int index = (int) ((y/1.6) * context.depthWidth() + (x/1.6));
		System.out.println(context.depthHeight());
	    try{
			if(depthMap[index] > min && depthMap[index] < max ){
		    	rueck = true;
		    }
	    }catch (ArrayIndexOutOfBoundsException e) {
	    }
		return rueck;
	}
	
	public String highestPoint(SimpleOpenNI context, int area){
		context.update();
		int min, max;
		int steps= 3;
		int dot = 0;
		String rueck;
		
		//man kann das sicherlich auch anders machen, indem man direkt den min und max wert uebergibt...
		if(area == 1){
			min = 400;
			max = 800;
		}else if(area == 2){
			min = 700;
			max = 1000;			
		}
		else{
			min = 800;
			max = 1500;
		}
		int[]   depthMap = context.depthMap();
		//ich weiss nicht, ob die rechnung stimmt... muesste man nochmal ausprobieren
		
		  for(int y=0;y < depthMap.length;y+=steps){
			  if(dot==0 && depthMap[y] > min && depthMap[y] < max){
				 dot = y; 				 
			  }
		  }
		  //PVector hallo;
		  rueck = (dot/ context.depthHeight()) + " " + (dot / context.depthWidth());
		  	System.out.println(rueck);
		return rueck;
	}
	
	// -----------------   PARTICLE --------------------
	
	void movement(Particle part) {
		  part.movement();
		  collisionTest(part);
	}
	void collisionTest(Particle part){
		//Rand
		  if (part.getX()<0 || part.getX()>p.width) {
			  part.reverseSpeedX();
	    }
		//bottom
	    if (part.getY()>p.height) {
	    	part.reset();
	    }
	    //Body
	    if (pointInArea(context, (int) part.getX(), (int) part.getY(), 2)) {
	    	part.y=part.y-10;
	    	part.ySpeed =(float) (-part.ySpeed*0.95);
	    	part.satu = 0;
	    	part.colorAR = 255;
	    	part.colorST = 255;
	    }
	  }
	
	
	
	
	// -----------------   HAND --------------------
	
	// to do
}

