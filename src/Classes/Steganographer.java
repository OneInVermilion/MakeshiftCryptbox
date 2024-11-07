package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Steganographer {
	
	private static int boolToInt(boolean a) {
		if (a) return 1;
		return 0;
	}
	
	private static String changeChar(String str, int pos, char c) {
		return str.substring(0, pos) + c + str.substring(pos + 1);
	}
	
	public static BufferedImage fileToImg(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
		int[] result = new int[] {bits_to_encode, available_bits};
		return result;
	}
	
	public static BufferedImage lsbHide(BufferedImage img, String message, int pixel_start, int pixel_step, int bits_per_val_enc, boolean r, boolean g, boolean b, boolean a) {
		String binary = ""; //this is the total message in binary form (by 8 bits for 1 ascii symbol)
		String bin; //current character in binary
		for (int i = 0; i < message.length(); i++) {
			bin = Integer.toBinaryString(message.charAt(i));
			while (bin.length() < 8) {bin = "0" + bin;}
			binary += bin;
		}
		
		int pix = pixel_start; //current pixel
		int x; int y; int colors; int R; int G; int B; int A;
		while (binary.length() > 0) { //while the message exists; it will be cut as we hide information
			x = pix % img.getWidth(); //get x coordinate from integer value of pixel start
			y = pix / img.getWidth(); //get y coordinate from integer value of pixel start
			colors = img.getRGB(x, y);
			/*
			R = (colors >> 8*3) & 255;
			G = (colors >> 8*2) & 255;
			B = (colors >> 8) & 255;
			A = colors & 255;
			String binval; //one channel color value in binary
			binval = Integer.toBinaryString(R);
			for (int i = 7*boolToInt(r); i >= 8-bits_per_val_enc; i--) { //encoding bits on the less significant side; if r == false - don't do it
				if (binary.length() == 0) {break;}
				binval = changeChar(binval, i, binary.charAt(0));
				binary = binary.substring(1);
			}
			R = Integer.parseInt(binval, 2);
			binval = Integer.toBinaryString(G);
			for (int i = 7*boolToInt(g); i >= 8-bits_per_val_enc; i--) {
				if (binary.length() == 0) {break;}
				binval = changeChar(binval, i, binary.charAt(0));
				binary = binary.substring(1);
			}
			G = Integer.parseInt(binval, 2);
			binval = Integer.toBinaryString(B);
			for (int i = 7*boolToInt(b); i >= 8-bits_per_val_enc; i--) {
				if (binary.length() == 0) {break;}
				binval = changeChar(binval, i, binary.charAt(0));
				binary = binary.substring(1);
			}
			B = Integer.parseInt(binval, 2);
			binval = Integer.toBinaryString(A);
			for (int i = 7*boolToInt(a); i >= 8-bits_per_val_enc; i--) {
				if (binary.length() == 0) {break;}
				binval = changeChar(binval, i, binary.charAt(0));
				binary = binary.substring(1);
			}
			A = Integer.parseInt(binval, 2);
			colors = Integer.parseInt(Integer.toBinaryString(R) + Integer.toBinaryString(G) + Integer.toBinaryString(B) + Integer.toBinaryString(A), 2);
			*/
			String colorsBin = Integer.toBinaryString(colors);
			//---------------------------------------------------------------------
			//for (i = 7; i < 32; i+=8):
			//	if i == 7 and r == false OR i == 15... COUNTIUE
			//	for (enc = 0; enc < bits_per_val_enc; enc++):
			//		changeChar(binary, i-enc, binary.charAt(0));
			//		binary = binary.substring(1);
			//		check if binary is ended --> BREAK
			//		see ternary operators java
			//		add more comments
			//---------------------------------------------------------------------
			img.setRGB(x, y, colors);
			pix++;
		}
		
		return img;
	}
}
