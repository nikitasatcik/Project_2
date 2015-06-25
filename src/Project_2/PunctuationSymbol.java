package Project_2;

public class PunctuationSymbol extends Symbol{
    private boolean isEndOfSentence;

    public PunctuationSymbol(char c){
        super(c);
        if (c ==  '.' || c == '!' || c == '?') {
            isEndOfSentence = true;
        }
    }

    public static boolean isPunctuationSymbol(char c){
        return !(c >= 65 && c <= 90 || c >= 97 && c <= 122 || c >= 48 && c <= 57);
    }

    public static PunctuationSymbol createSymbol(char c){
        if (isPunctuationSymbol(c)){
            return new PunctuationSymbol(c);
        } else {
            return null;
        }
    }

    public boolean isEndOfSentence() {
        return isEndOfSentence;
    }
}
