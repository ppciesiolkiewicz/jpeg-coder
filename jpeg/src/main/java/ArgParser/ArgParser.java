package ArgParser;

public class ArgParser {
	public static ArgInfo parseArg(String[] args) {
		ArgInfo info = new ArgInfo();

		try {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("encode")) {
					info.action = ArgInfo.ActionType.encode;
				} 
				else if(args[i].equals("decode")) {
					info.action = ArgInfo.ActionType.decode;
				}
				else if (args[i].equals("-q")) {
					info.quality = Integer.parseInt(args[++i]);

				} else if (args[i].equals("-i")) {
					info.input = args[++i];

				} else if (args[i].equals("-o")) {
					info.output = args[++i];

				} else if (args[i].equals("--gui")) {
					info.gui = true;
				} else {
					throw new RuntimeException();
				}
			}
		
		} catch (Exception e) {
			System.err.print("Argument parse error\n\n");
			System.err.print(getInfo());
			System.exit(-1);
		}
		
		if (!info.isCorrect()) {
			System.err.print("Argument parse error\n\n");
			System.err.print(getInfo());
			System.exit(-1);
		}

		return info;
	}

	public static String getInfo() {
		String s = "Usage:\n";
		s += "java -jar jpeg.jar encode/decode -i input_file ";
		s += "-o output_file (default output.jpg) ";
		s += "-q quality_of_image_in_percent (default 80)\n";
		s += "OR\njava -jar jpeg.jar --gui- opens graphical user interface\n";
		
		return s;
	}
}