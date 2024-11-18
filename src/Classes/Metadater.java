package Classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;

public class Metadater {
		
	private static String formatPath(String path) {
		path = path.replaceAll("\\\\", "/");
		return path;
	}
	
	public static void readData(String path) throws IOException {
		Path fileP = Paths.get(path);
		BasicFileAttributes attr1 = Files.readAttributes(fileP, BasicFileAttributes.class);
		//System.out.println("\tBasic File Attributes");
		System.out.println("Current Time:\t\t" + FileTime.fromMillis(System.currentTimeMillis()));
		System.out.println("Created:\t\t" + attr1.creationTime());
		System.out.println("Last Modified:\t\t" + attr1.lastModifiedTime());
		System.out.println("Last Accessed:\t\t" + attr1.lastAccessTime());
		System.out.println("Is Regular File:\t" + attr1.isRegularFile());
		System.out.println("Is Directory:\t\t" + attr1.isDirectory());
		System.out.println("Is Other:\t\t" + attr1.isOther());
		System.out.println("Is Symbolic Link:\t" + attr1.isSymbolicLink());
		System.out.println("Size:\t\t\t" + attr1.size() + " Bytes");
		System.out.println("Is Other:\t\t" + attr1.isOther());
		//System.out.println("\tDOS File Attributes");
		DosFileAttributes attr2 = Files.readAttributes(fileP, DosFileAttributes.class);
	    System.out.println("Is Read Only:\t\t" + attr2.isReadOnly());
	    System.out.println("Is Hidden:\t\t" + attr2.isHidden());
	    System.out.println("Is Archive:\t\t" + attr2.isArchive());
	    System.out.println("Is System:\t\t" + attr2.isSystem());
	    //System.out.println("\tOther File Attributes");
	    System.out.println("Owner:\t\t\t" + Files.getOwner(fileP));
	}
	
}
