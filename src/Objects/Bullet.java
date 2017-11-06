package Objects;
/*
 Base bullet class, all bullets have the same speed
 They are removed from the canvas on contact of building or zombie, or off screen
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bullet extends Actor{

	private final int BULLET_SPEED = 14;
	private static final int BULLET_SIZE = 4;

	private final int damage;
	
	private int dx,dy;
	private boolean fromTurret;
	private BufferedImage imageToDraw;
	
	public Bullet( int startX, int startY, int targetX, int targetY, int aDamage){
		super(startX, startY, BULLET_SIZE, BULLET_SIZE, Color.RED);
		
		//Find how to move the bullets
		double hypot = Math.sqrt(Math.pow(Math.abs(targetX-startX),2) + Math.pow(Math.abs(targetY-startY),2));
		double factor = hypot/BULLET_SPEED;
		dx = (int) Math.round( (targetX - startX)/factor );
		dy = (int) Math.round( (targetY - startY)/factor );
		
		damage = aDamage;
	}

	public void act(Graphics2D win){
		drawActor(win);
		move();
	}

	public void drawActor(Graphics2D win){
		super.drawActor(win);
		//win.drawImage(imageToDraw, getXPos(), getYPos(), null);
	}
    public void move(){
		getHitBox().translate(dx,dy);
    }
    public int getDamage(){
    	return damage;
    }
}