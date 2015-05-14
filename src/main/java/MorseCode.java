import morse.MorseKeyboardApplication;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;


public class MorseCode extends EtyllicaFrame {

	private static final long serialVersionUID = 7739713774644387495L;

	public MorseCode() {
		super(100,100);
	}

	// Main program
	public static void main(String[] args) {
		MorseCode app = new MorseCode();
		app.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("../");
		return new MorseKeyboardApplication(w, h);
	}

}