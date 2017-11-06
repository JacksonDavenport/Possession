package Objects;
/*
 *	A building is an Actor that cannot move, and whose hitbox collides with
 *	the player, enemies, bullets, etc.
 *	
 *	Own object for naming convention
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Building extends Actor{
	
	private static final Color defaultColor = new Color(205,133,63);
	
	public Building(int x, int y, int w, int h) {
		super(x, y, w, h, defaultColor);
	}
	public Building(int x, int y, int w, int h, BufferedImage i) {
		super(x, y, w, h, i);
	}
	
	public void act(Graphics2D win){
		super.drawActor(win);
	}
}