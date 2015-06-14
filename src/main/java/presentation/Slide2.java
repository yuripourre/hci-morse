package presentation;

import br.com.etyllica.core.graphics.Graphic;

public class Slide2 extends Presentation {
	
	public Slide2(int w, int h) {
		super(w, h);
	}
	
	public void draw(Graphic g) {
		super.draw(g);
		
		g.setFontSize(38);
		g.drawStringShadowX(40, "Concepção");
		
		g.setFontSize(32);
		g.drawStringShadowX(100, "Necessidade do Cliente/Usuário");
		g.drawStringShadowX(250, "- Evitar Mouse/Teclado");
		g.drawStringShadowX(350, "- Evitar movimento dos braços");
		g.drawStringShadowX(450, "- Permitir que usuário digite");
		
	}
	
	public void nextSlide() {
		nextApplication = new Slide3(w,h);
	}
	
	public void previousSlide() {
		nextApplication = new Slide1(w,h);
	}

}
