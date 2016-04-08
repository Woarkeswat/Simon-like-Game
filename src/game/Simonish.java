package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import assignment6.ColorPiece;
import assignment6.MenuBar;
import assignment6.ScorePanel;
/**
 *  
 * @author Devin Parsons
 *Class that runs the Simon game
 */
public class Simonish implements MouseListener{

	ScorePanel scoreBoard;
	ColorPiece red;
	ColorPiece blue;
	ColorPiece yellow;
	ColorPiece green;
	MenuBar menu;
	JFrame frame;
	Container pane;
	Container color;
	Vector<Integer> input;
	Vector<Integer> player;
	ArrayList<Integer> scoreList;
	ArrayList<String> nameList;
	Timer displayTimer;
	int displayCounter;
	int scoreCounter;
	int highScore;
	int score;
	int speed;
	boolean compTurn;
	int totalGames;
	double averageScore;
	
	/**
	 * Simonish constructor that creates and implements other classes to run the game
	 */
	public Simonish(){
		frame = new JFrame();
		frame.setTitle("Simon Supreme");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		pane.setPreferredSize(new Dimension(450, 500));
		color = new Container();
		color.setLayout(new GridLayout(2, 2));
	
		displayCounter = 0;
		scoreCounter = 0;
		score = 0;
		highScore = 0;
		speed = 500;
		compTurn = true;
		totalGames = 0;
		averageScore = 0;
		
		menu = new MenuBar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menu.panel1){
					Color color = JColorChooser.showDialog(frame,
				               "Choose background color", Color.white);
					if (color == null){
						return;
					}
					red.setNewColor(color.darker());
				}
				else if(e.getSource() == menu.panel2){
					Color color = JColorChooser.showDialog(frame,
				               "Choose background color", Color.white);
					if (color == null){
						return;
					}
					blue.setNewColor(color.darker());
				}
				else if (e.getSource() == menu.panel3){
					Color color = JColorChooser.showDialog(frame,
				               "Choose background color", Color.white);
					if (color == null){
						return;
					}
					yellow.setNewColor(color.darker());
				}
				else if (e.getSource() == menu.panel4){
					Color color = JColorChooser.showDialog(frame,
				               "Choose background color", Color.white);
					if (color == null){
						return;
					}
					green.setNewColor(color.darker());
				}
				else if (e.getSource() == menu.fast){
					speed = 100;
				}
				else if (e.getSource() == menu.med){
					speed = 250;
				}
				else if (e.getSource() == menu.slow){
					speed = 500;
				}
				else if (e.getSource() == menu.highScore){
					String topTen = "";
					for (int i=0; i<nameList.size(); i++){
						if (scoreList.get(i)!=-1)
							topTen+=nameList.get(i)+ ": score of " + scoreList.get(i)+"\n";
					}
					JOptionPane.showMessageDialog(null, "Top Ten High Scores: \n" +
							topTen);
				}
				else if (e.getSource() == menu.history){
					try {
						Scanner read = new Scanner(new File("games.txt"));
						Scanner read2 = new Scanner(new File("average.txt"));
						if(!read.hasNext())
							return;
						else if(!read2.hasNext())
							return;
						totalGames += read.nextInt();
						averageScore += read2.nextDouble();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					averageScore = (averageScore / totalGames);
					JOptionPane.showMessageDialog(null, "Total games played: " + totalGames +
					"\nAverage Score: " + averageScore);
				}
				else if (e.getSource() == menu.about){
					JOptionPane.showMessageDialog(null, "New Features: \n" + "1.You can change color of the panels!\n" +
				"2.You can change the speed of the game!\n" + "3.You can view the previous high scores!\n" +
				"4.You can add your name to the list of high scores!");
				}
				else if (e.getSource() == menu.rules){
					JOptionPane.showMessageDialog(null, "1. Press the 'Start Button' to begin game.\n" + 
				"2. Pay attention to the sequence of colors the computer displays.\n" + 
				"3. Press the colors in the same order.\n" + "4. Continue until you miss.\n" +
				"5. Have fun!");
				}
			}
		});
		
		scoreBoard = new ScorePanel(this);
		red = new ColorPiece(Color.red, this);
		blue = new ColorPiece(Color.blue, this);
		yellow = new ColorPiece(Color.yellow, this);
		green = new ColorPiece(Color.green, this);
		
		scoreBoard.setBounds(0, 0, 450, 50);
		
		/**
		 * uses the scorepanel class to create the "start" button
		 */
		scoreBoard.getStartBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		
		player = new Vector<>();
		input = new Vector<>();
		scoreList = new ArrayList<>();
		nameList = new ArrayList<>();
		
		pane.add(scoreBoard, BorderLayout.NORTH);
		
		color.add(red);
		color.add(blue);
		color.add(yellow);
		color.add(green);
		pane.add(color, BorderLayout.CENTER);
		
		/**
		 *associates computer player color flashes with integer
		 */
		displayTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {				
				if (displayCounter == input.size()){
					displayTimer.stop();
					return;
				}
				switch (input.get(displayCounter)){
				case 1:
					red.blink(speed);
					break;
				case 2:
					blue.blink(speed);
					compTurn = false;
					break;
				case 3:
					yellow.blink(speed);
					break;
				case 4:
					green.blink(speed);
					break;
				}
				displayCounter++;
				compTurn = false;
			}
		});
	
		frame.setJMenuBar(menu);
		frame.pack();
		frame.setLocationRelativeTo(null);	
		frame.setVisible(true);
		
		populateList();
	}
	
/**
 * calls computer sequence function
 * @return 
 */
	
	public void populateList(){
		for (int i = 0; i < 10; i++){
			scoreList.add(-1);			
			nameList.add("");
		}
		int i=0; 
		try {
			Scanner results = new Scanner(new File("results.txt"));
			while (results.hasNext()){
				nameList.set(i, results.next());
				scoreList.set(i, results.nextInt());
				i++;
			}
		} catch (FileNotFoundException e1){
			e1.printStackTrace();
		}
	}
	/**
	 * finds a random number and adds that to the computer's sequence
	 */
	public void displayComSeq(){	
		Thread t = new Thread(new Runnable() {
			public void run(){
				compTurn = true;
				Random random = new Random();
				int val = random.nextInt(5 - 1)+1;
				input.add(val);
				displayCounter = 0;
				displayTimer.start();
			}
		});
		t.start();
	}
	
	public void findGames(){
		Writer list;
		try {
			list = new PrintWriter("games.txt");
			list.write(new Integer(totalGames).toString());
			list.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findAvg(){
		averageScore = score;
		/*if (averageScore == 0){
			return;
		}*/
		//else{
			Writer avg;
			try {
				avg = new PrintWriter("average.txt");
				avg.write(new Double(averageScore).toString());
				avg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		//}
	}
	
	public void startGame(){
		displayComSeq();
	}
	
	/**
	 * compares each click the user makes with the computer's sequence and returns an action
	 */
	public void compare(){
		boolean gameOver = false;
		for (int j = 0; j < player.size(); j++){
			/**
			 * gameover screen
			 */
			if (player.get(j) != input.get(j)){
				if (score <= 3){JOptionPane.showMessageDialog(pane, "Game Over!\n" + "You got the rank "
						+ "of a Joker with a score of " + score + '.');}
				else if (score > 3 && score <= 10){JOptionPane.showMessageDialog(pane, "Game Over!\n" +
				"You got the ranking of a Knight with a score of " + score + '.');}
				else if (score > 10 && score <=20){JOptionPane.showMessageDialog(pane, "Game Over!\n" +
				"You got the ranking of a King with a score of " + score + '.');}
				else if (score >20){JOptionPane.showMessageDialog(pane, "Game Over!\n" +
				"You got the ranking of a Master with a score of " + score + '.');}
				gameOver = true;
				if (score > highScore){
					highScore = score;
					scoreBoard.setHighScore(highScore);
				}
				player.clear();
				input.clear();
				list();
				++totalGames;
				findGames();
				findAvg();
				score = 0;
				scoreBoard.setScore(score);
			}
		}
		/**
		 * starts new round after a successful round
		 */
		if (player.size() == input.size() && !gameOver){
			player.clear();
			score++;
			scoreBoard.setScore(score);
			startGame();
			}
	}
	
	void list(){
		if (score > scoreList.get(9)){
			scoreList.add(score);
			String name=null;
			while(true){
				name = JOptionPane.showInputDialog(null,"Enter name for new High Score:");
				name = name.trim();
				if(name.equals("") || name.isEmpty()){
				   continue;
				}
				else
					break;
			}
			nameList.add(name);
			int index = scoreList.size()-1;
			int index2 = index - 1;
			while (scoreList.get(index) > scoreList.get(index2)){
				int temp = scoreList.get(index);
				scoreList.set(index, scoreList.get(index2));
				scoreList.set(index2, temp);
				String temp2 = nameList.get(index);
				nameList.set(index, nameList.get(index2));
				nameList.set(index2, temp2);
				index = index - 1;
				index2 = index2 - 1;
				if (index2 < 0){
					break;
				}
			}
			scoreList.remove(10);
			nameList.remove(10);
		}
		try {
			PrintWriter fout = new PrintWriter(new FileWriter("results.txt"));
			if (!nameList.isEmpty()){
				for (int i = 0; i < 10; i++){
					if (i < nameList.size())
						if (scoreList.get(i)!=-1)
							fout.println(nameList.get(i)+" " + scoreList.get(i) + "\n");
				}
			}
			fout.flush();
			fout.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Simonish();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * associates the clicks the player makes with a integer and adds to vector
	 */
	public void mousePressed(MouseEvent e) {
		if (compTurn == true){
			return;
		}
		ColorPiece temp = (ColorPiece)e.getSource();
		temp.setBackground(temp.getColor().brighter());
		if (e.getSource() == red){
			player.add(1);
		}
		else if (e.getSource() == blue){
			player.add(2);
		}
		else if (e.getSource() == yellow){
			player.add(3);
		}
		else if (e.getSource() == green){
			player.add(4);
		}
	}

	@Override
	/**
	 * returns color to normal and call compare function
	 */
	public void mouseReleased(MouseEvent e) {
		if (compTurn == true){
			return;
		}
		ColorPiece temp = (ColorPiece)e.getSource();
		temp.setBackground(temp.getColor().darker());
		compare();
	}
}