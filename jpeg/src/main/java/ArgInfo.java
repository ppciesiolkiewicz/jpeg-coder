import java.util.HashMap;
import java.util.Map;


public class ArgInfo {
	public ArgInfo() {
		gui = false; 
		action = "";
		input = "";
		output = "output.jpg";
		quality = 80;
		
		initFormatMap();
	}
	
	private void initFormatMap() {
		String jpeg= "jpeg";
		String jpeg2000="jpeg2000";
		
		
		formatMap.put(".jpg", jpeg);
		formatMap.put(".JPG", jpeg);
		formatMap.put(".jpeg", jpeg);
		formatMap.put(".JPEG", jpeg);
		
		formatMap.put(".j2k", jpeg2000);
		formatMap.put(".jp2", jpeg2000);
	}
	
	public static Map<String,String> formatMap = new HashMap<String,String>();
	public boolean gui;
	public String input;
	public String output;
	public Integer quality;
	public String action;
	
	@Override
	public String toString() {
		return "Arguments info: \naction="+action+"\ngui=" + gui + "\ngui=" + gui + ", \ninput=" + input + ", \noutput="
				+ output + ", \nquality=" + quality + "\njpeg="+ isJpeg() + ", \njpeg2000="+isJpeg2000();
	}

	public boolean doEncode() {
		return action.equals("encode");
	}

	public boolean doDecode() {
		return action.equals("decode");
	}
	
	public boolean isJpeg() {
		try {
			if(doEncode()) {
				int dotPos = output.indexOf('.');
				return (formatMap.get(output.substring(dotPos, output.length())).equals("jpeg"));
			}
			int dotPos = input.indexOf('.');
			return (formatMap.get(input.substring(dotPos, input.length())).equals("jpeg"));
		} catch(Exception e) {
			System.err.println("Incorrect file format");
			//System.exit(-1);
		}
		return false;
	}
	
	public boolean isJpeg2000() {
		try {
			if(doEncode()) {
				int dotPos = output.indexOf('.');
				return (formatMap.get(output.substring(dotPos, output.length())).equals("jpeg2000"));
			}
			int dotPos = input.indexOf('.');
			return (formatMap.get(input.substring(dotPos, input.length())).equals("jpeg2000"));
		} catch(Exception e) {
			System.err.println("Incorrect file format");
			//System.exit(-1);
		}
		return false;
	}
	
	public boolean isCorrect() {
		if(gui == true)
			return true;
		
		return (!action.equals("") && !input.equals("")) 
				&& ((isJpeg2000() && !isJpeg()) || 
					(isJpeg() && !isJpeg2000()));
	}
	
}
