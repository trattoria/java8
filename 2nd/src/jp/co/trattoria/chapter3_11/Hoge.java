package jp.co.trattoria.chapter3_11;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);

	default ColorTransformer compose(ColorTransformer before) {
		return (x, y, c) -> apply(x, y, before.apply(x, y, c));
	}
}

public class Hoge extends Application {

	/**
	 * x座標、y座標を無視するUnaryOperator<Color>をColorTransformer<Color>へ変えるメソッド
	 *
	 * @param op
	 *            関数型インタフェース
	 * @return ColorTransformerの関数型インタrフェース
	 */
	static ColorTransformer colorOnly(UnaryOperator<Color> op) {

		return (x, y, c) -> op.apply(c);
	}

	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}

	// いわゆるコールバック実行するメソッド
	public static Image transform(Image in, ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
		return out;
	}

	public void start(Stage stage) throws Exception {
		Image image = new Image("jp/co/trattoria/chapter3_5/queen-mary.png");

		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// 指定範囲はグレー、それ以外はそのまま・・・の実装
		ColorTransformer tempFrame = (x, y, c) -> x < 10 || y < 10 || x >= width - 10 || y >= height - 10 ? Color.GRAY
				: c;

		Image image2 = transform(image, tempFrame.compose(Hoge.colorOnly(Color::brighter)));

		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2))));
		stage.show();
	}

	public static void main(String[] args) {

		Application.launch(args);
	}

}
