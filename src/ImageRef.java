import java.awt.*; // For the Color and Graphics classes
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageRef {
    static BufferedImage screenshot = null;
    static boolean t = false;
    static Rectangle rectangle = null;
    static Robot robot = null;
    static File file = null;
    static Dimension screenSize = null;

    public ImageRef() {

        try {
            robot = new Robot();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            screenSize = toolkit.getScreenSize();
            rectangle = new Rectangle(screenSize);
            screenshot = robot.createScreenCapture(rectangle);
            file = new File("screenshot.png");
            ImageIO.write(screenshot, "png", file);
            System.out.println("Screenshot saved to screenshot.png");
        } catch (Exception e) {

        }
    }

    public int[] getRgb(int x, int y) {
        int rgb[] = { 0, 0, 0, 255 };
        int temp = screenshot.getRGB(x, y);
        rgb[0] = (temp & 0x00ff0000) >> 16;
        rgb[1] = (temp & 0x0000ff00) >> 8;
        rgb[2] = temp & 0x000000ff;
        return (rgb);
    }

    public void capture() {
        try {
            robot = new Robot();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            screenSize = toolkit.getScreenSize();
            rectangle = new Rectangle(screenSize);
            screenshot = robot.createScreenCapture(rectangle);
            file = new File("screenshot.jpeg");
            ImageIO.write(screenshot, "jpeg", file);
            System.out.println("Screenshot saved to screenshot.jpeg");
        } catch (Exception e) {

        }
    }
}