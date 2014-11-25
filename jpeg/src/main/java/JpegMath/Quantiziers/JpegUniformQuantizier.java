package JpegMath.Quantiziers;

import java.util.Iterator;

import DataObjects.EncodedTile;
import DataObjects.Tile;

public class JpegUniformQuantizier {
	private Tile<Integer> quantumLuminance;
	private Tile<Integer> quantumChrominance;

	public JpegUniformQuantizier(Integer quality) {
		setQuantumValues();
		scaleQuantumMatrixes(quality);
	}

	private void setQuantumValues() {
		quantumLuminance = new Tile<Integer>(new Integer[][] {
				{ 16, 11, 10, 16, 24, 40, 51, 61 },
				{ 12, 12, 14, 19, 26, 58, 60, 55 },
				{ 14, 13, 16, 24, 40, 57, 69, 56 },
				{ 14, 17, 22, 29, 51, 87, 80, 62 },
				{ 18, 22, 37, 56, 68, 109, 103, 77 },
				{ 24, 35, 55, 64, 81, 104, 113, 92 },
				{ 49, 64, 78, 87, 103, 121, 120, 101 },
				{ 72, 92, 95, 98, 112, 100, 103, 99 },

		});
		quantumChrominance = new Tile<Integer>(new Integer[][] {
				{ 17, 18, 24, 47, 99, 99, 99, 99 },
				{ 18, 21, 26, 66, 99, 99, 99, 99 },
				{ 24, 26, 56, 99, 99, 99, 99, 99 },
				{ 47, 66, 99, 99, 99, 99, 99, 99 },
				{ 99, 99, 99, 99, 99, 99, 99, 99 },
				{ 99, 99, 99, 99, 99, 99, 99, 99 },
				{ 99, 99, 99, 99, 99, 99, 99, 99 },
				{ 99, 99, 99, 99, 99, 99, 99, 99 } });
	}

	public Tile<Integer> getQuantumLuminance() {
		return quantumLuminance;
	}

	public Tile<Integer> getQuantumChrominance() {
		return quantumChrominance;
	}

	private void scaleQuantumMatrixes(Integer quality) {
		double S;
		if( quality > 100)
			quality = 100;
		if (quality <50)
			S= 5000/quality;
		else
			S= 200-(2*quality);
		for (int i = 0; i < quantumLuminance.getLength(); i++) {
			Integer temp = (int) ((S* quantumLuminance.getVal(i) +50)/ 100);
			if (temp <= 0)
				temp = 1;
			if (temp > 255)
				temp = 255;
			quantumLuminance.setVal(i, temp);
		}

		for (int i = 0; i < quantumChrominance.getLength(); i++) {
			Integer temp =  (int) ((S* quantumLuminance.getVal(i) +50)/ 100);
			if (temp <= 0)
				temp = 1;
			if (temp > 255)
				temp = 255;
			quantumChrominance.setVal(i, temp);
		}
	}

	public Tile<Integer> quantize(Tile<Double> t, Integer tableNo) {
		Tile<Integer> out = calculateQuantiziedValues(t, tableNo);
		//removeRedundantElements(out); We don't need to remove them...... SHIT, IT WAS HARD AND USELESS IMPLEMENTATION
		
		return out;
	}

	private void removeRedundantElements(Tile<Integer> out, Integer tableNo) {
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

	private Tile<Integer> calculateQuantiziedValues(Tile<Double> t, Integer tableNo) {
		Tile<Integer> quantTable = tableNo==0 ? quantumLuminance : quantumChrominance;
		Tile<Integer> out = new Tile<Integer>(0);
		
		for (int c = 0; c < out.getLength(); c++)
			out.setVal(c,
					(int) Math.round(t.getVal(c) / quantTable.getVal(c)));

		return out;
	}

	public Tile dequantize(Tile t) {
		// TODO Auto-generated method stub
		return null;
	}

}
