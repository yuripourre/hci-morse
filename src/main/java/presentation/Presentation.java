package presentation;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;
import morse.MorseSerialApplication;

public abstract class Presentation extends Application {

	private ImageLayer background;
	
	public Presentation(int w, int h) {
		super(w, h);
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		if(event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)) {
			nextSlide();
		}
		
		if(event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)) {
			previousSlide();
		}
			
		return super.updateKeyboard(event);
	}
	
	protected void pressDot() {
		nextSlide();
	}
	
	protected void pressDash() {
		previousSlide();
	}
	
	public abstract void nextSlide();
	public abstract void previousSlide();
	
	public void load() {		
		background = new ImageLayer("wallpaper.jpg");  
	}
	
	public void draw(Graphic g) {
		background.draw(g);
	}

}
