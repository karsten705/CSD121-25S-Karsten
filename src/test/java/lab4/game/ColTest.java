package lab4.game;

import static org.junit.jupiter.api.Assertions.*;

public class ColTest {

    @org.junit.jupiter.api.Test
    public void testValidInputs(){
        assertEquals(Col.Right, Col.from("r"));
        assertEquals(Col.Right, Col.from("3"));

        assertEquals(Col.Middle, Col.from("m"));
        assertEquals(Col.Middle, Col.from("c"));
        assertEquals(Col.Middle, Col.from("2"));

        assertEquals(Col.Left, Col.from("l"));
        assertEquals(Col.Left, Col.from("1"));

    }

    @org.junit.jupiter.api.Test
    public void testInputCase(){

        assertEquals(Col.Right, Col.from("R"));
        assertEquals(Col.Middle, Col.from("M"));
        assertEquals(Col.Middle, Col.from("C"));
        assertEquals(Col.Left, Col.from("L"));

    }

    @org.junit.jupiter.api.Test
    public void testException(){

        assertThrows(NullPointerException.class, () -> Col.from(null));
        assertThrows(IllegalArgumentException.class, () -> Col.from("0"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("4"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("q"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("xx"));
        assertThrows(IllegalArgumentException.class, () -> Col.from(" "));
        assertThrows(IllegalArgumentException.class, () -> Col.from("left"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("middle"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("right"));
    }
}