public class Main {
	static Application app;
	public static void main(String[] args) {
		final String inputPath = args[0];
		final String outputPath = args[1];
		final Integer quality = Integer.parseInt(args[2]);
		System.out.println(inputPath+" "+outputPath+" "+quality);
		app = new ConsoleApplication();
		app.run(inputPath, outputPath, quality);
	}
}