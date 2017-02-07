import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class WordList {
	/* read the content of file line by line to get string list
	 * turn string list to string set
	*/
	Set<String> set = new HashSet<String>();

	public static WordList FromFile(Path file) throws IOException {
		String[] lines = Utility.ReadAllLines(file);
		WordList r = new WordList();
		for (String line : lines) {
			if (line == null || line.isEmpty()) {
				continue;
			} else {
				r.Add(line);
			}
		}
		return r;
	}

	public void Add(String word) {
		set.add(word);
	}

	public boolean Exist(String word) {
		return set.contains(word);
	}
}