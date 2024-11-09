package Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Hexer {
	
	public static byte[] getBinaryData(String path) throws IOException {
		FileInputStream input = null;
		try {
			input = new FileInputStream(new String(path));
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		/*
		String str = "";
		int b;
		while ((b = input.read()) != -1) {
			str += (char) b;
		}
		*/
		byte[] b = input.readAllBytes();
		input.close();
		
		return b;
	}
	
	public static void printHex(byte[] arr, int row_size) {
		for (int i = 0; i < arr.length; i++) {
			String hex = Integer.toHexString(arr[i] & 255);
			hex = hex.length() == 1 ? "0" + hex : hex;
			System.out.print(hex);
			System.out.print(" ");
			if ((i + 1) % row_size == 0) {System.out.println();}
		}
		System.out.println();
	}
	
	public static void printByteArray(byte[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(i + " : " + arr[i]);
		}
	}
	
	public static String[] getByteRepresentations(byte b) {
		String[] reps = new String[4];
		int b_ = b & 255;
		
		reps[0] = Byte.toString(b); //int, signed
		reps[1] = Integer.toString(b_); //int, unsigned
		reps[2] = Integer.toBinaryString(b & 255); //binary, 8 bits
		reps[3] = Integer.toHexString(b_); //hex
		
		return reps;
	}
	
}
