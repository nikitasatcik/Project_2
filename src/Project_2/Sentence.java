package Project_2;

import java.util.*;

public class Sentence implements Iterable<Lexema> {
    private List<Lexema> lexemas;
    private LinkedList<PunctuationSymbol> punctuations;

    public Sentence() {
        lexemas = new ArrayList<>();
        punctuations = new LinkedList<>();
    }

    public void addLexema(Lexema l) {
        lexemas.add(l);
    }

    public void readNext(Scanner scanner) {
        String currString;
        boolean endOfSentence = false;
        int wordLength;

        while (!endOfSentence) {
            if (scanner.hasNext()) {

                currString = scanner.next();
                wordLength = currString.length();

                // проверяем последний символ на пунктуацию из текущего токена сканера
                punctuations.push(PunctuationSymbol.createSymbol((currString.charAt(wordLength - 1))));
                if (punctuations.peek() == null) {
                    addLexema(new Word(currString));
                } else {
                    wordLength--;

                    if (punctuations.peek().isEndOfSentence()) {
                        endOfSentence = true;
                    }
                    for (int i = wordLength - 1; i >= 0; i--) {
                        punctuations.push(PunctuationSymbol.createSymbol(currString.charAt(i)));
                        if (punctuations.peek() == null) {
                            break;
                        } else {
                            wordLength--;
                        }
                    }
                    if (wordLength > 0) {
                        addLexema(new Word(currString.substring(0, wordLength)));
                    }
                    while (!punctuations.isEmpty()) {
                        addLexema(punctuations.pop());
                    }
                }
            } else {
                endOfSentence = true;
            }
        }
    }

    @Override
    public Iterator<Lexema> iterator() {
        return lexemas.iterator();
    }
}
