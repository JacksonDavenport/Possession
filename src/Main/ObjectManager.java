package Main;

import java.util.ArrayList;

import Objects.Actor;
import Objects.Building;

public class ObjectManager implements ActionManager, MovementManager{

	Actor actor;
	ArrayList<Building> objects;
	
	public ObjectManager(ArrayList<Building> objects, Actor a) {
		this.actor = a;
		this.objects = objects;
	}
	
	public ObjectManager(ArrayList<Building> objects) {
		this.objects = objects;
	}
	
	public void action() {
		
	}
	
	public void move(Actor hitbox, int dx, int dy) {
		
	}
	
	public int distanceRemainingLeftSide(Actor hitbox, int dx, int dy) {
		if(hitbox.getLeft() <= 0)
			return 0;
		
		dx = Math.abs(dx);
		int distanceToMove = dx;
		int distance;
		for(int i = 0; i < objects.size(); i++) {
			Actor o = objects.get(i);
			if(hitbox.getLeft() >= o.getRight() && withinVertical(hitbox, o)) {
				distance = hitbox.getLeft() - o.getRight();
				if(distance < dx) {
					Logger.logDebug("Will collide left side with " + o.toString() + " with speed: " + dx);
					distanceToMove = Math.min(distanceToMove,  distance);
				}
			}
		}
		return distanceToMove;
	}
	
	public int distanceRemainingRightSide(Actor hitbox, int dx, int dy) {
		if(hitbox.getRight() >= PossessionDriver.MAP_WIDTH)
			return 0;
		
		dx = Math.abs(dx);
		int distanceToMove = dx;
		int distance;
		for(int i = 0; i < objects.size(); i++) {
			Actor o = objects.get(i);
			if(hitbox.getRight() <= o.getLeft() && withinVertical(hitbox, o)) {
				distance = o.getLeft() - hitbox.getRight();
				if(distance < dx) {
					Logger.logDebug("Will collide right side with " + o.toString() + " with speed: " + dx);
					distanceToMove = Math.min(distanceToMove,  distance);
				}
			}
		}
		return distanceToMove;
	}
	
	public int distanceRemainingTopSide(Actor hitbox, int dx, int dy) {
		if(hitbox.getTop() <= 0)
			return 0;
		
		dy = Math.abs(dy);
		int distanceToMove = dy;
		int distance;
		for(int i = 0; i < objects.size(); i++) {
			Actor o = objects.get(i);
			if(hitbox.getTop() >= o.getBottom() && withinHorizontal(hitbox, o)) {
				distance = hitbox.getTop() - o.getBottom();
				if(distance < dy) {
					Logger.logDebug("Will collide top side with " + o.toString() + " with speed: " + dy);
					distanceToMove = Math.min(distanceToMove,  distance);
				}
			}
		}
		return distanceToMove;
	}
	
	public int distanceRemainingBottomSide(Actor hitbox, int dx, int dy) {
		if(hitbox.getBottom() >= PossessionDriver.MAP_HEIGHT)
			return 0;
		
		dy = Math.abs(dy);
		int distanceToMove = dy;
		int distance;
		for(int i = 0; i < objects.size(); i++) {
			Actor o = objects.get(i);
			if(hitbox.getBottom() <= o.getTop() && withinHorizontal(hitbox, o)) {
				distance = o.getTop() - hitbox.getBottom();
				if(distance < dy) {
					Logger.logDebug("Will collide bottom side with " + o.toString() + " with speed: " + dx);
					distanceToMove = Math.min(distanceToMove, distance);
				}
			}
		}
		return distanceToMove;
	}
	
	public void passMovement(Actor a, int dx, int dy) {
		if(!willIntersectArena(a, dx, dy)) {
			a.passMovement(dx,  dy);
		}
		else {
			Logger.logError(a.toString() + " would have collided with the arena");
		}
	}
	
	public boolean willCollideLeftSide(Actor hitbox, int dx, int dy) {
		return dx <= this.distanceRemainingLeftSide(hitbox, dx, dy);
	}
	
	public boolean willCollideRightSide(Actor hitbox, int dx, int dy) {
		return dx <= this.distanceRemainingRightSide(hitbox, dx, dy);
	}

	public boolean willCollideTopSide(Actor hitbox, int dx, int dy) {
		return dy <= this.distanceRemainingTopSide(hitbox, dx, dy);
	}

	public boolean willCollideBottomSide(Actor hitbox, int dx, int dy) {
		return dy <= this.distanceRemainingBottomSide(hitbox, dx, dy);
	}
	
	public boolean withinVertical(Actor hitbox, Actor object) {
		return (hitbox.getBottom() > object.getTop() && hitbox.getTop() < object.getBottom());
	}
	
	public boolean withinHorizontal(Actor hitbox, Actor object) {
		return (hitbox.getRight() > object.getLeft() && hitbox.getLeft() < object.getRight());
	}
	
	public boolean containsObject(Actor a) {
		return actor.getHitBox().contains(a.getHitBox());
	}
	
	public boolean intersectsObject(Actor a) {
		return actor.getHitBox().intersects(a.getHitBox());
	}
	
	public boolean willIntersectArena(Actor a, int dx, int dy) {
		int x = a.getXPos() + dx;
		int y = a.getYPos() + dy;
		int w = a.getWidth();
		int h = a.getHeight();
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getHitBox().intersects(x,y,w,h)) {
				return true;
			}
		}
		return false;
	}
	
	public void setActor(Actor a) {
		this.actor = a;
	}

}
