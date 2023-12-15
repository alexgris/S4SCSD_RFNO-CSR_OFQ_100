package org.project;

import ax.generic.FileProcessing;
import com.pages.*;
import init.settings.SeleniumSetUp;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Set;

import static ax.generic.FileProcessing.deleteOld_Files;
import static ax.generic.Graphics_Screenshots.deleteFilesFromFolder;
import static ax.generic.Waitings.explicitWaitsUntilElementPresent;
import static ax.generic.Waitings.waitTillElementDetectedByProperty;


public class Test_Cases extends SeleniumSetUp {

    public Logger logger = Logger.getLogger(Test_Cases.class);

    JavascriptExecutor js = (JavascriptExecutor) driver;

    //global variable that stores the status of "Customer Role Check" popup as displayed/not displayed
    public static boolean popup_CustomerRoleCheck = false;

    //global variable that stores the status of "WARNING" (after clicking on "REVERSE" button) popup as displayed/not displayed
    public static boolean popup_Warning = false;


    //global variable for new Purchasing Order -> Item Overview
    public static String MatlGroup;
    public static String DelivDate;
    public static String DateCat;
    public static String OrderUnit;
    public static String ShortText;
    public static String ItemCategory;
    public static String Item;


    //global variable for "Item Category"
    public static String itemType;
    public static String doc_Num;
    public static String doc_NumReversed;
    public static String conf_SystemClient;
    public static String num_StandardOrder;
    public static String[][] beforeCSR_Analysis_ResultsList_Array;
    public static String[][] afterCSR_Analysis_ResultsList_Array;
    public static String[] beforeCSR_Analysis_Log_Array;
    public static String[] afterCSR_Analysis_Log_Array;
    public static Set<String> before_unique_DeliveryIDs;
    public static Set<String> after_unique_DeliveryIDs;
    public static Set<String> before_unique_DocNumbers;
    public static Set<String> after_unique_DocNumbers;
    public static boolean cell_Order = false;
    public static boolean cell_Mail = false;

    @BeforeSuite
    public void preTestSettinngs() throws Exception {

        //Delete previously created screenshots from .\src\main\resources\current_images folder
        deleteFilesFromFolder(".\\src\\main\\resources\\current_images");

        //Delete previously created screenshots from .\src\main\resources\error_images folder
        deleteFilesFromFolder(".\\src\\main\\resources\\error_images");

        //Delete previously created log4j files from /log_files_temp folder
        deleteFilesFromFolder(".\\src\\main\\resources\\log_files_temp");

    }


    @BeforeTest
    public void preTestConfigurations() throws Exception {

        //Delete previously created log4j files
        deleteOld_Files(".\\src\\main\\resources\\log_files", "LOG files from 'log_files' folder:");

        //Path to the Log4j logger config file
        PropertyConfigurator.configure(".\\src\\main\\resources\\log4j.properties");

        //Turn on logging level
        //Logger.getRootLogger().setLevel(Level.INFO);


    }



    @Test(priority = 0)
    @Parameters({"target_System_Client"})
    public void open_RFNO_CSR_Page_Testing(String target_System_Client) throws Exception {

        driver.manage().window().maximize();

       /*
        //change size of web-browser window
        Dimension d = new Dimension(1600,900);
        driver.manage().window().setSize(d);

        //move web-browser window on the screen
        driver.manage().window().setPosition(new Point(50,50));*/
        conf_SystemClient = target_System_Client;
        System.out.println("Current System: " + conf_SystemClient);


        //Select correct URL for a particular System/Client
        switch (target_System_Client) {

            //Open Data Collation page on OFQ/100
            case "OFQ_100":

                driver.navigate().to("https://ldciofq.wdf.sap.corp:44300/sap/bc/webdynpro/ico/rn_wa_csr_main?WDCONFIGURATIONID=%2fICO%2fRN_WA_CSR_MAIN_OVP&sap-client=100&sap-language=EN#");

                //wait till "Locator" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//*[contains (text(), 'Locator')]/ancestor::td[@class='urSpTPTD'][1]", "LOCATOR PANEL");

                //wait till "Group Settings" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (text(), 'Group Settings')]/ancestor::td[@class='urSpTPTD'][1]", "GROUP SETTINGS PANEL");

                //wait till "Page: CSR Groups" dropdown ges displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (@id, 'sapwd_main_window_root_')]/div[1]/table/tbody/tr[1]/descendant::div[contains (@class, 'lsToolbar--standards-rightItems')]/span[2]/descendant::input[contains (@value, 'CSR')]", "'PAGE: DROPDOWN'");
                Thread.sleep(1000);

                break;

            //Open Data Collation page on OGQ/100
            case "OGQ_100":

                driver.navigate().to("https://ldciogq.wdf.sap.corp:44300/sap/bc/webdynpro/ico/rn_wa_csr_main?WDCONFIGURATIONID=%2fICO%2fRN_WA_CSR_MAIN_OVP&sap-client=100&sap-language=EN#");

                //wait till "Locator" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//span[contains (text(), 'Locator')]/ancestor::td[@class='urSpTPTD'][1]", "LOCATOR PANEL");

                //wait till "Group Settings" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (text(), 'Group Settings')]/ancestor::td[@class='urSpTPTD'][1]", "GROUP SETTINGS PANEL");

                //wait till "Page: CSR Groups" dropdown ges displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (@id, 'sapwd_main_window_root_')]/div[1]/table/tbody/tr[1]/descendant::div[contains (@class, 'lsPageHeader--item urPgHITrans')]/div[1]/div[3]/span[2]/descendant::input[contains (@value, 'CSR')]", "'PAGE: DROPDOWN'");
                Thread.sleep(1000);

                break;
            default:
        }


        LandingPage landingPage = new LandingPage(driver); //Instantiating object of "LandingPage" class


        //click on "Personalize" button on the "CSR Groups" toolbar
        landingPage.click_PersonalizeBtn_CSR_GroupsPage();
        Thread.sleep(1000);

        //Select "Set to Default" menu item from "Personalize" dropdown on the "CSR Groups" toolbar
        landingPage.click_SetToDefault_CSR_GroupPage();
        Thread.sleep(1000);

        //check whether the "Page: dropdown" has the value "CSR Groups" and if not then open the dropdown and select "CSR Groups" value
        if (landingPage.check_PageDropdownValue("CSR Groups") == false) {

            landingPage.click_PageDropdown();
            Thread.sleep(1000);

            landingPage.select_CSR_Groups_value();


            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
                //wait till "Locator" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//*[contains (text(), 'Locator')]/ancestor::td[@class='urSpTPTD'][1]", "LOCATOR PANEL");

                //wait till "Group Settings" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//*[contains (text(), 'Group Settings')]/ancestor::td[@class='urSpTPTD'][1]", "GROUP SETTINGS PANEL");

                //wait till "Page: CSR Groups" dropdown ges displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (@id, 'sapwd_main_window_root_')]/div[1]/table/tbody/tr[1]/descendant::div[contains (@class, 'lsToolbar--standards-rightItems')]/span[2]/descendant::input[contains (@value, 'CSR Groups')]", "'PAGE: DROPDOWN'");
                Thread.sleep(1000);
            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //wait till "Locator" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//span[contains (text(), 'Locator')]/ancestor::td[@class='urSpTPTD'][1]", "LOCATOR PANEL");

                //wait till "Group Settings" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (text(), 'Group Settings')]/ancestor::td[@class='urSpTPTD'][1]", "GROUP SETTINGS PANEL");

                //wait till "Page: CSR Groups" dropdown ges displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (@id, 'sapwd_main_window_root_')]/div[1]/table/tbody/tr[1]/descendant::div[contains (@class, 'lsPageHeader--item urPgHITrans')]/div[1]/div[3]/span[2]/descendant::input[contains (@value, 'CSR')]", "'PAGE: DROPDOWN'");
                Thread.sleep(1000);
            }

        }


        //check whether "Group Settings" panel is expanded or collapsed
        landingPage.check_GroupSettingsPanel_Expanded();

        Thread.sleep(1000);

        //check that "Group Settings" tab is selected
        landingPage.check_GroupSettingsTab_Selected();
        Thread.sleep(1000);

    }


    @Test(dependsOnMethods = {"open_RFNO_CSR_Page_Testing"})
    @Parameters({"CSR_Group_ID"})
   public void findAndSelectGroupInLocator_Testing(String CSR_Group_ID) throws Exception {

        LandingPage landingPage = new LandingPage(driver); //Instantiating object of "LandingPage" class
        Locator_DocCompare docCompare = new Locator_DocCompare(driver); //Instantiating object of "Locator_DocCompare" class


        //confirm that the "Locator" panel is displayed
        if (!docCompare.checkLocatorPanelDisplayed()==true){

            //click on "LOCATOR" button
            docCompare.click_LocatorBtn_StatusCheck();

            //wait till "Locator" panel gets displayed
            explicitWaitsUntilElementPresent(40, "//div[contains (text(), 'Locator')]/ancestor::td[@class='urSpTPTD'][1]", "LOCATOR PANEL");
            Thread.sleep(1000);

        }


        //confirm that "Search Criteria" toggler is expanded
        docCompare.check_SearchCriteriaToggler_Expanded();

        //confirm that the first input field has "CSR Group ID" value selected by default
        if (!docCompare.check_InputFieldValue()==true){
            docCompare.fillOutAttributeSelectionInputWithCorrectValue();
        }
        Thread.sleep(1000);

        //Click on "Personalize" button in "Locator" panel
        docCompare.click_PersonalizeBtn_LocatorPanel();
        Thread.sleep(1000);

        //check whether the "Set to Default" button is disabled or enabled. If enabled them click on it to display all columns and set the columns' width. If it disabled then
        //simply set the columns' width
        docCompare.check_SetToDefaultBtn_PersonolizedPopup_LocatorPanel();
        Thread.sleep(1000);

        //Click on "Save" button on the "Personalization" popup (for "Locator" panel) and wait till the landing page gets downloaded again
        landingPage.click_SaveBtn();
        Thread.sleep(1000);

        //click on "Search" button and wait till groups are displayed in the lower table
        docCompare.click_SearchBtn_CSR();
        Thread.sleep(1000);

        //check if any of the results has the value "CSR - DNT" and click on it
        docCompare.select_GroupIDinSearchResults(CSR_Group_ID);

        //wait till "Start CSR Analysis" button gets enabled
        waitTillElementDetectedByProperty(15, "//div[contains (@id, 'sapwd_main_window_root')]/div[1]/table/tbody/tr[1]/descendant::div[contains (@class, 'lsToolbar--standards-leftItems')]/descendant::div[contains (@title, 'Start CSR Analysis')]", "class", "lsButton--focusable", "START CSR ANALYSIS button");
        Thread.sleep(1000);

    }


    @Test(dependsOnMethods = {"findAndSelectGroupInLocator_Testing"})
    public void prior_CSR_Analysis_Results_Testing() throws Exception {

        CSR_AnalysisResults CSR_Analysys = new CSR_AnalysisResults(driver); //Instantiating object of "CSR_AnalysisResults" class


        //click on "Start CSR Analysis" button
        CSR_Analysys.click_StartCSR_AnalysisBtn();

        //wait till the "Results of CSR Analysis" popup gets loaded
        CSR_Analysys.wait_ResultsOfCSR_AnalysisPopupLoads();

        //Click on "Personalize" button on the "Results of CSR Analysis" popup -> "Results List" table
        CSR_Analysys.click_PersonalizeBtn_ResultsOfCSR_Analysis();
        Thread.sleep(1000);

        //check whether the "Set to Default" button is disabled or enabled. If enabled them click on it to display all columns.
        CSR_Analysys.check_SetToDefaultBtn_PersonolizedPopup_ResultsOfCSR_Analysis();

        //Click on "Personalize" button on the "Results of CSR Analysis" popup -> "Results List" table
        CSR_Analysys.click_PersonalizeBtn_ResultsOfCSR_Log();
        Thread.sleep(1000);

        //check whether the "Set to Default" button is disabled or enabled. If enabled them click on it to display all columns.
        CSR_Analysys.check_SetToDefaultBtn_PersonolizedPopup_ResultsOfCSR_Log();
        Thread.sleep(1000);
    }


    @Test(dependsOnMethods = {"prior_CSR_Analysis_Results_Testing"})
    public void readAnalysisAndLogTables_beforeProcessing_Testing() throws Exception {

        CSR_AnalysisResults CSR_Analysys = new CSR_AnalysisResults(driver); //Instantiating object of "CSR_AnalysisResults" class

        //read all values from all rows for columns "Material", "Delivery ID", "Delivery Qty" in "Results List" table and save them in array
        CSR_Analysys.read_CSR_Analysis_ResultsList_Table();
        Thread.sleep(1000);

        //retrieve unique "DeliveryID"s
        CSR_Analysys.retrieve_uniqueMaterials_before();
        Thread.sleep(1000);

        //retrieve document numbers from the "Log" table
        CSR_Analysys.read_CSR_Analysis_Log_Table();
        Thread.sleep(1000);

        //retrieve unique "Document numbers" before processing
        CSR_Analysys.retrieve_uniqueDocNumbers_before();
        Thread.sleep(1000);

        if (before_unique_DeliveryIDs.size()!=before_unique_DocNumbers.size()) {
            logger.error("The number of DELIVERY IDs is not the same as the number of DOCUMENT NUMBERS: " + before_unique_DeliveryIDs.size() + " vs. " + before_unique_DocNumbers.size() + ", respectively.");
        }else{
            logger.info("The number of DELIVERY IDs is THE SAME as the number of DOCUMENT NUMBERS: " + before_unique_DeliveryIDs.size() + " vs. " + before_unique_DocNumbers.size() + ", AS EXPECTED.");
        }

        //click on the "Start Processing" button
        CSR_Analysys.click_StartProcessing_Btn();

        //wait till the "Postprocessing" popup gets loaded
        CSR_Analysys.wait_Postprocessing_popup();
        Thread.sleep(1000);

    }


    @Test(dependsOnMethods = {"readAnalysisAndLogTables_beforeProcessing_Testing"})
   public void make_Postprocessing_Testing() throws Exception {

        PostProcessing postProcess = new PostProcessing(driver); //Instantiating object of "PostProcessing" class

        //Click on "Personalize" button on "Postprocessing" popup
        postProcess.click_PersonalizeBtn_PostprocessingPanel();
        Thread.sleep(1000);

        //check whether the "Set to Default" button is disabled or enabled. If enabled them click on it to display all columns and set the columns' width.
        postProcess.check_SetToDefaultBtn_PersonolizedPopup_PostprocessingPanel();
        Thread.sleep(1000);

        //Click on "Save" button on the "Personalization" popup and wait till the "Postprocessing" panel gets downloaded again
        postProcess.click_SaveBtn();
        Thread.sleep(1000);

        //check that "ORDER" and "MAIL" options are displayed in the "Postprocessing Selection" table
        postProcess.read_PostprocessingSelection_Table();
        Thread.sleep(1000);

        if(cell_Order==true){
            if(cell_Mail==true){
                logger.info("Both 'ORDER' and 'MAIL' rows were SUCCESSFULLY found as SELECTED in the POSTPROCESSING SELECTION table on the POSTPROCESSING popup.");
            }else{
                logger.warn("Only 'ORDER' row was found as SELECTED in the POSTPROCESSING SELECTION table on the POSTPROCESSING popup.");
            }
        }else{
            if(cell_Mail==true){
                logger.info("Only 'MAIL' row was found as SELECTED in the POSTPROCESSING SELECTION table on the POSTPROCESSING popup.");
            }else{
                logger.warn("Neither 'ORDER' nor 'MAIL' rows were found as SELECTED in the POSTPROCESSING SELECTION table on the POSTPROCESSING popup.");
            }
        }


        //check whether the "Execute Postprocessing" button is enabled and click on it
        postProcess.click_ExecutePostprocessing_Button();
        Thread.sleep(2000);

        //click on "Close" button on the "Postprocessing" popup
        postProcess.click_CloseButton_PostprocessingPopup();
        Thread.sleep(2000);

    }


    @Test(dependsOnMethods = {"make_Postprocessing_Testing"})
    public void finalize_CompareNewDocumentsWithOld_Testing() throws Exception {

        ComparePostprocessingResults Postprocessing = new ComparePostprocessingResults(driver); //Instantiating object of "CSR_AnalysisResults" class
        //CSR_AnalysisResults CSR_Analysys = new CSR_AnalysisResults(driver); //Instantiating object of "CSR_AnalysisResults" class

        //read all values from all rows for columns "Material", "Delivery ID", "Delivery Qty" in "Results List" table and save them in array
        Postprocessing.read_CSR_Analysis_ResultsList_Table_AfterProcessing();
        Thread.sleep(1000);

        //retrieve unique "DeliveryID"s
        Postprocessing.retrieve_uniqueMaterials_after();
        Thread.sleep(1000);

        //retrieve document numbers from the "Log" table
        Postprocessing.read_CSR_Analysis_Log_Table_AfterProcessing();
        Thread.sleep(1000);

        //retrieve unique "Document numbers" after processing
        Postprocessing.retrieve_uniqueDocNumbers_after();
        Thread.sleep(1000);

        if (before_unique_DeliveryIDs.size()!=after_unique_DeliveryIDs.size()) {
            logger.error("The number of UNIQUE DELIVERY IDs before and after POSTPROCESSING is NOT THE SAME: " + before_unique_DeliveryIDs.size() + " vs. " + after_unique_DeliveryIDs.size() + ", respectively.");


        }else{
            logger.info("The number of UNIQUE DELIVERY IDs before and after POSTPROCESSING is THE SAME: " + before_unique_DeliveryIDs.size() + " vs. " + after_unique_DeliveryIDs.size() + ", AS EXPECTED.");
        }



        if (before_unique_DocNumbers.size()!=after_unique_DocNumbers.size()) {
            logger.error("The number of UNIQUE DOCUMENTS before and after POSTPROCESSING is NOT THE SAME: " + before_unique_DocNumbers.size() + " vs. " + after_unique_DocNumbers.size() + ", respectively.");
        }else{
            logger.info("The number of UNIQUE DOCUMENTS before and after POSTPROCESSING is THE SAME: " + before_unique_DocNumbers.size() + " vs. " + after_unique_DocNumbers.size() + ", AS EXPECTED.");

            if (before_unique_DocNumbers.containsAll(after_unique_DocNumbers)) {
                System.out.println("The UNIQUE DOCUMENTS before and after processing are THE SAME.");
                logger.info("The UNIQUE DOCUMENTS before and after processing are THE SAME.");
            } else {
                if(before_unique_DocNumbers.size()>after_unique_DocNumbers.size()) {
                    before_unique_DocNumbers.removeAll(after_unique_DocNumbers);
                    System.out.println("BEFORE DOCs bigger than AFTER DOCs.");

                    for (String value : Test_Cases.before_unique_DocNumbers) {

                        System.out.println("REDUNDENT Values from BEFORE DOCs list': " + value);

                        logger.info("REDUNDENT Values from BEFORE DOCs list': " + value);
                    }

                }else{
                    after_unique_DocNumbers.removeAll(before_unique_DocNumbers);
                    System.out.println("AFTER DOCs bigger than BEFORE DOCs.");

                    for (String value : Test_Cases.after_unique_DocNumbers) {

                        System.out.println("REDUNDENT Values from AFTER DOCs list': " + value);

                        logger.info("REDUNDENT Values from AFTER DOCs list': " + value);
                    }

                }
            }
        }



        if (after_unique_DeliveryIDs.size()!=after_unique_DocNumbers.size()) {
            logger.error("The number of UNIQUE DELIVERY IDs is not the same as the number of UNIQUE DOCUMENT NUMBERS after POSTPROCESSING: " + after_unique_DeliveryIDs.size() + " vs. " + after_unique_DocNumbers.size() + ", respectively.");
        }else{
            logger.info("The number of UNIQUE DELIVERY IDs is THE SAME as the number of UNIQUE DOCUMENT NUMBERS after POSTPROCESSING: " + after_unique_DeliveryIDs.size() + " vs. " + after_unique_DocNumbers.size() + ", AS EXPECTED.");
        }


        //click on "Close" button on the "Results of CSR Analysis" popup
        Postprocessing.click_Close_Btn();

        Thread.sleep(8000);


    }



    @AfterTest
    @Parameters({"item_Type_1", "item_Type_2"})
    public void insertLinksIntoLogFile(String item_Type_1, String item_Type_2) {

        FileProcessing fileProcessing = new FileProcessing(); //Instantiating object of "FileProcessing" class


        //create a separate log file for every Item Category
        try {
            fileProcessing.renameLogHTMLfile("RFNO_CSR");
        } catch (IOException e) {
            logger.error("Unable to run the 'fileProcessing.renameLogHTMLfile(itemType)' method: " + e.getMessage());
        }

    }

}
