import processing.core.*;


public class Particles{
	
	Particle [] particles = new Particle[50];
	PApplet p; 	// Parent-Applet
	Analyser analyser;
	
	
	public Particles(PApplet mainClass,Analyser analyser) {
		p = mainClass;
		this.analyser = analyser;
		//p.background(255);
		//p.colorMode(p.HSB);
		//p.rectMode(p.CENTER);
		for (int i=0;i<particles.length;i++) {
		  particles[i] = new Particle(p);
		}
		//p.smooth();
	}
	public void iterate(int c){
		//p.background(0);
		for (int i=0;i<particles.length;i++) {
		  //particles[i].movement(damper);
		  analyser.movement(particles[i]);
		  for (int j=i+1;j<particles.length;j++) {
		    for (int k=j+1;k<particles.length;k++) {
		      particles[i].drawTri(particles[j], particles[k]);
		    }
		    particles[i].ligature(particles[j]);
		  }
		  particles[i].display();
		}
		//damper.movement();
		//damper.display();
		}
	}


