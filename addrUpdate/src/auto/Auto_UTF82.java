package auto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import findDiff.*;

public class Auto_UTF82 {
	//public static String[] folder = {"10509","10510","10511","10512"};
	public static String[] folder = {"10507","10508","10509","10510","10511","10512"};
	//UTF-8 file
	public static String[] file = {"TGOS_A09007.CSV","TGOS_A09020.CSV","TGOS_A10004.CSV","TGOS_A10005.CSV","TGOS_A10007.CSV","TGOS_A10008.CSV","TGOS_A10009.CSV","TGOS_A10010.CSV","TGOS_A10013.CSV","TGOS_A10015.CSV","TGOS_A10016.CSV","TGOS_A10017.CSV","TGOS_A10018.CSV","TGOS_A10020.CSV","TGOS_A63000.CSV","TGOS_A65000.CSV","TGOS_A66000.CSV","TGOS_A67000.CSV"};
	//public static String[] file = {"TGOS_A10010.CSV"};
	public static void main(String[] args) {
		for(int i=0; i<folder.length; i++){
			if(i != folder.length-1){
				System.out.println(folder[i]);
				String f1 = folder[i]+"/";
				String f2 = folder[i+1]+"/";
				for(int k=0; k<file.length; k++){
					String f = "D:/105/"+folder[i]+"-"+folder[i+1]+" "+file[k].substring(6, 11);
					System.out.println(f);
					comp("D:/105/"+f1+file[k],"D:/105/"+f2+file[k],f);
				}
			}
		}
	}

	public static int comp(String csvFile1,String csvFile2,String f) {
		
		//String csvFile1 = "D:/1.txt";
		//String csvFile2 = "D:/2.txt";
		int cont = 0;
		
		ArrayList<Item> dataset1 = new ArrayList<Item>();
		ArrayList<Item> dataset2 = new ArrayList<Item>();
		ArrayList<Item> deleteset = new ArrayList<Item>();
		
		//ArrayList<String> addList = new ArrayList<String>();
		String str;
		try {
			File fileDir = new File(csvFile1);
	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
	        str = in.readLine();
	        while ((str = in.readLine()) != null) {
	        	String[] country = str.split(",");
	        	dataset1.add(new Item(country));
	        }
	        in.close();
		}catch (Exception e){
				e.printStackTrace();
		}
		try {
			File fileDir = new File(csvFile2);
	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
	        str = in.readLine();
	        while ((str = in.readLine()) != null) {
	        	String[] country = str.split(",");
	        	dataset2.add(new Item(country));
	        }
	        in.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		for(int i=0; i<dataset1.size(); i++){
			//System.out.println(cont++);
			Item s = dataset1.get(i);
			//System.out.println("S: "+s);
			boolean found = false;
			for(int j=0; j<dataset2.size(); j++){
				Item tmp = dataset2.get(j);
				//System.out.println("  j"+j+": "+tmp);
				if(s.equal(tmp)=="Equivalent"){
					//System.out.println(s+"-"+tmp);
					dataset1.remove(s);
					dataset2.remove(tmp); 
					found = true;
					i--;
					break;
				}
				
			}
			if(!found){
				//System.out.println("!found: "+s);
				deleteset.add(s);
				dataset1.remove(s);
				i--;
			}
			
		}
		
		
		
		
		
		print(f+" delete.txt", deleteset);
		print(f+" add.txt", dataset2);
		/*
		try{
		    PrintWriter writer = new PrintWriter("D:/delete.txt", "UTF-8");
		    for(String s : deleteList){
		    	writer.println(s);
		    }
		    writer.close();
		} catch (IOException e) {
		  e.printStackTrace();
		}
		try{
		    PrintWriter writer = new PrintWriter("D:/add.txt", "UTF-8");
		    for(String s : data2){
		    	writer.println(s);
		    }
		    writer.close();
		} catch (IOException e) {
		   e.printStackTrace();
		}*/
		return cont;
		
	}
	public static void print(String s, ArrayList<Item> data1) {
		try{
		    PrintWriter writer = new PrintWriter(s, "UTF-8");
		    for(Item tmp : data1){
		    	writer.println(tmp);
		    }
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
