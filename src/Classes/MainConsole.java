package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

public class MainConsole {
	
	/*
	 Byte info:
	 hex value
	 signed 8bit int
	 unsigned 8bit int = signed & 255
	 binary value
	 */
	
	public static void main(String[] args) throws IOException {
		// C:/Users/ermak/Documents/fileProgrammingTests
		// 
		byte[] input;
		input = Hexer.getBinaryData("C:/Users/ermak/Documents/fileProgrammingTests/test.png");
		
		//Hexer.printByteArray(input);
		//Hexer.printHex(input, 8);
		//print();
		
		byte b = input[0];
		/*print(b); //signed
		print(Integer.toBinaryString(b));
		print(b & 255); //unsigned
		print(Integer.toBinaryString(b & 255));*/
		String[] reps = Hexer.getByteRepresentations(b);
		for (int i = 0; i < reps.length; i++) {
			print(reps[i]);
		}
		//print((byte) 137);
	}
	
	
	
	public static void print(Object text) {
		System.out.println(text);
	}
	public static void print() {
		System.out.println();
	}
}
