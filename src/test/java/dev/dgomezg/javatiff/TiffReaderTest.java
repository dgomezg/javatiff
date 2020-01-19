package dev.dgomezg.javatiff;



import org.junit.Test;

import java.io.IOException;

import static dev.dgomezg.javatiff.TiffHeader.TIFF_MAGIC_NUMBER;
import static dev.dgomezg.javatiff.TiffReader.TIFF_BYTE_ORDER_BE;
import static dev.dgomezg.javatiff.TiffReader.TIFF_BYTE_ORDER_LE;
import static org.junit.Assert.assertEquals;

public class TiffReaderTest {


    private static final String TIFF_IIORDER_1PX_WHITE_FILENAME = "1px-white-IIorder.tif";
    private static final String TIFF_MMORDER_1PX_WHITE_FILENAME = "1px-white-MMorder.tif";
    private static final String IMG_TEST_FOLDER = "imgs/";

    private TiffReader sut = new TiffReader();

    @Test
    public void shouldParseTiffHeadersUsingTheRightByteOrder() throws IOException {
        sut.open(getTestFilePath(TIFF_IIORDER_1PX_WHITE_FILENAME));
        assertEquals(TIFF_BYTE_ORDER_LE, sut.getHeader().byteOrder());
        assertEquals(TIFF_MAGIC_NUMBER, sut.getHeader().magicNumber());
        assertEquals(8, sut.getHeader().offset());

        sut.open(getTestFilePath(TIFF_MMORDER_1PX_WHITE_FILENAME));
        assertEquals(TIFF_BYTE_ORDER_BE, sut.getHeader().byteOrder());
        assertEquals(TIFF_MAGIC_NUMBER, sut.getHeader().magicNumber());
        assertEquals(8, sut.getHeader().offset());
    }

    private String getTestFilePath(String filename) {
        return getClass().getClassLoader().getResource(IMG_TEST_FOLDER + filename).getFile();
    }


}
