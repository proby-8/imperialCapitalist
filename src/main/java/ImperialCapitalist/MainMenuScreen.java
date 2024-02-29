package ImperialCapitalist;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import objects.ResourceManager;

public class MainMenuScreen {

	// Open resource manager to create images
	ResourceManager resources = new ResourceManager();

	// Declare the main menu screen frame
	private JFrame MainFrame;

	/*
	 * Creates the main menu screen, with the following components
	 * Title - JLabel
	 * New Game - JButton; opens LoadScreen
	 * Load Game - JButton; opens LoadScreen
	 * Settings - JButton; opens SettingsScreen
	 * Quit - JButton; System.exit();
	 */
	public MainMenuScreen(MusicPlayer Musicobj) {
		// stop the music if main menu is run from game screen
		try {
			Musicobj.stopClip();
		}
		catch (Exception e) {}

		// Frame for main menu
		MainFrame = new JFrame();
		MainFrame.setVisible(true);
		MainFrame.setBounds(100, 100, 816, 600);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.getContentPane().setLayout(null);
		MainFrame.setLocationRelativeTo(null);

		// space background
		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setBounds(0, 0, 800, 600);
		BackgroundLBL.setOpaque(true);
		BackgroundLBL.setIcon(resources.getImage(8));
		MainFrame.getContentPane().add(BackgroundLBL);

		// display title
		JLabel TitleLBL = new JLabel("Imperial Capitalist");
		TitleLBL.setBounds(50, 25, 700, 150);
		TitleLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 75));
		TitleLBL.setForeground(Color.white);
		TitleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		BackgroundLBL.add(TitleLBL);

		// button for opening new game
		JButton NewLoadBTN = new JButton("New Game");
		NewLoadBTN.setBounds(300, 250, 200, 40);
		NewLoadBTN.setHorizontalAlignment(SwingConstants.CENTER);
		NewLoadBTN.setFocusable(false);
		NewLoadBTN.setContentAreaFilled(false);
		NewLoadBTN.setBorderPainted(false);
		NewLoadBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		NewLoadBTN.setForeground(Color.white);
		NewLoadBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(NewLoadBTN);

		// button for opening old save 
		JButton LoadSaveBTN = new JButton("Load Game");
		LoadSaveBTN.setBounds(300, 300, 200, 40);
		LoadSaveBTN.setHorizontalAlignment(SwingConstants.CENTER);
		LoadSaveBTN.setFocusable(false);
		LoadSaveBTN.setContentAreaFilled(false);
		LoadSaveBTN.setBorderPainted(false);
		LoadSaveBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		LoadSaveBTN.setForeground(Color.white);
		LoadSaveBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(LoadSaveBTN);

		// button to open setting screen
		JButton SettingsBTN = new JButton("Settings");
		SettingsBTN.setBounds(300, 350, 200, 40);
		SettingsBTN.setHorizontalAlignment(SwingConstants.CENTER);
		SettingsBTN.setFocusable(false);
		SettingsBTN.setContentAreaFilled(false);
		SettingsBTN.setBorderPainted(false);
		SettingsBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		SettingsBTN.setForeground(Color.white);
		SettingsBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(SettingsBTN);

		// button to close program
		JButton QuitBTN = new JButton("Quit");
		QuitBTN.setBounds(300, 400, 200, 40);
		QuitBTN.setHorizontalAlignment(SwingConstants.CENTER);
		QuitBTN.setFocusable(false);
		QuitBTN.setContentAreaFilled(false);
		QuitBTN.setBorderPainted(false);
		QuitBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		QuitBTN.setForeground(Color.white);
		QuitBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(QuitBTN);

		// creates files if they don't exist (first launch)
		try {
			fileCreator("SaveFile1.txt");
			fileCreator("SaveFile2.txt");
			fileCreator("SaveFile3.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// open save screen in new load mode
		NewLoadBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.dispose();
				new SaveScreen(0, Musicobj);
			}
		});

		// open save screen in old load mode
		LoadSaveBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.dispose();
				new SaveScreen(1, Musicobj); // there are two file saves
			}
		});

		// open settings screen
		SettingsBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.dispose();
				new SaveScreen(2, Musicobj);
			}
		});

		// close the program
		QuitBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

	}

	/*
	 * First checks if the file exists, if it does this method does nothing.
	 * Creates the files and inputs default values for everything, e.g
	 * sets total money to 0, upgrades to no, and managers to no plus more.
	 */
	private void fileCreator(String filename) throws IOException {
		/* LAYOUT by lines (0 = false, 1 = true):
		 * Number of lines for array size
		 * if file has a save (true/false)
		 * profile picture index
		 * Musicobj.getSongIndex() (0,1,2)
		 * Musicobj.getPlaying() (true/false)
		 * Musicobj.getVolume() (0-100)
		 * total money
		 * Shopobj.getNumberBought(0)
		 * Shopobj.getNumberBought(1)
		 * 2
		 * 3
		 * 4
		 * 5
		 * 6
		 * 7
		 * Shopobj.getUpgradeBought(0) (true/false)
		 * 1
		 * 2
		 * 3
		 * 4
		 * 5
		 * 5
		 * 6
		 * 7
		 * Shopobj.getManager(0) (true/false)
		 * 1
		 * 2
		 * 3
		 * 4
		 * 5
		 * 6
		 * 7
		 */
		if (!Files.exists(Paths.get(filename))) { 
			File fout = new File(filename);
			FileOutputStream fos = new FileOutputStream(fout);
		 
			BufferedWriter fileout = new BufferedWriter(new OutputStreamWriter(fos));
			
			fileout.write("30");
			fileout.newLine();
			fileout.write("0");
			fileout.newLine();
			fileout.write("20");
			fileout.newLine();
			fileout.write("0");
			fileout.newLine();
			fileout.write("1");
			fileout.newLine();
			fileout.write("100");
			fileout.newLine();
			fileout.write("0");
			fileout.newLine();
			
			// first droid should start as 1
			fileout.write("1");
			fileout.newLine();
			// number bought
			for (int i = 1; i < 8; i++) {
				fileout.write("0");
				fileout.newLine();
			}

			// upgrade bought
			for (int i =0; i < 8; i++) {
				fileout.write("0");
				fileout.newLine();
			}
			
			// manager bought
			for (int i =0; i < 8; i++) {
				fileout.write("0");
				fileout.newLine();
			}
			
			fileout.close();		
		}
	}
}


