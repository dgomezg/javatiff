package dev.dgomezg.javatiff;

import java.nio.ByteBuffer;
import java.util.Objects;


public record TiffHeader(short byteOrder, short magicNumber, int offset) {
    public static final short TIFF_MAGIC_NUMBER = 42;

    public static TiffHeader from(short byteOrder, ByteBuffer buffer) {
        return new TiffHeader(byteOrder, buffer.getShort(), buffer.getInt());
    }

}

/*
 * The java 14 record above is 'equivalent' to the class below
public class TiffHeader {

    public static final short TIFF_MAGIC_NUMBER = 42;

    private final short byteOrder;
    private final short magicNumber;
    private final int offset;

    public static TiffHeader from(short byteOrder, ByteBuffer buffer) {
        return new TiffHeader(byteOrder, buffer.getShort(), buffer.getInt());
    }

    public TiffHeader(short byteOrder, short magicNumber, int offset) {
        assert magicNumber == TIFF_MAGIC_NUMBER;
        this.byteOrder = byteOrder;
        this.magicNumber = magicNumber;
        this.offset = offset;
    }

    public short byteOrder() {
        return byteOrder;
    }

    public short magicNumber() {
        return magicNumber;
    }

    public int offset() {
        return offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TiffHeader that = (TiffHeader) o;
        return byteOrder == that.byteOrder &&
                magicNumber == that.magicNumber &&
                offset == that.offset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(byteOrder, magicNumber, offset);
    }

    @Override
    public String toString() {
        return "TiffHeader{" +
                "byteOrder=" + byteOrder +
                ", magicNumber=" + magicNumber +
                ", offset=" + offset +
                '}';
    }
}
*/

