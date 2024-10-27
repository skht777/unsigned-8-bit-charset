module io.github.skht.charset {
    requires java.management;
    exports io.github.skht.charset;
    provides java.nio.charset.spi.CharsetProvider with io.github.skht.charset.Unsigned8BitCharsetProvider;
}