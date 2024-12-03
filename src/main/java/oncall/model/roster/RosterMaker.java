package oncall.model.roster;

import oncall.constant.DayType;
import oncall.model.Date;
import oncall.model.member.Member;
import oncall.model.member.Team;
import oncall.model.member.Teams;

public class RosterMaker {
    private final Roster roster;
    private final Date date;

    public RosterMaker(Roster roster, Date date) {
        this.roster = roster;
        this.date = date;
    }

    public void make(Teams teams) {
        Team weekdayTeam = teams.weekdayTeam();
        Team weekendTeam = teams.weekendTeam();
        if (date.getDayType() == DayType.WEEKDAY) {
            pushRosterElement(weekdayTeam, DayType.WEEKDAY);
            return;
        }
        pushRosterElement(weekendTeam, DayType.WEEKEND);
    }

    public void switchRoster(Teams teams) {
        Team weekdayTeam = teams.weekdayTeam();
        Team weekendTeam = teams.weekendTeam();
        RosterElement lastRoster = roster.getLastRoster();
        roster.removeLastRosterElement();
        
        if (date.getDayType() == DayType.WEEKDAY) {
            switchMember(weekdayTeam, lastRoster);
            return;
        }
        switchMember(weekendTeam, lastRoster);
    }

    private void switchMember(Team team, RosterElement lastRoster) {
        Member changedMember = team.getThisTurnMember();
        roster.addRoster(
                new RosterElement(lastRoster.month(), lastRoster.day(), lastRoster.dayOfWeek(), changedMember));
        roster.addRoster(
                new RosterElement(date.getMonth(), date.getDay(), date.getDayOfWeek(), lastRoster.member()));
    }


    private void pushRosterElement(Team team, DayType dayType) {
        if (date.getDayType() == dayType) {
            roster.addRoster(
                    new RosterElement(date.getMonth(), date.getDay(), date.getDayOfWeek(), team.getThisTurnMember()));
            team.moveNextTurn();
            date.moveToNextDay();
        }
    }
}
