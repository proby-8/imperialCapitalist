package ImperialCapitalist;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import objects.ResourceManager;
import objects.ShopObjects;

public class SaveScreen {

	private JFrame SaveFrame;
	private boolean delete;

	ResourceManager resources = new ResourceManager();
	ShopObjects Shopobj = new ShopObjects();

	public SaveScreen(int i, MusicPlayer Musicobj) {

		// 2 = from settings 1 = load game, 0 = new game
		boolean[] fileExist = new boolean[3];

		// Frame for save menu
		SaveFrame = new JFrame();
		SaveFrame.setVisible(true);
		SaveFrame.setBounds(100, 100, 816, 600);
		SaveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SaveFrame.getContentPane().setLayout(null);
		SaveFrame.setLocationRelativeTo(null);

		// space background
		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setBounds(0, 0, 800, 600);
		BackgroundLBL.setOpaque(true);
		MakePictureLBL(BackgroundLBL, 8);
		SaveFrame.getContentPane().add(BackgroundLBL);

		JLabel TitleLBL = new JLabel("Imperial Capitalist");
		TitleLBL.setBounds(50, 25, 700, 150);
		TitleLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 75));
		TitleLBL.setForeground(Color.white);
		TitleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		BackgroundLBL.add(TitleLBL);

		JButton SaveBTN1 = new JButton();
		SaveBTN1.setBounds(320, 210, 250, 40);
		SaveBTN1.setHorizontalAlignment(SwingConstants.CENTER);
		SaveBTN1.setFocusable(false);
		SaveBTN1.setContentAreaFilled(false);
		SaveBTN1.setBorderPainted(false);
		SaveBTN1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		SaveBTN1.setForeground(Color.white);
		SaveBTN1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(SaveBTN1);

		JButton SavePicBTN1 = new JButton();
		SavePicBTN1.setBounds(260, 190, 80, 80);
		SavePicBTN1.setHorizontalAlignment(SwingConstants.CENTER);
		SavePicBTN1.setFocusable(false);
		SavePicBTN1.setBorderPainted(false);
		SavePicBTN1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SavePicBTN1.setIcon(resources.getScaledImage(21, 3, 3));
		BackgroundLBL.add(SavePicBTN1);

		JButton SaveBTN2 = new JButton();
		SaveBTN2.setBounds(320, 300, 250, 40);
		SaveBTN2.setHorizontalAlignment(SwingConstants.CENTER);
		SaveBTN2.setFocusable(false);
		SaveBTN2.setContentAreaFilled(false);
		SaveBTN2.setBorderPainted(false);
		SaveBTN2.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		SaveBTN2.setForeground(Color.white);
		SaveBTN2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(SaveBTN2);

		JButton SavePicBTN2 = new JButton();
		SavePicBTN2.setBounds(260, 280, 80, 80);
		SavePicBTN2.setHorizontalAlignment(SwingConstants.CENTER);
		SavePicBTN2.setFocusable(false);
		SavePicBTN2.setBorderPainted(false);
		SavePicBTN2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SavePicBTN2.setIcon(resources.getScaledImage(21, 3, 3));
		BackgroundLBL.add(SavePicBTN2);

		JButton SaveBTN3 = new JButton();
		SaveBTN3.setBounds(320, 390, 250, 40);
		SaveBTN3.setHorizontalAlignment(SwingConstants.CENTER);
		SaveBTN3.setFocusable(false);
		SaveBTN3.setContentAreaFilled(false);
		SaveBTN3.setBorderPainted(false);
		SaveBTN3.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		SaveBTN3.setForeground(Color.white);
		SaveBTN3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(SaveBTN3);

		JButton SavePicBTN3 = new JButton();
		SavePicBTN3.setBounds(260, 370, 80, 80);
		SavePicBTN3.setHorizontalAlignment(SwingConstants.CENTER);
		SavePicBTN3.setFocusable(false);
		SavePicBTN3.setBorderPainted(false);
		SavePicBTN3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SavePicBTN3.setIcon(resources.getScaledImage(21, 3, 3));
		BackgroundLBL.add(SavePicBTN3);

		SaveBTN1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (i != 2) {
					String file = "SaveFile1.txt";
					if (!delete) {
						setUpSave(file, Musicobj);
						SaveFrame.setVisible(false);
						new GameScreen(file, Musicobj, Shopobj);
					}
					else {
						new FileWriter(Shopobj, Musicobj, file, true);
						SaveBTN1.setForeground(Color.white);
						SaveBTN2.setForeground(Color.white);
						SaveBTN3.setForeground(Color.white);
						SavePicBTN1.setIcon(resources.getScaledImage(21, 3, 3));
						SaveBTN1.setText("New Load 1");
						delete = false;
					}
				}
				else {
					SaveFrame.setVisible(false);
					new SettingsScreen(0, Musicobj);
				}
			}
		});

		SavePicBTN1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String file = "SaveFile1.txt";
				if (!delete) {
					setUpSave(file, Musicobj);
					SaveFrame.setVisible(false);
					new GameScreen(file, Musicobj, Shopobj);
				}
				else {
					new FileWriter(Shopobj, Musicobj, file, true);
					SaveBTN1.setForeground(Color.white);
					SaveBTN2.setForeground(Color.white);
					SaveBTN3.setForeground(Color.white);
					SavePicBTN1.setIcon(resources.getScaledImage(21, 3, 3));
					SaveBTN1.setText("New Load 1");
					delete = false;
				}
			}
		});

		SaveBTN2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String file = "SaveFile2.txt";
				if (!delete) {
					setUpSave(file, Musicobj);
					SaveFrame.setVisible(false);
					new GameScreen(file, Musicobj, Shopobj);
				}
				else {
					new FileWriter(Shopobj, Musicobj, file, true);
					SaveBTN1.setForeground(Color.white);
					SaveBTN2.setForeground(Color.white);
					SaveBTN3.setForeground(Color.white);
					SavePicBTN2.setIcon(resources.getScaledImage(21, 3, 3));
					SaveBTN2.setText("New Load 2");
					delete = false;
				}
			}
		});

		SavePicBTN2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String file = "SaveFile2.txt";
				if (!delete) {
					setUpSave(file, Musicobj);
					SaveFrame.setVisible(false);
					new GameScreen(file, Musicobj, Shopobj);
				}
				else {
					new FileWriter(Shopobj, Musicobj, file, true);
					SaveBTN1.setForeground(Color.white);
					SaveBTN2.setForeground(Color.white);
					SaveBTN3.setForeground(Color.white);
					SavePicBTN2.setIcon(resources.getScaledImage(21, 3, 3));
					SaveBTN2.setText("New Load 2");
					delete = false;
				}
			}
		});

		SaveBTN3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String file = "SaveFile3.txt";
				if (!delete) {
					setUpSave(file, Musicobj);
					SaveFrame.setVisible(false);
					new GameScreen(file, Musicobj, Shopobj);
				}
				else {
					new FileWriter(Shopobj, Musicobj, file, true);
					SaveBTN1.setForeground(Color.white);
					SaveBTN2.setForeground(Color.white);
					SaveBTN3.setForeground(Color.white);
					SavePicBTN3.setIcon(resources.getScaledImage(21, 3, 3));
					SaveBTN3.setText("New Load 3");
					delete = false;
				}
			}
		});

		SavePicBTN3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String file = "SaveFile3.txt";
				if (!delete) {
					setUpSave(file, Musicobj);
					SaveFrame.setVisible(false);
					new GameScreen(file, Musicobj, Shopobj);
				}
				else {
					new FileWriter(Shopobj, Musicobj, file, true);
					SaveBTN1.setForeground(Color.white);
					SaveBTN2.setForeground(Color.white);
					SaveBTN3.setForeground(Color.white);
					SavePicBTN3.setIcon(resources.getScaledImage(21, 3, 3));
					SaveBTN3.setText("New Load 3");
					delete = false;
				}
			}
		});

		fileExist[0] = fileReader("SaveFile1.txt", SavePicBTN1);
		fileExist[1] = fileReader("SaveFile2.txt", SavePicBTN2);
		fileExist[2] = fileReader("SaveFile3.txt", SavePicBTN3);


		String SaveBTNtext1;
		String SaveBTNtext2;
		String SaveBTNtext3;

		if (fileExist[0]) {
			SaveBTNtext1 = "Load 1";
		}
		else {
			SaveBTNtext1 = "New Load 1";
		}

		if (fileExist[1]) {
			SaveBTNtext2 = "Load 2";
		}
		else {
			SaveBTNtext2 = "New Load 2";
		}

		if (fileExist[2]) {
			SaveBTNtext3 = "Load 3";
		}
		else {
			SaveBTNtext3 = "New Load 3";
		}

		SaveBTN1.setText(SaveBTNtext1);
		SaveBTN2.setText(SaveBTNtext2);
		SaveBTN3.setText(SaveBTNtext3);

		if (i == 0 && SaveBTNtext1 == "New Load 1") {
			SaveBTN1.setForeground(Color.CYAN);
		}
		if (i == 0 && SaveBTNtext2 == "New Load 2") {
			SaveBTN2.setForeground(Color.CYAN);
		}
		if (i == 0 && SaveBTNtext3 == "New Load 3") {
			SaveBTN3.setForeground(Color.CYAN);
		}

		if (i == 1 && SaveBTNtext1 == "Load 1") {
			SaveBTN1.setForeground(Color.CYAN);
		}
		if (i == 1 && SaveBTNtext2 == "Load 2") {
			SaveBTN2.setForeground(Color.CYAN);
		}
		if (i == 1 && SaveBTNtext3 == "Load 3") {
			SaveBTN3.setForeground(Color.CYAN);
		}

		JButton DeleteBTN = new JButton("Delete Save");
		DeleteBTN.setBounds(275, 460, 250, 40);
		DeleteBTN.setHorizontalAlignment(SwingConstants.CENTER);
		DeleteBTN.setFocusable(false);
		DeleteBTN.setContentAreaFilled(false);
		DeleteBTN.setBorderPainted(false);
		DeleteBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		DeleteBTN.setForeground(Color.white);
		DeleteBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(DeleteBTN);

		DeleteBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!delete) {
					if (SaveBTNtext1 == "Load 1") {
						SaveBTN1.setForeground(Color.red);
					}
					if (SaveBTNtext2 == "Load 2") {
						SaveBTN2.setForeground(Color.red);
					}
					if (SaveBTNtext3 == "Load 3") {
						SaveBTN3.setForeground(Color.red);
					}
					delete = true;
				}
				else if (delete) {
					SaveBTN1.setForeground(Color.white);
					SaveBTN2.setForeground(Color.white);
					SaveBTN3.setForeground(Color.white);
					delete = false;
				}
			}
		});

		JButton BackBTN = new JButton("Back");
		BackBTN.setBounds(300, 510, 200, 40);
		BackBTN.setHorizontalAlignment(SwingConstants.CENTER);
		BackBTN.setFocusable(false);
		BackBTN.setContentAreaFilled(false);
		BackBTN.setBorderPainted(false);
		BackBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		BackBTN.setForeground(Color.white);
		BackBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(BackBTN);

		BackBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SaveFrame.setVisible(false);
				delete = false;
				new MainMenuScreen(Musicobj);
			}
		});
	}

	public void MakePictureLBL(JLabel Label, int i) {
		// Add a picture to a label easily
		Label.setFocusable(false);
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		Label.setVerticalAlignment(SwingConstants.CENTER);
		Label.setIcon(resources.getImage(i));
	}

	/*
	 * Reads the first two lines 
	 * first line is array length, do not need
	 * second line is whether or not the file exists
	 */
	public boolean fileReader(String filename, JButton SavePicBTN) {

		BufferedReader filein;
		try {
			filein = new BufferedReader(new FileReader(filename));
			filein.readLine();
			int exist = Integer.parseInt(filein.readLine());
			if (exist == (1)) {
				// set profile picture
				SavePicBTN.setIcon(resources.getScaledImage(Integer.parseInt(filein.readLine()), 3, 3));
				filein.close();
				return true;
			}
			else {
				// leave blank guy pfp
				filein.close();
				return false;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if something goes wrong with reading the file, return file probably does not exist
		return false;
	}

	/*
	 * First index is array length, do not need
	 * Second index is whether file exists, also don't need
	 */
	public void setUpSave(String filename, MusicPlayer Musicobj) {
		BufferedReader filein;
		try {
			filein = new BufferedReader(new FileReader(filename));
			int numValues = Integer.parseInt(filein.readLine());
			filein.readLine(); // get rid of exist line
			int[] info = new int[numValues];
			for (int i = 0; i < numValues-1; i++) {
				info[i] = Integer.parseInt(filein.readLine());
			}
			filein.close();

			Shopobj.setProfileIndex(info[0]);
			try {
				Musicobj.startClip(info[1]);
			} catch (UnsupportedAudioFileException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (info[2] == 0) {
				Musicobj.stopClip();
				Musicobj.setPlaying(false);
			}
			Musicobj.clipVolume(info[3]);
			Shopobj.setTotalMoney(info[4]);

			// number bought
			for (int i =0; i < 8; i++) {
				Shopobj.setNumberBought(info[i+5], i);
			}

			// upgrade bought
			for (int i =0; i < 8; i++) {
				if (info[i+13] == 1) {
					Shopobj.setUpgradeBought(i);
				}
			}

			// manager bought
			for (int i =0; i < 8; i++) {
				if (info[i+21] == 1) {
					Shopobj.setManager(i);
				}
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
