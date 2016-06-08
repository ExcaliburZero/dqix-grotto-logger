package dqixgrottologger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
}
