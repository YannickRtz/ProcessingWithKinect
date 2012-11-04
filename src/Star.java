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
	int moving = 0;
	int direction = 1;
	int distance = 0;
	
	public Star(PApplet mainClass,Analyser analyser) {
		p = mainClass;
		this.analyser = analyser;
		x = random.nextInt(1024);
		y = random.nextInt(768);
		size = random.nextInt(10);
	}
	
	void shine() {
		distance = (int) PApplet.dist(x, y, p.mouseX, p.mouseY);
		if (analyser.getSpeed() > 30 && distance < 30) {
			moving = 8;
			direction = analyser.getDirection();
		}
		if (moving > 0) {
			move();
		}
		if (distance < 100) {
			twinkle();
		} else {
			if (size < 5) {
				p.stroke(60);
				p.point(x, y);
			} else if (size < 6) {
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
		twinkle = ((twinkle+1)%4);
		if (twinkle == 0)
			p.stroke(255,255,0);
		else if (twinkle == 1)
			p.stroke(255,0,255);
		else if (twinkle == 2)
			p.stroke(0,255,255);
		else if (twinkle == 3)
			p.stroke(50,50,0);
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
		switch (direction) {
          	case 1:
          		x = x + (int)(moving/2);
          		y = y - (moving);
          		break;
          	case 2:
          		x = x + (moving);
          		y = y - (int)(moving/2);
          		break;
          	case 3:
          		x = x + moving;
          		y = y + (int)(moving/2);
          		break;
          	case 4:
          		x = x + (int)(moving/2);
          		y = y + (moving);
          		break;
          	case 5:
          		x = x - (int)(moving/2);
          		y = y + (moving);
          		break;
          	case 6:
          		x = x - moving;
          		y = y + (int)(moving/2);
          		break;
          	case 7:
          		x = x - moving;
          		y = y - (int)(moving/2);
          		break;
          	case 8:
          		x = x - (int)(moving/2);
          		y = y - (moving);
          		break;
        }
		moving--;
	}
	
}
