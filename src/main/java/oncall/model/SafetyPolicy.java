package oncall.model;

import oncall.model.roster.Roster;
import oncall.model.roster.RosterElement;

public class SafetyPolicy {
    public boolean isIllegal(Roster roster, Date date) {
        int day = date.getDay()-1;
        if (day == 1) {
            return false;
        }
        RosterElement rosterDDay = roster.getRosterByDay(day);
        RosterElement rosterYesterday = roster.getRosterByDay(day-1);

        // member() 만으로 동일한지 확실치 않아 getName()까지 들어가서 비교
        return rosterDDay.member().equals(rosterYesterday.member());
    }

}
