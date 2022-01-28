package hsa2;

import java.awt.*;

import javax.crypto.spec.GCMParameterSpec;
import javax.swing.text.AttributeSet.ColorAttribute;

public class AnimationMain {

	private static GraphicsConsole gc = new GraphicsConsole(2000, 1000);
	private static int x = 50;
	private static int y = 50;

	public static void main(String[] args) {
		gc.drawRect(x, y, y, x);
	}

}