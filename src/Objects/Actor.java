package Objects;
/*
 Base class for almost all things
*/

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Actor{
	
	public final int EAST = 0;
	public final int NORTH = 90;
	public final int WEST = 180;
	public final int SOUTH = 270;

	private int xVel, yVel;
	private int width,height;
	Rectangle  hitBox;
	Color col;
	private BufferedImage imageToDraw;

	public Actor(int x, int y, int w, int h, Color c) {
		col = c;
		width = w;
		height = h;
		hitBox = new Rectangle(x,y,w,h);
		imageToDraw = null;
	}
	
	public Actor(int x, int y, int w, int h, BufferedImage i) {
		col = null;
		width = w;
		height = h;
		hitBox = new Rectangle(x,y,w,h);
		imageToDraw = i;
	}


	public void drawActor(Graphics2D win) {
		if(imageToDraw == null){
			win.setColor(col);
			win.fill(hitBox);
		}
		else {
			win.drawImage(imageToDraw, getXPos(), getYPos(), null);
		}
	}

	public void setWidth(int value){
		width = value;
		hitBox.setSize(width,height);
	}
	public void setHeight(int value){
		height = value;
		hitBox.setSize(width,height);
	}
	public void setColor(Color value){
		col = value;
	}
	public int getXPos() {
		return (int) hitBox.getX();
	}
	public int getYPos() {
		return (int) hitBox.getY();
	}
	public void setSpeed(int value) {
		setXVel(value);
		setYVel(value);
	}
	public void setXVel(int value) {
		xVel = value;
	}
	public void setYVel(int value) {
		yVel = value;
	}
	public Color getColor(){
		return col;
	}
	public int getXVel() {
		return xVel;
	}
	public int getYVel() {
		return yVel;
	}
	public Rectangle getHitBox() {
		return hitBox;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public int getBottom(){
		return getYPos() + getHeight();
	}
	public int getRight(){
		return getXPos() + getWidth();
	}
	public int getLeft(){
		return getXPos();
	}
	public int getTop(){
		return getYPos();
	}
	public int getXCenter(){
		return getXPos() + getWidth()/2;
	}
	public int getYCenter(){
		return getYPos() + getHeight()/2;
	}
	public void setLocation(int x,int y){
		hitBox.setLocation(x,y);
	}
	
	public String toString() {
		return (this.getClass().getSimpleName() + ", " + this.getHitBox().toString());
	}
}