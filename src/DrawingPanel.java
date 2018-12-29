/* Project 5: Drawing 
 * Group's members: Lien (Ally) Quann, Jiahui Chen, Landan Hu
 * CS111B 
 * 05/26/2107
 * 
 * This is the drawingPanel that does the drawing 
 * It contains MouseListener and MouseMotionListener 
 * to keep track of the movement of the mouse 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class DrawingPanel extends JPanel {

	// instance data variables for 
	private ArrayList<ColorPoint> colorPointList;
	private Color penColor;
	private boolean isClicked;
	private int penThickness;
	private boolean isReleased = false;
	public int x1, y1, x2, y2;

	// constructor for the drawing panel 
	public DrawingPanel() {
		setBackground(Color.WHITE);
		colorPointList = new ArrayList<ColorPoint>();
	}
	
	// getters
	public Color getPenColor() {
		return penColor;
	}
	public boolean getIsClicked() {
		return isClicked;
	}
	public int getPenThickness() {
		return penThickness;
	}
	public boolean getIsReleased() {
		return isReleased;
	}
	
	
	// setters
	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}
	public void setIsClicked(boolean isClickedStatus) {
		this.isClicked=isClickedStatus;
	}
	public void setIsReleased(boolean isReleasedStatus) {
		this.isReleased=isReleasedStatus;
	}
	public void setPenThickness(int penThickness) {
		if (penThickness>0) {
			this.penThickness = penThickness;
		} else {
			System.out.println("ERROR: Pen's thickness cannot be negative.");
		}
	}

	// method to draw 
	// using mouse clicked and mouse move to keep track of point 
	public void draw() {
		this.addMouseListener(new MyMouseListener());
		this.addMouseMotionListener(new MyMouseListener());
	}
	
	
	// clear the ArrayList and redraw 
	public void clearAll() {
		colorPointList.clear();
		repaint();
	}
	
	DrawingPanel dp = new DrawingPanel();

	public class MyMouseListener extends MouseAdapter {
		
		
		
		@Override 
		public void mouseMoved(MouseEvent event) {
			// if isClicked is true 
			// start the drawing 
			if (isClicked) {
				colorPointList.add(new ColorPoint(event.getPoint(), penColor, penThickness));
				repaint();
			}
		}
		
		@Override 
		public void mouseClicked(MouseEvent event) {
			isClicked=!isClicked;
		}	
		
		@Override 
		public void mousePressed(MouseEvent event) {
			x1 = event.getX();
			y1 = event.getY();
			System.out.println("Starting " + event.getX() + " " + event.getY());
			dp.setIsReleased(false);
		}
		
		@Override 
		public void mouseReleased(MouseEvent event) {
			x2 = event.getX();
			y2 = event.getY();
			System.out.println("Ending " + event.getX() + " " + event.getY() );
			dp.setIsReleased(true);
			repaint();
		}
	}
	
    @Override
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		
		Graphics2D g = (Graphics2D) pen;
		if (isClicked){
			for (ColorPoint p  : colorPointList) {
				double x = p.getPoint().getX();
				double y = p.getPoint().getY();
				double penDiameter = p.getPointDiameter();
				g.setColor(p.getColor());
				g.fill(new Ellipse2D.Double(x, y, penDiameter, penDiameter));
			}
		}
		else if (isReleased) {
			g.drawLine(x1, y1, x2, y2);
		}
	}	
  
}
