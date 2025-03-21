package sh.miles.crdatalib.parsing.deserialize;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import sh.miles.crdatalib.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ParsingTest<T> {

    @Test
    public void testShouldDeserializeWithoutError() {
        final JsonMapper mapper = new JsonMapper();
        final SimpleModule module = new SimpleModule();
        register(module);
        mapper.registerModule(module);

        final T[] expected = getExpected();
        final String[] resources = getResourceLocation();
        final Object[] actual = new Object[expected.length];
        for (int i = 0; i < expected.length; i++) {
            final int finalI = i;
            actual[i] = assertDoesNotThrow(() -> TestUtil.jsonResource(resources[finalI], (Class<T>) expected[finalI].getClass(), mapper));
            assertEquals(expected[i], actual[i]);
        }
    }

    public abstract void register(SimpleModule module);

    public abstract T[] getExpected();

    public abstract String[] getResourceLocation();
}
