import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PS4 {
	// ArrayList<ArrayList<Double>> w1 = new ArrayList<ArrayList<Double>>();
	double[][] w1 = new double[30][785];
	//ArrayList<ArrayList<Double>> w2 = new ArrayList<ArrayList<Double>>();
	double[][] w2 = new double[10][31];
	//ArrayList<ArrayList<Double>> x = new ArrayList<ArrayList<Double>>();
	double[][] x = new double[10000][785];
	//ArrayList<int[]> y = new ArrayList<int[]>();

	public static void main(String[] args) {

		if (args.length == 4) {
			PS4 p = new PS4();
			p.readEm(args[0], args[1], args[2], args[3]);

		} else {
			System.out.println("Invalid amount of arguments");
		}
	}

	public void print() {

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
					int count = 0;
					for (int row = 0; row < w1.length; row++) {
						w1[row][0] = 1.0;
						for (String item : data) {
							for (int col = 1; col < w1[row].length; col++) {
								w1[row][col] = Double.parseDouble(item);
							}
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
					ArrayList<Double> row = new ArrayList<Double>();
					int count = 0;
					for (String item : data) {
						if (count == 0) {
							row.add(1.0);
							count++;
						}
						row.add(Double.parseDouble(item));

					}
					w2.add(row);
				}
			}
			file = new File(xD);
			if (file.exists()) {
				f = new FileReader(xD);
				br = new BufferedReader(f);
				String line = "";
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					ArrayList<Double> row = new ArrayList<Double>();
					int count = 0;
					for (String item : data) {
						if (count == 0) {
							row.add(1.0);
							count++;
						}
						row.add(Double.parseDouble(item));

					}
					x.add(row);
				}
			}
			file = new File(yD);
			if (file.exists()) {
				f = new FileReader(yD);
				br = new BufferedReader(f);
				String line = "";
				while ((line = br.readLine()) != null) {
					int num = Integer.parseInt(line);
					int[] a = new int[10];
					a[num - 1] = 1;
					y.add(a);
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
