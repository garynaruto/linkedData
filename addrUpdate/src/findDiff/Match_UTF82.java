package findDiff;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Match_UTF82 {
	public static String[] folderFileTitle = {"10507-10508 ","10508-10509 ","10509-10510 ","10510-10511 ","10511-10512 "};
	public static String[] date = {"105,07,105,08,","105,08,105,09,","105,09,105,10,","105,10,105,11,","105,11,105,12,"};
	public static String[] folder = {"07-08/","08-09/","09-10/","10-11/","11-12/"};
	//UTF-8 file
	public static String[] file = {"09007","09020","10004","10007","10008","10009","10010","10013","10015","10016","10017","10018","10020","63000","65000","66000","67000"};
	
	public static String address ="D:/105/new/";
	public static void main(String[] args) {
		for(int i=0;i<folder.length; i++){
			System.out.println("folder : "+folder[i]);
		    
			for(int j=0;j<file.length; j++){
				System.out.println(">>file : "+file[j]);
			    String f=address+folder[i]+folderFileTitle[i]+file[j];
				String out=address+"Match5/"+folderFileTitle[i]+file[j];
				//System.out.println(">>file : "+ out);
			    
				match(f,out,date[i]); 
			}
		}
	}
	public static void match(String f,String out,String date) {
		String addFile = f+" add.txt";
		String deleteFile = f+" delete.txt";
		ArrayList<Item> data1 = new ArrayList<Item>();
		ArrayList<Item> data2 = new ArrayList<Item>();
		ArrayList<matchType> alterList = new ArrayList<matchType>();
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
		//===sort
		//sortItem(data1);
		//sortItem(data2);
		
		ArrayList<Item> addList = new ArrayList<Item>(data1);
		ArrayList<Item> deleteList = new ArrayList<Item>(data2);
		
		try{
		    PrintWriter writer = new PrintWriter(out+" log.txt", "UTF-8");
		    //***star
		    
			writer.println("-------------------");
			for(int i=0; i<addList.size(); i++){
				Item tmp = addList.get(i);
				for(int j=0; j<deleteList.size(); j++){
					Item tmp2 = deleteList.get(j);
					String t = tmp.equal(tmp2);
					if(t !="No"){
						writer.println(tmp);
						writer.println(tmp2);
						writer.println(t);
						writer.println("-------------------");
						addList.remove(tmp);
						deleteList.remove(tmp2);
						alterList.add(new matchType(tmp,tmp2,t));
						i--;
						break;
					}
				}
			}
						
		    //***over
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		print(out+" addList.txt",addList,date);
		print(out+" deleteList.txt",deleteList,date);
		print(alterList,out+" alterList.txt",date);
		
		CSVtoJSON.toJson2(out+" addList.txt", out+" addList.json", true);
		CSVtoJSON.toJson2(out+" deleteList.txt", out+" deleteList.json", true);
		CSVtoJSON.alterListToJson(out+" alterList.txt", out+" alterList.json", true);
	}
	public static void sortItem(List<Item> data1){
		int index1 = 0, index2 = 0;
		
		if(data1.size()<2){
			return;
		}
		Item tmp1 = data1.get(0);
		for(int i=0; i<data1.size()-1; i++){
			Item tmp2 = data1.get(i+1);
			if(tmp1.cmpCoordinate(tmp2)){
				index2 = i+1;
			}
			else{
				//sort
				for(int j=index1; j<index2; j++){
					for(int k=j+1; k<index2; k++){
						Item item1 = data1.get(j);
						Item item2 = data1.get(k);
						if(item1.compareTo(item2)<0){
							data1.set(j, item2);
							data1.set(k, item1);
						}
					}
				}
				tmp1 = data1.get(i+1);
				index1 = i+1;
			}
		}
	}
	public static void print(String s, ArrayList<Item> data,String date) {
		try{
		    PrintWriter writer = new PrintWriter(s, "UTF-8");
		    writer.println("year1,month1,year2,month2,city1,Township1,village1,neighbor1,road1,Block1,lane1,alley1,number1,lon,lat");
		    for(Item tmp : data){
		    	writer.println(date+tmp.toStringLonLat());
		    }
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void print(ArrayList<matchType> data,String s,String date) {
		try{
		    PrintWriter writer = new PrintWriter(s, "UTF-8");
		    writer.println("year1,month1,year2,month2,city1,Township1,village1,neighbor1,road1,Block1,lane1,alley1,number1,lon1,lat1,singn,city2,Township2,village2,neighbor2,road2,Block2,lane2,alley2,number2,lon2,lat2,Type");
		    
		    for(matchType tmp : data){
		    	writer.println(date+tmp.toString());
		    }
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
