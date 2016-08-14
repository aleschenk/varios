package com.rhythmdb;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/*
<?xml version="1.0" standalone="yes"?>
<rhythmdb version="2.0">
  <entry type="song">
    <title>Ando ganas (llora llora)</title>
    <genre>Rock &amp; Roll</genre>
    <artist>Los Piojos</artist>
    <album>Ay ay ay</album>
    <duration>330</duration>
    <file-size>5281796</file-size>
    <location>file:///home/ale/Music/Los%20Piojos/Ay%20ay%20ay/Ando%20ganas%20(llora%20llora).mp3</location>
    <mtime>1414775700</mtime>
    <first-seen>1470593473</first-seen>
    <last-seen>1471130542</last-seen>
    <bitrate>127</bitrate>
    <date>727929</date>
    <media-type>audio/mpeg</media-type>
    <album-artist>Los Piojos</album-artist>
    <composer>Unknown</composer>
  </entry>
  <entry type="iradio">
    <title>80 Nacional E Internacional</title>
    <genre>Anglo, Nacional y Latino</genre>
    <artist></artist>
    <album></album>
    <location>http://buecrplb01.cienradios.com.ar/571_80_Nacional_E_Internacional_32000.aac</location>
    <play-count>1</play-count>
    <last-played>1471127743</last-played>
    <bitrate>32</bitrate>
    <date>0</date>
    <media-type>application/octet-stream</media-type>
  </entry>
</rhythmdb>
*/
@Root(name = "rhythmdb")
public class Rhythmdb {

    @ElementList(name = "entry", inline = true)
    private List<Entry> entries = new ArrayList<>();

    @Attribute
    private String version = "2.0";

    public Rhythmdb addEntry(final Entry anEntry) {
        if(anEntry == null) {
            throw new RuntimeException("The entry cannot be null");
        }

        entries.add(anEntry);

        return this;
    }

    public List<Entry> entries() {
        return entries;
    }

}
