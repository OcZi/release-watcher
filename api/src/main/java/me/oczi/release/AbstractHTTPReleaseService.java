package me.oczi.release;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public abstract class AbstractHTTPReleaseService implements ReleaseService {
    public static final int TIME_OUT = (int) TimeUnit.MILLISECONDS.toSeconds(5);

    protected final String repository;

    public AbstractHTTPReleaseService(String repository) {
        this.repository = repository;
    }

    @Override
    public String getRepository() {
        return repository;
    }

    @Override
    public abstract CompletableFuture<Release[]> requestLastRelease();

    protected CompletableFuture<String> getHttpRequest() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(repository);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection connection = (HttpURLConnection) urlConnection;
                connection.setRequestMethod("GET");
                connection.setReadTimeout(TIME_OUT);
                connection.setConnectTimeout(TIME_OUT);

                StringBuilder builder = new StringBuilder();
                try (final BufferedReader request = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = request.readLine()) != null) {
                        builder.append(line.trim());
                    }
                }
                return builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
}
