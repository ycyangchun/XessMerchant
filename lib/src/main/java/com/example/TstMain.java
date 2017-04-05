package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TstMain {

	static int k = 0;

	public static String path = "D:/studio_workspace/XessMerchant/app/src/main/res/values/dimens2.xml";

	public static double offsetMdpi = 1.0;

	public static double offsetHdpi = 1.5;

	public static double offsetXhdpi = 2.0;

	public static int max = 1280;

	public static List<String> dimesList = new ArrayList<String>();

	public static final String preFilePath = "D:/studio_workspace/XessMerchant/app/src/main/res/values/dimens.xml";
	static StringBuffer outPut = new StringBuffer();
	public static final int degree = (int) offsetXhdpi;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		readFile();
	}

	private static void readFile() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
			String str = "";
			String str1 = "";
			fis = new FileInputStream(preFilePath);// FileInputStream
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
											// InputStreamReader的对象
			while ((str = br.readLine()) != null) {
				parseString(str);
			}
			Write();
			// 当读取的一行不为空时,把读到的str的值赋给str1
			System.out.println(str1);// 打印出str1
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
				// 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void parseString(String lineString) {
		if (!lineString.contains("<")) {
			System.out.println("11111 " + lineString);
			outPut.append(lineString + "\n");
		} else {
			int posSp = lineString.lastIndexOf("sp");
			int posDp = lineString.lastIndexOf("dp");
			int posDip = lineString.lastIndexOf("dip");
			int posNameTagEnd = lineString.indexOf(">");
			String newLineString = lineString;

			if (posSp > 0 && posSp > posNameTagEnd) {
				String sp = lineString.substring(lineString.indexOf(">") + 1, posSp);
				double newsp = Double.parseDouble(sp) / degree;
				String preString = lineString.substring(0, posNameTagEnd+1);
				String endString = lineString.substring(posSp, lineString.length());
				System.out.println("2222 " + preString + " " + newsp + "  " + endString + "  sp==" + sp);
				newLineString = preString + newsp + endString;
				// newLineString = lineString.replace(sp, newsp + "");
			} else if (posDp > 0 && posDp > posNameTagEnd) {
				String dp = lineString.substring(lineString.indexOf(">") + 1, posDp);
				double newdp = Double.parseDouble(dp) / degree;
				String preString = lineString.substring(0, posNameTagEnd+1);
				String endString = lineString.substring(posDp, lineString.length());
				
				newLineString = preString + newdp + endString;
				// newLineString = lineString.replace(dp, newdp + "");
			} else if (posDip > 0 && posDip > posNameTagEnd) {
				String dip = lineString.substring(lineString.indexOf(">") + 1, posDip);
				double newdip = Double.parseDouble(dip) / degree;
				String preString = lineString.substring(0, posNameTagEnd+1);
				String endString = lineString.substring(posDip, lineString.length());

				newLineString = preString + newdip + endString;
				// newLineString = lineString.replace(dip, newdip + "");
			}
			outPut.append(newLineString + "\n");

		}
	}

	public static boolean Write() {
		try {
			newTxt(); // 重新建立一个txt文件

			FileWriter fw = new FileWriter(path);// 建立FileWriter对象，并实例化fw
			// for (String str : list)
			fw.write(outPut + "\n"); // 写入文件内容;

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static void newTxt() throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
