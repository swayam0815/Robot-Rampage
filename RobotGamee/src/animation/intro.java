package animation;

import java.awt.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hsa2.GraphicsConsole;

public class intro {
	private static Image[] op = new Image[30];

	private static void getImg() throws IOException {

		op[1] = ImageIO.read(new File("ezgif-frame-001.png"));

		op[2] = ImageIO.read(new File("ezgif-frame-002.png"));
		op[3] = ImageIO.read(new File("ezgif-frame-003.png"));
		op[4] = ImageIO.read(new File("ezgif-frame-004.png"));
		op[5] = ImageIO.read(new File("ezgif-frame-005.png"));
		op[6] = ImageIO.read(new File("ezgif-frame-006.png"));
		op[7] = ImageIO.read(new File("ezgif-frame-007.png"));
		op[8] = ImageIO.read(new File("ezgif-frame-008.png"));
		op[9] = ImageIO.read(new File("ezgif-frame-009.png"));
		op[10] = ImageIO.read(new File("ezgif-frame-010.png"));
		op[11] = ImageIO.read(new File("ezgif-frame-011.png"));
		op[12] = ImageIO.read(new File("ezgif-frame-012.png"));
		op[13] = ImageIO.read(new File("ezgif-frame-013.png"));
		op[14] = ImageIO.read(new File("ezgif-frame-014.png"));
		op[15] = ImageIO.read(new File("ezgif-frame-015.png"));
		op[16] = ImageIO.read(new File("ezgif-frame-016.png"));
		op[17] = ImageIO.read(new File("ezgif-frame-017.png"));
		op[18] = ImageIO.read(new File("ezgif-frame-018.png"));
		op[19] = ImageIO.read(new File("ezgif-frame-019.png"));
		op[20] = ImageIO.read(new File("ezgif-frame-020.png"));
		op[21] = ImageIO.read(new File("ezgif-frame-021.png"));
		op[22] = ImageIO.read(new File("ezgif-frame-022.png"));
		op[23] = ImageIO.read(new File("ezgif-frame-023.png"));
		op[24] = ImageIO.read(new File("ezgif-frame-024.png"));
		op[25] = ImageIO.read(new File("ezgif-frame-025.png"));
		op[26] = ImageIO.read(new File("ezgif-frame-026.png"));
		op[27] = ImageIO.read(new File("ezgif-frame-027.png"));
		op[28] = ImageIO.read(new File("ezgif-frame-028.png"));
		op[29] = ImageIO.read(new File("ezgif-frame-029.png"));

	}

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit
// the screen

	public intro(GraphicsConsole gc) throws IOException, InterruptedException {
		Image loading = ImageIO.read(new File("loading.png"));

		gc.setBackgroundColor(loading, GRWIDTH, GRHEIGHT);

		getImg();

		synchronized (gc) {
			gc.drawImage(op[2], 0, 0, GRWIDTH, GRHEIGHT);
			for (int i = 1; i < op.length; i++) {
				gc.drawImage(op[i], 0, 0, GRWIDTH, GRHEIGHT);
				gc.wait(100 - i);
			}
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {

	}

}
