package me.oczi.release.gson;

import com.google.gson.*;
import me.oczi.release.GithubReleases;
import me.oczi.release.Release;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * {@link Release} Deserializer for github API.
 */
public class GsonReleaseDeserializer implements JsonDeserializer<Release> {

    @Override
    public Release deserialize(JsonElement json,
                               Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        return Release.ofVersion(jsonObject.get("tag_name").getAsString());
    }
}
