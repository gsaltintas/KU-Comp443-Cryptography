package enigma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnigmaBreaker {
    private String defaultKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String leftKey;
    private String middleKey;
    private String rightKey;
    private Bombe bombe;

    public EnigmaBreaker(String leftKey, String middleKey, String rightKey) {

        this.leftKey = leftKey;
        this.middleKey = middleKey;
        this.rightKey = rightKey;

        this.bombe = new Bombe(leftKey, middleKey, rightKey);
    }

    public EnigmaBreaker() {

        this.leftKey = defaultKey;
        this.middleKey = defaultKey;
        this.rightKey = defaultKey;

    }

    public List<String> crack(String ciphertextPath) {
        List<String> possibleDecryptions = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream(String.format("/%s.txt", ciphertextPath));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String ciphertext = reader.readLine();
            possibleDecryptions = bombe.crack(ciphertext);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return possibleDecryptions;
    }

    private String shiftKey(String key, int i) {
        return key.substring(i) + key.substring(0, i);
    }

}
