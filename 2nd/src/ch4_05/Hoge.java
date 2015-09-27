package ch4_05;

import static javafx.beans.binding.Bindings.*;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Q：2つのメソッドを書きなさい。
 *
 */
public class Hoge extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Button smaller = new Button("Smaller");
		Button larger = new Button("Larger");
		Rectangle gauge = new Rectangle(0, 5, 50, 15);
		Rectangle outline = new Rectangle(0, 5, 100, 15);
		outline.setFill(null);
		outline.setStroke(Color.BLACK);
		Pane pane = new Pane();
		pane.getChildren().addAll(gauge, outline);

		smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
		larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));

		// smaller.disableProperty().bind(lessThanOrEqual(gauge.widthProperty(),
		// 0));
		// larger.disableProperty().bind(greaterThanOrEqual(gauge.widthProperty(),
		// 100));
		smaller.disableProperty().bind(observe(t -> t.intValue() <= 0, gauge.widthProperty()));
		larger.disableProperty().bind(observe(t -> t.intValue() >= 100, gauge.widthProperty()));

		HBox box = new HBox(10);
		box.getChildren().addAll(smaller, pane, larger);
		Scene scene = new Scene(box);
		stage.setScene(scene);
		stage.show();
	}

	public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t) {
		return createObjectBinding(() -> f.apply(t.getValue()), t);
	}

	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t,
			ObservableValue<U> u) {
		return createObjectBinding(() -> f.apply(t.getValue(), u.getValue()), t, u);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
