import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initializing all columns in the tableview
        isCompleted.setCellValueFactory(new PropertyValueFactory<>("Completed"));
        itemDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        itemDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        //initializing the list to display in tableview
        itemContainer.setItems(list);
    }

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

    //Creating list that can be modified for the table
    ObservableList<ToDoListData> list = FXCollections.observableArrayList();

    @FXML
    void addItem(ActionEvent event) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        //Getting values from the text boxes for the add button
        String description = itemDescriptionBox.getText();
        String name = itemNameBox.getText();
        String date;

        //This if else block allows for a custom date or no date at all
        if(itemDateBox.getValue() != null){
            date = itemDateBox.getValue().toString();
        }
        else{
            date = "";
        }

        //If either the name or description is not filled in, it will return with an error
        //and not add the item to the list
        if(name.equals("") || description.equals("")){
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Both description and name must be filled out.");
            errorAlert.showAndWait();
        }
        else{
            //It will only add the item if the length of description is less than 256 characters
            if(description.length() <= 256){
                list.add(new ToDoListData(name, description, date, false));
                itemContainer.setItems(list);

                //clearing out the text boxes so a user can enter another item
                itemNameBox.clear();
                itemDescriptionBox.clear();
                itemDateBox.setValue(null);
            }
            else{
                //if the length of description is more than 256 chars, it will give an error
                //and clear only the description box
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Description is above the max character limit.");
                errorAlert.showAndWait();
                itemDescriptionBox.clear();
            }
        }
    }

    @FXML
    void removeItem(ActionEvent event) {
        ToDoListData selectedItem = itemContainer.getSelectionModel().getSelectedItem();
        //making sure the user actually selected an item
        if(selectedItem != null){
            //prompt the user for confirmation while showing the details of the item
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Needed");
            alert.setHeaderText("Are you sure you would like to delete the item:");
            alert.setContentText("Name: " + selectedItem.getName() + "\nDescription: "
                    + selectedItem.getDescription() + "\nDate: " + selectedItem.getDate());

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                //if they click okay, it will remove the item from the list and
                //refresh the tableview
                list.remove(selectedItem);
                itemContainer.refresh();
            }
        }
    }

    @FXML
    void editItem(ActionEvent event){
        ToDoListData selectedItem = itemContainer.getSelectionModel().getSelectedItem();
        //making sure the user selected an item
        if(selectedItem != null){
            String selectedName = selectedItem.getName();
            String selectedDesc = selectedItem.getDescription();
            String selectedDate = selectedItem.getDate();

            // Create the custom dialog to edit the selected item
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Edit Item");
            dialog.setHeaderText("Please type new values for item:");

            // Adding buttons to the dialog
            ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

            //setting up a grid pane to make it look nicer
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));

            //adding the text boxes and setting the box text to the selected items contents
            TextField name = new TextField();
            name.setText(selectedName);
            TextField desc = new TextField();
            desc.setText(selectedDesc);
            DatePicker date = new DatePicker();
            date.setPromptText(selectedDate);

            //Adding labels for each of the text fields
            gridPane.add(new Label("Name: "), 0, 0);
            gridPane.add(name, 1, 0);
            gridPane.add(new Label("Description: "), 0, 1);
            gridPane.add(desc, 1, 1);
            gridPane.add(new Label("Date: "), 0, 2);
            gridPane.add(date, 1, 2);

            dialog.getDialogPane().setContent(gridPane);

            Optional<ButtonType> result = dialog.showAndWait();
            //if any of the text boxes are filled, replace the items corresponding subitems with the new name
            if(!name.getText().equals("")) {
                selectedItem.setName(name.getText());
            }
            if(!desc.getText().equals("")){
                selectedItem.setDescription(desc.getText());
            }
            if(date.getValue() != null){
                selectedItem.setDate(date.getValue().toString());
            }

            //If the okay button is pushed, refresh the tableview
            if(result.isPresent()){
                itemContainer.refresh();
            }
        }
    }

    @FXML
    void clearList(ActionEvent event) {
        //Ask the user for confirmation before clearing list
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Needed");
        alert.setHeaderText("Are you sure you would like to clear the list?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK && itemContainer.getItems() != null){
            //if the user clicked okay, it will clear the observable list and refresh the tableview
            list.clear();
            itemContainer.refresh();
        }
    }

    @FXML
    void loadList(ActionEvent event) throws IOException {
        LoadFile temp = new LoadFile();
        //Creating a temporary observable list to hold the new lists' data
        ObservableList<ToDoListData> loadedData = FXCollections.observableArrayList();

        loadedData = temp.load(loadedData);

        //if the new list is not empty, it will clear the current list
        //set the current list to the passed observable list and set the tableview to the new list
        if(loadedData != null){
            list.clear();
            list = loadedData;
            itemContainer.setItems(list);
        }
    }

    @FXML
    void saveList(ActionEvent event) {
        //pass the current observable list to the savefile class to handle saving the file
        SaveFile temp = new SaveFile();
        temp.save(list);
    }

    @FXML
    void openHelp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Help.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Help Guide");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void showAllItems(ActionEvent event) {
        //enabled by default to show all items
        FilteredList<ToDoListData> items = new FilteredList<>(list);
        itemContainer.setItems(items);
    }

    @FXML
    void showCompleted(ActionEvent event) {
        //creating a filtered list to store only completed items
        FilteredList<ToDoListData> items = new FilteredList<>(list, p -> true);

        //adding a predicate to only show items whose completed status is true
        items.setPredicate(ToDoListData -> ToDoListData.getCompleted().equals(true));
        //refreshing the tableview with the new list of filtered items
        itemContainer.setItems(items);
    }

    @FXML
    void showIncompleted(ActionEvent event) {
        //creaing a filtered list to store the incompleted items
        FilteredList<ToDoListData> items = new FilteredList<>(list, p -> true);

        //adding a predicate to only show items whose completed status is false
        items.setPredicate(ToDoListData -> ToDoListData.getCompleted().equals(false));
        //refreshing the tableview with the new list of incompleted items
        itemContainer.setItems(items);
    }

    @FXML
    void editCompleted(ActionEvent event){
        ToDoListData selectedItem = itemContainer.getSelectionModel().getSelectedItem();
            //if else statement that changes the value of completed for an item
            //if its true, the new value is false and same visa versa
            if(selectedItem.getCompleted() != null && selectedItem.getCompleted()){
                selectedItem.setCompleted(false);
            }
            else if(selectedItem.getCompleted() != null && !selectedItem.getCompleted()){
                selectedItem.setCompleted(true);
            }
        //refreshing the tableview to show updated items
        itemContainer.refresh();
    }
}
