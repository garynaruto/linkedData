package findBug;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class classification {
	public static void main(String[] args) {
		String str = null;
		
		List<String> sList = new ArrayList<String>();
		List<Integer> intList = new ArrayList<Integer>();
		try {
			PrintWriter writer = new PrintWriter("D:/class less5csv.txt");
			File fileDir = new File("D:/bugList less5(no 09007).txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir)));
			while ((str = in.readLine()) != null){
				String[] tmp = str.split(",");
				boolean find = false;
				for(int i=0; i<sList.size();i++){
					String s = sList.get(i);
					if(s.equals(tmp[2])){
						find = true;
						intList.set(i, intList.get(i)+1);
						break;
					}
				}
				if(!find){
					sList.add(tmp[2]);
					intList.add(0);
				}
			}
			for(int i=0; i<sList.size();i++){
				writer.println(sList.get(i)+","+intList.get(i));
			}
			
			writer.flush();
			in.close();
			}catch (Exception e){
				e.printStackTrace();
			}
					
		}
	
}
