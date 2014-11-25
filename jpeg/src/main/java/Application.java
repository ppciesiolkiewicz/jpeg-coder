import Factories.AbstractJpegEncoderDecoderFactory;


public interface Application {
	public void run();

	public void run(String inputPath, String outputPath, int quality);
}