package presentation;

import morse.MorseSerialApplication;
import br.com.etyllica.core.graphics.Graphic;

public class Slide6 extends Presentation {
	
	public Slide6(int w, int h) {
		super(w, h);
	}
	
	public void draw(Graphic g) {
		super.draw(g);
		
		g.setFontSize(38);
		g.drawStringShadowX(40, "Conclusão");
		
		g.setFontSize(32);
		g.drawStringShadowX(100, "Problemas encontrados:");
		g.drawStringShadowX(250, "Protótipo não funcionou como o esperado");
		g.drawStringShadowX(350, "Resistores com valores muito altos");
		g.drawStringShadowX(450, "Solução via software");
		
	}
	
	public void nextSlide() {
		nextApplication = new MorseSerialApplication(w,h);
	}
	
	public void previousSlide() {
		nextApplication = new Slide5(w,h);
	}

}
