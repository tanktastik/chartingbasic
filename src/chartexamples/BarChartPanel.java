package chartexamples;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;


public class BarChartPanel extends JPanel 
{
	private Rectangle2D.Double borderRectangle;
	private int n;
	private double[] y;
	private double b;
	private Color c;
	
  public BarChartPanel() 
  {
  	// default constructor for initialization
  }
  
  public BarChartPanel(Rectangle2D.Double border, int nPoints, double[] yValues, double base, Color colorValue) 
  {
  	this.borderRectangle = border;
  	this.n = nPoints;
  	this.y = yValues;
  	this.b = base;
  	this.c = colorValue;
  }
  public void paintComponent(Graphics g) 
  {
		// Draws a  bar chart
  	// borderRectangle - rectangle region to draw plot
  	// n - number of points to plot
  	// y - array of y points (lower index is 0, upper index is n-1)
  	// c - color of bars
  	// find minimums and maximums
 		double yMin = y[0]; double yMax = y[0];
	  for (int i = 1; i < n; i++)
	  {
	  	yMin = Math.min(yMin, y[i]);
	  	yMax = Math.max(yMax, y[i]);
	  }
  	// Extend y values a bit so bars are not right on borders
  	yMin = (1 - 0.05 * Double.compare(yMin, 0)) * yMin;
  	yMax = (1 + 0.05 * Double.compare(yMax, 0)) * yMax;
  	Graphics2D g2D = (Graphics2D) g;
    super.paintComponent(g2D);
  	// Find bar width in client coordinates
  	// use half bar-width as margins between bars
  	double barWidth = 2 * (borderRectangle.width - 1) / (3 * n + 1);
  	double clientBase = yPhysicalToyUser(borderRectangle, b, yMin, yMax);
  	Rectangle2D.Double myRectangle;
  	for (int i = 0; i < n; i++)
  	{
    	// draw bars
    	if (y[i] > b)
    	{
    		myRectangle = new Rectangle2D.Double(borderRectangle.x + (1.5 * i + 0.5) * barWidth, yPhysicalToyUser(borderRectangle, y[i], yMin, yMax), barWidth, clientBase - yPhysicalToyUser(borderRectangle, y[i], yMin, yMax));
    	}
    	else
    	{
    		myRectangle = new Rectangle2D.Double(borderRectangle.x + (1.5 * i + 0.5) * barWidth, clientBase, barWidth, yPhysicalToyUser(borderRectangle, y[i], yMin, yMax) - clientBase);
    	}
    	g2D.setPaint(c);
    	g2D.fill(myRectangle);
    }
    // draw border
    g2D.setPaint(Color.BLACK);
    g2D.draw(borderRectangle);
  	// line at base
  	g2D.draw(new Line2D.Double(borderRectangle.x, clientBase, borderRectangle.x + borderRectangle.width - 1, clientBase));
  	g2D.dispose();
  }
  private double xPhysicalToxUser(Rectangle2D.Double r, double xPhysical, double xMin, double xMax)
  {
  	return(r.x + (xPhysical - xMin) * (r.width - 1) / (xMax - xMin));
  }
  private double yPhysicalToyUser(Rectangle2D.Double r, double yPhysical, double yMin, double yMax)
  {
  	return(r.y + (yMax - yPhysical) * (r.height - 1) / (yMax - yMin));
  }
}