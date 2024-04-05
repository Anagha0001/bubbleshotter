import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Shared
{
    public static final int SPEED = 20;
    public static final int RADIUS = 25;
    public static final int DIAMETER = RADIUS*2;
    public static final int ROWS = 14;
    public static final int COLS = 8;
    public static final int STARTINGROWS = 14;
    public static final int NUMCOLORS = 4;
    public static final char EMPTY = '-';
    public static final char[] COLORS = {'R', 'G', 'B', 'O', 'Y', 'P'};
    public static final int[][] EVEN_NEIGHBORS = {{-1,-1},{-1,0},{0,-1},{0,1},{1,-1},{1,0}};
    public static final int[][] ODD_NEIGHBORS = {{-1,0},{-1,1},{0,-1},{0,1},{1,0},{1,1}};

    public static BufferedImage image1;
    public static BufferedImage image2;
    public static BufferedImage image3;
    public static BufferedImage image4;
    public static BufferedImage image5;
    public static BufferedImage image6;

    static
    {
        try
        {
            image1 = ImageIO.read(new File("Orbz/Airless.png"));
            image2 = ImageIO.read(new File("Orbz/Blless.png"));
            image3 = ImageIO.read(new File("Orbz/Bloodless.png"));
            image4 = ImageIO.read(new File("Orbz/Curseless.png"));
            image5 = ImageIO.read(new File("Orbz/Ecto Orb.png"));
            image6 = ImageIO.read(new File("Orbz/Emptyless.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static final Map<Character, BufferedImage> colorMap = new HashMap<>(){{
        put('R',image1);
        put('G',image2);
        put('B',image3);
        put('O',image4);
        put('Y',image5);
        put('P',image6);
    }};
}
