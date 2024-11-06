package Classes;

import java.util.ArrayList;

public class Cipherer {
	
	private final static String base64table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"; //for sextets
	
	private static int getAlphabetID(char chr) {
		chr = Character.toUpperCase(chr);
		int id = chr - 65;
		if (id < 0 || id > 25) {return -1;}
		return id;
	}
	
	private static char getLetterFromID(int id, boolean capital) {
		if (getAlphabetID((char) (id + 65)) == -1) {return (char) (id);}
		char c = (char) (id + 65);
		if (!capital) {c = Character.toLowerCase(c);}
		return c;
	}
	
	private static char CeasarChar(char chr, int key /*key must be normalized*/, boolean bounded /*whether to stay bounded in letters or not*/) {
		boolean capital = Character.isUpperCase(chr);
		if (!bounded) {return (char) ((int) chr + key);}
		else if (getAlphabetID(chr) == -1) {return chr;}
		chr = (char) ((int) chr + key);
		if (capital) {
			if ((int) chr > 90) {chr -= 26;}
			else if ((int) chr < 65) {chr += 26;}
		}
		else {
			if ((int) chr > 122) {chr -= 26;}
			else if ((int) chr < 97) {chr += 26;}
		}
		return chr;
	}
	
	public static boolean isANumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean CheckMapValidity(String map) {
		if (map.length() != 26) {return false;}
		map = map.toUpperCase();
		for (int i = 65; i <= 90; i++) {
			if (map.indexOf((char) i) == -1) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean CheckSymbolsValidity(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (base64table.indexOf(input.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	
	public static String Ceasar(String input, int key) {
		String output = input;
		key = Math.floorMod(key, 26);
		for (int i = 0; i < output.length(); i++) {
			output = output.substring(0, i) + CeasarChar(output.charAt(i), key, true) + output.substring(i + 1);
		}
		return output;
	}
	
	public static String Vigenere(String input, String key, boolean encode) {
		int enc_dec;
		if (encode) {enc_dec = 1;}
		else {enc_dec = -1;}
		String output = input;
		for (int i = 0; i < output.length(); i++) {
			output = output.substring(0, i) + CeasarChar(output.charAt(i), (getAlphabetID(key.charAt(i))) * enc_dec, true) + output.substring(i + 1);
		}
		return output;
	}
	
	public static String Keymap(String input, String keymap, boolean encode) {
		String output = input;
		String keymapL;
		if (encode) {keymapL = "abcdefghijklmnopqrstuvwxyz" + keymap.toLowerCase();}
		else {keymapL = keymap.toLowerCase() + "abcdefghijklmnopqrstuvwxyz";}
		String keymapU = keymapL.toUpperCase();
		String keymapCurrent;
		for (int i = 0; i < output.length(); i++) {
			if (Character.isUpperCase(output.charAt(i))) {keymapCurrent = keymapU;}
			else {keymapCurrent = keymapL;}
			if (getAlphabetID(output.charAt(i)) != -1) {
				output = output.substring(0, i) + keymapCurrent.charAt(keymapCurrent.indexOf(output.charAt(i)) + 26) + output.substring(i + 1);
			}
		}
		return output;
	}
	
	public static String Vernam(String input, String key) {
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			int i_id = base64table.indexOf(input.charAt(i));
			int k_id = base64table.indexOf(key.charAt(i));
			output += base64table.charAt(i_id ^ k_id);
		}
		return output;
	}
	
	public static String Base64Enc(String input) {
		String binary = "";
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			String add = Integer.toBinaryString((int) input.charAt(i));
			while (add.length() % 8 != 0) {add = "0" + add;}
			binary += add;
		}
		while (binary.length() % 6 != 0) {binary += "0";}
		int pad = (binary.length() % 8) / 2;
		while (binary.length() >= 6) {
			String sect = (String) binary.subSequence(0, 6);
			int id = Integer.parseInt(sect, 2);
			output += base64table.charAt(id);
			binary = (String) binary.subSequence(6, binary.length());
		}
		for (int i = 0; i < pad; i++) {
			output += "=";
		}
		return output;
	}
	
	public static String Base64Dec(String input) {
		String binary = "";
		String output = "";
		int pad = 0;
		for (int i = 0; i < input.length(); i++) {
			char chr = input.charAt(i);
			if (chr != '=') {
				int id = base64table.indexOf(chr);
				String bin = Integer.toBinaryString(id);
				while (bin.length() < 6) {bin = "0" + bin;}
				binary += bin;
			}
			else {pad++;}
		}
		for (int i = 0; i < pad; i++) {
			binary = (String) binary.subSequence(0, binary.length() - 2);
		}
		for (int i = 0; i < binary.length(); i+=8) {
			String bin = binary.substring(i, i+8);
			output += (char) Integer.parseInt(bin, 2);
		}
		return output;
	}

}
