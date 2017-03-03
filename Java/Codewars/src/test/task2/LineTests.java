package test.task2;

import main.task2.Line;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LineTests {

    @Test
    public void test1() {
        assertEquals("YES", Line.Tickets(new int[]{25, 25, 50}));
    }

    @Test
    public void test2() {
        assertEquals("NO", Line.Tickets(new int []{25, 100}));
    }

    @Test
    public void test3() {
        assertEquals("NO", Line.Tickets(new int []{25, 50, 50}));
    }

    @Test
    public void test4() {
        assertEquals("YES", Line.Tickets(new int []{25, 50, 25, 25, 100}));
    }

    @Test
    public void test5() {
        assertEquals("YES", Line.Tickets(new int []{25, 50, 25, 25, 100, 50}));
    }

    @Test
    public void test6() {
        assertEquals("NO", Line.Tickets(new int[]{50}));
    }

}