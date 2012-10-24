import processing.core.*;



public class Test1 extends PApplet{
Damper damper;
Particle [] particles = new Particle[50];

@Override
public void setup() {
	size(800, 600);
	frameRate(30);
	background(255);
	colorMode(HSB);
	rectMode(CENTER);
	for (int i=0;i<particles.length;i++) {
	  particles[i] = new Particle(this);
	}
	damper = new Damper(100,20, this);
	smooth();
}

public void draw() {
	background(0);
	for (int i=0;i<particles.length;i++) {
	  particles[i].movement(damper);
	  for (int j=i+1;j<particles.length;j++) {
	    for (int k=j+1;k<particles.length;k++) {
	      particles[i].drawTri(particles[j], particles[k]);
	    }
	    particles[i].ligature(particles[j]);
	  }
	  particles[i].display();
	}
	damper.movement();
	//damper.display();
	}
}
