package ImageLoader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SimpleImageLoader implements ImageLoaderInterface {

	public BufferedImage getImage(File imageFile) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Error while loading file" + imageFile.getAbsolutePath());
			System.exit(-1);
		}
		
		if(img == null) {
			System.err.println("Error while loading file" + imageFile.getAbsolutePath());
			System.exit(-1);	
		}

		return img;
	}

}
