
import java.util.Scanner;

public class AutoKey{

    private String alphabet;
    private String key;

    public AutoKey() {
        this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the key for the cipher:");
        this.key = scanner.next();
        if (key.matches("[-+]?\\d*\\.?\\d+")) {
            key = "" + alphabet.charAt(Integer.parseInt(key));
        }
    }

    public String cleanPlainTextInput(String text) {
        text = text.replaceAll("\\s+", "");

        for (int x = 0; x < text.length(); x++) {
            int position = alphabet.indexOf(text.charAt(x));

            if (position == -1) {
                text = text.replace(text.charAt(x), ' ');
            }
        }
        return text;
    }

    public String encryptPlaintTextInput(String plainText) {
        String text = cleanPlainTextInput(plainText);
        int len = text.length();

        String subkey = key + text;
        subkey = subkey.substring(0, subkey.length() - key.length());

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < len; x++) {
            int get1 = alphabet.indexOf(text.charAt(x));
            int get2 = alphabet.indexOf(subkey.charAt(x));

            int total = (get1 + get2) % 26;

            sb.append(alphabet.charAt(total));
        }

        return sb.toString();
    }

    public String decryptCipherTextInput(String cipherText) {
        String text = cleanPlainTextInput(cipherText);
        int len = text.length();

        StringBuilder current = new StringBuilder(key);
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < len; x++) {
            int get1 = alphabet.indexOf(text.charAt(x));
            int get2 = alphabet.indexOf(current.charAt(x));

            int total = (get1 - get2) % 26;
            total = (total < 0) ? total + 26 : total;
            sb.append(alphabet.charAt(total));

            current.append(alphabet.charAt(total));
        }
        return sb.toString();
    }
}
