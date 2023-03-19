package org.ryuu.gdx.encrypt.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import org.ryuu.gdx.encrypt.file.EncryptFileHandle;

import static org.ryuu.gdx.encrypt.setting.EncryptSettings.isEncrypt;
import static org.ryuu.gdx.encrypt.util.Paths.getCipherPath;

public class EncryptAssetManger extends AssetManager {
    public EncryptAssetManger() {
        super();
    }

    public EncryptAssetManger(FileHandleResolver resolver) {
        super(resolver);
    }

    public EncryptAssetManger(FileHandleResolver resolver, boolean defaultLoaders) {
        super(resolver, defaultLoaders);
    }

    @Override
    public synchronized <T> T get(String path, Class<T> type) {
        return super.get(getActualPath(path), type);
    }

    @Override
    public synchronized <T> void load(String path, Class<T> type) {
        String actualPath = getActualPath(path);
        super.load(actualPath, type);
    }

    public static EncryptAssetManger newEncryptAssetManger() {
        EncryptAssetManger assetManger = new EncryptAssetManger(path -> {
            if (isEncrypt() && !path.contains(".")) {
                return new EncryptFileHandle(Gdx.files.internal(path));
            } else {
                return Gdx.files.internal(path);
            }
        });

        assetManger.setLoader(ParticleEffect.class, new EncryptParticleEffectLoader(assetManger.getFileHandleResolver()));
        return assetManger;
    }

    private static String getActualPath(String plainPath) {
        if (isEncrypt()) {
            if (Gdx.files.internal(plainPath).exists()) {
                return plainPath;
            }
            return getCipherPath(plainPath);
        } else {
            return plainPath;
        }
    }
}