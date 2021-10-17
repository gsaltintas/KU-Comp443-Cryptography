package chat;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Communication {
    private ArrayList<String> messages;
    private String username;
    private int nextMsgIndex;
    private boolean connectionOngoing;

    public Communication(String username) {
        if (validUser(username)) {
            this.username = username;
            loadUserMessages(username);
            connectionOngoing = true;
            nextMsgIndex = 0;
        } else {
            System.out.println("Invalid user.");
        }
    }

    private boolean validUser(String username) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File userFile = new File(classLoader.getResource(String.format("%s.txt", username)).getFile());
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void loadUserMessages(String username) {
        ClassLoader classLoader = getClass().getClassLoader();
        File userFile = new File(classLoader.getResource(String.format("%s.txt", username)).getFile());
//        File userFile = new File(classLoader.getResource(String.format("%s.txt", username)).getFile());

        try (InputStream inputStream = getClass().getResourceAsStream(String.format("/%s.txt", username));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            this.messages = new ArrayList<String>(reader.lines().collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String getNextMessage() {
        String message = new String();
        if (nextMsgIndex < messages.size()) {
            message = messages.get(nextMsgIndex).replace(" ","");
            nextMsgIndex++;
            if (nextMsgIndex >= messages.size())
                connectionOngoing = false;
        }
        return message;
    }

    public boolean isConnectionOngoing() {
        return connectionOngoing;
    }
}
