import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TodoListApplication extends Application {

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RealOne.fxml")));

        Scene scene = new Scene(root);
        //Uncomment line when style sheet is made
        //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        //Table
//        TableView<ToDoListData> tab = new TableView<>();
//
//        TableColumn<ToDoListData, Boolean> isCompleted = new TableColumn<>("Completed");
//        isCompleted.setCellValueFactory(new PropertyValueFactory<>("Completed"));
//        TableColumn<ToDoListData, String> itemDescription = new TableColumn<>("Description");
//        itemDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
//        TableColumn<ToDoListData, String> itemDateBox = new TableColumn<>("Due Date");
//        itemDateBox.setCellValueFactory(new PropertyValueFactory<>("Due Date"));
//
//        tab.getColumns().addAll(isCompleted, itemDescription, itemDateBox);


        stage.setResizable(false);
        stage.setTitle("ToDo Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
