package net.digitocean.sjc.feature.fp.chapter01;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: haoshichuan
 * @date: 2023/7/10 11:15
 */
public class Chapter01 extends MusicChapter {

    List<String> getNamesOfArtists_Lambda() {
        return artists.stream()
                .map(artist -> artist.getName())
                .collect(Collectors.toList());
    }

    List<String> getNameOfArtists_MethodReference() {
        return artists.stream()
                .map(Artist::getName)
                .collect(Collectors.toList());
    }

    List<Artist> artistsLivingInLondon() {
        return artists.stream()
                .filter(artist -> Objects.equals("London", artist.getNationality()))
                .collect(Collectors.toList());
    }

}
