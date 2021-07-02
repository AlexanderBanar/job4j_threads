package threads.sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IndexSearchTest {
    @Test
    public void whenObjectIsFourthElementInArray() {
        Integer[] array = {1, 2, 5, 8, 10, 22, 16, 18, 7, 4, 55, 56, 58, 59};
        Integer forSearch = 10;
        Integer index = IndexSearch.search(array, forSearch);
        assertThat(index, is(4));
    }

    @Test
    public void whenObjectIsMissingInArray() {
        Integer[] array = {1, 2, 5, 8, 10, 22, 16, 18, 7, 4, 55, 56, 58, 59};
        Integer forSearch = 77;
        Integer index = IndexSearch.search(array, forSearch);
        assertThat(index, is(-1));
    }
}