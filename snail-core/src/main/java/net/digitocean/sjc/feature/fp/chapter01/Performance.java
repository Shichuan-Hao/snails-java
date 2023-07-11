package net.digitocean.sjc.feature.fp.chapter01;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * Performance
 *
 * @author: haoshichuan
 * @date: 2023/7/10 10:40
 */
public interface Performance {
    public String getName();

    public Stream<Artist> getMusicians();

    // TODO: test
    public default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> {
            return concat(Stream.of(artist), artist.getMembers());
        });
    }
}
