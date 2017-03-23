package findBug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class threshold {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = " bugList.txt";
		String str = null;
		
		try {
			PrintWriter writer = new PrintWriter("D:/105/Item/bug/bug 5-30.txt");
			File fileDir = new File("D:/105/Item/bug/bug.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir)));
			while ((str = in.readLine()) != null) {
				String[] tmp = str.split(",");
				double delta = Double.parseDouble(tmp[tmp.length-1]);
				if(delta<=30.0){
					writer.println(str);
				}
			}
			writer.flush();
			in.close();
			}catch (Exception e){
				e.printStackTrace();
			}
					
		}
	
}
