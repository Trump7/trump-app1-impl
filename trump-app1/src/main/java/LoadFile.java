import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadFile {

    public ObservableList<ToDoListData> load(ObservableList<ToDoListData> mainList) throws IOException {
        //Temporary observable list to pass to the controller
        ObservableList<ToDoListData> temp = null;
        //making a file chooser to make it easier for the user to open a file
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            temp = loadTextFromFile(mainList, file);
        }
        return temp;
    }

    public ObservableList<ToDoListData> loadTextFromFile(ObservableList<ToDoListData> list, File file) throws IOException {
            FileReader temp = new FileReader(file);
            BufferedReader reader = new BufferedReader(temp);
            String line;

            //While there is still a line present, split the line up into four strings and add it to the
            //temporary observable list
            while((line = reader.readLine()) != null){
                String[] info = line.split(",");
                list.add(new ToDoListData(info[0], info[1], info[2], Boolean.parseBoolean(info[3])));
            }
            reader.close();

        return list;
    }
}
