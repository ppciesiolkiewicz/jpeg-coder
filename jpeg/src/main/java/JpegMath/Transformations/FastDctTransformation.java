package JpegMath.Transformations;

import DataObjects.Tile;

public class FastDctTransformation implements TransformationInterface {

	public  Tile<Double> transfom(Tile<Integer> input) {
		Double output[][] = new Double[8][8];
		Double tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
		Double tmp10, tmp11, tmp12, tmp13;
		Double z1, z2, z3, z4, z5, z11, z13;

		// Subtracts 128 from the input values
		for (int i = 0; i < 8; i++)
			for ( int j = 0; j < 8; j++)
				output[i][j] = input.getVal(i, j).doubleValue();

		for (int i = 0; i < 8; i++) {
			tmp0 = output[i][0] + output[i][7];
			tmp7 = output[i][0] - output[i][7];
			tmp1 = output[i][1] + output[i][6];
			tmp6 = output[i][1] - output[i][6];
			tmp2 = output[i][2] + output[i][5];
			tmp5 = output[i][2] - output[i][5];
			tmp3 = output[i][3] + output[i][4];
			tmp4 = output[i][3] - output[i][4];

			tmp10 = tmp0 + tmp3;
			tmp13 = tmp0 - tmp3;
			tmp11 = tmp1 + tmp2;
			tmp12 = tmp1 - tmp2;

			output[i][0] = tmp10 + tmp11;
			output[i][4] = tmp10 - tmp11;

			z1 = (tmp12 + tmp13) * 0.707106781d;
			output[i][2] = tmp13 + z1;
			output[i][6] = tmp13 - z1;

			tmp10 = tmp4 + tmp5;
			tmp11 = tmp5 + tmp6;
			tmp12 = tmp6 + tmp7;

			z5 = (tmp10 - tmp12) * 0.382683433d;
			z2 = 0.541196100d * tmp10 + z5;
			z4 = 1.306562965d * tmp12 + z5;
			z3 = tmp11 * 0.707106781d;

			z11 = tmp7 + z3;
			z13 = tmp7 - z3;

			output[i][5] = z13 + z2;
			output[i][3] = z13 - z2;
			output[i][1] = z11 + z4;
			output[i][7] = z11 - z4;
		}

		for (int i = 0; i < 8; i++) {
			tmp0 = output[0][i] + output[7][i];
			tmp7 = output[0][i] - output[7][i];
			tmp1 = output[1][i] + output[6][i];
			tmp6 = output[1][i] - output[6][i];
			tmp2 = output[2][i] + output[5][i];
			tmp5 = output[2][i] - output[5][i];
			tmp3 = output[3][i] + output[4][i];
			tmp4 = output[3][i] - output[4][i];

			tmp10 = tmp0 + tmp3;
			tmp13 = tmp0 - tmp3;
			tmp11 = tmp1 + tmp2;
			tmp12 = tmp1 - tmp2;

			output[0][i] = tmp10 + tmp11;
			output[4][i] = tmp10 - tmp11;

			z1 = (tmp12 + tmp13) * 0.707106781d;
			output[2][i] = tmp13 + z1;
			output[6][i] = tmp13 - z1;

			tmp10 = tmp4 + tmp5;
			tmp11 = tmp5 + tmp6;
			tmp12 = tmp6 + tmp7;

			z5 = (tmp10 - tmp12) * 0.382683433d;
			z2 = 0.541196100d * tmp10 + z5;
			z4 = 1.306562965d * tmp12 + z5;
			z3 = tmp11 * 0.707106781d;

			z11 = tmp7 + z3;
			z13 = tmp7 - z3;

			output[5][i] = z13 + z2;
			output[3][i] = z13 - z2;
			output[1][i] = z11 + z4;
			output[7][i] = z11 - z4;
		}

		return new Tile<Double>(output);
	}


	public Tile<Integer> inverseTransform(Tile<Double> t) {
		// TODO Auto-generated method stub
		return null;
	}
}
