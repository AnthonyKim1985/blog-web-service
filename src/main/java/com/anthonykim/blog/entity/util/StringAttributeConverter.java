package com.anthonykim.blog.entity.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;

public class StringAttributeConverter implements AttributeConverter<String, String> {
    private static final String ALGORITHM = "AES";
    private static final byte[] SECRET = {98, 108, 111, 103, 45, 119, 101, 98, 45, 115, 101, 114, 118, 105, 99, 101};

    private final Key key;
    private final Cipher cipher;

    public StringAttributeConverter() throws Exception {
        this.key = new SecretKeySpec(SECRET, ALGORITHM);
        this.cipher = Cipher.getInstance(ALGORITHM);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            if (attribute == null || attribute.isEmpty())
                return null;

            this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
            return Base64.getEncoder().encodeToString(this.cipher.doFinal(attribute.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty())
                return null;

            this.cipher.init(Cipher.DECRYPT_MODE, this.key);
            return new String(this.cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }
    }
}
