import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFile {

    public void save(ObservableList<ToDoListData> mainList){
        //Creating a file chooser to make it easier for the user to save
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            //calls the save to txt file
            saveTextToFile(mainList, file);
        }
    }

    public void saveTextToFile(ObservableList<ToDoListData> list, File file){
        try{
            PrintWriter writer;
            writer = new PrintWriter(file);

            //Goes through every item in the todolist and adds commas in between each item
            for (ToDoListData todo : list) {
                writer.write(todo.getName() + "," + todo.getDescription() +
                        "," + todo.getDate() + "," + todo.getCompleted() + "\n");
            }
            //the above for loop adds all items line by line to a text file
            writer.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
