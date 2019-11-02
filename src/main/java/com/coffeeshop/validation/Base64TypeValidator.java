package com.coffeeshop.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tika.Tika;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Base64;

public class Base64TypeValidator implements ConstraintValidator<Base64Type, String> {

    @Override
    public void initialize(Base64Type type) {}

    public Base64TypeValidator() {}

    @Override
    public boolean isValid(String base64Type, ConstraintValidatorContext constraintValidatorContext) {
        if (base64Type == null) {
            return true;
        }
        if (base64Type.isEmpty()) {
            return false;
        }
        return isSupportedImageType(base64Type);
    }

    private boolean isSupportedImageType(String base64Type) {
        byte[] imageByteArray = Base64.getDecoder().decode(base64Type);
        String contentType = new Tika().detect(imageByteArray);

        return ImageType.getByType(contentType) != null;
    }

    @AllArgsConstructor
    @Getter
    enum ImageType {
        JPEG("jpeg"),
        JGP("jpg"),
        JFIF("jfif");

        private String type;

        public static ImageType getByType(String inputType) {
            if (inputType == null) {
                return null;
            }

            return Arrays.stream(values())
                    .filter(x -> inputType.toLowerCase().contains(x.getType()))
                    .findFirst()
                    .orElse(null);
        }

    }

}


