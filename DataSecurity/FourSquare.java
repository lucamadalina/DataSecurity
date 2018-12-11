import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FourSquare {

    private String firstKeyword;
    private String secondKeyword;
    private static final String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    private static final char[][] ALPHABET_SQUARE = new char[5][5];

    public FourSquare() {
        populateAlphabetSquare();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the first keyword for the cipher:");
        this.firstKeyword = scanner.next();
        System.out.println("Please insert the second keyword for the cipher:");
        this.secondKeyword = scanner.next();
    }

    public String cleanPlainTextInput(String text) {
        String cleanText = text.replaceAll("\\s+", "").replace("Q", "").toUpperCase();

        for (int x = 0; x < cleanText.length(); x++) {
            int position = alphabet.indexOf(cleanText.charAt(x));

            if (position == -1) {
                cleanText = cleanText.replace(cleanText.charAt(x), ' ');
            }
        }
        return cleanText;
    }

    public String encryptPlaintTextInput(String plainText) {
        String text = cleanPlainTextInput(plainText);
        String[] pairs = split(text);
        char[][] keytable1 = generateKeyTable(firstKeyword);
        char[][] keytable2 = generateKeyTable(secondKeyword);
        char first, second;
        int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
        StringBuilder ciphertext = new StringBuilder();
        for (String s : pairs) {
            first = s.charAt(0);
            second = s.charAt(1);
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (ALPHABET_SQUARE[x][y] == first) {
                        xFirst = x;
                        yFirst = y;
                    } else if (ALPHABET_SQUARE[x][y] == second) {
                        xSecond = x;
                        ySecond = y;
                    }
                }
            }
            ciphertext.append(keytable1[xSecond][yFirst]).append(keytable2[xFirst][ySecond]);
        }
        return ciphertext.toString();
    }

    public String decryptCipherTextInput(String cipherText) {
        String[] pairs = split(cipherText);
        char[][] keytable1 = generateKeyTable(firstKeyword);
        char[][] keytable2 = generateKeyTable(secondKeyword);
        char first, second;
        int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
        StringBuilder plaintext = new StringBuilder();
        for (String s : pairs) {
            first = s.charAt(0);
            second = s.charAt(1);
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (keytable1[x][y] == first) {
                        xFirst = x;
                        yFirst = y;
                    } else if (keytable2[x][y] == second) {
                        xSecond = x;
                        ySecond = y;
                    }
                }
            }
            plaintext.append(ALPHABET_SQUARE[xSecond][yFirst]).append(ALPHABET_SQUARE[xFirst][ySecond]);
        }
        return plaintext.toString();
    }

    private char[][] generateKeyTable(String keyword) {
        keyword = cleanPlainTextInput(keyword);
        char[][] keyTable = new char[5][5];
        List<Character> used = new ArrayList<>();
        int x = 0, y = 0;
        for (char c : keyword.toCharArray()) {
            if (!used.contains(c)) {
                keyTable[x][y] = c;
                used.add(c);
                x++;
                if (x == 5) {
                    x = 0;
                    y++;
                    if (y == 5) {
                        return keyTable;
                    }
                }
            }
        }
        for (char c : alphabet.toCharArray()) {
            if (!used.contains(c)) {
                keyTable[x][y] = c;
                x++;
                if (x == 5) {
                    x = 0;
                    y++;
                    if (y == 5) {
                        return keyTable;
                    }
                }
            }
        }
        return keyTable;
    }

    private String[] split(String plaintext) {
        if (plaintext.length() % 2 != 0) {
            plaintext = plaintext + "X";
        }
        String[] pairs = new String[plaintext.length() / 2];
        int count = 0;
        for (int i = 0; i < (plaintext.length() / 2); i++) {
            pairs[i] = plaintext.substring(count, count + 2);
            count = count + 2;
        }
        return pairs;
    }

    private void populateAlphabetSquare() {
        int x = 0, y = 0;
        for (char c : alphabet.toCharArray()) {
            ALPHABET_SQUARE[x][y] = c;
            x++;
            if (x == 5) {
                x = 0;
                y++;
            }
        }
    }
}
