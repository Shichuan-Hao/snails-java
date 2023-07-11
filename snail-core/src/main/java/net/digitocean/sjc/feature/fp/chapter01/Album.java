package net.digitocean.sjc.feature.fp.chapter01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;

/**
 * @author: haoshichuan
 * @date: 2023/7/10 13:31
 */
public class Album implements Performance{
    private final String name;
    private final List<Track> tracks;
    private final List<Artist> musicians;

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(tracks);
        Objects.requireNonNull(musicians);

        this.name = name;
        this.tracks = new ArrayList<>(tracks);
        this.musicians = new ArrayList<>(musicians);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the tracks
     */
    public Stream<Track> getTracks() {
        return tracks.stream();
    }

    /**
     * @return Used in imperative code examples that need to iterate over a list
     */
    public List<Track> getTrackList() {
        return unmodifiableList(tracks);
    }

    /**
     * @return the musicians
     */
    @Override
    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }

    /**
     * @return Used in imperative code examples that need to iterate over a list
     */
    public List<Artist> getMusicianList() {
        return unmodifiableList(musicians);
    }

    public Artist getMainMusician() {
        return musicians.get(0);
    }

    public Album copy() {
        List<Track> tracks = getTracks()
                .map(Track::copy)
                .collect(Collectors.toList());
        List<Artist> musicians = getMusicians()
                .map(Artist::copy)
                .collect(Collectors.toList());
        return new Album(name, tracks, musicians);
    }
}
