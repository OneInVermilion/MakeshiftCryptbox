package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

public class MainConsole {

	
	/*
	Message length for reading
	OR
	encode message length as well
	then make it max 256 and code the length in 1 symbol at the start
	
	read
	only the involved bits
	from right to left in terms of each pixel's colors binvalue
	up until the length expires
	*/
	
	
	public static void main(String[] args) {
		// C:/Users/ermak/Documents/fileProgrammingTests
		// test.png
	}
	
	public static void print(Object text) {
		System.out.println(text);
	}
}
