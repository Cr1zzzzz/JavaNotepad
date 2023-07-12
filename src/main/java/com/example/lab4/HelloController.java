package com.example.lab4;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.print.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.scene.text.Font;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;
import java.util.Optional;

public class HelloController {

    @FXML
    public TextArea Tf;
    @FXML
    private TextField Sizee;
    @FXML
    public String gettext()
    {
        String text = Tf.getText();
        return text;
    }
    @FXML
    private void handleOpenAction() {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Current file is modified");
            alert.setContentText("Do you want to save the changes before opening a new file?");

            ButtonType saveButton = new ButtonType("Save");
            ButtonType discardButton = new ButtonType("Discard");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(saveButton, discardButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == saveButton) {
                handleSaveAction();
                openFile();
            } else if (result.isPresent() && result.get() == discardButton) {
                openFile();

        }
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                String fileContent = readFileContent(selectedFile);
                Tf.setText(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String readFileContent(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }

    @FXML
    private void handleCloseActionn() {

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
                handleSaveAction();System.exit(0);
            } else if (result.get() == dontSaveButton) {
                System.exit(0);
            }
        }
    }

    @FXML
    void handleSaveAction() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                saveToFile(file, Tf.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToFile(File file, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.close();
    }

    @FXML
    private void changeFontSize() {
        String Sizef = Sizee.getText();

        try {
            int Sizeff = Integer.parseInt(Sizef);
            Tf.setFont(Font.font(Sizeff));
        } catch (NumberFormatException e) {
//
        }

    }

    private boolean isBold = false;
    private boolean isUnderline = false;
    private boolean isItalic = false;
    private int isColor = 1;

    @FXML
    private void changeFontItalic() {
        String Sizef = Sizee.getText();
        if (!isItalic) {
            try {
                int Sizeff = Integer.parseInt(Sizef);
                if(!isBold){
                    Tf.setFont(Font.font("Serif", FontPosture.ITALIC, Sizeff));
                }
                else {
                    Tf.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.ITALIC, Sizeff));
                }
                isItalic = true;
            } catch (NumberFormatException e) {
                if(!isBold){
                    Tf.setFont(Font.font("Serif", FontPosture.ITALIC, 12));
                }
                else {
                    Tf.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.ITALIC, 12));
                }
                isItalic = true;
            }} else {
            try {
                int Sizeff = Integer.parseInt(Sizef);
                if(!isBold){
                Tf.setFont(Font.font("Serif", FontPosture.REGULAR, Sizeff));}
                else {
                    Tf.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.REGULAR, Sizeff));
                }
                isItalic = false;
            } catch (NumberFormatException e) {
                if(!isBold){
                    Tf.setFont(Font.font("Serif", FontPosture.REGULAR, 12));}
                else {
                    Tf.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.REGULAR, 12));
                }
                isItalic = false;
            }
        }
    }

    @FXML
    private void changeFontBold() {
        String Sizef = Sizee.getText();
        if (!isBold) {
            try {
                int Sizeff = Integer.parseInt(Sizef);
                if(!isItalic){
                Tf.setFont(Font.font("Serif", FontWeight.BOLD, Sizeff));}
                else {
                    Tf.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.ITALIC, Sizeff));
                }
                isBold = true;
            } catch (NumberFormatException e) {
                if(!isItalic){
                    Tf.setFont(Font.font("Serif", FontWeight.BOLD, 12));}
                else {
                    Tf.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.ITALIC, 12));
                }
                isBold = true;
            }
        } else {
            try {
                int Sizeff = Integer.parseInt(Sizef);
                if(!isItalic){
                    Tf.setFont(Font.font("Serif", FontWeight.NORMAL, Sizeff));}
                else {
                    Tf.setFont(Font.font("Serif", FontWeight.NORMAL, FontPosture.ITALIC, Sizeff));
                }
                isBold = false;
            } catch (NumberFormatException e) {
                if(!isItalic){
                    Tf.setFont(Font.font("Serif", FontWeight.NORMAL, 12));}
                else {
                    Tf.setFont(Font.font("Serif", FontWeight.NORMAL, FontPosture.ITALIC, 12));
                }
                isBold = false;
            }
        }

    }

    @FXML
    private void updateTextUnderline() {
        if (isUnderline) {
            Tf.getStyleClass().remove("text-area-underline-text");
            isUnderline = false;
        } else {
            Tf.getStyleClass().add("text-area-underline-text");
            isUnderline = true;
        }
    }


    @FXML
    private void updateColor() {
        ColorPicker colorPicker = new ColorPicker();

        Stage colorStage = new Stage();
        colorStage.setTitle("Виберіть колір тексту");
        colorStage.setScene(new Scene(colorPicker, 300, 200));
        colorStage.show();

        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            Tf.setStyle("-fx-text-fill: " + toRGBCode(selectedColor) + ";");
            colorStage.close();
        });
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    @FXML
    private void printText() {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.getDefaultPageLayout();

        PrinterJob job = PrinterJob.createPrinterJob(printer);
        boolean proceed = job.showPrintDialog(Tf.getScene().getWindow());
        if (proceed) {
            job.getJobSettings().setPageLayout(pageLayout);

            boolean printed = job.printPage(Tf);
            if (printed) {
                job.endJob();
            } else {
                System.out.println("Помилка друку");
            }
        }
    }

    @FXML
    private void fprintText() {
        Printer printer = Printer.getDefaultPrinter();

        PrinterJob job = PrinterJob.createPrinterJob(printer);

        boolean printed = job.printPage(Tf);
        if (printed) {
            job.endJob();
        } else {
            System.out.println("Помилка друку");
        }

    }
    @FXML
    private void showAboutWindow() {
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About");

        Label aboutLabel = new Label("Текстовий редактор. Виконав Студент Кі1-20-1 Ратушняк Арсен");

        VBox vbox = new VBox(aboutLabel);
        Scene scene = new Scene(vbox, 300, 200);

        aboutStage.setScene(scene);

        aboutStage.initModality(Modality.APPLICATION_MODAL);

        aboutStage.showAndWait();
    }
}
