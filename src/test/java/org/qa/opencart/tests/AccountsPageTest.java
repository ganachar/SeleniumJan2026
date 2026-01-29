package org.qa.opencart.tests;

import org.qa.opencart.base.BaseTest;
import org.qa.opencart.constants.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void accSetup(){
        acctsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    }

  @Test
   public void accPageTitleTest(){
       String actTitle = acctsPage.getAccountsPageTitle();
       System.out.println("Accounts Page Title : "+actTitle);
       Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
  }

    @Test
    public void accPageURLTest(){
        String actURL = acctsPage.getAccountsPageURL();
        System.out.println("Accounts Page URL : "+actURL);
        Assert.assertTrue(actURL.contains(Constants.ACCOUNTS_PAGE_URL));
    }

    @Test
    public void accPageSectionsTest(){
        List<String> actAccSecList = acctsPage.getAccountsPageSectionsList();
        System.out.println("Actual Accounts Section List "+actAccSecList);
        Assert.assertEquals(actAccSecList,Constants.EXPECTED_ACCOUNTS_SECTION_LIST);
    }

    @Test
    public void logoutLinkTest(){
        Assert.assertTrue(acctsPage.isLogoutLinkExists());
    }

    @Test
    public void SearchExistTest(){
        Assert.assertTrue(acctsPage.isSearchFieldExists());
    }

//    @Test(dependsOnMethods = "searchTest")
//    public void logoutTest(){
//        Assert.assertEquals(acctsPage.clickOnLogout().getLogoutSucessMsg(),Constants.LOGOUT_SUCCESS_MSG);
//    }

    @DataProvider
    public Object[][] getSearchKey(){

        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Apple"},
                {"Samsung"}
        };

    }

    @Test(dataProvider = "getSearchKey")
    public void searchTest(String searchKey){
        searchResPage = acctsPage.doSearch(searchKey);
        Assert.assertTrue(searchResPage.getSearchResultsCount() > 0);
    }

    @DataProvider
    public Object[][] getProductName(){

        return new Object[][]{
                {"Macbook","MacBook Pro"},
                {"iMac","iMac"},
                {"Apple","Apple Cinema 30\""},
                {"Samsung","Samsung SyncMaster 941BW"}
        };

    }
//    @DataProvider
//    public Object [][] getProductName(){
//        return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
//    }

    @Test(dataProvider = "getProductName")
    public void selectProductTest(String searchKey,String productName){
        searchResPage = acctsPage.doSearch(searchKey);
        productInfoPage = searchResPage.selectProduct(productName);
        String productHeader = productInfoPage.getProductHeaderName();
        System.out.println("Product header : "+productHeader);
        Assert.assertEquals(productHeader,productName);
    }

}
