package ImperialCapitalist;

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

public class UnlockScreen {

	private JFrame UnlockFrame;

	ResourceManager resources = new ResourceManager();

	public UnlockScreen(ShopObjects Shopobj) {
		UnlockFrame = new JFrame();
		UnlockFrame.setBounds(100, 100, 516, 600);
		UnlockFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UnlockFrame.setUndecorated(true);
		UnlockFrame.getContentPane().setLayout(null);
		UnlockFrame.setVisible(true);
		UnlockFrame.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 480, 400);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(null);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JPanel cursorPanel = new JPanel();
		cursorPanel.setBounds(10,100,400,400);
		cursorPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cursorPanel.setBackground(new Color (0,0,0,0));

		JButton CloseBTN = new JButton();
		CloseBTN.setBounds(200, 510, 100, 30);
		CloseBTN.setText("Close");
		CloseBTN.setFocusable(false);;
		CloseBTN.setBackground(Color.pink);
		CloseBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel UnlockLBL = new JLabel();
		UnlockLBL.setBounds(100, 0, 300, 258);
		UnlockLBL.setVerticalAlignment(SwingConstants.TOP);
		UnlockLBL.setHorizontalAlignment(SwingConstants.CENTER);
		UnlockLBL.setIcon(resources.getImage(24));

		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setBounds(0, 0, 800, 600);
		BackgroundLBL.setOpaque(true);
		BackgroundLBL.setIcon(resources.getImage(8));

		UnlockFrame.add(cursorPanel);
		UnlockFrame.add(UnlockLBL);
		UnlockFrame.add(scrollPane);
		UnlockFrame.add(CloseBTN);
		UnlockFrame.add(BackgroundLBL);

		JPanel OverallUnlockPanel = new JPanel();

		JPanel[] AllUnlockPanel = new JPanel[9];
		JLabel[] AllUnlockText = new JLabel[9];
		JButton[] AllUnlockBTN = new JButton[9];

		scrollPane.setViewportView(OverallUnlockPanel);
		OverallUnlockPanel.setLayout(new GridLayout(0, 1, 0, 10));
		OverallUnlockPanel.setBackground(Color.black);

		for (int i = 0; i<8; i++) {
			makeUnlockPanels(AllUnlockPanel, AllUnlockBTN, AllUnlockText, i, OverallUnlockPanel, Shopobj);
		}

		CloseBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UnlockFrame.getContentPane().removeAll();
				UnlockFrame.dispose();
			}
		});

	}

	private void makeUnlockPanels(JPanel[] AllUnlockPanel, JButton[] AllUnlockBTN, JLabel[] AllUnlockText, int i,
			JPanel OverallUnlockPanel, ShopObjects Shopobj) {
		AllUnlockPanel[i] = new JPanel();
		AllUnlockText[i] = new JLabel();
		AllUnlockBTN[i] = new JButton();

		AllUnlockPanel[i].setPreferredSize(new Dimension(480, 50));
		AllUnlockPanel[i].setBackground(new Color (0,0,0,0));
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent
		AllUnlockPanel[i].setBorder(BorderFactory.createLineBorder(Color.white));

		AllUnlockText[i].setVerticalAlignment(SwingConstants.CENTER);

		AllUnlockText[i].setText(Shopobj.getName(i) + " Unlockables");


		AllUnlockText[i].setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		AllUnlockText[i].setForeground(Color.white);

		AllUnlockPanel[i].add(AllUnlockText[i]);
		AllUnlockPanel[i].setBackground(new Color (0,0,0,0));
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent
		// add the label onto the panel

		AllUnlockBTN[i].setPreferredSize(new Dimension(480, 50));
		AllUnlockBTN[i].add(AllUnlockPanel[i]);

		OverallUnlockPanel.add(AllUnlockBTN[i]);
		// add the manager panel onto the main panel

		AllUnlockBTN[i].setContentAreaFilled(false);
		AllUnlockBTN[i].setFocusable(false);
		AllUnlockBTN[i].setBorderPainted(false);

		//name class with constructor
		//name of the class.setbounds

		AllUnlockBTN[i].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Shopobj.getNumberBought(i) > 1) {
					if (Shopobj.getNumberBought(i) >= 25) {
						int multiple = (int) Math.pow((int) Shopobj.getNumberBought(i) / 25, 2);
						if (multiple > 16) {
							multiple = 16;
						}
						if (multiple == 1) {
							multiple = 2;
						}
						JOptionPane.showMessageDialog(OverallUnlockPanel, 
								"For " + Shopobj.getName(i) + ", every 25 droids will multiply the progress bar speed by 2, giving you double"
										+ " the revenue!\n\nCurrently, you have " + Shopobj.getNumberBought(i) +" droids, giving you "
										+ "a multiple of " + multiple + "\n\nNote: Once you hit 100 "
										+ "droids, your revenue speed will no longer double. Try investing in something else!", 
										"Locked", 1);
					}
					else {
						JOptionPane.showMessageDialog(OverallUnlockPanel, 
								"For " + Shopobj.getName(i) + ", every 25 droids will multiply the progress bar speed by 2, giving you double"
										+ " the revenue!\n\nCurrently, you have " + Shopobj.getNumberBought(i) +" droids, which means "
										+ "you currently have the deafult revenue speed. \n\nNote: Once you hit 100 "
										+ "droids, your revenue speed will no longer double. Try investing in something else!", 
										"Locked", 1);
					}
				}
				else {
					JOptionPane.showMessageDialog(OverallUnlockPanel, 
							"For " + Shopobj.getName(i) + ", every 25 droids will multiply the progress bar speed by 2, giving you double "
									+ "the revenue!\n\nCurrently, you have " + Shopobj.getNumberBought(i) +" droid, giving you no multiple"
									+ " for revenue speed. \n\nNote: Once you hit 100 droids, your revenue speed will no longer "
									+ "double. Try investing in something else!", 
									"Locked", 1);
				}
			}
		});


	}
}
