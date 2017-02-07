import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		String rootFolder = "/Users/pengwang/documents/02-cs580/ps1";
		Path wordListFile = Paths.get(rootFolder, "words.txt");
		Path wordDictionaryFile = Paths.get(rootFolder, "dictionary.txt");
		Path testResultFile = Paths.get(rootFolder, "testResult.txt");

		WordList allWords = WordList.FromFile(wordListFile);
		WordDictionary dict = WordDictionary.FromFile(wordDictionaryFile);

		String input = GetInput();
		Utility u = new Utility();
		if (allWords.Exist(input)) {
			WordDefinition def = dict.GetDefinition(input);
			if (def != null) {
				System.out.println(def.GetDefinition());
				u.writeResult(testResultFile , def.GetWord());
				u.writeResult(testResultFile, "\n");
				u.writeResult(testResultFile, def.GetDefinition());
				u.writeResult(testResultFile, "\n");
				
			} else {
				u.writeResult(testResultFile, "Cannot find definition to word" + input + "\n");
				
			}
		} else {
			u.writeResult(testResultFile, "Cannot find word " + input + "\n");
		}
	}

	private static String GetInput() {
		String input = null;
		while (input == null) {
			System.out.print("Please input a word: ");
			input = new Scanner(System.in).nextLine();
		}
		return input;
	}

}
