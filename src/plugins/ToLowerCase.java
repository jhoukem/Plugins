package plugins;

public class ToLowerCase implements Plugin{

	@Override
	public String transform(String s) {
		return s.toLowerCase();
	}

	@Override
	public String getLabel() {
		return "To Lower Case";
	}
}
