package ArgParser;

import java.util.HashMap;
import java.util.Map;

public class ArgInfo {
	public ArgInfo() {
		gui = false; 
		action = null;
		input = "";
		output = "output.jpg";
		quality = 80;

		initFormatMap();
	}

	public enum ConversionType {
		JPEG, JPEG2000
	}

	private void initFormatMap() {
		formatMap.put(".jpg", ConversionType.JPEG);
		formatMap.put(".JPG", ConversionType.JPEG);
		formatMap.put(".jpeg", ConversionType.JPEG);
		formatMap.put(".JPEG", ConversionType.JPEG);

		formatMap.put(".j2k", ConversionType.JPEG2000);
		formatMap.put(".jp2", ConversionType.JPEG2000);
	}

	public static Map<String, ConversionType> formatMap = new HashMap<String, ConversionType>();
	public boolean gui;
	public String input;
	public String output;
	public Integer quality;
	public ActionType action;
	public enum ActionType {encode, decode};
	

	@Override
	public String toString() {
		return "Arguments info: \naction=" + action + "\ngui=" + gui
				+ ", \ninput=" + input + ", \noutput=" + output
				+ ", \nquality=" + quality + "\njpeg=" + isJpeg()
				+ ", \njpeg2000=" + isJpeg2000();
	}

	public boolean doEncode() {
		return action == ActionType.encode;
	}

	public boolean doDecode() {
		return action == ActionType.decode;
	}

	public boolean isJpeg() {
		if (doEncode()) {
			int dotPos = output.indexOf('.');
			if (dotPos == -1)
				return false;
			return (formatMap.get(output.substring(dotPos, output.length()))
					.equals("jpeg"));
		} else { // Couldnt be "else if (doDecode())"
			int dotPos = input.indexOf('.');
			if (dotPos == -1)
				return false;
			return (formatMap.get(input.substring(dotPos, input.length()))
					.equals("jpeg"));
		}
	}

	public boolean isJpeg2000() {
		if (doEncode()) {
			int dotPos = output.indexOf('.');
			if (dotPos == -1)
				return false;
			return (formatMap.get(output.substring(dotPos, output.length()))
					.equals("jpeg2000"));
		} else { // Couldnt be "else if (doDecode())"
			int dotPos = input.indexOf('.');

			if (dotPos == -1)
				return false;
			return (formatMap.get(input.substring(dotPos, input.length()))
					.equals("jpeg2000"));
		}
	}

	public boolean isCorrect() {
		if (gui == true)
			return true;

		return (!action.equals("") && !input.equals(""))
				&& ((isJpeg2000() && !isJpeg()) || (isJpeg() && !isJpeg2000()));
	}

}
