package DitherThatImageINT272;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Bruno on 6/07/2016.
 * Link to challenge: https://www.reddit.com/r/dailyprogrammer/comments/4paxp4/20160622_challenge_272_intermediate_dither_that/
 */
public class DitherThatImage {

    public static int height = 0;
    public static int width = 0;

    public static void main(String[] args) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        height = image.getHeight();
        width = image.getWidth();

        int[][] pixels = readImage(image);

        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                int oldPixelColour = (pixels[x][y]);
                int newPixelColour = findClosestPalletColour(oldPixelColour);
                int difference = oldPixelColour - newPixelColour; // Calculate error; Should it be in absolute value?
                //System.out.print(difference + "\t");
                pixels[x][y] = newPixelColour;

                /*
                    Distribute error
                             X    7/16
                     3/16  5/16   1/16

                     Warning: very ugly code ahead. I was too lazy to do this the proper way
                */
               try {
                    pixels[x][y + 1] += (int) (difference * (7.0d / 16.0d));
                }catch (Throwable e) {}
                try {
                    pixels[x + 1][y - 1] += (int) (difference * (3.0d / 16.0d));
                }catch (Throwable e) {}
                try {
                    pixels[x + 1][y] += (int) (difference * (5.0d / 16.0d));
                }catch (Throwable e) {}
                try {
                    pixels[x + 1][y + 1] += (int) (difference * (1.0d / 16.0d));
                }catch (Throwable e) {}
            }
        }
        writePixelsToImage(pixels);
    }

    /**
     * Finds if the param oldColour is closer to 255 or 0
     * @param oldColour
     * @return returns closer number (255 or 0)
     */
    public static int findClosestPalletColour(int oldColour) {
        int newColour;
        int middle = 255/2;
        if (oldColour > middle) // Also account if the colour is in middle of the spectrum
            newColour = 255;
        else
            newColour = 0;
        return newColour;
    }

    /**
     * Converts 2D array to 1D array
     * @param 2-dimensional array to be converted
     * @return 1-dimensional array
     */
    public static int[] convert2DArrayTo1DArray(int[][] array) {
        int[] finalArray = new int[array.length * array[0].length];
        int i = 0;
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[0].length; y++) {
                finalArray[i] = array[x][y];
                i++;
            }
        }
        return finalArray;
    }

    public static int[][] readImage(BufferedImage image) {
        int[][] pixels = new int[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                Color color = new Color(image.getRGB(y, x));
                int finalColour = (color.getBlue() + color.getGreen() + color.getRed()) / 3;
                pixels[x][y] = finalColour;
            }
        }
        return pixels;
    }

    public static void writePixelsToImage(int[][] pixelsSource) {
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[] pixels = convert2DArrayTo1DArray(pixelsSource);
        for (int i = 0; i < pixels.length; i++) {
            int oldColour = pixels[i];
            int colourARGB = (255 << 24) | (oldColour << 16) | (oldColour << 8) | oldColour;
            pixels[i] = colourARGB;
        }
        outputImage.setRGB(0, 0, width, height, pixels, 0, width);
        try {
            ImageIO.write(outputImage, "png", new File("imageOutput.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints out an array using System.out.
     * @param array to be display
     */
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
