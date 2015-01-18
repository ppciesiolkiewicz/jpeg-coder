
public class ArgParser {
	public static ArgInfo parseArg(String[] args) {
		ArgInfo info = new ArgInfo();
		try {
			for(int i = 0; i < args.length; i++) {
				switch(args[i]) {
					case "-q":
						info.quality = Integer.parseInt(args[++i]);
						break;
					case "-i":
						info.input = args[++i];
						break;
					case "-o":
						info.output = args[++i];
						break;
					case "--gui":
						info.gui = true;
						break;
					default:
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
		s += "--gui - opens graphical user interface\n";
		s += "Otherwise parameters given below should be provided: \n";
		s += "-i input file path (default input.jpg) \n";
		s += "-o output file path (default output.jpg) \n";
		s += "-q quality of image in percent (default 80) \n";
		return s;
	}
}
