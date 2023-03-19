package org.ryuu.gdx.encrypt.io;

import org.ryuu.gdx.encrypt.file.EncryptFileHandle;

import java.io.InputStream;

public class EncryptInputStream extends InputStream {
    private int index = 0;
    private final byte[] bytes;

    public EncryptInputStream(EncryptFileHandle encryptFileHandle) {
        bytes = encryptFileHandle.readBytes();
    }

    @Override
    public int read() {
        if (index == bytes.length) {
            return -1;
        }
        int res = bytes[index] & 0xff;
        index++;
        return res;
    }
}