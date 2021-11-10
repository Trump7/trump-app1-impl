import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {

    @Test
    public void getNameTest(){
        ToDoListData temp = new ToDoListData("exampleName", "exampleDescription", "2021-01-11", true);

        assertEquals("exampleName", temp.getName());
    }

    @Test
    public void getDescTest(){
        ToDoListData temp = new ToDoListData("exampleName", "exampleDescription", "2021-01-11", true);

        assertEquals("exampleDescription", temp.getDescription());
    }

    @Test
    public void getDateTest(){
        ToDoListData temp = new ToDoListData("exampleName", "exampleDescription", "2021-01-11", true);

        assertEquals("2021-01-11", temp.getDate());
    }

    @Test
    public void getCompletedTest(){
        ToDoListData temp = new ToDoListData("exampleName", "exampleDescription", "2021-01-11", true);

        assertEquals(true, temp.getCompleted());
    }
}
