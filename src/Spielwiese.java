
// Diese Klasse dient nur zum Testen, zum Beispiel der Analyser Klasse

import processing.core.PApplet;
import processing.core.PFont;


@SuppressWarnings("serial") //Entfernt nur die serializable-Warnung aus Eclipse
public class Spielwiese extends PApplet {
	
	int customWidth = 1024;
	int customHeight = 768;
	int counter = 0;
	int analyserValue1 = 0;
	int analyserSpeed = 0;
	DigitalesRauschen rauschen = new DigitalesRauschen(this,200,200);
	Analyser analyser = new Analyser(this);
	PFont arial;
	
	public int getWidth() {
		return customWidth;
	}
	public int getHeight() {
		return customHeight;
	}
	
	// Setup-Methode
	@Override
	public void setup() {
		size(customWidth,customHeight);
		frameRate(60);
		background(60);
		arial = createFont("Arial Bold",48);
		textFont(arial,36); // Oversampled Font
	}
	
	// Draw-Methode
	@Override
	public void draw() {
		analyser.analyseMovement(mouseX, mouseY);
		analyserSpeed = analyser.getSpeed();
		if (counter > 0) {
			rauschen.rauschen();
			counter--;
		} else if (counter == 0) {
			background(60);
			counter--;
		} else {
			fill(60,60,60,5);
			rect(0, 0, width, height);
			noStroke();
			fill(8*analyserSpeed,255-analyserSpeed*8,0);
			ellipse(mouseX,mouseY,15,15);
		}
		
		// Framerate und Analysewerte anzeigen
		fill(0);
		rect(0,0,width,45);
		fill(255); // white float frameRate
		text(frameRate,5,35);
		text(analyserSpeed,200,35);
		text(analyser.getDirection(),300,35);
	}
	
	// Mausklick-Methode
	public void mousePressed() {
		counter = 20;
	}
	
	// Main-Methode
	public static void main(String[] args) {
		PApplet.main(new String[] { Spielwiese.class.getName() });
	}

}
