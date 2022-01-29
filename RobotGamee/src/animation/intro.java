// Author: Swayam and Aryan
// Creation Date: December 2022
// description: display intro for game
// Last Modified: January 26 2022
package animation;

import java.awt.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hsa2.GraphicsConsole;

public class intro {
	// array of images
	// using this to store the video for logo as multiple images, since this Graphics library doesnt support videos
	// only way to animate video is to split them up into images, and animate the images instead
	private static Image[] logo = new Image[30];

	private static void getImg() throws IOException {
		// reading in all the images for the video logo
		logo[1] = ImageIO.read(new File("ezgif-frame-001.png"));
		logo[2] = ImageIO.read(new File("ezgif-frame-002.png"));
		logo[3] = ImageIO.read(new File("ezgif-frame-003.png"));
		logo[4] = ImageIO.read(new File("ezgif-frame-004.png"));
		logo[5] = ImageIO.read(new File("ezgif-frame-005.png"));
		logo[6] = ImageIO.read(new File("ezgif-frame-006.png"));
		logo[7] = ImageIO.read(new File("ezgif-frame-007.png"));
		logo[8] = ImageIO.read(new File("ezgif-frame-008.png"));
		logo[9] = ImageIO.read(new File("ezgif-frame-009.png"));
		logo[10] = ImageIO.read(new File("ezgif-frame-010.png"));
		logo[11] = ImageIO.read(new File("ezgif-frame-011.png"));
		logo[12] = ImageIO.read(new File("ezgif-frame-012.png"));
		logo[13] = ImageIO.read(new File("ezgif-frame-013.png"));
		logo[14] = ImageIO.read(new File("ezgif-frame-014.png"));
		logo[15] = ImageIO.read(new File("ezgif-frame-015.png"));
		logo[16] = ImageIO.read(new File("ezgif-frame-016.png"));
		logo[17] = ImageIO.read(new File("ezgif-frame-017.png"));
		logo[18] = ImageIO.read(new File("ezgif-frame-018.png"));
		logo[19] = ImageIO.read(new File("ezgif-frame-019.png"));
		logo[20] = ImageIO.read(new File("ezgif-frame-020.png"));
		logo[21] = ImageIO.read(new File("ezgif-frame-021.png"));
		logo[22] = ImageIO.read(new File("ezgif-frame-022.png"));
		logo[23] = ImageIO.read(new File("ezgif-frame-023.png"));
		logo[24] = ImageIO.read(new File("ezgif-frame-024.png"));
		logo[25] = ImageIO.read(new File("ezgif-frame-025.png"));
		logo[26] = ImageIO.read(new File("ezgif-frame-026.png"));
		logo[27] = ImageIO.read(new File("ezgif-frame-027.png"));
		logo[28] = ImageIO.read(new File("ezgif-frame-028.png"));
		logo[29] = ImageIO.read(new File("ezgif-frame-029.png"));

	}

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit
	
	// class contructor
	public intro(GraphicsConsole gc) throws IOException, InterruptedException {
		// displaying loading, while getting all image assets
		Image loading = ImageIO.read(new File("loading.png"));
		// drawing loading iamge
		gc.setBackgroundColor(loading, GRWIDTH, GRHEIGHT);
		// getting assets
		getImg();

		// drawing all the images
		synchronized (gc) {
			gc.drawImage(logo[2], 0, 0, GRWIDTH, GRHEIGHT);
			for (int i = 1; i < logo.length; i++) {
				gc.drawImage(logo[i], 0, 0, GRWIDTH, GRHEIGHT);
				gc.wait(100 - i);
			}
		}
	}

}
