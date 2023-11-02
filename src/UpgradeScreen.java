import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import objects.ResourceManager;
import objects.ShopObjects;
import objects.UpgradeObjects;

public class UpgradeScreen {

	private JFrame UpgradeFrame;

	ResourceManager resources = new ResourceManager();
	UpgradeObjects Upgradeobj = new UpgradeObjects();	

	public UpgradeScreen(ShopObjects Shopobj, JLabel[] ShopRevenue, JLabel MoneyDisplay) {
		UpgradeFrame = new JFrame();
		UpgradeFrame.setBounds(100, 100, 516, 600);
		UpgradeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UpgradeFrame.setUndecorated(true);
		UpgradeFrame.getContentPane().setLayout(null);
		UpgradeFrame.setVisible(true);
		UpgradeFrame.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 480, 400);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(null);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JPanel cursorPanel = new JPanel();
		// cursor glitching with scroll panel, transparent panel fixes
		cursorPanel.setBounds(10,100,400,400);
		cursorPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cursorPanel.setBackground(new Color (0,0,0,0));

		JButton CloseBTN = new JButton();
		CloseBTN.setBounds(200, 510, 100, 30);
		CloseBTN.setText("Close");
		CloseBTN.setFocusable(false);;
		CloseBTN.setBackground(Color.pink);
		CloseBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel UpgradeLBL = new JLabel();
		UpgradeLBL.setBounds(100, 0, 300, 258);
		UpgradeLBL.setVerticalAlignment(SwingConstants.TOP);
		UpgradeLBL.setHorizontalAlignment(SwingConstants.CENTER);
		UpgradeLBL.setIcon(resources.getImage(11));

		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setBounds(0, 0, 800, 600);
		BackgroundLBL.setOpaque(true);
		BackgroundLBL.setIcon(resources.getImage(8));

		UpgradeFrame.add(cursorPanel);
		UpgradeFrame.add(UpgradeLBL);
		UpgradeFrame.add(scrollPane);
		UpgradeFrame.add(CloseBTN);
		UpgradeFrame.add(BackgroundLBL);

		JPanel UpgradePanel = new JPanel();

		JPanel[] AllUpgradesPanel = new JPanel[9];
		JLabel[] AllUpgradesText = new JLabel[9];
		JButton[] AllUpgradesBTN = new JButton[9];

		scrollPane.setViewportView(UpgradePanel);
		UpgradePanel.setLayout(new GridLayout(0, 1, 0, 10));
		UpgradePanel.setBackground(Color.black);

		for (int i = 0; i<9; i++) {
			makeUpgradePanels(AllUpgradesPanel, AllUpgradesBTN, AllUpgradesText, i, UpgradePanel, Shopobj, ShopRevenue, MoneyDisplay);
		}

		CloseBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpgradeFrame.dispose();
			}
		});
	}

	private void makeUpgradePanels(JPanel[] AllUpgradesPanel, JButton[] AllUpgradesBTN, JLabel[] AllUpgradesText, int i,
			JPanel UpgradePanel, ShopObjects Shopobj, JLabel[] ShopRevenue, JLabel MoneyDisplay) {
		AllUpgradesPanel[i] = new JPanel();
		AllUpgradesText[i] = new JLabel();
		AllUpgradesBTN[i] = new JButton();

		AllUpgradesPanel[i].setPreferredSize(new Dimension(480, 50));
		AllUpgradesPanel[i].setBackground(new Color (0,0,0,0));
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent
		AllUpgradesPanel[i].setBorder(BorderFactory.createLineBorder(Color.white));

		AllUpgradesText[i].setVerticalAlignment(SwingConstants.CENTER);
		if (i == 8) {
			AllUpgradesText[i].setText("Monopoly Upgrade: " + Upgradeobj.getName(i) + "; Price: " + Upgradeobj.getCostString(i));
		}
		else {
			AllUpgradesText[i].setText(Shopobj.getName(i) + " Upgrade - " + Upgradeobj.getName(i) + "; Price " + Upgradeobj.getCostString(i));
		}

		AllUpgradesText[i].setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		AllUpgradesText[i].setForeground(Color.white);

		AllUpgradesPanel[i].add(AllUpgradesText[i]);
		AllUpgradesPanel[i].setBackground(new Color (0,0,0,0));
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent
		// add the label onto the panel

		AllUpgradesBTN[i].setPreferredSize(new Dimension(480, 50));
		AllUpgradesBTN[i].add(AllUpgradesPanel[i]);

		UpgradePanel.add(AllUpgradesBTN[i]);
		// add the manager panel onto the main panel

		AllUpgradesBTN[i].setContentAreaFilled(false);
		AllUpgradesBTN[i].setFocusable(false);
		AllUpgradesBTN[i].setBorderPainted(false);

		//name class with constructor
		//name of the class.setbounds

		// if the upgrade has already been bought, panel will be green
		if (Shopobj.getUpgradeBought(i)) {
			AllUpgradesPanel[i].setBackground(new Color(0, 255, 0, 100));
		}

		AllUpgradesBTN[i].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (i != 8) {
					if (Shopobj.getNumberBought(i) >= 1) {
						if (Shopobj.getTotalMoney() >= Upgradeobj.getCost(i)) {
							Shopobj.setTotalMoney(Shopobj.getTotalMoney() - Upgradeobj.getCost(i));
							Shopobj.setUpgradeBought(i);
							JOptionPane.showMessageDialog(UpgradePanel, "Congratulations! You purchased " + Upgradeobj.getName(i) + "!"
									+ "\nThis means your revenue is multiplied by 3!", "Purchased", JOptionPane.DEFAULT_OPTION); 
							AllUpgradesPanel[i].setBackground(new Color(0, 255, 0, 100));

							for (int i = 0; i < 8; i++) {
								ShopRevenue[i].setText(Shopobj.getRevenueString(i));
							}
							MoneyDisplay.setText(Shopobj.getTotalMoneyString());
						}
						else {
							JOptionPane.showMessageDialog(UpgradePanel, "Insufficient Funds", "ERROR", JOptionPane.ERROR_MESSAGE); 
						}
					}
					else {
						JOptionPane.showMessageDialog(UpgradePanel, "Make sure you purchase the droid before trying to purchase an upgrade!", 
								"Locked", 0);
					}
				}

				else {
					if (Shopobj.getTotalMoney() >= Upgradeobj.getCost(i)) {
						Shopobj.setTotalMoney(Shopobj.getTotalMoney() - Upgradeobj.getCost(i));
						Shopobj.setMonopoly(true);
						JOptionPane.showMessageDialog(UpgradePanel, "Congratulations! You purchased " + Upgradeobj.getName(i) + "!"
								+ "\nThis means all of your revenue is multiplied by 3!", "Purchased", JOptionPane.DEFAULT_OPTION); 
						AllUpgradesPanel[i].setBackground(new Color(0, 255, 0, 100));

						for (int i = 0; i < 8; i++) {
							ShopRevenue[i].setText(Shopobj.getRevenueString(i));
						}
						MoneyDisplay.setText(Shopobj.getTotalMoneyString());
					}
					else {
						JOptionPane.showMessageDialog(UpgradePanel, "Insufficient Funds", "ERROR", JOptionPane.ERROR_MESSAGE); 
					}
				}
			}
		});


	}
}
