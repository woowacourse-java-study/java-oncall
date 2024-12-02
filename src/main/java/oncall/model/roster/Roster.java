package oncall.model.roster;

import java.util.ArrayList;
import java.util.List;

public class Roster {
    private final List<RosterElement> roster;

    public Roster() {
        roster = new ArrayList<RosterElement>();
    }

    public List<RosterElement> getAllRoster() {
        return roster;
    }

    public RosterElement getRosterByDay(int day) {
        if (roster.size() >= day) {
            return roster.get(day - 1);
        }
        throw new IllegalArgumentException("근무표 검색 오류");
    }


    public void addRoster(RosterElement rosterElement) {
        roster.add(rosterElement);
    }

    public RosterElement getLastRoster() {
        return roster.getLast();
    }

    public void removeLastRosterElement() {
        roster.removeLast();
    }



}
