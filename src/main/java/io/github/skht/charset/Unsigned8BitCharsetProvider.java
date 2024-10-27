package io.github.skht.charset;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Unsigned8BitCharsetProvider extends CharsetProvider {
    private static final Map<String, Charset> charsets = new HashMap<>();

    static {
        // Register the Unsigned8BitCharset with its canonical name
        Charset charset = new Unsigned8BitCharset("Unsigned8Bit", new String[]{});
        charsets.put(charset.name(), charset);
    }

    @Override
    public Iterator<Charset> charsets() {
        // Return an iterator over the registered charsets
        return Collections.unmodifiableCollection(charsets.values()).iterator();
    }

    @Override
    public Charset charsetForName(String charsetName) {
        // Look up the charset by name
        return charsets.get(charsetName);
    }
}
