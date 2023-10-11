import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class KeyIn {
	String buf = null;
	
	public String readKey() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.forName(System.getProperty("native.encoding"))));
			buf = br.readLine();
		} catch(IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		return buf;
	}
	
	
	public String readKey(String msg) {
		System.out.print(msg + ">");
		return readKey();
	}
}