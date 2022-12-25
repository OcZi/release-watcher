# Release Watcher
A small API to request github releases (<10 kb size).  
Methods of release are inspired by python's version info.  

Supports Gson as deserializer of http requests (Jackson and other libs can be adapted).
## Usage
```java
import me.oczi.release.GithubReleases;
import me.oczi.release.Release;

class MyClass {

    public static void main(String[] args) {
        // Create the service to request releases
        GsonReleaseService service = new GsonReleaseService(
            GithubReleases.of("OcZi", "Margaret") // Build the release URL of the repository
        );
        // Wait CompletableFuture to finish
        Release[] releases = service.requestLastRelease().join();
        // Github will return it in date order
        Release lastRelease = releases[0];

        // New version found?
        if (lastRelease.isGreaterThan(0, 8)) {
            System.out.println("New version available: " + lastRelease.getVersion());
        }
    }
}
```
## Dependency

### Maven:

```xml
<repositories>
    <repository>
        <id>unnamed-snapshots</id>
        <url>https://repo.unnamed.team/repository/unnamed-snapshots/</url>
    </repository>
</repositories>


<dependencies>
    <!-- Without deserializer -->
    <dependency>
        <groupId>me.oczi</groupId>
        <artifactId>release-watcher-api</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>

    <!-- Gson deserializer -->
    <!-- It compiles API too -->
    <dependency>
        <groupId>me.oczi</groupId>
        <artifactId>release-watcher-gson</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

### Gradle:

```groovy
repositories {
    maven { url 'https://repo.unnamed.team/repository/unnamed-snapshots/' }
}

// Without deserializer
compileOnly('me.oczi:release-watcher-api:1.0-SNAPSHOT')

// Gson deserializer
// It compiles API too
compileOnly('me.oczi:release-watcher-gson:1.0-SNAPSHOT')
```
## Build
JDK: Minimum 8  
Gson: Minimum 2.2.4
