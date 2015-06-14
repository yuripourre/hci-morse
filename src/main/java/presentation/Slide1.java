package presentation;

import br.com.etyllica.core.graphics.Graphic;

public class Slide1 extends Presentation {
	
	public Slide1(int w, int h) {
		super(w, h);
	}
	
	public void draw(Graphic g) {
		super.draw(g);
		
		g.setFontSize(38);
		g.drawStringShadowX(40, "IHC - Capitulo 11");
		
		g.setFontSize(32);
		g.drawStringShadowX(100, "Concepção, construção e Prototipação");
		
		g.drawStringShadowX(350, "Por Yuri Vitor Pourre");
		
		g.drawStringShadowX(540, "Professora Simone Bacelar");
	}
	
	public void nextSlide() {
		nextApplication = new Slide2(w,h);
	}
	
	public void previousSlide() {
		
	}

}
