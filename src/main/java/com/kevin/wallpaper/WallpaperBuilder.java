
package com.kevin.wallpaper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WallpaperBuilder
{
    private Color color;

    private int high;

    private int weight;

    private String address;

    public WallpaperBuilder()
    {
        this.color = new Color(0, 0, 0);
        this.high = 1920;
        this.weight = 1080;
        this.address = "C:\\Users\\Administrator\\";
    }

    public WallpaperBuilder(int r, int g, int b)
    {
        this.color = new Color(r, g, b);
        this.high = 1920;
        this.weight = 1080;
        this.address = "C:\\Users\\Administrator\\";
    }

    public WallpaperBuilder(int r, int g, int b, int high, int weight)
    {
        this.color = new Color(r, g, b);
        this.high = high;
        this.weight = weight;
    }

    public WallpaperBuilder(int r, int g, int b, int high, int weight, String address)
    {
        this.color = new Color(r, g, b);
        this.high = high;
        this.weight = weight;
        this.address = address;
    }

    public void printWallpaper()
    {
        BufferedImage bi = new BufferedImage(high, weight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)bi.getGraphics();
        g2.setColor(color);
        g2.fillRect(0, 0, high, weight);
        try
        {
            ImageIO.write(bi, "JPEG", new FileOutputStream(address + "wallpaper.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
