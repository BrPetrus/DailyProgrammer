package TheBealeCipherE274;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Bruno on 5/07/2016.
 * Link to challenge: https://www.reddit.com/r/dailyprogrammer/comments/4r8fod/20160704_challenge_274_easy_gold_and_treasure_the/
 */
public class TheBealeCipher {
    public static void main(String[] args) {
        /*if (args.length != 1) {
            System.out.println("Usage: <cipher text>");
            System.exit(1);
        }*/

        BufferedReader in;

        //First off read the declaration of independence
        ArrayList<Character> wordsFromText = new ArrayList<>();
        try {
            in = new BufferedReader(new FileReader("declarationOfIndependence.txt"));
            String line;
            while((line = in.readLine()) != null) { // Read line by line
                String[] words = line.split(" "); // Split line into words
                for (String c : words) { // Cycle through words
                    wordsFromText.add(c.charAt(0)); // We only care about the first character
                }
            }
            in.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }

        //Now read the cipher text and decrypt it
        try {
            in = new BufferedReader(new FileReader(args[0]));

            String line;
            while((line = in.readLine()) != null) { // Read line by line
                String[] numbers = line.split(", "); // Split line into words
                for (String c : numbers) { // Cycle through words
                    System.out.print(wordsFromText.get(Integer.parseInt(c) % wordsFromText.size() - 1));
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(wordsFromText.get(wordsFromText.size() - 1));
    }
}
