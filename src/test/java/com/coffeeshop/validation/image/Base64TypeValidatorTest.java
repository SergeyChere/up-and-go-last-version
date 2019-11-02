package com.coffeeshop.validation.image;

import com.coffeeshop.validation.Base64TypeValidator;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.util.Base64;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Base64TypeValidatorTest {

    private static final String COFFEE_IMAGE_TEST_PNG = "/image-type/small_png_33KB.png";
    private static final String COFFEE_IMAGE_TEST_GIF = "/image-type/available_size_gif_224KB.gif";
    private static final String COFFEE_IMAGE_TEST_PDF = "/image-type/small_pdf_8KB.pdf";
    private static final String COFFEE_IMAGE_TEST_JPG = "/image-type/small_jpg_8KB.jpg";
    private static final String COFFEE_IMAGE_TEST_ZIP = "/image-type/small_zip_7KB.zip";
    private static final String COFFEE_IMAGE_TEST_JPEG = "/image-type/big_jpeg_416KB.jpeg";

    private Base64TypeValidator validator = new Base64TypeValidator();

    @Test
    public void testValidNullBase64ImageTypePositive() {
        assertTrue(validator.isValid(null,null));
    }

    @Test
    public void testValidEmptyBase64ImageTypeNegative() {
        String imageEmpty = "";
        assertFalse(validator.isValid(imageEmpty,null));
    }

    @Test
    public void testValidBase64ImageTypePositive() {
        String imageJPG = encoder(COFFEE_IMAGE_TEST_JPG);
        String imageJPEG = encoder(COFFEE_IMAGE_TEST_JPEG);
        assertTrue(validator.isValid(imageJPG, null));
        assertTrue(validator.isValid(imageJPEG, null));
    }

    @Test
    public void testValidBase64ImageTypeNegative() {
        String imagePDF = encoder(COFFEE_IMAGE_TEST_PDF);
        String imageGIF = encoder(COFFEE_IMAGE_TEST_GIF);
        String imageZIP = encoder(COFFEE_IMAGE_TEST_ZIP);
        String imagePNG = encoder(COFFEE_IMAGE_TEST_PNG);
        assertFalse(validator.isValid(imagePDF,null));
        assertFalse(validator.isValid(imageGIF,null));
        assertFalse(validator.isValid(imageZIP,null));
        assertFalse(validator.isValid(imagePNG,null));
    }

    @SneakyThrows()
    private String encoder(String imageSrc){
        InputStream inputStream = Base64TypeValidatorTest.class.getResourceAsStream(imageSrc);
        byte[] imageToBytes = IOUtils.toByteArray(inputStream);
        return Base64.getEncoder().encodeToString(imageToBytes);
    }
}
