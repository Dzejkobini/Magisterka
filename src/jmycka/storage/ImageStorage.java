package jmycka.storage;

import jmycka.image.ImageWrapper;

public class ImageStorage extends AbstracStorage<ImageWrapper> {

	private static ImageStorage instance = null;
	
	private ImageStorage()
	{
		//nop
	}
	
	public static ImageStorage getInstance()
	{
		if (instance == null)
		{
			instance = new ImageStorage();
		}
		return instance;
	}
}
