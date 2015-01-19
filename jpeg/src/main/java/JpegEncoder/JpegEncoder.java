package JpegEncoder;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Tile;
import JpegMath.Transformations.DctTransformation;
import JpegMath.Transformations.FastDctTransformation;
import JpegMath.Transformations.TransformationInterface;

public class JpegEncoder extends AbstractJpegEncoder {

	public JpegEncoder(String inputPath_, String outputPath_,Integer quality_) {
		super(inputPath_, outputPath_, quality_);
	}

	@Override
	protected List<List<Tile<Double>>> transform(Tile<Integer>[][][] tiles) {
		TransformationInterface dct = new FastDctTransformation();
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
