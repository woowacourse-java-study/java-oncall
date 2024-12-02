package oncall.model;

import java.util.ArrayList;
import java.util.List;
import oncall.model.member.Team;

public class Parser {
    public Date parseDate(String input) {
        List<String> splitInput  = new ArrayList<>(List.of(input.trim().split(",", -1)));
        dateInputLengthValidator(splitInput);
        return new Date(parseMonth(splitInput), splitInput.getLast());
    }
    public List<String> parseTeam(String input) {
        teamBlankValidator(input);
        return new ArrayList<>(List.of(input.trim().split(",", -1)));
    }

    private static void teamBlankValidator(String input) {
        if (input.trim().isBlank()) {
            throw new IllegalArgumentException("사원 닉네임을 입력하지 않았습니다.");
        }
    }

    private static void dateInputLengthValidator(List<String> splitInput) {
        if (splitInput.size() != 2) {
            throw new IllegalArgumentException("월과 요일만 입력하세요.");
        }
    }

    private static int parseMonth(List<String> splitInput) {
        try {
            return Integer.parseInt(splitInput.getFirst());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("월은 숫자로 입력해주세요.");
        }
    }
}
