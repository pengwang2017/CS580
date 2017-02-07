import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WordDictionary {

	private List<WordDefinition> list = null;

	public WordDictionary() {
		this.list = new ArrayList<WordDefinition>();
	}

	public static WordDictionary FromFile(Path file) throws IOException {
		String[] lines = Utility.ReadAllLines(file);
		WordDictionary dict = new WordDictionary();
		int preStart = -1;
		for (int i = 0; i < lines.length; i++) {
			if (IsWordLine(lines[i])) {
				if (preStart == -1) {
					// this is the first word
					preStart = i;
				} else {
					// add the definition
					// from preStart+1 to i-1 is the definition
					String definition = Utility.JoinString(lines, preStart + 1, i - 1, System.lineSeparator());
					String word = lines[preStart];
					WordDefinition def = new WordDefinition(word, definition);
					dict.AddDefinition(def);
					// go next
					preStart = i;
				}
			}
		}
		return dict;

	}

	// Check if a line is a word line
	private static boolean IsWordLine(String line) {
		if (line == null || line.length() == 0) {
			return false;
		}
		for (int i = 0; i < line.length(); i++) {
			if (!IsWordChar(line.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private static boolean IsWordChar(char c) {
		return (c >= 'A' && c <= 'Z') || c == ' ' || c == '-';
	}

	public void AddDefinition(WordDefinition def) {
		this.list.add(def);
	}

	public WordDefinition GetDefinition(String word) {
		for (WordDefinition def : this.list) {
			if (def.GetWord().toUpperCase().equals(word.toUpperCase())) {
				return def;
			}
		}
		return null;
	}
}
