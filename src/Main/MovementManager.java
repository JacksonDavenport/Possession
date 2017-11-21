package Main;

import java.util.ArrayList;

import Objects.Actor;
import Objects.Building;

public interface MovementManager {
	
	public void move(Actor hitbox, int dx, int dy);
			
	public int distanceRemainingLeftSide(Actor hitbox, int dx, int dy);
	public int distanceRemainingRightSide(Actor hitbox, int dx, int dy);
	public int distanceRemainingTopSide(Actor hitbox, int dx, int dy);	
	public int distanceRemainingBottomSide(Actor hitbox, int dx, int dy);
	
	public boolean willCollideLeftSide(Actor hitbox, int dx, int dy);
	public boolean willCollideRightSide(Actor hitbox, int dx, int dy);
	public boolean willCollideTopSide(Actor hitbox, int dx, int dy);	
	public boolean willCollideBottomSide(Actor hitbox, int dx, int dy);	
}
