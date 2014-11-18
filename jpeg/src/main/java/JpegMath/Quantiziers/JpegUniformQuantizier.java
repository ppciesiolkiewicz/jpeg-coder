package JpegMath.Quantiziers;

import java.util.Iterator;

import DataObjects.EncodedTile;
import DataObjects.Tile;

public class JpegUniformQuantizier {
	private Tile<Double> quantumLuminance;
	private Tile<Double> quantumChrominance;

	public JpegUniformQuantizier(Double quality) {
		setQuantumValues();
		scaleQuantumMatrixes(quality);
	}

	private void setQuantumValues() {
		quantumLuminance = new Tile<Double>(new Double[][] {
				{ 16d, 11d, 10d, 16d, 24d, 40d, 51d, 61d },
				{ 12d, 12d, 14d, 19d, 26d, 58d, 60d, 55d },
				{ 14d, 13d, 16d, 24d, 40d, 57d, 69d, 56d },
				{ 14d, 17d, 22d, 29d, 51d, 87d, 80d, 62d },
				{ 18d, 22d, 37d, 56d, 68d, 109d, 103d, 77d },
				{ 24d, 35d, 55d, 64d, 81d, 104d, 113d, 92d },
				{ 49d, 64d, 78d, 87d, 103d, 121d, 120d, 101d },
				{ 72d, 92d, 95d, 98d, 112d, 100d, 103d, 99d },

		});
		quantumChrominance = new Tile<Double>(new Double[][] {
				{ 17d, 18d, 24d, 47d, 99d, 99d, 99d, 99d },
				{ 18d, 21d, 26d, 66d, 99d, 99d, 99d, 99d },
				{ 24d, 26d, 56d, 99d, 99d, 99d, 99d, 99d },
				{ 47d, 66d, 99d, 99d, 99d, 99d, 99d, 99d },
				{ 99d, 99d, 99d, 99d, 99d, 99d, 99d, 99d },
				{ 99d, 99d, 99d, 99d, 99d, 99d, 99d, 99d },
				{ 99d, 99d, 99d, 99d, 99d, 99d, 99d, 99d },
				{ 99d, 99d, 99d, 99d, 99d, 99d, 99d, 99d } });
	}

	private void scaleQuantumMatrixes(Double quality) {
		for (int i = 0; i < quantumLuminance.getLength(); i++) {
			Double temp = (quantumLuminance.getVal(i) * (quality + 50d)) / 100d;
			if (temp <= 0)
				temp = 1.0;
			if (temp > 255)
				temp = 255.0;
			quantumLuminance.setVal(i, temp);
		}

		for (int i = 0; i < quantumChrominance.getLength(); i++) {
			Double temp = (quantumChrominance.getVal(i) * (quality + 50)) / 100;
			if (temp <= 0)
				temp = 1.0;
			if (temp > 255)
				temp = 255.0;
			quantumChrominance.setVal(i, temp);
		}
	}

	public EncodedTile<Integer> quantize(Tile<Double> t) {
		EncodedTile<Integer> encTile = new EncodedTile<Integer>();

		Tile<Integer> out = calculateQuantiziedValues(t);
		removeRedundantElements(out);
		encTile.tile = out;
		encTile.quantTable = quantumLuminance;
		return encTile;
	}

	private void removeRedundantElements(Tile<Integer> out) {
		Iterator<Integer> it = out.zigZagIterator();

		int lastNotZeroIndex = 0, i = 0;
		while (it.hasNext()) {
			++i;
			if (it.next() != 0)
				lastNotZeroIndex = i;
		}

		it = out.zigZagIterator();
		i = 0;
		while (it.hasNext()) {
			it.next();
			if (i++ > lastNotZeroIndex)
				it.remove();
		}
	}

	private Tile<Integer> calculateQuantiziedValues(Tile<Double> t) {
		Tile<Integer> out = new Tile<Integer>(0);
		for (int c = 0; c < out.getLength(); c++)
			out.setVal(c,
					(int) Math.round(t.getVal(c) / quantumLuminance.getVal(c)));

		return out;
	}

	public Tile dequantize(Tile t) {
		// TODO Auto-generated method stub
		return null;
	}

}
