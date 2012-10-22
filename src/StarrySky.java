
// Diese Klasse soll der erste Prototyp werden

import processing.core.PApplet;
import processing.core.PImage;


public class StarrySky {
	
	PApplet p; 	// Parent-Applet
	Analyser analyser;
	PImage moon;
	PImage skyline;
	int moonTwinkle=0;
	Star[] stars = new Star[400];  // Anzahl Sterne wird hier festgelegt

	public StarrySky(PApplet mainClass,Analyser analyser) {
		p = mainClass;
		this.analyser = analyser;
		for (int i = 0; i < stars.length;i++) {
			stars[i] = new Star(p,analyser);
		}
		moon = p.loadImage("moon.jpg");
		skyline = p.loadImage("Skyline.jpg");
	}
	
	public void iterate(int c) {// C ist der globale Counter
		p.background(0);
		int moonDistance = (int) PApplet.dist(p.mouseX, p.mouseY, 218, 156);
		if (moonDistance <= 100 && analyser.getSpeed() < 2) {
			moonTwinkle = (moonTwinkle+1)%3;
			if (moonTwinkle == 0)
				p.tint(255, 255, 0);
			if (moonTwinkle == 1)
				p.tint(255, 0, 255);
			if (moonTwinkle == 2)
				p.tint(0, 255, 255);
		}
		p.image(moon, 70, 50,287,215);
		p.tint(255);
		p.image(skyline,0,700,1024,50);
		for (Star star : stars) {
			star.shine();
		}
		p.noStroke();
		p.fill(255, 3);
		for (int r = 200;r>0;r=r-10){
			p.ellipse(218,156,r+120,r+120);  	// Zieht zu viel Leistung :-(
		}
	}
}
