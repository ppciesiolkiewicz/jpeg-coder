package JpegMath.Transformations;

import DataObjects.MatrixTileInterface;
import DataObjects.Tile;

//Sequential DCT-based mode of operation.
public class DctTransformation {//implements TransformationInterface {

	public Tile<Double> transfom(Tile<Integer> t) {
		MatrixTileInterface<Integer> input = t;
		Tile<Double> output = new Tile<Double>(Double.class, input.getSizeX(), input.getSizeY());

		for (int u = 0; u < output.getSizeX(); u++)
			for (int v = 0; v < output.getSizeY(); v++) {
				for (int x = 0; x < input.getSizeX(); x++)
					for (int y = 0; y < input.getSizeY(); y++) {
						output.setVal(
								u,
								v,
								output.getVal(u, v) + 
								input.getVal(x, y) * 
								Math.cos( (2 * x + 1) * u * Math.PI/16 ) * 
								Math.cos( (2 * y + 1) * v * Math.PI/16 )
						);
					}
				if(u==0 && v==0)
					output.setVal(u, v, output.getVal(u, v)/2);
				output.setVal(u, v, output.getVal(u, v)/4);
		}

		return output;
	}


	public Tile<Integer> inverseTransform(Tile<Double> t) {
		
		return null;
	}

}