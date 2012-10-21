
// Diese Klasse soll der erste Prototyp werden

import processing.core.PApplet;


public class StarrySky {
	
	PApplet p; 	// Parent-Applet
	Analyser analyser;
	Star[] stars = new Star[40];

	public StarrySky(PApplet mainClass,Analyser analyser) {
		p = mainClass;
		this.analyser = analyser;
		for (Star star : stars) {
			star = new Star(p,analyser);
		}
	}
	
	public void iterate(int c) {  // C ist der globale Counter
		for (Star star : stars) {
			star.shine();
		}
	}

}
