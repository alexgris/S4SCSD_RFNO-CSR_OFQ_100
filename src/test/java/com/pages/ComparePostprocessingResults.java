package com.pages;

import init.settings.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.project.Test_Cases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static ax.generic.Waitings.*;
import static org.project.Test_Cases.*;

public class ComparePostprocessingResults extends PageObject {

    public Logger logger = Logger.getLogger(ComparePostprocessingResults.class);


    //Constructor
    public ComparePostprocessingResults(WebDriver driver) {
        super(driver);

    }

    JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions action = new Actions(driver);


    public void click_Close_Btn() {

      //  if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
            // driver.switchTo().defaultContent();
            //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
            // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-0')]")));

            try {

                WebElement btn_StartProcessing = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[4]/descendant::td[contains (@class, 'lsPopupWindow_Footer-buttons')]/descendant::*[contains (text(), 'Close')]/ancestor::div[1] "));

                js.executeScript("arguments[0].click();", btn_StartProcessing);

                logger.info("The CLOSE button on the RESULTS OF CSR ANALYSIS popup was clicked SUCCESSFULLY.");

            } catch (Exception e) {
                logger.warn("Unable to click on the CLOSE button on the RESULTS OF CSR ANALYSIS popup: " + e.getMessage());
            }

            // driver.switchTo().defaultContent();
      //  }
    }


    public void retrieve_uniqueDocNumbers_after() {

        List<String> dynDocs = new ArrayList<String>();

        try {

            System.out.println("Size of Array:" + afterCSR_Analysis_Log_Array.length);


            //copy DOCUMENT NUMBERS from array into a list
            for (int c = 0; c < afterCSR_Analysis_Log_Array.length; c++) {
                dynDocs.add(afterCSR_Analysis_Log_Array[c]);
            }

            //read copied DOCUMENT NUMBERS from the list
            for (String item : dynDocs) {
                System.out.println("RETRIEVED 'DELIVERY IDs': " + item);
            }


            Test_Cases.after_unique_DocNumbers = new HashSet<>(dynDocs);
            for (String value : Test_Cases.after_unique_DocNumbers) {

                System.out.println("RETRIEVED UNIQUE 'DOCUMENT NUMBERS after processing': " + value);

                logger.info("RETRIEVED UNIQUE 'DOCUMENT NUMBERS after processing': " + value);
            }
        } catch (Exception e) {
            logger.warn("Unable to retrieve UNIQUE 'DOCUMENT NUMBERS after processing': " + e.getMessage());
        }

    }


    public void read_CSR_Analysis_Log_Table_AfterProcessing() {

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

         //   if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                List<String> docNums = new ArrayList<String>();

                //scroll the table all the way up to the top
                try {
                    //scroll up the "LOG MESSAGES" table
                    WebElement belowRows2 = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td/div/div[2]"));

                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);

                    WebElement panel_Height = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);
                    int height = panel_Height.getSize().getHeight();
                    System.out.println("The height of the vertical scroll bar in 'LOG MESSAGES' table is: " + height);

                    action.moveToElement(belowRows2).clickAndHold();
                    action.moveByOffset(0, -height);
                    action.release();
                    action.perform();

                    if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                        try {

                            // WebElement upperTable = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[1]/td[1]"));

                            //wait till "Postprocessing Selection" table gets loaded on the "Postprocessing" popup
                            //explicitWaitsUntilElementPresent(30, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[1]/td[1]", "Updated 'CSR ANALYSIS RESULTS' TABLE after SCROLL-UP");
                            waitForExtAjaxIsReadyState(30, "Updated 'CSR ANALYSIS LOG MESSAGES' TABLE after SCROLL-UP");
                            Thread.sleep(3000);

                            // explicitWaitsUntilElementPresent(30, "Updated 'CSR ANALYSIS RESULTS' TABLE after SCROLL-UP");
                        } catch (Exception e) {
                            logger.error("Error while scrolling up the LOG MESSAGES of 'RESULTS OF CSR ANALYSIS' table AFTER POSTPROCESSING: " + e.getMessage());
                        }
                    }

                    if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                        try {

                            // WebElement upperTable = driver.findElement(By.xpath("//div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[1]/td[1]"));

                            //wait till "Postprocessing Selection" table gets loaded on the "Postprocessing" popup
                            //explicitWaitsUntilElementPresent(30, "//div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[1]/td[1]", "Updated 'CSR ANALYSIS RESULTS' TABLE after SCROLL-UP");
                            waitForExtAjaxIsReadyState(30, "Updated 'CSR ANALYSIS LOG MESSAGES' TABLE after SCROLL-UP");
                            Thread.sleep(3000);

                        } catch (Exception e) {
                            logger.error("Error while scrolling up the LOG MESSAGES of 'RESULTS OF CSR ANALYSIS' table AFTER POSTPROCESSING: " + e.getMessage());
                        }
                    }

                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while scrolling and counting rows in LOG MESSAGES of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
                }

                //scroll the table and count total rows to initialize array
                try {

                    WebElement scroller_DisplayedColumns = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--next')]/td/div"));

                    //check the number of TBODY elements displayed by default
                    List<WebElement> row_Blocks = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                    numberOfDefaultTbodySections1 = row_Blocks.size();
                    System.out.println("Number of TBODY tags in the 'RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table': " + numberOfDefaultTbodySections1);


                    //loop through every BODY section in LOG table
                    while (TbodySection1 <= numberOfDefaultTbodySections1) {


                        //address each TBODY section in LOG table via variable
                        WebElement elm_1_1 = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]"));
                        //System.out.println("Number of TBODY tags in 'DISPLAYED COLUMNS' section on PERSONALIZATION popup: " + elm_1.size());

                        //count rows in current TBODY section in LOG table
                        List<WebElement> elm_1_2 = elm_1_1.findElements(By.xpath("child::tr"));
                        System.out.println("Number of TR tags in TBODY [" + TbodySection1 + "] tag in the 'RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table': " + elm_1_2.size());

                        int rows_Number1 = elm_1_2.size();//total rows in current TBODY section

                        for (int i = 1; i <= rows_Number1; i++) {//iterate through all rows found in "TEXT MESSAGES" column in current TBODY section

                            //Find input field below the "MESSAGE TEXT" column title
                            //WebElement field_MessageText = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]/tr[" + i + "]/td[" +  numMessageTextColumn + "]/div/span"));

                            //Wait for the next row to appear in the "MESSAGE TEXT" column
                            waitTillElementDetected(15, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]/tr[" + i + "]/td[" + numMessageTextColumn + "]/div/span", "NEXT DYNAMICALLY LOADED ROW in 'LOG MESSAGES' Table");

                            rowCountTextMessages1++;

                            //click vertical scroller to move the list one row up
                            //action.click(scroller_DisplayedColumns).perform();
                            js.executeScript("arguments[0].click();", scroller_DisplayedColumns);

                        }


                        //check whether new TBODY got displayed dynamically
                        List<WebElement> newDynamicTBODY = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
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
                    WebElement belowRows2 = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td/div/div[2]"));
                    //js.executeScript("arguments[0].scrollIntoView();", belowRows);

                    WebElement panel_Height = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td"));
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

                    WebElement scroller_DisplayedColumns = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--next')]/td/div"));

                    //check the number of TBODY elements displayed by default
                    List<WebElement> row_Blocks = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                    numberOfDefaultTbodySections = row_Blocks.size();
                    System.out.println("Number of TBODY tags in the 'RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table': " + numberOfDefaultTbodySections);


                    //loop through every BODY section in RESULTS table
                    while (TbodySection <= numberOfDefaultTbodySections) {

                        //address each TBODY section in RESULTS table via variable
                        WebElement elm_1_1 = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]"));
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
                            waitTillElementDetected(15, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" + numMessageTextColumn + "]/div/span", "NEXT DYNAMICALLY LOADED ROW in 'LOG MESSAGES' Table");

                            //Find input field below the "Message Text" column title
                            WebElement field_MessageText = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" + numMessageTextColumn + "]/div/span"));

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
                        List<WebElement> newDynamicTBODY = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
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
                afterCSR_Analysis_Log_Array = new String[docNums.size()];
                docNums.toArray(afterCSR_Analysis_Log_Array);

                //read the array with copied values
                for (int i = 0; i < afterCSR_Analysis_Log_Array.length; i++) {
                    System.out.println("Element at the index " + i + " is ::" + afterCSR_Analysis_Log_Array[i]);
                }

           // }


        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the LOG table of 'RESULTS OF CSR ANALYSIS' popup: " + e.getMessage());
        }

    }

    public int return_MessageTextColumn_Number() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int numMessageTextColumn = 0;


        try {

          //  if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

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
          //  }
        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the LOG MESSAGES of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
        return numMessageTextColumn;
    }


    public int return_MaterialsColumn_Number() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int numMaterialColumn = 0;


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
                        if (name_TableColumn.contains("Material")) {
                            if (!name_TableColumn.contains("Material description")) {
                                numMaterialColumn = ColumnCount;
                                System.out.println("Sequential Number of the 'MATERIAL' column is: " + numMaterialColumn);

                            }
                        }

                    }
                }
            }
            // }
        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
        return numMaterialColumn;
    }


    public int return_DeliveryIDColumn_Number() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int numDeliveryIDColumn = 0;


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


    public int return_DeliveryQTYColumn_Number() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int numDeliveryQTYColumn = 0;


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

    public void moveVerticalScrollerUpTillEnd_CSR_Analysis_ResultsList_Table_AfterProcessing() {
        //scroll the table up
        try {
            //  if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

            //scroll the "RESULTS LIST" table all the way up till the top
            WebElement belowRows2 = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td/div/div[2]"));
            //js.executeScript("arguments[0].scrollIntoView();", belowRows);

            WebElement panel_Height = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--track')]/td"));
            //js.executeScript("arguments[0].scrollIntoView();", belowRows);
            int height = panel_Height.getSize().getHeight();
            System.out.println("The height of the vertical scroll bar in 'RESULTS LIST' table is: " + height);

            action.moveToElement(belowRows2).clickAndHold();
            action.moveByOffset(0, -height);
            action.release();
            action.perform();
            //  }

        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while scrolling up the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }
    }

    public void read_CSR_Analysis_ResultsList_Table_AfterProcessing() {

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


            //   if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
            //scroll the "RESULTS LIST" table all the way up till the top
            moveVerticalScrollerUpTillEnd_CSR_Analysis_ResultsList_Table_AfterProcessing();

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    // WebElement upperTable = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[1]/td[1]"));

                    //wait till "Postprocessing Selection" table gets loaded on the "Postprocessing" popup
                    //explicitWaitsUntilElementPresent(30, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[1]/td[1]", "Updated 'CSR ANALYSIS RESULTS' TABLE after SCROLL-UP");
                    waitForExtAjaxIsReadyState(30, "Updated 'CSR ANALYSIS RESULTS' TABLE after SCROLL-UP");
                    Thread.sleep(3000);

                    // explicitWaitsUntilElementPresent(30, "Updated 'CSR ANALYSIS RESULTS' TABLE after SCROLL-UP");
                } catch (Exception e) {
                    logger.error("Error while scrolling up the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table AFTER POSTPROCESSING: " + e.getMessage());
                }
            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                try {

                    // WebElement upperTable = driver.findElement(By.xpath("//div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[1]/td[1]"));

                    //wait till "Postprocessing Selection" table gets loaded on the "Postprocessing" popup
                    //explicitWaitsUntilElementPresent(30, "//div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[1]/td[1]", "Updated 'CSR ANALYSIS RESULTS' TABLE after SCROLL-UP");
                    waitForExtAjaxIsReadyState(30, "Updated 'CSR ANALYSIS RESULTS' TABLE after SCROLL-UP");
                    Thread.sleep(3000);

                } catch (Exception e) {
                    logger.error("Error while scrolling up the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table AFTER POSTPROCESSING: " + e.getMessage());
                }
            }


            //scroll the table and count total rows to initialize array
            try {

                WebElement scroller_DisplayedColumnsNext = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--next')]/td/div"));

                WebElement scroller_DisplayedColumnsPrevious = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--previous')]/td/div"));

                //check the number of TBODY elements displayed by default
                List<WebElement> row_Blocks = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                numberOfDefaultTbodySections1 = row_Blocks.size();
                System.out.println("Number of TBODY blocks BY DEFAULT in the 'RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table': " + numberOfDefaultTbodySections1);


                //loop through every BODY section in RESULTS table
                while (TbodySection1 <= numberOfDefaultTbodySections1) {

                    //address each TBODY section in RESULTS table via variable
                    WebElement elm_1_1 = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]"));
                    //System.out.println("Number of TBODY tags in 'DISPLAYED COLUMNS' section on PERSONALIZATION popup: " + elm_1.size());

                    //count rows in current TBODY section in RESULTS table
                    List<WebElement> elm_1_2 = elm_1_1.findElements(By.xpath("descendant::tr"));
                    System.out.println("Number of TR tags in TBODY [" + TbodySection1 + "] tag in the 'RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table': " + elm_1_2.size());

                    int rows_Number1 = elm_1_2.size();//total rows in current TBODY section

                    for (int i = 1; i <= rows_Number1; i++) {//iterate through all rows found in "DELIVERY QUANTITY" column in current TBODY section

                        //Find input field below the "DELIVERY QUANTITY" column title
                        //WebElement field_DeliveryQty = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]/tr[" + i + "]/td[" + numDeliveryQtyColumn + "]/div/span"));

                        //Wait for the next row to appear in the "DELIVERY QUANTITY" column
                        waitTillElementDetected(15, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection1 + "]/tr[" + i + "]/td[" + numDeliveryQtyColumn + "]/div/span", "NEXT DYNAMICALLY LOADED ROW in 'RESULTS LIST' Table");


                        rowCountDeliveryQty1++;

                        //click vertical scroller to move the list one row down
                        // action.click(scroller_DisplayedColumns).release().perform();
                        js.executeScript("arguments[0].click();", scroller_DisplayedColumnsNext);
                        //scroller_DisplayedColumns.click();

                    }


                    //check whether new TBODY got displayed dynamically
                    List<WebElement> newDynamicTBODY = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                    numberOfUpdatedTbodySections1 = newDynamicTBODY.size();
                    System.out.println("UPDATED number of TBODY sections  [" + numberOfUpdatedTbodySections1 + "]");


                    if (numberOfUpdatedTbodySections1 >= numberOfDefaultTbodySections1) {
                        numberOfDefaultTbodySections1 = numberOfUpdatedTbodySections1;
                        System.out.println("Number of TBODY sections was dynamically updated to [" + numberOfUpdatedTbodySections1 + "]");
                    }

                    TbodySection1++;
                }

                totalRowsResultsListTable = rowCountDeliveryQty1;

                //count total number of records/rows in the "Results List" table
                System.out.println("The TOTAL number of rows in RESULTS LIST table is: " + totalRowsResultsListTable);


                int m = 1;
                while (m <= totalRowsResultsListTable) {
                    //click vertical scroller to move the list one row down
                    // action.click(scroller_DisplayedColumns).release().perform();
                    js.executeScript("arguments[0].click();", scroller_DisplayedColumnsPrevious);
                    //scroller_DisplayedColumns.click();
                    m++;
                }


            } catch (NoSuchElementException e) {
                logger.error("No such element - Error while scrolling and counting rows in RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
            }


            //scroll the table and read required fields
            try {

                //scroll the "RESULTS LIST" table all the way up till the top
                //moveVerticalScrollerUpTillEnd_CSR_Analysis_ResultsList_Table_AfterProcessing();

                //initialize the "after" array
                afterCSR_Analysis_ResultsList_Array = new String[3][totalRowsResultsListTable];

                WebElement scroller_DisplayedColumns = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[3]/div/descendant::tr[contains (@class, 'lsScrollbar__container--next')]/td/div"));

                //check the number of TBODY elements displayed by default
                List<WebElement> row_Blocks = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
                numberOfDefaultTbodySections = row_Blocks.size();

                System.out.println("The number of default row blocks in RESULTS LIST table is: " + numberOfDefaultTbodySections);

                int rc1 = 0;//array size parameter

                //loop through every BODY section in RESULTS table
                while (TbodySection <= numberOfDefaultTbodySections) {

                    //address each TBODY section in RESULTS table via variable
                    WebElement elm_1_1 = driver.findElement(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]"));
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
                        WebElement field_Material = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" + numMaterialColumn + "]/div/span"));

                        String textValue = field_Material.getAttribute("innerText");
                        System.out.println("Value from MATERIAL column, row #" + i + " from BLOCK #" + TbodySection + " is:" + textValue);

                        afterCSR_Analysis_ResultsList_Array[0][rc1] = textValue;//assign value into array

                        //Find input field below the "DELIVERY ID" column title
                        WebElement field_DeliveryID = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" + numDeliveryIDColumn + "]/div/span"));

                        String textValue2 = field_DeliveryID.getAttribute("innerText");
                        System.out.println("Value from DELIVERY ID column, row #" + i + " from BLOCK #" + TbodySection + " is:" + textValue2);

                        afterCSR_Analysis_ResultsList_Array[1][rc1] = textValue2;//assign value into array


                        //Find input field below the "DELIVERY QUANTITY" column title
                        WebElement field_DeliveryQty = driver.findElement(By.ByXPath.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[" + TbodySection + "]/tr[" + i + "]/td[" + numDeliveryQtyColumn + "]/div/span"));

                        String textValue3 = field_DeliveryQty.getAttribute("innerText");
                        System.out.println("Value from DELIVERY QUANTITY column, row #" + i + " from BLOCK #" + TbodySection + " is:" + textValue3);

                        afterCSR_Analysis_ResultsList_Array[2][rc1] = textValue3;//assign value into array

                        rc1++;
                        rowCountDeliveryQty++;

                        //click vertical scroller to move the list one row up
                        //action.click(scroller_DisplayedColumns).perform();
                        js.executeScript("arguments[0].click();", scroller_DisplayedColumns);

                    }


                    //check whether new TBODY got displayed dynamically
                    List<WebElement> newDynamicTBODY = driver.findElements(By.xpath("//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/child::tbody"));
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
            //   }

               /* totalRowsResultsListTable = rowCountDeliveryQty;

                //count total number of records/rows in the "Results List" table
                System.out.println("The TOTAL number of rows in RESULTS LIST table is:" + totalRowsResultsListTable);*/


            System.out.println("Size of ARRAY: " + afterCSR_Analysis_ResultsList_Array.length);
            for (int r = 0; r < afterCSR_Analysis_ResultsList_Array.length; r++) {
                for (int c = 0; c < afterCSR_Analysis_ResultsList_Array[r].length; c++) {
                    System.out.print(afterCSR_Analysis_ResultsList_Array[r][c] + "\t");
                }
                System.out.println();
            }


        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the RESULTS LIST of 'RESULTS OF CSR ANALYSIS' table: " + e.getMessage());
        }

    }


    public void retrieve_uniqueMaterials_after() {

        List<String> MaterialIDs = new ArrayList<String>();

        try {

            System.out.println("Size of Array:" + afterCSR_Analysis_ResultsList_Array[1].length);


            //copy DELIVERY IDs from array into a list
            for (int c = 0; c < afterCSR_Analysis_ResultsList_Array[0].length; c++) {
                MaterialIDs.add(afterCSR_Analysis_ResultsList_Array[0][c]);
            }

            //read copied DELIVERY IDs from the list
            for (String item : MaterialIDs) {
                System.out.println("RETRIEVED 'MATERIAL': " + item);
            }


            Test_Cases.after_unique_DeliveryIDs = new HashSet<>(MaterialIDs);
            for (String value : Test_Cases.after_unique_DeliveryIDs) {

                System.out.println("RETRIEVED UNIQUE 'MATERIALS after processing': " + value);

                logger.info("RETRIEVED UNIQUE 'MATERIALS after processing': " + value);
            }
        } catch (Exception e) {
            logger.warn("Unable to retrieve UNIQUE 'MATERIALS after processing': " + e.getMessage());
        }

    }

}
