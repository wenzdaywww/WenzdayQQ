package www.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginData {
	/**
	 * �����¼�����û���������
	 * @param Id
	 * @param pwd
	 * @param RememberPwd
	 * @param autoLink
	 * @param fileData
	 * @throws IOException
	 */
	public static void saveData(int Id,String pwd,Boolean RememberPwd,Boolean autoLink,String fileData) throws IOException{
		FileOutputStream fileOut=new FileOutputStream(fileData);
		DataOutputStream output=new DataOutputStream(fileOut);
		output.writeInt(Id);
		output.writeUTF(pwd);
		output.writeBoolean(RememberPwd);
		output.writeBoolean(autoLink);
		output.flush();
		output.close();
	}
	/**
	 * ��ȡ�û�ID
	 * @param fileData
	 * @return
	 * @throws IOException
	 */
	public static int getId(String fileData) throws IOException{
		FileInputStream fileIn=new FileInputStream(fileData);
		DataInputStream input=new DataInputStream(fileIn);
		int id=input.readInt();
		input.close();
		return id;
	}
	/**
	 * ��ȡ�û�����
	 * @param fileData
	 * @return
	 * @throws IOException
	 */
	public static String getPwd(String fileData) throws IOException{
		FileInputStream fileIn=new FileInputStream(fileData);
		DataInputStream input=new DataInputStream(fileIn);
		input.readInt();
		String pwd=input.readUTF();
		input.close();
		return pwd;
	}
	/**
	 * ��ȡ�û��Ƿ�ѡ�м�ס����
	 * @param fileData
	 * @return
	 * @throws IOException
	 */
	public static boolean isReberPwd(String fileData) throws IOException{
		FileInputStream fileIn=new FileInputStream(fileData);
		DataInputStream input=new DataInputStream(fileIn);
		input.readInt();
		input.readUTF();
		boolean isRemember=input.readBoolean();
		input.close();
		return isRemember;
	}
	/**
	 * ��ȡ�û��Ƿ�ѡ���Զ�����
	 * @param fileData
	 * @return
	 * @throws IOException
	 */
	public static boolean isAutoLink(String fileData) throws IOException{
		FileInputStream fileIn=new FileInputStream(fileData);
		DataInputStream input=new DataInputStream(fileIn);
		input.readInt();
		input.readUTF();
		input.readBoolean();
		boolean isAuto=input.readBoolean();
		input.close();
		return isAuto;
	}
	
}
