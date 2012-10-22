
// Diese ist die Steuernde Klasse, sie enthält die Main-Methode, zeichnet selbst aber nichts
// (bis auf Debug-Informationen)

import processing.core.PApplet;
import processing.core.PFont;


@SuppressWarnings("serial")
public class MainClass extends PApplet {
	
	// Variablen
	int defaultWidth = 1024;
	int defaultHeight = 768;
	int counter = 0;
	PFont arial;
	Analyser analyser = new Analyser(this);
	StarrySky starrySky = new StarrySky(this,analyser);
	
	
	// Setup
	@Override
	public void setup() {
		size(defaultWidth,defaultHeight);
		frameRate(60);
		smooth();
		background(0);
		arial = createFont("Arial",48);
		textFont(arial,30); 				// Oversampled Font
	}
	
	
	// Hier beginnt die "Dauerschleife"
	@Override
	public void draw() {
		if(counter < 1500) { 				//Bei 60fps 20 Sekunden Ausführung des ersten Parts
			analyser.analyseMovement(mouseX, mouseY);
			starrySky.iterate(counter);
		} else if (counter >= 1200) {  		// Am Ende des "Programms" Zurücksetzen auf Anfang
			counter = 0;
		}
		
		fill(0);
		noStroke();
		rect(0,0,width,45);
		fill(255); 							// white float frameRate
		text(frameRate,5,35);
		text(mouseX,200,35);
		text(mouseY,280,35);
		text(analyser.getSpeed(),400,35);
		text(analyser.getDirection(),480,35);
		counter++;
	}
	
	public int getWidth() {
		return defaultWidth;
	}
	public int getHeight() {
		return defaultHeight;
	}
	
	// Main-Methode
	public static void main(String[] args) {
		PApplet.main(new String[] { MainClass.class.getName() });
	}

}
