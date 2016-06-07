package dqixgrottologger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {
    @FXML private TableView<Grotto> tableView;

    @FXML
    private void addGrotto(ActionEvent event) {
        ObservableList<Grotto> data = tableView.getItems();
        data.add(new Grotto(
                "prefix",
                "environment",
                "suffix",
                0, // Level
                "terrain",
                "boss",
                "area",
                0, // Floors
                "monsterLevel",
                "notes"
        ));

    }
}
