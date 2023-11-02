package objects;

import java.text.NumberFormat;

public class UpgradeObjects {

	private int[] cost = new int[9];
	private int base = 2500;
	private String[] name = new String[9];
	
	public UpgradeObjects() {
		cost[0] = base;
		name[0] = "Reinforced Armour";
		
		cost[1] = base*2;
		name[1] = "Powerful Blasters";

		cost[2] = base*4;
		name[2] = "Enhanced Eyesight";
		
		cost[3] = base*8;
		name[3] = "Increased Stealth";
		
		cost[4] = base*16;
		name[4] = "Improved Shields";
		
		cost[5] = base*32;
		name[5] = "Boosted Voltage";
		
		cost[6] = base*64;
		name[6] = "Fear Factor";
		
		cost[7] = base*128;
		name[7] = "More Lightsabers";

		cost[8] = base*256;
		name[8] = "Monopoly";
	}
	
	public int getCost(int index) {
		return cost[index];
	}
	
	public String getCostString(int index) {
		return NumberFormat.getInstance().format(cost[index]);
	}
	
	public String getName(int index) {
		return name[index];
	}
}
