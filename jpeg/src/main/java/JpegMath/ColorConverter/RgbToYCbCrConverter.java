package JpegMath.ColorConverter;


//TODO use instead of ImageToYCbCrArray
public class RgbToYCbCrConverter implements
		ColorConverterInterface<Integer> {

	public Integer[] conversion(Integer[] rgb) {
		Integer[] ycbcr = new Integer[3];
		int r = rgb[0], g = rgb[1], b = rgb[2];
		
		ycbcr[0] = (int) (0 + 0.299 * r + 0.587 * g + 0.114 * b); // Y
		ycbcr[1] = (int) (128 - 0.168736 * r - 0.331264 * g + 0.5 * b); // Cb
		ycbcr[2] = (int) (128 + 0.5 * r - 0.418618 * g - 0.081312 * b); // Cr
		return ycbcr;
	}

	public Integer[] inverse_conversion(Integer[] ycbcr) {
		Integer[] rgb = new Integer[3];
		int y = ycbcr[0], cb = ycbcr[1], cr = ycbcr[2];
		
		rgb[0] = (int) (0 + 0.299 * y + 0.587 * cb + 0.114 * cr); // Y
		rgb[1] = (int) (128 - 0.168736 * y - 0.331264 * cb + 0.5 * cr); // Cb
		rgb[2] = (int) (128 + 0.5 * y - 0.418618 * cb - 0.081312 * cr); // Cr
		return rgb;	}
}
