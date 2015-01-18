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
		
		System.out.print(info.toString());
		app.run(info);
	}
}