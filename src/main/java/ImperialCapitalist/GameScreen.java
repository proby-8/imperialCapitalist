package ImperialCapitalist;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import objects.ManagerObject;
import objects.ResourceManager;
import objects.ShopObjects;

public class GameScreen {

	private JFrame GameFrame = new JFrame();

	DecimalFormat df = new DecimalFormat("0.00");

	ResourceManager resources = new ResourceManager();
	ManagerObject Managerobj = new ManagerObject();

	JPanel MainPanel = new JPanel();
	JLabel MoneyDisplay = new JLabel();

	private static boolean flashing_red = false;

	private JButton[] ShopBuy = new JButton[8];
	private JLabel[] ShopRevenue = new JLabel[8];
	private JProgressBar[] ShopProgress = new JProgressBar[8];

	private int ShopIndex = 0;

	private boolean[] Debounce = new boolean[8];
	public boolean[] ShopThreadRunning = new boolean[8];

	public Thread[] ShopThreadArray = new Thread[8];

	public GameScreen(String filename, MusicPlayer Musicobj, ShopObjects Shopobj) {

		// create the main frame for the game
		GameFrame.setBounds(300, 100, 816, 600);
		GameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GameFrame.getContentPane().setLayout(null);
		GameFrame.setLocationRelativeTo(null);
		GameFrame.setVisible(true);

		// Shopobj is going to have all the save stuff by now

		for (int i = 0; i < 8; i++) {
			MakeShopPanel(MainPanel, MoneyDisplay, i, Shopobj);
		}
		checkUnlocked(Shopobj);

		// panel for radio buttons on choosing profile pic
		JFrame ProfileChooseFrame = new JFrame();
		ProfileChooseFrame.setBounds(150, 100, 316, 400);
		ProfileChooseFrame.setLocationRelativeTo(null);
		ProfileChooseFrame.setVisible(false);
		ProfileChooseFrame.setUndecorated(true);

		// panel for radio buttons on choosing profile pic
		JPanel ProfileChoosePanel = new JPanel();
		ProfileChoosePanel.setBounds(250, 100, 316, 400);
		ProfileChoosePanel.setLayout(new GridLayout(0, 1, 0, 10));
		ProfileChoosePanel.setBackground(Color.black);
		ProfileChooseFrame.getContentPane().add(ProfileChoosePanel);
		ProfileChoosePanel.setVisible(false);

		// Panel for upgrades, managers, investors, and overview
		JPanel SideBarPanel = new JPanel();
		SideBarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		SideBarPanel.setBackground(Color.BLACK);
		SideBarPanel.setBounds(0, 175, 175, 386);
		GameFrame.getContentPane().add(SideBarPanel);
		SideBarPanel.setLayout(new GridLayout(0, 1, 0, 0));

		// button for upgrades
		JButton UpgradeBTN = new JButton("Upgrades");
		UpgradeBTN.setFocusable(false);
		UpgradeBTN.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		SideBarPanel.add(UpgradeBTN);

		// button for managers
		JButton ManagerBTN = new JButton("Managers");
		ManagerBTN.setFocusable(false);
		ManagerBTN.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		SideBarPanel.add(ManagerBTN);

		// button to view unlocks
		JButton UnlockBTN = new JButton("Unlocks");
		UnlockBTN.setFocusable(false);
		UnlockBTN.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		SideBarPanel.add(UnlockBTN);

		// button for overview 
		JButton OverviewBTN = new JButton("Overview");
		OverviewBTN.setFocusable(false);
		OverviewBTN.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		SideBarPanel.add(OverviewBTN);

		// button for investors
		JButton SettingBTN = new JButton("Settings");
		SettingBTN.setFocusable(false);
		SettingBTN.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		SideBarPanel.add(SettingBTN);

		// button to return to main menu
		JButton MainMenuBTN = new JButton("Main Menu");
		MainMenuBTN.setFocusable(false);
		MainMenuBTN.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		SideBarPanel.add(MainMenuBTN);

		// Panel for ProfileBTN
		JPanel ProfilePanel = new JPanel();
		ProfilePanel.setBackground(Color.DARK_GRAY);
		ProfilePanel.setBounds(0, 0, 175, 175);
		GameFrame.getContentPane().add(ProfilePanel);
		ProfilePanel.setLayout(new GridLayout(0, 1, 0, 0));

		// button for profile
		JButton ProfileBTN = new JButton("");
		ProfileBTN.setFocusable(false);
		ProfileBTN.setContentAreaFilled(false);
		ProfileBTN.setIcon(resources.getImage(Shopobj.getProfileIndex()));
		ProfilePanel.add(ProfileBTN);

		// panel to display the label for total money
		JPanel MoneyPanel = new JPanel();
		MoneyPanel.setBackground(Color.black);
		MoneyPanel.setBounds(175, 0, 626, 100);
		MoneyPanel.setLayout(null);
		GameFrame.getContentPane().add(MoneyPanel);

		// display money
		MoneyDisplay.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		MoneyDisplay.setText(Shopobj.getTotalMoneyString());
		MoneyDisplay.setBounds(60, 11, 414, 78);
		MoneyDisplay.setForeground(Color.white);
		MoneyPanel.add(MoneyDisplay);

		// button to change how many things are bought at once
		JButton BuyOptionBTN = new JButton("Buy x" + Shopobj.getBuyOption());
		BuyOptionBTN.setFocusable(false);
		BuyOptionBTN.setBounds(527, 11, 89, 23);
		MoneyPanel.add(BuyOptionBTN);

		// picture of the credit symbol, same as $ but star wars
		JLabel CreditSymbol = new JLabel();
		CreditSymbol.setBounds(10, 18, 47, 66);
		CreditSymbol.setIcon(resources.getImage(9));
		MoneyPanel.add(CreditSymbol);

		// create background for money panel
		JLabel MoneyLabelBackground = new JLabel();
		MoneyLabelBackground.setIcon(resources.getImage(8));
		MoneyLabelBackground.setBounds(0, 0, 626, 100);
		MoneyPanel.add(MoneyLabelBackground);

		// main panel where the droids will be
		MainPanel.setBounds(175, 100, 625, 461);
		MainPanel.setBorder(new LineBorder(new Color(120, 81, 169), 4));
		GameFrame.getContentPane().add(MainPanel);
		MainPanel.setLayout(new GridLayout(4, 2, 0, 0));

		runWhenMoneyUpdate(Shopobj);

		Thread ShopBuyButtonThread = new Thread() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(10);
						if (flashing_red) {
							flashing_red = false;
							ShopBuy[ShopIndex].setBackground(Color.red); //index
							Thread.sleep(100);
							ShopBuy[ShopIndex].setBackground(Color.lightGray); //index once again
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		ShopBuyButtonThread.start();


		GameFrame.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				new FileWriter(Shopobj, Musicobj, filename, false);
				GameFrame.dispose();
				new MainMenuScreen(Musicobj);	
			}
		} );

		// button listeners
		MainMenuBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FileWriter(Shopobj, Musicobj, filename, false);
				GameFrame.dispose();
				new MainMenuScreen(Musicobj);			
			}
		});

		UpgradeBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UpgradeScreen(Shopobj, ShopRevenue, MoneyDisplay);
				runWhenMoneyUpdate(Shopobj);
				GameFrame.repaint();
			}

		});

		SettingBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SettingsScreen(1, Musicobj);
			}
		});

		OverviewBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new OverviewScrollScreen(Shopobj);
			}
		});

		UnlockBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UnlockScreen(Shopobj);
			}
		});

		ProfileBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// panel for radio buttons on choosing profile pic
				ProfileChooseFrame.setVisible(true);
				ProfileChoosePanel.setVisible(true);

				JLabel textLBL = new JLabel();
				textLBL.setText("Select one of the following:");
				textLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
				textLBL.setForeground(Color.white);
				ProfileChoosePanel.add(textLBL);

				JRadioButton[] options = new JRadioButton[9];
				options[0] = new JRadioButton("Darth Vader");
				options[4] = new JRadioButton("Poe Dameron");
				options[1] = new JRadioButton("Obi-Wan Kenobi");
				options[2] = new JRadioButton("Darth Maul");
				options[5] = new JRadioButton("Darth Sidious");
				options[3] = new JRadioButton("Mace Windu");
				options[6] = new JRadioButton("Anakin Skywalker");
				options[7] = new JRadioButton("Boss Nass");
				options[8] = new JRadioButton("Ahsoka Tano");

				ButtonGroup group = new ButtonGroup();
				for (int i = 0; i < options.length; i++) {
					// math for y aligns i with the desired image
					int y = i + 12;
					// add each radio button to the panel
					ProfileChoosePanel.add(options[i]);
					options[i].setFocusable(false);
					options[i].setContentAreaFilled(false);
					options[i].setForeground(Color.white);

					// add action listener for each button, sets the profile picture 
					options[i].addActionListener(new ActionListener() { 
						public void actionPerformed(ActionEvent e) {
							Shopobj.setProfileIndex(y);
							ProfileBTN.setIcon(resources.getImage(y));
						}
					});
				}
				// these have to be added individually or else multiple can be selected at the same time
				group.add(options[0]);
				group.add(options[1]);
				group.add(options[2]);
				group.add(options[3]);
				group.add(options[4]);
				group.add(options[5]);		
				group.add(options[6]);
				group.add(options[7]);
				group.add(options[8]);

				// declare close button
				JButton CloseBTN = new JButton();
				CloseBTN.setBounds(200, 510, 100, 30);
				CloseBTN.setText("Close");
				CloseBTN.setFocusable(false);;
				CloseBTN.setBackground(Color.pink);
				CloseBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				ProfileChoosePanel.add(CloseBTN);

				GameFrame.repaint();

				// close the panel when close is clicked
				CloseBTN.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ProfileChooseFrame.setVisible(false);
					}
				});

			}
		});

		ManagerBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ManagerScreen(Managerobj, Shopobj, MoneyDisplay);
				runWhenMoneyUpdate(Shopobj);
			}
		});

		BuyOptionBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// cycle 1, 10, 100
				Shopobj.setBuyOption(Shopobj.getBuyOption() * 10);
				if (Shopobj.getBuyOption() == 10000) { Shopobj.setBuyOption(1); }
				if (Shopobj.getBuyOption() == 1000) {
					BuyOptionBTN.setText("Buy Max");
				}
				else {
					BuyOptionBTN.setText("Buy x" + Shopobj.getBuyOption());
				}

				for (int i = 0; i < 8; i++) {
					changeBuyOption(Shopobj, ShopBuy);
				}

				runWhenMoneyUpdate(Shopobj);
			}
		});

	}


	public void MakeShopPanel(JPanel MainPanel, JLabel MoneyDisplay, int i, ShopObjects Shopobj) {

		int index = i;

		ShopBuy[i] = new JButton();
		ShopRevenue[i] = new JLabel();
		ShopProgress[i] = new JProgressBar();
		JPanel ShopPanel = new JPanel();
		JButton ShopIcon = new JButton();
		JLabel ShopNumberOfPurchases = new JLabel();

		ShopPanel.setLayout(null);
		ShopPanel.setOpaque(false);
		ShopPanel.setBackground(new Color(0,0,0,0));
		ShopPanel.setFocusable(false);
		ShopPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setIcon(resources.getImage(8));
		BackgroundLBL.setBounds(0,0,500,500);

		ShopIcon.setIcon(resources.getScaledImage(i, 3, 3));
		ShopIcon.setBounds(0, 0, 80, 100);
		ShopIcon.setContentAreaFilled(false);
		ShopIcon.setFocusable(false);
		ShopIcon.setBorderPainted(false);
		ShopIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		ShopNumberOfPurchases.setHorizontalAlignment(SwingConstants.CENTER); 
		ShopNumberOfPurchases.setForeground(Color.white);
		ShopNumberOfPurchases.setBounds(10, 95, 60, 20);
		ShopNumberOfPurchases.setText(Integer.toString(Shopobj.getNumberBought(i)));

		ShopProgress[i].setBounds(80, 25, 222, 32);
		ShopProgress[i].setMaximum(100);
		ShopProgress[i].setMinimum(0);
		ShopProgress[i].setBackground(Color.white);
		ShopProgress[i].setForeground(new Color(0, 0, 255, 100));

		ShopRevenue[i].setBounds(80, 25, 222, 32);
		if (Shopobj.getNumberBought(i) > 0) {
			ShopRevenue[i].setText(Shopobj.getRevenueString(i));
		}
		else {
			ShopRevenue[i].setText("LOCKED");
			ShopProgress[i].setBackground(Color.pink);
		}
		ShopRevenue[i].setHorizontalAlignment(SwingConstants.CENTER);

		ShopBuy[i].setMargin(new Insets(2, 5, 2, 2));
		ShopBuy[i].setIconTextGap(0);
		ShopBuy[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		ShopBuy[i].setHorizontalAlignment(SwingConstants.LEFT);
		ShopBuy[i].setBounds(80, 68, 222, 23);
		ShopBuy[i].setFocusable(false);
		ShopBuy[i].setText("x " + Shopobj.getBuyOption() + "          Cost: " + Shopobj.getCostString((Shopobj.getBuyOption()), i));

		ShopPanel.add(ShopRevenue[i]);
		ShopPanel.add(ShopProgress[i]);
		ShopPanel.add(ShopNumberOfPurchases);
		ShopPanel.add(ShopBuy[i]);
		ShopPanel.add(ShopIcon);
		ShopPanel.add(BackgroundLBL);

		MainPanel.add(ShopPanel);

		ShopBuy[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Shopobj.getBuyOption() != 1000) {
					if (Shopobj.getTotalMoney() >= Shopobj.getCost(Shopobj.getBuyOption(), index) && Shopobj.getTotalMoney() != 0) {

						if (Shopobj.getNumberBought(i) == 0) {
							new OverviewScreen(Shopobj, i);
							ShopProgress[index].setBackground(Color.white);
							GameFrame.repaint();
						}

						// subtract cost from total money, display new money, display new price, display droids owned
						Shopobj.setTotalMoney(Shopobj.purchase(Shopobj.getBuyOption(), Shopobj.getTotalMoney(), index));
						runWhenMoneyUpdate(Shopobj);
						MoneyDisplay.setText(Shopobj.getTotalMoneyString());					
						ShopBuy[i].setText("x " + Shopobj.getBuyOption() + "       Cost: " + Shopobj.getCostString(Shopobj.getBuyOption(), index));
						ShopNumberOfPurchases.setText(Integer.toString(Shopobj.getNumberBought(index)));	
						ShopRevenue[i].setText(Shopobj.getRevenueString(index));

						// check for unlocked things
						checkUnlocked(Shopobj);
					}
					else {
						flashing_red=true;
						ShopIndex = index;
					}
				}
				else {
					if (Shopobj.getTotalMoney() >= Shopobj.getMaxBuyCost(index) && Shopobj.getTotalMoney() != 0 && Shopobj.getMaxBuy(index) != 0) {
						if (Shopobj.getNumberBought(i) == 0) {
							//Overview(Shopobj, i);
						}

						// subtract cost from total money, display new money, display new price, display droids owned
						Shopobj.setTotalMoney(Shopobj.purchaseMax(Shopobj.getMaxBuy(index), index, Shopobj.getMaxBuyCost(index)));
						runWhenMoneyUpdate(Shopobj);
						MoneyDisplay.setText(Shopobj.getTotalMoneyString());					
						ShopNumberOfPurchases.setText(Integer.toString(Shopobj.getNumberBought(index)));	
						ShopRevenue[i].setText(Shopobj.getRevenueString(index));

						checkUnlocked(Shopobj);
					}
				}
			}
		});



		ShopIcon.addActionListener(new ActionListener() { // progress bar
			public void actionPerformed(ActionEvent e) {
				if (Shopobj.getNumberBought(index) > 0 && !Debounce[index]) {
					Debounce[index] = true;
					Shopobj.setRunning(index,  true);
					ThreadGalore(i, Shopobj);
				}
			}
		});
	}

	public void runWhenMoneyUpdate(ShopObjects Shopobj) {
		MoneyDisplay.setText(Shopobj.getTotalMoneyString());
		if (Shopobj.getBuyOption() != 1000) {
			for (int i = 0; i<=7; i++) {
				if (Shopobj.getTotalMoney() >= Shopobj.getCost((Shopobj.getBuyOption()), i)) {
					ShopBuy[i].setBackground(Color.green);
				}
				else {
					ShopBuy[i].setBackground(Color.lightGray);
				}
			}
		}
		else {
			for (int i = 0; i<=7; i++) {
				changeBuyOption(Shopobj, ShopBuy);
				if (Shopobj.getTotalMoney() >= Shopobj.getMaxBuyCost(i) && Shopobj.getMaxBuy(i) != 0) {
					if (Shopobj.getMaxBuy(i) != 0) {
						ShopBuy[i].setText("x " + Shopobj.getMaxBuy(i) + "      Cost: " + Shopobj.getMaxBuyCostString(i));
					}
					else {
						ShopBuy[i].setText("x " + 1 + "           Cost: " + Shopobj.getCostString(1, i));
					}
					ShopBuy[i].setBackground(Color.green);
				}
				else {
					ShopBuy[i].setBackground(Color.lightGray);
				}
			}
		}
	}

	private void changeBuyOption(ShopObjects Shopobj, JButton ShopBuy[]) { 
		// loop through all droids
		for (int i = 0; i < 8; i++) {
			if (Shopobj.getBuyOption() == 1) {
				ShopBuy[i].setText("x " + Shopobj.getBuyOption() + "           Cost: " + Shopobj.getCostString(Shopobj.getBuyOption(), i));
				// buy one thing at once
			}
			else if (Shopobj.getBuyOption() == 10) {
				ShopBuy[i].setText("x " + Shopobj.getBuyOption() + "         Cost: " + Shopobj.getCostString(Shopobj.getBuyOption(), i));
				// buy ten things at once
			}
			else if (Shopobj.getBuyOption() == 100) {
				ShopBuy[i].setText("x " + Shopobj.getBuyOption() + "       Cost: " + Shopobj.getCostString(Shopobj.getBuyOption(), i));
				// buy a hundred things at once
			}
			else if (Shopobj.getBuyOption() == 1000) {
				if (Shopobj.getMaxBuy(i) != 0) {
					ShopBuy[i].setText("x " + Shopobj.getMaxBuy(i) + "      Cost: " + Shopobj.getMaxBuyCostString(i));
				}
				else {
					ShopBuy[i].setText("x " + 1 + "           Cost: " + Shopobj.getCostString(1, i));
				}
			}
		}
	}

	private void checkUnlocked(ShopObjects Shopobj) {
		// 25 of individ = speed x 2
		for (int i = 0; i<=7; i++) {
			Shopobj.changeCycleSpeed(i);
		}
	}

	private void ThreadGalore(int index, ShopObjects Shopobj) {
		ShopThreadArray[index] = new Thread() {
			public void run() {
				try {
					while (true) {
						ShopRevenue[index].repaint();
						Thread.sleep(10);
						if (Shopobj.getRunning(index)) {
							int i = 0;
							while (true) {
								Thread.sleep(10);
								double temp = ((i++ / Shopobj.getCycleTime(index) * 100));
								ShopProgress[index].setValue((int) temp);	
								GameFrame.repaint();
								if (ShopProgress[index].getValue() >= 100) {
									break;
								}
							}
							ShopProgress[index].setValue(0);
							ShopRevenue[index].repaint();
							Shopobj.setTotalMoney((Shopobj.getTotalMoney() + Shopobj.getRevenue(index)));
							runWhenMoneyUpdate(Shopobj);
							MoneyDisplay.setText(Shopobj.getTotalMoneyString());
							Debounce[index] = false;
							Shopobj.setRunning(index, false);
						}

					}
				}

				catch (InterruptedException e) {e.printStackTrace();}
				Debounce[index] = false;
			}

		};

		if (!ShopThreadRunning[index]) {
			ShopThreadRunning[index] = true;
			ShopThreadArray[index].start();
		}

	}


}
