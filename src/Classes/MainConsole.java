package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class MainConsole {
	
	/*
	 * current problem: OK button only changes the label text, when it's clicked again the old value pops up
	 * 
	 hex needs to be HEAVILY optimized - parallelize? only update 1 label when can?
	 hex progress bar = bytes in array / total size
	*/
	
	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\ermak\\Documents\\fileProgrammingTests\\birb.png";
		Metadater.printData(file);
	}
	
	
	
	public static void print(Object text) {
		System.out.println(text);
	}
	public static void print() {
		System.out.println();
	}
}
