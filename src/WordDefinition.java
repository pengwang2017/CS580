
public class WordDefinition {

	private String word;
	private String definition;

	public WordDefinition(String word, String definition) {
		this.word = word;
		this.definition = definition;
	}

	public String GetWord() {
		return this.word;
	}

	public String GetDefinition() {
		return this.definition;
	}
}