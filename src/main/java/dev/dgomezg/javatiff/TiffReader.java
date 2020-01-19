package dev.dgomezg.javatiff;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;

public class TiffReader {

    private static final int TIFF_HEADER_SIZE_BYTES = 8;
    /* Little Endian Byte order (referred as II or IBM/PC order in Tiff Specs or digital imaging SW)*/
    public static final short TIFF_BYTE_ORDER_LE = 0x4949;
    /* Little Endian Byte order (referred as MM or Mac order in Tiff Specs or digital imaging SW)*/
    public static final short TIFF_BYTE_ORDER_BE = 0x4D4D;

    private TiffHeader header;

    public void open(String testFilePath) throws IOException {
        var byteChannel = Files.newByteChannel(Path.of(testFilePath));
        var byteBuffer = ByteBuffer.allocate(TIFF_HEADER_SIZE_BYTES);

        var bytesRead = byteChannel.read(byteBuffer);
        byteBuffer.rewind();
        assert bytesRead == TIFF_HEADER_SIZE_BYTES;

        var byteOrder = byteBuffer.getShort();
        if (byteOrder == 0x4949) {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        }

        //header = new TiffHeader(byteOrder, byteBuffer.getShort(), byteBuffer.getInt());
        header = TiffHeader.from(byteOrder, byteBuffer);
    }

    public TiffHeader getHeader() {
        return header;
    }

    private void readUsingInputStreams(File file) throws IOException {
        System.out.println("Reading using InputStreams");
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            for (int i=0; i<=4; i++) {
                int read = bis.read();
                System.out.printf("%02X - %d\n", read, read);
            }
            System.out.println("----");
        }
    }
}
