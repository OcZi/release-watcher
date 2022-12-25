package me.oczi.release.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.oczi.release.AbstractHTTPReleaseService;
import me.oczi.release.Release;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of {@link AbstractHTTPReleaseService} with Gson as deserializer.
 */
public class GsonReleaseService extends AbstractHTTPReleaseService {
    protected Gson gson = new GsonBuilder()
        .serializeNulls()
        .registerTypeAdapter(Release.class, new GsonReleaseDeserializer())
        .create();

    public GsonReleaseService(String repository) {
        super(repository);
    }

    @Override
    public CompletableFuture<Release[]> requestLastRelease() {
        return getHttpRequest()
            .thenApply(httpRequest -> gson.fromJson(httpRequest, Release[].class));
    }
}
