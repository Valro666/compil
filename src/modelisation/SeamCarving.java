package modelisation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SeamCarving {

	public void writepgm(int[][] image, String filename) {

		try {

			FileWriter fw = new FileWriter(filename + ".pgm");

			BufferedWriter data = new BufferedWriter(fw);
			data.write("P2\n# CREATOR: XV Version 3.10a  Rev: 12/29/94\n"
					+ image.length + " " + image[0].length + "\n255\n");

			int i = 0;
			int j = 0;
			for (i = 0; i < image.length; i++) {
				for (j = 0; j < image[0].length; j++) {

					data.write(image[i][j] + "");

				}

			}

			data.close();

		} catch (Exception E) {
			System.out.println(E);

		}

	}

	public int[][] interest(int[][] image) {

		int[][] interest = new int[image.length][image[0].length];
		int ligne, colone;

		int maxi = image.length;
		int maxj = image[0].length;
		int tmp = 0;

		for (ligne = 0; ligne < maxi; ligne++) {
			for (colone = 0; colone < maxj; colone++) {

				if (colone > 0 && colone < maxj - 1) {
					tmp++;
					interest[ligne][colone] = Math.abs(image[ligne][colone]
							- ((image[ligne][colone - 1] + image[ligne][colone + 1]) / 2));
				} else if (colone == 0) {
					tmp = 0;
					interest[ligne][colone] = Math.abs(image[ligne][colone] - (image[ligne][colone + 1]));
				} else if (colone == maxj - 1) {
					interest[ligne][colone] = Math.abs(image[ligne][colone] - (image[ligne][colone - 1]));
				}
				System.out.print(interest[ligne][colone] + " ");

			}
			System.out.println();
		}
		System.out.println("tmp : " + tmp);
		return interest;
	}

	public static int[][] readpgm(String fn) {
		try {
			InputStream f = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(fn);
			BufferedReader d = new BufferedReader(new InputStreamReader(f));
			String magic = d.readLine();
			String line = d.readLine();
			while (line.startsWith("#")) {
				line = d.readLine();
			}
			Scanner s = new Scanner(line);
			int width = s.nextInt();
			int height = s.nextInt();
			line = d.readLine();
			s = new Scanner(line);
			int maxVal = s.nextInt();
			int[][] im = new int[height][width];
			s = new Scanner(d);
			int count = 0;
			while (count < height * width) {
				im[count / width][count % width] = s.nextInt();
				count++;
			}
			return im;
		}

		catch (Throwable t) {
			t.printStackTrace(System.err);
			return null;
		}
	}

}
