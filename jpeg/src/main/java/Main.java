public class Main {
	static Application app;
	public static void main(String[] args) {
		final ArgInfo info = ArgParser.parseArg(args);
		
		if(info.gui == false)
			app = new ConsoleApplication();
		else
			;//app = new GuiApplication(); TODO
		
		
		app.run(info);
	}
}