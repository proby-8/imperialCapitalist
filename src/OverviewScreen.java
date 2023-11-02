import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import objects.ResourceManager;
import objects.ShopObjects;

public class OverviewScreen {

	private JFrame OverviewFrame;
	
	ResourceManager resources = new ResourceManager();

	public OverviewScreen(ShopObjects Shopobj, int index) {

		// Frame for main menu
		OverviewFrame = new JFrame();
		OverviewFrame.setBounds(100, 100, 516, 600);
		OverviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		OverviewFrame.getContentPane().setLayout(null);
		OverviewFrame.setUndecorated(true);
		OverviewFrame.setVisible(true);
		OverviewFrame.setLocationRelativeTo(null);

		JLabel DroidPictureLBL = new JLabel();
		DroidPictureLBL.setBounds(100, 15, 300, 300);
		DroidPictureLBL.setIcon(resources.getImage(index));
		DroidPictureLBL.setVerticalAlignment(SwingConstants.TOP);
		DroidPictureLBL.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel SunRayLBL = new JLabel(resources.getImage(10));
		SunRayLBL.setBounds(0, 0, 500, 500);
		SunRayLBL.setVerticalAlignment(SwingConstants.TOP);
		SunRayLBL.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel DroidNameLBL = new JLabel();
		DroidNameLBL.setBounds(10, 310, 480, 20);
		DroidNameLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		DroidNameLBL.setText(Shopobj.getName(index));
		DroidNameLBL.setForeground(Color.white);
		DroidNameLBL.setHorizontalAlignment(SwingConstants.CENTER);

		final String html = "<html><body style='width: %1spx'>%1s";

		JLabel DroidDescriptionLBL = new JLabel(String.format(html, 380, Shopobj.getDescription(index)));
		DroidDescriptionLBL.setBounds(10, 330, 490, 200);
		DroidDescriptionLBL.setForeground(Color.white);
		DroidDescriptionLBL.setHorizontalAlignment(SwingConstants.CENTER);

		JButton CloseBTN = new JButton();
		CloseBTN.setBounds(200, 540, 100, 30);
		CloseBTN.setText("Close");
		CloseBTN.setFocusable(false);;
		CloseBTN.setBackground(Color.pink);
		CloseBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		OverviewFrame.getContentPane().setBackground(Color.BLACK);
		OverviewFrame.getContentPane().add(DroidPictureLBL);
		OverviewFrame.getContentPane().add(DroidNameLBL);
		OverviewFrame.getContentPane().add(DroidDescriptionLBL);
		OverviewFrame.getContentPane().add(CloseBTN);
		OverviewFrame.getContentPane().add(SunRayLBL);

		CloseBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OverviewFrame.dispose();
			}
		});
	}

}
