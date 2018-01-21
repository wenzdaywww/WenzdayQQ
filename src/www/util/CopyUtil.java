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
	 * �����ļ�
	 * @param from Ҫ���Ƶ��ļ��л��ļ�
	 * @param to	ճ�����ļ���
	 */
	private static void copyFile(File from,File to)throws FileNotFoundException,IOException{
		if (from.isDirectory()) {//Ҫ���Ƶ�from���ļ���
			to.mkdir();
			File fileList[] =from.listFiles();//��ȡ�ļ����е��ļ�����fileList�������ļ��к��ļ�
			File newDir=new File(to.getAbsolutePath()+"/"+from.getName());//��Ҫճ�����ļ����д������ļ���newDir��newDir�ļ�������fromyiyang
			newDir.mkdir();
			for (int i = 0; i < fileList.length; i++) {	//�ݹ��ж��ļ�����fileList���ļ��л��ļ�
				copyFile(fileList[i], newDir);
			}
		}else {//Ҫ���Ƶ�from���ļ�
			String newFileName=to.getAbsolutePath()+"/"+from.getName();
			File tofileList[] =to.listFiles();//��ȡ�ļ����е��ļ�����fileList�������ļ��к��ļ�
			for (int i = 0; i < tofileList.length; i++) {	//�ݹ��ж��ļ�����fileList���ļ��л��ļ�
				if (from.getName().equals(tofileList[i].getName())) {
					newFileName=to.getAbsolutePath()+"/����-"+from.getName();
					break;
				}
			}
			FileInputStream input=new FileInputStream(from);
			FileOutputStream output=new FileOutputStream(newFileName);//ճ����to�ļ��о���·���͸��ƺ���ļ���
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
