package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

public class MainConsole {

	
	/*
	Message length for reading
	change 4 cycles of inserting colors into 2 cycles, one inside the other, working with the single string of all 4 channels 
	*/
	
	
	public static void main(String[] args) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:/Users/ermak/Documents/python idle saves/steganography/test.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//print(img);
		print(Steganographer.lsbHide(img, "abc", 0, 1, 1, true, true, true, false));
	}
	
	public static void print(Object text) {
		System.out.println(text);
	}
}
