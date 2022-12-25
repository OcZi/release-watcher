package me.oczi.release;

import java.util.Arrays;

/**
 * Object representation of a string release.
 * Contains various methods to interpret it as int (inspired on python)
 */
public interface Release {
    enum Operator {
        EQUALS, LESS, GREATER, LESS_OR_EQUALS, GREATER_OR_EQUALS;
    }

    static Release ofVersion(String version) {
        return new ReleaseImpl(version);
    }

    /**
     * Trim last zeros from an array of integers.
     * <p>Example: 1.2.0 -> 1.2
     * @param ints Array of integers to trim.
     * @return Trimmed array of integers.
     */
    static int[] trimVersion(int... ints) {
        int index = trimIndex(ints);
        return index == 0 ? new int[0] : Arrays.copyOfRange(ints, 0, index); // uh
    }

    static int trimIndex(int... ints) {
        int index = -1;
        for (int i = ints.length - 1; i >= 0; i--) {
            if (ints[i] != 0) {
                break;
            }

            index = i;
        }
        return index;
    }

    String getVersion();

    String[] getSemanticVersion();

    int[] getSemanticIntVersion();

    boolean semanticEquals(String... version);

    default boolean isEquals(int... version) {
        return operateInt(Operator.EQUALS, version);
    }

    default boolean isLessThan(int... version) {
        return operateInt(Operator.LESS, version);
    }

    default boolean isGreaterThan(int... version) {
        return operateInt(Operator.GREATER, version);
    }

    default boolean isLessOrEquals(int... version) {
        return operateInt(Operator.LESS_OR_EQUALS, version);
    }

    default boolean isGreaterOrEquals(int... version) {
        return operateInt(Operator.GREATER_OR_EQUALS, version);
    }

    boolean operateInt(Operator operator, int... version);
}
