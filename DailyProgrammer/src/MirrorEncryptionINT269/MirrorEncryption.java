package MirrorEncryptionINT269;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Bruno on 21/06/2016.
 * Link to challenge: https://www.reddit.com/r/dailyprogrammer/comments/4m3ddb/20160601_challenge_269_intermediate_mirror/
 */
public class MirrorEncryption {
    public static void main(String[] args) {
        Encryption encrypt = new Encryption(args[0]);
        encrypt.displayMap();
        System.out.println(encrypt.encrypt(args[1]));
    }
}

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

class Encryption {
    private char[][] map;

    public Encryption(String path) {
        resetMap();
        readInput(path);
    }

    public void displayMap() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
    }

    public String encrypt(String sourceText) {
        String result = "";
        for(int i = 0; i < sourceText.length(); i++) {
            int[] letterPosition = findLetter(sourceText.charAt(i));
            if(letterPosition == null) {
                System.err.println("Error finding the character '" + sourceText.charAt(i) + "'");
                System.exit(1);
            }
            Direction direction = determineStartingDirection(letterPosition);
            if(direction == null) {
                System.err.println("Error determining starting position! [" + letterPosition[0] + ";" + letterPosition[1] + "]");
                System.exit(1);
            }

            int x = letterPosition[0];
            int y = letterPosition[1];
            boolean isRunning = true;
            while (isRunning) {
                switch (direction) {
                    case UP:
                        if(map[x - 1][y] == ' ') {
                            x -= 1;
                        }
                        else if(map[x - 1][y] == '/') {
                            x -= 1;
                            direction = Direction.RIGHT;
                        }
                        else if(map[x - 1][y] == '\\') {
                            x -= 1;
                            direction = Direction.LEFT;
                        }
                        else {
                            result += map[x - 1][y];
                            isRunning = false;
                        }
                        break;
                    case DOWN:
                        if(map[x + 1][y] == ' ') {
                            x += 1;
                        }
                        else if(map[x + 1][y] == '/') {
                            x += 1;
                            direction = Direction.LEFT;
                        }
                        else if(map[x + 1][y] == '\\') {
                            x += 1;
                            direction = Direction.RIGHT;
                        }
                        else {
                            result += map[x + 1][y];
                            isRunning = false;
                        }
                        break;
                    case RIGHT:
                        if(map[x][y + 1] == ' ') {
                            y += 1;
                        }
                        else if(map[x][y + 1] == '/') {
                            y += 1;
                            direction = Direction.UP;
                        }
                        else if(map[x][y + 1] == '\\') {
                            y += 1;
                            direction = Direction.DOWN;
                        }
                        else {
                            result += map[x][y + 1];
                            isRunning = false;
                        }
                        break;
                    case LEFT:
                        if(map[x][y - 1] == ' ') {
                            y -= 1;
                        }
                        else if(map[x][y - 1] == '/') {
                            y -= 1;
                            direction = Direction.DOWN;
                        }
                        else if(map[x][y - 1] == '\\') {
                            y -= 1;
                            direction = Direction.UP;
                        }
                        else {
                            result += map[x][y - 1];
                            isRunning = false;
                        }
                        break;
                }
            }
        }
        return result;
    }

    private Direction determineStartingDirection(int[] letterPosition) {
        if(letterPosition[1] == 0)
            return Direction.RIGHT;
        else if (letterPosition[1] == map.length - 1)
            return Direction.LEFT;
        else if (letterPosition[0] == 0)
            return Direction.DOWN;
        else if(letterPosition[0] == map.length - 1)
            return Direction.UP;
        return null;
    }

    private int[] findLetter(char letter) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if(map[x][y] == letter) {
                    return new int[]{x,y};
                }
            }
        }
        return null;
    }

    private void readInput(String path) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String line;
            int x = 1;
            while((line = in.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    map[x][i + 1] = line.charAt(i);
                }
                x++;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetMap() {
        map = new char[15][15];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                map[x][y] = ' ';
            }
        }
        generateBorders();
    }

    private void generateBorders() {
        //First row
        for (int y = 1; y < map[0].length - 1; y++) {
            map[0][y] = (char)('a' + y - 1);
        }
        //Right column
        for (int x = 1; x < map.length - 1; x++) {
            map[x][14] = (char)('n' + x - 1);
        }

        //Left column
        for (int x = 1; x < map.length - 1; x++) {
            map[x][0] = (char)('A' + x - 1);
        }
        //Last row
        for (int y = 1; y < map[14].length - 1; y++) {
            map[14][y] = (char)('N' + y - 1);
        }
    }
}
