import processing.core.*;


class Particle {
	PApplet p;
	  float x, y;
	  float xSpeed, ySpeed;
	  float yAccel;
	  float thres, areaThres;
	  float satu;
	  int c;
	  Particle(PApplet parent) {
		p = parent;
	    satu = 0;
	    x = p.random(p.width);
	    y = p.random(p.height);
	    xSpeed = p.random((float)0.3, 3);
	    ySpeed = p.random((float)0.3, 2);
	    yAccel = p.random((float)0.1, 1);
	    thres = 75;
	    areaThres = 2000;
	    c = p.color(p.random(360), 255, 255);
	  }
	  void movement(Damper damper) {
	    ySpeed += yAccel;
	    ySpeed = PApplet.constrain(ySpeed, -5, 5);
	    x += xSpeed;
	    y += ySpeed;
	    collisionTest(damper);
	  }
	  void collisionTest(Damper damper){
	    if (x<0 || x>p.width) {
	      xSpeed = -xSpeed;
	    }
	    if (y>p.height) {
	      reset();
	    }
	    if (y>=p.mouseY-damper.h/2 && y<=p.mouseY+damper.h/2 && x>=p.mouseX-damper.w/2 && x<=p.mouseX+damper.w/2) {
	      y=p.mouseY-10;
	      ySpeed =(float) (-ySpeed*0.95);
	      satu = 255;
	    }
	  }
	  void  display() {
	    p.strokeWeight(3);
	    p.stroke(c, 100);
	    p.point(x, y);
	  }
	  void ligature(Particle particle_) {
	    float distance = PApplet.dist(x, y, particle_.x, particle_.y);
	    if (distance < thres) {
	      float averageHue = (p.hue(c)+p.hue(particle_.c))/2;
	      p.stroke(averageHue, satu, 255, (thres-distance)*(255/thres));
	      p.strokeWeight((thres-distance)/10);
	      p.line(x, y, particle_.x, particle_.y);
	    }
	  }
	  void drawTri(Particle particle1_, Particle particle2_) {
	    float distance1 = PApplet.dist(x, y, particle1_.x, particle1_.y);
	    float distance2 = PApplet.dist(x, y, particle2_.x, particle2_.y);
	    float distance3 = PApplet.dist(particle1_.x, particle1_.y, particle2_.x, particle2_.y);
	    float l = (distance1+distance2+distance3)/2;
	    float s = PApplet.sqrt(l*(l-distance1)*(l-distance2)*(l-distance3));
	    if (s < areaThres && distance1<thres && distance2<thres && distance3<thres) {
	      p.noStroke();
	      float averageHue = (p.hue(c)+p.hue(particle1_.c)+p.hue(particle2_.c))/3;
	      p.fill(averageHue, satu, 255, 255-(s*255/areaThres));
	      p.beginShape();
	      p.vertex(x, y);
	      p.vertex(particle1_.x, particle1_.y);
	      p.vertex(particle2_.x, particle2_.y);
	      p.endShape(PConstants.CLOSE);
	    }
	  }
	  void reset() {
	    y = 0;
	    satu = 0;
	  }
	}
