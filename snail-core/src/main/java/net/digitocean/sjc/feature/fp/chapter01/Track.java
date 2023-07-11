package net.digitocean.sjc.feature.fp.chapter01;

/**
 *
 * @author: haoshichuan
 * @date: 2023/7/10 10:50
 */
public class Track {
    private final String name;
    private final int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the length of the track in milliseconds（曲目时长，以毫秒为单位）
     */
    public int getLength() {
        return length;
    }

    public Track copy() {
        return new Track(name, length);
    }

}
