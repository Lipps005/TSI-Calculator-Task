import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class MyStepdefs extends RoomObject.Builder {

    public RoomObject.Builder testWall;
    private float wallSize = 0.0f;
    private float sizeAfter = 0.0f;
    private float socketSize = 0.0f;

    @Override
    public RoomObject.Builder addSocket(float width, float height) {
            socketSize = width*height;
            this.size -=  socketSize;
            sizeAfter = this.size;
            return this;
    }

    @Given("I HAVE A ROOM OBJECT BUILDER")
    public void iHAVEAROOMOBJECTBUILDER() {
        testWall = new RoomObject.Builder();
        testWall.setSize(2, 4);
        wallSize = testWall.size;


    }

    @When("I ADD A SOCKET")
    public void iADDASOCKET() {
        testWall.addSocket(0.2f, 0.1f);
    }

    @Then("THE SIZE OF THE SOCKET SHOULD BE CALCULATED")
    public void theSIZEOFTHESOCKETSHOULDBECALCULATED() {
        assertEquals(0.2f*0.1f, socketSize);
    }

    @And("THE SOCKET SIZE IS SUBTRACTED FROM THE SIZE OF THE WALL")
    public void theSOCKETSIZEISSUBTRACTEDFROMTHESIZEOFTHEWALL() {
        assertEquals(wallSize-(0.2f*0.1f), sizeAfter);
    }
}
