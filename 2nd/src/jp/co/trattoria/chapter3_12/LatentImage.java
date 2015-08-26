package jp.co.trattoria.chapter3_12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);

	default ColorTransformer compose(ColorTransformer before) {
		return (x, y, c) -> apply(x, y, before.apply(x, y, c));
	}

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
}

public class LatentImage {

	private final Image in;
	private List<ColorTransformer> pendingOperations;

	private LatentImage(Image in) {
		this.in = in;
		pendingOperations = new ArrayList<ColorTransformer>();
	}

	public static LatentImage from(Image in) {
		return new LatentImage(in);
	}

	/** 座標を無視 */
	public LatentImage transform(UnaryOperator<Color> f) {
		pendingOperations.add(ColorTransformer.colorOnly(f));
		return this;
	}

	/** 色変換 */
	public LatentImage transform(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	/** 色変換適用済みの画像を取得 */
	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (ColorTransformer f : pendingOperations) {
					c = f.apply(x, y, c);
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
}