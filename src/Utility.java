import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public final class Utility {
	
	public static String[] ReadAllLines(Path filepath) throws IOException {
	//read file and save all content to a string list
	//	https://docs.oracle.com/javase/tutorial/essential/io/file.html#textfiles
		List<String> list = new ArrayList<>();
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(filepath, charset)) 
		//http://tutorials.jenkov.com/java-exception-handling/try-with-resources.html
		//try with resources can close resources automatically
		{	String line = null; 
			while ((line = reader.readLine()) != null) {list.add(line);}		
		} 
		String[] result = new String[list.size()];
		return list.toArray(result);
	}
	
	public void writeResult(Path filepath, String content) {
		Charset charset = Charset.forName("UTF-8");
		try(BufferedWriter writer = Files.newBufferedWriter(filepath, charset, StandardOpenOption.CREATE,StandardOpenOption.APPEND))
		{
			writer.write(content);
        } catch (IOException e) {
        	System.err.format("IOException: %s%n", e);
        } 
    }

	public static String JoinString(String[] ary, int start, int end, String delimeter) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i <= end; i++) {
			if (i == start) {
				sb.append(ary[i]);
			} else {
				sb.append(delimeter + ary[i]);
			}
		}
		return sb.toString();
	}

	public static boolean IsAllUpperCase(String s) {
		if (s == null) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLowerCase(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
