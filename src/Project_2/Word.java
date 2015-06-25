package Project_2;

public class Word implements Lexema, Comparable<Word> {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(Word word) {
        return this.word.compareToIgnoreCase(word.getWord());
    }
}
