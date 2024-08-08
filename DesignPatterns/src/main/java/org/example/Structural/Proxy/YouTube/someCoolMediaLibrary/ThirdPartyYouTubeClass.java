package org.example.Structural.Proxy.YouTube.someCoolMediaLibrary;

import java.util.HashMap;

public class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {

    @Override
    public HashMap<String, Video> popularVideos() {
        connectToServer("http://www.youtube.com");
        return getRandomVideos();
    }


    @Override
    public Video getVideo(String videoId) {
        connectToServer("http://www.youtube.com/" + videoId);
        return getSomeVideo(videoId);
    }

    // --------------------------------------------------------------
    // Fake methods to simulate network activity. They as slow as  a real life.

    private void connectToServer(String server) {
        System.out.println("Connecting to " + server + " ....");
        experienceNetworkLatency();
        System.out.println("Connected!\n");
    }

    private void experienceNetworkLatency() {
        int randomLatency = random(5, 10);
        for (int i = 0; i < randomLatency; ++i) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int random(int mn, int mx) {
        return mn + (int) (Math.random() * ((mx - mn) + 1));
    }

    private HashMap<String, Video> getRandomVideos() {
        System.out.println("Downloading populars...");

        experienceNetworkLatency();

        HashMap<String, Video> hmap = new HashMap<>();
        hmap.put("catzzzzzzzz", new Video("sadgas", "Catzzz.avi"));
        hmap.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        hmap.put("dancesvideoo", new Video("asdfas3ffasd", "Dancing video.mpq"));
        hmap.put("dlsdk5jfslaf", new Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"));
        hmap.put("3sdfgsd1j333", new Video("3sdfgsd1j333", "Programing lesson#1.avi"));

        System.out.println("Done!\n");
        return hmap;
    }

    private Video getSomeVideo(String videoId) {
        System.out.print("Downloading video... ");

        experienceNetworkLatency();
        Video video = new Video(videoId, "Some video title");

        System.out.print("Done!" + "\n");
        return video;
    }

}
