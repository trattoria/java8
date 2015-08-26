package jp.co.trattoria.chapter3_12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Hoge extends Application {

	public void start(Stage stage) throws Exception {
		Image image = new Image("jp/co/trattoria/chapter3_5/queen-mary.png");
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();

		ColorTransformer frame = (x, y, c) -> x < 10 || y < 10 || x >= width - 10 || y >= height - 10 ? Color.AQUAMARINE
				: c;

		Image image2 = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale).transform(frame)
				.toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2))));
		stage.show();
	}

	public static void main(String[] args) {

		Application.launch(args);
	}

}
