package Gameinjava;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
     //For window created
		int boardwidth=600;
		int boardheight=boardwidth;
		
		JFrame frame = new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(boardwidth,  boardheight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Snakegame snakegame =new Snakegame(boardwidth, boardheight);
		frame.add(snakegame);
		frame.pack();
        snakegame.requestFocus();
}
}
