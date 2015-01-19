import ArgParser.ArgInfo;
import ArgParser.ArgParser;

public class Main {
	static Application app;
	public static void main(String[] args) {
		final ArgInfo info = ArgParser.parseArg(args);
		
		if(info.gui == false) {
			app = new ConsoleApplication();
		}
		else {
			return;
			//app = new GuiApplication(); TODO
		}
		
		System.out.println(info.toString());
		app.run(info);
		System.out.println("Finished sucessfully");
	}
}