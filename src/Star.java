import java.util.Random;

import processing.core.PApplet;


public class Star {
	PApplet p;
	Analyser analyser;
	Random random = new Random();
	int size;
	int x = 0;
	int y = 0;
	
	public Star(PApplet mainClass,Analyser analyser) {
		p = mainClass;
		this.analyser = analyser;
		x = random.nextInt(1024);
		y = random.nextInt(768);
		size = random.nextInt(10);
	}
	
	void shine() {
		p.ellipse(x, y, 4, 4);
	}
	
	void twinkle() {
		
	}
	
	void move() {
		
	}
	
	void shoot() {
		
	}
	
}
