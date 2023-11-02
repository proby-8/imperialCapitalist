import java.awt.EventQueue;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class InitialCode {

	// Declare MusicPlayer now and pass it through to all necessary methods
	// Easier to declare now as MainMenu has to accept it as a parameter anyways from GameScreen
	MusicPlayer Musicobj = new MusicPlayer();

	/*
	 * Launch the application
	 */
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new InitialCode();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create a background loading screen that stays open until program ends,
	 * shouldn't use too much memory as it is just one JLabel sitting in the back.
	 * Necessary so that the user doesn't have to look at nothing in between frames.
	 * 
	 * Initialize the main menu
	 */
	public InitialCode() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new BackgroundLoadingScreen();
		new MainMenuScreen(Musicobj);
	}
}