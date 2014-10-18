package JpegMath.Tilers;
import DataObjects.Tile;

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
	
	public abstract <E extends Number> Tile<E>[][] tile(E[][] img);
	public abstract <E extends Number> E[][] connect(Tile<E>[][] tiles);
}
