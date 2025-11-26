package pokerDecks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DecksStubTest {

    @Test
    public void testConstructedCardsMatchFacesAndSuit() {
        // cover all faces 1..13 so we exercise every switch case in Face.fromValue
        ArrayList<Integer> faces = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13));
        DecksStub ds = new DecksStub(faces);

        assertEquals("♥A", ds.draw().toString());
        assertEquals("♥2", ds.draw().toString());
        assertEquals("♥3", ds.draw().toString());
        assertEquals("♥4", ds.draw().toString());
        assertEquals("♥5", ds.draw().toString());
        assertEquals("♥6", ds.draw().toString());
        assertEquals("♥7", ds.draw().toString());
        assertEquals("♥8", ds.draw().toString());
        assertEquals("♥9", ds.draw().toString());
        assertEquals("♥10", ds.draw().toString());
        assertEquals("♥J", ds.draw().toString());
        assertEquals("♥Q", ds.draw().toString());
        assertEquals("♥K", ds.draw().toString());
    }

    @Test
    public void testOrderPreservedAndExhaustionThrows() {
        ArrayList<Integer> faces = new ArrayList<>(Arrays.asList(2, 3));
        DecksStub ds = new DecksStub(faces);

        assertEquals("♥2", ds.draw().toString());
        assertEquals("♥3", ds.draw().toString());
        assertThrows(IndexOutOfBoundsException.class, () -> ds.draw());
    }

    @Test
    public void testInvalidFaceThrows() {
        ArrayList<Integer> faces = new ArrayList<>(Arrays.asList(0));
        assertThrows(IllegalArgumentException.class, () -> new DecksStub(faces));
    }

    @Test
    public void testNullFaceThrows() {
        ArrayList<Integer> faces = new ArrayList<>();
        faces.add(null);
        Throwable t = assertThrows(Throwable.class, () -> new DecksStub(faces));
        // Some implementations may throw NullPointerException, others IllegalArgumentException.
        assertTrue(t instanceof IllegalArgumentException || t instanceof NullPointerException,
                "Expected IllegalArgumentException or NullPointerException but was " + t.getClass().getName());
    }

    @Test
    public void testEmptyDeckHasNoCards() {
        ArrayList<Integer> faces = new ArrayList<>();
        DecksStub ds = new DecksStub(faces);
        assertThrows(IndexOutOfBoundsException.class, () -> ds.draw());
    }

}