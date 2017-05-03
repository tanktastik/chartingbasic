
	/*
 * ChartExamples.java
 */
package chartexamples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.geom.*;

public class ChartExamples extends JFrame
{

  JPanel myPanel = new JPanel();
  JButton lineButton = new JButton();
  JButton spiralButton = new JButton();
  JButton barButton = new JButton();
  JButton pieButton = new JButton();
  // data arrays 
  double[] x = new double[200];
  double[] y = new double[200];
  double[] yd = new double[200];
  Color[] plotColor = new Color[10];
  Random myRandom = new Random();

  

  public ChartExamples()
  {
    // frame constructor
    setTitle("Chart Examples");
    setResizable(false);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        exitForm(evt);
      }
    });
    getContentPane().setLayout(new GridBagLayout());

    myPanel.setPreferredSize(new Dimension(400, 300));
    myPanel.setBackground(Color.WHITE);
    GridBagConstraints gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 0;
    gridConstraints.gridwidth = 4;
    gridConstraints.insets = new Insets(10, 10, 10, 10);
    getContentPane().add(myPanel, gridConstraints);

    lineButton.setText("Line");
    lineButton.setPreferredSize(new Dimension(100, 25));
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 1;
    gridConstraints.insets = new Insets(5, 5, 5, 5);
    getContentPane().add(lineButton, gridConstraints);
    lineButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        lineButtonActionPerformed(e);
      }
    });

    spiralButton.setText("Spiral");
    spiralButton.setPreferredSize(new Dimension(100, 25));
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 1;
    gridConstraints.gridy = 1;
    gridConstraints.insets = new Insets(5, 5, 5, 5);
    getContentPane().add(spiralButton, gridConstraints);
    spiralButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        lineButtonActionPerformed(e);
      }
    });

    barButton.setText("Bar");
    barButton.setPreferredSize(new Dimension(100, 25));
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 2;
    gridConstraints.gridy = 1;
    gridConstraints.insets = new Insets(5, 5, 5, 5);
    getContentPane().add(barButton, gridConstraints);
    barButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        barButtonActionPerformed(e);
      }
    });

    pieButton.setText("Pie");
    pieButton.setPreferredSize(new Dimension(100, 25));
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 3;
    gridConstraints.gridy = 1;
    gridConstraints.insets = new Insets(5, 5, 5, 5);
    getContentPane().add(pieButton, gridConstraints);
    pieButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        pieButtonActionPerformed(e);
      }
    });

    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
    // colors to use
    plotColor[0] = Color.YELLOW;
    plotColor[1] = Color.BLUE;
    plotColor[2] = Color.GREEN;
    plotColor[3] = Color.CYAN;
    plotColor[4] = Color.RED;
    plotColor[5] = Color.MAGENTA;
    plotColor[6] = Color.ORANGE;
    plotColor[7] = Color.DARK_GRAY;
    plotColor[8] = Color.GRAY;
    plotColor[9] = Color.BLACK;

  }

  private void lineButtonActionPerformed(ActionEvent e)
  {
	    // Draws line and spiral charts
    // Create a sinusoid with 200 points
    double alpha = 0.1 - myRandom.nextDouble() * 0.2;
    double beta = myRandom.nextDouble() * 10 + 5;
    for (int i = 0; i < 200; i++)
    {
      x[i] = i;
      y[i] = Math.exp(-alpha * i) * Math.sin(Math.PI * i / beta);
      yd[i] = Math.exp(-alpha * i) * (Math.PI * Math.cos(Math.PI * i / beta) / beta - alpha * Math.sin(Math.PI * i / beta));
    }
    // Draw plots
    Rectangle2D.Double borderRectangle = new Rectangle2D.Double(20, 20, 360, 260);
    LineChartPanel myLineChart = new LineChartPanel();
    if (e.getActionCommand().equals(lineButton.getText()))
    {
      myLineChart = new LineChartPanel(borderRectangle, 200, x, y, plotColor[myRandom.nextInt(10)]);
    } else
    {
      myLineChart = new LineChartPanel(borderRectangle, 200, y, yd, plotColor[myRandom.nextInt(10)]);
    }
    myLineChart.setPreferredSize(new Dimension(400, 300));
    myLineChart.setBackground(Color.WHITE);
    myPanel.removeAll();
    myPanel.add(myLineChart);
    this.pack();
  }

  private void barButtonActionPerformed(ActionEvent e)
  {
    // generate 5-10 bars with values from -10 to 10 and draw bar chart
    int numberBars = myRandom.nextInt(6) + 5;
    for (int i = 0; i < numberBars; i++)
    {
      y[i] = myRandom.nextDouble() * 20 - 10;
    }
    // Draw chart
    Rectangle2D.Double borderRectangle = new Rectangle2D.Double(20, 20, 360, 260);
    BarChartPanel myBarChart = new BarChartPanel(borderRectangle, numberBars, y, 0.0, plotColor[myRandom.nextInt(10)]);
    myBarChart.setPreferredSize(new Dimension(400, 300));
    myBarChart.setBackground(Color.WHITE);
    myPanel.removeAll();
    myPanel.add(myBarChart);
    this.pack();
  }

  private void pieButtonActionPerformed(ActionEvent e)
  {
    // Generate 3 to 10 slices at random with values from 1 to 5
    int numberSlices = myRandom.nextInt(8) + 3;
    for (int i = 0; i < numberSlices; i++)
    {
      y[i] = myRandom.nextDouble() * 5 + 1;
    }
    Rectangle2D.Double borderRectangle = new Rectangle2D.Double(70, 20, 260, 260);
    PieChartPanel myPieChart = new PieChartPanel(borderRectangle, numberSlices, y, plotColor);
    myPieChart.setPreferredSize(new Dimension(400, 300));
    myPieChart.setBackground(Color.WHITE);
    myPanel.removeAll();
    myPanel.add(myPieChart);
    this.pack();
  }

  private void exitForm(WindowEvent e)
  {
    System.exit(0);
  }
}