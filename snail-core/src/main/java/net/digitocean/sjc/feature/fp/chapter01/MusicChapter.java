package net.digitocean.sjc.feature.fp.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: haoshichuan
 * @date: 2023/7/10 11:06
 */
public abstract class MusicChapter {
    protected final List<Artist> artists;
    protected final List<Album> albums;

    public MusicChapter() {
        artists = new ArrayList<>();
        albums = new ArrayList<>();
        loadData("");
    }

    protected void loadData(String file) {
    }
}
