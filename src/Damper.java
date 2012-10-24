import processing.core.*;

class Damper{
PApplet p;
float x, y;
float w, h;

Damper(float w_,float h_, PApplet parent) {
	p = parent;
	x = parent.width/2;
	y = parent.height/2;
	w = w_;
	h = h_;
  }

void movement() {
	float a = (float) 0.3;
	x = PApplet.lerp(x, (float)p.mouseX, a);
	y = PApplet.lerp(y, (float)p.mouseY, a);
}

void display() {
	p.fill(100, 50);
	p.noStroke();
	p.rect(x, y, w, h);
  }
}
