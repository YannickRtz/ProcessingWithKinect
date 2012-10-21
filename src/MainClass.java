
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
	Analyser analyser;
	StarrySky starrySky = new StarrySky(this,analyser);
	
	
	// Setup
	@Override
	public void setup() {
		size(defaultWidth,defaultHeight);
		frameRate(60);
		background(60);
		arial = createFont("Arial",48);
		textFont(arial,30); 				// Oversampled Font
	}
	
	
	// Hier beginnt die "Dauerschleife"
	@Override
	public void draw() {
		if(counter < 1500 && counter > 300) { 				//Bei 60fps 20 Sekunden Ausführung des ersten Parts
			starrySky.iterate(counter);
		} else if (counter >= 1200) {  		// Am Ende des "Programms" Zurücksetzen auf Anfang
			counter = 0;
		}
		
		fill(0);
		rect(0,0,width,45);
		fill(255); 							// white float frameRate
		text(frameRate,5,35);
		counter++;
	}
	
	
	// Main-Methode
	public static void main(String[] args) {
		PApplet.main(new String[] { MainClass.class.getName() });
	}

}
