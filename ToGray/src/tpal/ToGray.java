package tpal;

import java.awt.image.BufferedImage;
import java.io.IOException;
	
public class ToGray implements Plugin {
		
		
		@Override
		public BufferedImage processImage(BufferedImage img) throws IOException {
			for (int x = 0; x < img.getWidth(); x++)
				for (int y = 0; y < img.getHeight(); y++) {
					
					int rgb = img.getRGB(x, y);
					int A = (rgb & 0xff000000);
					int R = (rgb & 0x00ff0000) >> 16;
					int G = (rgb & 0x0000ff00) >> 8;
					int B = rgb & 0x000000ff;	
					int gray = (int) (0.30f * R + 0.59f * G + 0.11f * B);
					int color = A + (gray << 16) + (gray << 8) + gray;
					img.setRGB(x, y, color);
					
				}
			return img;		    
		}

		@Override
		public String getOperationName(String language) {
			if(language == "PL") {
				return "Do skali szarosci";
			} else {
				return "To gray";
			}
			
		}
		
	}


