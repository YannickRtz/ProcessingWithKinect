import java.util.Random;

import processing.core.PApplet;


public class Star {
	PApplet p;
	Analyser analyser;
	Random random = new Random();
	int size;
	int x = 0;
	int y = 0;
	int twinkle = 0;
	
	public Star(PApplet mainClass,Analyser analyser) {
		p = mainClass;
		this.analyser = analyser;
		x = random.nextInt(1024);
		y = random.nextInt(768);
		size = random.nextInt(10);
	}
	
	void shine() {
		if (PApplet.dist(x, y, p.mouseX, p.mouseY) < 100) {
			twinkle();
		} else {
			if (size < 4) {
				p.stroke(60);
				p.point(x, y);
			} else if (size < 5) {
				p.stroke(255);
				p.point(x, y);
				p.point(x, y-1);
				p.point(x, y+1);
				p.point(x-1, y);
				p.point(x+1, y);
			} else {
				p.stroke(200);
				p.point(x, y);
			}
		}
	}
	
	void twinkle() {
		twinkle = ((twinkle+1)%3);
		if (twinkle == 0)
			p.stroke(255,255,0);
		if (twinkle == 1)
			p.stroke(255,0,255);
		if (twinkle == 2)
			p.stroke(0,255,255);
		if (size < 4) {
			p.point(x, y);
		} else if (size < 5) {
			p.point(x, y);
			p.point(x, y-1);
			p.point(x, y+1);
			p.point(x-1, y);
			p.point(x+1, y);
		} else {
			p.point(x, y);
		}
	}
	
	void move() {
		
	}
	
	void shoot() {
		
	}
	
}
