package oncall.constant;

public enum OnCallDayOfWeek {
    월(DayType.WEEKDAY),
    화(DayType.WEEKDAY),
    수(DayType.WEEKDAY),
    목(DayType.WEEKDAY),
    금(DayType.WEEKDAY),
    토(DayType.WEEKEND),
    일(DayType.WEEKEND),
    ;
    private final DayType dayType;

    OnCallDayOfWeek(DayType dayType) {
        this.dayType = dayType;
    }

    public DayType getDayType() {
        return dayType;
    }

    public OnCallDayOfWeek next() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}

