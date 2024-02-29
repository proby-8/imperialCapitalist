package ImperialCapitalist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import objects.ShopObjects;

public class FileWriter {

	FileWriter(ShopObjects Shopobj, MusicPlayer Musicobj, String filename, boolean clear) { 
		// read the file into info[]
		BufferedReader filein;
		try {
			filein = new BufferedReader(new FileReader(filename));
			int numValues = Integer.parseInt(filein.readLine());
			filein.readLine(); // get rid of exist line
			int[] info = new int[numValues];
			for (int i = 0; i < numValues-1; i++) {
				info[i] = Integer.parseInt(filein.readLine());
			}
			filein.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		try {
			if (!Files.exists(Paths.get(filename)) || clear) { 
				File fout = new File(filename);
				FileOutputStream fos = new FileOutputStream(fout);
			 
				BufferedWriter fileout = new BufferedWriter(new OutputStreamWriter(fos));
				
				fileout.write("30");
				fileout.newLine();
				fileout.write("0");
				fileout.newLine();
				fileout.write("20");
				fileout.newLine();
				fileout.write("0");
				fileout.newLine();
				fileout.write("1");
				fileout.newLine();
				fileout.write("100");
				fileout.newLine();
				fileout.write("0");
				fileout.newLine();
				
				// first droid should start as 1
				fileout.write("1");
				fileout.newLine();
				// number bought
				for (int i = 1; i < 8; i++) {
					fileout.write("0");
					fileout.newLine();
				}

				// upgrade bought
				for (int i =0; i < 8; i++) {
					fileout.write("0");
					fileout.newLine();
				}
				
				// manager bought
				for (int i =0; i < 8; i++) {
					fileout.write("0");
					fileout.newLine();
				}
				
				fileout.close();
			}
			
			// this runs if there is a file, so this should always run unless something goes drastically wrong
			// have to turn them all to strings because 0 doesn't print properly
			else {
				File fout = new File(filename);
				FileOutputStream fos = new FileOutputStream(fout);

				BufferedWriter fileout = new BufferedWriter(new OutputStreamWriter(fos));

				fileout.write("30");
				fileout.newLine();
				fileout.write("1");
				fileout.newLine();
				fileout.write(String.valueOf(Shopobj.getProfileIndex()));
				fileout.newLine();
				fileout.write(String.valueOf(Musicobj.getSongIndex()));
				fileout.newLine();
				if (Musicobj.getPlaying()) {
					fileout.write("1");
				}
				else {
					fileout.write("0");
				}
				fileout.newLine();
				fileout.write(String.valueOf(Musicobj.getVolume()));
				fileout.newLine();
				fileout.write(String.valueOf((int) Shopobj.getTotalMoney()));
				fileout.newLine();

				// number bought
				for (int i =0; i < 8; i++) {
					fileout.write(String.valueOf(Shopobj.getNumberBought(i)));
					fileout.newLine();
				}

				// upgrade bought
				for (int i =0; i < 8; i++) {
					if (Shopobj.getUpgradeBought(i)) {
						fileout.write("1");
					}
					else {
						fileout.write("0");
					}
					fileout.newLine();
				}

				// manager bought
				for (int i =0; i < 8; i++) {
					if (Shopobj.getManager(i)) {
						fileout.write("1");
					}
					else {
						fileout.write("0");
					}
					fileout.newLine();
				}

				fileout.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

