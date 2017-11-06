package Main;

import java.util.ArrayList;

import Objects.Actor;
import Objects.Building;

public interface MovementManager {
	
	public void move(Actor hitbox, ArrayList<Building> objects, int dx, int dy);
			
	public int distanceRemainingLeftSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy);
	public int distanceRemainingRightSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy);
	public int distanceRemainingTopSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy);	
	public int distanceRemainingBottomSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy);
	
	public boolean willCollideLeftSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy);
	public boolean willCollideRightSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy);
	public boolean willCollideTopSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy);	
	public boolean willCollideBottomSide(Actor hitbox, ArrayList<Building> objects, int dx, int dy);	
}
