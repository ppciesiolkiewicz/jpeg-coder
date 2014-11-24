package JpegMath.Transformations;

import DataObjects.Tile;

//Sequential DCT-based mode of operation.
public class DctTransformation implements TransformationInterface {

	public Tile<Double> transfom(Tile<Integer> input) {
		Tile<Double> output = new Tile<Double>(input.getSizeX(),
				input.getSizeY(), 0d);

		for (int u = 0; u < output.getSizeX(); u++)
			for (int v = 0; v < output.getSizeY(); v++)
				output.setVal(u, v, calcDctCoeff(input, u, v));

		return output;
	}

	private Double calcDctCoeff(Tile<Integer> input, int u, int v) {
		Double dctCoeff = 0d;
		for (int x = 0; x < input.getSizeX(); x++) {
			for (int y = 0; y < input.getSizeY(); y++) {
				dctCoeff += input.getVal(x, y)
						* Math.cos((2 * x + 1) * u * Math.PI / 16)
						* Math.cos((2 * y + 1) * v * Math.PI / 16);

			}
		}

		if (u == 0)
			dctCoeff = dctCoeff / Math.sqrt(2);
		if (v == 0)
			dctCoeff = dctCoeff / Math.sqrt(2);
		dctCoeff = dctCoeff / 4;

		return dctCoeff;
	}

	public Tile<Integer> inverseTransform(Tile<Double> input) {
		Tile<Integer> output = new Tile<Integer>(input.getSizeX(),
				input.getSizeY(), 0);

		for (int x = 0; x < output.getSizeX(); x++)
			for (int y = 0; y < output.getSizeY(); y++)
				output.setVal(x, y, calcIdctCoeff(input, x, y));

		return output;
	}

	private Integer calcIdctCoeff(Tile<Double> input, int x, int y) {
		Double idctCoeff = 0d;
		for (int u = 0; u < input.getSizeX(); u++) {
			for (int v = 0; v < input.getSizeY(); v++) {
				Double cu = 1d, cv = 1d;
				if (u == 0)
					cu = 1 / Math.sqrt(2);
				if (v == 0)
					cv = 1 / Math.sqrt(2);

				idctCoeff += cu * cv * input.getVal(u, v)
						* Math.cos((2 * x + 1) * u * Math.PI / 16)
						* Math.cos((2 * y + 1) * v * Math.PI / 16);
			}
		}
		
		idctCoeff = (idctCoeff / 4);
		return idctCoeff.intValue();
	}
}