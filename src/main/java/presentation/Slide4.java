package presentation;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Slide4 extends Presentation {
	
	private ImageLayer design;
	
	public Slide4(int w, int h) {
		super(w, h);
	}
	
	public void load() {
		super.load();
		design = new ImageLayer("design.jpg");
		design.setY(250);
		design.centralizeX(this);
	}
	
	public void draw(Graphic g) {
		super.draw(g);
		
		g.setFontSize(38);
		g.drawStringShadowX(40, "Concepção");
		
		g.setFontSize(32);
		g.drawStringShadowX(100, "Design/Sketches");
		
		design.draw(g);
	}
	
	public void nextSlide() {
		nextApplication = new Slide5(w,h);
	}
	
	public void previousSlide() {
		nextApplication = new Slide3(w,h);
	}

}
