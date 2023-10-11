import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileOut {
	BufferedWriter bw = null;
	
	public boolean open(String fname) {
		boolean sts = true;
		try {
			bw = new BufferedWriter(new FileWriter(fname));
		} catch (IOException e) {
			System.out.println("ファイル名に誤りがあります");
			System.out.println(e);
			sts = false;
		}
		return sts;
	}
	
	
	public boolean writeln(String str) {
		boolean sts = true;
		try {
			bw.write(str);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("書き込みエラー");
			System.out.println(e);
			sts = false;
		}
		return sts;
	}
	
	
	public boolean close() {
		boolean sts = true;
		try {
			bw.close();
		} catch(IOException e) {
			System.out.println("ファイルクローズエラー");
			System.out.println(e);
			sts = false;
		}
		return sts;
	}
}