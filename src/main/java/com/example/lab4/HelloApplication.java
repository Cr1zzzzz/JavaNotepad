package com.example.lab4;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    private TextArea Tf;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Tf = (TextArea) fxmlLoader.getNamespace().get("Tf");
        scene.getStylesheets().add("style.css");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            event.consume(); // Відміна стандартної обробки події закриття
            showConfirmationDialog(stage);
        });
        stage.show();

    }
    private void showConfirmationDialog(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Закриття програми");
        alert.setHeaderText("Потрібно зберегти файл?");
        alert.setContentText("Оберіть опцію:");

        ButtonType saveButton = new ButtonType("Зберегти");
        ButtonType dontSaveButton = new ButtonType("Не зберігати");
        ButtonType cancelButton = new ButtonType("Відмінити", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(saveButton, dontSaveButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == saveButton) {
                handleSaveAction(stage);
            } else if (result.get() == dontSaveButton) {
                stage.close();
            }
        }
    }

    private void handleSaveAction(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(Tf.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}
