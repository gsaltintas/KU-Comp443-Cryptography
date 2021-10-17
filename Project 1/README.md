# Comp 443 Project 1
## Gül Sena Altıntaş - 64284
Please run gradle build before running any code

Run the main class: WW2 and specify the part in the part field. 

As we shouldn't reset rotors for every message I created two instances of the Enigma class: encryptionEnigma and decryptionEnigma. Also Enigma class has a field newCommunication which should be true if the communication starts from the beginning, otherwise (ie in part 3) it is false.

## Part 1
Rotor and Enigma implementations are straight forward and can be found in enigma folder.

## Part 2
Bombe Class implements a crack method which tries for all the combinations (3!x26^3) and returns a list of possible decryptions which contain "MINE" and "ONE" texts.

Note that the original combinations are (26!)^3 but since our problem statement specifies the use of the given left, middle and right rotor keys it works.
## Part 3
Communication class loads the messages of a user and acts as a stream giving the next message. As long as both users have a next message the communication continues. In part 3, the programmer have the option to specify whether they are an eavesdropper or a party in the communication. 

Messages from the parties -Alice and Bob- can be found in the resources/main folder.

Enigma File Breaker class contructs a Bombe and sends the read message to its crack method.

Note that I also implemented a client-server architecture but it would make the task a lot harder to run and grade therefore I opted out from this option.