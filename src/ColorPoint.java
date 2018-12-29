/* Project 5: Drawing 
 * Group's members: Lien (Ally) Quan, Jiahui Chen, Landan Hu 
 * CS111B 
 * 05/26/2107
 * 
 * This is ColorPonint class
 * This class is created to represent a point with color and diameter
 */

import java.awt.*;

public class ColorPoint {
	// instance data variables
	private Point point;
	private Color color;
	private int pointDiameter;
	
	// constructor
	public ColorPoint(Point point, Color color, int pointDiamter) {
		this.point=point;
		this.color=color;
		this.pointDiameter=pointDiamter;
	}
	
	// getters
	public Point getPoint() {
		return point;
	}
	public Color getColor() {
		return color;
	}
	public int getPointDiameter() {
		return pointDiameter;
	}
	
	// setters
	public void setPoint(Point newPoint) {
		point = newPoint;
	}
	public void setColor(Color newColor) {
		color = newColor;
	}
	public void setPointDiameter(int newPointDiameter) {
		pointDiameter = newPointDiameter;
	}
	
	// toString method 
	@Override 
	public String toString() {
		return "(" + point.getX() + ", " + point.getY() + ")\n"
				+ "Color: " + color 
				+ "\nPoint diameter: " + pointDiameter;
	}
}
