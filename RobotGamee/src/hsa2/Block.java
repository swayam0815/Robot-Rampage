package hsa2;

import java.awt.Color;
import java.awt.Rectangle;

public class Block extends Rectangle {
	
	//this can be used for setting various types of blocks. (Unbreakable ones, ones that make the paddle smaller or larger, ones that give an extra ball...) 
	//They could have different colours too.
	int type = 1;
	
	Color colour = new Color(209, 245, 255);
	
	//is the block displayed on the screen?
	boolean isVisible = true;	
	
	//constructor. Set parameters for all blocks
	Block() {
		width = 100;
		height = 20;
	}
	
	Block(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public void setColour(int color1, int color2, int color3) {
		colour = new Color(color1, color2, color3);
	}
	
}
