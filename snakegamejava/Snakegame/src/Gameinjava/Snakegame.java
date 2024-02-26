package Gameinjava;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Snakegame extends JPanel implements ActionListener, KeyListener {
	private class Title
	{
		int x;
		int y;
		
		Title(int x,int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	int boardwidth;
	int boardheight;
	int titleSize = 25;
	
	//Snake Head
	Title snakeHead;
	ArrayList<Title>snakeBody;
	
	Title food;
	Random random;
	
	//Gamelogic
	Timer gameloop;
	
    //for moving snake
	int velocityX;
	int velocityY;
	boolean gameOver = false;
	Snakegame(int boardWidth, int boardHeight)
	{
		this.boardwidth = boardWidth;
		this.boardheight = boardHeight;
		setPreferredSize(new Dimension(this.boardwidth, this.boardheight));
		setBackground(Color.black);
		addKeyListener(this);
		setFocusable(true);
		
		snakeHead = new Title(5,5);
		snakeBody = new ArrayList<Title>();
		
		food = new Title(10, 10);
		random = new Random();
		placeFood();
		
		velocityX = 0;
		velocityY = 0;
		
		gameloop = new Timer(100, this);
		gameloop.start();
	}
	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}

	private void draw(Graphics g) {
		
	   //food
		g.setColor(Color.white);
		g.fill3DRect(food.x * titleSize, food.y * titleSize, titleSize, titleSize, true);
		
		
		
		//snake as in rectangle shape
		g.setColor(Color.green);
		g.fill3DRect(snakeHead.x * titleSize , snakeHead.y * titleSize, titleSize, titleSize, true);
		
		
	    //snake body
		for(int i=0; i< snakeBody.size(); i++)
		{
			Title snakePart = snakeBody.get(i);
			g.fill3DRect(snakePart.x *titleSize, snakePart.y *titleSize, titleSize, titleSize, true);

		}
		
		//score display
		g.setFont(new Font("Arial", Font.PLAIN,16));
		if(gameOver)
		{
			g.setColor(Color.red);
			g.drawString("Game Over: "+ String.valueOf(snakeBody.size()), titleSize - 16, titleSize);
		}
		else
		{
			g.drawString("Score: "+ String.valueOf(snakeBody.size()), titleSize - 16, titleSize);
		}
	}

  //food place
	public void placeFood() {
		
		food.x = random.nextInt(boardwidth/titleSize); //600/25 =24 (random num)
		food.y = random.nextInt(boardheight/titleSize);
	}
	
	public boolean collision(Title title1, Title title2)
	{
		return title1.x == title2.x && title1.y == title2.y;
	}

	public void move() {
		
		//eat food
		if(collision(snakeHead, food))
		{
			snakeBody.add(new Title(food.x, food.y));
			placeFood();
		}
		
		
		
		//snakebody
		for(int i=snakeBody.size()-1; i>=0; i--)
		{
			Title snakePart = snakeBody.get(i);
			if(i==0)
			{
				snakePart.x = snakeHead.x;
				snakePart.y = snakeHead.y;
			}
			else{
				Title prevSnakePart = snakeBody.get(i-1);
			snakePart.x = prevSnakePart.x;
			snakePart.y = prevSnakePart.y;
			}
		}
    		
		//snake head
		snakeHead.x += velocityX;
		snakeHead.y += velocityY;
		
		
		//game over
		for(int i=0; i<snakeBody.size(); i++)
		{
			Title snakePart = snakeBody.get(i);
			
			if(collision(snakeHead, snakePart))
			{
				gameOver = true;
			}
		}
		
		if(snakeHead.x*titleSize <0 || snakeHead.x*titleSize > boardwidth || snakeHead.y *titleSize < 0 || snakeHead.y *titleSize > boardheight)
		{
			gameOver = true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		if(gameOver)
		{
			gameloop.stop();
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
	if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1)
	{
		velocityX = 0;
		velocityY = -1;
	}
	else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1)
	{
		velocityX = 0;
		velocityY = 1;
	}
	else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1)
	{
		velocityX = -1;
		velocityY = 0;
	}
	
	else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1)
	{
		velocityX = 1;
		velocityY = 0;
	}
	

	}

//Not required this method
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
