package org.ryuu.gdx.encrypt.file.util;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.ryuu.gdx.encrypt.file.EncryptFileHandle;

import static org.ryuu.gdx.encrypt.setting.EncryptSettings.*;
import static org.ryuu.gdx.encrypt.util.Paths.*;

public class FileHandles {
    private FileHandles() {
    }

    public static FileHandle getActualFileHandle(String path, FileType fileType) {
        FileHandle fileHandle = Gdx.files.getFileHandle(path, fileType);
        if (fileHandle.exists()) {
            return fileHandle;
        }
        if (isEncrypt()) {
            return new EncryptFileHandle(Gdx.files.getFileHandle(getCipherPath(path), fileType));
        }
        return fileHandle;
    }
}