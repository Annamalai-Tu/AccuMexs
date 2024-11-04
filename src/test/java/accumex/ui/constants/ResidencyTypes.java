package accumex.ui.constants;

public enum ResidencyTypes{

    RESIDENT("Resident"),
    NON_RESIDENT("Non-Resident");

    private final String residentType;

    // Constructor with the correct name
    ResidencyTypes(String residentType) {
        this.residentType = residentType;
    }

    public String getResidentType() {
        return residentType;
    }
}
