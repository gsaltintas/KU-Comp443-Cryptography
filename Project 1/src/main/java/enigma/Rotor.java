package enigma;

public class Rotor {
    private char[] key;
    private char firstLetterInOriginalKey;
    private char[] originalKey;
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private boolean turnedOneTime = false;
    private boolean unclickedOnce = false;

    public Rotor(String key) {
        this.key = key.toCharArray();
        this.firstLetterInOriginalKey = key.charAt(0);
        this.originalKey = this.key;
    }

    public char encryptLetter(char letter) {
        char cipherLetter = 'a';
        try {
            int ind = alphabet.indexOf(letter);
            cipherLetter = key[ind];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(letter + " " + alphabet);
        }
        return cipherLetter;
    }

    public char decryptLetter(char cipherLetter) {
        int ind = getIndex(key, cipherLetter);
        char plainLetter = alphabet.charAt(ind);

        return plainLetter;
    }

    public void click() {
        char[] tmp = new char[key.length];
        for (int i = 1; i < key.length; i++) {
            tmp[i - 1] = key[i];
        }
        tmp[key.length - 1] = key[0];
        this.key = tmp;

        if (tmp[0] == firstLetterInOriginalKey) {
            this.turnedOneTime = true;
        } else {
            this.turnedOneTime = false;
        }
    }

    private int getIndex(char[] arr, char letter) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == letter) {
                return i;
            }
        }
        return -1;
    }

    public boolean returnedOneTime() {
        return turnedOneTime;
    }


    protected void resetKeys() {
        this.key = originalKey;
        turnedOneTime = false;
    }

    public void setTurnedOneTime(boolean bool) {

        this.turnedOneTime = bool;
    }

    protected String getKey() {
        return key.toString();
    }
}
