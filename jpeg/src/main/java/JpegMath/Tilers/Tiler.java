package JpegMath.Tilers;

import java.awt.image.BufferedImage;
import DataObjects.Tile;

public abstract class Tiler {
	int sizeX, sizeY;
	public Tiler(int x, int y) {
		setTileSize(x,y);
	}
	
	public Tiler(int x) {
		setTileSize(x);
	}
	
	public void setTileSize(int x, int y) {
		sizeX = x;
		sizeY = y;
	}
	
	public void setTileSize(int x) {
		sizeX = x;
		sizeY = x;
	}
	
	public abstract <E extends Number> Tile<E>[][] tile(BufferedImage img);
	public abstract <E extends Number> BufferedImage connect(Tile<E>[][] tiles); //Impossible probably
	//BufferedImageInfo needed or something
}
