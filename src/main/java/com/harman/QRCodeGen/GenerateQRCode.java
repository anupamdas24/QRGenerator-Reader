package com.harman.QRCodeGen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class GenerateQRCode {

	/*
	public static void main(String[] args) throws WriterException, IOException {
		String Name;
		String Address;
		String WardNumber;

		Scanner sc= new Scanner(System.in);
		StringBuilder strBuild=new StringBuilder();

		Name=sc.next();
		System.out.print("Name :"+Name);
		System.out.println("\n");
		strBuild.append(Name);

		Address=sc.nextLine();
		System.out.print("Address :"+Address);
		System.out.println("\n");
		strBuild.append(Address);

		WardNumber=sc.next();
		System.out.print("WardNumber :"+WardNumber);
		System.out.println("\n");
		strBuild.append(WardNumber);

		String qrCodeText=strBuild.toString();

		final String filePath = "./MyQRCode.png";
		int size=125;
		String fileType="png";
		File qrFile=new File(filePath);

		createQRImage(qrFile,qrCodeText,size,fileType);

	}
	*/

	public static void main(String[] args) throws WriterException, IOException {
		Scanner sc= new Scanner(System.in);
		StringBuilder strBuild=new StringBuilder();

		System.out.println("Please enter the below details");


		System.out.println("Name :"+strBuild.append(sc.nextLine()));
		strBuild.append(" ");
		System.out.println("Address :"+strBuild.append(sc.nextLine()));
		strBuild.append(" ");
		System.out.println("WardNumber :"+strBuild.append(sc.nextLine()));

		String qrCodeText=strBuild.toString();

		final String filePath = "./MyQRCode.png";
		int size=125;
		String fileType="png";
		File qrFile=new File(filePath);

		createQRImage(qrFile,qrCodeText,size,fileType);

	}



	private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType) throws WriterException, IOException {

		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter=new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);

		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);

	}

}
