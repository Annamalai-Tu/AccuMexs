package accumex.ui.pageobjects;

import accumex.ui.constants.IdentityTypes;
import accumex.ui.constants.MemberTypes;
import accumex.ui.constants.RegistrationTypes;
import accumex.ui.constants.ResidencyTypes;
import framework.init.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MemberRegPo extends AbstractPage {

    @FindBy(xpath = "//input[@name = 'MemberCategoryCode']")
    public WebElement memberCategoryCode;

    @FindBy(xpath = "//li[starts-with(@id, 'mui-') and contains(@id, '-option-0')]")
    public WebElement corporateOption;

    @FindBy(xpath = "//li[starts-with(@id, 'mui-') and contains(@id, '-option-1')]")
    public WebElement individualOption;

    @FindBy(xpath = "//input[@name = 'RegistrationType']")
    public WebElement registrationType;

    @FindBy(xpath = "//li[starts-with(@id, 'mui-') and contains(@id, '-option-0')]")
    public WebElement quickRegisteredOption;

    @FindBy(xpath = "//li[starts-with(@id, 'mui-') and contains(@id, '-option-1')]")
    public WebElement registeredOption;

    @FindBy(xpath = "//input[@name = 'ResidencyType']")
    public WebElement residencyType;

    @FindBy(xpath = "//li[starts-with(@id, 'mui-') and contains(@id, '-option-0')]")
    public WebElement nonResidentOption;

    @FindBy(xpath = "//li[starts-with(@id, 'mui-') and contains(@id, '-option-1')]")
    public WebElement residentOption;

    @FindBy(xpath = "//input[@name = 'identityTypeCode']")
    public WebElement identityType;

    @FindBy(xpath = "//li[@id='identityTypeCode-option-0']")
    public WebElement emiratesId;

    @FindBy(xpath = "//li[@id='identityTypeCode-option-1']")
    public WebElement gccId;

    @FindBy(xpath = "//li[@id='identityTypeCode-option-0']")
    public WebElement corporateLiscence;

    @FindBy(xpath = "//li[@id='identityTypeCode-option-1']")
    public WebElement tradeLiscence;

    @FindBy(xpath = "//li[@id='identityTypeCode-option-2']")
    public WebElement passportVisa;

    @FindBy(xpath = "//li[@id='identityTypeCode-option-3']")
    public WebElement seamanPass;

    @FindBy(xpath = "//input[@name = 'idNumber']")
    public WebElement idNumber;

    @FindBy(xpath = "//input[@name ='issueDate']")
    public WebElement issueDate;

    @FindBy(xpath = "//input[@name ='expiryDate']")
    public WebElement expiryDate;

    @FindBy(xpath = "//input[@name = 'placeOfIssueCode']")
    public WebElement placeOfIssue;

    @FindBy(xpath = "//input[@name = 'subCategoryId']")
    public WebElement visaType;

    @FindBy(xpath = "//li[starts-with(@id, 'mui-') and contains(@id, '-option-0')]")
    public WebElement selectFirstPlace;

    @FindBy(xpath = "//span[contains(text()  , 'Validate Identity Details')]")
    public WebElement validateIdentityDetails;

    @FindBy(xpath = "//div[contains(text() , 'Invalid')]")
    public WebElement idInvalidError;

    @FindBy(xpath = "//input[@name='FirstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='LastName']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='MobileNumber']")
    public WebElement mobileNumber;

    @FindBy(xpath = "//span[text()='Date Of Birth']/ancestor::div[contains(@class, 'MuiInputBase-root')]//input")
    public WebElement dateOfBirth;

    @FindBy(xpath = "//input[@name='Gender']")
    public WebElement gender;

    @FindBy(xpath = "//input[@name='Address1']")
    public WebElement address1;

    @FindBy(xpath = "//input[@name='Address2']")
    public WebElement address2;

    @FindBy(xpath = "//span[text()='Emirate']/ancestor::div[contains(@class, 'MuiInputBase-root')]//input")
    public WebElement emirate;

    @FindBy(xpath = "//input[@name='CityId']")
    public WebElement city;

    @FindBy(xpath = "//input[@name='PlaceOfBirth']")
    public WebElement placeOfBirth;

    @FindBy(xpath = "//input[@name='CountryOfBirth']")
    public WebElement countryOfBirth;

    @FindBy(xpath = "//input[@name='NationalityCode']")
    public WebElement nationality;

    @FindBy(xpath = "//input[@name='EconomicActivityCode']")
    public WebElement economicActivity;

    @FindBy(xpath = "//input[@name='SubEconomicActivityCode']")
    public WebElement subEconomicActivity;

    @FindBy(xpath = "//input[@name='AirportCode']")
    public WebElement airport;

    @FindBy(xpath = "//input[@name='ExpectedTurnover']")
    public WebElement expectedTurnover;

    @FindBy(xpath = "//input[@name='ExpectedTransactionCountPerYear']")
    public WebElement expectedTransactionCount;

    @FindBy(xpath = "//span[text() ='Address Same As Above']/following::input[1]")
    public WebElement checkAddressSame;

    @FindBy(xpath = "//input[@name ='Profession']")
    public WebElement profession;

    @FindBy(xpath = "(//span[contains(text(), 'Risk Type')]/preceding::input[1])[2]")
    public WebElement riskType;

    @FindBy(xpath = "//span[text() = 'Next']")
    public WebElement pageNext;

    @FindBy(xpath = "//input[@name='Email']")
    public WebElement email;

    @FindBy(xpath = "//input[@name='ZipCode']")
    public WebElement zipCode;

    @FindBy(xpath = "//input[@name='UBOCount']")
    public WebElement uboCount;

    @FindBy(xpath ="//label//span[text() = 'Date Of Incorporation']/following::input[1]")
    public WebElement  dateOfIncorporation;

    @FindBy(xpath =  "//input[@name='PresentAddress1']")
    public WebElement presentAddress1;

    @FindBy(xpath = "//button//span[text() = 'Proceed']")
    public WebElement proceed;

    @FindBy(xpath = "//button//span[text() = 'Yes, confirm']")
    public WebElement dobConfirmPopup;


    @FindBy(xpath = "//button//span[text() = 'Browse Files']")
    public WebElement browseFiles;

    public void enterProfession(String professionInput ){
        type(driver , profession , professionInput , true);
        clickOn(driver , selectFirstPlace);
    }

    public void enterRiskType(String riskInput ){
        type(driver , riskType , riskInput , true);
        clickOn(driver , selectFirstPlace);

    }

    public MemberRegPo(WebDriver driver) {
        super(driver);
    }

    public void selectMemberCategory(MemberTypes type){
        testStepsLog("Click on the Member Category Code");
        clickOn(driver , memberCategoryCode);
        testInfoLog("Select Member type as " , String.valueOf(type));
        if (type.equals(MemberTypes.CORPORATE)) {
            clickOn(driver, corporateOption);
        } else {
            clickOn(driver, individualOption);
        }
    }

    public void selectRegistrationType(RegistrationTypes type){
        testStepsLog("Click on Registration types");
        clickOn(driver , registrationType);
        testInfoLog("Select Registration type as " , String.valueOf(type));
        if (type.equals(RegistrationTypes.REGISTERED)) {
            clickOn(driver, registeredOption);
        } else {
            clickOn(driver, quickRegisteredOption);
        }
    }

    public void selectResidencyType(ResidencyTypes type){
        testStepsLog("Click on Residency type");
        clickOn(driver , residencyType);
        testInfoLog("Select residency type as" , String.valueOf(type));
        if (type.equals(ResidencyTypes.RESIDENT)) {
            clickOn(driver, residentOption);
        } else {
            clickOn(driver, nonResidentOption);
        }
    }

    public void enterIdNumber(String id){
        testInfoLog("Enter id number in the id input field as " , id);
        type(driver , idNumber , id , true);
    }

    public void enterIssueDate(String date){
        testInfoLog("Select date as" , DATE_FORMAT_DASH_DD_MM_YYYY);
        type(driver , issueDate , date , true);
    }

    public void enterExpiryDate(String date){
        testInfoLog("Select date as" , DATE_FORMAT_DASH_DD_MM_YYYY);
        type(driver , expiryDate , date , true);
    }

    public void clickValidateIdentityDetails(){
        testStepsLog("Click on Valid Identity Details");
        clickOn(driver , validateIdentityDetails);
    }


    public void selectIdentityType(IdentityTypes type) {
       testStepsLog("Click on Identity type");
        clickOn(driver , identityType);
        testInfoLog("Selecting Identity Type" , String.valueOf(type));

        if (type.equals(IdentityTypes.EMIRATES_ID)) {
           clickOn(driver ,emiratesId );
        } else if (type.equals(IdentityTypes.GCC_ID)) {
            clickOn(driver ,gccId );
        } else if (type.equals(IdentityTypes.PASSPORT_WITH_VISA)) {
            clickOn(driver ,passportVisa );
        } else if (type.equals(IdentityTypes.SEAMAN_PASS)) {
            clickOn(driver ,seamanPass );
        } else {
            throw new IllegalArgumentException("Invalid identity type: " + identityType);
        }
    }


    public void selectCorporateIdentityType(IdentityTypes type) {
        testStepsLog("Click on Identity type");
        clickOn(driver , identityType);
        testInfoLog("Selecting Identity Type" , String.valueOf(type));

        if (type.equals(IdentityTypes.CORPORATE_LISCENCE)) {
            clickOn(driver ,corporateLiscence );
        }  else {
           clickOn(driver , tradeLiscence);
        }
    }

    public void selectPlaceOfIssue(String place){
        testInfoLog("Enter Place as " , place);
        type(driver , placeOfIssue  , place , true);
        clickOn(driver , selectFirstPlace);
    }

    public void selectVisaType(String visa){
        testInfoLog("Enter Visa as " , visa);
        type(driver , visaType , visa , true);
        clickOn(driver , selectFirstPlace);
    }

    public boolean isIdInvalidPresent(){
        testStepsLog("Validate the Error result");
        return isElementPresent(idInvalidError);
    }

    public void enterFullName(String fullnamevalue) {
        testInfoLog("Enter first name as ", fullnamevalue);
        type(driver, firstName, fullnamevalue, true);
    }


    public void enterFirstNameLastName(String firstNameValue , String lastNameValue) {
        testInfoLog("Enter first name as ", firstNameValue);
        type(driver, firstName, firstNameValue, true);
        testInfoLog("Enter last name as ", lastNameValue);
        type(driver, lastName, lastNameValue, true);
    }


    public void enterMobileNumber(String mobileNumberValue) {
        testInfoLog("Enter mobile number as ", String.valueOf(mobileNumberValue));
        type(driver, mobileNumber, mobileNumberValue, true);
    }

    public void enterDateOfBirth(String dobValue) {
        testInfoLog("Enter date of birth as ", dobValue);
        pause(3);
        type(driver, dateOfBirth, dobValue, false);
    }

    public void selectGender(String genderValue) {
        testInfoLog("Select gender as ", genderValue);
        type(driver , gender , genderValue , true);
        clickOn(driver,selectFirstPlace);
    }

    public void enterAddress1(String Address1Value) {
        testInfoLog("Enter building number and street as ", Address1Value);
        type(driver, address1, Address1Value, true);
    }

    public void enterAddress2(String Address2Value) {
        testInfoLog("Enter building number and street as ", Address2Value);
        type(driver, address2, Address2Value, true);
    }

    public void selectEmirate(String emirateValue) {
        testInfoLog("Select emirate as ", emirateValue);
        type(driver , emirate, emirateValue , true);
        clickOn(driver,selectFirstPlace);
    }

    public void selectCity(String cityValue) {
        testInfoLog("Select city as ", cityValue);
        type(driver , city, cityValue , true);
        clickOn(driver,selectFirstPlace);
    }

    public void selectPlaceOfBirth(String placeOfBirthValue) {
        testInfoLog("Select place of birth as ", placeOfBirthValue);
        type(driver ,placeOfBirth, placeOfBirthValue , true);
    }

    public void selectCountryOfBirth(String countryOfBirthValue) {
        testInfoLog("Select country of birth as ", countryOfBirthValue);
        type(driver , countryOfBirth, countryOfBirthValue , true);
        clickOn(driver,selectFirstPlace);
    }

    public void selectNationality(String nationalityValue) {
        testInfoLog("Select nationality as ", nationalityValue);
        type(driver , nationality, nationalityValue , true);
        clickOn(driver,selectFirstPlace);
    }

    public void selectEconomicActivity(String economicActivityValue) {
        testInfoLog("Select economic activity as ", economicActivityValue);
        type(driver,economicActivity, economicActivityValue , true);
        clickOn(driver,selectFirstPlace);
    }

    public void selectSubEconomicActivity(String subEconomicActivityValue) {
        testInfoLog("Select sub-economic activity as ", subEconomicActivityValue);
        clickOn(driver ,subEconomicActivity);
        clickOn(driver,selectFirstPlace);
    }

    public void selectAirport(String airportValue) {
        testInfoLog("Select airport as ", airportValue);
        type(driver , airport, airportValue , true);
        clickOn(driver,selectFirstPlace);
    }

    public void enterExpectedTurnover(String expectedTurnoverValue) {
        testInfoLog("Select expected turnover as ", expectedTurnoverValue);
        type(driver , expectedTurnover, expectedTurnoverValue , true);
    }

    public void enterExpectedTransactionCount(String expectedTransactionCountValue) {
        testInfoLog("Select expected transaction count as ", expectedTransactionCountValue);
        type(driver , expectedTransactionCount, expectedTransactionCountValue , true);
    }

    public void clickSameAddressCheckbox(){
        clickOn(driver , checkAddressSame);
    }

    public void clickPageNext(){
        clickOn(driver , pageNext);
    }

    public void enterEmail(String emailValue) {
        testInfoLog("Enter Email ", emailValue);
        type(driver, email, emailValue, true);
    }

    public void enterZipcode(String ZipCode) {
        testInfoLog("Enter Zipcode ", ZipCode);
        type(driver, zipCode, ZipCode, true);
    }

    public void enterUboCount(int ubo) {
        testInfoLog("Enter UBO count as  ", String.valueOf(ubo));
        type(driver, uboCount, String.valueOf(ubo), true);
    }

    public void enterDateOfIncorporation(String date){
        testInfoLog("Enter Date as  " , DATE_FORMAT_DASH_DD_MM_YYYY);
        type(driver , dateOfIncorporation , date , true);
    }






    public void enterPresentAddress1(String address1){
        testInfoLog("Enter Date as  " , address1);
        type(driver , presentAddress1 , address1 , true);
    }

    public void  clickProceed(){
        testStepsLog("Click on Proceed");
        clickOn(driver , proceed);
    }


    public void dobPopUpWarninng(){
        testStepsLog("Accepting the Date of birth warning");
        clickOn(driver , dobConfirmPopup);
    }

    public void clickBrowseFiles(){
        testStepsLog("Clicking on Browse files to select the document");
        clickOn(driver , browseFiles);
    }

    public boolean isBrowseFilesPresent(){
        testVerifyLog("Clicking on Browse files to select the document");
        return isElementPresent(browseFiles);
    }


}
