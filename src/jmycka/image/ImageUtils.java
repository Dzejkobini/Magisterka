package jmycka.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageUtils {

	public static ImageIcon createThumbnailIcon(BufferedImage image)
	{
		BufferedImage thumbnail = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
		Image tmp = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		Graphics2D g2d = thumbnail.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		
		return new ImageIcon(thumbnail);
	}
}
