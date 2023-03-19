package org.ryuu.gdx.encrypt.setting;

import lombok.Setter;

public class EncryptSettings {
    @Setter
    private static boolean isEncrypt = true;

    private EncryptSettings() {
    }

    public static boolean isEncrypt() {
        return isEncrypt;
    }
}