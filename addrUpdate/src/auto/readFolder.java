package auto;

import java.io.File;

public class readFolder {
	public static void main(String[] args) {
		getFileList("D:/105/10507");
	}

	public static void getFileList(String folderPath) {
		// String folderPath = "C:\\";//資料夾路徑
		try {
			java.io.File folder = new java.io.File(folderPath);
			
			File[] f = folder.listFiles();
			for(File tmp : f){
				System.out.print(tmp.toString());
				System.out.println(tmp.isDirectory());
			}
			
			String[] list = folder.list();
			for (int i = 0; i < list.length; i++) {
				System.out.println(list[i].substring(6, 11));
			}
		} catch (Exception e) {
			System.out.println("'" + folderPath + "'此資料夾不存在");
		}
		
	}

}
