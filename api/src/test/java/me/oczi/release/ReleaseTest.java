package me.oczi.release;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReleaseTest {

    // release operator test
    @Test
    public void main() {
        Release release = Release.ofVersion("v1.4.2");

        Assertions.assertArrayEquals(release.getSemanticIntVersion(), new int[]{1, 4, 2});
        Assertions.assertArrayEquals(release.getSemanticVersion(), new String[]{"v1", "4", "2"});
        Assertions.assertTrue(release.isEquals(1, 4, 2)); // 1.4.2 == 1.4.2
        Assertions.assertTrue(release.isEquals(1, 4, 2, 0)); // 1.4.2 == 1.4.2.0 (trimmed)
        Assertions.assertFalse(release.isEquals(1, 4, 2, 4)); // 1.4.2 != 1.4.2.4

        Assertions.assertTrue(release.isLessThan(2, 1)); // 1.4.2 < 2.1
        Assertions.assertTrue(release.isLessThan(1, 4, 2, 1)); // 1.4.2 < 1.4.2.1

        Assertions.assertTrue(release.isGreaterThan(1, 4)); // 1.4.2 > 1.4
        Assertions.assertTrue(release.isGreaterThan(0)); // 1.4.2 > 0

        Assertions.assertTrue(release.isGreaterOrEquals(1, 4, 2, 1)); // 1.4.2 < 1.4.2.1 and 1.4.2 != 1.4.2.1
        Assertions.assertTrue(release.isGreaterOrEquals(1, 4)); // 1.4.2 > 1.4 and 1.4.2 != 1.4

        Assertions.assertFalse(release.isLessOrEquals(1, 4)); // Same result

        Release release2 = Release.ofVersion("v1.4.0");
        int[] semanticIntVersion = release2.getSemanticIntVersion();
        Assertions.assertArrayEquals(semanticIntVersion, new int[]{1, 4}); // 1.4.0 (trimmed) == 1.4
    }
}