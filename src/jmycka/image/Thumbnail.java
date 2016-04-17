package jmycka.image;

import javax.swing.JLabel;

public class Thumbnail extends JLabel{

	private static final long serialVersionUID = 8663888492537314693L;
	private boolean isClicked = false;
	
	public Thumbnail()
	{
		//nop
	}
	
	public boolean isSelected() {
		return this.isClicked;
	}
	
	public void setSelect(boolean isClicked) {
		this.isClicked = isClicked;
	}
}
