package findBug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import findDiff.Item;
import findDiff.matchType;

public class Mixbug {
	public static String addr = "D:/105/Item/bug2/";
	public static String[] folderFileTitle = {"10507-10508 ","10508-10509 ","10509-10510 ","10510-10511 ","10511-10512 "};
	public static String[] FileCSV = {"07,08,","08,09,","09,10,","10,11,","11,12,"};
	public static String[] file = {"TGOS_A10002.CSV","TGOS_A10005.CSV","TGOS_A10014.CSV","TGOS_A64000.CSV","TGOS_A68000.CSV"};
	//public static String[] file = {"TGOS_A09007.CSV","TGOS_A09020.CSV","TGOS_A10004.CSV","TGOS_A10007.CSV","TGOS_A10008.CSV","TGOS_A10009.CSV","TGOS_A100010.CSV","TGOS_A10013.CSV","TGOS_A10015.CSV","TGOS_A10016.CSV","TGOS_A10017.CSV","TGOS_A10018.CSV","TGOS_A10020.CSV","TGOS_A63000.CSV","TGOS_A65000.CSV","TGOS_A66000.CSV","TGOS_A67000.CSV"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = " bugList less5.txt";
		String str = null;
		try{
		    PrintWriter writer = new PrintWriter(addr+" bugList less5_ANSI.txt");
		    
		    for(int i=0; i<folderFileTitle.length; i++){
				for(int j=0; j<file.length; j++){
					String f = addr+folderFileTitle[i]+file[j]+s;
					System.out.println(f);
					
					try {
						File fileDir = new File(f);
				        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir)));
				        while ((str = in.readLine()) != null) {
				        	 writer.println(FileCSV[i]+str);
				        }
				        in.close();
					}catch (Exception e){
							e.printStackTrace();
					}
					
				}
			}
		    
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
