package me.oczi.release;

import java.util.Arrays;

public class ReleaseImpl implements Release {
    private final String version;

    public ReleaseImpl(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String[] getSemanticVersion() {
        return version.split("\\.");
    }

    @Override
    public int[] getSemanticIntVersion() {
        int[] ints = new int[0];
        int[] nonZeroInts = null;
        for (int i = 0; i < version.length(); i++) {
            char symbol = version.charAt(i);
            if (!Character.isDigit(symbol)) {
                continue;
            }


            ints = Arrays.copyOf(ints, ints.length + 1);
            int numericValue = Character.getNumericValue(symbol);
            // Get last array copy without zeros at the end
            if (numericValue != 0) {
                nonZeroInts = ints;
            }

            ints[ints.length - 1] = numericValue;
        }
        // Trim if necessary
        return ints.length > 1 && ints[ints.length - 1] == 0 ? nonZeroInts : ints;
    }

    @Override
    public boolean semanticEquals(String... parts) {
        String[] version = getSemanticVersion();
        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].equals(version[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean operateInt(Operator operator, int... parts) {
        int[] version = getSemanticIntVersion();
        int length = version.length;
        int partLength = parts.length;
        if (parts[partLength - 1] == 0 && partLength > 1) {
            parts = Release.trimVersion(parts);
            partLength = parts.length;
        }

        switch (operator) {
            case EQUALS:
                if (length != partLength) {
                    return false;
                }

                for (int i = 0; i < length; i++) {
                    if (version[i] != parts[i]) {
                        return false;
                    }
                }
                return true;
            case LESS:
                for (int i = 0; i < partLength; i++) {
                    if (length < i + 1) {
                        return true;
                    }

                    if (parts[i] > version[i]) {
                        return true;
                    }
                }
                break;
            case GREATER:
                for (int i = 0; i < length; i++) {
                    if (partLength < i + 1) {
                        return true;
                    }

                    if (parts[i] < version[i]) {
                        return true;
                    }
                }
                break;
            case LESS_OR_EQUALS:
                boolean equals = true;
                for (int i = 0; i < length; i++) {
                    if (partLength < i + 1) {
                        return false;
                    }

                    int part = parts[i];
                    int ver = version[i];
                    if (part > ver) {
                        return true;
                    } else if (equals) {
                        equals = part == ver;
                    }
                }

                if (equals) {
                    return true;
                }
                break;
            case GREATER_OR_EQUALS:
                equals = true;
                for (int i = 0; i < length; i++) {
                    if (partLength <  i + 1) {
                        return true;
                    }

                    int part = parts[i];
                    int ver = version[i];
                    if (part < ver) {
                        return true;
                    } else if (equals) {
                        equals = part == ver;
                    }
                }

                if (equals) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ReleaseImpl{" +
               "release='" + version + '\'' +
               '}';
    }
}
