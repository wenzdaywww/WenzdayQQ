package www.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyUtil {

	public static void copyFile(String from,String to)throws FileNotFoundException,IOException{
		copyFile(new File(from), new File(to));
	}
	/**
	 * 复制文件
	 * @param from 要复制的文件夹或文件
	 * @param to	粘贴的文件夹
	 */
	private static void copyFile(File from,File to)throws FileNotFoundException,IOException{
		if (from.isDirectory()) {//要复制的from是文件夹
			to.mkdir();
			File fileList[] =from.listFiles();//获取文件夹中的文件数组fileList，包括文件夹和文件
			File newDir=new File(to.getAbsolutePath()+"/"+from.getName());//在要粘贴的文件夹中创建新文件夹newDir，newDir文件夹名和fromyiyang
			newDir.mkdir();
			for (int i = 0; i < fileList.length; i++) {	//递归判断文件数组fileList是文件夹或文件
				copyFile(fileList[i], newDir);
			}
		}else {//要复制的from是文件
			String newFileName=to.getAbsolutePath()+"/"+from.getName();
			File tofileList[] =to.listFiles();//获取文件夹中的文件数组fileList，包括文件夹和文件
			for (int i = 0; i < tofileList.length; i++) {	//递归判断文件数组fileList是文件夹或文件
				if (from.getName().equals(tofileList[i].getName())) {
					newFileName=to.getAbsolutePath()+"/副本-"+from.getName();
					break;
				}
			}
			FileInputStream input=new FileInputStream(from);
			FileOutputStream output=new FileOutputStream(newFileName);//粘贴的to文件夹绝对路径和复制后的文件名
			BufferedInputStream bufInput=new BufferedInputStream(input);
			BufferedOutputStream bufOutput=new BufferedOutputStream(output);
			byte temp[] = new byte[1024];
			while (true) {
				int date=bufInput.read(temp);
				if (date<0) {
					break;
				}
				bufOutput.write(temp, 0, date);
				bufOutput.flush();
			}
			bufInput.close();
			bufOutput.close();
		}
	}
}
