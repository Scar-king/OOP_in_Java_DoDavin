package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	//Screen Panel
	final int originalTileSize =16; //16x 16
	final int scale =3;
	
	final int tileSize = originalTileSize*scale; //make element 48x48
	final int maxScreenRow =12;
	final int maxScreenCol =16;
	final int screenWidth = tileSize*maxScreenCol; //768 pxs of screen
	final int screenHeight = tileSize*maxScreenRow; //576 pxs
	
	//FPS
	int fps = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; //to start and to stop
	
	//initialize player position & speed
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	//using sleep method
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//		double drawInterval = 1000000000/fps; //draw the screen within 0.01666 s
//		double nextDrawTime = System.nanoTime() +drawInterval;
//		//game loop or core
//		while(gameThread != null) {
////			long currentTime = System.nanoTime();
////			System.out.println("Current time: "+currentTime);
//			
//			// update character position
//			update();
//			
//			//draw the screen with the update postion of character
//			repaint();
//			
//			try {
//				
//			double remainingTime = nextDrawTime -System.nanoTime();
//			remainingTime = remainingTime/1000000;
//			
//			if(remainingTime <0) {
//				remainingTime =0;
//			}
//			
//			
//			Thread.sleep((long) remainingTime);
//			nextDrawTime +=drawInterval;
//			
//		}catch(InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//}
	
	
	//delta method
	public void run() {
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >=1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer =0;
			}
		}
	}
	public void update() {
		//The Y-axis decreases as we move up
		if(keyH.upPressed == true) {
			playerY -=playerSpeed;
		}
		//The Y-axis increases as we move down
		else if(keyH.downPressed ==true) {
			playerY +=playerSpeed;
		}
		//The X-axis decreases as we move left.
		else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		//The X-axis increases as we move right
		else if(keyH.rightPressed == true) {
			playerX +=playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY,tileSize ,tileSize);
		g2.dispose();
	}
	
}
