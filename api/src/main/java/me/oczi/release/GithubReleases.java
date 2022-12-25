package me.oczi.release;

/**
 * <a href="https://github.com/OcZi/Margaret/blob/master/Common/src/main/java/me/oczi/common/api/github/GithubApi.java">From my old code of Margaret - 2020</a>
 */
public interface GithubReleases {
    String URL = "https://api.github.com/repos/%s/%s/releases";

    /**
     * Build github api url for a specific repository.
     * @param owner Owner of repository (user or organization).
     * @param repository Repository name.
     * @return github release url of repository.
     */
    static String of(String owner, String repository) {
        return String.format(URL, owner, repository);
    }
}
