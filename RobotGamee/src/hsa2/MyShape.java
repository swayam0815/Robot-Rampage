
//Author: Swayam Sachdeva
//Date Created: November 2 2021
//Purpose: creates a screensaver using the hsa2 graphics library
//Last Modified: November 15 2021

package hsa2;

import java.awt.*;

public class MyShape {

	// Code taken from exemplar project shared by Mr. Francis:
	// finding the dimensions of the device screen
	// storing as instance variables, so they can be used throughout the program
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int) screenSize.getWidth();
	static int height = (int) screenSize.getHeight();

	// integers used to generate colours
	// static to use at multiple points within the program
	static int a;
	static int b;
	static int c;

	// array to store a gradient of colours
	// allows for the use of the gradient colors through program
	static Color[] colors = new Color[35];

	// graphic console for the screen saver
	private static GraphicsConsole gc = new GraphicsConsole(width, height);

	/*
	 * Method Name: setColour 
	 * Author: Swayam Sachdeva 
	 * Creation Date: November 2 2021
	 * Modified Date: November 15 2021 
	 * Description: generate a color object based on input parameters 
	 * Parameters: rgb color values: int color1, color2, color3
	 * Return Value: Color object Throws/Exceptions: N/A
	 */

	public static Color setColour(int color1, int color2, int color3) {
		// generating new color object and returning it
		return new Color(color1, color2, color3);
	}

	/*
	 * Method Name: setColour 
	 * Author: Swayam Sachdeva 
	 * Creation Date: November 2 2021 
	 * Modified Date: November 15 2021 
	 * Description: generates a gradient of a particular color and stores it within an array 
	 * Parameters: int color; option number of which color to make a gradient of Return Value: N/A
	 * Throws/Exceptions: N/A
	 */

	public static void setColour(int colour) {

		// switch statement to act on input parameter value

		// INSIDE EACH CASE IS: a for loop and rgb color values
		// the for loop performs the following functionality:
		/*
		 * - for loop to decrease the color rgb values and get new color objects using
		 * setColor method - the objects are then stored into the array to be used later
		 * - decreasing the color values allows for the creation of a gradient effect -
		 * - program will keep looping until the array is filled
		 */

		switch (colour) {
		case 1:
			// predetermined color values found online
			// a shade of red
			a = 255;
			b = 0;
			c = 0;

			for (int i = 0; i < colors.length; i++) {

				colors[i] = setColour(a, b, c);
				if (a - 10 > 0)
					a -= 10;
			}

			break;

		case 2:
			// predetermined color values found online
			// a shade of blue
			a = 184;
			b = 201;
			c = 255;
			for (int i = 0; i < colors.length; i++) {

				colors[i] = setColour(a, b, c);
				if (a - 5 > 0 && b > 0) {
					a -= 5;
					b -= 5;
				}
			}
			break;
		case 3:
			// predetermined color values found online
			// a shade of green
			a = 148;
			b = 255;
			c = 94;
			for (int i = 0; i < colors.length; i++) {

				colors[i] = setColour(a, b, c);
				if (a - 3 > 0 && b - 3 > 0 && c - 3 > 0) {
					b -= 3;
					c -= 3;
				}
			}
			break;
		case 4:
			// predetermined color values found online
			// a shade of yellow/orange
			a = 255;
			b = 231;
			c = 144;
			for (int i = 0; i < colors.length; i++) {

				colors[i] = setColour(a, b, c);
				if (c - 4 > 0 && b - 4 > 0) {
					b -= 4;
					c -= 4;
				}
			}
			break;
		case 5:
			// predetermined color values found online
			// a shade of purple
			a = 202;
			b = 156;
			c = 255;
			for (int i = 0; i < colors.length; i++) {

				colors[i] = setColour(a, b, c);
				if (a - 5 > 0 && b > 0) {
					a -= 3;
					b -= 3;
				}
			}
		}

	}

	/*
	 * Method Name: phaser(); 
	 * Author: Swayam Sachdeva 
	 * Creation Date: November 2 2021
	 * Modified Date: November 15 2021 
	 * Description: generates a pattern onto the screen, that starts at the edges and works its way inwards 
	 * Parameters: N/A
	 * Throws/Exceptions: N/A
	 */
	public static void phaser() {
		// coordinates variables to hold the values where each pattern should be drawn
		int x = 0;
		int y = 0;
		// variable to cycle through colour gradient array
		int color = 1;
		// choosing a random number to get a random color gradient array
		int ranNum = (int) Math.floor(Math.random() * 5 + 1);
		setColour(ranNum); // generating color gradient with method

		// pattern moves from the top of the screen to the botton of the screen
		// condition to run the loop, only if the x coordinates is less than or equal to
		// the width
		while (x < width / 2 | x == width / 2) {
			// condition to draw the pattern while the y is under the height, so the pattern
			// does not go of screen
			while (y < height) {

				gc.setColor(colors[color]); // setting the colour to be a colour from the colours array

				gc.fillRect(x, y, 50, 50); // filling rect on top left side of screen
				gc.fillRect(x, height - y, 50, 50); // filling bottom left side of screen

				gc.fillRect(width - x, y, 50, 50); // filling top right of screen
				gc.fillRect(width - x, height - y, 50, 50); // filling bottom right of screen

				gc.sleep(65); // putting the thread to sleep, to get an animation effect

				y += 50; // making the pattern move across screen

			}

			y = 0; // making pattern start fronm the edges of the screen
			x += 50; // creating new column of pattern

			// making sure the color value does not go outside of the array length
			// conditional resets colors back to 1, in case the color value goes outside
			// array range
			if (color - 1 <= colors.length - 1) {
				color++;
			} else {
				color = 1;
			}

		}

		// resetting coordinate values, so that they can be used in next part of the
		// pattern
		x = 0;
		y = 0;

		color = 1; // resetting the color value, so pattern can start from the beginning of the
					// gradient again

		// repeating above pattern, but horizontally
		// so now pattern moves from left to right of the screen and vice versa
		while (y < height + 50 / 2) {

			while (x < width) {
				gc.setColor(colors[color]);

				gc.setColor(colors[color]); // setting the colour to be a colour from the colours array

				gc.fillRect(x, y, 50, 50); // filling rect on top left side of screen
				gc.fillRect(x, height - y, 50, 50); // filling bottom left side of screen

				gc.fillRect(width - x, y, 50, 50); // filling top right of screen
				gc.fillRect(width - x, height - y, 50, 50); // filling bottom right of screen

				gc.sleep(65);

				x += 50; // making the pattern move acroos the screen

			}

			x = 0; // reseting x values so that the pattern starts at the edges of the screen
			y += 50; // incrementing y value to create new rows

			// making sure the color value does not go outside of the array length
			// conditional resets colors back to 1, in case the color value goes outside
			// array range
			if (color + 1 <= colors.length - 1) {
				color += 1;
			} else {
				color = colors.length;
			}

		}

	}

	/*
	 * Method Name: save(); 
	 * Author: Swayam Sachdeva 
	 * Creation Date: November 2 2021
	 * Modified Date: November 15 2021 
	 * Description: generates a polygon pattern onto the screen, that starts at the top left corner and stops at bottom right corner 
	 * Parameters: N/A 
	 * Throws/Exceptions: N/A 
	 * Returns: N/A
	 */

	private static void save() {

		// array to store the x and y coordinates for the polygon shape
		int[] x = { 0, 25, 0, -25 };
		int[] y = { -25, 0, 25, 0 };

		// counter to count which row the pattern is on
		int counter = 1;
		// variable to iterate through colors array
		int color = 0;
		// sleepspeed variable to vary thread's sleep speed through the drawing process
		int sleepSpeed = 65;

		// random number to choose a random color gradient
		int ranNum = (int) Math.floor(Math.random() * 5 + 1);
		setColour(ranNum); // filling the color gradient with the random choice

		// while loop to ensure that the pattern does not go off the bottom of the
		// screen
		while (y[3] < height) {
			// setting the console's color to be one from the color gradient
			gc.setColor(colors[color]);

			// while loop to ensure that the pattern is drawn as long as it does not go off
			// the screen
			while (x[3] < width) {
				// drawing the polygon
				gc.fillPolygon(x, y, 4);
				gc.sleep(sleepSpeed); // putting thread to sleep

				// incrementing all the x coordinates of the polygon, so an effect of pattern
				// moving across screen is created
				for (int i = 0; i < x.length; i++) {
					x[i] += 50;
				}

			}
			// making sure the color value does not go outside of the array length
			// conditional resets colors back to 1, in case the color value goes outside
			// array range
			if (color + 1 <= colors.length - 1) {
				color++;
			} else {
				color = 1;
			}

			// incrementing counter so it can be used for if statements
			counter++;
			if (sleepSpeed - 5 > 0)
				sleepSpeed -= 2;

			// changing the x coordinate values of the polygon based on which type of row
			// they are on
			// this allows for the scaly pattern to be created
			if (counter % 2 == 0) {
				x[0] = 25;
				x[1] = 50;
				x[2] = 25;
				x[3] = 0;
			} else {
				x[0] = 0;
				x[1] = 25;
				x[2] = 0;
				x[3] = -25;
			}

			// incrementing the values of each y coordinate so a new row can be created
			for (int i = 0; i < y.length; i++) {
				y[i] += 25;
			}

		}

	}

	/*
	 * Method Name: arrow(); 
	 * Author: Swayam Sachdeva 
	 * Creation Date: November 2 2021
	 * Modified Date: November 15 2021 
	 * Description: generates a parabola pattern on the screen Parameters: int val; value that can either keep the parabola the same or invert it 
	 * Throws/Exceptions: N/A 
	 * Returns: N/A
	 */
	public static void arrow(int val) {
		// variables to keep track of where the pattern is being drawn
		int x = 0;
		int y = 0;
		// variable to allow for sleep speed variation
		int sleepSpeed = 10;
		// size variable so that the value stays constant through out the program
		int size = 50;
		// variable to count the loop #, so it can be used in the formula
		int counter = 1;
		// variable to iterate through the color gradient
		int color = 1;
		// random number to choose a random gradient color
		int ranNum = (int) Math.floor(Math.random() * 5 + 1);
		setColour(ranNum);

		boolean check = true;

		while (check) {
			// setting the color to equal a shade from the gradient
			gc.setColor(colors[color]);
			// while loop to draw the pattern, while x < width, so it doesn't go off screen
			while (x < width) {
				// drawing rectangle of the pattern
				gc.fillRect(x, y, size, size);
				gc.sleep(sleepSpeed); // putting thread to sleep

				x += size / 2; // incrementing x value by half of size, so rectangles overlap
				y += (int) Math.sqrt(x); // parabola formula

				// filling rectangles from opposite side of screen, so they collide at the
				// center
				gc.fillRect(width - x, y, size, size);
			}

			// resetting x value, so pattern restars at the corners of the screen
			x = 0;
			// moving the pattern upwards, while still overlapping it
			y = size / 3 * (val) * (counter);
			// incrementing counter value, so it can be used in formula above next time
			counter++;

			// making sure pattern does not go off screen
			// if condition to reflect which way parabola is going
			// this turns off the main while loop, once the pattern reaches the point of
			// going off screen

			if (val == -1 && y <= (height + 820) * -1) {
				check = false;
			}
			if (val == 1 && y >= 864) {
				check = false;
			}
			// varying the sleepspeed at every even loop, but not letting it drop below 2,
			// where the animation would get too fast
			if (sleepSpeed - 1 > 2 && counter % 2 == 0) {
				sleepSpeed--; // decrementing the speed
			}

			// iterating through all the shades of the color variable
			if (color + 1 <= colors.length - 1) {
				color++;
			} else {
				color = 1; // resetting color value so it does not go beyond array index
				sleepSpeed = 10; // resetting sleepspeed so the speed gets faster along with the color changes
			}

		}

	}

	/*
	 * Method Name: eraser(); 
	 * Author: Swayam Sachdeva 
	 * Creation Date: November 2 2021
	 * Modified Date: November 15 2021 
	 * Description: generates an animation that paints the screen black 
	 * Parameters: N/A 
	 * Throws/Exceptions: N/A 
	 * Returns: N/A
	 */
	public static void eraser() {
		// coordinates to hold where pattern is being drawn, so animation can be created
		int x = 0;
		int y = 0;
		// size variable, so it can be modified throughout the method
		int size = 50;
		// setting the color to black, so screen can be "cleared" by painting it black
		// while loop to make sure pattern only draws, as long as it does not go over
		// the screen

		gc.setColor(Color.BLACK);
		while (y < height) {
			// while loop to make sure pattern does not go off screen
			while (x < width) {
				// drawing a rectangle at specified point
				gc.fillRect(x, y, size, size);
				gc.sleep(45); // putting thread to sleep to creation animation illusion
				x += 50; // incrementing x value to create a moving across screen effect, from left to
							// right
			}
			// modifying size, so that the screen is painted across faster
			size += 5;
			y += 50; // moving program to next "row" to paint

			// while loop to move the pattern back from the right side of screen, back to
			// the left side of the screen
			while (x >= 0) {
				// drawing a rectangle at specified point
				gc.fillRect(x, y, size, size);
				gc.sleep(45); // putting thread to sleep to creation animation illusion

				x -= 50; // moving pattern back across screen, right to left
			}
			// modifying size, so that the screen is painted across faster
			size += 5;
			// moving program to next "row" to paint
			y += 50;

		}
	}

	public static void main(String[] args) {
		// main screensaver animation
		while (true) {
			gc.setBackgroundColor(Color.BLACK);
			save();
			arrow(-1);
			arrow(1);
			phaser();
			eraser();
		}

	}

}
