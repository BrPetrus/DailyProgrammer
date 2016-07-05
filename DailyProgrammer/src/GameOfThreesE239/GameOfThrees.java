package GameOfThreesE239;

import java.util.Scanner;

/**
 * Created by Bruno on 30/06/2016.
 * Link to challenge: https://www.reddit.com/r/dailyprogrammer/comments/3r7wxz/20151102_challenge_239_easy_a_game_of_threes/
 */
public class GameOfThrees {
    public static void main(String[] args) {
        //Get the number
        System.out.print("Enter number: ");
        Scanner keyboardInput = new Scanner(System.in);
        int number = keyboardInput.nextInt();

        int[] tuple = {0, -1, 1};

        //Loop
        while(number > 1) {
            int remainder = number % 3;
            System.out.print(number + " " + tuple[remainder]);
            number += tuple[remainder];
            number /= 3;
            System.out.println();
        }

        //Print result
        System.out.println(number);
    }
}
