package chartexamples;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;


public class PieChartPanel extends JPanel 
{
	private Rectangle2D.Double borderRectangle;
	private int n;
	private double[] y;
	private Color[] c;
	
  public PieChartPanel() 
  {
  	// default constructor for initialization
  }
  
  public PieChartPanel(Rectangle2D.Double border, int nSegments, double[] yValues, Color[] colorValues) 
  {
  	this.borderRectangle = border;
  	this.n = nSegments;
  	this.y = yValues;
  	this.c = colorValues;
  }
  public void paintComponent(Graphics g) 
  {
		// Draws a pie chart 
  	// borderRectangle - rectangle object to draw chart
  	// n - number of pie segments to draw
  	// y - array of points (Double type) to chart (lower index is 1, upper index is N)
  	// c - color of pie segments

    Graphics2D g2D = (Graphics2D) g;
    super.paintComponent(g2D);
    double sum = 0.0;
    for (int i = 0; i < n; i++)
    {
    	sum += y[i];
    }
    // draw pie
    double startAngle = 0;
    Arc2D.Double myArc;
    // for each slice fill and draw
    for (int i = 0; i < n; i++)
    {
     	myArc = new Arc2D.Double(borderRectangle.x, borderRectangle.y, borderRectangle.width, borderRectangle.height, startAngle, 360 * y[i] / sum, Arc2D.PIE);
     	g2D.setPaint(c[i]);
     	g2D.fill(myArc);
     	g2D.setPaint(Color.BLACK);
     	g2D.draw(myArc);
     	startAngle += 360 * y[i] / sum;
    }
    g2D.dispose();
  }
}