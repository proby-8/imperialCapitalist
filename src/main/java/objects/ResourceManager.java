package objects;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

// Allows me to easily call all images from any class
public class ResourceManager {
	
	// create array for each image
	private ImageIcon[] Images = new ImageIcon[26];
	
	// x is used for profile picture size
	int x = 240;

	// initialize all images
	public ResourceManager() {
		Images[0] = setImage("BattleDroid", 180, 300);
		Images[1] = setImage("SuperBattleDroid", 210, 300);
		Images[2] = setImage("ProbeDroid", 207, 300);
		Images[3] = setImage("BX-Series", 210, 300);
		Images[4] = setImage("droideka", 240, 258);
		Images[5] = setImage("MagnaGuard", 234, 261);
		Images[6] = setImage("DarkTrooper", 196, 300);
		Images[7] = setImage("GeneralGrievous", 240, 300);

		Images[8] = setImage("space", 800, 600);
		Images[9] = setImage("white_credit", 40, 60);
		Images[10] = setImageGIF("big_sun_ray", 500, 500);
		Images[11] = setImage("shop", 120, 103);
		
		Images[12] = setImage("vader_profile", x, x);
		Images[13] = setImage("obiwan_profile", x, x);
		Images[14] = setImage("maul_profile", x, x);
		Images[15] = setImage("windu_profile", x, x);
		Images[16] = setImage("poe_profile", x, x);
		Images[17] = setImage("sidious_profile", x, x);
		Images[18] = setImage("anakin_profile", x , x);
		Images[19] = setImage("boss_profile", x, x);
		Images[20] = setImage("ahsoka_profile", x, x);
		Images[21] = setImage("blank_profile", x, x);
		
		Images[22] = setImage("on_switch", 244, 130);
		Images[23] = setImage("off_switch", 244, 130);
		Images[24] = setImage("factory", 120, 107);
		Images[25] = setImage("book", 120, 95);
	}
	
	// creates the image using the file name
	private ImageIcon setImage(String filename, int width, int height) {
		URL urlImage = this.getClass().getResource("/images/" + filename + ".png");
		ImageIcon iconImage = new ImageIcon(urlImage);
		Image Image = iconImage.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(Image);
	}
	
	// creates the gif using the file name
	private ImageIcon setImageGIF(String filename, int width, int height) {
		URL urlImage = this.getClass().getResource("/images/" + filename + ".gif");
		ImageIcon iconImage = new ImageIcon(urlImage);
		return iconImage;
	}
	
	// returns the image
	public ImageIcon getImage(int index) {
		return Images[index];
	}
	
	// returns scaled instance of the image
	public ImageIcon getScaledImage(int index, int scaleWidth, int scaleHeight) {
		Image Image = Images[index].getImage().getScaledInstance(
				Images[index].getIconWidth() / scaleWidth, 
				Images[index].getIconHeight() / scaleHeight, 
				java.awt.Image.SCALE_SMOOTH);
		
		return new ImageIcon(Image);
	}
}
