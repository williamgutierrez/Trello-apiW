package trello.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrelloApiBoardTest {
    private Board board;


    @Before
    public void init(){
        Board newboard = new Board();
        newboard.setName("Example Board");
        newboard.setDesc("Something to test");
    //  board = RequestManager.post("/1/boards","{\"name\":\"Example Board\"}").then().log().all().extract().as(Board.class);
      board = RequestManager.post("/1/boards",newboard)
              .then()
              .log().all()
              .extract().as(Board.class);

    }
// metodo Get
    @Test
    public void testBoardGetWithSerialization(){
       Board currentBoard = RequestManager.get("/1/boards/"+board.getId()).as(Board.class);
       assertEquals("Example Board", currentBoard.getName() );
       assertEquals("Something to test",currentBoard.getDesc());
    }
//metodo Delete
    //deseralizacion y serializacion
    @Test
    public void testBoardDeleteWithSerialization(){
  RequestManager.delete("/1/boards/"+board.getId()).then().statusCode(200);

    }



}
