package enigma;

public class Enigma {
    private Rotor leftRotor;
    private Rotor middleRotor;
    private Rotor rightRotor;
    public boolean newCommunication;

    public Enigma(String key1, String key2, String key3) {
        this.leftRotor = new Rotor(key1);
        this.middleRotor = new Rotor(key2);
        this.rightRotor = new Rotor(key3);
        newCommunication = true;

    }

    /**
     * @param message
     * @return encryption of the message
     */
    public String encrypt(String message) {
        if (newCommunication) {
            resetRotorKeys();
        }
        String cipherText = "";
        for (char ch : message.toCharArray()) {
            char firstC = leftRotor.encryptLetter(ch);
            char secondC = middleRotor.encryptLetter(firstC);
            char thirdC = rightRotor.encryptLetter(secondC);
            cipherText += thirdC;
            clickRotors();

        }
        return cipherText;
    }

    /**
     * @param ciphertext
     * @return decryption of the ciphertext
     */
    public String decrypt(String ciphertext) {
        if (newCommunication) {
            resetRotorKeys();
        }
        String plainText = "";
        for (char ch : ciphertext.toCharArray()) {
            char thirdC = rightRotor.decryptLetter(ch);
            char secondC = middleRotor.decryptLetter(thirdC);
            char firstC = leftRotor.decryptLetter(secondC);

            plainText += firstC;
            clickRotors();
        }
        return plainText;
    }

    /***
     * resets the keys of rotors to their original place, should be used before decryption
     */
    public void resetRotorKeys() {
        leftRotor.resetKeys();
        rightRotor.resetKeys();
        middleRotor.resetKeys();
    }

    /**
     * triggers click in the rotors
     */
    private void clickRotors() {
        if (!rightRotor.returnedOneTime()) {
            rightRotor.click();
        } else if (!middleRotor.returnedOneTime()) {
            middleRotor.click();
            rightRotor.click();
            rightRotor.setTurnedOneTime(false);
        } else if (!leftRotor.returnedOneTime()) {
            rightRotor.click();
            middleRotor.click();
            leftRotor.click();
            rightRotor.setTurnedOneTime(false);
            middleRotor.setTurnedOneTime(false);
        } else {
            rightRotor.click();
            rightRotor.setTurnedOneTime(false);
            middleRotor.setTurnedOneTime(false);
            leftRotor.setTurnedOneTime(false);
        }
    }

    public Rotor getLeftRotor() {
        return leftRotor;
    }

    public Rotor getMiddleRotor() {
        return middleRotor;
    }

    public Rotor getRightRotor() {
        return rightRotor;
    }

    protected String reverse(String str) {
        String res = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            res += str.charAt(i);
        }
        return res;
    }

    public void setNewCommunication(boolean newCommunication) {
        this.newCommunication = newCommunication;
    }
}

