package warehouse;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.util.Delay;

public class test {
	public static void main(String[] test) {
		final String LEFT = "left";
		final String RIGHT = "right";
		final String FORWARD = "forward";
		String[] directions = { FORWARD, LEFT, LEFT, FORWARD, FORWARD, RIGHT, FORWARD };
		Robot testing = new Robot();
		LightSensor left = testing.LEFT;
		LightSensor right = testing.RIGHT;
		LCD.drawString("hello there", 3, 4);
		Button.waitForAnyPress();
		int[] boundaries = testing.calibrateLightSensors();
		int lMax = boundaries[0];
		int lMin = boundaries[1];
		int rMax = boundaries[4];
		int rMin = boundaries[5];
		Button.waitForAnyPress();
		int index = 0;
		testing.forward();
		while (index < directions.length) {
			int lVal = left.getLightValue();
			int rVal = right.getLightValue();
			if (lVal <= lMin + 3 && rVal <= rMin + 3) {
				testing.stop();
				testing.travel(testing.LENGTH / 10);
				switch (directions[index++]) {
				case LEFT:
					testing.rotate(90, false);
					break;
				case RIGHT:
					testing.rotate(-90, false);
					break;
				case FORWARD:
					testing.forward();
					break;
				}
			} else {
				testing.forward();
			}
			Delay.msDelay(50);
		}
	}
}