package daGame;

import java.awt.*;
import java.util.ArrayList;

import javax.crypto.spec.GCMParameterSpec;

import hsa2.GraphicsConsole;

public class New {

	/***** Global Variables ******/
	private Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen size
	private static GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);

	ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
	ArrayList<Rectangle> hit = new ArrayList<Rectangle>();

	private int x = 0;
	private int y = 0;
	private int size = 50;
	private int counter = 0;
	private Rectangle enemy = new Rectangle(0, GRHEIGHT / 8, GRWIDTH, 100);
	private static Color enemgyC = Color.RED;

	public static void main(String[] args) {
		new AnimationMain(gc, GRHEIGHT, GRWIDTH);
	}

	public New() {
	
		/*
		 * gc.enableMouseMotion(); gc.enableMouse(); // enables motion and click for the
		 * mouse
		 * 
		 * while (gc.getKeyCode() != 'Q') {
		 * 
		 * mechanics();
		 * 
		 * drawGraphics();
		 * 
		 * gc.sleep(1);
		 */		//}
	}

	public void mechanics() {
		
		// command to shoot bullets
		// works by adding rectangle objects to bullets list
		// and then animation draws whatever in array list
		if (gc.isKeyDown(32)) {
			bullets.add(new Rectangle(x, y, size, size));
			enemgyC = Color.GREEN;
		}
		
		// movement controls
		if (gc.isKeyDown(65) || gc.isKeyDown(97)) {
			x -= 3;
		}
		if (gc.isKeyDown(68) || gc.isKeyDown(100)) {
			x += 3;
		}
		if (gc.isKeyDown(87) || gc.isKeyDown(119)) {
			y -= 3;
		}
		if (gc.isKeyDown(83) || gc.isKeyDown(115)) {
			y += 3;
		}
		
		
		// animating size
		size = y / 4;

	}
	public void shoot (ArrayList<Integer> x) {
		x.add(50);
	}

	public void drawGraphics() {
		synchronized (gc) {
			//graphics stuff
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();
			gc.setColor(Color.GREEN);
			gc.drawRect(x, y, size, size);
			// drawing an enemy to test stuff on
			gc.setColor(enemgyC);
			gc.fillRect(enemy);

			// for each loop to go through and draw everything from bullet list
			// refer to mechanics method in regards for more logic
			// loop draws the stuff, and then checks their values with if statements
			// if some value is found to be true, it adds them to hit list
			// hit list is just a list to keep track of what stuff to remove from the bullets arraylist
			// 
			//say for example the bullets touches the edges of screen,
			// we wanna remove it, so add to hit list 
			for (Rectangle rect : bullets) {
				gc.setColor(Color.GREEN);
				gc.drawRect(rect);
				rect.y -= 1;
				//rect.width--;
				//rect.height--;
				// if stuff touches the edges of screen, add to hit list
				if (rect.y <= 0) {
					rect.width = 0;
					rect.height = 0;
					hit.add(rect);
				}
				// checking to see if hit enenmy
				if (rect.y == enemy.y || rect.x == enemy.x) {
					
					enemy.width -= 2;
					enemy.height -= 2;
					gc.setColor(Color.RED);
					gc.drawRect(rect.x, rect.y, rect.width, rect.width);
				}
			}
			System.out.println(bullets.size());
			// removing hit bullets from main bullets list
			bullets.removeAll(hit);
			System.out.println(bullets.size());
		}

	}
}
