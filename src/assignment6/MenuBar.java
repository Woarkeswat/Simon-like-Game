package assignment6;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	public JMenu settings, stats, help, colorScheme, mode;
	public JMenuItem highScore, history, about, rules, panel1, panel2, panel3, panel4, 
	fast, med, slow;
	
	public MenuBar(ActionListener action){
		settings = new JMenu("Settings");
		stats = new JMenu("Stats");
		help = new JMenu("Help");
		
		this.add(settings);
		this.add(stats);
		this.add(help);
		
		colorScheme = new JMenu("Change color scheme");
		panel1 = new JMenuItem("Panel1");
		panel2 = new JMenuItem("Panel2");
		panel3 = new JMenuItem("Panel3");
		panel4 = new JMenuItem("Panel4");
		mode = new JMenu("Change game mode");
		fast = new JMenuItem("Fast blink");
		med = new JMenuItem("Medium blink");
		slow = new JMenuItem("Slow blink");
		highScore = new JMenuItem("HighScores");
		history = new JMenuItem("History");
		about = new JMenuItem("About");
		rules = new JMenuItem("Rules");		
		
		colorScheme.add(panel1);
		colorScheme.add(panel2);
		colorScheme.add(panel3);
		colorScheme.add(panel4);
		
		panel1.addActionListener(action);
		panel2.addActionListener(action);
		panel3.addActionListener(action);
		panel4.addActionListener(action);
		
		mode.add(fast);
		fast.addActionListener(action);
		mode.add(med);
		med.addActionListener(action);
		mode.add(slow);
		slow.addActionListener(action);
		
		settings.add(colorScheme);
		settings.add(mode);
		
		highScore.addActionListener(action);
		history.addActionListener(action);
		
		stats.add(highScore);
		stats.add(history);
		
		about.addActionListener(action);
		rules.addActionListener(action);
		
		help.add(about);
		help.add(rules);
	}
}
