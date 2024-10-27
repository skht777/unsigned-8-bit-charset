package io.github.skht.charset;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

class Main {
    public static void main(final String[] args) throws IOException {
        Charset charset = Charset.forName("Unsigned8Bit");

        if (charset != null) {
            Writer writer = new OutputStreamWriter(System.out, charset);
            writer.write(0x89);
            writer.write(0x50);
            writer.flush();
        } else {
            System.out.println("Charset not found");
        }
    }
}
