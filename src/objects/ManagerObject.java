package objects;

import java.text.NumberFormat;

public class ManagerObject {

	private int[] cost = new int[8];
	private String[] name = new String[8];
	private String[] shortName = new String[8];
	ShopObjects Shopobj = new ShopObjects();

	public ManagerObject() {
		cost[0] = 4000;
		name[0] = "Geonosians";
		shortName[0] = "Geonosians";

		cost[1] = 120000;
		name[1] = "Techno Union";
		shortName[1] = "Techno Union";

		cost[2] = 650000;
		name[2] = "Arakyd Industries";
		shortName[2] = "Arakyd Industries";
		
		cost[3] = 4000000;
		name[3] = "Baktoid Combat Automata";
		shortName[3] = "BCA";

		cost[4] = 10000000;
		name[4] = "Colicoid Creation Nest";
		shortName[4] = "CCN";

		cost[5] = 750000000;
		name[5] = "Holowan Mechanicals";
		shortName[5] = "Holowan Mechanicals";

		cost[6] = 511000000;
		name[6] = "Imperial Department of Military Research";
		shortName[6] = "IDMR";

		cost[7] = 106 * 100000000;
		name[7] = "Count Dooku";
		shortName[7] = "Count Dooku";
		// cant put in a billion so have to bait the system for big numbers
	}

	public double getCost(int index) {
		return Math.pow(250.0, index+1);
	}

	public String getCostString(int index) {
		return NumberFormat.getInstance().format(Math.pow(250.0, index+1));
	}

	public String getName(int index) {
		return name[index];
	}
	
	public String getShortName(int index) {
		return shortName[index];
	}
}
