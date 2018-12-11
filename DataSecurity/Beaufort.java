public class Beaufort{

    private char[][] table;
    private char[] cols;
    private char[] rows;

  
    private char[][] gettable() {
        if (this.table == null) {
            this.createTable();
        }
        return this.table;
    }

    
    public char[] getCols() {
        if (this.cols == null) {
            this.cols = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        }
        return this.cols;
    }

    
    public char[] getRows() {
        if (this.rows == null) {
            this.rows = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
        }
        return this.rows;
    }

    private void createTable() {
        char[][] table = new char[26][26];
        table[0] = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();

        for (int i = 1; i < 26; i++) {
            for (int j = 1; j < 26; j++) {
                table[i][j - 1] = table[i - 1][j];
            }
            table[i][25] = table[i - 1][0];
        }

        this.table = table;
    }

    public String removeInvalidCharacters(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

   
    public String encrypt(String key, String text) {

        char[] textArray = text.toCharArray();
        char[] keyArray = key.toCharArray();
        StringBuilder cipherStringBuilder = new StringBuilder(text.length());

        int inputStringIterator = 0;
        while (inputStringIterator < textArray.length) { // Iterates over the whole string

            int columnIterator = 0;

            while (columnIterator < keyArray.length && inputStringIterator < textArray.length) {

                int rowIndex = this.getIndexOfColCharacter(textArray[inputStringIterator]);
                int colIndex = -1;
                if(rowIndex > -1) {
                    colIndex = this.getIndexOfRowCharacter(keyArray[columnIterator]);
                }

                if (rowIndex > -1 && colIndex > -1) {
                    cipherStringBuilder.append(this.gettable()[rowIndex][colIndex]);
                    inputStringIterator++;
                    columnIterator++;
                } else {
                    inputStringIterator++;
                }
            }
        }

        return cipherStringBuilder.toString();
    }

   
    public String decrypt(String key, String text) {

        char[] keyArray = key.toCharArray();
        char[] textArray = text.toCharArray();
        StringBuilder openStringBuilder = new StringBuilder(text.length());

        int inputStringIterator = 0;
        while (inputStringIterator < textArray.length) { // Iterates over the whole string

            int columnIterator = 0;

            while (columnIterator < keyArray.length && inputStringIterator < textArray.length) {

                int rowIndex = this.getIndexOfRowCharacter(keyArray[columnIterator]);
                int colIndex = -1;
                if (rowIndex >= 0) {
                    colIndex = this.getIndexOftableCharacter(textArray[inputStringIterator], rowIndex);
                }

                if(rowIndex != -1 && colIndex != -1) {
                    openStringBuilder.append(Character.toUpperCase(this.getCols()[colIndex]));
                    inputStringIterator++;
                    columnIterator++;
                } else {
                    inputStringIterator++;
                }
            }
        }

        return openStringBuilder.toString();
    }

   
    private int getIndexOfRowCharacter(char c) {
        for (int i = 0; i < 26; i++) {
            if (this.getRows()[i] == Character.toUpperCase(c)) {
                return i;
            }
        }
        return -1; // Fallback
    }

  
    private int getIndexOfColCharacter(char c) {
        for (int i = 0; i < 26; i++) {
            if (this.getCols()[i] == Character.toLowerCase(c)) {
                return i;
            }
        }
        return -1; // Fallback
    }

   
    private int getIndexOftableCharacter(char c, int row) {
        for (int i = 0; i < 26; i++) {
            if (this.gettable()[row][i] == Character.toUpperCase(c)) {
                return i;
            }
        }
        return -1; // Fallback
    }
}