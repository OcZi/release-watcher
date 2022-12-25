package me.oczi.release;

import java.util.concurrent.CompletableFuture;

/**
 * Service to request releases from repository.
 */
public interface ReleaseService {

    String getRepository();

    CompletableFuture<Release[]> requestLastRelease();
}
