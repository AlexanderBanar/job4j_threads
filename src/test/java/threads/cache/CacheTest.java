package threads.cache;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CacheTest {
    @Test
    public void whenAdd1Element() {
        Base base = new Base(1, 1);
        Cache cache = new Cache();
        assertThat(cache.add(base), is(true));
    }

    @Test
    public void whenAdd3ElAndTrueAnd1TheSameElAndFalse() {
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        Base base3 = new Base(3, 1);
        Cache cache = new Cache();
        assertThat(cache.add(base1), is(true));
        assertThat(cache.add(base2), is(true));
        assertThat(cache.add(base3), is(true));
        assertThat(cache.add(base1), is(false));
    }

    @Test
    public void whenUpdateWithSameVersionAndTrue() {
        Base base1 = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(base1);
        assertThat(cache.update(base1), is(true));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateWithDiffVersionAndException() {
        Base base1 = new Base(1, 2);
        Base base1WithVer1 = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(base1);
        cache.update(base1WithVer1);
    }
}