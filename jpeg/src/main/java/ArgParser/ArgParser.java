package ArgParser;

public class ArgParser {
	public static ArgInfo parseArg(String[] args) {
		ArgInfo info = new ArgInfo();
		info.action = args[0];
		
		try {
			for(int i = 1; i < args.length; i++) {
				if (args[i].equals("-q")) {
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
				if(!info.isCorrect()) {
					throw new RuntimeException();
				}
			}
		
		} catch(Exception e) {
			System.err.print("Argument parse error\n\n");
			System.err.print(getInfo());
			System.exit(-1);
		}
		
		
		return info;
	}
	
	public static String getInfo() {
		String s = "Usage:\n";
		s += "application encode/decode -i input_file\n";
		s += "-o output file path (default output.jpg)\n";
		s += "-q quality of image in percent (default 80)\n";
		
		s += "--gui- opens graphical user interface\n";
		return s;
	}
}
