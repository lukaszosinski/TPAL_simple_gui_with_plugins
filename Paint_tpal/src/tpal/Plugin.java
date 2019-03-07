package tpal;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface Plugin {
	public BufferedImage processImage(BufferedImage img) throws IOException;
	public String getOperationName(String language);
}
