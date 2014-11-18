package JpegEncoder;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Tile;
import JpegMath.Transformations.DctTransformation;
import JpegMath.Transformations.TransformationInterface;

public class JpegEncoder extends AbstractJpegEncoder {

	public JpegEncoder(Double quality_) {
		super(quality_);
	}

	@Override
	protected List<Tile<Double>> transform(Tile<Integer>[][][] tiles) {
		DctTransformation dct = new DctTransformation();
		
		List<Tile<Double>> out = new ArrayList<Tile<Double>>();
		
		for(int i = 0; i < tiles.length; i++)
			for(int j = 0; j < tiles[0].length; j++)
				for(int k = 0; k < tiles[0][0].length; k++)
						out.add(dct.transfom(tiles[i][j][k]));
		return out;
	}

}
