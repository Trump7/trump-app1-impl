import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class ToDoListData {
    private String description;
    private String date;
    private Boolean completed;

    public ToDoListData(String description, String date, Boolean completed) {
        this.setDescription(description);
        this.setCompleted(completed);
        this.setDate(date);
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Boolean getCompleted(){
        return completed;
    }

    public void setCompleted(Boolean completed){
        this.completed = completed;
    }
}
