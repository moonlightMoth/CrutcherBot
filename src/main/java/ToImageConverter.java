import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;

public class ToImageConverter extends JPanel {

    private BufferedImage image;

    public ToImageConverter() {
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }




    public File convert(String inString)
    {
        BufferedImage img = null;
        File outFile = new File("E://outImage.png");
        String[] stringArray = toStringArray(inString);
        Font font = new Font("Serif", Font.ROMAN_BASELINE, 30);
        FontMetrics fontMetrics = null;
        Graphics2D graphics2D= null;
        Color textColor = Color.black;
        Color numerateColor = Color.gray;
        int x = font.getSize();
        int y = 2 * font.getSize();
        int count=0;
        int iCount=0;

        for (int i = 0; i < stringArray.length; i++)
        {
            if(stringArray[i].length()>count) {
                count = stringArray[i].length();
                iCount=i;
            }
        }


        BufferedImage tempImg = new BufferedImage(100000,
                stringArray.length * font.getSize()*3/2 + y,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D tempGraphics2D = tempImg.createGraphics();
        fontMetrics = tempGraphics2D.getFontMetrics(font);

        img = new BufferedImage(
                fontMetrics.stringWidth("12:      " + stringArray[iCount])+x,
                stringArray.length * font.getSize()*3/2 + y,
                BufferedImage.TYPE_INT_RGB);

        graphics2D = img.createGraphics();
        graphics2D.setRenderingHint(KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0,
                fontMetrics.stringWidth("12:      " + stringArray[iCount])+x,
                stringArray.length * font.getSize()*3/2 + y);

        graphics2D.setPaint(textColor);
        graphics2D.setFont(font);

        for (int i = 0; i < stringArray.length; i++)
        {
            graphics2D.setPaint(numerateColor);
            graphics2D.drawString(Integer.toString(i+1) + ": ", x, y);

            graphics2D.setPaint(textColor);
            graphics2D.drawString(stringArray[i], x + font.getSize()*2, y);
            y+=font.getSize()*3/2;
        }

        try {
            ImageIO.write(img, "png" , outFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        return outFile;

    }

    private String[] toStringArray (String inString)
    {
        String[] stringArray = inString.split("\n");
        return stringArray;
    }
}



















