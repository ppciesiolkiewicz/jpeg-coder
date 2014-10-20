package JpegMath.Tilers;
import DataObjects.Tile;

//consider e.g. 15x10pixel image, with 8x8 tiles some pixels doesen't fit. 
//Some duplication/copying/zerofilling is needed
public abstract class Tiler {
	int tileSizeX, tileSizeY;
	public Tiler(int tileSizeX_, int tileSizeY_) {
		setTileSize(tileSizeX_, tileSizeY_);
	}
	
	public Tiler(int tileSizeXY_) {
		setTileSize(tileSizeXY_);
	}
	
	public void setTileSize(int tileSizeX_, int tileSizeY_) {
		this.tileSizeX = tileSizeX_;
		this.tileSizeY = tileSizeY_;
	}
	
	public void setTileSize(int tileSizeXY_) {
		this.tileSizeX = tileSizeXY_;
		this.tileSizeY = tileSizeXY_;
	}
	
	public abstract <E extends Number> Tile<E>[][] tile(E[][] pixelMatrix);
	public abstract <E extends Number> E[][] connect(Tile<E>[][] tiles);
}
