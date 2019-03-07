package tpal;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class History {
	Stack<BufferedImage> undoStack;
	Stack<BufferedImage> redoStack;
	
	History() {
		undoStack = new Stack<BufferedImage>();
		redoStack = new Stack<BufferedImage>();
	}
	
	public BufferedImage getPreviousStep(BufferedImage img) {
		if(undoStack.isEmpty())
			return img;
		
		redoStack.push(img);
		return undoStack.pop();		
	}
	
	public BufferedImage getNextStep(BufferedImage img) {
		if(redoStack.isEmpty())
			return img;
		
		undoStack.push(img);				
		return redoStack.pop();		
	}
	
	public void saveStep(BufferedImage img) {
		undoStack.push(copyImage(img));
	}
		
	private BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(
				source.getWidth(),
				source.getHeight(),
				source.getType());
	    Graphics2D g =  (Graphics2D) b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    return b;
	}
}
