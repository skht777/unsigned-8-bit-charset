package io.github.skht.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public class Unsigned8BitCharset extends Charset {

    // Constructor for the Unsigned8BitCharset
    protected Unsigned8BitCharset(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
    }

    // Required method to create an encoder instance for this charset
    @Override
    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }

    // Required method to create a decoder instance for this charset
    @Override
    public CharsetDecoder newDecoder() {
        return new Decoder(this);
    }

    // Determines if this charset contains another charset
    @Override
    public boolean contains(Charset cs) {
        // This charset only supports simple 8-bit mappings, so it will only contain itself
        return (cs instanceof Unsigned8BitCharset);
    }

    // Encoder class that converts characters to bytes
    private static class Encoder extends CharsetEncoder {

        protected Encoder(Charset cs) {
            super(cs, 1.0f, 1.0f); // Sets average and maximum bytes per character to 1
        }

        @Override
        protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
            // Loop over input characters
            while (in.hasRemaining()) {
                char inputChar = in.get();
                // Ensure the character is within the 8-bit range
                if (inputChar <= 0xFF) {
                    if (!out.hasRemaining()) {
                        return CoderResult.OVERFLOW; // Not enough space in the output buffer
                    }
                    // Ensure the character is within the 7-bit range
                    if (inputChar < 0x80) {
                        out.put((byte) inputChar);
                    } else {
                        out.put((byte) 0x2e);
                    }
                } else {
                    return CoderResult.unmappableForLength(1); // Character is not mappable
                }
            }
            return CoderResult.UNDERFLOW; // All input characters have been processed
        }
    }

    // Decoder class that converts bytes to characters
    private static class Decoder extends CharsetDecoder {

        protected Decoder(Charset cs) {
            super(cs, 1.0f, 1.0f); // Sets average and maximum characters per byte to 1
        }

        @Override
        protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
            // Loop over input bytes
            while (in.hasRemaining()) {
                byte inputByte = in.get();
                if (!out.hasRemaining()) {
                    return CoderResult.OVERFLOW; // Not enough space in the output buffer
                }
                out.put((char) (inputByte & 0xFF)); // Mask to treat byte as unsigned and convert to char
            }
            return CoderResult.UNDERFLOW; // All input bytes have been processed
        }
    }
}
