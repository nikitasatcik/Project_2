package Project_2;

import java.io.*;
import java.util.*;

/**
 * Создать программу обработки текста учебника по программированию
 * с использованием классов: Символ, Слово, Предложение, Знак препинания и др.
 * Во всех задачах с формированием текста заменять табуляции и последовательности пробелов одним пробелом.
 * Напечатать слова текста в алфавитном порядке по первой
 * букве. Слова, начинающиеся с новой буквы, печатать с красной строки.
 */


public class TextReader {
    private List<Sentence> textSentances;
    private List<Word> textWords;
    private TreeSet<Word> alpabeticalWords;

    public TextReader() {
        textSentances = new ArrayList<>();
        textWords = new ArrayList<>();
        alpabeticalWords = new TreeSet<>();
    }

    public void read(Scanner scanner) {
        Sentence s;
        while (scanner.hasNext()) {
            s = new Sentence();
            s.readNext(scanner);
            textSentances.add(s);
        }
        fill();
        print();
    }

    public void fill() {
        for (Sentence s : textSentances) {
            for (Lexema lexema : s) {
                if (lexema instanceof Word) {
                    textWords.add((Word) lexema);
                    alpabeticalWords.add((Word) lexema);
                }
            }
        }
    }

    public void print() {
        String currWord;
        for (Word w : textWords) {
            currWord = w.getWord();
            System.out.println(currWord);
        }
    }


    public void printAlfabetical(String filename) {
        BufferedWriter out;
        char currLetter = ' ';
        boolean newLetter = false;
        String currWord;
        try {
            out = new BufferedWriter(new FileWriter(filename));

            for (Word w : alpabeticalWords) {
                currWord = w.getWord();
                if (Character.toLowerCase(currWord.charAt(0)) != currLetter) {
                    newLetter = true;
                    currLetter = Character.toLowerCase(currWord.charAt(0));
                    out.write("\t");
                }
                out.write(currWord);
                if (newLetter) {
                    newLetter = false;
                }
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        TextReader t = new TextReader();
        t.read(new Scanner(new File("D:\\Dropbox\\Java\\src\\Project_2\\book.txt")));
        t.printAlfabetical("D:\\Dropbox\\Java\\src\\Project_2\\output.txt");
    }
}
