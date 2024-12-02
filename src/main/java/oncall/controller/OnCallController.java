package oncall.controller;

import oncall.model.Date;
import oncall.model.Parser;
import oncall.model.SafetyPolicy;
import oncall.model.member.Team;
import oncall.model.member.Teams;
import oncall.model.roster.Roster;
import oncall.model.roster.RosterMaker;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {
    private final InputView inputView;
    private final OutputView outputView;
    private final SafetyPolicy safetyPolicy;
    private final Parser parser;

    public OnCallController(InputView inputView, OutputView outputView,
                            SafetyPolicy safetyPolicy,
                            Parser parser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.safetyPolicy = safetyPolicy;
        this.parser = parser;
    }

    public void run() {
        Date date = requestDate();
        Teams teams = requestTeams();

        Roster roster = new Roster();
        RosterMaker rosterMaker = new RosterMaker(roster, date);

        while(!date.isNextMonth()){
            rosterMaker.make(teams);
            if (safetyPolicy.isIllegal(roster, date)) {
                rosterMaker.switchRoster(teams);
            }
        }
        outputView.responseRoster(roster);
    }

    private Teams requestTeams() {
        while (true) {
            try{
                String inputWeekdayTeam = inputView.requestWeekdayTeam();
                Team weekdayTeam = new Team(parser.parseTeam(inputWeekdayTeam));
                String inputWeekendTeam = inputView.requestWeekendTeam();
                Team weekendTeam = new Team(parser.parseTeam(inputWeekendTeam));
                return new Teams(weekdayTeam, weekendTeam);
            }catch(IllegalArgumentException e){
                outputView.responseErrorMessage(e.getMessage());
            }
        }
    }

    private Date requestDate() {
        while (true) {
            try{
                String inputDate = inputView.requestDate();
                return parser.parseDate(inputDate);
            }catch(IllegalArgumentException e){
                outputView.responseErrorMessage(e.getMessage());
            }
        }
    }
}
