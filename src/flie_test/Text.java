package flie_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class Text {
	
	static List<File> paths = new ArrayList<File>();
	static Set<String> suffixs = new HashSet<String>();
	
    public static void main(String[] args) {
        String fileName="D:\\安装包"+File.separator;
        File f=new File(fileName);
        printList(f);
        
        for (String string : suffixs) {
			System.out.println(string);
		}
        for (File file : paths) {
        	String suffix = f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase();
        	if(suffix.equals("")){
        		copyFile(file.getPath(), "D:\\安装包\\复制后\\图片\\" + file.getName(), true);
        	}else if(suffix.equals("")){
        		copyFile(file.getPath(), "D:\\安装包\\复制后\\视频\\" + file.getName(), true);
        	}else{
        		copyFile(file.getPath(), "D:\\安装包\\复制后\\未知格式\\" + file.getName(), true);
        	}
		}
    }
    public static void print(File f){
        if(f!=null){
            if(f.isDirectory()){
                File[] fileArray=f.listFiles();
                if(fileArray!=null){
                    for (int i = 0; i < fileArray.length; i++) {
                        //递归调用
                        print(fileArray[i]);
                    }
                }
            }
            else{
                System.out.println(f);
            }
        }
    }
    public static void printList(File f){
    	
        if(f!=null){
            if(f.isDirectory()){
                File[] fileArray=f.listFiles();
                if(fileArray!=null){
 
                	for (int i = 0; i < fileArray.length; i++) {
                        //递归调用
                		printList(fileArray[i]);
                    }
                }
            }
            else{
                //System.out.println(f.getPath());
            	String suffix = f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase();  
                paths.add(f);
                suffixs.add(suffix);
            }
        }
    }

    
    private static String MESSAGE = "";  
    
    /** 
     * 复制单个文件 
     *  
     * @param srcFileName 
     *            待复制的文件名 
     * @param descFileName 
     *            目标文件名 
     * @param overlay 
     *            如果目标文件存在，是否覆盖 
     * @return 如果复制成功返回true，否则返回false 
     */  
    public static boolean copyFile(String srcFileName, String destFileName,  
            boolean overlay) {  
        File srcFile = new File(srcFileName);  
  
        // 判断源文件是否存在  
        if (!srcFile.exists()) {  
            MESSAGE = "源文件：" + srcFileName + "不存在！";  
            JOptionPane.showMessageDialog(null, MESSAGE);  
            return false;  
        } else if (!srcFile.isFile()) {  
            MESSAGE = "复制文件失败，源文件：" + srcFileName + "不是一个文件！";  
            JOptionPane.showMessageDialog(null, MESSAGE);  
            return false;  
        }  
  
        // 判断目标文件是否存在  
        File destFile = new File(destFileName);  
        if (destFile.exists()) {  
            // 如果目标文件存在并允许覆盖  
            if (overlay) {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } else {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) {
                // 目标文件所在目录不存在
                if (!destFile.getParentFile().mkdirs()) {
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;
                }  
            }  
        }  
  
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
}
