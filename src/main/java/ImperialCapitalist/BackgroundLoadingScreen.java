package ImperialCapitalist;

import javax.swing.JFrame;
import javax.swing.JLabel;

import objects.ResourceManager;

public class BackgroundLoadingScreen {

	// get resources for images
	ResourceManager resources = new ResourceManager();

	// frame for background
	private JFrame BackgroundFrame;

	// creates the background frame and adds a JLabel with space background
	public BackgroundLoadingScreen() {
		
		// background frame
		BackgroundFrame = new JFrame();
		BackgroundFrame.setVisible(true);
		BackgroundFrame.setBounds(100, 100, 816, 600);
		BackgroundFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		BackgroundFrame.getContentPane().setLayout(null);
		BackgroundFrame.setLocationRelativeTo(null);

		//background label
		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setBounds(0, 0, 800, 600);
		BackgroundLBL.setOpaque(true);
		BackgroundLBL.setIcon(resources.getImage(8));
		BackgroundFrame.getContentPane().add(BackgroundLBL);
	}
}


