package JpegMath.Quantiziers;

import java.util.Iterator;

import DataObjects.EncodedTile;
import DataObjects.Tile;
import DataObjects.Tile.TileIterator;

public class JpegUniformQuantizier {
	Tile<Double> quantizationTable;

	public JpegUniformQuantizier(Double quality) {
		quantizationTable = new Tile<Double>(new Double[][] {
				{ 16d, 11d, 10d, 16d, 24d, 40d, 51d, 61d },
				{ 12d, 12d, 14d, 19d, 26d, 58d, 60d, 55d },
				{ 14d, 13d, 16d, 24d, 40d, 57d, 69d, 56d },
				{ 14d, 17d, 22d, 29d, 51d, 87d, 80d, 62d },
				{ 18d, 22d, 37d, 56d, 68d, 109d, 103d, 77d },
				{ 24d, 35d, 55d, 64d, 81d, 104d, 113d, 92d },
				{ 49d, 64d, 78d, 87d, 103d, 121d, 120d, 101d },
				{ 72d, 92d, 95d, 98d, 112d, 100d, 103d, 99d },

		});

		for (int i = 0; i < quantizationTable.getLength(); i++) {
			Double temp = (quantizationTable.getVal(i) * quality + 50) / 100;
			if (temp <= 0)
				temp = 1.0;
			if (temp > 255)
				temp = 255.0;
			quantizationTable.setVal(i, temp);
		}
	}

	public EncodedTile<Integer> quantize(Tile<Double> t) {
		EncodedTile<Integer> encTile = new EncodedTile<Integer>();
		Tile<Integer> out = new Tile<Integer>(0);

		for (int c = 0; c < out.getLength(); c++)
			out.setVal(c,
					(int) Math.round(t.getVal(c) / quantizationTable.getVal(c)));

		encTile.tile = out;
		encTile.quantTable = quantizationTable;

		return encTile;
	}

	public Tile dequantize(Tile t) {
		// TODO Auto-generated method stub
		return null;
	}

}
