package dqixgrottologger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Controller {
    @FXML private TableView<Grotto> tableView;
    @FXML private TextField prefixField;
    @FXML private TextField environmentField;
    @FXML private TextField suffixField;
    @FXML private TextField levelField;
    @FXML private TextField terrainField;
    @FXML private TextField bossField;
    @FXML private TextField areaField;
    @FXML private TextField floorsField;
    @FXML private TextField monsterLevelField;
    @FXML private TextArea notesArea;
    @FXML private Stage primaryStage;

    @FXML
    private void addGrotto(ActionEvent event) {
        ObservableList<Grotto> data = tableView.getItems();
        data.add(new Grotto(
                this.prefixField.getText(),
                this.environmentField.getText(),
                this.suffixField.getText(),
                this.levelField.getText(),
                this.terrainField.getText(),
                this.bossField.getText(),
                this.areaField.getText(),
                this.floorsField.getText(),
                this.monsterLevelField.getText(),
                this.notesArea.getText()
        ));
    }

    @FXML
    private void importCSV(ActionEvent event) {
        ObservableList<Grotto> data = tableView.getItems();

        // Get the file location to import grottoes from from the user
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import grottoes list");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fileChooser.showOpenDialog(primaryStage);
        Path csvFile = Paths.get(file.getPath());

        // Try to add the grottoes defined in the file to the table
        try {
            String csvContents = new String(Files.readAllBytes(csvFile));
            ArrayList<Grotto> grottoes = Grotto.fromCSV(csvContents);
            for (Grotto grotto : grottoes) {
                data.add(grotto);
            }
        } catch (IOException ex) {
            System.err.println("Unable to open csv file: " + file.getPath());
        } catch (NullPointerException | IndexOutOfBoundsException ex) {
            System.err.println("Error parsing grotto csv file: " + file.getPath());
            System.err.println(ex);
        }
    }

    @FXML
    private void exportCSV(ActionEvent event) {
        ObservableList<Grotto> data = tableView.getItems();

        // Get the file location to save to from the user
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export grottoes list");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fileChooser.showSaveDialog(primaryStage);

        saveCSV(data, file.getPath());
    }

    /**
     * Saves the given grottoes into a csv file at the given filepath.
     *
     * @param grottoes The grottoes to be recorded into the csv file.
     * @param filePath The filepath of where the csv file will be saved.
     */
    private static void saveCSV(ObservableList<Grotto> grottoes, String filePath) {
        ArrayList<String> csvContents = new ArrayList<>();

        // Add headers
        String csvLine = "";
        csvLine += "Prefix, ";
        csvLine += "Environment, ";
        csvLine += "Suffix, ";
        csvLine += "Level, ";
        csvLine += "Terrain, ";
        csvLine += "Boss, ";
        csvLine += "Area, ";
        csvLine += "Floors, ";
        csvLine += "Monster Level, ";
        csvLine += "Notes";
        csvContents.add(csvLine);

        // Add grotto entries
        for (Grotto grotto : grottoes)  {
            csvContents.add(grotto.toCSV());
        }

        // Write csv contents to file
        Path csvFile = Paths.get(filePath);
        try {
            Files.write(csvFile, csvContents, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            System.err.println("Unable to write to csv file.");
        }

    }
}
