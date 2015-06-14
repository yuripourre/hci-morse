package presentation;

import br.com.etyllica.core.graphics.Graphic;

public class Slide3 extends Presentation {
	
	public Slide3(int w, int h) {
		super(w, h);
	}
	
	public void draw(Graphic g) {
		super.draw(g);
		
		g.setFontSize(38);
		g.drawStringShadowX(40, "Concepção");
		
		g.setFontSize(32);
		g.drawStringShadowX(100, "Código Morse");
		g.drawStringShadowX(250, "- Necessidade de um único botão");
		g.drawStringShadowX(350, "- Feito para digitação");
		g.drawStringShadowX(450, "- Permite expansão/adaptação do alfabeto");
		
	}
	
	public void nextSlide() {
		nextApplication = new Slide4(w,h);
	}
	
	public void previousSlide() {
		nextApplication = new Slide2(w,h);
	}

}
