import ArgParser.ArgInfo;
import ArgParser.ArgParser;
import GUI.MainWIndow;

public class Main {
	static Application app;
	public static void main(String[] args) {
		final ArgInfo info = ArgParser.parseArg(args);
		
		if(!info.action.equals("--gui")) {
			app = new ConsoleApplication();
		}
		else {
			MainWIndow.main(args);
			return;
		}
		
		System.out.println(info.toString());
		app.run(info);
		System.out.println("Finished sucessfully");
	}
}