package ch4_01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Hello extends Application {

	public static void main(String[] args) {
		// P.86にmain()は不要とあるが、ないと実行できない？
		// 実行の構成を用意しないといけないようなので、現状はmain()で実行しておく。
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {

		Label message = new Label("Hello, JavaFX.");
		message.setFont(new Font(100));

		TextField textField = new TextField(message.getText());
		textField.textProperty().addListener(property -> message.setText(textField.getText()));

		VBox root = new VBox();
		root.getChildren().addAll(textField, message);

		stage.setScene(new Scene(root));
		stage.setTitle("Hello");
		stage.show();

	}

}
