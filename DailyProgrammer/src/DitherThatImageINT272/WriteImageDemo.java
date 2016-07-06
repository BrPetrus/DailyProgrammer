package DitherThatImageINT272;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Bruno on 6/07/2016.
 */
public class WriteImageDemo {

    public static void main(String[] args) throws IOException {
        int[] pixelsGrayScale = {255, 255, 0, 0, 255, 0, 0, 0, 0, 156, 0, 0, 0, 0, 0, 0};

        BufferedImage outputImage = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        int[] pixels = new int[pixelsGrayScale.length];
        for (int i = 0; i < pixels.length; i++) {
            int oldColour = pixelsGrayScale[i];
            int colourARGB = (255 << 24) | (oldColour << 16) | (oldColour << 8) | oldColour;
            pixels[i] = colourARGB;
        }

        outputImage.setRGB(0, 0, 4, 4, pixels, 0, 4);
        ImageIO.write(outputImage, "png", new File("imageOutput.png"));
    }

}
