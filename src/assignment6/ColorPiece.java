package assignment6;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPiece extends JPanel {
	JFrame frame;
	Container pane;
	Color bkgColor;
	/**
	 * ColorPiece constructor
	 * @param c - uses color class
	 * @param m - uses mouse listener class
	 */
	public ColorPiece(Color c, MouseListener m) {
		bkgColor = c;
		
		setBackground(c.darker());
		
		addMouseListener(m);
	}
	/**
	 * blink function that is called when computer sequence is ran
	 */
	
	public void setNewColor(Color c){
		
		bkgColor = c;
		setBackground(c.darker());
	}
	public void blink(int speed) {
		setBackground(bkgColor.brighter());
		this.update(this.getGraphics());
		try {
			Thread.sleep(speed);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setBackground(getColor().darker());
		this.update(this.getGraphics());
		try {
			Thread.sleep(speed);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public Color getColor(){
		return bkgColor;
	}
}
