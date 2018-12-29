import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class ControlDrawingPanel extends JPanel {
	// instance data variables from DrawingPanel
	ArrayList<ColorPoint> colorPointList;
	public static ArrayList<ArrayList<Point>> colorLine = new ArrayList<ArrayList<Point>>();
	// instance data variables from ControlPanel
	private JRadioButton pen, eraser, red, blue, green;
	private static JRadioButton line;
	private ButtonGroup toolOption, colorOption;
	private JButton clearAll;
	private JPanel toolPanel, colorPanel;
	private Drawing drawingPanel;
	public static int x1, y1, x2, y2;
	public static boolean paintLine = false;
	public static Color penColor;
	public static Point p1, p2;
	
	public ControlDrawingPanel(Drawing drawingPanel) {
		setBackground(Color.WHITE);
		
		// toolPanel choose between pen and eraser 
		toolPanel = new JPanel();
		
		pen = new JRadioButton("pen");
		toolPanel.add(pen);
		pen.addActionListener(new ButtonListener());
		eraser = new JRadioButton("eraser");
		toolPanel.add(eraser);
		eraser.addActionListener(new ButtonListener());
		line = new JRadioButton("line");
		toolPanel.add(line);
		line.addActionListener(new ButtonListener());
		this.add(toolPanel);
		
		toolOption = new ButtonGroup();
		toolOption.add(pen);
		toolOption.add(eraser);
		toolOption.add(line);
		
		// colorPanel choose color for the pen
		colorPanel = new JPanel();
		
		red = new JRadioButton("red");
		colorPanel.add(red);
		red.addActionListener(new ButtonListener());
		blue = new JRadioButton("blue");
		colorPanel.add(blue);
		blue.addActionListener(new ButtonListener());
		green = new JRadioButton("green");
		colorPanel.add(green);
		green.addActionListener(new ButtonListener());
		this.add(colorPanel);
		
		colorOption = new ButtonGroup();
		colorOption.add(red);
		colorOption.add(blue);
		colorOption.add(green);
		
		// Clear All Button 
		clearAll = new JButton("Clear All");
		this.add(clearAll);
		clearAll.addActionListener(new ButtonListener());
		
		
		
		this.drawingPanel = drawingPanel;
	}
	
	// Drawing class
	public static class Drawing extends JPanel {

		ArrayList<ColorPoint> colorPointList;
		private Color penColor;
		public boolean isClicked, drawLine;
		protected int diameter;
		protected ColorPoint startingPoint, endingPoint;
		
		
		public Drawing() {
			setBackground(Color.WHITE);
			colorPointList = new ArrayList<ColorPoint>();
			isClicked=false;
			this.addMouseListener(new MyMouseListener());
			this.addMouseMotionListener(new MyMouseListener());
		}
		
		public void setColor(Color penColor) {
			this.penColor=penColor;
		}
		public void setDiameter(int diameter) {
			this.diameter=diameter;
		}
		public void clearList() {
			colorPointList.clear();
			colorLine.clear();
			repaint();
		}
		public void setDrawLine(boolean drawLine) {
			this.drawLine = drawLine;
		}
		
		
		// paintComponent for Drawing Panel 
		@Override
		public void paintComponent(Graphics pen) {
			super.paintComponent(pen);

			Graphics2D g = (Graphics2D) pen;
			for (ColorPoint p  : colorPointList) {
				double x = p.getPoint().getX();
				double y = p.getPoint().getY();
				g.setColor(p.getColor());
				int pointDiameter = p.getPointDiameter();
				g.fill(new Ellipse2D.Double(x, y, pointDiameter, pointDiameter));
			}
			for (ArrayList<Point> l : colorLine) {
				x1 = (int)l.get(0).getX();
				y1 = (int)l.get(0).getY();

				x2 = (int)l.get(1).getX();
				y2 = (int)l.get(1).getY();
				g.setColor(Color.BLACK);
				g.drawLine(x1, y1, x2, y2);
			}			
		}
		
		
		// MouseListener for DrawingClass
		public class MyMouseListener extends MouseAdapter {
			
			@Override 
			public void mouseMoved(MouseEvent event) {
				if (isClicked) {
					colorPointList.add(new ColorPoint(event.getPoint(), penColor, diameter));
					repaint();
				}
			}
			
			@Override 
			public void mouseClicked(MouseEvent event) {
				isClicked=!isClicked;
			}	
			
			@Override 
			public void mousePressed(MouseEvent event) {	
				if (paintLine) {
					p1 = event.getPoint();
				}
				
			}
			
			@Override 
			public void mouseReleased(MouseEvent event) {
				if (paintLine) {
					p2 = event.getPoint();
					// l is an array list of 2 point that represent the line 
					ArrayList<Point> l = new ArrayList<Point>();
					l.add(p1);
					l.add(p2);
					colorLine.add(l);
					repaint();
				}
				
			}
			
		}
	}

	
	// ButtonListener for controlPanel
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (eraser.isSelected()) {
				penColor = Color.WHITE;
				drawingPanel.setDiameter(200);
				drawingPanel.setColor(penColor);
			} 
			else if (pen.isSelected()) {
				paintLine = false;
				if (red.isSelected()) {
					drawingPanel.setDiameter(5);
					drawingPanel.setColor(Color.RED);
				} else if (blue.isSelected()) {
					drawingPanel.setDiameter(5);
					drawingPanel.setColor(Color.BLUE);
				} else if (green.isSelected()) {
					drawingPanel.setDiameter(5);
					drawingPanel.setColor(Color.GREEN);
				}
			}
			else if (line.isSelected()) {
				paintLine = true;
			}
			
			if (event.getSource()==clearAll) {
				drawingPanel.clearList();
			}			
		} 
		
	}
	
	
	// main method
	public static void main (String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("DRAWING");
				Drawing panel = new Drawing();
				ControlDrawingPanel controlPanel = new ControlDrawingPanel(panel);
				frame.setSize(600, 600);
				frame.getContentPane().add(controlPanel, BorderLayout.NORTH);
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);	
			}
		});
	}
	
	
	
}
