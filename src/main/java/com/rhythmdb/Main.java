package com.rhythmdb;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
//        new Main().init();
        new Main().read();
    }

    public void read() throws Exception {
//        Rhythmdb rhythmdb = new Rhythmdb().addEntry(entry);

        Format format = new Format(2, "<?xml version=\"1.0\" standalone=\"yes\"?>");

        Serializer serializer = new Persister(format);
        Rhythmdb rhythmdb = serializer.read(Rhythmdb.class, new File("rhythmdb.xml"));
        Rhythmdb example = new Rhythmdb();
        for(Entry entry : rhythmdb.entries()) {
            System.out.println(entry.getLocation());
            example.addEntry(entry);
        }
        serializer.write(example, new File("example.xml"));
    }

    public void init() throws Exception {
        Entry entry = new Entry("song");
        entry.setTitle("Ando ganas (llora llora)");
        entry.setGenre("Rock & Roll");
        entry.setArtist("Los Piojos");
        entry.setAlbum("Ay ay ay");
        entry.setDuration(330);
        entry.setFileSize(5281796);
        entry.setLocation("file:///home/ale/Music/Los%20Piojos/Ay%20ay%20ay/Ando%20ganas%20(llora%20llora).mp3");
        entry.setMtime(1414775700);
        entry.setFirstSeen(1470593473);
        entry.setLastSeen(1471130542);
        entry.setBitrate(127);
        entry.setDate(727929);
        entry.setMediaType("audio/mpeg");
        entry.setAlbumArtist("Los Piojos");
        entry.setComposer("Unknown");

        Rhythmdb rhythmdb = new Rhythmdb().addEntry(entry);

        Serializer serializer = new Persister();
        serializer.write(rhythmdb, new File("example.xml"));
    }

}
