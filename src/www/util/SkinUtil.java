package www.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class SkinUtil {
	
//	public static void main(String[] args) {
//		try {
//			saveSkin("楷体", 255, 0, 0, 0,15,"skin3.png", "skin/skin1.skin");
//			saveSkin("宋体", 0, 255, 0, 1,15,"skin2.png", "skin/skin2.skin");
//			saveSkin("幼圆", 0, 0, 255, 2,15,"skin.png", "skin/skin3.skin");
//		} catch (IOException e) {
//		}
//	}
	/**
	 * 保存皮肤
	 * @param font
	 * @param fontColor
	 * @param image
	 * @param skinName
	 * @throws IOException
	 */
	public static void saveSkin(Font font,Color fontColor,String image,String skinName)throws IOException{
		saveSkin(font.getFontName(), fontColor.getRed(), fontColor.getGreen(), 
				fontColor.getBlue(), font.getStyle(), font.getSize(), image, skinName);
	}
	/**
	 * 保存皮肤
	 * @param font 字体名称
	 * @param r	RGB-R
	 * @param g RGB-G
	 * @param b RGB-B
	 * @param style 字体样式
	 * @param size 字体大小
	 * @param image	背景图片
	 * @param skinName 保存的皮肤文件名
	 * @throws IOException
	 */
	public static void saveSkin(String font,int r,int g,int b,int style,int size,String image,String skinName) throws IOException{
		int i=0;
		ArrayList<Byte> list=new ArrayList<Byte>();
		FileInputStream fileImage=new FileInputStream(image);
		BufferedInputStream input=new BufferedInputStream(fileImage);
		FileOutputStream fileOut=new FileOutputStream(skinName);
		DataOutputStream output=new DataOutputStream(fileOut);
		output.writeUTF(font);
		output.writeInt(r);
		output.writeInt(g);
		output.writeInt(b);
		output.writeInt(style);
		output.writeInt(size);
		while (true) {
			int date=input.read();
			if (date<0) {
				break;
			}
			i++;
			Byte integer=(byte) date;
			list.add(integer);
		}
		output.writeInt(i);
		for (int j = 0; j < list.size(); j++) {
			output.write(list.get(j));
		}
		input.close();
		output.close();
	}
	/**
	 * 获取字体
	 * @param skinFile
	 * @return
	 * @throws IOException
	 */
	public static Font getFont(String skinFile){
		Font font=null;
		try {
			String fontName=null;
			int style=0;
			int size=0;
			FileInputStream fileIn=new FileInputStream(skinFile);
			DataInputStream input=new DataInputStream(fileIn);
			fontName=input.readUTF();
			input.readInt();
			input.readInt();
			input.readInt();
			style=input.readInt();
			size=input.readInt();
			input.close();
			font=new Font(fontName, style, size);
		} catch (FileNotFoundException e) {
			System.out.println("找不到"+skinFile+"文件");
		} catch (IOException e) {
			System.out.println("获取字体失败");
		}
		return font;
	}
	/**
	 * 获取字体颜色
	 * @param skinFile
	 * @return
	 * @throws IOException
	 */
	public static Color getFontColor(String skinFile){
		Color color=null;
		try {
			int rgb[]=new int[3];
			FileInputStream fileIn=new FileInputStream(skinFile);
			DataInputStream input=new DataInputStream(fileIn);
			input.readUTF();
			rgb[0]=input.readInt();
			rgb[1]=input.readInt();
			rgb[2]=input.readInt();
			input.close();
			color=new Color(rgb[0], rgb[1], rgb[2]);
		} catch (FileNotFoundException e) {
			System.out.println("找不到"+skinFile+"文件");
		} catch (IOException e) {
			System.out.println("获取字体颜色失败");
		}
		return color;
	}
	/**
	 * 获取背景图片
	 * @return
	 * @throws IOException
	 */
	public static Image getImage(String skinFile){
		Image image=null;
		try {
			FileInputStream fileIn=new FileInputStream(skinFile);
			DataInputStream input=new DataInputStream(fileIn);
			input.readUTF();
			input.readInt();
			input.readInt();
			input.readInt();
			input.readInt();
			input.readInt();
			int i=input.readInt();
			byte temp[]=new byte[i];
			input.read(temp);
			input.close();
			image=new ImageIcon(temp).getImage();
		} catch (FileNotFoundException e) {
			System.out.println("找不到"+skinFile+"文件");
		} catch (IOException e) {
			System.out.println("获取背景图片失败");
		}
		return image;
	}
}
