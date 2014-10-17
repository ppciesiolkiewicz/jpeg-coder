package JpegMath.Tilers;

import java.awt.image.BufferedImage;
import DataObjects.Tile;

public interface TilerInterface {
	public Tile[][] tile(BufferedImage img);
	public BufferedImage connect(Tile[][] tiles);
}
