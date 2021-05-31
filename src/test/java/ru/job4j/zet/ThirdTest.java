package ru.job4j.zet;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ThirdTest {
    @Test
    public void whenReceivingThreeInt() {
        int one = new Third().justGivingThreeInt();
        assertThat(one, is(3));
    }
}