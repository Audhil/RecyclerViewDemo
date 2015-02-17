package com.wordpress.smdaudhilbe.model;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.util.LruCache;

/**
 * Created by mohammed-2284 on 17/02/15.
 */
public class PaletteManager {

    private LruCache<String, Palette> cache = new LruCache<String, Palette>(100);

    public interface CallBack {
        void onPaletteReady(Palette palette);
    }

    public void getPalette(final String key, Bitmap bitmap, final CallBack callBack) {
        Palette palette = cache.get(key);

        if (palette != null) {
            callBack.onPaletteReady(palette);
        } else {
            Palette.generateAsync(bitmap, 24, new Palette.PaletteAsyncListener() {

                @Override
                public void onGenerated(Palette palette) {
                    cache.put(key, palette);
                    callBack.onPaletteReady(palette);
                }
            });
        }
    }
}