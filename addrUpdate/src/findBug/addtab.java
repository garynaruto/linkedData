package findBug;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import findDiff.Item;
import findDiff.TMToLatLon;

public class addtab {
	public static void main(String[] args) {
		String s = " bugList.txt";
		String str = null;
		String out = "[";
		String out2 = "Lon,Lat\n";
		try {
			PrintWriter writer = new PrintWriter("D:/bug 5-30.csv");
			File fileDir = new File("D:/bug 5-30.txt");
	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
	        while ((str = in.readLine()) != null) {
	        	String[] country = str.split(",");
	        	TMToLatLon t = new TMToLatLon();
	        	double[] ans = t.convert(Double.parseDouble(country[11]),Double.parseDouble(country[12]));
	        	out +="["+ans[0]+","+ans[1]+"],";
	        	if(ans[1]<100.0){
	        		out2 +=ans[0]+","+ans[1]+ "\n";
	        	}
	        }
	        in.close();
	        writer.println(out2);
	        writer.flush();
	        writer.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		out +="]";
		System.out.println(out2);
		
	}
}
