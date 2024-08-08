package org.example.Structural.Proxy.YouTube;

import org.example.Structural.Proxy.YouTube.downloader.YouTubeDownloader;
import org.example.Structural.Proxy.YouTube.proxy.YouTubeCacheProxy;
import org.example.Structural.Proxy.YouTube.someCoolMediaLibrary.ThirdPartyYouTubeClass;

public class Demo {
    public static void main(String[] args) {
        YouTubeDownloader naiveDownloader = new YouTubeDownloader(new ThirdPartyYouTubeClass());
        YouTubeDownloader smartDownloader = new YouTubeDownloader(new YouTubeCacheProxy());

        long naive = test(naiveDownloader);
        long smart = test(smartDownloader);

        System.out.println("Time saved by caching proxy: " + (naive - smart) + " ms");
    }

    private static long test(YouTubeDownloader downloader) {
        long startTime = System.currentTimeMillis();

        // User behavior in our app
        downloader.renderPopularVideos();
        downloader.renderVideoPage("catzzzzzzzz");
        downloader.renderPopularVideos();
        downloader.renderVideoPage("dancesvideoo");

        // Users might visit the same page quite often.
        downloader.renderVideoPage("catzzzzzzzz");
        downloader.renderVideoPage("someothervid");

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time elapsed: " + estimatedTime + " ms\n");
        return estimatedTime;
    }



}

