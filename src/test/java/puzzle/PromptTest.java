package puzzle;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromptTest {

    @Test
    public void testStart() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Prompt.start();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Welcome to the GIC geometry puzzle app\n");
        stringBuilder.append("[1] Create a custom shape\n");
        stringBuilder.append("[2] Generate a random shape\n");
        stringBuilder.append("Thank you for playing the GIC geometry puzzle app\n");
        stringBuilder.append("Have a nice day!\n");
        String mainOutput = stringBuilder.toString();

        assertEquals(mainOutput, outContent.toString());

        System.setOut(System.out);
    }

}
