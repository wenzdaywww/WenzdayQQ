package www.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginData {
	/**
	 * 保存登录界面用户操作数据
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
	 * 获取用户ID
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
	 * 获取用户密码
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
	 * 获取用户是否选中记住密码
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
	 * 获取用户是否选中自动登入
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
