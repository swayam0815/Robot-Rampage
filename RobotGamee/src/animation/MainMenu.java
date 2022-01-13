package animation;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.GraphicsConsole;

public class MainMenu {

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen
	
	private static GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);
	
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		new AnimationMain(gc, GRWIDTH, GRHEIGHT);
	}

}
