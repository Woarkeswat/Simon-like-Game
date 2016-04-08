package assignment6;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.Simonish;
/**
 * 
 * @author Devin Parsons
 *ScorePanel class makes all parts of the 
 */
public class ScorePanel extends JPanel{

	private JLabel scoreLabel;
	private JLabel highScoreLabel;
	private JButton startGameBtn;
	Simonish game;
	/**
	 * ScorePanel constructor
	 * @param g - uses Simonish class
	 */
	public ScorePanel(Simonish g){
		int score = 0;
		int highScore = 0;
		scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
		highScoreLabel = new JLabel("High Score: " + highScore, SwingConstants.CENTER);
		startGameBtn = new JButton("Start Game");
		add(scoreLabel);
		add(startGameBtn);
		add(highScoreLabel);
		game = g;
	}
	
	public JButton getStartGameBtn(){
		return startGameBtn;
	}
	public JLabel getScore(){
		return scoreLabel;
	}
	public void setScore(int num){
		scoreLabel.setText("Score: " + num);
	}
	public JLabel getHighScore(){
		return highScoreLabel;
	}
	public void setHighScore(int num){
		highScoreLabel.setText("High Score: " + num);
	}
	
	public JButton getStartBtn(){
		return startGameBtn;
	}
}