package org.ryuu.gdx.encrypt.util;

import static org.ryuu.gdx.encrypt.Encryptors.*;

public class Paths {
    private Paths() {
    }

    public static String getCipherPath(String path) {
        if (!path.contains(".")) {
            return path;
        }
        int lastIndexOfSlash = path.lastIndexOf("/");
        if (lastIndexOfSlash == -1) {
            return "";
        }
        String parent = path.substring(0, lastIndexOfSlash + 1);
        String name = path.substring(lastIndexOfSlash + 1);
        return parent + getEncryptor().encrypt(name);
    }

    public static String getPlainPath(String path) {
        if (path.contains(".")) {
            return path;
        }
        int lastIndexOfSlash = path.lastIndexOf("/");
        if (lastIndexOfSlash == -1) {
            return "";
        }
        String parent = path.substring(0, lastIndexOfSlash + 1);
        String name = path.substring(lastIndexOfSlash + 1);
        return parent + getEncryptor().decrypt(name);
    }
}