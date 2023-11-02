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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import objects.ResourceManager;
import objects.ShopObjects;

public class OverviewScrollScreen {

	private JFrame OverviewScrollFrame;

	ResourceManager resources = new ResourceManager();

	public OverviewScrollScreen(ShopObjects Shopobj) {
		OverviewScrollFrame = new JFrame();
		OverviewScrollFrame.setBounds(100, 100, 516, 600);
		OverviewScrollFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		OverviewScrollFrame.setUndecorated(true);
		OverviewScrollFrame.getContentPane().setLayout(null);
		OverviewScrollFrame.setVisible(true);
		OverviewScrollFrame.setLocationRelativeTo(null);

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

		JLabel OverviewLBL = new JLabel();
		OverviewLBL.setBounds(100, 0, 300, 258);
		OverviewLBL.setVerticalAlignment(SwingConstants.TOP);
		OverviewLBL.setHorizontalAlignment(SwingConstants.CENTER);
		OverviewLBL.setIcon(resources.getImage(25));

		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setBounds(0, 0, 800, 600);
		BackgroundLBL.setOpaque(true);
		BackgroundLBL.setIcon(resources.getImage(8));

		OverviewScrollFrame.add(cursorPanel);
		OverviewScrollFrame.add(OverviewLBL);
		OverviewScrollFrame.add(scrollPane);
		OverviewScrollFrame.add(CloseBTN);
		OverviewScrollFrame.add(BackgroundLBL);

		JPanel OverallOverviewPanel = new JPanel();

		JPanel[] AllOverviewPanel = new JPanel[9];
		JLabel[] AllOverviewText = new JLabel[9];
		JButton[] AllOverviewBTN = new JButton[9];

		scrollPane.setViewportView(OverallOverviewPanel);
		OverallOverviewPanel.setLayout(new GridLayout(0, 1, 0, 10));
		OverallOverviewPanel.setBackground(Color.black);

		for (int i = 0; i<8; i++) {
			makeOverviewPanels(AllOverviewPanel, AllOverviewBTN, AllOverviewText, i, OverallOverviewPanel, Shopobj);
		}

		CloseBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OverviewScrollFrame.getContentPane().removeAll();
				OverviewScrollFrame.dispose();
			}
		});

	}

	private void makeOverviewPanels(JPanel[] AllOverviewPanel, JButton[] AllOverviewBTN, JLabel[] AllOverviewText, int i,
			JPanel OverallOverviewPanel, ShopObjects Shopobj) {
		AllOverviewPanel[i] = new JPanel();
		AllOverviewText[i] = new JLabel();
		AllOverviewBTN[i] = new JButton();

		AllOverviewPanel[i].setPreferredSize(new Dimension(480, 50));
		AllOverviewPanel[i].setBackground(new Color (0,0,0,0));
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent
		AllOverviewPanel[i].setBorder(BorderFactory.createLineBorder(Color.white));

		AllOverviewText[i].setVerticalAlignment(SwingConstants.CENTER);

		AllOverviewText[i].setText(Shopobj.getName(i) + " Overview");


		AllOverviewText[i].setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		AllOverviewText[i].setForeground(Color.white);

		AllOverviewPanel[i].add(AllOverviewText[i]);
		AllOverviewPanel[i].setBackground(new Color (0,0,0,0));
		// the fourth zero in new colour is the alpha, or transparency, 0 makes it transparent
		// add the label onto the panel

		AllOverviewBTN[i].setPreferredSize(new Dimension(480, 50));
		AllOverviewBTN[i].add(AllOverviewPanel[i]);

		OverallOverviewPanel.add(AllOverviewBTN[i]);
		// add the manager panel onto the main panel

		AllOverviewBTN[i].setContentAreaFilled(false);
		AllOverviewBTN[i].setFocusable(false);
		AllOverviewBTN[i].setBorderPainted(false);

		//name class with constructor
		//name of the class.setbounds

		AllOverviewBTN[i].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new OverviewScreen(Shopobj, i);
			}
		});


	}
}
