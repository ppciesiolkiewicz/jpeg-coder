package Application;

import ArgParser.ArgInfo;
import Factories.AbstractJpegEncoderDecoderFactory;


public interface Application {
	public void run();
	public void run(ArgInfo info);
}