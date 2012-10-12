
// Diese Testklasse ist unwichtig und ist nur Teil der "Spielwiese"

import java.util.Random;
import processing.core.PApplet;

public class DigitalesRauschen {
	int spalten;
	int zeilen;
	int[][] screen;
	PApplet p;
	Random random = new Random();
	
	// Konstruktor
	DigitalesRauschen(PApplet parent, int spalten, int zeilen) {
		this.p = parent;
		this.spalten = spalten;
		this.zeilen = zeilen;
		this.screen = new int[spalten][zeilen];
	}
	
	// Diese Methode soll das Rauschen Zeichnen
	public void rauschen() {
		// Punkte generieren
		for (int i = 0; i < spalten; i++) {
			for (int j = 0; j < zeilen; j++) {
				screen[i][j] = random.nextInt(2);
			}
		}
		float positionX = p.width/spalten;
		float positionY = p.height/zeilen;
		p.noStroke();
		
		// Punkte anzeigen
		for (int i = 0; i < spalten; i++) {
			for (int j = 0; j < zeilen; j++) {
				p.fill(screen[i][j]*254);
				p.rect(i*positionX, j*positionY,positionX,positionY);
			}
		}
	}
}
