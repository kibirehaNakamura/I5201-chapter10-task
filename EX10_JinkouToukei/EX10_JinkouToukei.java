import java.util.ArrayList;
import java.util.stream.IntStream;

public class EX10_JinkouToukei {
	public static void main(String[] args) {
		FileIn fi = new FileIn();
		boolean flag = false;	//ファイル操作の成否を受けるフラグ
		/* 読み込むファイルを開く */
		try {
			flag = fi.open(args[0]);
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
		
		/* ArrayList型っていうサイズ可変の配列みたいなのがあるんだって　便利だなあ */
		/* 急に大阪が独立宣言しても安心 */
		ArrayList<String> region = new ArrayList<>();
		ArrayList<Integer> area = new ArrayList<>();
		ArrayList<Integer> pop = new ArrayList<>();
		
		/* データの読み込み、保存 */
		while(true) {
			String buf = fi.readLine();
			if(buf == null) break;
			/* splitメソッドで分けて */
			String[] spl = buf.split("\\s");
			/*
			intはプリミティブ型、Integerはオブジェクト型(テキストのStringの解説で出た参照型と同義？わからん)
			リストはオブジェクト型しか使えないのでint型じゃなくてInteger型の数値を入れる必要がある
			int⇔Integerのように対応してるのはJavaくんが気を利かせて必要な時に勝手に変換してくれるらしい
			これをオートボクシング/オートアンボクシングというらしいぞ　殴り合え
			*/
			/* 対応するリストに型変換しながら入れていく */
			try {
				region.add(spl[0]);
				area.add(Integer.parseInt(spl[1]));
				pop.add(Integer.parseInt(spl[2]));
			} catch(NumberFormatException e) {
				System.out.println("データの記入形式に誤りがあります : " + buf);
				System.out.println("面積か人口に小数点か数字以外の文字が含まれています");
				System.out.println("プログラムを終了します");
				System.exit(1);
			} catch(IndexOutOfBoundsException e) {
				System.out.println("データの記入形式に誤りがあります : " + buf);
				System.out.println("データが半角スペースで区切られていない可能性があります");
				System.out.println("プログラムを終了します");
				System.exit(1);
			}
		}
		
		/* データの集計 */
		/* コレクションの要素全部足すみたいなメソッドなかった？前の課題のIssueにあったわ */
		/* CollectionじゃなくてStreamだからリファレンスのCollection見ても見つからなかったんだ */
		/* オートアンボクシングしてくれなかった… */
		// int popSum = IntStream.of(pop).sum();
		/* 悲しみの拡張for文 */
		int popSum = 0;
		for(int popbuf : pop) {
			popSum += popbuf;
		}
		
		/* 表の出力 */
		System.out.println("*** 地域別人口統計 ***");
		System.out.println("地域　　面積 　人口　人口密度 人口比率");
		System.out.println("--------------------------------------");
		for(int i = 0; i < region.size(); i++) {
			System.out.printf("%s　%5d　%4d　%7.2f　%5.2f", region.get(i), area.get(i), pop.get(i),(pop.get(i) * 10000.0 / area.get(i)), ((double)pop.get(i) / popSum * 100));
			System.out.println();
		}
	}
}