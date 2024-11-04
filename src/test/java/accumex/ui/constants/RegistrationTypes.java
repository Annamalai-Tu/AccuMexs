package accumex.ui.constants;

public enum RegistrationTypes {

    REGISTERED("Registered"),
    QUICKREGISTERED("Quick_Registered");

    private final String registrationType;

    // Corrected constructor name to match enum name
    RegistrationTypes(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getRegistrationType() {
        return registrationType;
    }
}
