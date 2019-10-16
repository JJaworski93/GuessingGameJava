import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;


import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	
	// setting game start properties
	private boolean start = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	// setting the time of ball (ball speed)
	private Timer timer;
	private int delay = 8;
	
	// x-axis and y-axis, starting position
	private int playerX = 310;
	
	private int ballX = 350;
	private int ballY = 400;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	//creating an object of MapGenerator class
	private  MapGenerator map;
	
	
	public GamePlay() {
		
	// object of the MapGenerator
		map = new MapGenerator(3, 7);
		
	// adding KeyListener
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
		
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.darkGray);
		g.fillRect(1, 1, 692, 592);
		
		//drawing map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.white);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//score board
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 35);
		
		
		//paddle
		g.setColor(Color.white);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball 
		g.setColor(Color.green);
		//fillOval will display oval figure - ball
		g.fillOval(ballX, ballY, 20, 20);
		//win case
		if(totalBricks <= 0) {
			start = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won! Your Score: "+score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart the Game", 230, 350);
			
		}
		
		//game over? is ball out?
		if(ballY > 570) {
			start = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over! Your Score: "+score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart the Game", 230, 350);
			
		}
		
		//g.dispose will release all graphics resources taken from the PC by the Graphics instance
		g.dispose();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(start) {
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}
			//
			//map.map - 1st map is private MapGenerator map; and 2nd is public int map[][] from MapGenerator class
			
			A: for(int i = 0; i < map.map.length; i++) {
				for(int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						//rectangle around brick
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score +=5;
							
							if(ballX + 19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
								
							} else {
								ballYdir = -ballYdir;
							}
							
							break A;
						}
						
					}
				}
			}
			
		//is the ball touching the borders?
			ballX += ballXdir;
			ballY += ballYdir;
			
			//left border
			if(ballX < 0) {
				ballXdir = -ballXdir;
			}
			//top border
			if(ballY < 0) {
				ballYdir = -ballYdir;
			}
			//right border
			if(ballX > 670) {
				ballXdir = -ballXdir;
			}
		}
		
		
		//we won't lose the graphics when moving the paddle
		repaint();
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >=585) {
				playerX = 585;
			} else {
				moveRight();
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <= 10) {
				playerX = 10;
			} else {
				moveLeft();
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!start) {
				start = true;
				ballX = 350;
				ballY = 400;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);
				
				repaint();
			}
		}
	
		
	}
	
	public void moveRight() {
		start = true;
		playerX+=20;
	}
	
	public void moveLeft() {
		start = true;
		playerX-=20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
  // panel for our game, KeyListener for arrows and ActionListener for ball to move
	
	
}
