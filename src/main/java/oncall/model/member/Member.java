package oncall.model.member;

public class Member {

    private final String name;

    public Member(String rawName) {

        this.name = validator(rawName);
    }

    public String getName() {
        return name;
    }

    private String validator(String rawName) {
        rawName = rawName.trim();
        if (rawName.length() > 5) {
            throw new IllegalArgumentException("이름의 길이는 5자 이하여야 합니다.");
        }
        return rawName;
    }
}
