public class EX10_InputTiiki {
	public static void main(String[] args) {
		FileOut fo = new FileOut();
		boolean flag = false;	//ファイル操作の成否を受けるフラグ
		/* 書き込むファイルを開く */
		try {
			flag = fo.open(args[0]);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("ファイル名が指定されていません");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		if(flag == false) {
			System.out.println("ファイルオープン時にエラーが発生しました");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		System.out.println("---- 地域データ入力開始 ----");
		KeyIn ki = new KeyIn();
		int count = 0;	// 入力件数カウンタ
		
		/* 途中終了時に丸ごと抜け出す用のラベル */
		InputData:
		/* データ入力を繰り返すためのfor */
		for(count = 0; count < 8; count++) {
			String data = "";	// これに入力情報を足して1行にして書き込む
			String buf = "";	// これはバッファ
			/* 地域の入力 */
			while(true) {
				buf = ki.readKey("地域(未入力で終了)");
				if(buf == null) {
					System.out.println("ｶﾞｯ");
				}else if(buf.isEmpty()) {
					break InputData;
				}else if(buf.length() == 3) {
					break;
				} else {
					System.out.println("入力に誤りがあります");
					System.out.println("地域名が2文字の場合は間に全角スペースを入れてください");
				}
			}
			data += buf;
			
			/* 面積の入力 */
			int bufint;	//これはint型変換時のバッファ
			while(true) {
				buf = ki.readKey("面積(km2)");
				try {
					bufint = Integer.parseInt(buf);
				} catch(NumberFormatException e) {
					System.out.println("入力に誤りがあります");
					System.out.println("数字以外の値や小数点を入力しないでください");
					continue;
				}
				if(bufint <= 0 || 100000 <= bufint) {
					System.out.println("入力に誤りがあります");
					System.out.println("数値が範囲外です : 0以下や6桁以上の数値を入力しないでください");
				} else {
					break;
				}
			}
			data += " " + bufint;
			
			/* 人口の入力 */
			while(true) {
				buf = ki.readKey("人口(万人)");
				try {
					bufint = Integer.parseInt(buf);
				} catch(NumberFormatException e) {
					System.out.println("入力に誤りがあります");
					System.out.println("数字以外の値や小数点を入力しないでください");
					continue;
				}
				if(bufint <= 0 || 10000 <= bufint) {
					System.out.println("入力に誤りがあります");
					System.out.println("数値が範囲外です : 0以下や5桁以上の数値を入力しないでください");
				} else {
					break;
				}
			}
			data += " " + bufint;
			
			/* まとめたデータをファイルへ書き込み */
			flag = fo.writeln(data);
			if(flag == false) {
				System.out.println("データ書き込み時にエラーが発生しました");
				System.out.println("プログラムを終了します");
				fo.close();
				System.exit(1);
			}
		}
		
		System.out.println("---- 地域データ入力終了 ----");
		System.out.println("地域データ入力件数 = " + count + "件");
		
		flag = fo.close();
		if(flag == false) {
			System.out.println("ファイルクローズ時にエラーが発生しました");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
	}
}