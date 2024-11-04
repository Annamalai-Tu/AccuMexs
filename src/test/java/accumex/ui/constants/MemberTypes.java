package accumex.ui.constants;

public enum MemberTypes {

    CORPORATE("Corporate"),
    INDIVIDUAL("Individual");

    private final String memberType;

    MemberTypes(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberType() {
        return memberType;
    }

}
