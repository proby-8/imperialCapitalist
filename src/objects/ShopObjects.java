package objects;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ShopObjects {

	//For object, make one for each shop, variables  boolean unlocked, cost, number bought, earnings per cycle, time for cycle (period)
	//in overview, put overview of each droid, also show this unlock screen (droid picture, quick description) when droid is unlocked

	public double[] Cost = new double[8];
	public int[] numberBought = new int[8];
	public int[] revenue = new int[8];
	public boolean[] manager = new boolean[8];
	public boolean[] running = new boolean[8];
	public final double[] firstCost = new double[8];
	private int[] upgradeCoefficient = new int[8];
	public final double[] coefficient = new double[8];
	public final int[] initialRevenue = new int[8];
	public double[] cycleTime = new double[8];
	public int[] height = new int[8];
	public int[] width = new int[8];
	public final String[] description = new String[8];
	public final String[] name = new String[8];
	public final String[] link = new String[8];

	private int BuyOption = 1;
	private double TotalMoney;

	public boolean[] boughtUpgrade = new boolean[9];

	private boolean[] i25 = new boolean[8];
	private boolean[] i50 = new boolean[8];
	private boolean[] i75 = new boolean[8];
	private boolean[] i100 = new boolean[8];
	
	private int profileIndex = 20;
	
	private boolean monopoly = false;

	public ShopObjects() {
		coefficient[0] = 1.07;
		initialRevenue[0] = 1;
		firstCost[0] = 3.738;
		Cost[0] = 4;
		cycleTime[0] = 0.6;
		if (numberBought[0] == 0) {
			numberBought[0] = 1;
		}
		name[0] = "Battle Droid";
		description[0] = "	Battle droids, also known as combat droids, war droids clankers, droid soldiers, or battle bots,"
				+ " were a type of droid designed for combat. Over the years, many different models of battle droid were being "
				+ "utilized by various factions throughout the galaxy. One such faction was the Confederacy of Independent Systems, "
				+ "which used a number of different models of battle droid during the Clone Wars to make up the Separatist Droid Army. "
				+ "Under the command of General Grievous, varying models of battle droids fought against the Galactic Republic's "
				+ "clone troopers until the final days of the war, when the Confederate battle droids were deactivated by the Galactic Empire.";
		width[0] = 60;
		height[0] = 100;
		link[0] = "BattleDroid.png";

		coefficient[1] = 1.15;
		initialRevenue[1] = 60;
		Cost[1] = firstCost[1] = 60;
		cycleTime[1] = 3;
		name[1] = "Super Battle Droid";
		description[1] = "	B2-series super battle droids, also known as B2 super battle droids, super battle droids or super droids, were"
				+ " an advanced battle droid used by the Confederacy of Independent Systems during the Clone Wars. Although one model of "
				+ "the B2-series was only equipped with wrist blasters on their right forearm, another was outfitted with wrist blasters on "
				+ "both forearms. Super battle droids were much stronger than their predecessors, and like the updated B1s used by the Confederacy, "
				+ "they did not require a command system to operate, which gave the droids limited independence. Another improvement was their "
				+ "thick armor casing, which contained their fragile sensors. However, B2s were designed with simple processors, limiting their"
				+ " ability to formulate strategies. As a result, they relied on organic commanders or tactical droids in order to effectively "
				+ "operate. The B2 was much stronger than the B1s, with enough strength to lift a clone trooper off the ground.";
		width[1] = 70;
		height[1] = 100;
		link[0] = "SuperBattleDroid.png";

		coefficient[2] = 1.14;
		initialRevenue[2] = 540;
		Cost[2] = firstCost[2] = 720;
		cycleTime[2] = 6;
		name[2] = "Probe Droid";
		description[2] = "	A probe droid, also known as a recon droid, probot, or Sensor Droid, was a type of droid that could be used for deep "
				+ "space exploration and reconnaissance. They were tenacious hunters and searchers and used manipulator arms to perform various"
				+ " tasks. Notable models included the DRK-1 Dark Eye probe droid and the Viper probe droid. They could be equipped with both "
				+ "shields and blasters.";
		width[2] = 69;
		height[2] = 100;
		link[1] = "ProbeDroid.png";

		coefficient[3] = 1.13;
		initialRevenue[3] = 4320;
		Cost[3] = firstCost[3] = 8640;
		cycleTime[3] = 12;
		name[3] = "Assassin Droid";
		description[3] = "	Assassin droids, also known as war droids, were a type of droid built specifically for the purpose of assassination. "
				+ "They were independently programmed, answering to no masters, but could be programmed to serve one or more masters. The company "
				+ "Holowan Laboratories produced a model of assassin droid known as the IG-86 sentinel droid, which were used as enforcers by "
				+ "criminal elements. Other assassin droids included the BT-1 assassin droids and 0-0-0 produced by the Galactic Empire's Tarkin "
				+ "Initiative, and the droid bounty hunters IG-88, IG-90, and IG-11. The Imperial Security Bureau and Imperial Army Intelligence"
				+ " utilized the E522 assassin droid for use of black op missions. The Rebel Alliance's Intelligence also later used it.";
		width[3] = 70;
		height[3] = 100;
		link[2] = "BX-Series.png";

		coefficient[4] = 1.12;
		initialRevenue[4] = 51840;
		Cost[4] = firstCost[4] = 103680;
		cycleTime[4] = 24;
		name[4] = "Droideka";
		description[4] = "	Droidekas, also known as destroyer droids (destroyers for short), or as rollies in clone trooper slang, were a type"
				+ " of battle droid used by the Trade Federation during the Invasion of Naboo and, later, by the Confederacy of Independent Systems "
				+ "during the Clone Wars. Manufactured by the Colicoids on Colla IV, droidekas were dangerous and deadly, designed to exterminate"
				+ " their adversaries with extreme prejudice. They could transform their shape by curling into a ball and moving up to 75 kilometers"
				+ " per hour across a surface, or stand on three legs and utilize a shield generator while firing at a target. Before the Invasion "
				+ "of Naboo, Obi-Wan Kenobi and Qui-Gon Jinn had to face droidekas on a Droid Control Ship.";
		width[4] = 80;
		height[4] = 86;
		link[4] = "droideka.png";

		coefficient[5] = 1.11;
		initialRevenue[5] = 622080;
		Cost[5] = firstCost[5] = 1244160;
		cycleTime[5] = 96;
		name[5] = "Magna Guard";
		description[5] = "	G-100 MagnaGuards, or Magna Guard, were fearsome bodyguard droids and a type of battle droid created by Holowan Mechanicals."
				+ " They were a favorite of General Grievous, who used them as his bodyguards during the Clone Wars, though they were also known to "
				+ "accompany other high-ranking Separatist personnel. MagnaGuards were equipped with electrostaffs that could be used against Jedi "
				+ "lightsabers and were capable of continuing a fight even with the loss of one or multiple limbs or even their heads. They were worthy "
				+ "competitors even to the most skilled Jedi; one was even able to back Obi-Wan Kenobi into a corner.";
		width[5] = 78;
		height[5] = 87;
		link[5] = "MagnaGuard.png";

		coefficient[6] = 1.11;
		initialRevenue[6] = 622080;
		Cost[6] = firstCost[6] = 1244160;
		cycleTime[6] = 96;
		name[6] = "Dark Trooper";
		description[6] = "	The third generation units were deployed on Tython to retrieve Grogu after he was brought to the seeing stone. "
				+ "These dark troopers were equipped with jet boosters in their feet to enable in-atmospheric flight and were capable of launching "
				+ "directly from a cruiser, as opposed to having to be deployed in a troop transport. Later, when the Mandalorian bounty hunter "
				+ "Din Djarin and his allies attempted to rescue Grogu from Gideon's light cruiser, the Moff activated his dark trooper platoon, "
				+ "though the entire garrison was eliminated by Jedi Master Luke Skywalker.";
		width[6] = 64;
		height[6] = 100;
		link[6] = "DarkTrooper.png";

		coefficient[7] = 1.10;
		initialRevenue[7] = 7464960;
		Cost[7] = firstCost[7] = 14929920;
		cycleTime[7] = 384;
		name[7] = "General Grievous";
		description[7] = "	Grievous, born Qymaen jai Sheelal, was a Kaleesh male warlord who served within the military forces of the Confederacy "
				+ "of Independent Systems as a commanding officer during the Clone Wars in the final years of the Republic Era. In addition to his "
				+ "position as a general within the Separatist Droid Army, the Kaleesh held the title of Supreme Martial Commander of the Separatist "
				+ "Droid Armies throughout the duration of the war, in which he led countless engagements against the Republic's Grand Army. "
				+ "Recognized for his ruthless tactics and extensive cybernetic enhancements, Grievous utilized the Separatist Droid Army and "
				+ "his adept combat skills to instill fear throughout the galaxy as he traveled to and invaded planets, while also engaging numerous "
				+ "Knights of the Jedi Order, his sworn enemies.";
		width[7] = 80;
		height[7] = 100;
		link[7] = "GeneralGrievous.png";
	}

	public int getNumberBought (int index) {
		return numberBought[index];
	}
	public void setNumberBought (int num, int index) {
		numberBought[index] = num;
	}

	public double purchase(int NumberPurchased, double TotalMoney, int index) {
		revenue[index] = revenue[index] + (initialRevenue[index] * NumberPurchased);

		TotalMoney -= getCost(NumberPurchased, index);

		numberBought[index] += NumberPurchased;
		double temp = Math.pow(coefficient[index], numberBought[index]);
		Cost[index] = firstCost[index] * temp;

		return TotalMoney;
	}

	public double purchaseMax(int numPurchased, int index, double cost) {
		revenue[index] = revenue[index] + (initialRevenue[index] * numPurchased);

		numberBought[index] += getMaxBuy(index);
		double temp = Math.pow(coefficient[index], numberBought[index]);
		Cost[index] = firstCost[index] * temp;

		TotalMoney -= cost;
		return TotalMoney;
	}

	public double getCost(int i, int index) {
		int fakenumberBought = numberBought[index];
		double fakecost;
		double overallfakecost=0;
		DecimalFormat df = new DecimalFormat("0.00");


		if (i==1) {
			return Double.parseDouble(df.format(Cost[index]));
		}
		else if (i==10) {
			for (int y=0; y<=i-1; y++) {
				double temp = Math.pow(coefficient[index], y + fakenumberBought);
				fakecost = firstCost[index] * temp;
				overallfakecost += fakecost;
			}
			return Double.parseDouble(df.format(overallfakecost));
		}
		else if (i==100) {
			for (int y=0; y<=i-1; y++) {
				double temp = Math.pow(coefficient[index], y + fakenumberBought);
				fakecost = firstCost[index] * temp;
				overallfakecost += fakecost;
			}
			return Double.parseDouble(df.format(overallfakecost));
		}
		return 0.0;
	}

	public int getMaxBuy(int index) {
		DecimalFormat df = new DecimalFormat("0.00");

		int y = 0;
		double totalCost=0;
		double tempCost;

		while (true) {
			double temp = Math.pow(coefficient[index], y + numberBought[index]);
			y++;
			tempCost = firstCost[index] * temp;
			totalCost += tempCost;
			if (TotalMoney < Double.parseDouble(df.format(totalCost))) {
				return y-1;
			}
		}
	}

	public String getMaxBuyCostString(int index) {
		DecimalFormat df = new DecimalFormat("0.00");

		int y = 0;
		double totalCost=0;
		double tempCost;
		double finalCost;

		while (true) {
			double temp = Math.pow(coefficient[index], y + numberBought[index]);
			y++;
			tempCost = firstCost[index] * temp;
			totalCost += tempCost;
			if (TotalMoney < Double.parseDouble(df.format(totalCost))) {
				finalCost = (totalCost-tempCost);
				return format(Double.parseDouble(df.format(finalCost)));
			}
		}
	}

	public Double getMaxBuyCost(int index) {
		DecimalFormat df = new DecimalFormat("0.00");

		int y = 0;
		double totalCost=0;
		double tempCost;
		double finalCost;

		while (true) {
			double temp = Math.pow(coefficient[index], y + numberBought[index]);
			y++;
			tempCost = firstCost[index] * temp;
			totalCost += tempCost;
			if (TotalMoney < Double.parseDouble(df.format(totalCost))) {
				finalCost = (totalCost-tempCost);
				return Double.parseDouble(df.format(finalCost));
			}
		}
	}

	public String getCostString(int i, int index) {
		int fakenumberBought = numberBought[index];
		double fakecost;
		double overallfakecost=0;
		DecimalFormat df = new DecimalFormat("0.00");

		if (i==1) {
			return format(Cost[index]);
		}
		else if (i==10) {
			for (int y=0; y<=i-1; y++) {
				double temp = Math.pow(coefficient[index], y + fakenumberBought);
				fakecost = firstCost[index] * temp;
				overallfakecost += Double.parseDouble(df.format(fakecost));
			}
			return format(overallfakecost);
		}
		else if (i==100) {
			for (int y=0; y<=i-1; y++) {
				double temp = Math.pow(coefficient[index], y + fakenumberBought);
				fakecost = firstCost[index] * temp;
				overallfakecost += Double.parseDouble(df.format(fakecost));
			}
			return format(overallfakecost);
		}
		return "unlucky";
	}

	public double getRevenue(int index) {
		if (revenue[index] == 0) {
			revenue[index] = initialRevenue[index];
		}
		revenue[index] =+ (initialRevenue[index] * numberBought[index]);

		if (boughtUpgrade[index]) {
			revenue[index] *= 3;
		}
		if (monopoly) {
			revenue[index] *= 3;
		}
		return revenue[index];
	}

	public String getRevenueString(int index) {
		if (revenue[index] == 0) {
			revenue[index] = initialRevenue[index];
		}
		revenue[index] =+ (initialRevenue[index] * numberBought[index]);

		if (boughtUpgrade[index]) {
			revenue[index] *= 3;
		}
		if (monopoly) {
			revenue[index] *= 3;
		}
		return NumberFormat.getInstance().format(revenue[index]);
	}

	public double getCycleTime(int index) {
		return cycleTime[index] * 100;
	}

	public void setManager(int index) {
		manager[index] = true;
	}

	public boolean getManager(int index) {
		return manager[index];
	}

	public boolean getRunning(int index) {
		if (this.getManager(index)) {
			return true;
		}
		else {
			return running[index];
		}
	}

	public void setRunning(int index, boolean thing) {
		running[index] = thing;
	}

	public String getLink(int index) {
		return link[index];
	}
	public int getWidth(int index) {
		return width[index];
	}
	public int getHeight(int index) {
		return height[index];
	}

	public String getName(int index) {
		return name[index];
	}

	public String getDescription(int index) {
		return description[index];
	}

	public void setBuyOption(int number) {
		BuyOption = number;
	}

	public int getBuyOption() {
		return BuyOption;
	}

	public void setTotalMoney(double d) {
		TotalMoney = d;
	}
	public double getTotalMoney() {
		return TotalMoney;
	}

	public static String format(double value) {
		if (value == 0) {
			return "0.00";
		}
		int power; 
		String[] suffix = new String[23];
		suffix[0] = "";
		suffix[1] = " Thousand";
		suffix[2] = " Million";
		suffix[3] = " Billion";
		suffix[4] = " Trillion";
		suffix[5] = " Quadrillion";
		suffix[6] = " Quintillion";
		suffix[7] = " Sextillion";
		suffix[8] = " Septillion";
		suffix[9] = " Octillion";
		suffix[10] = " Nonillion";
		suffix[11] = " Decillion";
		suffix[12] = " Undecillion";
		suffix[13] = " Duodecillion";
		suffix[14] = " Tredecillion";
		suffix[15] = " Quattuordecillion";
		suffix[16] = " Quindecillion";
		suffix[17] = " Sexdecillion";
		suffix[18] = " Septendecillion";
		suffix[19] = " Octodecillion";
		suffix[20] = " Novemdecillion";
		suffix[21] = " Vigintillion";
		suffix[22] = " Unvigintillion";

		for (int i = 0; i <=22; i++) {
			suffix[i].toUpperCase();
		}
		String formattedNumber = "";

		NumberFormat formatter = new DecimalFormat("#,###.00");
		power = (int)StrictMath.log10(value);
		value = value/(Math.pow(10,(power/3)*3));
		formattedNumber=formatter.format(value);
		formattedNumber = formattedNumber + suffix[power/3];
		// formattedNumber.length()>4 ?  formattedNumber.replaceAll("\\.[0-9]+", "") : formattedNumber;
		return formattedNumber;
	}

	public String getTotalMoneyString() {
		return format(TotalMoney);
	}

	public void setUpgradeCoefficient(int index, int coef) {
		upgradeCoefficient[index] = coef;
	}

	public void setUpgradeBought(int index) {
		boughtUpgrade[index] = true;
	}

	public boolean getUpgradeBought(int index) {
		return boughtUpgrade[index];
	}

	public void changeCycleSpeed(int index) {
		if (numberBought[index] >= 25) {
			if (!i25[index]) {
				cycleTime[index] /= 2;
				i25[index] = true;
			}
		}
		if (numberBought[index] >= 50) {
			if (!i50[index]) {
				cycleTime[index] /= 2;
				i50[index] = true;
			}
		}
		if (numberBought[index] >= 75) {
			if (!i75[index]) {
				cycleTime[index] /= 2;
				i75[index] = true;
			}
		}
		if (numberBought[index] >= 100) {
			if (!i100[index]) {
				cycleTime[index] /= 2;
				i100[index] = true;
			}
		}
	}
	
	public int getProfileIndex() {
		return profileIndex;
	}
	
	public void setProfileIndex(int i) {
		profileIndex = i;
	}
	
	public void setMonopoly(boolean bought) {
		monopoly = bought;
	}
	
	public boolean getMonopoly() {
		return monopoly;
	}
}
