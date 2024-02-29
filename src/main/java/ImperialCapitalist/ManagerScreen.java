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

import objects.ManagerObject;
import objects.ResourceManager;
import objects.ShopObjects;

public class ManagerScreen {

	private JFrame ManagerFrame;
	ResourceManager resources = new ResourceManager();

	public ManagerScreen(ManagerObject Managerobj, ShopObjects Shopobj, JLabel MoneyDisplay) {
		
		// Frame for main menu
		ManagerFrame = new JFrame();
		ManagerFrame.setBounds(100, 100, 516, 600);
		ManagerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ManagerFrame.getContentPane().setLayout(null);
		ManagerFrame.setVisible(true);
		ManagerFrame.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 480, 400);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
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

		JLabel ManagerLBL = new JLabel();
		ManagerLBL.setBounds(100, 0, 300, 258);
		ManagerLBL.setVerticalAlignment(SwingConstants.TOP);
		ManagerLBL.setHorizontalAlignment(SwingConstants.CENTER);
		ManagerLBL.setIcon(resources.getImage(11));

		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setBounds(0, 0, 800, 600);
		BackgroundLBL.setOpaque(true);
		BackgroundLBL.setIcon(resources.getImage(8));

		ManagerFrame.add(cursorPanel);
		ManagerFrame.add(ManagerLBL);
		ManagerFrame.add(scrollPane);
		ManagerFrame.add(CloseBTN);
		ManagerFrame.add(BackgroundLBL);

		JPanel ManagerPanel = new JPanel();

		JPanel[] AllManagersPanel = new JPanel[8];
		JButton[] AllManagersBTN = new JButton[8];
		JLabel[] AllManagersCosts = new JLabel[8];

		scrollPane.setViewportView(ManagerPanel);
		ManagerPanel.setLayout(new GridLayout(0, 1, 0, 0));

		ManagerPanel.setBackground(Color.black);
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent

		for (int i = 0; i<8; i++) {
			makeManagerPanels(AllManagersPanel, AllManagersBTN, AllManagersCosts, i, ManagerPanel, Managerobj, Shopobj, MoneyDisplay);
		}

		CloseBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagerFrame.dispose();
			}
		});

	}

	private void makeManagerPanels(JPanel[] AllManagersPanel, JButton[] AllManagersBTN, JLabel[] AllManagersCosts, int i,
			JPanel ManagerPanel, ManagerObject Managerobj, ShopObjects Shopobj, JLabel MoneyDisplay) {
		AllManagersPanel[i] = new JPanel();
		AllManagersCosts[i] = new JLabel();
		AllManagersBTN[i] = new JButton();

		AllManagersPanel[i].setPreferredSize(new Dimension(480, 50));
		AllManagersPanel[i].setBackground(new Color (0,0,0,0));
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent
		AllManagersPanel[i].setBorder(BorderFactory.createLineBorder(Color.white));

		AllManagersCosts[i].setText(Managerobj.getCostString(i));
		AllManagersCosts[i].setVerticalAlignment(SwingConstants.CENTER);

		AllManagersCosts[i].setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		AllManagersCosts[i].setForeground(Color.white);

		AllManagersCosts[i].setText(Shopobj.getName(i) + " Manager: " + Managerobj.getShortName(i) + "; Price: " + Managerobj.getCostString(i));

		AllManagersPanel[i].add(AllManagersCosts[i]);
		AllManagersPanel[i].setBackground(new Color (0,0,0,0));
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent
		// add the label onto the panel

		AllManagersBTN[i].setPreferredSize(new Dimension(480, 50));
		AllManagersBTN[i].add(AllManagersPanel[i]);

		ManagerPanel.add(AllManagersBTN[i]);
		// add the manager panel onto the main panel

		AllManagersBTN[i].setContentAreaFilled(false);
		AllManagersBTN[i].setFocusable(false);
		AllManagersBTN[i].setBorderPainted(false);

		//name class with constructor
		//name of the class.setbounds

		if (Shopobj.getManager(i)) {
			AllManagersPanel[i].setBackground(new Color(0, 255, 0, 100));
		}

		AllManagersBTN[i].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!Shopobj.getManager(i)) {
					if (Shopobj.getNumberBought(i) >= 1) {
						if (Shopobj.getTotalMoney() >= Managerobj.getCost(i)) {
							Shopobj.setTotalMoney((Shopobj.getTotalMoney() - Managerobj.getCost(i)));
							Shopobj.setManager(i);
							Shopobj.setRunning(i,  true);
							JOptionPane.showMessageDialog(ManagerPanel, "Congratulations! You purchased " + Managerobj.getName(i) + "!\nThis means your " 
									+ Shopobj.getName(i) + " will automatically run!", "Purchased", JOptionPane.DEFAULT_OPTION); 
							AllManagersPanel[i].setBackground(new Color(0,255,0,100));
							MoneyDisplay.setText(Shopobj.getTotalMoneyString());
						}
						else {
							JOptionPane.showMessageDialog(ManagerPanel, "Insufficient Funds", "ERROR", JOptionPane.ERROR_MESSAGE); 
						}
					}
					else {
						JOptionPane.showMessageDialog(ManagerPanel, "Make sure you purchase the droid before trying to purchase a manager!", 
								"Locked", 0);
					}
				}
				else {
					JOptionPane.showMessageDialog(ManagerPanel, "You have already purchased this manager!", 
							"Locked", 0);
				}
			}
		});
	}
}
