package enigma;

import java.util.ArrayList;
import java.util.List;

public class Bombe {
    private String leftKey;
    private String middleKey;
    private String rightKey;
    private String leftKeyOriginal;
    private String middleKeyOriginal;
    private String rightKeyOriginal;
    private Enigma enigma;

    public Bombe(String key1, String key2, String key3) {

        this.leftKey = key1;
        this.middleKey = key2;
        this.rightKey = key3;

        this.leftKeyOriginal = key1;
        this.middleKeyOriginal = key2;
        this.rightKeyOriginal = key3;

        this.enigma = new Enigma(key1, key2, key3);
    }

    public List<String> crack(String ciphertext) {

        List<String> possibleDecryptions = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < 6; i++) {       // try all 3! combinations

            switchRotorOrder(i);
            for (int first = 0; first < 26; first++) {
                rightKey = shiftKey(rightKey);
                for (int second = 0; second < 26; second++) {
                    middleKey = shiftKey(middleKey);
                    for (int third = 0; third < 26; third++) {
                        leftKey = shiftKey(leftKey);
                        enigma = new Enigma(leftKey, middleKey, rightKey);
//                        enigma.resetRotorKeys();
                        String plaintext = enigma.decrypt(ciphertext);
                        if (plaintext.contains("MINE") && plaintext.contains("ONE")) {
                            possibleDecryptions.add(plaintext);
                        }
                    }
                }
            }
        }
        return possibleDecryptions;
    }

    private void switchRotorOrder(int i) {
        switch (i) {
            case 1:
                leftKey = leftKeyOriginal;
                middleKey = rightKeyOriginal;
                rightKey = middleKeyOriginal;
            case 2:
                leftKey = middleKeyOriginal;
                middleKey = leftKeyOriginal;
                rightKey = rightKeyOriginal;
            case 3:
                leftKey = middleKeyOriginal;
                middleKey = rightKeyOriginal;
                rightKey = leftKeyOriginal;
            case 4:
                leftKey = rightKeyOriginal;
                middleKey = middleKeyOriginal;
                rightKey = leftKeyOriginal;
            case 5:
                leftKey = rightKeyOriginal;
                middleKey = leftKeyOriginal;
                rightKey = middleKeyOriginal;
        }
    }


    private String shiftKey(String key) {
        return key.substring(1) + key.charAt(0);
    }

    public void resetBombe() {
        leftKey = leftKeyOriginal;
        middleKey = middleKeyOriginal;
        rightKey = rightKeyOriginal;
    }

}
