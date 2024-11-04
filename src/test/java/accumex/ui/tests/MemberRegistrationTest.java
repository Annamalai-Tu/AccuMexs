package accumex.ui.tests;

import accumex.ui.constants.IdentityTypes;
import accumex.ui.constants.MemberTypes;
import accumex.ui.constants.RegistrationTypes;
import accumex.ui.constants.ResidencyTypes;
import framework.init.Generics;
import framework.init.WebDriverInit;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MemberRegistrationTest extends WebDriverInit {

	@Test
	public void TC_001_INDIVIDUAL_MEMBER() {
		testCaseLog("Page Load Time : " + getPageLoadTime(driver) + " ms");
		testCaseLog("TC_001 - Individual Member registration");
		loginPO.loginAs(USERNAME,PASSWORD);
		homepagePo.clickMemberRegistration();
		memberRegPo.selectMemberCategory(MemberTypes.INDIVIDUAL);
		memberRegPo.selectRegistrationType(RegistrationTypes.REGISTERED);
		memberRegPo.selectResidencyType(ResidencyTypes.RESIDENT);
		memberRegPo.selectIdentityType(IdentityTypes.EMIRATES_ID);
		memberRegPo.enterIdNumber("784198012345670");
		memberRegPo.selectPlaceOfIssue("Dubai");
		memberRegPo.enterIssueDate("29102024");
		memberRegPo.enterExpiryDate("05122024");
		memberRegPo.selectVisaType("Platinum visa");
		memberRegPo.clickValidateIdentityDetails();
		//Assert.assertTrue( memberRegPo.isIdInvalidPresent());
		memberRegPo.enterFirstNameLastName(getRandomFirstName(), getRandomLastName());
		memberRegPo.enterMobileNumber("501234567"); //getRandomNumberBetween(500000000 , 600000000)  );
		memberRegPo.enterDateOfBirth("29031999");
		memberRegPo.selectGender("Male");
		memberRegPo.enterAddress1("Apt. 5B, Suite 101");
		memberRegPo.enterAddress2("Al Barsha Street");
		memberRegPo.selectEmirate("Abu Dhabi");
		memberRegPo.selectCity("Abu");
		memberRegPo.selectCountryOfBirth("India");
		memberRegPo.selectPlaceOfBirth("Bangalore");
		memberRegPo.selectNationality("India");
		memberRegPo.selectEconomicActivity("Others");
		memberRegPo.selectSubEconomicActivity("Others");
		memberRegPo.selectAirport("Abu Dhabi");
		memberRegPo.enterExpectedTurnover("75000");
		memberRegPo.enterExpectedTransactionCount("200");
		memberRegPo.clickSameAddressCheckbox();
		memberRegPo.enterRiskType("Natural Person");
		memberRegPo.enterProfession("Actor");
		memberRegPo.clickPageNext();
		//memberRegPo.clickProceed();
		memberRegPo.dobPopUpWarninng();
	}

	@Test
	public void TC_002_CORPORATE_MEMBER() {
		testCaseLog("Page Load Time : " + getPageLoadTime(driver) + " ms");
		testCaseLog("TC_001 - Corporate Member registration");
		loginPO.loginAs(USERNAME,PASSWORD);
		homepagePo.clickMemberRegistration();
		memberRegPo.selectMemberCategory(MemberTypes.CORPORATE);
		memberRegPo.selectCorporateIdentityType(IdentityTypes.TRADE_LISCENCE);
		memberRegPo.enterIdNumber("784198012345670");
		memberRegPo.enterIssueDate("29102024");
		memberRegPo.enterExpiryDate("05122024");
		memberRegPo.clickValidateIdentityDetails();
		memberRegPo.enterFullName(getFullName());
		memberRegPo.enterDateOfIncorporation("20042020");
		memberRegPo.enterMobileNumber("501234567"); //getRandomNumberBetween(500000000 , 600000000)  );
		memberRegPo.enterAddress1("Office 101, ABC Tower");
		memberRegPo.enterAddress2("Sheikh Zayed Road");
		memberRegPo.selectEmirate("Abu Dhabi");
		memberRegPo.selectCity("Abu");
		memberRegPo.enterEmail("contact@abc.ae");
		memberRegPo.enterZipcode("12345");
		memberRegPo.selectEconomicActivity("services");
        memberRegPo.selectSubEconomicActivity("Hotel");
		memberRegPo.selectAirport("Abu Dhabi");
		memberRegPo.enterExpectedTurnover("5000000");
		memberRegPo.enterExpectedTransactionCount("1200");
		//memberRegPo.clickSameAddressCheckbox();
		memberRegPo.enterPresentAddress1("ABC apartment");
		memberRegPo.enterRiskType("Natural Person");
		memberRegPo.enterUboCount(1);
		memberRegPo.clickPageNext();
       // memberRegPo.clickProceed();
	}

}

