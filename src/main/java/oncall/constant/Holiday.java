package oncall.constant;

public enum Holiday {
    FIRST_DAY_OF_YEAR(1, 1), THREE_ONE_DAY(3, 1),CHILDREN_DAY(5,5), MEMORIAL_DAY(6,6), LIBERATION_DAY(8, 15), FOUNDATION_DAY(
            10, 3),KOREAN_LANG_DAY(10,9), CHRISTMAS(12, 25),
    ;
    private final int month;
    private final int day;

    Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
