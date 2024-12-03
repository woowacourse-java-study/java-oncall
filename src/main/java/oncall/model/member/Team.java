package oncall.model.member;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.constant.OnCallValue;
import oncall.constant.DayType;

public class Team {
    private final List<Member> members;
    private int turn;

    public Team(List<String> allMembers) {
        duplicateValidator(allMembers);
        sizeLimitValidator(allMembers);
        members = new ArrayList<>();
        for (String member : allMembers) {
            members.add(new Member(member));
        }
        this.turn = 0;
    }

    public void addAllMember(List<String> allMembers) {

        for (String member : allMembers) {
            members.add(new Member(member));
        }
    }
    public Member getThisTurnMember() {
        return members.get(turn);
    }

    public List<Member> getMembers() {
        return members;
    }


    public int getTurn() {
        return turn;
    }

    public Member getTurnMember() {
        return members.get(turn);
    }

    public void moveNextTurn() {
        if (turn + 1 == members.size()) {
            turn = 0;
            return;
        }
        turn++;
    }


    private void duplicateValidator(List<String> allMembers) {
        Set<String> memberSet = new HashSet<String>();
        for (String member : allMembers) {
            memberSet.add(member.trim());
        }
        if (memberSet.size() != allMembers.size()) {
            throw new IllegalArgumentException("비상 근무자는 한 팀에 1회만 편성될 수 있습니다.");
        }
    }

    private void sizeLimitValidator(List<String> allMembers) {
        if (allMembers.size() < OnCallValue.TEAM_SIZE_MIN_LIMIT.getValue()
                || allMembers.size() > OnCallValue.TEAM_SIZE_MAX_LIMIT.getValue()) {
            throw new IllegalArgumentException("비상 근무팀에는 최소 5명, 최대 35명이 들어갈 수 있습니다.");
        }
    }
}
