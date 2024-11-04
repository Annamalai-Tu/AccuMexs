package accumex.ui.constants;

public enum IdentityTypes {

    EMIRATES_ID("EmiratesId"),
    GCC_ID("GccId"),
    PASSPORT_WITH_VISA("PassportWithVisa"),
    SEAMAN_PASS("SeamanPass"),
    CORPORATE_LISCENCE("Corporate Liscence"),
    TRADE_LISCENCE("Trade Liscence");



    private final String identityType;

    // Constructor with the correct name
    IdentityTypes(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityType() {
        return identityType;
    }
}


