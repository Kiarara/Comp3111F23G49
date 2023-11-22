package Function_B;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class Main_BTest {
    @Test
    public static void main(String[] args){
        try {
            Main_B.main(new String[]{});
        } catch (IOException e) {
            // Handle or log the exception if necessary
        }
    }
}