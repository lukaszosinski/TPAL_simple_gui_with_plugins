package tpal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;


public class DrawArea extends JComponent {
 
  private static final long serialVersionUID = 1L;
  private BufferedImage image;
  private Graphics2D g2;
  private int currentX, currentY, oldX, oldY;
  private History history = new History();
 
  public DrawArea() {
    setDoubleBuffered(false);
       
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
    	history.saveStep(image);
        oldX = e.getX();
        oldY = e.getY();
      }
    });
 
    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();
 
        if (g2 != null) {
          g2.drawLine(oldX, oldY, currentX, currentY);
          repaint();
          oldX = currentX;
          oldY = currentY;
        }
      }
    });
  }
 
  protected void paintComponent(Graphics g) {
    if (image == null) {      
      image = (BufferedImage) createImage(getSize().width, getSize().height);
      g2 = (Graphics2D) image.getGraphics();     
      clear();
    }
     g.drawImage(image, 0, 0, null);
  }
  
  public void setImage(BufferedImage im) throws IOException {
	  	history.saveStep(image);
	    image = im;
		g2 = (Graphics2D) image.getGraphics();
		black();
		repaint();
  }
  
  public void open() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				BufferedImage im = ImageIO.read(file);
				setImage(im);
			} catch (IOException e) {}
		}
		
  }
  
  public void undo() {
		image = history.getPreviousStep(image);
		g2 = (Graphics2D) image.getGraphics();
		black();
		repaint();
  }
  
  public void redo() {
		image = history.getNextStep(image);
		g2 = (Graphics2D) image.getGraphics();
		black();
		repaint();
}
 
  public void clear() {
    g2.setPaint(Color.white);
    g2.fillRect(0, 0, getSize().width, getSize().height);
    g2.setPaint(Color.black);
    repaint();
  }
  
  public BufferedImage getImage() {
	   return copyImage(image);
  }
 
  public void red() {
    g2.setPaint(Color.red);
  }
 
  public void black() {
    g2.setPaint(Color.black);
  }
 
  public void green() {
    g2.setPaint(Color.green);
  }
 
  public void blue() {
    g2.setPaint(Color.blue);
  }
  
  public BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(
				source.getWidth(),
				source.getHeight(),
				source.getType());
	    Graphics2D g =  (Graphics2D) b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    return b;
	}
 
}
