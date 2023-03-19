package org.ryuu.gdx.encrypt;

import lombok.Setter;
import org.ryuu.encrypt.Encryptor;

public class Encryptors {
    @Setter
    private static Encryptor encryptor;

    private Encryptors() {
    }

    public static Encryptor getEncryptor() {
        return encryptor;
    }
}