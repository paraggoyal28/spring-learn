package org.example.Structural.Proxy.YouTube.someCoolMediaLibrary;

import java.util.HashMap;

public interface ThirdPartyYouTubeLib {
    HashMap<String, Video> popularVideos();
    Video getVideo(String videoId);
}
