package me.oczi.release.gson;

import me.oczi.release.GithubReleases;
import me.oczi.release.Release;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GsonReleaseServiceTest {

    @Test
    void requestLastRelease() {
        GsonReleaseService service = new GsonReleaseService(
            GithubReleases.of("OcZi", "Margaret")
        );
        Release[] releases = service.requestLastRelease().join();
        Assertions.assertTrue(releases.length > 0); // Margaret should have releases
        System.out.println("Releases[" + releases.length + "]");
        for (Release release : releases) {
            System.out.println("- " + release.getVersion());
        }
    }
}