import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.html.Option;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        isCompleted.setCellValueFactory(new PropertyValueFactory<>("Completed"));
        itemDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        itemDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("Name"));

        itemContainer.setItems(list);
    }

    private final ObservableList<ToDoListData> list = FXCollections.observableArrayList();


    @FXML
    private ToggleGroup Group;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonMark;

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
    private TableColumn<ToDoListData, Boolean> isCompleted;

    @FXML
    private TableView<ToDoListData> itemContainer;

    @FXML
    private TableColumn<ToDoListData, DatePicker> itemDate;

    @FXML
    private TableColumn<ToDoListData, String> itemDescription;

    @FXML
    private TableColumn<ToDoListData, String> itemName;

    @FXML
    private DatePicker itemDateBox;

    @FXML
    private TextField itemDescriptionBox;

    @FXML
    private TextField itemNameBox;


    @FXML
    void addItem(ActionEvent event) {
        String description = itemDescriptionBox.getText();
        String name = itemNameBox.getText();
        String date;
        if(itemDateBox.getValue() != null){
            date = itemDateBox.getValue().toString();
        }
        else{
            date = "";
        }

        list.add(new ToDoListData(name, description, date, false));
        itemContainer.setItems(list);

        itemNameBox.clear();
        itemDescriptionBox.clear();
        itemDateBox.setValue(null);
    }

    @FXML
    void clearList(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Needed");
        alert.setHeaderText("Are you sure you would like to clear the list?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK && itemContainer.getItems() != null){
            itemContainer.getItems().clear();
        }
    }

    @FXML
    void loadList(ActionEvent event) {

    }

    @FXML
    void openHelp(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {
        ToDoListData selectedItem = itemContainer.getSelectionModel().getSelectedItem();

        if(selectedItem != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Needed");
            alert.setHeaderText("Are you sure you would like to delete the item:");
            alert.setContentText("Name: " + selectedItem.getName() + "\nDescription: "
                    + selectedItem.getDescription() + "\nDate: " + selectedItem.getDate());

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                itemContainer.getItems().remove(selectedItem);
            }
        }
    }

    @FXML
    void saveList(ActionEvent event) {

    }

    @FXML
    void showAllItems(ActionEvent event) {
        FilteredList<ToDoListData> items = new FilteredList<>(list);

        itemContainer.setItems(items);
    }

    @FXML
    void showCompleted(ActionEvent event) {
        FilteredList<ToDoListData> items = new FilteredList<>(list, p -> true);

        items.setPredicate(ToDoListData -> {
            if(ToDoListData.getCompleted().equals(true)){
                return true;
            }
            else{
                return false;
            }
        });

        itemContainer.setItems(items);
    }

    @FXML
    void showIncompleted(ActionEvent event) {
        FilteredList<ToDoListData> items = new FilteredList<>(list, p -> true);

        items.setPredicate(ToDoListData -> {
            if(ToDoListData.getCompleted().equals(false)){
                return true;
            }
            else{
                return false;
            }
        });

        itemContainer.setItems(items);
    }

    @FXML
    void editCompleted(ActionEvent event){
        ToDoListData selectedItem = itemContainer.getSelectionModel().getSelectedItem();

            if(selectedItem.getCompleted() != null && selectedItem.getCompleted()){
                selectedItem.setCompleted(false);
            }
            else if(selectedItem.getCompleted() != null && !selectedItem.getCompleted()){
                selectedItem.setCompleted(true);
            }
        itemContainer.refresh();
    }
}
