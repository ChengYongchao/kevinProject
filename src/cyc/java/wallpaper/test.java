
package cyc.java.wallpaper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test
{

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        // 得到图片缓冲区
        BufferedImage bi = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150

        // 得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D)bi.getGraphics();

        //g2.fillRect(0, 0, 150, 70);// 填充一个矩形 左上角坐标(0,0),宽70,高150;填充整张图片
        // 设置颜色
        Color color = new Color(186,202,198);
        g2.setColor(color);
        g2.fillRect(0, 0, 1920, 1080);// 填充整张图片(其实就是设置背景颜色)

        // g2.setColor(Color.RED);
        // g2.drawRect(0, 0, 150 - 1, 70 - 1); // 画边框

        // g2.setFont(new Font("宋体", Font.BOLD, 18)); // 设置字体:字体、字号、大小
        // g2.setColor(Color.BLACK);// 设置背景颜色

        // g2.drawString("HelloWorld", 3, 50); // 向图片上写字符串
        ImageIO.write(bi, "JPEG", new FileOutputStream("F:/a.jpg"));// 保存图片
                                                                    // JPEG表示保存格式

    }
}
