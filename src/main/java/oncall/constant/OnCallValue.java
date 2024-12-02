package oncall.constant;

public enum OnCallValue {
    MEMBER_NAME_LENGTH_LIMIT(5),
    TEAM_SIZE_MIN_LIMIT(5),
    TEAM_SIZE_MAX_LIMIT(35);
    private final int value;

    OnCallValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
