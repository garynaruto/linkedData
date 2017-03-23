package findDiff;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CSVtoJSON {

	public static void main(String[] args) {

	}

	public static void alterListToJson(String csvFile, String out, boolean UTF8code) {
		// TODO Auto-generated method stub
		String[] lable = null;
		// String csvFile = "D:/alterList.txt";
		BufferedReader br = null;
		String line = "";
		try {
			PrintWriter writer = null;
			if(UTF8code){
				writer = new PrintWriter(out,"UTF-8");
				File fileDir = new File(csvFile);
				br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
		        
			}
			else{
				writer = new PrintWriter(out);
				br = new BufferedReader(new FileReader(csvFile));
			}
			
			if ((line = br.readLine()) != null) {
				lable = line.split(",");
			}
			if ((line = br.readLine()) != null) {
				writer.println("[");
			}
			else{
				writer.println("[]");
				writer.flush();
				writer.close();
				return;
			}
			
			while (true) {
				// use comma as separator
				String[] country = line.split(",");
				writer.println("  {");
				for (int i = 0; i < lable.length; i++) {
					writer.print("    ");
					if (i == 13 || i == 14 || i == 25 || i == 26) {
						writer.print("\"" + lable[i] + "\":" + country[i]);
					} else {
						writer.print("\"" + lable[i] + "\":\"" + country[i] + "\"");
					}
					if (i != 27) {
						writer.println(",");
					} else {
						writer.println();
					}
				}

				if ((line = br.readLine()) != null) {
					writer.println("  },");
				} else {
					writer.println("  }");
					break;
				}
			}
			writer.println("]");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void toJson2(String csvFile, String out, boolean UTF8code) {
		// TODO Auto-generated method stub
		String[] lable = null;
		//String csvFile = "D:/deleteList.txt";
		BufferedReader br = null;
		String line = "";
		try {
			PrintWriter writer = null;
			if(UTF8code){
				writer = new PrintWriter(out,"UTF-8");
				File fileDir = new File(csvFile);
				br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
		        
			}
			else{
				writer = new PrintWriter(out);
				br = new BufferedReader(new FileReader(csvFile));
			}
			
			if ((line = br.readLine()) != null) {
				lable = line.split(",");
			}
			if ((line = br.readLine()) != null) {
				writer.println("[");
			}
			else{
				writer.println("[]");
				writer.flush();
				writer.close();
				return;
			}
			while (true) {
				// use comma as separator
				String[] country = line.split(",");
				writer.println("  {");
				for (int i = 0; i < lable.length; i++) {
					writer.print("    ");
					if (i == 13 || i == 14 || i == 25 || i == 26) {
						writer.print("\"" + lable[i] + "\":" + country[i]);
					} else {
						writer.print("\"" + lable[i] + "\":\"" + country[i] + "\"");
					}
					if (i != 14) {
						writer.println(",");
					} else {
						writer.println();
					}
				}

				if ((line = br.readLine()) != null) {
					writer.println("  },");
				} else {
					writer.println("  }");
					break;
				}
			}
			writer.println("]");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
