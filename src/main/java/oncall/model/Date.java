package oncall.model;

import java.util.ArrayList;
import java.util.List;
import oncall.constant.DayType;
import oncall.constant.Holiday;
import oncall.constant.OnCallDayOfWeek;

public class Date {
    private final List<Integer> DAY_31_MONTH = new ArrayList<Integer>(List.of(1, 3, 5, 7, 8, 10, 12));
    private final List<Integer> DAY_30_MONTH = new ArrayList<Integer>(List.of(4, 6, 9, 11));
    private final List<Integer> DAY_28_MONTH = new ArrayList<Integer>(List.of(2));

    private final int month;
    private int day;
    private String dayOfWeek;
    private DayType dayType;


    public Date(int month, String rawDayOfWeek) {
        existMonthValidator(month);
        this.month = month;
        this.day = 1;
        this.dayOfWeek = parseDayOfWeek(rawDayOfWeek);
        this.dayType = parseDayType(dayOfWeek);
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public DayType getDayType() {
        return dayType;
    }

    public void moveToNextDay() {
        if (isNextMonth()) {
            return;
        }
        nextDay();
    }


    private void nextDay() {
        day += 1;
        for (OnCallDayOfWeek onCallDayOfWeek : OnCallDayOfWeek.values()) {
            if (onCallDayOfWeek.name().equals(dayOfWeek)) {
                dayOfWeek = onCallDayOfWeek.next().name();
                dayType = parseDayType(dayOfWeek);
                return;
            }
        }
    }

    private void existMonthValidator(int month) {
        if (month<1 || month>12) {
            throw new IllegalArgumentException("존재하지 않는 달입니다");
        }
    }

    public boolean isNextMonth() {
        int maxDayOfMonth = getMaxDayOfMonth();
        return day == maxDayOfMonth + 1;
    }

    private int getMaxDayOfMonth() {
        if (DAY_31_MONTH.contains(month)) {
            return 31;
        }
        if (DAY_30_MONTH.contains(month)) {
            return 30;
        }
        if (DAY_28_MONTH.contains(month)) {
            return 28;
        }
        throw new IllegalArgumentException("존재하지 않는 달 입니다.");
    }

    private String parseDayOfWeek(String rawDayOfWeek) {
        for (OnCallDayOfWeek dayOfWeek : OnCallDayOfWeek.values()) {
            if (dayOfWeek.name().equals(rawDayOfWeek.trim())) {
                return rawDayOfWeek.trim();
            }
        }
        throw new IllegalArgumentException("잘못된 요일을 입력하였습니다.");
    }

    private DayType parseDayType(String dayOfWeek) {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.getMonth() == month && holiday.getDay() == day) {
                return DayType.WEEKEND;
            }
        }
        for (OnCallDayOfWeek onCallDayOfWeekdayOfWeek : OnCallDayOfWeek.values()) {
            if (onCallDayOfWeekdayOfWeek.name().equals(dayOfWeek)) {
                return onCallDayOfWeekdayOfWeek.getDayType();
            }
        }
        throw new IllegalArgumentException("요일이 휴일인지 평일인지 찾지 못했습니다.");
    }


}
