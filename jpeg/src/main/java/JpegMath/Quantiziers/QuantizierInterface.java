package JpegMath.Quantiziers;

import DataObjects.Tile;

public interface QuantizierInterface {
	public Tile quantize(Tile val);
	public Tile dequantize(Tile val);
}
