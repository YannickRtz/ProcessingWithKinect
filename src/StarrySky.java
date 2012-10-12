
// Diese Klasse soll der erste Prototyp werden

import processing.core.PApplet;


public class StarrySky {
	
	PApplet p; 	// Parent-Applet

	public StarrySky(PApplet mainClass) {
		p = mainClass;
	}
	
	public void iterate(int c) {  // C ist der globale Counter
		p.ellipse(p.mouseX, p.mouseY, 20, 20);
	}

}
