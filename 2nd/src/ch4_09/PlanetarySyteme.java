package ch4_09;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Q：惑星を表す円をアニメーション化して、楕円軌道を回るようにしなさい。
 *
 * @see http://docs.oracle.com/javase/jp/8/javafx/api/javafx/scene/shape/Ellipse
 *      .html
 */
public class PlanetarySyteme extends Application {

	public void start(Stage stage) throws Exception {
		final double radiusX = 200;
		final double radiusY = 160;
		final double centerX = radiusX + 24;
		final double centerY = radiusY + 24;

		// 楕円軌道の作成
		Ellipse orbit = new Ellipse(radiusX, radiusY); // JavaFXの楕円クラス
		orbit.setCenterX(centerX);
		orbit.setCenterY(centerY);
		orbit.setStroke(Color.LAVENDER);
		orbit.setFill(null);

		// 恒星
		Circle sun = new Circle(48, Color.GOLD);
		sun.setCenterX(centerX);
		sun.setCenterY(centerY);

		// 惑星
		Circle planet = new Circle(16, Color.SLATEBLUE);
		planet.setCenterX(centerX + radiusX);
		planet.setCenterY(centerY);

		// 惑星の周回軌道
		PathTransition transition = new PathTransition();
		transition.setNode(planet);
		transition.setPath(orbit);
		transition.setDuration(Duration.millis(4_000));
		transition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		transition.setInterpolator(Interpolator.LINEAR);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.play();

		Group group = new Group(orbit, planet, sun);
		stage.setScene(new Scene(group, centerX * 2, centerY * 2));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
