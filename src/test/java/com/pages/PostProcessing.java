package com.pages;

import init.settings.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.project.Test_Cases;

import java.util.List;

import static ax.generic.Waitings.*;

public class PostProcessing extends PageObject {

    public Logger logger = Logger.getLogger(PostProcessing.class);

    //Locating "Check" button in the toolbar
    @FindBy(xpath = "//span[contains(text(), 'Check')]")
    private WebElement btn_Check;

    //Locating "Check" button's container
    //OFQ//@FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[@class='lsToolbar--overflow urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar']/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[13]/div")
    @FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[contains (@class, 'urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar')]/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[13]/div")
    private WebElement btn_CheckContainer;

    //Locating "Release" button's container
    //OFQ//@FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[@class='lsToolbar--overflow urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar']/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[14]/div")
    @FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[contains (@class, 'urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar')]/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[14]/div")
    private WebElement btn_ReleaseContainer;

    //Locating "Release" button in the toolbar
    @FindBy(xpath = "//span[contains(text(), 'Release')]")
    private WebElement btn_Release;

    //Locating "Save" button's container
    //OFQ//@FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[@class='lsToolbar--overflow urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar']/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[1]/div")
    @FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[contains (@class, 'urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar')]/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[1]/div")
    private WebElement btn_SaveContainer;

    //Locating "Save" button in the toolbar
    @FindBy(xpath = "//span[contains(text(), 'Save')]")
    private WebElement btn_Save;

    //Locating "Process" button's container
    //OFQ//@FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[@class='lsToolbar--overflow urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar']/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[15]/div")
    @FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[contains (@class, 'urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar')]/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[15]/div")
    private WebElement btn_ProcessContainer;

    //Locating "Process" button in the toolbar
    @FindBy(xpath = "//span[contains(text(), 'Process')]")
    private WebElement btn_Process;

    //Locating "StandardDataCollation" section on the top of the page
    // @FindBy(xpath = "//span[contains(text(), 'Transaction Type')]/ancestor::table[@class='lsTWhlNested urTWhlTrans urHtmlTableReset urTBar lsToolbar'][contains (@ct, 'T')]")
    @FindBy(xpath = "//span[contains(text(), 'Transaction Type')]/ancestor::td[contains (@class, 'lsContainerCell lsGLCTopVAlign lsContainerCellVAlign--top ')][contains (@ct, 'GLC')]")
    private WebElement header_StandardDataCollation;

    //Locating "Reverse" button's container
    //OFQ//@FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[@class='lsToolbar--overflow urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar']/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[18]/div")
    @FindBy(xpath = "//div[@class='lsPageHeader--item urPgHITrans']/table[contains (@class, 'urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar')]/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[18]/div")
    private WebElement btn_ReverseContainer;

    //Locating "Process" button in the toolbar
    @FindBy(xpath = "//span[contains(text(), 'Reverse')]")
    private WebElement btn_Reverse;

    //Locating "Process" button in the toolbar
    @FindBy(xpath = "//span[contains(text(), 'Reverse')]/ancestor::div[1]")
    private WebElement btn_Reverse2;

    //Locating PROCESSING STATUS panel
    @FindBy(xpath = "//td[contains (text(), 'Data Collation')]/ancestor::div[contains (@ct, 'PHM')][contains (@class, 'urPgHSmall sapLSStandalonePH')]/div[2]/descendant::tr[contains(@rr, '1')][contains (@rt, '0')]/td[2]/descendant::span[contains (@class, 'urTxtStd urTxtColor')]")
    private WebElement textStatus_Panel;

    //Locating "Personalize" button on "Localization" panel for CSR
    @FindBy(xpath = "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::td[contains (@class, 'lsToolbar--rightItems')]/span/div[contains (@title, 'Personalize')]")
    private WebElement btn_Personalize_Postprocessing;


    //Constructor
    public PostProcessing(WebDriver driver) {
        super(driver);

    }

    JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions action = new Actions(driver);

    public void click_CloseButton_PostprocessingPopup() {

        try {

            //js.executeScript("arguments[0].click();", btn_Personalize_DC);
            //btn_Personalize_Postprocessing.click();

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
                //Exit iframe to have access to the main window's web-elements
                driver.switchTo().defaultContent();

                WebElement btn_Close = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[4]/div/table/tbody/tr/td[3]/descendant::span[contains (text(), 'Close')]/ancestor::div[1]"));

                btn_Close.click();

                logger.info("CLOSE button was SUCCESSFULLY clicked on the POSTPROCESSING popup.");


                //wait till "Results List" table gets loaded on the "Results of CSR Analysis" popup
                // waitTillElementDetected(30, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
               // waitTillElementDetected(30, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/descendant::tr[contains (@vpm, 'mrss-cont')]/td[2]/div/descendant::table[contains (@id, 'mrss-cont-none-content')]/descendant::tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");
                //span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[" + j + "]/child::tr

                //wait till "Start Processing" button gets disabled on the "Results of CSR Analysis" popup
                waitTillWebElementChangesClassProperty(30, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::span[contains (text(), 'Start Postprocessing')]/ancestor::div[1]", "lsButton--disabled", "RESULTS OF CSR ANALYSIS popup");


                //wait till "Results List" table gets loaded on the "Results of CSR Analysis" popup
                //waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");


            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //Exit iframe to have access to the main window's web-elements
                //driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
               // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'URLSPW-1')][contains (@inuse, 'true')]")));

                WebElement btn_Close = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'lsTwhlFooter lsTbarLarge urPWFooterBottomLine')]/div/table/tbody/tr/td[3]/descendant::span[contains (text(), 'Close')]/ancestor::div[1]"));

                btn_Close.click();

               // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
               // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'URLSPW-0')][contains (@inuse, 'true')]")));

                //wait till "Start Processing" button gets disabled on the "Results of CSR Analysis" popup
                waitTillWebElementChangesClassProperty(30, "//div[contains (text(), 'Result of CSR Analysis')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[3]/table/tbody/tr/td/div/div[1]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::span[contains (text(), 'Start Postprocessing')]/ancestor::div[1]", "lsButton--disabled", "RESULTS OF CSR ANALYSIS popup");


                //wait till "Results List" table gets loaded on the "Results of CSR Analysis" popup
                //waitTillElementDetected(30, "//span[contains (text(), 'Results List')]/ancestor::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')][1]/tbody[1]/tr[contains (@vpm, 'mrss-cont')]/td[2]/div/div[2]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/tr[contains (@class, 'urST4RowFirstVisible')]", "RESULTS OF CSR ANALYSIS popup -> RESULTS LIST table");

                //wait till "Log Messages" table gets loaded on the "Results of CSR Analysis" popup
                waitTillDescendentElementsAvailable(30, "//span[contains (text(), 'Log Messages')]/ancestor::table[contains (@ct, 'STCS')][contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[contains (@class, 'urBorderBox')][contains (@bisposelement, 'X')]/table[contains (@id, 'mrss-cont-none-content')]/tbody[1]/descendant::tr", "RESULTS OF CSR ANALYSIS popup -> LOG MESSAGES table");

               // driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Failed to click on 'CLOSE' button on the 'POSTPROCESSING'popup: " + e.getMessage());
        }
    }

    public void click_ExecutePostprocessing_Button() {

        try {

            //js.executeScript("arguments[0].click();", btn_Personalize_DC);
            //btn_Personalize_Postprocessing.click();

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
                //Exit iframe to have access to the main window's web-elements
                driver.switchTo().defaultContent();

                WebElement btn_ExecutePostprocessing = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::div[contains (@title, 'Execute Postprocessing')]"));

                String buttonStatus = btn_ExecutePostprocessing.getAttribute("class");
                System.out.println("Class name for 'Execute Postprocessing' button is: " + buttonStatus);

                if (!buttonStatus.contains("lsButton--disabled")) {
                    btn_ExecutePostprocessing.click();

                    logger.info("EXECUTE POSTPROCESSING button was SUCCESSFULLY clicked on the POSTPROCESSING popup.");

                    waitTillElementDetected(5, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/descendant::table[contains (@class, 'urMatrixLayout urHtmlTableReset')]/tbody/tr[1]/td/descendant::span[contains (text(), 'E-mail sent successfully')]", "E-MAIL SENT SUCCESSFULLY message");
                    waitTillElementDetected(5, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/descendant::table[contains (@class, 'urMatrixLayout urHtmlTableReset')]/tbody/tr[1]/td/descendant::span[contains (text(), 'CSR order generation completed successfully')]", "CSR ORDER GENERATION COMPLETED SUCCESSFULLY message");

                } else {
                    logger.warn("The 'EXECUTE POSTPROCESSING' button was detected to be DISABLED by DEFAULT. No postprocessing is possible!");
                }

            }
            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //Exit iframe to have access to the main window's web-elements
               //driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
               // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'URLSPW-1')][contains (@inuse, 'true')]")));

                WebElement btn_ExecutePostprocessing = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::div[contains (@title, 'Execute Postprocessing')]"));

                String buttonStatus = btn_ExecutePostprocessing.getAttribute("class");
                System.out.println("Class name for 'Execute Postprocessing' button is: " + buttonStatus);

                if (!buttonStatus.contains("lsButton--disabled")) {
                    btn_ExecutePostprocessing.click();

                    logger.info("EXECUTE POSTPROCESSING button was SUCCESSFULLY clicked on the POSTPROCESSING popup.");

                    waitTillElementDetected(5, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::span[contains (text(), 'E-mail sent successfully')]", "E-MAIL SENT SUCCESSFULLY message");
                    waitTillElementDetected(5, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::span[contains (text(), 'CSR order generation completed successfully')]", "CSR ORDER GENERATION COMPLETED SUCCESSFULLY message");

                } else {
                    logger.warn("The 'EXECUTE POSTPROCESSING' button was detected to be DISABLED by DEFAULT. No postprocessing is possible!");
                }

                //Exit iframe to have access to the main window's web-elements
               // driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Failed to click (or determine the status of) on 'EXECUTE POSTPROCESSING' button on the 'POSTPROCESSING'popup: " + e.getMessage());
        }
    }

    public void read_PostprocessingSelection_Table() {

        int rowsCount_LogTable = 0;
        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int blockCount = 0;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
                driver.switchTo().defaultContent();

                //Get all columns in the "Postprocessing Selection" table
                List<WebElement> available_ColumnsInPostprocessingSelectionTable = driver.findElements(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[1]/td[2]/descendant::table[contains (@id, 'mrss-hdr-none-content')]/tbody/tr/descendant::th"));
                System.out.println("Number of columns in the POSTPROCESSING SELECTION table: " + available_ColumnsInPostprocessingSelectionTable.size());
                logger.info("Number of columns in the POSTPROCESSING SELECTION table: " + available_ColumnsInPostprocessingSelectionTable.size());

                //Loop through all found coulmns
                for (WebElement element : available_ColumnsInPostprocessingSelectionTable) {
                    ColumnCount++; //column counter
                    if (neededColumns == 1) {
                        System.out.println("Counter of Needed columns in the POSTPROCESSING SELECTION table: " + neededColumns);
                        try {//check if every column contains column title text

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));

                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the POSTPROCESSING SELECTION table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Postprocessing Action ID")) {
                                name_TableColumn = "Postprocessing Action ID";
                            }


                            switch (name_TableColumn) {
                                // case "\n\nMaterial\n\n\n ":
                                case "Postprocessing Action ID":

                                    List<WebElement> rows_PostprocessingSelectionTable = driver.findElements(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/td[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/descendant::tr"));
                                    int rows_Number = rows_PostprocessingSelectionTable.size();

                                    System.out.println("Number of rows in the POSTPROCESSING SELECTION table: " + rows_Number);
                                    logger.info("Number of rows in the POSTPROCESSING SELECTION table: " + rows_Number);


                                    for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found

                                        boolean rowFound = false;
                                        String textValue = "";

                                        try {

                                            //Find input field below the "Postprocessing Action ID" column title
                                            WebElement field_ActionID = driver.findElement(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/td[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[" + i + "]/td[" + ColumnCount + "]/div/span/span"));

                                            textValue = field_ActionID.getAttribute("innerText");
                                            rowFound = true;

                                            System.out.println("The value in 'Postprocessing Action ID' column in row# " + i + " is equal to: " + textValue);
                                            logger.info("The value in 'Postprocessing Action ID' column in row# " + i + " is equal to: " + textValue);

                                        } catch (Exception e) {
                                            System.out.println("Failed to find  row #" + i + " in POSTPROCESSING SELECTION table!");
                                            logger.warn("Failed to find  row #" + i + " in POSTPROCESSING SELECTION table: " + e.getMessage());
                                        }

                                        if (rowFound == true) {
                                            System.out.println("Value from 'Postprocessing Action ID' column of POSTPROCESSING SELECTION table, row #" + i + " is:  " + textValue.toUpperCase());

                                            if (textValue.equals("ORDER")) {
                                                Test_Cases.cell_Order = true;
                                            }
                                            if (textValue.equals("MAIL")) {
                                                Test_Cases.cell_Mail = true;
                                            }

                                        }
                                    }
                                    neededColumns++;

                                    break;

                                default:
                            }

                        }
                    } else {
                        return;// break the cycle when all required fields have been filled in
                    }
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-1')][contains(@inuse, 'true')]")));

                //Get all columns in the "Postprocessing Selection" table
                List<WebElement> available_ColumnsInPostprocessingSelectionTable = driver.findElements(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')]/div/div[3]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[1]/td[2]/descendant::table[contains (@id, 'mrss-hdr-none-content')]/tbody/tr/descendant::th"));
                System.out.println("Number of columns in the POSTPROCESSING SELECTION table: " + available_ColumnsInPostprocessingSelectionTable.size());
                logger.info("Number of columns in the POSTPROCESSING SELECTION table: " + available_ColumnsInPostprocessingSelectionTable.size());

                //Loop through all found columns
                for (WebElement element : available_ColumnsInPostprocessingSelectionTable) {
                    ColumnCount++; //column counter
                    if (neededColumns == 1) {
                        System.out.println("Counter of Needed columns in the POSTPROCESSING SELECTION table: " + neededColumns);
                        try {//check if every column contains column title text

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));

                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the POSTPROCESSING SELECTION table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Postprocessing Action ID")) {
                                name_TableColumn = "Postprocessing Action ID";
                            }


                            switch (name_TableColumn) {
                                // case "\n\nMaterial\n\n\n ":
                                case "Postprocessing Action ID":

                                    List<WebElement> rows_PostprocessingSelectionTable = driver.findElements(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')]/div/div[3]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/td[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/child::tr"));
                                    int rows_Number = rows_PostprocessingSelectionTable.size();

                                    System.out.println("Number of rows in the POSTPROCESSING SELECTION table: " + rows_Number);
                                    logger.info("Number of rows in the POSTPROCESSING SELECTION table: " + rows_Number);


                                    for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found

                                        boolean rowFound = false;
                                        String textValue = "";

                                        try {

                                            //Find input field below the "Postprocessing Action ID" column title
                                            WebElement field_ActionID = driver.findElement(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')]/div/div[3]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/td[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[" + i + "]/td[" + ColumnCount + "]/div/span/span"));

                                            textValue = field_ActionID.getAttribute("innerText");
                                            rowFound = true;

                                            System.out.println("The value in 'Postprocessing Action ID' column in row# " + i + " is equal to: " + textValue);
                                            logger.info("The value in 'Postprocessing Action ID' column in row# " + i + " is equal to: " + textValue);

                                        } catch (Exception e) {
                                            System.out.println("Failed to find  row #" + i + " in POSTPROCESSING SELECTION table!");
                                            logger.warn("Failed to find  row #" + i + " in POSTPROCESSING SELECTION table: " + e.getMessage());
                                        }

                                        if (rowFound == true) {
                                            System.out.println("Value from 'Postprocessing Action ID' column of POSTPROCESSING SELECTION table, row #" + i + " is:  " + textValue.toUpperCase());

                                            if (textValue.equals("ORDER")) {
                                                if (read_PostprocessingSelectionCheckMarks_Table(i)==true) {//if "Order" parameter has selected check-box
                                                    Test_Cases.cell_Order = true;
                                                }
                                            }
                                            if (textValue.equals("MAIL")) {
                                                if (read_PostprocessingSelectionCheckMarks_Table(i)==true) {//if "Order" parameter has selected check-box
                                                    Test_Cases.cell_Mail = true;
                                                }

                                            }

                                        }
                                    }
                                    neededColumns++;

                                    break;

                                default:
                            }

                        }
                    } else {
                        return;// break the cycle when all required fields have been filled in
                    }
                }

                // driver.switchTo().defaultContent();
            }


        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the LOG table of 'RESULTS OF CSR ANALYSIS' popup: " + e.getMessage());
        }
    }


    public boolean read_PostprocessingSelectionCheckMarks_Table(int row_Count) {

        int rowsCount_LogTable = 0;
        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;
        int blockCount = 0;
        boolean rowSelected = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
                driver.switchTo().defaultContent();

                //Get all columns in the "Postprocessing Selection" table
                List<WebElement> available_ColumnsInPostprocessingSelectionTable = driver.findElements(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[1]/td[2]/descendant::table[contains (@id, 'mrss-hdr-none-content')]/tbody/tr/descendant::th"));
                System.out.println("Number of columns in the POSTPROCESSING SELECTION table: " + available_ColumnsInPostprocessingSelectionTable.size());
                logger.info("Number of columns in the POSTPROCESSING SELECTION table: " + available_ColumnsInPostprocessingSelectionTable.size());

                //Loop through all found coulmns
                for (WebElement element : available_ColumnsInPostprocessingSelectionTable) {
                    ColumnCount++; //column counter
                    if (neededColumns == 1) {
                        System.out.println("Counter of Needed columns in the POSTPROCESSING SELECTION table: " + neededColumns);
                        try {//check if every column contains column title text

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));

                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the POSTPROCESSING SELECTION table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Postprocessing Action ID")) {
                                name_TableColumn = "Postprocessing Action ID";
                            }


                            switch (name_TableColumn) {
                                // case "\n\nMaterial\n\n\n ":
                                case "Postprocessing Action ID":

                                    List<WebElement> rows_PostprocessingSelectionTable = driver.findElements(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/td[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/descendant::tr"));
                                    int rows_Number = rows_PostprocessingSelectionTable.size();

                                    System.out.println("Number of rows in the POSTPROCESSING SELECTION table: " + rows_Number);
                                    logger.info("Number of rows in the POSTPROCESSING SELECTION table: " + rows_Number);


                                    for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found

                                        boolean rowFound = false;
                                        String textValue = "";

                                        try {

                                            //Find input field below the "Postprocessing Action ID" column title
                                            WebElement field_ActionID = driver.findElement(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/td[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[" + row_Count + "]/td[" + ColumnCount + "]/div/span/span"));

                                            textValue = field_ActionID.getAttribute("innerText");
                                            rowFound = true;

                                            System.out.println("The value in 'Postprocessing Action ID' column in row# " + i + " is equal to: " + textValue);
                                            logger.info("The value in 'Postprocessing Action ID' column in row# " + i + " is equal to: " + textValue);

                                        } catch (Exception e) {
                                            System.out.println("Failed to find  row #" + i + " in POSTPROCESSING SELECTION table!");
                                            logger.warn("Failed to find  row #" + i + " in POSTPROCESSING SELECTION table: " + e.getMessage());
                                        }

                                        if (rowFound == true) {
                                            System.out.println("Value from 'Postprocessing Action ID' column of POSTPROCESSING SELECTION table, row #" + i + " is:  " + textValue.toUpperCase());

                                            if (textValue.equals("ORDER")) {
                                                Test_Cases.cell_Order = true;
                                            }
                                            if (textValue.equals("MAIL")) {
                                                Test_Cases.cell_Mail = true;
                                            }

                                        }
                                    }
                                    neededColumns++;

                                    break;

                                default:
                            }

                        }
                    }
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'URLSPW-1')][contains(@inuse, 'true')]")));

                //Get all columns in the "Postprocessing Selection" table
                List<WebElement> available_ColumnsInPostprocessingSelectionTable = driver.findElements(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')]/div/div[3]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[1]/td[2]/descendant::table[contains (@id, 'mrss-hdr-none-content')]/tbody/tr/descendant::th"));
                System.out.println("Number of columns in the POSTPROCESSING SELECTION table: " + available_ColumnsInPostprocessingSelectionTable.size());
                logger.info("Number of columns in the POSTPROCESSING SELECTION table: " + available_ColumnsInPostprocessingSelectionTable.size());

                //Loop through all found columns
                for (WebElement element : available_ColumnsInPostprocessingSelectionTable) {
                    ColumnCount++; //column counter
                    if (neededColumns == 1) {
                        System.out.println("Counter of Needed columns in the POSTPROCESSING SELECTION table: " + neededColumns);
                        try {//check if every column contains column title text

                            element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));

                            elAvailability = true;
                        } catch (NoSuchElementException e) {
                            elAvailability = false;
                        }

                        //if column contains a column title text then read it
                        if (elAvailability) {

                            String name_TableColumn = element.getAttribute("innerText");
                            System.out.println("SPAN element Text in the POSTPROCESSING SELECTION table: " + name_TableColumn);

                            //Trim found column title to text only
                            if (name_TableColumn.contains("Execute Action")) {
                                name_TableColumn = "Execute Action";
                            }


                            switch (name_TableColumn) {
                                // case "\n\nMaterial\n\n\n ":
                                case "Execute Action":

                                    List<WebElement> rows_PostprocessingSelectionTable = driver.findElements(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')]/div/div[3]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/td[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/child::tr"));
                                    int rows_Number = rows_PostprocessingSelectionTable.size();

                                    System.out.println("Number of rows in the POSTPROCESSING SELECTION table: " + rows_Number);
                                    logger.info("Number of rows in the POSTPROCESSING SELECTION table: " + rows_Number);


                                    for (int i = 1; i <= rows_Number; i++) {//iterate through all rows found


                                        String textValue = "";

                                        try {

                                            //Find input field below the "Execute Action" column title
                                            WebElement field_ActionID = driver.findElement(By.ByXPath.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')]/div/div[3]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/tbody[1]/tr[2]/td[2]/descendant::table[contains (@id, 'mrss-cont-none-content')]/tbody/tr[" + row_Count + "]/td[" + ColumnCount + "]/div/span"));

                                            textValue = field_ActionID.getAttribute("class");

                                            System.out.println("The class value in 'Execute Action' column in row# " + i + " is equal to: " + textValue);
                                            logger.info("The class value in 'Execute Action' column in row# " + i + " is equal to: " + textValue);

                                            if (textValue.contains("lsCheckBox--checked")) {
                                                rowSelected = true;
                                            }


                                        } catch (Exception e) {
                                            System.out.println("Failed to find  row #" + i + " in POSTPROCESSING SELECTION table while searching selection check-marks in 'Execute Action' column!");
                                            logger.warn("Failed to find  row #" + i + " in POSTPROCESSING SELECTION table while searching selection check-marks in 'Execute Action' column: " + e.getMessage());
                                        }

                                    }
                                    neededColumns++;

                                    break;

                                default:
                            }

                        }
                    }
                }

                // driver.switchTo().defaultContent();
            }

        } catch (NoSuchElementException e) {
            logger.error("No such element - Error while retrieving the column titles and fields in the LOG table of 'RESULTS OF CSR ANALYSIS' popup: " + e.getMessage());
        }
        return rowSelected;
    }



    public void click_SaveBtn() {

        try {
            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
                //Exit iframe to have access to the main window's web-elements
               // driver.switchTo().defaultContent();

                WebElement btn_SavePersonalization = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL3_POP_CONT')]/descendant::div[contains (@class, 'lsTwhlFooter lsTbarLarge urPWFooterBottomLine')]/descendant::span[contains (text(), 'Save')]/ancestor::div[1]"));


                //action.moveToElement(input_Width_1).click().build().perform();
                js.executeScript("arguments[0].click();", btn_SavePersonalization);
                //btn_New.click();

                logger.info("The 'SAVE' button (on 'PERSONALIZATION' popup for POSTPROCESSING) was clicked to save Default Settings.");

                //wait till landing page is loaded again after closing "Personalization" popup
                waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "POSTPROCESSING popup after closing PERSONALIZATION popup");
                Thread.sleep(1000);
            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //Exit iframe to have access to the main window's web-elements
                //driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
                //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'URLSPW-2')][contains (@inuse, 'true')]")));

                // WebElement btn_SavePersonalization = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL3_POP_CONT')]/div[1]/descendant::div[contains (text(), 'Personalization')]/ancestor::div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::span[contains (text(), 'Reset to Default')]/ancestor::div[1]"));
                WebElement btn_SavePersonalization = driver.findElement(By.xpath("//div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'lsTwhlFooter lsTbarLarge urPWFooterBottomLine')]/descendant::td[contains (@class, 'lsPopupWindow_Footer-buttons')]/descendant::span[contains (text(), 'Save')]/ancestor::div[1]"));


                //action.moveToElement(input_Width_1).click().build().perform();
                js.executeScript("arguments[0].click();", btn_SavePersonalization);
                //btn_New.click();

                logger.info("The 'SAVE' button (on 'PERSONALIZATION' popup for POSTPROCESSING) was clicked to save Default Settings.");

                //wait till landing page is loaded again after closing "Personalization" popup
                waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "POSTPROCESSING popup after closing PERSONALIZATION popup");
                Thread.sleep(1000);

                //Exit iframe to have access to the main window's web-elements
                //driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while clicking on SAVE button on PERSONALIZATION popup for POSTPROCESSING: " + e.getMessage());
        }

    }


    public void check_SetToDefaultBtn_PersonolizedPopup_PostprocessingPanel() {

        try {
            //check whether the "Set to Default" button is disabled or enabled.
            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                //Exit iframe to have access to the main window's web-elements
                //driver.switchTo().defaultContent();

                WebElement btn_SetToDefault_Persnlzd_Postprocessing = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL3_POP_CONT')]/div[1]/div/div[4]/descendant::span[contains (text(), 'Reset to Default')]/ancestor::div[1]"));

                String btn_Status = btn_SetToDefault_Persnlzd_Postprocessing.getAttribute("class");
                System.out.println("Class value for 'Set to Default' button: " + btn_Status);

                if (!btn_Status.contains("lsButton--disabled")) {//if the "Set to Default" button is enabled then click on it and wait/check that "Available Columns" section is empty

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup) was detected to be ENABLED.");

                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_SetToDefault_Persnlzd_Postprocessing);
                    //btn_New.click();

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup) was clicked.");

                    //waitTillDescendentElementsLessThanExpected(10, "//span[contains (text(), 'Available Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/descendant::tr", "'AVAILABLE COLUMNS' with EMPTY RECORDS", 1);

                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "DATA COLLATION PAGE after closing PERSONALIZATION popup on LOCATOR PANEL");
                    Thread.sleep(1000);

                    //re-open "Personolized" popup to customize WIDTH of the columns
                    click_PersonalizeBtn_PostprocessingPanel();

                } else {
                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for POSTPROCESSING) was detected to be DISABLED by Default.");
                }

            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //Exit iframe to have access to the main window's web-elements
               // driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
               // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'URLSPW-2')][contains (@inuse, 'true')]")));

                WebElement btn_SetToDefault_Persnlzd_Postprocessing = driver.findElement(By.xpath("//div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'lsTwhlFooter lsTbarLarge urPWFooterBottomLine')]/descendant::td[contains (@class, 'lsPopupWindow_Footer-buttons')]/table/tbody/tr/td[2]/descendant::span[contains (text(), 'Reset to Default')]/ancestor::div[1]"));

                String btn_Status = btn_SetToDefault_Persnlzd_Postprocessing.getAttribute("class");
                System.out.println("Class value for 'Set to Default' button: " + btn_Status);

                if (!btn_Status.contains("lsButton--disabled")) {//if the "Set to Default" button is enabled then click on it and wait/check that "Available Columns" section is empty

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup) was detected to be ENABLED.");

                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_SetToDefault_Persnlzd_Postprocessing);
                    //btn_New.click();

                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup) was clicked.");

                    // waitTillDescendentElementsLessThanExpected(10, "//span[contains (text(), 'Available Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')]/tbody[contains (@id, 'content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/descendant::tr", "'AVAILABLE COLUMNS' with EMPTY RECORDS", 1);
                    //Exit iframe to have access to the main window's web-elements
                   // driver.switchTo().defaultContent();

                    //wait till landing page is loaded again after closing "Personalization" popup
                    waitTillDescendentElementsAvailable(300, "//html/body/descendant::table", "POSTPROCESSING POPUP after closing PERSONALIZATION popup");
                    Thread.sleep(1000);

                    //re-open "Personalized" popup to customize WIDTH of the columns
                    click_PersonalizeBtn_PostprocessingPanel();

                } else {
                    logger.info("The 'Set to Default' button (on 'PERSONALIZATION' popup for POSTPROCESSING) was detected to be DISABLED by Default.");
                }

                //Exit iframe to have access to the main window's web-elements
               // driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Unable to process the status of 'SET TO DEFAULT' button on PERSONALIZATION popup belonging to POSTPROCESSING panel: " + e.getMessage());
        }

    }

    public void click_PersonalizeBtn_PostprocessingPanel() {

        try {

            //js.executeScript("arguments[0].click();", btn_Personalize_DC);
            //btn_Personalize_Postprocessing.click();

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                WebElement btn_Personalize_Postprocessing = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[1]/div/div[3]/table/tbody/tr/td/div/div[1]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::div[contains (@title, 'Settings')]"));

                btn_Personalize_Postprocessing.click();

                logger.info("PERSONALIZE button was SUCCESSFULLY clicked on the POSTPROCESSING popup.");

                waitTillDescendentElementsAvailable(5, "//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL3_POP_CONT')]/div[1]/descendant::span[contains (text(), 'Displayed Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')]/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/descendant::tr", "PERSONALIZATION POPUP");


            }
            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //Exit iframe to have access to the main window's web-elements
                //driver.switchTo().defaultContent();
                //When using an iframe, you will first have to switch to the iframe, before selecting the elements of that iframe.
               // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'URLSPW-1')][contains (@inuse, 'true')]")));

                WebElement btn_Personalize_Postprocessing = driver.findElement(By.xpath("//div[contains (@id, 'ALL_POPUPS')]/div[contains (@id, 'WDWL2_POP_CONT')]/div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::table[contains (@shscrollcontentid, 'mrss-hdr-none-content')]/thead/tr[2]/descendant::div[contains (@class, 'rightItems')]/descendant::div[contains (@title, 'Personalize')]"));

                btn_Personalize_Postprocessing.click();

                logger.info("PERSONALIZE button was SUCCESSFULLY clicked on the POSTPROCESSING popup.");

                // waitTillDescendentElementsAvailable(30, "//div[contains (@class, 'lsPopupWindow lsPopupWindow--dialog')][contains (@ct, 'PW')]/div/div[contains (@class, 'urPWContent')]/descendant::span[contains (text(), 'Displayed Columns')]/ancestor::table[contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiGeneric urHtmlTableReset')]/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/descendant::tr", "PERSONALIZATION POPUP");
                waitForExtAjaxIsReadyState(15, "PERSONALIZATION POPUP");
                Thread.sleep(3000);

                //Exit iframe to have access to the main window's web-elements
               // driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Failed to open 'PERSONALIZATION' popup from the 'POSTPROCESSING'popup: " + e.getMessage());
        }
    }

    public void click_CheckBtn_StatusCheck() {

        boolean statusAvailability = false;

        try {

            //Scroll back to the top of the page
            js.executeScript("arguments[0].scrollIntoView();", header_StandardDataCollation);
           /* js.executeScript("window.scrollBy(0,-250)");
            js.executeScript("window.scrollTo(0, -250)");
            js.executeScript("window.scrollTo(document.body.scrollHeight,0)");*/

            //driver.findElement(By.xpath("//table[@class='urMatrixLayout urHtmlTableReset']/tbody[@class='urLinStd']/tr[1]/td/div[@class='urPgHSmall sapLSStandalonePH']/div[2][@class='lsPageHeader--item urPgHITrans']/div/div/descendant::*[contains(text(), 'Sales partners were supplemented from customer master of customer') or contains(text(), 'Purchasing organization GPUR was added')]"));
            statusAvailability = true;
        } catch (NoSuchElementException e) {
            statusAvailability = false;
        }


        try {
            //wait till new status is displayed after clicking on "Check" button
            //explicitWaitsUntilElementPresent(20, "//span[contains(text(), 'Sales partners were supplemented from customer master of customer') or contains(text(), 'Purchasing organization GPUR was added')]", "Status update after clicking on CHECK button");


            //check if the document's status of "Sales partners were supplemented from customer master of customer" or "Purchasing organization GPUR was added" is displayed
            if (statusAvailability == true) {

                //logger.info("The expected pre-conditioned status before clicking on CHECK button is displayed.");
                logger.info("The pre-conditioned status before clicking on CHECK button is hidden as expected.");

                js.executeScript("arguments[0].click();", btn_Check);
                //action.click(btn_Check).build().perform();

            } else {
                logger.error("The pre-conditioned status should be hidden before clicking on CHECK button!");
            }
        } catch (Exception e) {

            logger.error("Unable to detect/click on 'CHECK' button: " + e.getMessage());

        }
    }


    public void click_ReleaseBtn_StatusCheck() {

        AddItemTable addItmPg = new AddItemTable(driver); //Instantiating object of "AddItemTable" class

        try {
            //wait till new status is displayed after clicking on "Release" button
            explicitWaitsUntilElementPresent(20, "//span[contains(text(), 'PAT document is complete')]", "Status update after clicking on RELEASE button");

            //check if the document's current status is "Sales partners were supplemented from customer master of customer"
            if (addItmPg.check_IfElementIsPresent("//span[contains(text(), 'PAT document is complete')]")) {

                logger.info("The preconditioned status 'PAT document is complete' is displayed as expected before clicking on RELEASE button");

                js.executeScript("arguments[0].click();", btn_Release);
                //action.click(btn_Check).build().perform();

            } else {
                logger.error("The document does not appear to have a pre-conditioned status of 'PAT document is complete'.");
            }
        } catch (Exception e) {

            logger.error("Unable to detect/click on 'RELEASE' button: " + e.getMessage());

        }
    }


    public void click_SaveBtn_StatusCheck() {

        AddItemTable addItmPg = new AddItemTable(driver); //Instantiating object of "AddItemTable" class

        try {
            //wait till new status is displayed after clicking on "Release" button
            explicitWaitsUntilElementPresent(20, "//span[contains(text(), 'PAT document was released')]", "Status update after clicking on SAVE button");

            //wait till "Process" button gets enabled
            //explicitWaitsUntilElementPresent(20, "//span[contains(text(), 'PAT document was released')]", "Status update after clicking on SAVE button");


            //check if the document's current status is "Sales partners were supplemented from customer master of customer"
            if (addItmPg.check_IfElementIsPresent("//span[contains(text(), 'PAT document was released')]")) {

                logger.info("The preconditioned status 'PAT document was released' is displayed as expected before clicking on RELEASE button");

                js.executeScript("arguments[0].click();", btn_Save);
                //action.click(btn_Check).build().perform();

            } else {
                logger.error("The document does not appear to have a pre-conditioned status of 'PAT document was released'.");
            }
        } catch (Exception e) {

            logger.error("Unable to detect/click on 'SAVE' button: " + e.getMessage());

        }
    }

    public void click_ProcessBtn() {

        AddItemTable addItmPg = new AddItemTable(driver); //Instantiating object of "AddItemTable" class

        try {

            js.executeScript("arguments[0].click();", btn_Process);
            //action.click(btn_Check).build().perform();

            logger.info("The PROCESS button was clicked SUCCESSFULLY");

        } catch (Exception e) {
            logger.error("Unable to detect/click on 'PROCESS' button: " + e.getMessage());
        }
    }

    public void click_ProcessBtn_StatusCheck() {

        AddItemTable addItmPg = new AddItemTable(driver); //Instantiating object of "AddItemTable" class

        try {
            //wait till "Process" button gets enabled
            explicitWaitsUntilElementPresent(20, "//tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[15]/div[contains(@class, 'lsButton lsTbarBtnStd urInlineMetricTop urNoUserSelect urBtnRadius  urBtnStdValign  lsButton--toolbar-image  lsButton--up lsButton--design-standard  urBtnStd') or ('lsButton lsTbarBtnStd urNoUserSelect urBtnRadius  urBtnStdValign  urInlineMetricTop  urBtnStdWithImg urBtnStd')]", "PROCESS button gets ENABLED");

            //check if the document's current status is "Sales partners were supplemented from customer master of customer"
            if (addItmPg.check_IfElementIsPresent("//span[contains(text(), 'was saved')]")) {

                logger.info("The preconditioned status 'Document '" + Test_Cases.doc_Num + "' was saved' is displayed as expected before clicking on PROCESS button");

                js.executeScript("arguments[0].click();", btn_Process);
                //action.click(btn_Check).build().perform();

            } else {
                logger.error("The document does not appear to have a pre-conditioned status of 'Document " + Test_Cases.doc_Num + " was saved'.");
            }
        } catch (Exception e) {

            logger.error("Unable to detect/click on 'PROCESS' button: " + e.getMessage());

        }
    }


    public void click_ProcessBtn_StatusCheck_AfterCancellation() {

        try {
            //wait till "Process" button gets enabled
            // explicitWaitsUntilElementPresent(20, "//tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[15]/div[contains(@class, 'lsButton lsTbarBtnStd urInlineMetricTop urNoUserSelect urBtnRadius  urBtnStdValign  lsButton--toolbar-image  lsButton--up lsButton--design-standard  urBtnStd') or ('lsButton lsTbarBtnStd urNoUserSelect urBtnRadius  urBtnStdValign  urInlineMetricTop  urBtnStdWithImg urBtnStd')]", "PROCESS button gets ENABLED");
            waitTillWebElementChangesInitialProperty(20, "//div[@class='lsPageHeader--item urPgHITrans']/table[contains (@class, 'urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar')]/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[15]/div", "urBtnStdDsbl", "PROCESS button gets ENABLED");
            js.executeScript("arguments[0].click();", btn_Process);
            //action.click(btn_Check).build().perform();

        } catch (Exception e) {
            logger.error("Unable to detect/click on 'PROCESS' button: " + e.getMessage());
        }
    }


    public boolean check_CheckBtnEnabledDisabledStatus(String docType) {

        boolean btn_Status = false;

        try {
            String btn_StatusIndicator = btn_CheckContainer.getAttribute("class");
            System.out.println(docType.toUpperCase() + " Class of CHECK button: " + btn_StatusIndicator);

            if (!btn_StatusIndicator.contains("urBtnStdDsbl")) {

                btn_Status = true;
                logger.info(docType.toUpperCase() + " Button 'CHECK' is in ENABLED status.");
            } else {
                logger.error(docType.toUpperCase() + " Button 'CHECK' is in DISABLED status.");
            }

        } catch (Exception e) {
            logger.error(docType.toUpperCase() + " Button 'CHECK' is not detected: " + e.getMessage());
        }

        return btn_Status;
    }


    public boolean check_ReleaseBtnEnabledDisabledStatus(String docType) {

        boolean btn_Status = false;

        try {
            String btn_StatusIndicator = btn_ReleaseContainer.getAttribute("class");
            System.out.println(docType.toUpperCase() + " Class of RELEASE button: " + btn_StatusIndicator);

            if (!btn_StatusIndicator.contains("urBtnStdDsbl")) {
                btn_Status = true;
                logger.info(docType.toUpperCase() + " Button 'RELEASE' is in ENABLED status.");
            } else {
                logger.error(docType.toUpperCase() + " Button 'RELEASE' is in DISABLED status.");
            }

        } catch (Exception e) {
            logger.error(docType.toUpperCase() + " Button 'RELEASE' is not detected: " + e.getMessage());
        }

        return btn_Status;
    }


    public boolean check_SaveBtnEnabledDisabledStatus(String docType) {

        boolean btn_Status = false;

        try {
            String btn_StatusIndicator = btn_SaveContainer.getAttribute("class");
            System.out.println(docType.toUpperCase() + " Class of SAVE button: " + btn_StatusIndicator);

            if (!btn_StatusIndicator.contains("urBtnStdDsbl")) {
                btn_Status = true;
                logger.info(docType.toUpperCase() + " Button 'SAVE' is in ENABLED status.");
            } else {
                logger.error(docType.toUpperCase() + " Button 'SAVE' is in DISABLED status.");
            }

        } catch (Exception e) {
            logger.error(docType.toUpperCase() + " Button 'SAVE' is not detected: " + e.getMessage());
        }

        return btn_Status;
    }


    public boolean check_ProcessBtnEnabledDisabledStatus_0() {

        boolean btn_Status = false;

        try {
            String btn_StatusIndicator = btn_ProcessContainer.getAttribute("class");
            System.out.println("Class of PROCESS button: " + btn_StatusIndicator);

            if (!btn_StatusIndicator.contains("urBtnStdDsbl")) {
                btn_Status = true;
                logger.info("Button 'PROCESS' is in ENABLED status after REVERSE action.");
            } else {
                logger.error("Button 'PROCESS' is in DISABLED status after REVERSE action.");
            }

        } catch (Exception e) {
            logger.error("Button 'PROCESS' is not detected after REVERSE action: " + e.getMessage());
        }

        return btn_Status;
    }


    public boolean check_ProcessBtnEnabledDisabledStatus(String docType) {

        boolean btn_Status = false;

        try {
            String btn_StatusIndicator = btn_ProcessContainer.getAttribute("class");
            System.out.println(docType.toUpperCase() + " Class of PROCESS button: " + btn_StatusIndicator);

            if (!btn_StatusIndicator.contains("urBtnStdDsbl")) {
                btn_Status = true;
                logger.info(docType.toUpperCase() + " Button 'PROCESS' is in ENABLED status.");
            } else {
                logger.error(docType.toUpperCase() + " Button 'PROCESS' is in DISABLED status.");
            }

        } catch (Exception e) {
            logger.error(docType.toUpperCase() + " Button 'PROCESS' is not detected: " + e.getMessage());
        }

        return btn_Status;
    }


    public boolean check_ReverseBtnEnabledDisabledStatus(String docType) {

        boolean btn_Status = false;

        try {
            String btn_StatusIndicator = btn_ReverseContainer.getAttribute("class");
            System.out.println(docType.toUpperCase() + " Class of REVERSE button: " + btn_StatusIndicator);

            //OFQ//if (btn_StatusIndicator.equals("lsButton lsTbarBtnStd urInlineMetricTop urNoUserSelect urBtnRadius  urBtnStdValign  lsButton--toolbar-image  lsButton--up lsButton--design-standard  urBtnStd")) {
            if (!btn_StatusIndicator.contains("urBtnStdDsbl")) {
                btn_Status = true;
                logger.info(docType.toUpperCase() + " Button 'REVERSE' is in ENABLED status.");
            } else {
                logger.error(docType.toUpperCase() + " Button 'REVERSE' is in DISABLED status.");
            }

        } catch (Exception e) {
            logger.error(docType.toUpperCase() + " Button 'REVERSE' is not detected: " + e.getMessage());
        }

        return btn_Status;
    }


    public void click_ReverseBtn_StatusCheck_RoundTwo() {

        AddItemTable addItmPg = new AddItemTable(driver); //Instantiating object of "AddItemTable" class

        try {
            //wait till "Reverse" button gets enabled
            //OFQ//explicitWaitsUntilElementPresent(20, "//tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[18]/div[contains(@class, 'lsButton lsTbarBtnStd urInlineMetricTop urNoUserSelect urBtnRadius  urBtnStdValign  lsButton--toolbar-image  lsButton--up lsButton--design-standard  urBtnStd')]", "REVERSE button gets ENABLED");
            waitTillWebElementChangesInitialProperty(20, "//div[@class='lsPageHeader--item urPgHITrans']/table[contains (@class, 'urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar')]/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[18]/div", "urBtnStdDsbl", "REVERSE button gets ENABLED");

            Thread.sleep(2000);

            js.executeScript("arguments[0].click();", btn_Reverse2);
            //action.click(btn_Reverse2).build().perform();
            //btn_Reverse2.click();

            logger.info("'REVERSE' button was successfully clicked.");

        } catch (Exception e) {

            logger.error("Unable to detect/click on 'REVERSE' button: " + e.getMessage());

        }
    }


    public void click_ReverseBtn_StatusCheck() {

        AddItemTable addItmPg = new AddItemTable(driver); //Instantiating object of "AddItemTable" class

        try {
            //wait till "Reverse" button gets enabled
            //OFQ//explicitWaitsUntilElementPresent(20, "//tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[18]/div[contains(@class, 'lsButton lsTbarBtnStd urInlineMetricTop urNoUserSelect urBtnRadius  urBtnStdValign  lsButton--toolbar-image  lsButton--up lsButton--design-standard  urBtnStd')]", "REVERSE button gets ENABLED");
            waitTillWebElementChangesInitialProperty(20, "//div[@class='lsPageHeader--item urPgHITrans']/table[contains (@class, 'urTWhlFlat lsTItmOvflFlat urHtmlTableReset urTBar lsToolbar')]/tbody/tr/td[1]/div[@class='lsTbarOvfl']/span[18]/div", "urBtnStdDsbl", "REVERSE button gets ENABLED");

            //check if the document's current status is "PAT document <number> was saved"
            if (addItmPg.check_IfElementIsPresent("//span[contains(text(), 'was posted')]")) {

                logger.info("The preconditioned status 'Document '" + Test_Cases.doc_Num + "' was posted' is displayed as expected before clicking on REVERSE button");

                js.executeScript("arguments[0].click();", btn_Reverse);
                //action.click(btn_Check).build().perform();

            } else {
                logger.error("The document does not appear to have a pre-conditioned status of 'PAT document #### was posted'.");
            }
        } catch (Exception e) {

            logger.error("Unable to detect/click on 'REVERSE' button: " + e.getMessage());

        }
    }


    public boolean retrieve_DocProcessingStatus() {

        boolean post_processing_Status = false;

        try {
            //Find "Stock Transfer in Stock in Transit" check-box and check if it is unselected
            String status_Text = textStatus_Panel.getText();
            System.out.println("Status of the document after clicling on 'PROCESSING' button is: " + status_Text);

            if (status_Text.contains("01 items could not be successfully posted")) {// if the processing has failed

                logger.warn("PROCESSING did NOT finish successfully!");
                post_processing_Status = false;

            } else {
                if (status_Text.contains("PAT document " + Test_Cases.doc_Num + " was posted")) {// if the processing has succeeded
                    logger.info("PROCESSING finish SUCCESSFULLY!");
                    post_processing_Status = true;
                }
            }

        } catch (Exception e) {
            logger.error("Error while retrieving document processing status after clicking PROCESSING button: " + e.getMessage());
        }

        return post_processing_Status;
    }

}
