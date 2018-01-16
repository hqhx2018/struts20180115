package com.hqhx.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class CreateImage {

	private static Random r=new Random();
	private static String code;

	public String getCode() {
		return code;
	}

	public static void main(String[] args) {
		BufferedImage img=getImage();
		//����һ��������ֽ�����ͼƬ�����������
		OutputStream os=null;
		try {
			os=new FileOutputStream(new File("f:\\qq.jpg"));
			ImageIO.write(img, "JPEG", os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//������֤��ͼƬ
	public static BufferedImage getImage(){
		String str=getString();
		code=str;
		BufferedImage image=new BufferedImage(100, 40, BufferedImage.TYPE_INT_BGR);
		//��ȡһ�����Ի��Ƶ�2dͼƬ
		Graphics g=image.getGraphics();
		//������ɫΪ��ɫ
		g.setColor(Color.WHITE);
		//�Ȱ�ͼƬ���ɰ�ɫ��ɫ
		g.fillRect(0, 0, 100, 40);
		
		//д�ַ���
		for(int i=0;i<str.length();i++){
			g.setFont(new Font("Times New Roman", Font.BOLD, 25));
			//������ɫ
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawString(str.substring(i,i+1), 10+20*i, 25);
		}
		//��������
		for(int i=0;i<400;i++){
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawOval(r.nextInt(100), r.nextInt(40), 1, 1);
		}
		return image;
	}
	
	//������֤��
	public static String getString(){
		String str="";
		for(int i=0;i<4;i++){
			str+=getChar();
		}
		return str;
	}
	
	//�������һ���ַ�
	public static char getChar(){
		Random r=new Random();
		int i=r.nextInt(62);
		char c;
		if(i>=0&i<=9){
			c=(char)(i+48);
		}else if(i>=10&i<=36){
			c=(char)(i+55);
		}else{
			c=(char)(i+60);
		}
		return c;
	}
}
