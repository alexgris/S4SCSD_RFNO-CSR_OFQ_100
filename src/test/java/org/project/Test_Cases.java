package org.project;

import ax.generic.FileProcessing;
import com.pages.*;
import init.settings.SeleniumSetUp;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;

import static ax.generic.FileProcessing.deleteOld_Files;
import static ax.generic.FileProcessing.insertingScreenshotLinksIntoLoggerHTMLfile;
import static ax.generic.Graphics_Screenshots.checkFileExists;
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
    public static String doc_Num_RCS;
    public static String doc_NumReversed;
    public static String conf_SystemClient;
    public static String num_StandardOrder;
    public static String input_MaterialDescription;
    public static String input_BaseUnitOfMeasure;
    public static String input_ReferenceValue;
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
                explicitWaitsUntilElementPresent(40, "//span[contains (text(), 'Locator')]/ancestor::td[@class='urSpTPTD'][1]", "LOCATOR PANEL");

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
                explicitWaitsUntilElementPresent(40, "//span[contains (text(), 'Locator')]/ancestor::td[@class='urSpTPTD'][1]", "LOCATOR PANEL");

                //wait till "Group Settings" panel gets displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (text(), 'Group Settings')]/ancestor::td[@class='urSpTPTD'][1]", "GROUP SETTINGS PANEL");

                //wait till "Page: CSR Groups" dropdown ges displayed
                explicitWaitsUntilElementPresent(40, "//div[contains (@id, 'sapwd_main_window_root_')]/div[1]/table/tbody/tr[1]/descendant::div[contains (@class, 'lsToolbar--standards-rightItems')]/span[2]/descendant::input[contains (@value, 'CSR')]", "'PAGE: DROPDOWN'");
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
            explicitWaitsUntilElementPresent(40, "//span[contains (text(), 'Locator')]/ancestor::td[@class='urSpTPTD'][1]", "LOCATOR PANEL");
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

        //inserting "DATA COLLATION" empty page screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\" + item_Type_1.toUpperCase() + "-Data_Collation_Page_Empty_FullSize.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " EMPTY DATA COLLATION PAGE window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\" + item_Type_1.toUpperCase() + "-Data_Collation_Page_Empty_FullSize.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_EMPTY_DATA_COLLATION_PAGE</a>";
                String endText = "window screenshot was made.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);

                //logger.info("URL Link for Empty DATA COLLATION page was successfully added to the 'log4j-application.html' file");
            }

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for " + item_Type_1.toUpperCase() + " Empty DATA COLLATION page: " + e.getMessage());
        }


        //inserting "FILLED OUT PURCHASING ORDER PAGE" page screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\Purchasing_Order_Page_FilledOut_FullSize.png")) {
                String tdLineBeforeChange = "FILLED OUT PURCHASING ORDER PAGE window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\Purchasing_Order_Page_FilledOut_FullSize.png").toRealPath().toUri().toURL() + ">FILLED OUT PURCHASING ORDER PAGE</a>";
                String endText = "window screenshot was made.";

                //inserting "FILLED OUT PURCHASING ORDER PAGE" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);

                //logger.info("URL Link for Empty DATA COLLATION page was successfully added to the 'log4j-application.html' file");
            }

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for FILLED OUT PURCHASING ORDER PAGE page: " + e.getMessage());
        }


        //inserting "DATA COLLATION with ERROR(s)" empty page screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Data_Collation_Page_Empty_FullSize_Error.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " The first record in the 'ADD ITEM' table is detected as editable. However, it is expected to be empty after saving PERSONOLIZED settings.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Data_Collation_Page_Empty_FullSize_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + " The first record in the 'ADD ITEM' table</a>";
                String endText = " is detected as editable. However, it is expected to be empty after saving PERSONOLIZED settings.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for Empty DATA COLLATION page with ERROR: " + e.getMessage());
        }


        //inserting "PARTNER SELECTION" popup with 1 row screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\PARTNER_SELECTION_POPUP-WithOneRow_Error.png")) {
                String tdLineBeforeChange = "The 'PARTNER SELECTION' popup has only 1 row. However, 2 rows are expected.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\PARTNER_SELECTION_POPUP-WithOneRow_Error.png").toRealPath().toUri().toURL() + ">The 'PARTNER SELECTION' popup has only 1 row.</a>";
                String endText = " However, 2 rows are expected.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for PARTNER SELECTION popup with 1 row: " + e.getMessage());
        }


        //inserting "CREATE PURCHASE ORDER Items Overview Table NotDisplayed" screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\CREATE_PURCHASE_ORDER-ItemOverview_Table_NotDisplayed_FullSize_Error.png")) {
                String tdLineBeforeChange = "UNABLE to open ITEMS OVERVIEW table for data input!";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\CREATE_PURCHASE_ORDER-ItemOverview_Table_NotDisplayed_FullSize_Error.png").toRealPath().toUri().toURL() + ">UNABLE to open ITEMS OVERVIEW table </a>";
                String endText = "for data input!";

                //inserting "CREATE PURCHASE ORDER Items Overview Table NotDisplayed" page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'CREATE PURCHASE ORDER page failed to display Item Overview Table' page with ERROR: " + e.getMessage());
        }


        //inserting "CREATE_STANDARD_ORDER-FooterText_Incorrect_FullSize_Error" screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-FooterText_Incorrect_FullSize_Error.png")) {
                String tdLineBeforeChange = "The footer text status before entering a value into 'Cust.' field is wrong!";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-FooterText_Incorrect_FullSize_Error.png").toRealPath().toUri().toURL() + ">The footer text status before entering a value into 'Cust.' field </a>";
                String endText = "is wrong!";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'CREATE PURCHASE ORDER page failed to display Header Table' page with ERROR: " + e.getMessage());
        }

        //inserting "Button 'NEW' is disabled or not found!" screenshot link into "log4j-application.html" file for STO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-New_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " Button 'NEW' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-New_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_Button_'NEW'</a>";
                String endText = "is in DISABLED status.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'NEW' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'NEW' is disabled or not found!" screenshot link into "log4j-application.html" file for STO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-New_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'NEW' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-New_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'NEW'</a>";
                String endText = "is in DISABLED status.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'NEW' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'ADD ITEM' is disabled or not found!" screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-AddItem_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " Button 'ADD ITEM' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-AddItem_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_Button_'ADD_ITEM'</a>";
                String endText = "is in DISABLED status.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'ADD ITEM' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'ADD ITEM' is disabled or not found!" screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-AddItem_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'ADD ITEM' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-AddItem_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'ADD_ITEM'</a>";
                String endText = "is in DISABLED status.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'ADD ITEM' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'CHECK' is disabled or not found!" screenshot link into "log4j-application.html" file for CSO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Check_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " Button 'CHECK' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Check_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_Button_'CHECK'</a>";
                String endText = "is in DISABLED status.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'CHECK' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'CHECK' is disabled or not found!" screenshot link into "log4j-application.html" file for RCS
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Check_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'CHECK' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Check_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'CHECK'</a>";
                String endText = "is in DISABLED status.";

                //inserting "DATA COLLATION" empty page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }

            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'CHECK' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'RELEASE' is disabled or not found!" screenshot link into "log4j-application.html" file for CSO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Release_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " Button 'RELEASE' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "Release_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_Button_'RELEASE'</a>";
                String endText = "is in DISABLED status.";

                //inserting "RELEASE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'RELEASE' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'RELEASE' is disabled or not found!" screenshot link into "log4j-application.html" file for RCS
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Release_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'RELEASE' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "Release_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'RELEASE'</a>";
                String endText = "is in DISABLED status.";

                //inserting "RELEASE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'RELEASE' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'SAVE' is disabled or not found!" screenshot link into "log4j-application.html" file for CSO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Save_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " Button 'SAVE' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Save_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_Button_'SAVE'</a>";
                String endText = "is in DISABLED status.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'SAVE' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'SAVE' is disabled or not found!" screenshot link into "log4j-application.html" file for RCS
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Save_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'SAVE' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Save_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'SAVE'</a>";
                String endText = "is in DISABLED status.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'SAVE' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'PROCESS' is disabled or not found!" screenshot link into "log4j-application.html" file for CSO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Process_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " Button 'PROCESS' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Process_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_Button_'PROCESS'</a>";
                String endText = "is in DISABLED status.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'PROCESS' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'PROCESS' is disabled or not found!" screenshot link into "log4j-application.html" file for RCS
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Process_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'PROCESS' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Process_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'PROCESS'</a>";
                String endText = "is in DISABLED status.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'PROCESS' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "DATA COLLATION" completed page screenshot link into "log4j-application.html" file for CSO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\" + item_Type_1.toUpperCase() + "-Data_Collation_Page_Completed_FullSize.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " COMPLETED DATA COLLATION PAGE window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\" + item_Type_1.toUpperCase() + "-Data_Collation_Page_Completed_FullSize.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_COMPLETED-DATA-COLLATION-PAGE</a>";
                String endText = "window screenshot was made.";

                //inserting "DATA COLLATION" completed page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);

                //logger.info("URL Link for Empty DATA COLLATION page was successfully added to the 'log4j-application.html' file");
            }

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for Completed DATA COLLATION page: " + e.getMessage());
        }


        //inserting "DATA COLLATION" completed page screenshot link into "log4j-application.html" file for RCS
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-Data_Collation_Page_Completed_FullSize.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " COMPLETED DATA COLLATION PAGE window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-Data_Collation_Page_Completed_FullSize.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_COMPLETED-DATA-COLLATION-PAGE</a>";
                String endText = "window screenshot was made.";

                //inserting "DATA COLLATION" completed page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);

                //logger.info("URL Link for Empty DATA COLLATION page was successfully added to the 'log4j-application.html' file");
            }

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for Completed DATA COLLATION page: " + e.getMessage());
        }


        //inserting "Button 'REVERSE' is disabled or not found!" screenshot link into "log4j-application.html" file for STO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Reverse_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'REVERSE' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Reverse_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'REVERSE'</a>";
                String endText = "is in DISABLED status.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'REVERSE' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "REVERSED DOCUMENT PAGE" completed page screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-Reversed_Document_Page_Completed_FullSize.png")) {
                String tdLineBeforeChange = "REVERSED DOCUMENT PAGE window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-Reversed_Document_Page_Completed_FullSize.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_REVERSED_DOCUMENT_PAGE</a>";
                String endText = "window screenshot was made.";

                //inserting "REVERSED DOCUMENT PAGE" completed page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);

                //logger.info("URL Link for Empty DATA COLLATION page was successfully added to the 'log4j-application.html' file");
            }

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for REVERSED DOCUMENT page: " + e.getMessage());
        }

        //inserting "PROCESSED REVERSED DOCUMENT PAGE" completed page screenshot link into "log4j-application.html" file for XST
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-ReProcessed_Document_Page_FullSize.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " REPROCESSED DOCUMENT PAGE window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-ReProcessed_Document_Page_FullSize.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_REPROCESSED_DOCUMENT_PAGE</a>";
                String endText = "window screenshot was made.";

                //inserting "REVERSED DOCUMENT PAGE" completed page screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);

                //logger.info("URL Link for Empty DATA COLLATION page was successfully added to the 'log4j-application.html' file");
            }

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for REPROCESSED DOCUMENT PAGE page: " + e.getMessage());
        }


        //inserting "Button 'LOCATOR' is disabled or not found!" screenshot link into "log4j-application.html" file for CSO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Locator_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " Button 'LOCATOR' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_1.toUpperCase() + "-Locator_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_Button_'LOCATOR'</a>";
                String endText = "is in DISABLED status.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'LOCATOR' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "Button 'LOCATOR' is disabled or not found!" screenshot link into "log4j-application.html" file for RCS
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Locator_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'LOCATOR' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Locator_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'LOCATOR'</a>";
                String endText = "is in DISABLED status.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'LOCATOR' button is disabled or not found ERROR: " + e.getMessage());
        }



        //inserting "Button 'LOCATOR' is disabled or not found!" screenshot link into "log4j-application.html" file for XST
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Locator_Btn_Disabled_NotFound_Error.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " Button 'LOCATOR' is in DISABLED status.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\" + item_Type_2.toUpperCase() + "-Locator_Btn_Disabled_NotFound_Error.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_Button_'LOCATOR'</a>";
                String endText = "is in DISABLED status.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'LOCATOR' button is disabled or not found ERROR: " + e.getMessage());
        }


        //inserting "LOCATOR" screenshot link into "log4j-application.html" file for CSO
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\" + item_Type_1.toUpperCase() + "-Locator_Panel_DocStatus.png")) {
                String tdLineBeforeChange = item_Type_1.toUpperCase() + " LOCATOR PANEL WITH DOCUMENT STATUS window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\" + item_Type_1.toUpperCase() + "-Locator_Panel_DocStatus.png").toRealPath().toUri().toURL() + ">" + item_Type_1.toUpperCase() + "_LOCATOR PANEL WITH DOCUMENT STATUS'</a>";
                String endText = "window screenshot was made.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for LOCATOR PANEL WITH DOCUMENT STATUS page: " + e.getMessage());
        }


        //inserting "LOCATOR" screenshot link into "log4j-application.html" file for RCS
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-Locator_Panel_DocStatus.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " LOCATOR PANEL WITH DOCUMENT STATUS window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-Locator_Panel_DocStatus.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_LOCATOR PANEL WITH DOCUMENT STATUS'</a>";
                String endText = "window screenshot was made.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for LOCATOR PANEL WITH DOCUMENT STATUS page: " + e.getMessage());
        }


        //inserting "BILL-TO flag does NOT have green color" screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-BILL-TO_Flag_IsNotGreen_FullSize_Error.png")) {
                String tdLineBeforeChange = "The BILL-TO flag does NOT have green color!";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-BILL-TO_Flag_IsNotGreen_FullSize_Error.png").toRealPath().toUri().toURL() + ">The BILL-TO flag </a>";
                String endText = "does NOT have green color!";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'BILL-TO flag does NOT have green color': " + e.getMessage());
        }


        //inserting "SHIP-TO flag does NOT have green color" screenshot link into "log4j-application.html" file
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-SHIP-TO_Flag_IsNotGreen_FullSize_Error.png")) {
                String tdLineBeforeChange = "The SHIP-TO flag does NOT have green color!";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-SHIP-TO_Flag_IsNotGreen_FullSize_Error.png").toRealPath().toUri().toURL() + ">The SHIP-TO flag </a>";
                String endText = "does NOT have green color!";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'SHIP-TO flag does NOT have green color': " + e.getMessage());
        }


            //inserting "The message in page footer is FAILURE!" screenshot link into "log4j-application.html" file
            try {

                //first check if the file was created and saved in the directory
                if (checkFileExists(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-AFTER_ADDING_ITEM-FAILURE_MESSAGE_FullSize_Error.png")) {
                    String tdLineBeforeChange = "The message in page footer is FAILURE!";
                    String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-AFTER_ADDING_ITEM-FAILURE_MESSAGE_FullSize_Error.png").toRealPath().toUri().toURL() + ">The message </a>";
                    String endText = "in page footer is FAILURE!";

                    //inserting "SAVE" button screenshot links into "log4j-application.html" file
                    insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
                }
                //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

            } catch (IOException e) {
                logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'FAILURE during creation of Standard Order': " + e.getMessage());
            }




            //inserting "The message in page footer is FAILURE after saving the order!" screenshot link into "log4j-application.html" file
            try {

                //first check if the file was created and saved in the directory
                if (checkFileExists(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-AFTER_SAVING_FullSize_Error.png")) {
                    String tdLineBeforeChange = "The text-message in page footer is FAILURE after saving the order!";
                    String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\error_images\\CREATE_STANDARD_ORDER-AFTER_SAVING_FullSize_Error.png").toRealPath().toUri().toURL() + ">The text-message </a>";
                    String endText = "in page footer is FAILURE after saving the order!";

                    //inserting "SAVE" button screenshot links into "log4j-application.html" file
                    insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
                }
                //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

            } catch (IOException e) {
                logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for 'FAILURE after saving standard order': " + e.getMessage());
            }



            //inserting "LOCATOR" screenshot link into "log4j-application.html" file for XST
        try {

            //first check if the file was created and saved in the directory
            if (checkFileExists(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-Locator_Panel_DocStatus.png")) {
                String tdLineBeforeChange = item_Type_2.toUpperCase() + " LOCATOR PANEL WITH DOCUMENT STATUS window screenshot was made.";
                String baselineScreen = "<a href=" + Paths.get(".\\src\\main\\resources\\current_images\\" + item_Type_2.toUpperCase() + "-Locator_Panel_DocStatus.png").toRealPath().toUri().toURL() + ">" + item_Type_2.toUpperCase() + "_LOCATOR PANEL WITH DOCUMENT STATUS'</a>";
                String endText = "window screenshot was made.";

                //inserting "SAVE" button screenshot links into "log4j-application.html" file
                insertingScreenshotLinksIntoLoggerHTMLfile(tdLineBeforeChange, baselineScreen, endText);
            }
            //logger.info("URL Link for Empty DATA COLLATION page WITH ERROR was successfully added to the 'log4j-application.html' file");

        } catch (IOException e) {
            logger.error("Unable to run the 'insertingScreenshotLinksIntoLoggerHTMLfile' method for LOCATOR PANEL WITH DOCUMENT STATUS page: " + e.getMessage());
        }

    }


}
