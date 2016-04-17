package jmycka.image;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import jmycka.storage.ImageStorage;

public class ImageWrapper {

	private static int index;
	private static int selectedImage;
	
	private int id;
	private BufferedImage image;
	private Thumbnail thumbnail = new Thumbnail();
	private ImageIcon thumbnailIcon;
	private ImageStorage imageStorage = ImageStorage.getInstance();
	
	public ImageWrapper(BufferedImage image)
	{
		this.id = index++;
		this.image = image;
		this.thumbnailIcon = ImageUtils.createThumbnailIcon(image);
		thumbnail.setIcon(thumbnailIcon);
		thumbnail.setAlignmentX(Component.CENTER_ALIGNMENT);
		thumbnail.setBorder(new EmptyBorder(5, 5, 5, 5));
		thumbnail.addMouseListener(new MouseAdapter()   {   

	        public void mouseClicked(MouseEvent e)   
	        {   	
	        	Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 5);
	        	Border invisibleBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5); 
	        	
	        	if(thumbnail.isSelected())
	        	{
	        		thumbnail.setBorder(invisibleBorder);
	        		thumbnail.setSelect(false);
	        	}
	        	else
	        	{
		        	thumbnail.setBorder(blueBorder);
		        	thumbnail.setSelect(true);
		        	selectedImage = id;
	        	}
	        	
	        	for (ImageWrapper iw: imageStorage.getItemList())
	        	{
	        		if (iw.getId() != id)
	        		{
	        			iw.getThumbnail().setBorder(invisibleBorder);
	        			iw.getThumbnail().setSelect(false);
	        		}
	        	}
	        	

	        }  
		});
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}

	public Thumbnail getThumbnail() {
		return this.thumbnail;
	}

	public static int getSelectedImage() {
		return selectedImage;
	}	
	
}
