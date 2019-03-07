package tpal;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rotate implements Plugin {

	@Override
	public BufferedImage processImage(BufferedImage img) throws IOException {
		BufferedImage newImg = new BufferedImage(
				img.getHeight(), 
				img.getWidth(), 
				img.getType()
				);
		for (int x = 0; x < img.getWidth(); x++)
			for (int y = 0; y < img.getHeight(); y++) {				
				int rgb = img.getRGB(x, y);
				newImg.setRGB(y, x, rgb);
				
			}
		return newImg;		    
	}

	@Override
	public String getOperationName(String language) {
		if(language == "PL") {
			return "Obróć";
		} else {
			return "Rotate";
		}
		
	}
	
}
