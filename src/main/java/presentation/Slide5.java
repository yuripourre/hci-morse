package presentation;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Slide5 extends Presentation {
	
	private ImageLayer foto1;
	private ImageLayer foto2;
	
	public Slide5(int w, int h) {
		super(w, h);
	}
	
	public void load() {
		super.load();
		foto1 = new ImageLayer(150,200,"foto1.jpg");
		foto2 = new ImageLayer(650,200,"foto2.jpg");
	}
	
	public void draw(Graphic g) {
		super.draw(g);
		
		g.setFontSize(38);
		g.drawStringShadowX(40, "Construção");
		
		g.setFontSize(32);
		g.drawStringShadowX(100, "Processo de Prototipação");
		
		foto1.draw(g);
		foto2.draw(g);
	}
	
	public void nextSlide() {
		nextApplication = new Slide6(w,h);
	}
	
	public void previousSlide() {
		nextApplication = new Slide4(w,h);
	}

}
