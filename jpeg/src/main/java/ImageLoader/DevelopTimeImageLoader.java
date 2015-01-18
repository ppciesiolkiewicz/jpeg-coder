package ImageLoader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DevelopTimeImageLoader implements ImageLoaderInterface {

	public BufferedImage getImage(File imageFile) {
		//imageFile = new File("example_bmp/example1.bmp");
		BufferedImage img = null;

		try {
			img = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("File "+imageFile+" not found");
			System.exit(-1);
		}

		return img;
	}

}
