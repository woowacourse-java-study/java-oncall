package oncall.model.roster;

import oncall.model.member.Member;

public record RosterElement(int month,
                            int day,
                            String dayOfWeek,

                            Member member) {
}

