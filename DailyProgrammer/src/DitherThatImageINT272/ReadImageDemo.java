package DitherThatImageINT272;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Bruno on 6/07/2016.
 */
public class ReadImageDemo {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("imageOutput.png"));

        int height = image.getHeight();
        int width = image.getWidth();

        int[][] pixels = new int[height][width];

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                Color color = new Color(image.getRGB(x, y));
                int finalColour = (color.getBlue() + color.getGreen() + color.getRed()) / 3;
                pixels[x][y] = finalColour;
            }
        }

        display(pixels);
    }

    public static void display(int[][] array) {
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[0].length; y++) {
                System.out.print(array[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
