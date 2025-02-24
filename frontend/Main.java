import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> searchEngineComboBox = new ComboBox<>();
        searchEngineComboBox.getItems().addAll("google", "bing", "yandex", "duckduckgo", "yahoo", "brave");

        TextField queryTextField = new TextField();
        queryTextField.setPromptText("Enter search query");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            String engine = searchEngineComboBox.getValue();
            String query = queryTextField.getText();
            String result = runSearch(engine, query);
            System.out.println(result);
        });

        VBox layout = new VBox(10, searchEngineComboBox, queryTextField, searchButton);
        Scene scene = new Scene(layout, 300, 200);

        primaryStage.setTitle("Multi-Search Browser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String runSearch(String engine, String query) {
        try {
            ProcessBuilder builder = new ProcessBuilder("dotnet", "run", engine, query);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return result.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error running search";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
