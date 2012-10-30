
// Diese Klasse soll der erste Prototyp werden

import processing.core.PApplet;
import processing.core.PImage;


public class StarrySky {
	
	PApplet p; 	// Parent-Applet
	Analyser analyser;
	PImage moon;
	PImage skyline;
	PImage gradient;
	int moonTwinkle=0;
	Star[] stars = new Star[400];  // Anzahl Sterne wird hier festgelegt

	public StarrySky(PApplet mainClass,Analyser analyser) {
		p = mainClass;
		this.analyser = analyser;
		for (int i = 0; i < stars.length;i++) {
			stars[i] = new Star(p,analyser);
		}
		moon = p.loadImage("moon.png");
		skyline = p.loadImage("Skyline.png");
		gradient = p.loadImage("Skylineverlauf.png");
	}
	
	public void iterate(int c) {// C ist der globale Counter
		p.fill(0,85);
		p.rect(0, 0, p.width, p.height);
		p.image(gradient,0,610);
		
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
		p.noStroke();
		p.fill(0);
		p.ellipse(210, 190, 300, 300);
		for (Star star : stars) {
			star.shine();
		}
		p.image(moon, 70, 50);
		p.tint(255);
		p.image(skyline,0,610);
		p.noStroke();
	}
	
	void shoot() {
		
	}
}
