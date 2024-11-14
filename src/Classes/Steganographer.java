package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import javax.imageio.ImageIO;

public class Steganographer {
	
	private static String formatPath(String path) {
		path = path.replaceAll("\\\\", "/");
		return path;
	}
	
	private static String changeChar(String str, int pos, char c) {
		return str.substring(0, pos) + c + str.substring(pos + 1);
	}
	
	public static BufferedImage fileToImg(String path) {
		path = formatPath(path);
		try {return ImageIO.read(new File(path));}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public static boolean imgToFile(BufferedImage img, String path, String format) {
		path = formatPath(path);
		try {ImageIO.write(img, format, new File(path)); return true;}
		catch (IOException e) {e.printStackTrace(); return false;}
	}
	
	public static void printPixels(BufferedImage img, int amount) {
		int x; int y;
		for (int i = 0; i < amount; i++) {
			x = i % img.getWidth();
			y = i / img.getWidth();
			String pixel = Integer.toBinaryString(img.getRGB(x, y));
			System.out.println("FEDCBA9876543210FEDCBA9876543210 " + i);
			System.out.println(pixel);
			System.out.println("       !       !       !       !");
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printMsgBin(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			String bin = Integer.toBinaryString(c);
			while (bin.length() < 8) {bin = "0" + bin;}
			System.out.println(c + " " + bin.substring(0, 4) + " " + bin.substring(4));
		}
	}
	
	public static int[] getMessageFitness(int width, int height, String message, int pixel_start, int pixel_step, int bits_per_val_enc, boolean r, boolean g, boolean b, boolean a) {
		int img_size = width * height;
		int bits_to_encode = message.length() * 8;
		int available_pixels = (img_size - pixel_start) / pixel_step;
		int available_bits = available_pixels * bits_per_val_enc;
		int available_channels = 0;
		if (r) {available_channels++;}
		if (g) {available_channels++;}
		if (b) {available_channels++;}
		if (a) {available_channels++;}
		available_bits *= available_channels;
		int[] result = new int[] {bits_to_encode, available_bits - 8}; //-8 for length byte
		return result;
	}
	
	public static int getSettingsCorrectness(int width, int height, String message, int pixel_start, int pixel_step, int bits_per_val_enc, boolean r, boolean g, boolean b, boolean a, String format) {
		int[] fitness;
		try {fitness = getMessageFitness(width, height, message, pixel_start, pixel_step, bits_per_val_enc, r, g, b, a);}
		catch (Exception e) {return 1;} //unknown error with getMessageFitness
		if (fitness[0] > fitness[1]) {return 2;} //message requires more than enough bits to encode in
		else if (message.length() > 256) {return 3;} //message exceeds 256 character limit
		else if (pixel_start > width * height || pixel_start < 0) {return 4;} //pixel start is outside image or less than 0
		else if (pixel_step < 1) {return 5;} //pixel step is below 1
		else if (bits_per_val_enc < 1 || bits_per_val_enc > 8) {return 6;} //bits per val enc is less than 1 or more than 8
		else if (!format.equals("png")) {return 7;} //format is not png
		
		return 0; //all good
	}
	
	public static String interpretCorrectness(int id) {
		switch (id) {
		case 1:
			return "Unknown Error, Please Check Correctness Of Settings";
		case 2:
			return "Message Requires More Than Available Bits To Encode";
		case 3:
			return "Message Must Not Exceed The 256 Character Limit";
		case 4:
			return "Pixel Start Is Outside Of Image Or Less Than Zero";
		case 5:
			return "Pixel Step Must Not Be Below 1";
		case 6:
			return "Bits Per Value Encoded Must Not Be Less Than One Or More Than Eight";
		case 7:
			return "Format Must Be png";
		}
		return "";
	}
	
	public static String insertSizeSymbol(String str) {
		return (char) str.length() + str;
	}
	
	public static int getMessageSize(BufferedImage img, int pixel_start, int pixel_step, int bits_per_val_enc, boolean r, boolean g, boolean b, boolean a) {
		String message = lsbRead(img, 1, pixel_start, pixel_step, bits_per_val_enc, r, g, b, a);
		return (int) message.charAt(0) + 1; //plus one for the symbol itself
	}
	
	public static BufferedImage lsbHide(BufferedImage img, String message, int pixel_start, int pixel_step, int bits_per_val_enc, boolean r, boolean g, boolean b, boolean a) {
		String binaryMsg = ""; //this is the total message in binary form (by 8 bits for 1 ascii symbol)
		String bin; //current character in binary
		for (int i = 0; i < message.length(); i++) {
			bin = Integer.toBinaryString(message.charAt(i));
			while (bin.length() < 8) {bin = "0" + bin;} //making it show the zeros at the front, until length 8
			binaryMsg += bin;
		}
		
		int pix = pixel_start; //current pixel
		int x; int y; int colors;
		while (binaryMsg.length() > 0) { //while the message exists; it will be cut as we hide information
			x = pix % img.getWidth(); //get x coordinate from integer value of pixel start
			y = pix / img.getWidth(); //get y coordinate from integer value of pixel start
			colors = img.getRGB(x, y);
			String colorsBin = Integer.toBinaryString(colors);
			for (int i = 7; i < 32; i += 8) { //i is the position on the binary string of ARGB values
				if ((i == 7 && a == false) || (i == 15 && r == false) || (i == 23 && g == false) || (i == 31 && b == false)) {continue;} //if we're on a place that we're not supposed to touch - skip this iteration
				if (binaryMsg.length() == 0) {break;}
				for (int enc = 0; enc < bits_per_val_enc; enc++) { //encode the necessary bits; bits_per_val_enc can't go above 8
					colorsBin = changeChar(colorsBin, i - enc, binaryMsg.charAt(0)); //change the bit
					binaryMsg = binaryMsg.substring(1); //remove the hidden bit from the strip of the total message
					if (binaryMsg.length() == 0) {break;} //break if the message is done
				}
			}
			colors = (int) Long.parseLong(colorsBin, 2); //sometimes the 32 bit string can't be converted to int, so we use this trick
			img.setRGB(x, y, colors);
			pix += pixel_step;
		}
		return img;
	}
	
	public static String lsbRead(BufferedImage img, int msg_length, int pixel_start, int pixel_step, int bits_per_val_enc, boolean r, boolean g, boolean b, boolean a) {
		msg_length *= 8; //in bits
		String binaryMsg = "";
		int pix = pixel_start; //current pixel + 1 for message size symbol
		int x; int y; int colors;
		while (binaryMsg.length() < msg_length) { //cycle from the lsbHide function with minor changes for reading, not writing
			x = pix % img.getWidth(); //get x coordinate from integer value of pixel start
			y = pix / img.getWidth(); //get y coordinate from integer value of pixel start
			colors = img.getRGB(x, y);
			String colorsBin = Integer.toBinaryString(colors);
			for (int i = 7; i < 32; i += 8) {
				if ((i == 7 && a == false) || (i == 15 && r == false) || (i == 23 && g == false) || (i == 31 && b == false)) {continue;}
				if (binaryMsg.length() == msg_length) {break;}
				for (int enc = 0; enc < bits_per_val_enc; enc++) { //encode the necessary bits; bits_per_val_enc can't go above 8
					binaryMsg += colorsBin.charAt(i - enc); //change the bit
					if (binaryMsg.length() == msg_length) {break;} //break if the message is done
				}
			}
			pix += pixel_step;
		
		}
		String message = "";
		for (int i = 0; i < binaryMsg.length(); i += 8) {
			char c = (char) Integer.parseInt(binaryMsg.substring(i, i + 8), 2);
			message += c;
		}
		return message;
	}
}
