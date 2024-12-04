package Classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Metadater {
		
	private static String formatPath(String path) {
		path = path.replaceAll("\\\\", "/");
		return path;
	}
	
	public static String formatUser(String user) {
		int start = user.indexOf("\\") + 1;
		int end = user.length() - 1;
		for (int i = end; i >= 0; i--) {
			if (user.charAt(i) == '(') {end = i; break;}
		}
		return user.substring(start, end - 1);
	}
	
	public static String[] getData(String path) throws IOException {
		path = formatPath(path);
		Path fileP = Paths.get(path);
		String[] data = new String[10];
		BasicFileAttributes attr1 = Files.readAttributes(fileP, BasicFileAttributes.class);
		
		data[9] = fileTimeToString(FileTime.fromMillis(System.currentTimeMillis()));
		data[0] = fileTimeToString(attr1.creationTime());
		data[1] = fileTimeToString(attr1.lastModifiedTime());
		data[2] = fileTimeToString(attr1.lastAccessTime());
		data[3] = Files.getOwner(fileP).toString();
		data[4] = Long.toString(attr1.size());
		data[5] = Boolean.toString(attr1.isRegularFile());
		data[6] = Boolean.toString(attr1.isDirectory());
		data[7] = Boolean.toString(attr1.isSymbolicLink());
		data[8] = Boolean.toString(attr1.isOther());
		
		return data;
	}
	
	public static void createFile(String iPath, String oPath, String[] data) throws IOException {
		//System.out.println("Metadater.createFile() " + iPath + " " + oPath + " " + data[0]);
		iPath = formatPath(iPath);
		oPath = formatPath(oPath);
		
		FileInputStream input = null;
		input = new FileInputStream(new String(iPath));
		
		FileOutputStream output = null;
		output = new FileOutputStream(new String(oPath));
		output.write(input.readAllBytes());
		input.close();
		
		Files.setAttribute(Paths.get(oPath), "creationTime", stringToFileTime(data[0]));
		Files.setAttribute(Paths.get(oPath), "lastModifiedTime", stringToFileTime(data[1]));
		Files.setAttribute(Paths.get(oPath), "lastAccessTime", stringToFileTime(data[2]));
		UserPrincipalLookupService lookup = Paths.get(oPath).getFileSystem().getUserPrincipalLookupService();
		Files.setOwner(Paths.get(oPath), lookup.lookupPrincipalByName(data[3]));
		
		output.close();
	}
	
	public static void printData(String path) throws IOException {
		path = formatPath(path);
		Path fileP = Paths.get(path);
		BasicFileAttributes attr1 = Files.readAttributes(fileP, BasicFileAttributes.class);
		//System.out.println("\tBasic File Attributes");
		System.out.println("Current Time:\t\t" + fileTimeToString(FileTime.fromMillis(System.currentTimeMillis())));
		System.out.println("Created:\t\t" + fileTimeToString(attr1.creationTime()));
		System.out.println("Last Modified:\t\t" + fileTimeToString(attr1.lastModifiedTime()));
		System.out.println("Last Accessed:\t\t" + fileTimeToString(attr1.lastAccessTime()));
		System.out.println("Is Regular File:\t" + attr1.isRegularFile());
		System.out.println("Is Directory:\t\t" + attr1.isDirectory());
		System.out.println("Is Symbolic Link:\t" + attr1.isSymbolicLink());
		System.out.println("Is Other:\t\t" + attr1.isOther());
		System.out.println("Size:\t\t\t" + attr1.size() + " Bytes");
		/*
		//System.out.println("\tDOS File Attributes");
		DosFileAttributes attr2 = Files.readAttributes(fileP, DosFileAttributes.class);
	    System.out.println("Is Read Only:\t\t" + attr2.isReadOnly());
	    System.out.println("Is Hidden:\t\t" + attr2.isHidden());
	    System.out.println("Is Archive:\t\t" + attr2.isArchive());
	    System.out.println("Is System:\t\t" + attr2.isSystem());
	    //System.out.println("\tOther File Attributes");
		*/
	    System.out.println("Owner:\t\t\t" + Files.getOwner(fileP));
	}
	
	private static String fileTimeToString(FileTime ft) {
		LocalDateTime ldt = LocalDateTime.ofInstant(ft.toInstant(), ZoneId.systemDefault());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss.SSS");
		String dt = ldt.format(dtf);
		return dt;
	}
	private static FileTime stringToFileTime(String time) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss.SSS");
		LocalDateTime ldt = LocalDateTime.parse(time, dtf);
		FileTime ft = FileTime.fromMillis(ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		return ft;
	}
	
}
