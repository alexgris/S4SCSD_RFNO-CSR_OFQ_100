package com.pages;

import init.settings.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.project.Test_Cases;

import java.util.*;

import static ax.generic.Waitings.*;
import static org.project.Test_Cases.beforeCSR_Analysis_Log_Array;
import static org.project.Test_Cases.beforeCSR_Analysis_ResultsList_Array;

public class CSR_AnalysisResults extends PageObject {

    public Logger logger = Logger.getLogger(CSR_AnalysisResults.class);


    //Locating "Start CSR_Analysis" button's container
    @FindBy(xpath = "//div[contains (@id, 'sapwd_main_window_root')]/div[1]/table/tbody/tr[1]/descendant::div[contains (@class, 'lsToolbar--standards-leftItems')]/descendant::div[contains (@title, 'Start CSR Analysis')]")
    private WebElement btn_StartCSR_Analysis;

    //Locating "Personolize" button on "Results of CSR Analysis" popup (Results List)
    @FindBy(xpath = "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::table[contains (@class, 'urSTCS urST3WhlBrdH urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')][contains (@ct, 'STCS')]/thead/tr[2]/descendant::div[contains (@title, 'Personalize')]")
    private WebElement btn_Personalize_ResultsCSR_Analysis;

    //Locating "Personolize" button on "Results of CSR Analysis" popup (Log)
    @FindBy(xpath = "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::table[contains (@class, 'urSTCS urST3WhlBrdH urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')][contains (@ct, 'STCS')]/thead/tr[2]/descendant::div[contains (@title, 'Personalize')]")
    private WebElement btn_Personalize_ResultsCSR_Log;

    //Locating "Set to Default" button on "Personalization" popup for "Results of CSR Analysis" popup ("Results List" table)
    @FindBy(xpath = "//div[contains (text(), 'Personalization')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Reset to Default')]/ancestor::div[1]")
    private WebElement btn_SetToDefault_Persnlzd_ResultsOfCSR_Analysis;

    //Locating "Set to Default" button on "Personalization" popup for "Results of CSR Analysis" popup ("LOG" table)
    @FindBy(xpath = "//div[contains (text(), 'Personalization')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Reset to Default')]/ancestor::div[1]")
    private WebElement btn_SetToDefault_Persnlzd_ResultsOfCSR_Log;

    //Locating "Set to Default" button on "Personalization" popup for "Results of CSR Analysis" popup ("Results List" table)
    @FindBy(xpath = "//div[contains (text(), 'Personalization')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Save')]/ancestor::div[1]")
    private WebElement btn_Save_Persnlzd_ResultsOfCSR_Analysis;

    //Locating "Set to Default" button on "Personalization" popup for "Results of CSR Analysis" popup ("LOG" table)
    @FindBy(xpath = "//div[contains (text(), 'Personalization')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Save')]/ancestor::div[1]")
    private WebElement btn_Save_Persnlzd_ResultsOfCSR_Log;


    //Constructor
    public CSR_AnalysisResults(WebDriver driver) {
        super(driver);

    }

    JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions action = new Actions(driver);


    public void retrieve_uniqueDocNumbers_before() {

        List<String> dynDocs = new ArrayList<String>();

        try {

            System.out.println("Size of Array:" + beforeCSR_Analysis_Log_Array.length);


            //copy DOCUMENT NUMBERS from array into a list
            for (int c = 0; c < beforeCSR_Analysis_Log_Array.length; c++) {
                dynDocs.add(beforeCSR_Analysis_Log_Array[c]);
            }

            //read copied DOCUMENT NUMBERS from the list
            for (String item : dynDocs) {
                System.out.println("RETRIEVED 'DOCUMENT NUMBERs': " + item);
            }


            Test_Cases.before_unique_DocNumbers = new HashSet<>(dynDocs);
            for (String value : Test_Cases.before_unique_DocNumbers) {

                System.out.println("RETRIEVED UNIQUE 'DOCUMENT NUMBERS before processing': " + value);

                logger.info("RETRIEVED UNIQUE 'DOCUMENT NUMBERS before processing': " + value);
            }
        } catch (Exception e) {
            logger.warn("Unable to retrieve UNIQUE 'DOCUMENT NUMBERS before processing': " + e.getMessage());
        }

    }

    public void retrieve_uniqueMaterials_before() {

        List<String> MaterialIDs = new ArrayList<String>();

        try {

            System.out.println("Size of Array:" + beforeCSR_Analysis_ResultsList_Array[1].length);


            //copy DELIVERY IDs from array into a list
            for (int c = 0; c < beforeCSR_Analysis_ResultsList_Array[0].length; c++) {
                MaterialIDs.add(beforeCSR_Analysis_ResultsList_Array[0][c]);
            }

            //read copied DELIVERY IDs from the list
            for (String item : MaterialIDs) {
                System.out.println("RETRIEVED 'MATERIAL': " + item);
            }


            Test_Cases.before_unique_DeliveryIDs = new HashSet<>(MaterialIDs);
            for (String value : Test_Cases.before_unique_DeliveryIDs) {

                System.out.println("RETRIEVED UNIQUE 'MATERIALS before processing': " + value);

                logger.info("RETRIEVED UNIQUE 'MATERIALS before processing': " + value);
            }
        } catch (Exception e) {
            logger.warn("Unable to retrieve UNIQUE 'MATERIALS before processing': " + e.getMessage());
        }

    }

    public void read_CSR_Analysis_Log_Table() {

        int TbodySection = 1;
        int numberOfDefaultTbodySections = 0;
        int numberOfUpdatedTbodySections = 0;
        int TbodySection1 = 1;
        int numberOfDefaultTbodySections1 = 0;
        int numberOfUpdatedTbodySections1 = 0;
        int totalRowsLogMessagesTable = 0;
        int numMessageTextColumn = 0;
        int rowCountTextMessages = 0;
        int rowCountTextMessages1 = 0;

        numMessageTextColumn = return_MessageTextColumn_Number();


        try {

          //  if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                List<String> docNums = new ArrayList<String>();

                //scroll the table and count total rows to initialize array
                try {

                    WebElement scroller_DisplayedColumns = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--next')]/td/div"));

                    //check the number of TBODY elements displayed by default
                    List<WebElement> row_Blocks = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                    numberOfDefaultTbodySections1 = row_Blocks.size();
                    System.out.println("Number of TBODY tags in the 'RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table': " + numberOfDefaultTbodySections1);


                    //loop through every BODY section in LOG table
                    while (TbodySection1 <= numberOfDefaultTbodySections1) {


                        //address each TBODY section in LOG table via variable
                        WebElement elm_1_1 = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]"));
                        //System.out.println("Number of TBODY tags in 'DISPLAYED COLUMNS' section on PERSONALIZATION popup: " + elm_1.size());

                        //count rows in current TBODY section in LOG table
                        List<WebElement> elm_1_2 = elm_1_1.findElements(By.xpath("child::tr"));
                        System.out.println("Number of TR tags in TBODY [" + TbodySection1 + "] tag in the 'RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table': " + elm_1_2.size());

                        int rows_Number1 = elm_1_2.size();//total rows in current TBODY section

                        for (int i = 1; i <= rows_Number1; i++) {//iterate through all rows found in "TEXT MESSAGES" column in current TBODY section

                            //Find input field below the "MESSAGE TEXT" column title
                            //WebElement field_MessageText = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]/tr[" + i + "]/td[" +  numMessageTextColumn + "]/div/span"));

                            //Wait for the next row to appear in the "MESSAGE TEXT" column
                            waitTillElementDetected(15, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]/tr[" + i + "]/td[" +  numMessageTextColumn + "]/div/span", "NEXT DYNAMICALLY LOADED ROW in 'LOG MESSAGES' Table");

                            rowCountTextMessages1++;

                            //click vertical scroller to move the list one row up
                            //action.click(scroller_DisplayedColumns).perform();
                            js.executeScript("arguments[0].click();", scroller_DisplayedColumns);

                        }


                        //check whether new TBODY got displayed dynamically
                        List<WebElement> newDynamicTBODY = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                        numberOfUpdatedTbodySections1 = newDynamicTBODY.size();

                        if (numberOfUpdatedTbodySections1 > numberOfDefaultTbodySections1) {
                            numberOfDefaultTbodySections1 = numberOfUpdatedTbodySections1;
                            System.out.println("Number of TBODY sections was dynamically updated to [" + numberOfUpdatedTbodySections1 + "]");
                        }

                        TbodySection1++;

                    }

                    totalRowsLogMessagesTable = rowCountTextMessages1;

                    //count total number of records/rows in the "Results List" table
                    System.out.println("The TOTAL number of rows in LOG MESSAGES table is: " + totalRowsLogMessagesTable);


                    //scroll down the "LOG MESSAGES" table to display the rest of the rows
                    // WebElement belowRows = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[2]"));
                    WebElement belowRows2 = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td/div[2]"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);

                    WebElement panel_Height = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);
                    int height = panel_Height.getSize().getHeight();
                    System.out.println("The height of the vertical scroll bar in 'LOG MESSAGES' table is: " + height);

                    action.moveToElement(belowRows2).clickAndHold();
                    action.moveByOffset(0, -height);
                    action.release();
                    action.perform();

                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while scrolling and counting rows in LOG MESSAGES of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
                }

                //scroll the table and read required fields
                try {

                    WebElement scroller_DisplayedColumns = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--next')]/td/div"));

                    //check the number of TBODY elements displayed by default
                    List<WebElement> row_Blocks = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                    numberOfDefaultTbodySections = row_Blocks.size();
                    System.out.println("Number of TBODY tags in the 'RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table': " + numberOfDefaultTbodySections);


                    //loop through every BODY section in RESULTS table
                    while (TbodySection <= numberOfDefaultTbodySections) {

                        //address each TBODY section in RESULTS table via variable
                        WebElement elm_1_1 = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]"));
                        //System.out.println("Number of TBODY tags in 'DISPLAYED COLUMNS' section on PERSONALIZATION popup: " + elm_1.size());

                        //count rows in current TBODY section in RESULTS table
                        List<WebElement> elm_1_2 = elm_1_1.findElements(By.xpath("child::tr"));
                        System.out.println("Number of TR tags in TBODY [" + TbodySection + "] tag in the 'RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table': " + elm_1_2.size());

                        int rows_Number = elm_1_2.size();//total rows in current TBODY section
                        // totalRowsResultsListTable = totalRowsResultsListTable + rows_Number;


                        //check whether this column is within the screen boundaries and scroll to the column
                        // bringElementIntoViewHorizontally("Reference Document");


                        for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found in "Material" column in current TBODY section
                            //ColumnCount++; //column counter

                            //Wait for the next row to appear in the "MESSAGE TEXT" column
                            waitTillElementDetected(15, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" +  numMessageTextColumn + "]/div/span", "NEXT DYNAMICALLY LOADED ROW in 'LOG MESSAGES' Table");

                            //Find input field below the "Message Text" column title
                            WebElement field_MessageText = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" +  numMessageTextColumn + "]/div/span"));

                            String textValue = field_MessageText.getAttribute("innerText");
                            System.out.println("Value from MESSAGE TEXT column, row #" + i + " from BLOCK #" + TbodySection + " is:" + textValue);

                            String num_Order = textValue.replaceAll("[^0-9]+", "");

                            if (num_Order != null) {

                                if (num_Order.length() == 20) {
                                    docNums.add(num_Order);

                                    logger.info("DOCUMENT NUMBER was successfully retrieved from the LOG table. It is equal to '" + num_Order + "' ");
                                } else {
                                    logger.warn("Retrieved 'Document number' consists of " + num_Order.length() + " instead of expected 20 digits!");
                                }

                            } else {
                                logger.error("The 'Document number' (from the LOG table) is NULL. This means that the current cell does not contain document number!");
                            }

                            rowCountTextMessages++;


                            //click vertical scroller to move the list one row up
                            //action.click(scroller_DisplayedColumns).perform();
                            js.executeScript("arguments[0].click();", scroller_DisplayedColumns);

                        }


                        //check whether new TBODY got displayed dynamically
                        List<WebElement> newDynamicTBODY = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                        numberOfUpdatedTbodySections = newDynamicTBODY.size();

                        if (numberOfUpdatedTbodySections > numberOfDefaultTbodySections) {
                            numberOfDefaultTbodySections = numberOfUpdatedTbodySections;
                            System.out.println("Number of TBODY sections was dynamically updated to [" + numberOfUpdatedTbodySections + "]");
                        }

                        TbodySection++;

                    }

                } catch (NoSuchElementException e) {
                    logger.error("Error while scrolling and reading values from fields in LOG MESSAGES of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
                }

                //read copied DOCUMENT NUMBERS from the list
                        for (String item : docNums) {
                            System.out.println("Retrieved values from 'MESSAGE TEXT' column: " + item);
                        }

                        //copy list into array
                        beforeCSR_Analysis_Log_Array = new String[docNums.size()];
                        docNums.toArray(beforeCSR_Analysis_Log_Array);

                        //read the array with copied values
                        for (int i = 0; i < beforeCSR_Analysis_Log_Array.length; i++) {
                            System.out.println("Element at the index " + i + " is ::" + beforeCSR_Analysis_Log_Array[i]);
                        }

           // }


        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the LOG table of 'RESULTS OF CSR ANALYSIS' popup: " + e.getMessage());
        }
    }




    public void click_StartProcessing_Btn() {

        if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
            driver.switchTo().defaultContent();

            try {
                WebElement btn_StartProcessing = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::div[contains (@title, 'Start Postprocessing')]"));

                js.executeScript("arguments[0].click();", btn_StartProcessing);

                logger.info("The START PROCESSING button on the RESULTS OF CSR ANALYSIS popup was clicked SUCCESSFULLY.");

            } catch (Exception e) {
                logger.warn("Unable to click on the START PROCESSING button on the RESULTS OF CSR ANALYSIS popup: " + e.getMessage());
            }
        }

        if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
            //driver.switchTo().defaultContent();
            //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
            //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-0')]")));

            try {

                WebElement btn_StartProcessing = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::div[contains (@title, 'Start Postprocessing')]"));

                js.executeScript("arguments[0].click();", btn_StartProcessing);

                logger.info("The START PROCESSING button on the RESULTS OF CSR ANALYSIS popup was clicked SUCCESSFULLY.");

            } catch (Exception e) {
                logger.warn("Unable to click on the START PROCESSING button on the RESULTS OF CSR ANALYSIS popup: " + e.getMessage());
            }

           // driver.switchTo().defaultContent();
        }
    }


    public void read_CSR_Analysis_ResultsList_TableOLD() {

        int rowsCount_ResultsListTable = 0;
        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int blockCount = 0;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
                driver.switchTo().defaultContent();

                try {

                    //scroll down the "RESULTS LIST" table to display the rest of the rows
                    // WebElement belowRows = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[2]"));
                    WebElement belowRows2 = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td/div"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);

                    WebElement panel_Height = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);
                    int height = panel_Height.getSize().getHeight();

                    action.moveToElement(belowRows2).clickAndHold();
                    action.moveByOffset(0, height);
                    action.release();
                    action.perform();

                    waitTillElementsAppearByChildrenNumber(30, 1, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody", "Hidden rows in RESULTS LIST table on the RESULTS OF CSR ANALYSIS popup");
                    Thread.sleep(2000);

                    action.moveToElement(belowRows2).clickAndHold();
                    action.moveByOffset(0, -height / 2);
                    action.release();
                    action.perform();

                    Thread.sleep(2000);

                } catch (Exception e) {
                    logger.warn("Unable to scroll down the rows from RESULTS LIST table on the RESULTS OF CSR ANALYSIS popup: " + e.getMessage());
                }

                //count row blocks in the "Results List" table
                List<WebElement> row_Blocks = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                blockCount = row_Blocks.size();
                System.out.println("The number of row blocks in RESULTS LIST table is:" + blockCount);

                //count total number of records/rows in the "Results List" table
                for (int m = 1; m <= blockCount; m++) {
                    List<WebElement> rows_ResultsListTable = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + m + "]/child::tr"));
                    rowsCount_ResultsListTable = rowsCount_ResultsListTable + rows_ResultsListTable.size();

                }

                System.out.println("The TOTAL number of rows in RESULTS LIST table is:" + rowsCount_ResultsListTable);

                //initialize the "before" array
                beforeCSR_Analysis_ResultsList_Array = new String[3][rowsCount_ResultsListTable];

                //Get all columns in the RIGHT part of "Add Item" table
                List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-hdr')]/td[2]/div/div[2]/table/tbody/tr/child::th"));


                System.out.println("Number of columns in the RESULTS LIST table: " + available_ColumnsInRightPartOfTable.size());


                //Loop through all found coulmns
                for (WebElement element : available_ColumnsInRightPartOfTable) {
                    ColumnCount++; //column counter
                    if (neededColumns <= 3) {
                        System.out.println("Counter of Needed columns in the RESULTS LIST table: " + neededColumns);
                        try {//check if every column contains column title text
                            //scroll the field to be visible (if outside of vision portal) and click on it
                            //js.executeScript("arguments[0].scrollIntoView(true);", element);

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the RESULTS LIST table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Material")) {
                                if (!name_TableColumn.contains("Material description")) {
                                    name_TableColumn = "Material";
                                }
                            }

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Delivery ID")) {
                                name_TableColumn = "Delivery ID";
                            }

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Delivery Quantity")) {
                                name_TableColumn = "Delivery Quantity";
                            }


                            switch (name_TableColumn) {
                                // case "\n\nMaterial\n\n\n ":
                                case "Material":

                                    //check whether this column is within the screen boundaries and scroll to the column
                                    // bringElementIntoViewHorizontally("Reference Document");

                                    int rc1 = 0;

                                    for (int j = 1; j <= blockCount; j++) {//iterate through all found row blocks

                                        List<WebElement> rows_InCurrentBlock = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/child::tr"));

                                        int rows_Number = rows_InCurrentBlock.size();

                                        for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found in each row block
                                            //ColumnCount++; //column counter
                                            //Find input field below the "Material" column title
                                            WebElement field_Material = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/tr[" + i + "]/td[" + ColumnCount + "]/div/span/span"));

                                            String textValue = field_Material.getAttribute("innerText");
                                            System.out.println("Value from MATERIAL column, row #" + i + " from BLOCK #" + j + " is:" + textValue);

                                            beforeCSR_Analysis_ResultsList_Array[0][rc1] = textValue;//assign value into array

                                            rc1++;
                                        }
                                    }

                                    logger.info("Array has been filled in with MATERIALS values.");
                                    neededColumns++;

                                    break;

                                case "Delivery ID":

                                    //check whether this column is within the screen boundaries and scroll to the column
                                    // bringElementIntoViewHorizontally("Reference Document");

                                    int rc2 = 0;

                                    for (int j = 1; j <= blockCount; j++) {//iterate through all found row blocks

                                        List<WebElement> rows_InCurrentBlock = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/child::tr"));

                                        int rows_Number = rows_InCurrentBlock.size();

                                        for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found in each row block
                                            //ColumnCount++; //column counter
                                            //Find input field below the "DELIVERY ID" column title
                                            WebElement field_Material = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/tr[" + i + "]/td[" + ColumnCount + "]/div/span/span"));

                                            String textValue = field_Material.getAttribute("innerText");
                                            System.out.println("Value from DELIVERY ID column, row #" + i + " from BLOCK #" + j + " is:" + textValue);

                                            beforeCSR_Analysis_ResultsList_Array[1][rc2] = textValue;//assign value into array

                                            rc2++;
                                        }
                                    }

                                    logger.info("Array has been filled in with DELIVERY ID values.");
                                    neededColumns++;

                                    break;

                                case "Delivery Quantity":

                                    //check whether this column is within the screen boundaries and scroll to the column
                                    // bringElementIntoViewHorizontally("Reference Document");

                                    int rc3 = 0;

                                    for (int j = 1; j <= blockCount; j++) {//iterate through all found row blocks

                                        List<WebElement> rows_InCurrentBlock = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/child::tr"));

                                        int rows_Number = rows_InCurrentBlock.size();

                                        for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found in each row block
                                            //ColumnCount++; //column counter
                                            //Find input field below the "DELIVERY ID" column title
                                            WebElement field_Material = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/tr[" + i + "]/td[" + ColumnCount + "]/div/span/span"));

                                            String textValue = field_Material.getAttribute("innerText");
                                            System.out.println("Value from DELIVERY QUANTITY column, row #" + i + " from BLOCK #" + j + " is:" + textValue);

                                            beforeCSR_Analysis_ResultsList_Array[2][rc3] = textValue;//assign value into array

                                            rc3++;
                                        }
                                    }

                                    logger.info("Array has been filled in with DELIVERY QUANTITY values.");
                                    neededColumns++;

                                    break;

                                default:
                            }

                        }
                    } else {

                        System.out.println("Size of ARRAY: " + beforeCSR_Analysis_ResultsList_Array.length);
                        for (int r = 0; r < beforeCSR_Analysis_ResultsList_Array.length; r++) {
                            for (int c = 0; c < beforeCSR_Analysis_ResultsList_Array[r].length; c++) {
                                System.out.print(beforeCSR_Analysis_ResultsList_Array[r][c] + "\t");
                            }
                            System.out.println();
                        }


                        return;// break the cycle when all required fields have been filled in
                    }
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-0')]")));

                try {

                    //scroll down the "RESULTS LIST" table to display the rest of the rows
                    // WebElement belowRows = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[2]"));
                    WebElement belowRows2 = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td/div"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);

                    WebElement panel_Height = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);
                    int height = panel_Height.getSize().getHeight();

                    action.moveToElement(belowRows2).clickAndHold();
                    action.moveByOffset(0, height);
                    action.release();
                    action.perform();

                    waitTillElementsAppearByChildrenNumber(15, 1, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/descendant::tbody", "Hidden rows in RESULTS LIST table on the RESULTS OF CSR ANALYSIS popup");
                    // waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[1]/descendant::*", "Hidden rows in RESULTS LIST table on the RESULTS OF CSR ANALYSIS popup");

                    // waitTillDescendentElementsAvailable(30, "//span[contains (@title, 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/descendant::tbody", "Hidden rows in RESULTS LIST table on the RESULTS OF CSR ANALYSIS popup");

                    Thread.sleep(2000);

                    action.moveToElement(belowRows2).clickAndHold();
                    action.moveByOffset(0, -height / 2);
                    action.release();
                    action.perform();

                } catch (Exception e) {
                    logger.warn("Unable to scroll down the rows from RESULTS LIST table on the RESULTS OF CSR ANALYSIS popup: " + e.getMessage());
                }

                //count row blocks in the "Results List" table
                List<WebElement> row_Blocks = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                blockCount = row_Blocks.size();
                System.out.println("The number of row blocks in RESULTS LIST table is:" + blockCount);

                //count total number of records/rows in the "Results List" table
                for (int m = 0; m <= blockCount; m++) {
                    List<WebElement> rows_ResultsListTable = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + m + "]/child::tr"));
                    rowsCount_ResultsListTable = rowsCount_ResultsListTable + rows_ResultsListTable.size();

                }

                System.out.println("The TOTAL number of rows in RESULTS LIST table is:" + rowsCount_ResultsListTable);

                //initialize the "before" array
                beforeCSR_Analysis_ResultsList_Array = new String[3][rowsCount_ResultsListTable];

                //Get all columns in the "Results List" table
                List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-hdr')]/td[2]/div/div[2]/table/tbody/tr/child::th"));


                System.out.println("Number of columns in the RESULTS LIST table: " + available_ColumnsInRightPartOfTable.size());


                //Loop through all found columns
                for (WebElement element : available_ColumnsInRightPartOfTable) {
                    ColumnCount++; //column counter
                    if (neededColumns <= 3) {
                        System.out.println("Counter of Needed columns in the RESULTS LIST table: " + neededColumns);
                        try {//check if every column contains column title text
                            //scroll the field to be visible (if outside of vision portal) and click on it
                            //js.executeScript("arguments[0].scrollIntoView(true);", element);

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the RESULTS LIST table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Material")) {
                                if (!name_TableColumn.contains("Material description")) {
                                    name_TableColumn = "Material";
                                }
                            }

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Delivery ID")) {
                                name_TableColumn = "Delivery ID";
                            }

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Delivery Quantity")) {
                                name_TableColumn = "Delivery Quantity";
                            }


                            switch (name_TableColumn) {
                                // case "\n\nMaterial\n\n\n ":
                                case "Material":

                                    //check whether this column is within the screen boundaries and scroll to the column
                                    // bringElementIntoViewHorizontally("Reference Document");

                                    int rc1 = 0;

                                    for (int j = 1; j <= blockCount; j++) {//iterate through all found row blocks

                                        List<WebElement> rows_InCurrentBlock = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/child::tr"));

                                        int rows_Number = rows_InCurrentBlock.size();

                                        for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found in each row block
                                            //ColumnCount++; //column counter
                                            //Find input field below the "Material" column title
                                            WebElement field_Material = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/tr[" + i + "]/td[" + ColumnCount + "]/div/span"));

                                            String textValue = field_Material.getAttribute("innerText");
                                            System.out.println("Value from MATERIAL column, row #" + i + " from BLOCK #" + j + " is:" + textValue);

                                            beforeCSR_Analysis_ResultsList_Array[0][rc1] = textValue;//assign value into array

                                            rc1++;
                                        }
                                    }

                                    logger.info("Array has been filled in wit MATERIALS values.");
                                    neededColumns++;

                                    break;

                                case "Delivery ID":

                                    //check whether this column is within the screen boundaries and scroll to the column
                                    // bringElementIntoViewHorizontally("Reference Document");

                                    int rc2 = 0;

                                    for (int j = 1; j <= blockCount; j++) {//iterate through all found row blocks

                                        List<WebElement> rows_InCurrentBlock = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/child::tr"));

                                        int rows_Number = rows_InCurrentBlock.size();

                                        for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found in each row block
                                            //ColumnCount++; //column counter
                                            //Find input field below the "DELIVERY ID" column title
                                            WebElement field_Material = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/tr[" + i + "]/td[" + ColumnCount + "]/div/span"));

                                            String textValue = field_Material.getAttribute("innerText");
                                            System.out.println("Value from DELIVERY ID column, row #" + i + " from BLOCK #" + j + " is:" + textValue);

                                            beforeCSR_Analysis_ResultsList_Array[1][rc2] = textValue;//assign value into array

                                            rc2++;
                                        }
                                    }

                                    logger.info("Array has been filled in wit DELIVERY ID values.");
                                    neededColumns++;

                                    break;

                                case "Delivery Quantity":

                                    //check whether this column is within the screen boundaries and scroll to the column
                                    // bringElementIntoViewHorizontally("Reference Document");

                                    int rc3 = 0;

                                    for (int j = 1; j <= blockCount; j++) {//iterate through all found row blocks

                                        List<WebElement> rows_InCurrentBlock = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/child::tr"));

                                        int rows_Number = rows_InCurrentBlock.size();

                                        for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found in each row block
                                            //ColumnCount++; //column counter
                                            //Find input field below the "DELIVERY ID" column title
                                            WebElement field_Material = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/tr[" + i + "]/td[" + ColumnCount + "]/div/span"));

                                            String textValue = field_Material.getAttribute("innerText");
                                            System.out.println("Value from DELIVERY QUANTITY column, row #" + i + " from BLOCK #" + j + " is:" + textValue);

                                            beforeCSR_Analysis_ResultsList_Array[2][rc3] = textValue;//assign value into array

                                            rc3++;
                                        }
                                    }

                                    logger.info("Array has been filled in wit DELIVERY QUANTITY values.");
                                    neededColumns++;

                                    break;

                                default:
                            }

                        }
                    } else {

                        System.out.println("Size of ARRAY: " + beforeCSR_Analysis_ResultsList_Array.length);
                        for (int r = 0; r < beforeCSR_Analysis_ResultsList_Array.length; r++) {
                            for (int c = 0; c < beforeCSR_Analysis_ResultsList_Array[r].length; c++) {
                                System.out.print(beforeCSR_Analysis_ResultsList_Array[r][c] + "\t");
                            }
                            System.out.println();
                        }


                        return;// break the cycle when all required fields have been filled in
                    }
                }

                // driver.switchTo().defaultContent();
            }


        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
    }


    public void read_CSR_Analysis_ResultsList_Table() {

        int TbodySection = 1;
        int numberOfDefaultTbodySections = 0;
        int numberOfUpdatedTbodySections = 0;
        int TbodySection1 = 1;
        int numberOfDefaultTbodySections1 = 0;
        int numberOfUpdatedTbodySections1 = 0;
        int totalRowsResultsListTable = 0;
        int numMaterialColumn = 0;
        int numDeliveryIDColumn = 0;
        int numDeliveryQtyColumn = 0;
        int rowCountDeliveryQty = 0;
        int rowCountDeliveryQty1 = 0;

        numMaterialColumn = return_MaterialsColumn_Number();
        numDeliveryIDColumn = return_DeliveryIDColumn_Number();
        numDeliveryQtyColumn = return_DeliveryQTYColumn_Number();


        try {

                //scroll the table and count total rows to initialize array
                try {

                    WebElement scroller_DisplayedColumns = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--next')]/td/div"));

                    //check the number of TBODY elements displayed by default
                    List<WebElement> row_Blocks = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                    numberOfDefaultTbodySections1 = row_Blocks.size();


                    //loop through every BODY section in RESULTS table
                    while (TbodySection1 <= numberOfDefaultTbodySections1) {

                        //address each TBODY section in RESULTS table via variable
                        WebElement elm_1_1 = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]"));
                        //System.out.println("Number of TBODY tags in 'DISPLAYED COLUMNS' section on PERSONALIZATION popup: " + elm_1.size());

                        //count rows in current TBODY section in RESULTS table
                        List<WebElement> elm_1_2 = elm_1_1.findElements(By.xpath("descendant::tr"));
                        System.out.println("Number of TR tags in TBODY [" + TbodySection1 + "] tag in the 'RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table': " + elm_1_2.size());

                        int rows_Number1 = elm_1_2.size();//total rows in current TBODY section

                        for (int i = 1; i <= rows_Number1; i++) {//iterate through all rows found in "DELIVERY QUANTITY" column in current TBODY section

                            //Find input field below the "DELIVERY QUANTITY" column title
                            //WebElement field_DeliveryQty = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]/tr[" + i + "]/td[" + numDeliveryQtyColumn + "]/div/span"));

                            //Wait for the next row to appear in the "DELIVERY QUANTITY" column
                            waitTillElementDetected(15, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]/tr[" + i + "]/td[" + numDeliveryQtyColumn + "]/div/span", "NEXT DYNAMICALLY LOADED ROW in 'RESULTS LIST' Table");


                            rowCountDeliveryQty1++;

                            //click vertical scroller to move the list one row up
                            //action.click(scroller_DisplayedColumns).perform();
                            js.executeScript("arguments[0].click();", scroller_DisplayedColumns);

                        }


                        //check whether new TBODY got displayed dynamically
                        List<WebElement> newDynamicTBODY = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                        numberOfUpdatedTbodySections1 = newDynamicTBODY.size();

                        if (numberOfUpdatedTbodySections1 > numberOfDefaultTbodySections1) {
                            numberOfDefaultTbodySections1 = numberOfUpdatedTbodySections1;
                            System.out.println("Number of TBODY sections was dynamically updated to [" + numberOfUpdatedTbodySections1 + "]");
                        }

                        TbodySection1++;
                    }

                    totalRowsResultsListTable = rowCountDeliveryQty1;

                    //count total number of records/rows in the "Results List" table
                    System.out.println("The TOTAL number of rows in RESULTS LIST table is: " + totalRowsResultsListTable);


                    //scroll down the "RESULTS LIST" table to display the rest of the rows
                    // WebElement belowRows = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[2]"));
                    WebElement belowRows2 = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td/div[2]"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);

                    WebElement panel_Height = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);
                    int height = panel_Height.getSize().getHeight();
                    System.out.println("The height of the vertical scroll bar in 'RESULTS LIST' table is: " + height);

                    action.moveToElement(belowRows2).clickAndHold();
                    action.moveByOffset(0, -height);
                    action.release();
                    action.perform();

                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while scrolling and counting rows in RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
                }

                //scroll the table and read required fields
                try {

                    //initialize the "before" array
                    beforeCSR_Analysis_ResultsList_Array = new String[3][totalRowsResultsListTable];

                    WebElement scroller_DisplayedColumns = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--next')]/td/div"));

                    //check the number of TBODY elements displayed by default
                    List<WebElement> row_Blocks = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                    numberOfDefaultTbodySections = row_Blocks.size();

                    System.out.println("The number of default row blocks in RESULTS LIST table is: " + numberOfDefaultTbodySections);

                    int rc1 = 0;//array size parameter

                    //loop through every BODY section in RESULTS table
                    while (TbodySection <= numberOfDefaultTbodySections) {

                        //address each TBODY section in RESULTS table via variable
                        WebElement elm_1_1 = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]"));
                        //System.out.println("Number of TBODY tags in 'DISPLAYED COLUMNS' section on PERSONALIZATION popup: " + elm_1.size());

                        //count rows in current TBODY section in RESULTS table
                        List<WebElement> elm_1_2 = elm_1_1.findElements(By.xpath("descendant::tr"));
                        System.out.println("Number of TR tags in TBODY [" + TbodySection + "] tag in the 'RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table': " + elm_1_2.size());

                        int rows_Number = elm_1_2.size();//total rows in current TBODY section
                        // totalRowsResultsListTable = totalRowsResultsListTable + rows_Number;


                        //check whether this column is within the screen boundaries and scroll to the column
                        // bringElementIntoViewHorizontally("Reference Document");


                        for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found in "Material" column in current TBODY section
                            //ColumnCount++; //column counter
                            //Find input field below the "Material" column title
                            WebElement field_Material = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" + numMaterialColumn + "]/div/span"));

                            String textValue = field_Material.getAttribute("innerText");
                            System.out.println("Value from MATERIAL column, row #" + i + " from BLOCK #" + TbodySection + " is:" + textValue);

                            beforeCSR_Analysis_ResultsList_Array[0][rc1] = textValue;//assign value into array

                            //Find input field below the "DELIVERY ID" column title
                            WebElement field_DeliveryID = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" + numDeliveryIDColumn + "]/div/span"));

                            String textValue2 = field_DeliveryID.getAttribute("innerText");
                            System.out.println("Value from DELIVERY ID column, row #" + i + " from BLOCK #" + TbodySection + " is:" + textValue2);

                            beforeCSR_Analysis_ResultsList_Array[1][rc1] = textValue2;//assign value into array


                            //Find input field below the "DELIVERY QUANTITY" column title
                            WebElement field_DeliveryQty = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" + numDeliveryQtyColumn + "]/div/span"));

                            String textValue3 = field_DeliveryQty.getAttribute("innerText");
                            System.out.println("Value from DELIVERY QUANTITY column, row #" + i + " from BLOCK #" + TbodySection + " is:" + textValue3);

                            beforeCSR_Analysis_ResultsList_Array[2][rc1] = textValue3;//assign value into array

                            rc1++;
                            rowCountDeliveryQty++;

                            //click vertical scroller to move the list one row up
                            //action.click(scroller_DisplayedColumns).perform();
                            js.executeScript("arguments[0].click();", scroller_DisplayedColumns);

                        }


                        //check whether new TBODY got displayed dynamically
                        List<WebElement> newDynamicTBODY = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                        numberOfUpdatedTbodySections = newDynamicTBODY.size();

                        if (numberOfUpdatedTbodySections > numberOfDefaultTbodySections) {
                            numberOfDefaultTbodySections = numberOfUpdatedTbodySections;
                            System.out.println("Number of TBODY sections was dynamically updated to [" + numberOfUpdatedTbodySections + "]");
                        }

                        TbodySection++;
                    }
                } catch (NoSuchElementException e) {
                    logger.error("Error while scrolling and reading values from fields in RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
                }

               /* totalRowsResultsListTable = rowCountDeliveryQty;

                //count total number of records/rows in the "Results List" table
                System.out.println("The TOTAL number of rows in RESULTS LIST table is:" + totalRowsResultsListTable);*/



            System.out.println("Size of ARRAY: " + beforeCSR_Analysis_ResultsList_Array.length);
            for (int r = 0; r < beforeCSR_Analysis_ResultsList_Array.length; r++) {
                for (int c = 0; c < beforeCSR_Analysis_ResultsList_Array[r].length; c++) {
                    System.out.print(beforeCSR_Analysis_ResultsList_Array[r][c] + "\t");
                }
                System.out.println();
            }

        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
    }


    public int return_DeliveryQTYColumn_Number() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int numDeliveryQTYColumn = 0;


        try {

          //  if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //Get all columns in the "Results List" table
                List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-hdr')]/td[2]/div/div[2]/table/tbody/tr/child::th"));
                System.out.println("Number of columns in the RESULTS LIST table: " + available_ColumnsInRightPartOfTable.size());


                //Loop through all found columns
                for (WebElement element : available_ColumnsInRightPartOfTable) {
                    ColumnCount++; //column counter
                    if (neededColumns <= 1) {
                        System.out.println("Counter of Needed columns in the RESULTS LIST table: " + neededColumns);
                        try {//check if every column contains column title text
                            //scroll the field to be visible (if outside of vision portal) and click on it
                            //js.executeScript("arguments[0].scrollIntoView(true);", element);

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the RESULTS LIST table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Delivery Quantity")) {
                                numDeliveryQTYColumn = ColumnCount;
                                System.out.println("Number of the 'DELIVERY QUANTITY' column is: " + numDeliveryQTYColumn);
                            }
                        }
                    }
                }
          //  }
        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
        return numDeliveryQTYColumn;
    }



    public int return_MessageTextColumn_Number () {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int numMessageTextColumn = 0;


        try {

           // if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //Get all columns in the "Log Messages" table
                List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-hdr')]/td[2]/div/div[2]/table/tbody/tr/child::th"));
                System.out.println("Number of columns in the LOG MESSAGES table: " + available_ColumnsInRightPartOfTable.size());


                //Loop through all found columns
                for (WebElement element : available_ColumnsInRightPartOfTable) {
                    ColumnCount++; //column counter
                    if (neededColumns <= 1) {
                        System.out.println("Counter of Needed columns in the LOG MESSAGES table: " + neededColumns);
                        try {//check if every column contains column title text
                            //scroll the field to be visible (if outside of vision portal) and click on it
                            //js.executeScript("arguments[0].scrollIntoView(true);", element);

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the RESULTS LIST table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Message text")) {
                                numMessageTextColumn = ColumnCount;
                                System.out.println("Sequential Number of the 'MESSAGE TEXT' column is: " + numMessageTextColumn);
                            }
                        }
                    }
                }
           // }
        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the LOG MESSAGES of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
        return numMessageTextColumn;
    }



    public int return_DeliveryIDColumn_Number() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int numDeliveryIDColumn = 0;


        try {

           // if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //Get all columns in the "Results List" table
                List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-hdr')]/td[2]/div/div[2]/table/tbody/tr/child::th"));
                System.out.println("Number of columns in the RESULTS LIST table: " + available_ColumnsInRightPartOfTable.size());


                //Loop through all found columns
                for (WebElement element : available_ColumnsInRightPartOfTable) {
                    ColumnCount++; //column counter
                    if (neededColumns <= 1) {
                        System.out.println("Counter of Needed columns in the RESULTS LIST table: " + neededColumns);
                        try {//check if every column contains column title text
                            //scroll the field to be visible (if outside of vision portal) and click on it
                            //js.executeScript("arguments[0].scrollIntoView(true);", element);

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the RESULTS LIST table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Delivery ID")) {
                                numDeliveryIDColumn = ColumnCount;
                                System.out.println("Sequential Number of the 'DELIVERY ID' column is: " + numDeliveryIDColumn);
                            }
                        }
                    }
                }
           // }
        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
        return numDeliveryIDColumn;
    }


    public int return_MaterialsColumn_Number() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int numMaterialColumn = 0;


        try {

          //  if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //Get all columns in the "Results List" table
                List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-hdr')]/td[2]/div/div[2]/table/tbody/tr/child::th"));
                System.out.println("Number of columns in the RESULTS LIST table: " + available_ColumnsInRightPartOfTable.size());


                //Loop through all found columns
                for (WebElement element : available_ColumnsInRightPartOfTable) {
                    ColumnCount++; //column counter
                    if (neededColumns <= 1) {
                        System.out.println("Counter of Needed columns in the RESULTS LIST table: " + neededColumns);
                        try {//check if every column contains column title text
                            //scroll the field to be visible (if outside of vision portal) and click on it
                            //js.executeScript("arguments[0].scrollIntoView(true);", element);

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the RESULTS LIST table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Material")) {
                                if (!name_TableColumn.contains("Material description")) {
                                    numMaterialColumn = ColumnCount;
                                    System.out.println("Sequential Number of the 'MATERIAL' column is: " + numMaterialColumn);

                                }
                            }

                        }
                    }
                }
           //}
        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
        return numMaterialColumn;
    }


    public void check_SetToDefaultBtn_PersonolizedPopup_ResultsOfCSR_Log() {

        try {
            //check whether the "Set to Default" button is disabled or enabled.
            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                //Exit iframe to have access to the main window's web-elements
               //driver.switchTo().defaultContent();

                WebElement btn_SetToDefault_Persnlzd_ResultsOfCSR_Log = driver.findElement(By.xpath("//div[contains (text(), 'Settings')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Reset to Default')]/ancestor::div[1]"));

                String btn_Status = btn_SetToDefault_Persnlzd_ResultsOfCSR_Log.getAttribute("class");
                System.out.println("Class value for 'Set to Default' button: " + btn_Status);

                if (!btn_Status.contains("lsButton--disabled")) {//if the "Set to Default" button is enabled then click on it and wait/check that "Available Columns" section is empty

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table) was detected to be ENABLED.");

                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_SetToDefault_Persnlzd_ResultsOfCSR_Log);
                    //btn_New.click();

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table) was clicked.");

                    //waitTillDescendentElementsLessThanExpected(10, "//span[contains (text(), 'Available Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/descendant::tr", "'AVAILABLE COLUMNS' with EMPTY RECORDS", 1);

                    //wait till landing page is loaded again after closing "Personalization" popup
                    //waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "CSR GROUP PAGE after closing PERSONALIZATION popup on RESULTS OF CSR ANALYSIS");
                    //waitTillDescendentElementsEscapeByChildrenNumber(15, 2, "//div[contains (@id, 'ALL_POPUPS')]/child::div", "'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table");
                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                    //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                    waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

                    Thread.sleep(1000);

                } else {

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table) was detected to be in DISABLED status BY DEFAULT.");

                    WebElement btn_Save_Persnlzd_ResultsOfCSR_Log = driver.findElement(By.xpath("//div[contains (text(), 'Settings')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Save')]/ancestor::div[1]"));

                    //click on "Save" button
                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_Save_Persnlzd_ResultsOfCSR_Log);
                    //btn_New.click();

                    logger.info("The 'Save' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table) was SUCCESSFULLY clicked.");
                    //waitTillDescendentElementsEscapeByChildrenNumber(15, 2, "//div[contains (@id, 'ALL_POPUPS')]/child::div", "'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table");
                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                    //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                    waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

                    Thread.sleep(1000);
                }

            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-1')]")));
                //Thread.sleep(1000);

                String btn_Status = btn_SetToDefault_Persnlzd_ResultsOfCSR_Log.getAttribute("class");
                System.out.println("Class value for 'Set to Default' button: " + btn_Status);

                if (!btn_Status.contains("lsButton--disabled")) {//if the "Set to Default" button is enabled then click on it and wait/check that "Available Columns" section is empty

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table) was detected to be ENABLED.");


                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_SetToDefault_Persnlzd_ResultsOfCSR_Log);
                    //btn_New.click();

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table) was clicked.");


                    // waitTillDescendentElementsLessThanExpected(10, "//span[contains (text(), 'Available Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/descendant::tr", "'AVAILABLE COLUMNS' with EMPTY RECORDS", 1);
                    //Exit iframe to have access to the main window's web-elements
                    // driver.switchTo().defaultContent();

                    //wait till landing page is loaded again after closing "Personalization" popup
                    // waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "DATA COLLATION PAGE after closing PERSONALIZATION popup on LOCATOR PANEL");
                    // waitTillElementDetectedByStyle(15, "//iframe[contains(@name,'URLSPW-1')]", "display", "none", "'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table");

                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                    //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                    waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

                    Thread.sleep(1000);


                } else {
                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table) was detected to be in DISABLED status BY DEFAULT.");

                    //click on "Save" button
                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_Save_Persnlzd_ResultsOfCSR_Log);
                    //btn_New.click();

                    logger.info("The 'Save' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table) was SUCCESSFULLY clicked.");

                    //Exit iframe to have access to the main window's web-elements
                    //driver.switchTo().defaultContent();

                    //wait till landing page is loaded again after closing "Personalization" popup
                    // waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "DATA COLLATION PAGE after closing PERSONALIZATION popup on LOCATOR PANEL");
                    // waitTillElementDetectedByStyle(15, "//iframe[contains(@name,'URLSPW-1')]", "display", "none", "'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> LOG table");


                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                    //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                    waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

                    Thread.sleep(1000);

                }

            }

        } catch (Exception e) {
            logger.error("Unable to process the status of 'SET TO DEFAULT' button on PERSONALIZATION popup belonging to RESULTS OF CSR ANALYSIS -> LOG table: " + e.getMessage());
        }

    }


    public void check_SetToDefaultBtn_PersonolizedPopup_ResultsOfCSR_Analysis() {

        try {
            //check whether the "Set to Default" button is disabled or enabled.
            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                //Exit iframe to have access to the main window's web-elements
                //driver.switchTo().defaultContent();

                WebElement btn_SetToDefault_Persnlzd_ResultsOfCSR_Analysis = driver.findElement(By.xpath("//div[contains (text(), 'Settings')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Reset to Default')]/ancestor::div[1]"));

                String btn_Status = btn_SetToDefault_Persnlzd_ResultsOfCSR_Analysis.getAttribute("class");
                System.out.println("Class value for 'Set to Default' button: " + btn_Status);

                if (!btn_Status.contains("lsButton--disabled")) {//if the "Set to Default" button is enabled then click on it and wait/check that "Available Columns" section is empty

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table) was detected to be ENABLED.");

                    //action.moveToElement(btn_SetToDefault_Persnlzd_ResultsOfCSR_Analysis).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_SetToDefault_Persnlzd_ResultsOfCSR_Analysis);
                    //btn_SetToDefault_Persnlzd_ResultsOfCSR_Analysis.click();

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table) was clicked.");

                    //waitTillDescendentElementsLessThanExpected(10, "//span[contains (text(), 'Available Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/descendant::tr", "'AVAILABLE COLUMNS' with EMPTY RECORDS", 1);

                    //wait till landing page is loaded again after closing "Personalization" popup
                    //waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "CSR GROUP PAGE after closing PERSONALIZATION popup on RESULTS OF CSR ANALYSIS");
                    //waitTillDescendentElementsEscapeByChildrenNumber(15, 1, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/child::div", "'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table");
                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                    //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                    waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

                    Thread.sleep(1000);

                } else {

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table) was detected to be in DISABLED status BY DEFAULT.");

                    WebElement btn_Save_Persnlzd_ResultsOfCSR_Analysis = driver.findElement(By.xpath("//div[contains (text(), 'Settings')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Save')]/ancestor::div[1]"));

                    //click on "Save" button
                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_Save_Persnlzd_ResultsOfCSR_Analysis);
                    //btn_New.click();

                    logger.info("The 'Save' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table) was SUCCESSFULLY clicked.");
                   // waitTillDescendentElementsEscapeByChildrenNumber(15, 2, "//div[contains (@id, 'ALL_POPUPS')]/child::div", "'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table");
                    waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                    //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                    waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

                    Thread.sleep(1000);
                }

            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-1')]")));
                // Thread.sleep(1000);

                String btn_Status = btn_SetToDefault_Persnlzd_ResultsOfCSR_Analysis.getAttribute("class");
                System.out.println("Class value for 'Set to Default' button: " + btn_Status);

                if (!btn_Status.contains("lsButton--disabled")) {//if the "Set to Default" button is enabled then click on it and wait/check that "Available Columns" section is empty

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table) was detected to be ENABLED.");


                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_SetToDefault_Persnlzd_ResultsOfCSR_Analysis);
                    //btn_New.click();

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table) was clicked.");

                    //Exit iframe to have access to the main window's web-elements
                    // driver.switchTo().defaultContent();

                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                    //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                    waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

                    Thread.sleep(1000);

                } else {

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table) was detected to be in DISABLED status BY DEFAULT.");

                    //click on "Save" button
                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_Save_Persnlzd_ResultsOfCSR_Analysis);
                    //btn_New.click();


                    logger.info("The 'Save' button (on 'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table) was SUCCESSFULLY clicked.");

                    //Exit iframe to have access to the main window's web-elements
                    // driver.switchTo().defaultContent();

                    //wait till landing page is loaded again after closing "Personalization" popup
                    // waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "DATA COLLATION PAGE after closing PERSONALIZATION popup on LOCATOR PANEL");
                    //waitTillElementDetectedByStyle(15, "//iframe[contains(@name,'URLSPW-1')]", "display", "none", "'PERSONALIZATION' popup for RESULTS OF CSR ANALYSIS -> RESULTS LIST table");
                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                    //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                    waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

                    Thread.sleep(1000);
                }

            }

        } catch (Exception e) {
            logger.error("Unable to process the status of 'SET TO DEFAULT' button on PERSONALIZATION popup belonging to RESULTS OF CSR ANALYSIS -> RESULTS LIST table: " + e.getMessage());
        }

    }

    public void click_PersonalizeBtn_ResultsOfCSR_Log() {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                WebElement btn_Personalize_ResultsCSR_Log = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::table[contains (@class, 'urSTCS urST3WhlBrdH urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')][contains (@ct, 'STCS')]/thead/tr[2]/descendant::div[contains (@title, 'Settings')]"));

                //js.executeScript("arguments[0].click();", btn_Personalize_DC);
                btn_Personalize_ResultsCSR_Log.click();

                logger.info("The 'PERSONALIZATION' button for RESULTS OF CSR ANALYSIS -> LOG table was SUCCESSFULLY clicked.");

                waitTillDescendentElementsAvailable(5, "//span[contains (text(), 'Displayed Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')][1]/tbody[contains (@id, 'content')]/tr[2]/td[2]/descendant::*", "PERSONALIZATION POPUP for RESULTS OF CSR ANALYSIS -> LOG table");
            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-0')]")));
                //Thread.sleep(1000);

                //js.executeScript("arguments[0].click();", btn_Personalize_DC);
                btn_Personalize_ResultsCSR_Log.click();

                logger.info("The 'PERSONALIZATION' button for RESULTS OF CSR ANALYSIS -> LOG table was SUCCESSFULLY clicked.");

                waitForExtAjaxIsReadyState(15, "PERSONALIZATION POPUP for RESULTS OF CSR ANALYSIS -> LOG table");

                //Exit iframe to have access to the main window's web-elements
                //driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Failed to open 'PERSONALIZATION' popup from the LOG table on RESULTS OF CSR ANALYSIS popup: " + e.getMessage());
        }
    }

    public void click_PersonalizeBtn_ResultsOfCSR_Analysis() {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                WebElement btn_Personalize_ResultsCSR_Analysis = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::table[contains (@class, 'urSTCS urST3WhlBrdH urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')][contains (@ct, 'STCS')]/thead/tr[2]/descendant::div[contains (@title, 'Settings')]"));

                //js.executeScript("arguments[0].click();", btn_Personalize_DC);
                btn_Personalize_ResultsCSR_Analysis.click();

                logger.info("The 'PERSONALIZATION' button for RESULTS OF CSR ANALYSIS -> RESULTS LIST table was SUCCESSFULLY clicked.");

                waitTillDescendentElementsAvailable(5, "//span[contains (text(), 'Displayed Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')][1]/tbody[contains (@id, 'content')]/tr[2]/td[2]/descendant::*", "PERSONALIZATION POPUP for RESULTS OF CSR ANALYSIS -> RESULTS LIST table");
            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-0')]")));
                // Thread.sleep(1000);

                //js.executeScript("arguments[0].click();", btn_Personalize_DC);
                btn_Personalize_ResultsCSR_Analysis.click();

                logger.info("The 'PERSONALIZATION' button for RESULTS OF CSR ANALYSIS -> RESULTS LIST table was SUCCESSFULLY clicked.");

                waitForExtAjaxIsReadyState(15, "PERSONALIZATION POPUP for RESULTS OF CSR ANALYSIS -> RESULTS LIST table");

                //Exit iframe to have access to the main window's web-elements
                //  driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Failed to open 'PERSONALIZATION' popup from the RESULTS LIST table on RESULTS OF CSR ANALYSIS popup: " + e.getMessage());
        }
    }

    public void wait_Postprocessing_popup() {

        try {
            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                //wait till "Postprocessing Selection" table gets loaded on the "Postprocessing" popup
                waitTillElementDetected(30, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[1]/td[1]/div", "POSTPROCESSING popup");

            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
               // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
             //   driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'URLSPW-1')]")));

                Thread.sleep(1000);

                //wait till "Postprocessing" popup gets loaded
                waitTillElementDetected(30, "//div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[1]/td[1]/div", "POSTPROCESSING popup");

            //    driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
            logger.error("Error while waiting the POSTPROCESSING popup to download: " + e.getMessage());
        }

    }

    public void wait_ResultsOfCSR_AnalysisPopupLoads() {

        try {
            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                //wait till "Results List" table gets loaded on the "Results of CSR Analysis" popup
                waitTillElementDetected(30, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");


                //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                waitTillElementDetected(30, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/descendant::tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");
                //span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/child::tr
            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'URLSPW-0')][contains (@inuse, 'true')]")));


                //wait till "Results List" table gets loaded on the "Results of CSR Analysis" popup
                // waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')][1]/tbody[1]/tr[1]/td[1]/div/span/input", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");
                // waitTillElementsAppearByChildrenNumber(15, 1, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");
                waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                // waitTillElementDetected(40, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/descendant::tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");
                // waitTillElementsAppearByChildrenNumber(40, 1,"//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");
                waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");


                // driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
            logger.error("Error while waiting the RESULTS OF CSR ANALYSIS popup to download: " + e.getMessage());
        }

    }


    public void click_StartCSR_AnalysisBtn() {
        try {

            //action.moveToElement(input_Width_1).click().build().perform();
            js.executeScript("arguments[0].click();", btn_StartCSR_Analysis);
            //btn_New.click();

            logger.info("The 'START CSR ANALYSIS' button (on 'CSR GROUP' page) was clicked.");

        } catch (Exception e) {
            logger.error("Error while clicking on 'START CSR ANALYSIS' button (on 'CSR GROUP' page): " + e.getMessage());
        }

    }

}
