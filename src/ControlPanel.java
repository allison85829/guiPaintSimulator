/* Project 5: Drawing 
 * Group's members: Lien (Ally) Quan, Jiahui Chen, Landan Hu
 * CS111B 
 * 05/26/2107
 * 
 * This is the ControlPanel that allow user to choose color of pen 
 * the eraser pen, erase everything and the thicker pen 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ControlPanel extends JPanel {

	private JRadioButton pen, thickerPen, eraser, red, blue, green;
	private ButtonGroup toolOption, colorOption;
	private JPanel toolPanel, colorPanel;
	private DrawingPanel drawingPanel;
	public Color penColor;
	private JButton clearAllButton;
	
	public ControlPanel(DrawingPanel drawingPanel) {
		setBackground(Color.CYAN);
		// adding DrawingPanel to ControlPanel
		this.drawingPanel = drawingPanel;
		// invoke draw method to start drawing
		drawingPanel.draw();
		
		// toolPanel choose between pen and eraser or the thicker pen 
		toolPanel = new JPanel();
		
		pen = new JRadioButton("Pen");
		toolPanel.add(pen);
		pen.addActionListener(new ButtonListener());
		eraser = new JRadioButton("Eraser");
		toolPanel.add(eraser);
		this.add(toolPanel);
		eraser.addActionListener(new ButtonListener());
		thickerPen = new JRadioButton("Thicker Pen");
		toolPanel.add(thickerPen);
		thickerPen.addActionListener(new ButtonListener());
		
		toolOption = new ButtonGroup();
		toolOption.add(pen);
		toolOption.add(eraser);
		toolOption.add(thickerPen);
		
		// colorPanel choose color for the pen
		colorPanel = new JPanel();
		// 3 color to chose RED, BLUE, GREEN
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
		
		// button for clearing all the drawing 
		clearAllButton = new JButton("Clear All");
		this.add(clearAllButton);
		clearAllButton.addActionListener(new ButtonListener());
	}
	

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// if eraser is selected 
			// set the color of the pen to white
			// so it will appear as if a point is erased
			// also make the thickness of the pen larger to eraser larger portion of the drawing
			if (eraser.isSelected()) {
				penColor = Color.WHITE;
				drawingPanel.setPenThickness(50);
				drawingPanel.setPenColor(penColor);
			} else if (pen.isSelected()) {
				// selection of color 
				// check the color options
				drawingPanel.setIsClicked(false);
				drawingPanel.setPenThickness(5);
				if (red.isSelected()) {
					penColor = Color.RED;
				} else if (blue.isSelected()) {
					penColor = Color.BLUE;
				} else if (green.isSelected()) {
					penColor = Color.GREEN;
				}
				// using the setPenColor() method in DrawingPanel class 
				// to set the pen's color
				drawingPanel.setPenColor(penColor);
			} else if (thickerPen.isSelected()) {
				drawingPanel.setIsClicked(false);
				// make the pen thicker by setting a larger pen's thickness
				drawingPanel.setPenThickness(10);
				if (red.isSelected()) {
					penColor = Color.RED;
				} else if (blue.isSelected()) {
					penColor = Color.BLUE;
				} else if (green.isSelected()) {
					penColor = Color.GREEN;
				}
				drawingPanel.setPenColor(penColor);
			}

			// clear entire drawing 
			if (event.getSource()==clearAllButton) {
				drawingPanel.clearAll();
			}
		} 
	}

//	public static void main (String args[]) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				JFrame frame = new JFrame("DRAWING");
//				DrawingPanel panel = new DrawingPanel();
//				ControlPanel controlPanel = new ControlPanel(panel);
//				frame.setSize(600, 600);
//				frame.getContentPane().add(controlPanel, BorderLayout.NORTH);
//				frame.getContentPane().add(panel, BorderLayout.CENTER);
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame.setVisible(true);
//				
//				
//			}
//		});
//	}
//	
}
