package org.ryuu.gdx.encrypt.file;

import com.badlogic.gdx.files.FileHandle;

import static org.ryuu.gdx.encrypt.setting.EncryptSettings.isEncrypt;
import static org.ryuu.gdx.encrypt.Encryptors.getEncryptor;

public class ChildNameEncryptFileHandle extends FileHandle {
    private final FileHandle fileHandle;

    public ChildNameEncryptFileHandle(FileHandle fileHandle) {
        this.fileHandle = fileHandle;
    }

    @Override
    public FileHandle child(String name) {
        if (isEncrypt()) {
            return new EncryptFileHandle(fileHandle.child(getEncryptor().encrypt(name)));
        } else {
            return fileHandle.child(name);
        }
    }
}