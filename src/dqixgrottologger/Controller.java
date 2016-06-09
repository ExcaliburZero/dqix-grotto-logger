package dqixgrottologger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private TableView<Grotto> tableView;
    @FXML private ComboBox prefixBox;
    @FXML private ComboBox environmentBox;
    @FXML private ComboBox suffixBox;
    @FXML private TextField levelField;
    @FXML private ComboBox terrainBox;
    @FXML private ComboBox bossBox;
    @FXML private ComboBox areaBox;
    @FXML private TextField floorsField;
    @FXML private TextField monsterLevelField;
    @FXML private TextArea notesArea;
    @FXML private Stage primaryStage;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        /*
         * Setup the values in the ComboBoxes and set their initial values.
         */

        // Prefix
        prefixBox.getItems().setAll(Grotto.getValidPrefixes());
        prefixBox.getItems().add(0, "Any");
        prefixBox.setValue("Any");

        // Environment
        environmentBox.getItems().setAll(Grotto.getValidEnvironments());
        environmentBox.getItems().add(0, "Any");
        environmentBox.setValue("Any");

        // Suffix
        suffixBox.getItems().setAll(Grotto.getValidSuffixes());
        suffixBox.getItems().add(0, "Any");
        suffixBox.setValue("Any");

        // Terrain
        terrainBox.getItems().setAll(Grotto.getValidTerrains());
        terrainBox.getItems().add(0, "Any");
        terrainBox.setValue("Any");

        // Boss
        bossBox.getItems().setAll(Grotto.getValidBosses());
        bossBox.getItems().add(0, "Any");
        bossBox.setValue("Any");

        // Area
        areaBox.getItems().setAll(Grotto.getValidAreas());
        areaBox.getItems().add(0, "Any");
        areaBox.setValue("Any");
    }

    @FXML
    private void addGrotto(ActionEvent event) {
        ObservableList<Grotto> data = tableView.getItems();

        Grotto newGrotto = new Grotto(
                (String) this.prefixBox.getValue(),
                (String) this.environmentBox.getValue(),
                (String) this.suffixBox.getValue(),
                this.levelField.getText(),
                (String) this.terrainBox.getValue(),
                (String) this.bossBox.getValue(),
                (String) this.areaBox.getValue(),
                this.floorsField.getText(),
                this.monsterLevelField.getText(),
                this.notesArea.getText()
        );

        ArrayList<String> invalidFields = newGrotto.getInvalidFields();

        if (invalidFields.isEmpty()) {
            data.add(newGrotto);
        } else {
            System.err.println("Invalid grotto field(s): " + invalidFields);
        }
    }

    @FXML
    private void clearGrottoes(ActionEvent event) {
        ObservableList<Grotto> data = tableView.getItems();
        data.setAll(new ArrayList<Grotto>());
    }

    @FXML
    private void deleteGrotto(ActionEvent event) {
        ObservableList<Grotto> data = tableView.getItems();
        Grotto selectedGrotto = tableView.getSelectionModel().getSelectedItem();
        data.remove(selectedGrotto);
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

    //public static boolean validatePrefix() {
    //}
}
