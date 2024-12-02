package oncall.view;

import oncall.constant.DayType;
import oncall.constant.Holiday;
import oncall.constant.OnCallDayOfWeek;
import oncall.model.roster.Roster;
import oncall.model.roster.RosterElement;

public class OutputView {
    public void responseErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void responseRoster(Roster roster) {
        StringBuilder stringBuilder = new StringBuilder();
        String LINE_SEPARATOR = System.lineSeparator();
        stringBuilder.append(LINE_SEPARATOR);
        for (RosterElement re : roster.getAllRoster()) {
            if (isHolidayInWeekday(re)) {
                stringBuilder.append(re.month()).append("월 ").append(re.day()).append("일 ").append(re.dayOfWeek())
                        .append("(휴일) ").append(re.member().getName()).append(LINE_SEPARATOR);
                continue;
            }
            stringBuilder.append(re.month()).append("월 ").append(re.day()).append("일 ").append(re.dayOfWeek())
                    .append(" ").append(re.member().getName()).append(LINE_SEPARATOR);
        }
        System.out.println(stringBuilder);
    }

    private boolean isHolidayInWeekday(RosterElement rosterElement) {
        for (Holiday holiday : Holiday.values()) {
            if (OnCallDayOfWeek.valueOf(rosterElement.dayOfWeek()).getDayType().equals(DayType.WEEKDAY)
                    && holiday.getMonth() == rosterElement.month() && holiday.getDay() == rosterElement.day()) {
                return true;
            }
        }
        return false;
    }
}

