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

public class Auto_ANSI2 {
	public static String[] folder = {"10507","10508","10509","10510","10511","10512"};
	//ANSI file
	public static String[] file = {"TGOS_A10002.CSV","TGOS_A10005.CSV","TGOS_A10014.CSV","TGOS_A64000.CSV","TGOS_A68000.CSV"};
	public static void main(String[] args) {
		for(int i=0; i<folder.length; i++){
			if(i != folder.length-1){
				String f1 = folder[i]+"/";
				String f2 = folder[i+1]+"/";
				for(int k=0; k<file.length; k++){
					if(file[k].equals("TGOS_A64000.CSV") && (folder[i].equals("10509") || folder[i+1].equals("10509")) ){
						continue;
					}
					if(file[k].equals("TGOS_A10005.CSV") && folder[i].equals("09-10/")){
						continue;
					}
					String f = "D:/105/"+folder[i]+"-"+folder[i+1]+" "+file[k].substring(6, 11);
					comp("D:/105/"+f1+file[k],"D:/105/"+f2+file[k],f);
					
				}
			}
		}
	}

	public static int comp(String csvFile1,String csvFile2,String f) {
		
		int cont = 0;
		
		ArrayList<Item> dataset1 = new ArrayList<Item>();
		ArrayList<Item> dataset2 = new ArrayList<Item>();
		ArrayList<Item> deleteset = new ArrayList<Item>();
		
		//ArrayList<String> addList = new ArrayList<String>();
		String str;
		try {
			File fileDir = new File(csvFile1);
	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir)));
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
	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir)));
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
			System.out.println(cont++);
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
		
		return cont;
		
	}
	public static void print(String s, ArrayList<Item> data1) {
		try{
		    PrintWriter writer = new PrintWriter(s);
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
