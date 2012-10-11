import processing.core.*;

@SuppressWarnings("serial") //Entfernt nur die serializable-Warnung aus Eclipse
public class Spielwiese extends PApplet {
	
	int customWidth = 400;
	int customHeight = 400;
	int counter = 0;
	int analyserValue1 = 0;
	int analyserValue2 = 0;
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
		analyserValue1 = analyser.analyseSpeed(mouseX, mouseY);
		analyserValue2 = analyserValue1%100;
		if (counter > 0) {
			rauschen.rauschen();
			counter--;
		} else if (counter == 0) {
			background(60);
			counter--;
		} else {
			fill(60,60,60,5);
			rect(0, 0, customWidth, customHeight);
			noStroke();
			fill(6*analyserValue2,255-analyserValue2*6,0);
			ellipse(mouseX,mouseY,15,15);
		}
		
		// Framerate und Analysewerte anzeigen
		fill(0);
		rect(0,0,customWidth,45);
		fill(255); // white float frameRate
		text(frameRate,5,35);
		text(analyserValue1,200,35);
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
