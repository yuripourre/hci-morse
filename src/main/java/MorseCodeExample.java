import presentation.Presentation;
import presentation.Slide1;
import morse.MorseKeyboardApplication;
import morse.MorseSerialApplication;
import morse.StoppedMorseSerialApplication;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;


public class MorseCodeExample extends EtyllicaFrame {

	private static final long serialVersionUID = 7739713774644387495L;

	/**
	 * sudo chgrp uucp /var/lock
	 * sudo chmod 775 /var/lock
	 */
	
	public MorseCodeExample() {
		super(1024,640);
	}

	// Main program
	public static void main(String[] args) {
		MorseCodeExample app = new MorseCodeExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("../");
		//return new Slide1(w, h);
		                       
		//return new MorseKeyboardApplication(w, h);		
		return new MorseSerialApplication(w, h);
		//return new Slide1(w, h);		
		//return new StoppedMorseSerialApplication(w, h);
	}

}