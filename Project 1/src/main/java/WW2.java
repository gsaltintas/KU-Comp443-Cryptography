import chat.Communication;
import chat.CommunicationMode;
import enigma.Bombe;
import enigma.Enigma;
import enigma.EnigmaBreaker;

public class WW2 {

    private static final int part = 1;

    public static void main(String[] args) {

        String leftKey = "SHBMFWEIQRODTAVXCPYZUJKGNL";
        String middleKey = "GYRFNUCZLQDWMKHSJOEPBVITXA";
        String rightKey = "MSEWGQHDPRFNXATOIBUJLCZVYK";
        Enigma enigma = new Enigma(leftKey, middleKey, rightKey);
        Enigma decryptionEnigma = new Enigma(leftKey, middleKey, rightKey);

        if (part == 1) {
            enigma.resetRotorKeys();
            decryptionEnigma.resetRotorKeys();
            String ciphertext1 = "OQMTANMGPABQSDAKAUFXXGJBSPHBZXHLXMBNOHTNQZQGDBMIQNZJ";
            String ciphertext2 = "MXNMFKPDVWDPIMPYACVYZUGUWGPVGAOXCDZDGYTLATOIBUJLCZVYNXATOIBUJLCZVYKMSEWGQHDPRFIBUJLCZVYKMSEWGQHDPRFNXATOWGQHDPRFNXATOIBUJLCZVYKMSEZVYKMSEWGQHDPRFNXATOIBUJLCXATOIBUJLCZVYKMSEWGQHDPRFNFNXATOIBUJLCZVYKMSEWGQHDPRDPRFNXATOIBUJLCZVYKMSEWGQHUJLCZVYKMSEWGQHDPRFNXATOIBRFNXATOIBUJLCZVYKMSEWGQHDPTOIBUJLCZVYKMSEWGQHDPRFNXAGQHDPRFNXATOIBUJLCZVYKMSEWOIBUJLCZVYKMSEWGQHDPRFNXATSEWGQHDPRFNXATOIBUJLCZVYKMCZVYKMSEWGQHDPRFNXATOIBUJLPRFNXATOIBUJLCZVYKMSEWGQHDJLCZVYKMSEWGQHDPRFNXATOIBUVYKMSEWGQHDPRFNXATOIBUJLCZMSEWGQHDPRFNXATOIBUJLCZVYKHDPRFNXATOIBUJLCZVYKMSEWGQYKMSEWGQHDPRFNXATOIBUJLCZVBUJLCZVYKMSEWGQHDPRFNXATOIQHDPRFNXATOIBUJLCZVYKMSEWGATOIBUJLCZVYKMSEWGQHDPRFNXLCZVYKMSEWGQHDPRFNXATOIBUJZ";

            System.out.println(enigma.decrypt(ciphertext1));
            enigma.resetRotorKeys();
            System.out.println(enigma.decrypt(ciphertext2));
        } else if (part == 2) {
            enigma.resetRotorKeys();
            decryptionEnigma.resetRotorKeys();

            Bombe bombe = new Bombe(leftKey, middleKey, rightKey);

            System.out.println("Trying bombe with the given ciphertext");
            String bombeText = "NPWCDPBRIVDZGARYLECHBTOCKJCMJVDRFZEYFWJTRZLPDEVDHIJXYHRBRJTVVQCFDQUWHRQKYPYFAJJKSDEJVOVZNWYFYINBPBSNHZAGDACJRYRLLJAWCJKHTEVATAAZWVUHSBTCKBVHTNSGFDPHGIZDSZXMBSIKWLMMISUQNWCRPSHSNFAALBQNMKESIHCPGVRTRFTPRYTRIRMNYMVSLEKAPRISAUSTRXQFVCLYWXZLLXHHKHJJUTPKHBTFENHMFRLFLUHYQJSCMNEBB";
            System.out.println(bombe.crack(bombeText));

            bombe.resetBombe();
            bombeText = enigma.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYABCDEFGHIJKLMNOPQRSTUVWXYZZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYABCDEFGHIJKLMNOPQRSTUVWXYZZMINEKONE");
            System.out.println(bombe.crack(bombeText));

        } else if (part == 3) {
            enigma.resetRotorKeys();
            decryptionEnigma.resetRotorKeys();
            enigma.setNewCommunication(false);
            decryptionEnigma.setNewCommunication(false);

            CommunicationMode mode = CommunicationMode.Communicator;
//        CommunicationMode mode = CommunicationMode.Eavesdropper;

            String alice = "Alice";
            String bob = "Bob";
            Communication aliceConnection = new Communication(alice);
            Communication bobConnection = new Communication(bob);

            if (mode == CommunicationMode.Eavesdropper) {
                System.out.println(String.format("Eavesdropping communication between %s and %s", alice, bob));
            } else if (mode == CommunicationMode.Communicator) {
                System.out.println("Initating communication");
            }
            while (aliceConnection.isConnectionOngoing() && bobConnection.isConnectionOngoing()) {
                String bobMessage = enigma.encrypt(bobConnection.getNextMessage());
                String aliceMessage = enigma.encrypt(aliceConnection.getNextMessage());
                if (mode == CommunicationMode.Communicator) {
                    bobMessage = decryptionEnigma.decrypt(bobMessage);
                    aliceMessage = decryptionEnigma.decrypt(aliceMessage);
                }
                System.out.println(String.format("%s: %s", bob, bobMessage));
                System.out.println(String.format("%s: %s", alice, aliceMessage));
            }
            System.out.println("Communication ended successfully.");

            System.out.println("Trying Enigma file breaker");
            EnigmaBreaker breaker = new EnigmaBreaker(leftKey, middleKey, rightKey);
            System.out.println(breaker.crack("EnigmaBreakerCipherText"));
        }


    }
}
