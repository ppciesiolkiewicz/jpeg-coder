package JpegMath.ColorConverter;

public interface ColorConverterInterface<E extends Number> {

	public E[] conversion(E[] elems);
	public E[] inverse_conversion(E[] elems);
}
