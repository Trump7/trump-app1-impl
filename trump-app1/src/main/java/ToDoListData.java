public class ToDoListData {
    private String name;
    private String description;
    private String date;
    private Boolean completed;

    public ToDoListData(String name, String description, String date, Boolean completed) {
        this.setName(name);
        this.setDescription(description);
        this.setCompleted(completed);
        this.setDate(date);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
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
