import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ToggleGroup Group;

    @FXML
    private Button buttonAdd;

    @FXML
    private RadioButton buttonAll;

    @FXML
    private Button buttonClear;

    @FXML
    private RadioButton buttonCompleted;

    @FXML
    private RadioButton buttonIncompleted;

    @FXML
    private MenuItem buttonLoad;

    @FXML
    private Button buttonRemove;

    @FXML
    private MenuItem buttonSave;

    @FXML
    private MenuItem helpGuide;

    @FXML
    private TableColumn<?, ?> isCompleted;

    @FXML
    private TableView<ToDoListData> itemContainer;

    @FXML
    private TableColumn<?, ?> itemDate;

    @FXML
    private DatePicker itemDateBox;

    @FXML
    private TableColumn<?, ?> itemDescription;

    @FXML
    private TextField itemDescriptionBox;

    @FXML
    void addItem(ActionEvent event) {
        String description = itemDescriptionBox.getText();
        LocalDate date = itemDateBox.getValue();


    }

    @FXML
    void clearList(ActionEvent event) {

    }

    @FXML
    void loadList(ActionEvent event) {

    }

    @FXML
    void openHelp(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {
        TableView<ToDoListData> table = new TableView<>();

        buttonRemove.setOnAction(e -> {
            ToDoListData selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);
        });
    }

    @FXML
    void saveList(ActionEvent event) {

    }

    @FXML
    void showAllItems(ActionEvent event) {

    }

    @FXML
    void showCompleted(ActionEvent event) {

    }

    @FXML
    void showIncompleted(ActionEvent event) {

    }

    
}
