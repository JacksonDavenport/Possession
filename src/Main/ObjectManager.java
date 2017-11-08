package Main;

import java.util.ArrayList;

import Objects.Actor;
import Objects.Building;

public class ObjectManager implements ActionManager, MovementManager{

	Actor actor;
	
	public ObjectManager(Actor a) {
		this.actor = a;
	}
	
	public void action() {
		
	}
	
	public void move(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		
	}
	
	public int distanceRemainingLeftSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		if(hitbox.getLeft() <= 0)
			return 0;
		
		int distanceToMove = dx;
		int distance;
		for(int i = 0; i < objects.size(); i++) {
			Actor o = objects.get(i);
			if(hitbox.getLeft() >= o.getRight() && withinVertical(hitbox, o)) {
				distance = hitbox.getLeft() - o.getRight();
				if(distance < dx) {
					Logger.logFinest("Will collide left side with " + o.toString() + " with speed: " + dx);
					distanceToMove = Math.min(distanceToMove,  distance);
				}
			}
		}
		return distanceToMove;
	}
	
	public int distanceRemainingRightSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		if(hitbox.getRight() >= PossessionDriver.MAP_WIDTH)
			return 0;
		
		int distanceToMove = dx;
		int distance;
		for(int i = 0; i < objects.size(); i++) {
			Actor o = objects.get(i);
			if(hitbox.getRight() <= o.getLeft() && withinVertical(hitbox, o)) {
				distance = o.getLeft() - hitbox.getRight();
				if(distance < dx) {
					Logger.logFinest("Will collide right side with " + o.toString() + " with speed: " + dx);
					distanceToMove = Math.min(distanceToMove,  distance);
				}
			}
		}
		return distanceToMove;
	}
	
	public int distanceRemainingTopSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		if(hitbox.getTop() <= 0)
			return 0;
		
		int distanceToMove = dy;
		int distance;
		for(int i = 0; i < objects.size(); i++) {
			Actor o = objects.get(i);
			if(hitbox.getTop() >= o.getBottom() && withinHorizontal(hitbox, o)) {
				distance = hitbox.getTop() - o.getBottom();
				if(distance < dy) {
					Logger.logFinest("Will collide top side with " + o.toString() + " with speed: " + dy);
					distanceToMove = Math.min(distanceToMove,  distance);
				}
			}
		}
		return distanceToMove;
	}
	
	public int distanceRemainingBottomSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		if(hitbox.getBottom() >= PossessionDriver.MAP_HEIGHT)
			return 0;
		
		int distanceToMove = dy;
		int distance;
		for(int i = 0; i < objects.size(); i++) {
			Actor o = objects.get(i);
			if(hitbox.getBottom() <= o.getTop() && withinHorizontal(hitbox, o)) {
				distance = o.getTop() - hitbox.getBottom();
				if(distance < dy) {
					Logger.logFinest("Will collide bottom side with " + o.toString() + " with speed: " + dx);
					distanceToMove = Math.min(distanceToMove, distance);
				}
			}
		}
		return distanceToMove;
	}
	
	public boolean willCollideLeftSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		return dx <= this.distanceRemainingLeftSide(hitbox, objects, dx, dy);
	}
	
	public boolean willCollideRightSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		return dx <= this.distanceRemainingRightSide(hitbox, objects, dx, dy);
	}

	public boolean willCollideTopSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		return dy <= this.distanceRemainingTopSide(hitbox, objects, dx, dy);
	}

	public boolean willCollideBottomSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy) {
		return dy <= this.distanceRemainingBottomSide(hitbox, objects, dx, dy);
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
	
	public void setActor(Actor a) {
		this.actor = a;
	}

}
