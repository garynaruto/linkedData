package findBug;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import findDiff.*;

public class FindBug_UTF8 {
	public static String[] folderFileTitle = {"10507-10508 ","10508-10509 ","10509-10510 ","10510-10511 ","10511-10512 "};
	public static String[] folder = {"07-08/","08-09/","09-10/","10-11/","11-12/"};
	//UTF-8 file
	public static String[] file = {"TGOS_A09007.CSV","TGOS_A09020.CSV","TGOS_A10004.CSV","TGOS_A10007.CSV","TGOS_A10008.CSV","TGOS_A10009.CSV","TGOS_A100010.CSV","TGOS_A10013.CSV","TGOS_A10015.CSV","TGOS_A10016.CSV","TGOS_A10017.CSV","TGOS_A10018.CSV","TGOS_A10020.CSV","TGOS_A63000.CSV","TGOS_A65000.CSV","TGOS_A66000.CSV","TGOS_A67000.CSV"};
	public static String address ="D:/105/Item/";
	public static void main(String[] args) {
		for(int i=0;i<folder.length; i++){
			System.out.println("folder : "+folder[i]);
		    
			for(int j=0;j<file.length; j++){
				System.out.println(">>file : "+file[j]);
			    
				String f=address+"105 "+folder[i]+folderFileTitle[i]+file[j];
				String out=address+folderFileTitle[i]+file[j];
				match(f,out); 
			}
		}
	}
	public static void match(String f,String out) {
		String addFile = f+" add.txt";
		String deleteFile = f+" delete.txt";
		ArrayList<Item> data1 = new ArrayList<Item>();
		ArrayList<Item> data2 = new ArrayList<Item>();
		//ArrayList<matchType> alterList = new ArrayList<matchType>();
		String str;
		try {
			File fileDir = new File(addFile);
	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
	        while ((str = in.readLine()) != null) {
	        	String[] country = str.split(",");
	        	data1.add(new Item(country));
	        }
	        in.close();
		}catch (Exception e){
				e.printStackTrace();
		}
		try {
			File fileDir = new File(deleteFile);
	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
	        while ((str = in.readLine()) != null) {
	        	String[] country = str.split(",");
	        	data2.add(new Item(country));
	        }
	        in.close();
		}catch (Exception e){
				e.printStackTrace();
		}
		ArrayList<Item> addList = new ArrayList<Item>(data1);
		ArrayList<Item> deleteList = new ArrayList<Item>(data2);
		ArrayList<matchType> bugList = new ArrayList<matchType>();
		
		try{
		    //PrintWriter writer = new PrintWriter(out+" log.txt", "UTF-8");
		    //***star
		    
			//writer.println("-------------------");
			for(int i=0; i<data1.size(); i++){
				Item tmp = data1.get(i);
				for(int j=0; j<data2.size(); j++){
					Item tmp2 = data2.get(j);
					String t = tmp.equal(tmp2);
					if(!t.equals("No")){
						addList.remove(tmp);
						deleteList.remove(tmp2);
						if(t.equals("addr")){
							double delta =Math.sqrt(Math.abs(tmp.TWD97x-tmp2.TWD97x) + Math.abs(tmp.TWD97y-tmp2.TWD97y));
							if(delta>0 && delta<5){
								matchType m = new matchType(tmp,tmp2,t);
								m.Delta = delta;
								bugList.add(m);
							}
						}
						
						//alterList.add(new matchType(tmp,tmp2,t));
					}
				}
			}
			/*
			writer.println("addList----------------------");
		    for(Item i : addList){
		    	writer.println(i);
			}
		    writer.println("deleteList-------------------");
			for(Item i : deleteList){
				writer.println(i);
			}*/
			
		    //***over
		    //writer.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//print(out+" addList.txt",addList);
		//print(out+" deleteList.txt",deleteList);
		printbug(bugList,out+" bugList less5.txt");
	}
	public static void print(String s, ArrayList<Item> data) {
		try{
		    PrintWriter writer = new PrintWriter(s, "UTF-8");
		    for(Item tmp : data){
		    	writer.println(tmp.toString());
		    }
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void print(ArrayList<matchType> data,String s) {
		try{
		    PrintWriter writer = new PrintWriter(s, "UTF-8");
		    for(matchType tmp : data){
		    	writer.println(tmp.toString());
		    }
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void printbug(ArrayList<matchType> data,String s) {
		try{
		    PrintWriter writer = new PrintWriter(s, "UTF-8");
		    for(matchType tmp : data){
		    	writer.print(tmp.addItem.toString()+",");
		    	writer.println(String.format("%.2f", tmp.Delta));
		    	
		    }
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
