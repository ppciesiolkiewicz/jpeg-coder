package JpegEncoder;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Tile;
import JpegMath.Transformations.Irreversible9_7WaveletTransformation;

public class Jpeg2000Encoder extends AbstractJpegEncoder {

	public Jpeg2000Encoder(Integer quality_, String outputPath_) {
		super(quality_, outputPath_);
	}

	@Override
	protected List<List<Tile<Double>>> transform(Tile<Integer>[][][] tiles) {
		Irreversible9_7WaveletTransformation dct = new Irreversible9_7WaveletTransformation();
		List<List<Tile<Double>>> out = new ArrayList<List<Tile<Double>>>();

		for (int j = 0; j < tiles[0].length; j++)
			for (int k = 0; k < tiles[0][0].length; k++) {
				ArrayList<Tile<Double>> components = new ArrayList<Tile<Double>>();
				for (int i = 0; i < tiles.length; i++) {
					components.add(dct.transfom(tiles[i][j][k]));
				}
				out.add(components);
			}
		return out;
	}

}
