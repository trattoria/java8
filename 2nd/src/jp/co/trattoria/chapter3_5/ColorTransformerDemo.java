package jp.co.trattoria.chapter3_5;
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
}

/**
 *
 * @see http://libro.tuyano.com/index3?id=12466003&page=2
 * @see http://naokirintechnews.hatenablog.com/entry/2012/01/19/134715
 * @see http://www.cresc.co.jp/tech/java/javafx/JavaFX_on_Eclipse.html
 */
public class ColorTransformerDemo extends Application {

	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}

	public static Image transform(Image in, ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(x, y, in.getPixelReader().getColor(x, y)));
		return out;
	}

	public void start(Stage stage) throws Exception{
		Image image = new Image("jp/co/trattoria/chapter3_5/queen-mary.png");

		Image image2 = transform(image, Color::brighter);

		int width  = (int)image.getWidth();
		int height = (int)image.getHeight();
		Image image3 = transform(image, (x, y, c) -> x < 10 || y < 10 || x >= width - 10 || y >= height - 10 ? Color.GRAY : c);

		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2), new ImageView(image3))));
		stage.show();
	}

	public static void main(String[] args) {

		Application.launch(args);
	}

}

