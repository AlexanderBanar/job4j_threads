package ru.job4j.zet;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FourthTest {
    @Test
    public void whenReceivingFourInt() {
        int one = new Fourth().justGivingFourInt();
        assertThat(one, is(4));
    }
}