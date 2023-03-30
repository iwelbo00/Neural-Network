import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class PS4 {
	double[][] w1 = new double[30][785];
	double[][] w2 = new double[10][31];
	double[][] x = new double[10000][785];
	int[][] y = new int[10000][1];

	public static void main(String[] args) {

		if (args.length == 4) {
			PS4 p = new PS4();
			p.readEm(args[0], args[1], args[2], args[3]);
		} else {
			System.out.println("Invalid amount of arguments");
		}
	}

	public void readEm(String wOne, String wTwo, String xD, String yD) {
		File file = new File(wOne);
		BufferedReader br;
		FileReader f;
		try {
			if (file.exists()) {
				f = new FileReader(wOne);
				br = new BufferedReader(f);
				String line = "";
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					for (int row = 0; row < w1.length; row++) {
						w1[row][0] = 1.0;
						for (int col = 1; col < w1[row].length; col++) {
							w1[row][col] = Double.parseDouble(data[col]);
						}
					}
				}
			} else {
				System.out.println("Invalid file: " + wOne);
			}
			file = new File(wTwo);
			if (file.exists()) {
				f = new FileReader(wTwo);
				br = new BufferedReader(f);
				String line = "";
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					for (int row = 0; row < w2.length; row++) {
						w2[row][0] = 1.0;
						for (int col = 1; col < w2[row].length; col++) {
							w2[row][col] = Double.parseDouble(data[col]);
						}
					}
				}
			}
			file = new File(xD);
			if (file.exists()) {
				f = new FileReader(xD);
				br = new BufferedReader(f);
				String line = "";
				int row = 0;
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					x[row][0] = 1.0;
					for (int col = 1; col < x[row].length; col++) {
						x[row][col] = Double.parseDouble(data[col-1]);
					}
					row++;
				}
			}
			file = new File(yD);
			if (file.exists()) {
				f = new FileReader(yD);
				br = new BufferedReader(f);
				String line = "";
				while ((line = br.readLine()) != null) {
					for (int i = 0; i < y.length; i++) {
						y[Integer.parseInt(line) - 1][0] = 1;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static double[][] dropFirstColumn(double m[][]) {
		double[][] temp = new double[m.length][m[0].length - 1];

		for (int i = 0; i < temp.length; i++) {
			for (int k = 0; k < temp[i].length; k++) {
				temp[i][k] = m[i][k + 1];
			}
		}
		return temp;
	}

	public static double[][] transpose(double m[][]) {
		double[][] temp = new double[m[0].length][m.length];

		for (int i = 0; i < m[0].length; i++)
			for (int j = 0; j < m.length; j++)
				temp[i][j] = m[j][i];

		return temp;
	}

	public static double[][] multiply(double[][] A, double[][] B) {

		int rows1 = A.length;
		int cols1 = A[0].length;
		int rows2 = B.length;
		int cols2 = B[0].length;

		if (cols1 != rows2) {
			System.err.println("Error with multiplication!  Check the dimensions.");
			throw new IllegalArgumentException();
		}

		double[][] C = new double[rows1][cols2];
		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < cols2; j++) {
				C[i][j] = 0.00000;
			}
		}

		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < cols2; j++) {
				for (int k = 0; k < cols1; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return C;
	}

	public static void printDimensions(double[][] m) {
		String xdim = String.format(" Matrix dimensions:    %d x %d ", m.length, m[0].length);

		System.out.println(xdim);
	}

}
