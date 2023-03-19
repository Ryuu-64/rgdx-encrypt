package org.ryuu.gdx.encrypt.file;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import org.ryuu.encode.Base64;
import org.ryuu.gdx.encrypt.io.EncryptInputStream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

import static org.ryuu.gdx.encrypt.Encryptors.getEncryptor;

public class EncryptFileHandle extends FileHandle {
    private final FileHandle fileHandle;

    public EncryptFileHandle(FileHandle fileHandle) {
        this.fileHandle = fileHandle;
    }

    @Override
    public String path() {
        return fileHandle.path();
    }

    @Override
    public String name() {
        return fileHandle.name();
    }

    @Override
    public String extension() {
        String name = fileHandle.file().getName();
        name = getEncryptor().decrypt(name);
        if (name == null) {
            return "";
        }
        int lastIndexOfDot = name.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";
        }
        return name.substring(lastIndexOfDot + 1);
    }

    @Override
    public String nameWithoutExtension() {
        return fileHandle.nameWithoutExtension();
    }

    @Override
    public String pathWithoutExtension() {
        return fileHandle.pathWithoutExtension();
    }

    @Override
    public Files.FileType type() {
        return fileHandle.type();
    }

    @Override
    public File file() {
        return fileHandle.file();
    }

    @Override
    public InputStream read() {
        return new EncryptInputStream(this);
    }

    @Override
    public BufferedInputStream read(int bufferSize) {
        return fileHandle.read(bufferSize);
    }

    @Override
    public Reader reader() {
        return fileHandle.reader();
    }

    @Override
    public Reader reader(String charset) {
        return fileHandle.reader(charset);
    }

    @Override
    public BufferedReader reader(int bufferSize) {
        return fileHandle.reader(bufferSize);
    }

    @Override
    public BufferedReader reader(int bufferSize, String charset) {
        return fileHandle.reader(bufferSize, charset);
    }

    @Override
    public String readString() {
        return fileHandle.readString();
    }

    @Override
    public String readString(String charset) {
        return getEncryptor().decrypt(fileHandle.readString(charset));
    }

    @Override
    public byte[] readBytes() {
        byte[] decode = Base64.getUrlDecoder().decode(fileHandle.readBytes());
        return Objects.requireNonNull(getEncryptor().decrypt(decode));
    }

    @Override
    public int readBytes(byte[] bytes, int offset, int size) {
        return fileHandle.readBytes(bytes, offset, size);
    }

    @Override
    public ByteBuffer map() {
        return fileHandle.map();
    }

    @Override
    public ByteBuffer map(FileChannel.MapMode mode) {
        return fileHandle.map(mode);
    }

    @Override
    public OutputStream write(boolean append) {
        return fileHandle.write(append);
    }

    @Override
    public OutputStream write(boolean append, int bufferSize) {
        return fileHandle.write(append, bufferSize);
    }

    @Override
    public void write(InputStream input, boolean append) {
        fileHandle.write(input, append);
    }

    @Override
    public Writer writer(boolean append) {
        return fileHandle.writer(append);
    }

    @Override
    public Writer writer(boolean append, String charset) {
        return fileHandle.writer(append, charset);
    }

    @Override
    public void writeString(String string, boolean append) {
        fileHandle.writeString(getEncryptor().encrypt(string), append);
    }

    @Override
    public void writeString(String string, boolean append, String charset) {
        fileHandle.writeString(getEncryptor().encrypt(string), append, charset);
    }

    @Override
    public void writeBytes(byte[] bytes, boolean append) {
        fileHandle.writeBytes(bytes, append);
    }

    @Override
    public void writeBytes(byte[] bytes, int offset, int length, boolean append) {
        fileHandle.writeBytes(bytes, offset, length, append);
    }

    @Override
    public FileHandle[] list() {
        return fileHandle.list();
    }

    @Override
    public FileHandle[] list(FileFilter filter) {
        return fileHandle.list(filter);
    }

    @Override
    public FileHandle[] list(FilenameFilter filter) {
        return fileHandle.list(filter);
    }

    @Override
    public FileHandle[] list(String suffix) {
        return fileHandle.list(suffix);
    }

    @Override
    public boolean isDirectory() {
        return fileHandle.isDirectory();
    }

    @Override
    public FileHandle child(String name) {
        return fileHandle.child(name);
    }

    @Override
    public FileHandle sibling(String name) {
        return fileHandle.sibling(name);
    }

    @Override
    public FileHandle parent() {
        return fileHandle.parent();
    }

    @Override
    public void mkdirs() {
        fileHandle.mkdirs();
    }

    @Override
    public boolean exists() {
        return fileHandle.exists();
    }

    @Override
    public boolean delete() {
        return fileHandle.delete();
    }

    @Override
    public boolean deleteDirectory() {
        return fileHandle.deleteDirectory();
    }

    @Override
    public void emptyDirectory() {
        fileHandle.emptyDirectory();
    }

    @Override
    public void emptyDirectory(boolean preserveTree) {
        fileHandle.emptyDirectory(preserveTree);
    }

    @Override
    public void copyTo(FileHandle dest) {
        fileHandle.copyTo(dest);
    }

    @Override
    public void moveTo(FileHandle dest) {
        fileHandle.moveTo(dest);
    }

    @Override
    public long length() {
        return fileHandle.length();
    }

    @Override
    public long lastModified() {
        return fileHandle.lastModified();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EncryptFileHandle that = (EncryptFileHandle) o;

        return fileHandle.equals(that.fileHandle);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + fileHandle.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return fileHandle.toString();
    }
}