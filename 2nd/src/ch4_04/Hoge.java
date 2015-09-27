package ch4_04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Q：円が真ん中に配置され、シーンの4つの辺の少なくとも2つの辺に常に接するように機能拡張しなさい。
 * A：円の中心且つ、辺に接する条件を満たすには、円の大きさを変更すればよい。
 */
public class Hoge extends Application {

	public void start(Stage stage) throws Exception {
		Circle circle = new Circle(100, 100, 100);
		circle.setFill(Color.AQUA);
		Pane pane = new Pane();
		pane.getChildren().add(circle);
		Scene scene = new Scene(pane);

		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2.0));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2.0));
		circle.radiusProperty().bind(Bindings.divide(Bindings.min(scene.widthProperty(), scene.heightProperty()), 2.0));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
