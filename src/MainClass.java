
// Diese ist die Steuernde Klasse, sie enthält die Main-Methode, zeichnet selbst aber nichts
// (bis auf Debug-Informationen)

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;
import processing.core.PFont;


@SuppressWarnings("serial")
public class MainClass extends PApplet {
	
	// Variablen
	
	boolean dev = true;
	
	int defaultWidth = 1024;
	int defaultHeight = 768;
	int counter = 0;
	PFont arial;
	Analyser analyser = new Analyser(this);
	StarrySky starrySky = new StarrySky(this,analyser);
	Particles particles = new Particles(this,analyser);
	public static SimpleOpenNI context;
	
	// Setup
	@Override
	public void setup() {
		size(defaultWidth,defaultHeight);

		frameRate(40);
		smooth();
		background(0);
		arial = createFont("Arial",48);
		textFont(arial,30); 				// Oversampled Font
		
		context = new SimpleOpenNI(this,SimpleOpenNI.RUN_MODE_MULTI_THREADED);
		context.enableDepth();
		context.setMirror(true);
		context.enableHands();
		Const.setContext(context);
		if(context.enableDepth() == false){
			println("Can't open the depthMap, maybe the camera is not connected!"); 
			// exit program if kinect not connected
			//exit();
			
			//analyser context uebergeben

		}
		analyser.setContext(context);
		return;
	}
	
	
	// Hier beginnt die "Dauerschleife"
	@Override
	public void draw() {
		if(counter < 500) { 				//Bei 60fps 20 Sekunden Ausführung des ersten Parts
			analyser.analyseMovement(mouseX, mouseY);
			starrySky.iterate(counter);
		} else if (counter >= 500) {  		// Am Ende des "Programms" Zurücksetzen auf Anfang
			background(0);
			particles.iterate(counter);
			colorMode(HSB);
			rectMode(CENTER);
			smooth();
		}
		else if(counter >= 1000){
			counter = 0;
		}
		
		// im developermodus wird information ausgegeben
		if(dev){
			fill(0);
			noStroke();
			rect(0,0,width,45);
			fill(255); 							// white float frameRate
			text(frameRate,5,35);
			text(mouseX,200,35);
			text(mouseY,280,35);
			text(analyser.getSpeed(),400,35);
			text(analyser.getDirection(),480,35);
			text(counter, 550,35);
			counter++;
		}
		//String arg = analyser.highestPoint(context, 1);
	}
	
	public int getWidth() {
		return defaultWidth;
	}
	public int getHeight() {
		return defaultHeight;
	}

	
	// Main-Methode
	public static void main(String[] args) {
		PApplet.main(new String[] { MainClass.class.getName() });
	}

}
