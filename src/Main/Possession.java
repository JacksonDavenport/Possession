package Main;
import javax.swing.JFrame;

public class Possession{

    public static void main(String[] args) {
       JFrame j1 = new JFrame();
       j1.setSize(PossessionDriver.SCREEN_WIDTH, PossessionDriver.SCREEN_HEIGHT);

       j1.setTitle("Possession");
       j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       PossessionCanvas g1 = new PossessionCanvas();
       j1.add(g1);
       j1.setVisible(true);
    }
}

/*

	Primary Goals
		-Get Player Movement				X
		-Get Arena Set Up					X
		-Get Enemy basic melee movement		X
		-Possess to switch between people
		-Pistol
		
	Secondary Goals
		-Different Enemies
		-More weapons
		-Enemy strategy (encircle then move in
		-User Interface
		
	Tertiary Goals
		-Images
		-Readable Code
		-Different strategy per unit
		



*/
	