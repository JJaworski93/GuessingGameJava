import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		//creating GamePlay object
		GamePlay gamePlay = new GamePlay();
		// setting the background
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Arkanoid");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//adding game panel (GamePlay object) to our frame
		frame.add(gamePlay);
		

	}

}
