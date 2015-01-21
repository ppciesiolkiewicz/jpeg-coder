import Application.Application;
import Application.ConsoleApplication;
import ArgParser.ArgInfo;
import ArgParser.ArgParser;
import GUI.MainWIndow;

public class Main {
	static Application app;

	public static void main(String[] args) {
		final ArgInfo info = ArgParser.parseArg(args);
		System.out.println(info.toString());
		
		if (info.gui == false) {
			app = new ConsoleApplication();
		} else {
			MainWIndow.run();
			return;
		}

		
		app.run(info);
	}
}
