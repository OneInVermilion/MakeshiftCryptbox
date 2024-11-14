package Classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;

public class Hexer {
	
	private static String formatPath(String path) {
		path = path.replaceAll("\\\\", "/");
		return path;
	}
	
	public static byte[] getBinaryData(String path) throws IOException {
		path = formatPath(path);
		FileInputStream input = null;
		input = new FileInputStream(new String(path));
		byte[] b = input.readAllBytes();
		input.close();
		
		return b;
	}
	
	public static void createFile(String path, byte[] data) throws IOException {
		//path = formatPath(path);
		FileOutputStream output = null;
		output = new FileOutputStream(new String(path));
		output.write(data);
		output.close();
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
		String[] reps = new String[5];
		int b_ = b & 255;
		
		reps[0] = Byte.toString(b); //int, signed
		reps[1] = Integer.toString(b_); //int, unsigned
		reps[2] = Integer.toBinaryString(b & 255); //binary, 8 bits
		reps[3] = Integer.toHexString(b_); if (reps[3].length() == 1) {reps[3] = "0" + reps[3];} //hex
		reps[4] = Character.toString((char) b); //ascii
		
		return reps;
	}
	
	public static byte getByteFromRepresentation (String s, int type) {
		switch (type) {
			case 0:
				return (byte) Integer.parseInt(s);
			case 1:
				return (byte) Integer.parseInt(s);
			case 2:
				return (byte) Integer.parseInt(s, 2);
			case 3:
				return (byte) Integer.parseInt(s, 16);
			case 4:
				return (byte) s.charAt(0);
		}
		return 0;
	}
	
}
