package com.pages;

import init.settings.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.project.Test_Cases;

import java.util.List;

import static ax.generic.Waitings.*;

public class StandardOrderPage extends PageObject {

    public Logger logger = Logger.getLogger(StandardOrderPage.class);

    //Locating "Stock Transp. Order" value in the "Order Type" dropdown
    @FindBy(xpath = "//div[@id='MEPO_TOPLINE-BSARTSAPLMEGUI_ei']/div[1]/div/descendant::div[contains (text(), 'Stock Transp. Order')]")
    private WebElement dropdown_OrderType_value;


    //Constructor
    public StandardOrderPage(WebDriver driver) {
        super(driver);

    }

    JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions action = new Actions(driver);


    public void click_ContinueBtn_OnCreateStandardOrder_Popup() {

        if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {

            try {

                WebElement button_Continue = driver.findElement(By.xpath("//div[@id='webguiPopups']/div/div/div[4]/div/table/tbody/descendant::td[contains (@id, 'webguiToolbar1-menuindicator')]/descendant::div[contains (@title, 'Continue (Enter)')]"));

                //action.moveToElement(row_One).click().build().perform();
                //js.executeScript("arguments[0].click();", row_One);
                button_Continue.click();

                logger.info("CONTINUE button was SUCCESSFULLY clicked on CREATE STANDARD ORDER popup.");

            } catch (Exception e) {
                logger.error("Error while clicking on CONTINUE button on CREATE STANDARD ORDER popup: " + e.getMessage());
            }
        }

        if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

            try {

                WebElement button_Continue = driver.findElement(By.xpath("//div[contains (@id, 'webguiPage1')]/div/div/div[4]/div/table/tbody/tr/td[3]/div/table/tbody/tr/td[1]/div/span[1]/div[contains (@title, 'Continue (Enter)')]"));

                //action.moveToElement(row_One).click().build().perform();
                //js.executeScript("arguments[0].click();", row_One);
                button_Continue.click();

                logger.info("CONTINUE button was SUCCESSFULLY clicked on CREATE STANDARD ORDER popup.");

            } catch (Exception e) {
                logger.error("Error while clicking on CONTINUE button on CREATE STANDARD ORDER popup: " + e.getMessage());
            }
            driver.switchTo().defaultContent();
        }

    }

    public Integer determine_WhichPopUpDisplayed() {
        Integer popupID = 0;

        if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {

            //check if "Sales Area for Customer" popup is displayed (the table's 1st row)
            try {

                WebElement popup_SalesAreaCustom = driver.findElement(By.xpath("//div[@id='webguiPopups']/div/div/div[3][contains (@class, 'urPWContent')]/descendant::td[@id='userarealist1-content'][1]/div/div[30][contains (text(), 'GNSO')]"));

                popupID = 1;

            } catch (Exception e) {
                logger.error("SALES AREA FOR CUSTOMER popup was not determined as being displayed: " + e.getMessage());
            }


            //check if "Partner Selection" popup is displayed
            try {

                WebElement popup_SalesAreaCustom = driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/div/div/div[3]/descendant::td[contains (@id, 'userarealist1-content')]/descendant::div[contains (text(), 'Bill-to party') or ('Ship-to party')]"));

                popupID = 2;

            } catch (Exception e) {
                logger.error("PARTNER SELECTION popup was not determined as being displayed: " + e.getMessage());
                popupID = 3;
            }
        }

        if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

            //check if "Sales Area for Customer" popup is displayed (the table's 1st row)
            try {

                WebElement popup_SalesAreaCustom = driver.findElement(By.xpath("//div[@id='webguiPopupWindow1-ib']/div[@id='webguiPopupWindow1-cnt']/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/table/tbody/tr/td[1]/div/div[30][contains (text(), 'GNSO')]"));

                popupID = 1;

            } catch (Exception e) {
                logger.error("SALES AREA FOR CUSTOMER popup was not determined as being displayed: " + e.getMessage());
            }


            //check if "Partner Selection" popup is displayed
            try {

                WebElement popup_SalesAreaCustom = driver.findElement(By.xpath("//div[@id='webguiPage1']/div[1]/div/div[3]/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/descendant::div[contains (text(), 'Bill-to party') or ('Ship-to party')]"));

                popupID = 2;

            } catch (Exception e) {
                logger.error("PARTNER SELECTION popup was not determined as being displayed: " + e.getMessage());
                popupID = 3;
            }

            driver.switchTo().defaultContent();

        }

        return popupID;
    }


    public void waitFor_FooterStatus_AfterItemIsAdded() {

        try {
            if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {

                //wait till the status is displayed in the footer of the web-page after adding a new material item
                waitTillElementDetected(15, "//div[@id='webguiPage0']/form[contains (@name, 'webguiform0')]/table/tbody/tr[3]/descendant::td[@id='msgarea-menuindicator']/div/div[1]/span[1]", "STATUS TEXT in the page footer");

                Thread.sleep(1000);

            }

            if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                //wait till the status is displayed in the footer of the web-page after adding a new material item
                waitTillElementDetected(15, "//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[1]", "STATUS TEXT in the page footer");
                Thread.sleep(1000);

                driver.switchTo().defaultContent();

            }
        } catch (Exception e) {
            logger.error("Unable to execute method 'waitFor_FooterStatus_AfterItemIsAdded()': " + e.getMessage());
        }
    }

    public void waitFor_OneOfTheTwoPopups() {

        try {
            if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {

                //wait till the "Sales Area for Customer" popup is displayed (the table's 1st row) or the "Partner Selection" popup
                waitTillElementDetected(15, "//div[@id='webguiPopups']/div/div/div[3][contains (@class, 'urPWContent')]/descendant::td[@id='userarealist1-content'][1]/div/div[30][contains (text(), 'GNSO')] |//div[contains (@id, 'webguiPopups')]/div/div/div[3]/descendant::td[contains (@id, 'userarealist1-content')]/descendant::div[contains (text(), 'Bill-to party') or ('Ship-to party')]", "Either 'SALES AREA FOR CUSTOMER' popup OR 'PARTNER SELECTION' popup");

                Thread.sleep(1000);

            }

            if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

                //wait till the "Sales Area for Customer" popup is displayed (the table's 1st row) or the "Partner Selection" popup
                waitTillElementDetected(15, "//div[@id='webguiPopupWindow1-ib']/div[@id='webguiPopupWindow1-cnt']/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/table/tbody/tr/td[1]/div/div[30][contains (text(), 'GNSO')] |//div[@id='webguiPage1']/div[1]/div/div[3]/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/descendant::div[contains (text(), 'Bill-to party') or ('Ship-to party')]", "Either 'SALES AREA FOR CUSTOMER' popup OR 'PARTNER SELECTION' popup");
                Thread.sleep(1000);

                driver.switchTo().defaultContent();

            }
        } catch (Exception e) {
            logger.error("Unable to execute method 'waitFor_OneOfTheTwoPopups()': " + e.getMessage());
        }
    }


    public void compressRows_PartnerSelectionPopup() {

        try {
            if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {
                WebElement list_ToggleBtn = driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/div/div/div[3]/descendant::td[contains (@id, 'userarealist1-content')]/div/div[5]"));

                String text_ToggleBtn = list_ToggleBtn.getAttribute("title");
                System.out.println("The title of the 'Compress/Open list' button on PARTNER SELECTION popup is: " + text_ToggleBtn);

                if (text_ToggleBtn.contains("List can be compressed")) {

                    //action.moveToElement(row_One).click().build().perform();
                    //js.executeScript("arguments[0].click();", row_One);
                    list_ToggleBtn.click();

                    logger.info("The list has been SUCCESSFULLY COMPRESSED on the PARTNER SELECTION popup.");
                }
            }

            if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

                WebElement list_ToggleBtn = driver.findElement(By.xpath("//div[@id='webguiPage1']/div[1]/div/div[3]/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[5]"));

                String text_ToggleBtn = list_ToggleBtn.getAttribute("title");
                System.out.println("The title of the 'Compress/Open list' button on PARTNER SELECTION popup is: " + text_ToggleBtn);

                if (text_ToggleBtn.contains("List can be Compressed")) {

                    //action.moveToElement(row_One).click().build().perform();
                    //js.executeScript("arguments[0].click();", row_One);
                    list_ToggleBtn.click();

                    logger.info("The list has been SUCCESSFULLY COMPRESSED on the PARTNER SELECTION popup.");

                }

                driver.switchTo().defaultContent();

            }

        } catch (Exception e) {
            logger.error("Error while finding/clicking on 'Compress/Open list' button on 'PARTNER SELECTION' popup: " + e.getMessage());
        }
    }


    public void optimizeColumnWidth_PartnerSelectionPopup() {

        try {
            if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {
                WebElement width_ColumnBtn = driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/div/div/div[4]/descendant::td[contains (@class, 'urPWWaveTop lsPopupWindow_Footer-buttons')]/descendant::td[contains (@id, 'webguiToolbar1-menuindicator')]/div/descendant::div[contains (@title, 'Optimize width')]"));

                //action.moveToElement(row_One).click().build().perform();
                //js.executeScript("arguments[0].click();", row_One);
                width_ColumnBtn.click();

                logger.info("The 'Optimize width' button has been SUCCESSFULLY cliked on 'PARTNER SELECTION' popup.");

            }

            if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

                WebElement width_ColumnBtn = driver.findElement(By.xpath("//div[@id='webguiPage1']/div[1]/div/div[4]/descendant::td[contains (@id, 'webguiPopupWindow1-btns')]/descendant::td[contains (@class, 'lsToolbar--noMenu lsTbarNoMnuInd')]/descendant::div[contains (@title, 'Optimize width')]"));

                //action.moveToElement(row_One).click().build().perform();
                //js.executeScript("arguments[0].click();", row_One);
                width_ColumnBtn.click();

                logger.info("The 'Optimize width' button has been SUCCESSFULLY cliked on 'PARTNER SELECTION' popup.");

                driver.switchTo().defaultContent();

            }

        } catch (Exception e) {
            logger.error("Error while finding/clicking on 'Optimize width' button on 'PARTNER SELECTION' popup: " + e.getMessage());
        }
    }


    public boolean check_PositiveMarker_BillToParty() {
        boolean available_PositiveMarker = false;
        String text_Row2 = null;

        try {
            if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {
                WebElement marker_BillToParty = driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/div/div/div[3]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[51]/*[name()='svg']/*[name()='use']"));
                WebElement name_Row2 = driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/div/div/div[3]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[47]"));

                text_Row2 = name_Row2.getText();

                String class_Text = marker_BillToParty.getAttribute("class");
                System.out.println("The class value for '" + text_Row2.toUpperCase() + "' marker is: " + class_Text);

                if (class_Text.contains("sapSvgIconPositive")) {
                    available_PositiveMarker = true;

                    logger.info("Positive (GREEN) marker is detected for '" + text_Row2.toUpperCase() + "' on 'PARTNER SELECTION' popup as EXPECTED!");

                } else {
                    logger.error("The marker for '" + text_Row2.toUpperCase() + "' is NOT POSITIVE on 'PARTNER SELECTION' popup. The class value is " + class_Text + " against expected 'sapSvgIconPositive'");
                }
            }

            if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

                WebElement marker_BillToParty = driver.findElement(By.xpath("//div[@id='webguiPage1']/div[1]/div/div[3]/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[51]/*[name()='svg']/*[name()='use']"));
                WebElement name_Row2 = driver.findElement(By.xpath("//div[@id='webguiPage1']/div[1]/div/div[3]/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[47]"));

                text_Row2 = name_Row2.getText();
                String class_Text = marker_BillToParty.getAttribute("class");
                System.out.println("The class value for '" + text_Row2.toUpperCase() + "' marker is: " + class_Text);

                if (class_Text.contains("sapSvgIconPositive")) {
                    available_PositiveMarker = true;

                    logger.info("Positive (GREEN) marker is detected for '" + text_Row2.toUpperCase() + "' on 'PARTNER SELECTION' popup as EXPECTED!");

                } else {
                    logger.error("The marker for '" + text_Row2.toUpperCase() + "' is NOT POSITIVE on 'PARTNER SELECTION' popup. The class value is " + class_Text + " against expected 'sapSvgIconPositive'");
                }

                driver.switchTo().defaultContent();

            }

        } catch (Exception e) {
            logger.error("Error while determining positive '" + text_Row2.toUpperCase() + "' marker on 'PARTNER SELECTION' popup: " + e.getMessage());
        }

        return available_PositiveMarker;
    }


    public boolean check_PositiveMarker_ShipToParty() {
        boolean available_PositiveMarker = false;
        String text_Row2 = null;

        try {
            if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {
                WebElement marker_ShipToParty = driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/div/div/div[3]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[65]/*[name()='svg']/*[name()='use']"));
                WebElement name_Row2 = driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/div/div/div[3]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[61]"));

                text_Row2 = name_Row2.getText();

                String class_Text = marker_ShipToParty.getAttribute("class");
                System.out.println("The class value for '" + text_Row2.toUpperCase() + "' marker is: " + class_Text);

                if (class_Text.contains("sapSvgIconPositive")) {
                    available_PositiveMarker = true;

                    logger.info("Positive (GREEN) marker is detected for '" + text_Row2.toUpperCase() + "' on 'PARTNER SELECTION' popup as EXPECTED!");

                } else {
                    logger.error("The marker for '" + text_Row2.toUpperCase() + "' is NOT POSITIVE on 'PARTNER SELECTION' popup. The class value is " + class_Text + " against expected 'sapSvgIconPositive'");
                }
            }

            if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

                WebElement marker_BillToParty = driver.findElement(By.xpath("//div[@id='webguiPage1']/div[1]/div/div[3]/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[65]/*[name()='svg']/*[name()='use']"));
                WebElement name_Row2 = driver.findElement(By.xpath("//div[@id='webguiPage1']/div[1]/div/div[3]/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/div[61]"));

                text_Row2 = name_Row2.getText();
                String class_Text = marker_BillToParty.getAttribute("class");
                System.out.println("The class value for '" + text_Row2.toUpperCase() + "' marker is: " + class_Text);

                if (class_Text.contains("sapSvgIconPositive")) {
                    available_PositiveMarker = true;

                    logger.info("Positive (GREEN) marker is detected for '" + text_Row2.toUpperCase() + "' on 'PARTNER SELECTION' popup as EXPECTED!");

                } else {
                    logger.error("The marker for '" + text_Row2.toUpperCase() + "' is NOT POSITIVE on 'PARTNER SELECTION' popup. The class value is " + class_Text + " against expected 'sapSvgIconPositive'");
                }

                driver.switchTo().defaultContent();

            }

        } catch (Exception e) {
            logger.error("Error while determining positive '" + text_Row2.toUpperCase() + "' marker on 'PARTNER SELECTION' popup: " + e.getMessage());
        }

        return available_PositiveMarker;
    }

    public void click_ContinueBtn_PartnerSelectionPopup() {

        try {
            if (Test_Cases.conf_SystemClient.contains("OFQ_100")) {
                WebElement width_ColumnBtn = driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/div/div/div[4]/descendant::td[contains (@class, 'urPWWaveTop lsPopupWindow_Footer-buttons')]/descendant::td[contains (@id, 'webguiToolbar1-menuindicator')]/div/descendant::div[contains (@title, 'Continue (Enter)')]"));

                //action.moveToElement(row_One).click().build().perform();
                //js.executeScript("arguments[0].click();", row_One);
                width_ColumnBtn.click();

                logger.info("The 'Continue' button has been SUCCESSFULLY cliked on 'PARTNER SELECTION' popup.");

                // waitTillWebElementEscapes(15, "//div[contains (@id, 'webguiPopups')]/div", "PARTNER SELECTION popup");
            }

            if (Test_Cases.conf_SystemClient.contains("OGQ_100")) {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

                WebElement width_ColumnBtn = driver.findElement(By.xpath("//div[@id='webguiPage1']/div[1]/div/div[4]/descendant::td[contains (@id, 'webguiPopupWindow1-btns')]/descendant::td[contains (@class, 'lsToolbar--noMenu lsTbarNoMnuInd')]/descendant::div[contains (@title, 'Continue (Enter)')]"));

                //action.moveToElement(row_One).click().build().perform();
                //js.executeScript("arguments[0].click();", row_One);
                width_ColumnBtn.click();

                logger.info("The 'Continue' button has been SUCCESSFULLY cliked on 'PARTNER SELECTION' popup.");

                driver.switchTo().defaultContent();

                waitTillElementDetectedByStyle(15, "//iframe[contains (@name, 'URLSPW-0')]", "display", "none", "PARTNER SELECTION popup");
            }

        } catch (Exception e) {
            logger.error("Error while finding/clicking on 'Continue' button on 'PARTNER SELECTION' popup: " + e.getMessage());
        }
    }


    public void select_RowAndClickChooseBtnOn_SalesAreaForCustomerPopup() {

        boolean row_found = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                //click on 1st table row on "Sales Area For Customer" popup
                try {
                    WebElement row_One = driver.findElement(By.xpath("//div[@id='webguiPopups']/div/div/div[3][contains (@class, 'urPWContent')]/descendant::td[@id='userarealist1-content'][1]/div/div[30][contains (text(), 'GNSO')]"));

                    //action.moveToElement(row_One).click().build().perform();
                    //js.executeScript("arguments[0].click();", row_One);
                    row_One.click();

                    logger.info("The 1st table row on 'SalesAreaForCustomer' popup was SUCCESSFULLY clicked.");

                    row_found = true;


                } catch (Exception e) {
                    logger.error("Error while clicking on the 1st table row on 'SalesAreaForCustomer' popup: " + e.getMessage());
                }


                //click on "Choose" button on "Sales Area For Customer" popup

                if (row_found == true) {
                    try {
                        WebElement btn_Choose = driver.findElement(By.xpath("//div[@id='webguiPopups']/div/div/div[4][contains (@class, 'urPWFooterBottomLine')]/descendant::td[contains (@class, 'urPWWaveTop lsPopupWindow_Footer-buttons')]/div/table/tbody/tr/td[1]/div/span[1]/div[contains (@title, 'Choose (Enter)')]"));

                        //action.moveToElement(btn_New).click().build().perform();
                        js.executeScript("arguments[0].click();", btn_Choose);
                        //btn_New.click();

                        logger.info("The 'CHOOSE' button on 'SalesAreaForCustomer' popup was SUCCESSFULLY clicked.");


                    } catch (Exception e) {
                        logger.error("Error while clicking on the 'CHOOSE' button on 'SalesAreaForCustomer' popup: " + e.getMessage());
                    }
                } else {
                    logger.error("The 1st table row on 'SalesAreaForCustomer' popup is not found!");
                }

            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

                //click on 1st table row on "Sales Area For Customer" popup
                try {
                    WebElement row_One = driver.findElement(By.xpath("//div[@id='webguiPopupWindow1-ib']/div[@id='webguiPopupWindow1-cnt']/descendant::form[contains (@name, 'webguiform1')]/table/tbody/tr/td/table/tbody/tr/td[1]/div/div[30][contains (text(), 'GNSO')]"));

                    //action.moveToElement(btn_New).click().build().perform();
                    // js.executeScript("arguments[0].click();", row_One);
                    row_One.click();

                    logger.info("The 1st table row on 'SalesAreaForCustomer' popup was SUCCESSFULLY clicked.");

                    row_found = true;


                } catch (Exception e) {
                    logger.error("Error while clicking on the 1st table row on 'SalesAreaForCustomer' popup: " + e.getMessage());
                }

                //click on "Choose" button on "Sales Area For Customer" popup
                if (row_found == true) {
                    try {
                        WebElement btn_Choose = driver.findElement(By.xpath("//div[@id='webguiPopupWindow1-ib']/div[@id='webguiPopupWindow1-ftr']/div/table/tbody/tr/td[3][contains (@id, 'webguiPopupWindow1-btns')]/div/table/tbody/tr/td[1]/div/span[1]/div[contains (@title, 'Choose (Enter)')]"));

                        //action.moveToElement(btn_New).click().build().perform();
                        js.executeScript("arguments[0].click();", btn_Choose);
                        //btn_New.click();

                        logger.info("The 'CHOOSE' button on 'SalesAreaForCustomer' popup was SUCCESSFULLY clicked.");


                    } catch (Exception e) {
                        logger.error("Error while clicking on the 'CHOOSE' button on 'SalesAreaForCustomer' popup: " + e.getMessage());
                    }
                } else {
                    logger.error("The 1st table row on 'SalesAreaForCustomer' popup is not found!");
                }

                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Unable to execute method 'select_RowAndClickChooseBtnOn_SalesAreaForCustomerPopup()': " + e.getMessage());
        }

    }


    public boolean check_CustInputIsEnabled() {

        boolean field_enabled = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {
                    WebElement input_Cust = driver.findElement(By.xpath("//input[contains (@title, 'Sold-To Party')]/ancestor::table[1]"));

                    String class_Value = input_Cust.getAttribute("class");
                    System.out.println("The class value for 'Cust.' input: " + class_Value);

                    if (class_Value.contains("lsField--focus")) {
                        field_enabled = true;
                        logger.info("CUST. input field is ENABLED by default as EXPECTED.");
                    } else {
                        logger.error("CUST. input field is DISABLED! It should be enabled to continue.");
                    }


                } catch (Exception e) {
                    logger.error("Error while retrieving the class value for 'Cust.' input field: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {
                    WebElement input_Cust = driver.findElement(By.xpath("//form[contains (@name, 'webguiform')]/table/tbody/tr[2]/descendant::div[contains (@class, ' IsITSUserAreaCntScrl urBorderBox')]/div/div[1]/descendant::div[contains (@class, 'urScrl urBorderBox')]/div[1][contains (@class, 'urBorderBox')]/div/div[1]/descendant::input[contains (@title, 'Sold-To Party')]/ancestor::table[1]"));

                    String class_Value = input_Cust.getAttribute("class");
                    System.out.println("The class value for 'Cust.' input: " + class_Value);

                    if (!class_Value.contains("lsField--readonly")) {
                        field_enabled = true;
                        logger.info("CUST. input field is ENABLED by default as EXPECTED.");
                    } else {
                        logger.error("CUST. input field is DISABLED! It should be enabled to continue.");
                    }


                } catch (Exception e) {
                    logger.error("Error while retrieving the class value for 'Cust.' input field: " + e.getMessage());
                }

                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Unable to execute method 'check_CustInputIsEnabled()': " + e.getMessage());
        }
        return field_enabled;
    }


    public void click_SaveButton() {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {
                    WebElement button_Save = driver.findElement(By.xpath("//div[@id='webguiPage0']/form[contains (@name, 'webguiform0')]/table/tbody/tr[3]/descendant::td[@id='msgarea-menuindicator']/div/span[1]/div[contains (@title, 'Save')]"));

                    js.executeScript("arguments[0].click();", button_Save);

                    logger.info("SAVE button was clicked to save created STANDARD ORDER.");

                    //wait till "Standard Order has been saved." status is displayed in the footer
                    waitTillElementDetected(15, "//div[@id='webguiPage0']/form[contains (@name, 'webguiform0')]/table/tbody/tr[3]/descendant::td[@id='msgarea-menuindicator']/div/div[1]/span[contains (text(), 'has been saved')]", "FOOTER STATUS 'STANDARD ORDER HAS BEEN SAVED...'");
                    Thread.sleep(1000);


                } catch (Exception e) {
                    logger.error("Error while clicking on 'Save' button while saving a new Standard Order: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));


                    try {

                    WebElement button_Save = driver.findElement(By.xpath("//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[3]/span[1]/div[contains (@title, 'Save')]"));

                    js.executeScript("arguments[0].click();", button_Save);
                    Thread.sleep(1000);

                    logger.info("SAVE button was clicked to save created STANDARD ORDER.");

                    //wait till "Standard Order has been saved." status is displayed in the footer
                    waitTillElementDetected(15, "//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[contains (text(), 'has been saved')]", "FOOTER STATUS 'STANDARD ORDER HAS BEEN SAVED...'");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    logger.error("Error while clicking on 'Save' button while saving a new Standard Order: " + e.getMessage());
                }
                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Unable to execute method 'click_SaveButton()': " + e.getMessage());
        }

    }


    public void read_StandardOrderNumber() {

        try {

            WebElement footer_StatusText = null;

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {
                footer_StatusText = driver.findElement(By.ByXPath.xpath("//div[@id='webguiPage0']/form[contains (@name, 'webguiform0')]/table/tbody/tr[3]/descendant::td[@id='msgarea-menuindicator']/div/div[1]/span[contains (text(), 'has been saved')]"));

                //scroll to bring the footer status text into view
                js.executeScript("arguments[0].scrollIntoView();", footer_StatusText);

                String textWithNumber = footer_StatusText.getText();
                System.out.println("The text from the footer after saving new Standard Order: " + textWithNumber.toUpperCase());

                String num_Order = textWithNumber.replaceAll("[^0-9]+", "");
                System.out.println("Retrieved 'STANDARD ORDER' number = " + num_Order);

            /*Pattern p = Pattern.compile("\\d+"); //read only digits from the string
            Matcher m = p.matcher(textWithNumber);

            System.out.println("Retrived 'STOCK TRANSPORT ORDER' number = " + m.toString());

            String num_StockTransportOrder = m.toString();*/


                if (num_Order != null) {

                    if (num_Order.length() == 4) {

                        Test_Cases.num_StandardOrder = num_Order;

                        logger.info("'Standard Order' number was successfully retrieved from the footer status. It is equal to '" + Test_Cases.num_StandardOrder + "' ");

                        //Scroll back to the top of the page
                        //js.executeScript("arguments[0].scrollIntoView();", header_StandardDataCollation);

                        //create a screenshot of filled out and saved Purchasing Order page
                        //captureWebElementScreenshot("//body", "Purchasing_Order_Page_FilledOut_FullSize.", "FILLED OUT PURCHASING ORDER PAGE", ".\\src\\main\\resources\\current_images\\"); //take a screenshot of created and saved Purchasing Order page
                        Thread.sleep(1000);

                    } else {
                        logger.warn("Retrieved 'Standard Order' number consists of " + num_Order.length() + " instead of expected 4 digits!");
                    }

                } else {
                    logger.error("The 'Standard Order' number (from the footer status) is NULL. This means that the document cannot be created!");
                }

            }

            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                footer_StatusText = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[contains (text(), 'has been saved')]"));

                //scroll to bring the footer status text into view
                js.executeScript("arguments[0].scrollIntoView();", footer_StatusText);

                String textWithNumber = footer_StatusText.getText();
                System.out.println("The text from the footer after saving new Standard Order: " + textWithNumber.toUpperCase());

                String num_Order = textWithNumber.replaceAll("[^0-9]+", "");
                System.out.println("Retrieved 'STOCK TRANSPORT ORDER' number = " + num_Order);

            /*Pattern p = Pattern.compile("\\d+"); //read only digits from the string
            Matcher m = p.matcher(textWithNumber);

            System.out.println("Retrived 'STOCK TRANSPORT ORDER' number = " + m.toString());

            String num_StockTransportOrder = m.toString();*/

                if (num_Order != null) {

                    if (num_Order.length() == 4) {

                        Test_Cases.num_StandardOrder = num_Order;

                        logger.info("'Standard Order' number was successfully retrieved from the footer status. It is equal to '" + Test_Cases.num_StandardOrder + "' ");

                        //Scroll back to the top of the page
                        //js.executeScript("arguments[0].scrollIntoView();", header_StandardDataCollation);

                        //create a screenshot of filled out and saved Purchasing Order page
                        //captureWebElementScreenshot("//form[contains (@id, 'webguiform')]", "Purchasing_Order_Page_FilledOut_FullSize.", "FILLED OUT PURCHASING ORDER PAGE", ".\\src\\main\\resources\\current_images\\"); //take a screenshot of created and saved Purchasing Order page
                        Thread.sleep(1000);

                    } else {
                        logger.warn("Retrieved 'Standard Order' number consists of " + num_Order.length() + " instead of expected 4 digits!");
                    }

                } else {
                    logger.error("The 'Standard Order' number (from the footer status) is NULL. This means that the document cannot be created!");
                }

                driver.switchTo().defaultContent();

            }


        } catch (Exception e) {
            logger.error("Unable to retrieve 'STANDARD ORDER' number from the footer status: " + e.getMessage());
            driver.close();
            driver.quit();
        }
    }


    public void check_AutocompeteFieldsInItemOverviewTable() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {
                    //Get all columns in the RIGHT part of "Add Item" table
                    List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div[3]/div/div[2]/div/div/div/div[1]/table[contains (@ct, 'STCS')][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[1]/tr[1]/td[2]/div/div[2]/table/tbody/tr/descendant::th"));

                    System.out.println("Number of columns in the RIGHT part: " + available_ColumnsInRightPartOfTable.size());

                    //Loop through all found coulmns
                    for (WebElement element : available_ColumnsInRightPartOfTable) {
                        ColumnCount++; //column counter
                        if (neededColumns <= 6) {
                            System.out.println("Counter of Needed columns in the RIGHT part of ITEM OVERVIEW table: " + neededColumns);

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

                                String name_TableColumn = element.getAttribute("title");
                                System.out.println("SPAN element Text in the RIGHT part: " + name_TableColumn);

                                //Trim found column title to text only
                                if (name_TableColumn.contains("Item Cat.")) {
                                    name_TableColumn = "Item Cat.";
                                }
                                if (name_TableColumn.contains("Short Text")) {
                                    name_TableColumn = "Short Text";
                                }
                                if (name_TableColumn.contains("Order Unit")) {
                                    name_TableColumn = "Order Unit";
                                }
                                if (name_TableColumn.contains("Date Cat.")) {
                                    name_TableColumn = "Date Cat.";
                                }
                                if (name_TableColumn.contains("Delivery Date")) {
                                    name_TableColumn = "Delivery Date";
                                }
                                if (name_TableColumn.contains("Material Group")) {
                                    name_TableColumn = "Material Group";
                                }


                                switch (name_TableColumn) {
                                    case "Item Cat.":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Item Cat.");

                                        //Find input field below the "Item Cat." column title and check whether the input field is enabled/editable
                                        WebElement field_ItemCat = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div[3]/div/div[2]/div/div/div/div[1]/table[contains (@ct, 'STCS')][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[contains (@id, '-content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/span[1]"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Item Cat." field
                                        Test_Cases.ItemCategory = field_ItemCat.getAttribute("innerText");
                                        System.out.println("The ITEM CAT. field was autofilled with '" + Test_Cases.ItemCategory + "' value.");

                                        if (!Test_Cases.ItemCategory.isEmpty()) {
                                            logger.info("The ITEM CAT. field was autofilled with '" + Test_Cases.ItemCategory + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field ITEM CAT. is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Short Text":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Short Text");

                                        //Find input field below the "Short Text" column title and check whether the input field is enabled/editable
                                        WebElement field_ShortText = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div[3]/div/div[2]/div/div/div/div[1]/table[contains (@ct, 'STCS')][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[contains (@id, '-content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/span[1]"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Short Text" field
                                        Test_Cases.ShortText = field_ShortText.getAttribute("innerText");
                                        System.out.println("The SHORT TEXT field was autofilled with '" + Test_Cases.ShortText + "' value.");

                                        if (!Test_Cases.ShortText.isEmpty()) {
                                            logger.info("The SHORT TEXT field was autofilled with '" + Test_Cases.ShortText + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field SHORT TEXT is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Order Unit":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Order Unit");

                                        //Find input field below the "Order Unit" column title and check whether the input field is enabled/editable
                                        WebElement field_OrderUnit = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div[3]/div/div[2]/div/div/div/div[1]/table[contains (@ct, 'STCS')][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[contains (@id, '-content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/span[1]"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Order Unit" field
                                        Test_Cases.OrderUnit = field_OrderUnit.getAttribute("innerText");
                                        System.out.println("The ORDER UNIT field was autofilled with '" + Test_Cases.OrderUnit + "' value.");

                                        if (!Test_Cases.OrderUnit.isEmpty()) {
                                            logger.info("The ORDER UNIT field was autofilled with '" + Test_Cases.OrderUnit + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field ORDER UNIT is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Date Cat.":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Date Cat.");

                                        //Find input field below the "Date Cat." column title and check whether the input field is enabled/editable
                                        WebElement field_DateCat = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div[3]/div/div[2]/div/div/div/div[1]/table[contains (@ct, 'STCS')][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[contains (@id, '-content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/span[1]"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Date Cat." field
                                        Test_Cases.DateCat = field_DateCat.getAttribute("innerText");
                                        System.out.println("The DATE CAT. field was autofilled with '" + Test_Cases.DateCat + "' value.");

                                        if (!Test_Cases.DateCat.isEmpty()) {
                                            logger.info("The DATE CAT. field was autofilled with '" + Test_Cases.DateCat + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field DATE CAT. is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Delivery Date":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Delivery Date");

                                        //Find input field below the "Delivery Date" column title and check whether the input field is enabled/editable
                                        WebElement field_DeliveryDate = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div[3]/div/div[2]/div/div/div/div[1]/table[contains (@ct, 'STCS')][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[contains (@id, '-content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/span[1]"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Delivery Date" field
                                        Test_Cases.DelivDate = field_DeliveryDate.getAttribute("innerText");
                                        System.out.println("The DELIVERY DATE field was autofilled with '" + Test_Cases.DelivDate + "' value.");

                                        if (!Test_Cases.DelivDate.isEmpty()) {
                                            logger.info("The DELIVERY DATE field was autofilled with '" + Test_Cases.DelivDate + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field DELIVERY DATE is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Material Group":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Material Group");

                                        //Find input field below the "Material Group" column title and check whether the input field is enabled/editable

                                        WebElement field_MaterialGroup = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div[3]/div/div[2]/div/div/div/div[1]/table[contains (@ct, 'STCS')][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[contains (@id, '-content')][1]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/span[1]"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Material Group" field
                                        Test_Cases.MatlGroup = field_MaterialGroup.getAttribute("innerText");
                                        System.out.println("The MATERIAL GROUP field was autofilled with '" + Test_Cases.MatlGroup + "' value.");

                                        if (!Test_Cases.MatlGroup.isEmpty()) {
                                            logger.info("The MATERIAL GROUP field was autofilled with '" + Test_Cases.MatlGroup + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field MATERIAL GROUP is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;

                                    default:
                                }

                            }
                        } else {
                            return;// break the cycle when all required fields have been filled in
                        }
                    }
                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while retrieving the column titles and fields in the RIGHT part of 'ITEM OVERVIEW' table: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'itsframe1_')]")));


                try {
                    //Get all columns in the RIGHT part of "Add Item" table
                    List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//div[@id='userarea-scrl'][contains (@class, 'urScrl urBorderBox')]/div/div/div/div/div/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/table[@ct='STCS'][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[1]/tr[1][@vpm='mrss-hdr']/td[2]/div/div[2]/table/tbody/tr/descendant::th"));

                    System.out.println("Number of columns in the RIGHT part: " + available_ColumnsInRightPartOfTable.size());

                    //Loop through all found coulmns
                    for (WebElement element : available_ColumnsInRightPartOfTable) {
                        ColumnCount++; //column counter
                        if (neededColumns <= 6) {
                            System.out.println("Counter of Needed columns in the RIGHT part of ITEM OVERVIEW table: " + neededColumns);

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

                                String name_TableColumn = element.getAttribute("title");
                                System.out.println("SPAN element Text in the RIGHT part: " + name_TableColumn);

                                //Trim found column title to text only
                                if (name_TableColumn.contains("Item Cat.")) {
                                    name_TableColumn = "Item Cat.";
                                }
                                if (name_TableColumn.contains("Short Text")) {
                                    name_TableColumn = "Short Text";
                                }
                                if (name_TableColumn.contains("Order Unit")) {
                                    name_TableColumn = "Order Unit";
                                }
                                if (name_TableColumn.contains("Date Cat.")) {
                                    name_TableColumn = "Date Cat.";
                                }
                                if (name_TableColumn.contains("Delivery Date")) {
                                    name_TableColumn = "Delivery Date";
                                }
                                if (name_TableColumn.contains("Material Group")) {
                                    name_TableColumn = "Material Group";
                                }


                                switch (name_TableColumn) {
                                    case "Item Cat.":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Item Cat.");

                                        //Find input field below the "Item Cat." column title and check whether the input field is enabled/editable
                                        WebElement field_ItemCat = driver.findElement(By.ByXPath.xpath("//div[@id='userarea-scrl'][contains (@class, 'urScrl urBorderBox')]/div/div/div/div/div/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/table[@ct='STCS'][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[1]/tr[2][@vpm='mrss-cont']/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/input"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Item Cat." field
                                        Test_Cases.ItemCategory = field_ItemCat.getAttribute("value");
                                        System.out.println("The ITEM CAT. field was autofilled with '" + Test_Cases.ItemCategory + "' value.");

                                        if (!Test_Cases.ItemCategory.isEmpty()) {
                                            logger.info("The ITEM CAT. field was autofilled with '" + Test_Cases.ItemCategory + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field ITEM CAT. is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Short Text":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Short Text");

                                        //Find input field below the "Short Text" column title and check whether the input field is enabled/editable
                                        WebElement field_ShortText = driver.findElement(By.ByXPath.xpath("//div[@id='userarea-scrl'][contains (@class, 'urScrl urBorderBox')]/div/div/div/div/div/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/table[@ct='STCS'][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[1]/tr[2][@vpm='mrss-cont']/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/input"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Short Text" field
                                        Test_Cases.ShortText = field_ShortText.getAttribute("value");
                                        System.out.println("The SHORT TEXT field was autofilled with '" + Test_Cases.ShortText + "' value.");

                                        if (!Test_Cases.ShortText.isEmpty()) {
                                            logger.info("The SHORT TEXT field was autofilled with '" + Test_Cases.ShortText + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field SHORT TEXT is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Order Unit":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Order Unit");

                                        //Find input field below the "Order Unit" column title and check whether the input field is enabled/editable
                                        WebElement field_OrderUnit = driver.findElement(By.ByXPath.xpath("//div[@id='userarea-scrl'][contains (@class, 'urScrl urBorderBox')]/div/div/div/div/div/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/table[@ct='STCS'][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[1]/tr[2][@vpm='mrss-cont']/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/input"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Order Unit" field
                                        Test_Cases.OrderUnit = field_OrderUnit.getAttribute("value");
                                        System.out.println("The ORDER UNIT field was autofilled with '" + Test_Cases.OrderUnit + "' value.");

                                        if (!Test_Cases.OrderUnit.isEmpty()) {
                                            logger.info("The ORDER UNIT field was autofilled with '" + Test_Cases.OrderUnit + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field ORDER UNIT is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Date Cat.":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Date Cat.");

                                        //Find input field below the "Date Cat." column title and check whether the input field is enabled/editable
                                        WebElement field_DateCat = driver.findElement(By.ByXPath.xpath("//div[@id='userarea-scrl'][contains (@class, 'urScrl urBorderBox')]/div/div/div/div/div/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/table[@ct='STCS'][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[1]/tr[2][@vpm='mrss-cont']/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/input"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Date Cat." field
                                        Test_Cases.DateCat = field_DateCat.getAttribute("value");
                                        System.out.println("The DATE CAT. field was autofilled with '" + Test_Cases.DateCat + "' value.");

                                        if (!Test_Cases.DateCat.isEmpty()) {
                                            logger.info("The DATE CAT. field was autofilled with '" + Test_Cases.DateCat + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field DATE CAT. is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Delivery Date":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Delivery Date");

                                        //Find input field below the "Delivery Date" column title and check whether the input field is enabled/editable
                                        WebElement field_DeliveryDate = driver.findElement(By.ByXPath.xpath("//div[@id='userarea-scrl'][contains (@class, 'urScrl urBorderBox')]/div/div/div/div/div/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/table[@ct='STCS'][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[1]/tr[2][@vpm='mrss-cont']/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/input"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Delivery Date" field
                                        Test_Cases.DelivDate = field_DeliveryDate.getAttribute("value");
                                        System.out.println("The DELIVERY DATE field was autofilled with '" + Test_Cases.DelivDate + "' value.");

                                        if (!Test_Cases.DelivDate.isEmpty()) {
                                            logger.info("The DELIVERY DATE field was autofilled with '" + Test_Cases.DelivDate + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field DELIVERY DATE is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Material Group":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        //bringElementIntoViewHorizontally("Material Group");

                                        //Find input field below the "Material Group" column title and check whether the input field is enabled/editable

                                        WebElement field_MaterialGroup = driver.findElement(By.ByXPath.xpath("//div[@id='userarea-scrl'][contains (@class, 'urScrl urBorderBox')]/div/div/div/div/div/div/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/table[@ct='STCS'][contains (@class, 'urSTCS urST3WhlBrd urST3WhlNoTit urST5SelColUiMulti urHtmlTableReset')]/tbody[1]/tr[2][@vpm='mrss-cont']/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/input"));

                                        //scroll the field to be visible (if outside of vision portal) and click on it
                                        // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                        //Read value from the "Material Group" field
                                        Test_Cases.MatlGroup = field_MaterialGroup.getAttribute("value");
                                        System.out.println("The MATERIAL GROUP field was autofilled with '" + Test_Cases.MatlGroup + "' value.");

                                        if (!Test_Cases.MatlGroup.isEmpty()) {
                                            logger.info("The MATERIAL GROUP field was autofilled with '" + Test_Cases.MatlGroup + "' value.");
                                            neededColumns++;

                                        } else {
                                            logger.error("The autofilled field MATERIAL GROUP is EMPTY but should contain a value!");
                                            neededColumns++;
                                        }

                                        break;

                                    default:
                                }

                            }
                        } else {
                            driver.switchTo().defaultContent();
                            return;// break the cycle when all required fields have been filled in
                        }
                    }
                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while retrieving the column titles and fields in the RIGHT part of 'ITEM OVERVIEW' table: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            logger.error("Error while executing method 'check_AutocompeteFieldsInItemOverviewTable': " + e.getMessage());
        }
    }


    public void filling_AllItemsTableFields_LeftPart(String cell_Material) {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {


                try {
                    //Get all columns in the LEFT part of "Add Item" table
                    List<WebElement> available_ColumnsLeftPartOfTable = driver.findElements(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[1]/td[1]/div/div[2]/table/tbody/tr/descendant::th"));


                    System.out.println("Number of columns in the LEFT part: " + available_ColumnsLeftPartOfTable.size());


                    //Loop through all found coulmns
                    for (WebElement element : available_ColumnsLeftPartOfTable) {
                        ColumnCount++; //column counter
                        if (neededColumns <= 1) {
                            System.out.println("Counter of Needed columns in the LEFT part of ALL ITEM table: " + neededColumns);
                            try {//check if every column contains column title text

                                //scroll the field to be visible (if outside of vision portal) and click on it
                                js.executeScript("arguments[0].scrollIntoView(true);", element);


                                element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                                elAvailability = true;
                            } catch (NoSuchElementException e) {
                                elAvailability = false;
                            }

                            //if column contains a column title text then read it
                            if (elAvailability) {

                                String name_TableColumn = element.getAttribute("title");
                                System.out.println("SPAN element Text in the LEFT part: " + name_TableColumn);

                                //Trim found column title to text only
                                if (name_TableColumn.contains("Material")) {
                                    //if (!name_TableColumn.contains("Material Group")) {
                                    name_TableColumn = "Material";
                                    //  }
                                }


                                switch (name_TableColumn) {
                                    // case "\n\nMaterial\n\n\n ":
                                    case "Material":

                                        //Find input field below the "Material" column title and check whether the input field is enabled/editable

                                        WebElement field_Material = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[1]/div/div[2]/table/tbody/tr[1]//td[" + ColumnCount + "]"));

                                        String inputStatus1 = field_Material.getAttribute("class");

                                        //OFQ//if (!inputStatus1.contains("lsField--disabled")) {
                                        if (!inputStatus1.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                            field_Material.click();
                                            //js.executeScript("arguments[0].click();", field_Material);

                                            //Provide value into the "Material" input field
                                            //OFQ//WebElement input_Material = driver.findElement(By.ByXPath.xpath("//tr[@class=' urST5SelColUiGeneric'][contains(@vpm, 'mrss-cont')]/td[2]/div/div[contains(@bisposelement, 'X')][@class='urBorderBox']/table/tbody/tr/td[" + ColumnCount + "]/div/span"));
                                            WebElement input_Material = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[1]/div/div[2]/table/tbody/tr[1]//td[" + ColumnCount + "]"));
                                            input_Material = input_Material.findElement(By.xpath("div/span/input"));
                                            input_Material.clear();
                                            input_Material.sendKeys(cell_Material);
                                            logger.info("MATERIAL input field is SUCCESSFULLY filled in.");
                                            neededColumns++;
                                        } else {
                                            logger.error("MATERIAL input field is in DISABLED status!");
                                            neededColumns++;
                                        }
                                        break;

                                    default:
                                }

                            }
                        } else {
                            return;// break the cycle when all required fields have been filled in
                        }
                    }
                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while retrieving the column titles and fields in the LEFT part of 'ALL ITEMS' table: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {
                    //Get all columns in the LEFT part of "All Items" table
                    List<WebElement> available_ColumnsLeftPartOfTable = driver.findElements(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[1]/td[1]/div/div[2]/table/tbody/tr/descendant::th"));


                    System.out.println("Number of columns in the LEFT part: " + available_ColumnsLeftPartOfTable.size());


                    //Loop through all found coulmns
                    for (WebElement element : available_ColumnsLeftPartOfTable) {
                        ColumnCount++; //column counter
                        if (neededColumns <= 1) {
                            System.out.println("Counter of Needed columns in the LEFT part of ALL ITEMS table: " + neededColumns);
                            try {//check if every column contains column title text

                                //scroll the field to be visible (if outside of vision portal) and click on it
                                js.executeScript("arguments[0].scrollIntoView(true);", element);


                                element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                                elAvailability = true;
                            } catch (NoSuchElementException e) {
                                elAvailability = false;
                            }

                            //if column contains a column title text then read it
                            if (elAvailability) {

                                String name_TableColumn = element.getAttribute("title");
                                System.out.println("SPAN element Text in the LEFT part: " + name_TableColumn);

                                //Trim found column title to text only
                                if (name_TableColumn.contains("Material")) {
                                    //if (!name_TableColumn.contains("Material Group")) {
                                    name_TableColumn = "Material";
                                    // }
                                }


                                switch (name_TableColumn) {
                                    // case "\n\nMaterial\n\n\n ":
                                    case "Material":


                                        //check whether this column is within the screen boundaries and scroll to the column
                                        // bringElementIntoViewHorizontally("Material");

                                        //Find input field below the "Material" column title and check whether the input field is enabled/editable
                                        WebElement field_Material = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[1]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus1 = field_Material.getAttribute("class");

                                        //OFQ//if (!inputStatus1.contains("lsField--disabled")) {
                                        if (!inputStatus1.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            // js.executeScript("arguments[0].scrollIntoView();", field_Material);

                                            field_Material.click();
                                            //js.executeScript("arguments[0].click();", field_Material);

                                            //Provide value into the "Material" input field
                                            //OFQ//WebElement input_Material = driver.findElement(By.ByXPath.xpath("//tr[@class=' urST5SelColUiGeneric'][contains(@vpm, 'mrss-cont')]/td[2]/div/div[contains(@bisposelement, 'X')][@class='urBorderBox']/table/tbody/tr/td[" + ColumnCount + "]/div/span"));
                                            WebElement input_Material = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[1]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_Material = input_Material.findElement(By.xpath("div/span/input"));
                                            input_Material.clear();
                                            input_Material.sendKeys(cell_Material);
                                            logger.info("MATERIAL input field is SUCCESSFULLY filled in.");
                                            neededColumns++;
                                        } else {
                                            logger.error("MATERIAL input field is in DISABLED status!");
                                            neededColumns++;
                                        }
                                        break;

                                    default:
                                }

                            }
                        } else {
                            driver.switchTo().defaultContent();
                            return;// break the cycle when all required fields have been filled in
                        }
                    }
                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while retrieving the column titles and fields in the LEFT part of 'ALL ITEMS' table: " + e.getMessage());
                }
            }


        } catch (Exception e) {
            logger.error("Error while executing method 'filling_AllItemsTableFields_LeftPart()': " + e.getMessage());
        }

    }


    public void retrive_ItemValue_FromAllItemsTable() {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {


                try {
                    //Get all columns in the LEFT part of "Add Item" table
                    List<WebElement> available_ColumnsLeftPartOfTable = driver.findElements(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[1]/td[1]/div/div[2]/table/tbody/tr/descendant::th"));


                    System.out.println("Number of columns in the LEFT part: " + available_ColumnsLeftPartOfTable.size());


                    //Loop through all found coulmns
                    for (WebElement element : available_ColumnsLeftPartOfTable) {
                        ColumnCount++; //column counter
                        if (neededColumns <= 1) {
                            System.out.println("Counter of Needed columns in the LEFT part of ALL ITEM table: " + neededColumns);
                            try {//check if every column contains column title text

                                //scroll the field to be visible (if outside of vision portal) and click on it
                                js.executeScript("arguments[0].scrollIntoView(true);", element);


                                element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                                elAvailability = true;
                            } catch (NoSuchElementException e) {
                                elAvailability = false;
                            }

                            //if column contains a column title text then read it
                            if (elAvailability) {

                                String name_TableColumn = element.getAttribute("title");
                                System.out.println("SPAN element Text in the LEFT part: " + name_TableColumn);

                                //Trim found column title to text only
                                if (name_TableColumn.contains("Sales Document Item")) {
                                    //if (!name_TableColumn.contains("Material Group")) {
                                    name_TableColumn = "Sales Document Item";
                                    //  }
                                }


                                switch (name_TableColumn) {
                                    // case "\n\nMaterial\n\n\n ":
                                    case "Sales Document Item":

                                        //Find input field below the "Sales Document Item" column title and read its value

                                        WebElement input_Item = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[1]/div/div[2]/table/tbody/tr[1]//td[" + ColumnCount + "]/div/span/span[1]"));
                                        Test_Cases.Item = input_Item.getAttribute("innerHTML");
                                        System.out.println("Test_Cases.Item = " + Test_Cases.Item);
                                        logger.info("SALES DOCUMENT ITEM was auto-filled with the value of: " + Test_Cases.Item);
                                        neededColumns++;

                                        break;

                                    default:
                                }

                            }
                        } else {
                            return;// break the cycle when all required fields have been filled in
                        }
                    }
                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while retrieving the column titles and fields in the LEFT part of 'ALL ITEMS' table: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {
                    //Get all columns in the LEFT part of "All Items" table
                    List<WebElement> available_ColumnsLeftPartOfTable = driver.findElements(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[1]/td[1]/div/div[2]/table/tbody/tr/descendant::th"));


                    System.out.println("Number of columns in the LEFT part: " + available_ColumnsLeftPartOfTable.size());


                    //Loop through all found coulmns
                    for (WebElement element : available_ColumnsLeftPartOfTable) {
                        ColumnCount++; //column counter
                        if (neededColumns <= 1) {
                            System.out.println("Counter of Needed columns in the LEFT part of ALL ITEMS table: " + neededColumns);
                            try {//check if every column contains column title text

                                //scroll the field to be visible (if outside of vision portal) and click on it
                                js.executeScript("arguments[0].scrollIntoView(true);", element);


                                element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                                elAvailability = true;
                            } catch (NoSuchElementException e) {
                                elAvailability = false;
                            }

                            //if column contains a column title text then read it
                            if (elAvailability) {

                                String name_TableColumn = element.getAttribute("title");
                                System.out.println("SPAN element Text in the LEFT part: " + name_TableColumn);

                                //Trim found column title to text only
                                if (name_TableColumn.contains("Sales Document Item")) {
                                    //if (!name_TableColumn.contains("Material Group")) {
                                    name_TableColumn = "Sales Document Item";
                                    // }
                                }


                                switch (name_TableColumn) {
                                    // case "\n\nMaterial\n\n\n ":
                                    case "Sales Document Item":


                                        //Find input field below the "Sales Document Item" column title and read its value
                                        WebElement input_Item = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[1]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]/div/span/input"));

                                        Test_Cases.Item = input_Item.getAttribute("value");
                                        System.out.println("Test_Cases.Item = " + Test_Cases.Item);
                                        logger.info("SALES DOCUMENT ITEM was auto-filled with the value of: " + Test_Cases.Item);
                                        neededColumns++;

                                        break;

                                    default:
                                }

                            }
                        } else {
                            driver.switchTo().defaultContent();
                            return;// break the cycle when all required fields have been filled in
                        }
                    }
                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while retrieving the column titles and fields in the LEFT part of 'ALL ITEMS' table: " + e.getMessage());
                }
            }


        } catch (Exception e) {
            logger.error("Error while executing method 'filling_AllItemsTableFields_LeftPart()': " + e.getMessage());
        }

    }


    public void filling_AllItemsTableFields_RightPart(String cell_Qty, String cell_Plant, String cell_StorgLocatn, String cell_NetPrice) {

        int ColumnCount = 0;
        int neededColumns = 1;
        boolean elAvailability = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {


                try {
                    //Get all columns in the RIGHT part of "Add Item" table
                    List<WebElement> available_ColumnsRightPartOfTable = driver.findElements(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[1]/td[2]/div/div[2]/table/tbody/tr/descendant::th"));


                    System.out.println("Number of columns in the RIGHT part: " + available_ColumnsRightPartOfTable.size());


                    //Loop through all found coulmns
                    for (WebElement element : available_ColumnsRightPartOfTable) {
                        ColumnCount++; //column counter
                        if (neededColumns <= 4) {
                            System.out.println("Counter of Needed columns in the RIGHT part of ALL ITEM table: " + neededColumns);
                            try {//check if every column contains column title text

                                element.findElement(By.xpath("div/table/tbody/tr/td[1]/div/span/span"));
                                elAvailability = true;
                            } catch (NoSuchElementException e) {
                                elAvailability = false;
                            }

                            //if column contains a column title text then read it
                            if (elAvailability) {

                                String name_TableColumn = element.getAttribute("title");
                                System.out.println("SPAN element Text in the RIGHT part: " + name_TableColumn);

                                //Trim found column title to text only
                                if (name_TableColumn.contains("Order Quantity")) {
                                    name_TableColumn = "Order Quantity";
                                }
                                if (name_TableColumn.contains("Plnt")) {
                                    name_TableColumn = "Plnt";
                                }
                                if (name_TableColumn.contains("Stor.Loc")) {
                                    name_TableColumn = "Stor.Loc";
                                }
                                if (name_TableColumn.contains("Net Price")) {
                                    name_TableColumn = "Net Price";
                                }


                                switch (name_TableColumn) {

                                    case "Order Quantity":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        bringElementIntoViewHorizontally("Order Quantity");

                                        //Find input field below the "Order Quantity" column title and check whether the input field is enabled/editable
                                        WebElement field_Quantity = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus2 = field_Quantity.getAttribute("class");

                                        //OFQ//if (!inputStatus2.contains("lsField--disabled")) {
                                        if (!inputStatus2.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            // js.executeScript("arguments[0].scrollIntoView();", field_Quantity);
                                            field_Quantity.click();
                                            // js.executeScript("arguments[0].click();", field_Quantity);

                                            //Provide value into the "Order Quantity" input field
                                            WebElement input_Qty = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_Qty = input_Qty.findElement(By.xpath("div/span/input"));
                                            input_Qty.clear();
                                            js.executeScript("arguments[0].value='" + cell_Qty + "';", input_Qty);
                                            //input_Qty.sendKeys("100");
                                            //input_Qty.sendKeys(Keys.TAB);
                                            logger.info("ORDER QUANTITY input field is SUCCESSFULLY filled in.");
                                            neededColumns++;
                                        } else {
                                            logger.error("ORDER QUANTITY input field is in DISABLED status!");
                                            neededColumns++;
                                        }
                                        break;


                                    case "Plnt":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        bringElementIntoViewHorizontally("Plnt");

                                        //Find input field below the "Plant" column title and check whether the input field is enabled/editable
                                        WebElement field_Plant = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus3 = field_Plant.getAttribute("class");

                                        //OFQ//if (!inputStatus3.contains("lsField--disabled")) {
                                        if (!inputStatus3.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            // js.executeScript("arguments[0].scrollIntoView();", field_Plant);
                                            field_Plant.click();
                                            //js.executeScript("arguments[0].click();", field_Plant);

                                            //Provide value into the "Plant" input field
                                            WebElement input_Plant = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_Plant = input_Plant.findElement(By.xpath("div/span/input"));
                                            input_Plant.clear();
                                            input_Plant.sendKeys(cell_Plant);
                                            logger.info("PLANT input field is SUCCESSFULLY filled in.");
                                            neededColumns++;
                                        } else {
                                            logger.error("PLANT input field is in DISABLED status!");
                                            neededColumns++;
                                        }
                                        break;


                                    // case "\n\nStorage location\n\n\n ":
                                    case "Stor.Loc":
                                        //check whether this column is within the screen boundaries and scroll to the column
                                        bringElementIntoViewHorizontally("Stor.Loc");

                                        //Find input field below the "Storage Location" column title and check whether the input field is enabled/editable
                                        WebElement field_StorageLocation = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus4 = field_StorageLocation.getAttribute("class");

                                        //OFQ//if (!inputStatus4.contains("lsField--disabled")) {
                                        if (!inputStatus4.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            //js.executeScript("arguments[0].scrollIntoView();", field_StorageLocation);
                                            field_StorageLocation.click();
                                            //js.executeScript("arguments[0].click();", field_StorageLocation);

                                            //Provide value into the "Storage Location" input field
                                            WebElement input_StorLoc = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_StorLoc = input_StorLoc.findElement(By.xpath("div/span/input"));
                                            input_StorLoc.clear();
                                            input_StorLoc.sendKeys(cell_StorgLocatn);
                                            logger.info("STORAGE LOCATION input field is SUCCESSFULLY filled in.");
                                            neededColumns++;

                                        } else {
                                            logger.error("STORAGE LOCATION input field is in DISABLED status!");
                                            neededColumns++;
                                        }
                                        break;

                                    case "Net Price":
                                        //check whether this column is within the screen boundaries and scroll to the column
                                        bringElementIntoViewHorizontally("Net Price");

                                        //Find input field below the "Storage Location" column title and check whether the input field is enabled/editable
                                        WebElement field_NetPrice = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus5 = field_NetPrice.getAttribute("class");

                                        //OFQ//if (!inputStatus4.contains("lsField--disabled")) {
                                        if (!inputStatus5.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            //js.executeScript("arguments[0].scrollIntoView();", field_StorageLocation);
                                            field_NetPrice.click();
                                            //js.executeScript("arguments[0].click();", field_StorageLocation);

                                            //Provide value into the "Net Price" input field
                                            WebElement input_NetPrice = driver.findElement(By.ByXPath.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_NetPrice = input_NetPrice.findElement(By.xpath("div/span/input"));
                                            input_NetPrice.clear();
                                            input_NetPrice.sendKeys(cell_NetPrice);
                                            input_NetPrice.sendKeys(Keys.RETURN);
                                            logger.info("NET PRICE input field is SUCCESSFULLY filled in.");
                                            neededColumns++;

                                        } else {
                                            logger.error("NET PRICE input field is in DISABLED status!");
                                            neededColumns++;
                                        }
                                        break;

                                    default:
                                }

                            }
                        } else {
                            return;// break the cycle when all required fields have been filled in
                        }
                    }
                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while retrieving the column titles and fields in the RIGHT part of 'ITEM OVERVIEW' table: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {
                    //Get all columns in the RIGHT part of "All Items" table
                    List<WebElement> available_ColumnsInRightPartOfTable = driver.findElements(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[1]/td[2]/div/div[2]/table/tbody/tr/descendant::th"));


                    System.out.println("Number of columns in the RIGHT part: " + available_ColumnsInRightPartOfTable.size());


                    //Loop through all found coulmns
                    for (WebElement element : available_ColumnsInRightPartOfTable) {
                        ColumnCount++; //column counter
                        if (neededColumns <= 4) {
                            System.out.println("Counter of Needed columns in the RIGHT part of ITEM OVERVIEW table: " + neededColumns);

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

                                String name_TableColumn = element.getAttribute("title");
                                System.out.println("SPAN element Text in the RIGHT part: " + name_TableColumn);

                                //Trim found column title to text only
                                if (name_TableColumn.contains("Order Quantity")) {
                                    name_TableColumn = "Order Quantity";
                                }
                                if (name_TableColumn.contains("Plnt")) {
                                    name_TableColumn = "Plnt";
                                }
                                if (name_TableColumn.contains("Stor.Loc")) {
                                    name_TableColumn = "Stor.Loc";
                                }
                                if (name_TableColumn.contains("Net Price")) {
                                    name_TableColumn = "Net Price";
                                }


                                switch (name_TableColumn) {

                                    case "Order Quantity":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        bringElementIntoViewHorizontally("Order Quantity");

                                        //Find input field below the "Order Quantity" column title and check whether the input field is enabled/editable
                                        WebElement field_Quantity = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus2 = field_Quantity.getAttribute("class");

                                        //OFQ//if (!inputStatus2.contains("lsField--disabled")) {
                                        if (!inputStatus2.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            // js.executeScript("arguments[0].scrollIntoView();", field_Quantity);
                                            field_Quantity.click();
                                            // js.executeScript("arguments[0].click();", field_Quantity);

                                            //Provide value into the "Order Quantity" input field
                                            WebElement input_Qty = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_Qty = input_Qty.findElement(By.xpath("div/span/input"));
                                            input_Qty.clear();
                                            js.executeScript("arguments[0].value='" + cell_Qty + "';", input_Qty);
                                            //input_Qty.sendKeys("100");
                                            //input_Qty.sendKeys(Keys.TAB);
                                            logger.info("ORDER QUANTITY input field is SUCCESSFULLY filled in.");
                                            neededColumns++;
                                        } else {
                                            logger.error("ORDER QUANTITY input field is in DISABLED status!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Plnt":

                                        //check whether this column is within the screen boundaries and scroll to the column
                                        bringElementIntoViewHorizontally("Plnt");

                                        //Find input field below the "Plant" column title and check whether the input field is enabled/editable
                                        WebElement field_Plant = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus3 = field_Plant.getAttribute("class");

                                        //OFQ//if (!inputStatus3.contains("lsField--disabled")) {
                                        if (!inputStatus3.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            // js.executeScript("arguments[0].scrollIntoView();", field_Plant);
                                            field_Plant.click();
                                            //js.executeScript("arguments[0].click();", field_Plant);

                                            //Provide value into the "Plant" input field
                                            WebElement input_Plant = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_Plant = input_Plant.findElement(By.xpath("div/span/input"));
                                            input_Plant.clear();
                                            input_Plant.sendKeys(cell_Plant);
                                            logger.info("PLANT input field is SUCCESSFULLY filled in.");
                                            neededColumns++;
                                        } else {
                                            logger.error("PLANT input field is in DISABLED status!");
                                            neededColumns++;
                                        }

                                        break;


                                    // case "\n\nStorage location\n\n\n ":
                                    case "Stor.Loc":
                                        //check whether this column is within the screen boundaries and scroll to the column
                                        bringElementIntoViewHorizontally("Stor.Loc");

                                        //Find input field below the "Storage Location" column title and check whether the input field is enabled/editable
                                        WebElement field_StorageLocation = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus4 = field_StorageLocation.getAttribute("class");

                                        //OFQ//if (!inputStatus4.contains("lsField--disabled")) {
                                        if (!inputStatus4.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            //js.executeScript("arguments[0].scrollIntoView();", field_StorageLocation);
                                            field_StorageLocation.click();
                                            //js.executeScript("arguments[0].click();", field_StorageLocation);

                                            //Provide value into the "Storage Location" input field
                                            WebElement input_StorLoc = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_StorLoc = input_StorLoc.findElement(By.xpath("div/span/input"));
                                            input_StorLoc.clear();
                                            input_StorLoc.sendKeys(cell_StorgLocatn);
                                            logger.info("STORAGE LOCATION input field is SUCCESSFULLY filled in.");
                                            neededColumns++;

                                        } else {
                                            logger.error("STORAGE LOCATION input field is in DISABLED status!");
                                            neededColumns++;
                                        }

                                        break;


                                    case "Net Price":
                                        //check whether this column is within the screen boundaries and scroll to the column
                                        bringElementIntoViewHorizontally("Net Price");

                                        //Find input field below the "Net Price" column title and check whether the input field is enabled/editable
                                        WebElement field_NetPrice = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));

                                        String inputStatus5 = field_NetPrice.getAttribute("class");

                                        //OFQ//if (!inputStatus4.contains("lsField--disabled")) {
                                        if (!inputStatus5.contains("urSTTDRo2")) {
                                            //scroll the field to be visible (if outside of vision portal) and click on it
                                            //js.executeScript("arguments[0].scrollIntoView();", field_StorageLocation);
                                            field_NetPrice.click();
                                            //js.executeScript("arguments[0].click();", field_StorageLocation);

                                            //Provide value into the "Net Price" input field
                                            WebElement input_NetPrice = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[2]/td[2]/div/div[2]/table/tbody/tr[1]/td[" + ColumnCount + "]"));
                                            input_NetPrice = input_NetPrice.findElement(By.xpath("div/span/input"));
                                            input_NetPrice.clear();
                                            input_NetPrice.sendKeys(cell_NetPrice);
                                            input_NetPrice.sendKeys(Keys.RETURN);
                                            logger.info("NET PRICE input field is SUCCESSFULLY filled in.");
                                            neededColumns++;


                                        } else {
                                            logger.error("NET PRICE input field is in DISABLED status!");
                                            neededColumns++;
                                        }

                                        break;

                                    default:
                                }

                            }
                        } else {
                            driver.switchTo().defaultContent();
                            return;// break the cycle when all required fields have been filled in
                        }
                    }
                } catch (NoSuchElementException e) {
                    logger.error("No such element - Error while retrieving the column titles and fields in the RIGHT part of 'ALL ITEMS' table: " + e.getMessage());
                }
            }


        } catch (Exception e) {
            logger.error("Error while executing method 'filling_ItemOverviewTableFields()': " + e.getMessage());
        }

    }


    public void read_FooterMessageAfterTableAutoFill() {

        String messageText = "";

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    WebElement footer_Message = driver.findElement(By.xpath("//form[@id='webguiform0']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[2][contains (@role, 'presentation')]"));

                    messageText = footer_Message.getAttribute("innerText");
                    System.out.println("Footer message after autofilling all 'OVERVIEW ITEM' table fields is: " + messageText);

                    logger.warn("Footer message after autofilling all 'OVERVIEW ITEM' table fields is: " + messageText.toUpperCase());

                } catch (Exception e) {
                    logger.info("Footer message is empty after autofilling all 'OVERVIEW ITEM' table fields.");
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'itsframe1_')]")));

                try {

                    //wait till "Please enter supplying plant" status is displayed in the footer
                    // waitTillElementDetected(15,         "//form[@id='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div/descendant::span[contains(text(), 'Please enter supplying plant')]", "FOOTER STATUS 'PLEASE ENTER SUPPLYING PLANT'");
                    //Thread.sleep(1000);

                    WebElement footer_Message = driver.findElement(By.xpath("//form[@id='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[2]"));

                    messageText = footer_Message.getAttribute("innerText");
                    System.out.println("Footer message after autofilling all 'OVERVIEW ITEM' table fields: " + messageText);
                    logger.warn("Footer message after autofilling all 'OVERVIEW ITEM' table fields: " + messageText.toUpperCase());

                } catch (Exception e) {
                    logger.info("Footer message is empty after autofilling all 'OVERVIEW ITEM' table fields.");
                }


                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Error while executing method 'filling_ItemOverviewTableFields()': " + e.getMessage());
        }

    }


    public void bringElementIntoViewHorizontally(String columnTitle) {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Column title in every column of the right-hand part of "All Items" table
                    WebElement el1 = driver.findElement(By.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[contains (@id, 'content')]/tr[1]/td[2]/div/div[2]/table/tbody/tr/descendant::span[@title='" + columnTitle + "']/ancestor::th[1]"));

                    //Horizontal slider in the second part of "All Items" table
                    WebElement webElement = driver.findElement(By.xpath("//form[@id='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/table/tbody[2]/tr/td[2]/div/table/tbody/tr/td[2]/descendant::span[contains (@id, 'hscroll-hdl')][contains (@acf, 'Hndl')]"));

                    System.out.println("HORIZONTAL SCROLLER DETECTED!!!");

                    int offset_Value = 0;


/*
        // java - get screen size using the Toolkit class
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // the screen height
            int screenHeight = screenSize.height;
            System.out.println("Screen HEIGHT is: " + screenHeight);

            // the screen width
            int screenWidth = screenSize.width;
            System.out.println("Screen WIDTH is: " + screenWidth);*/

                    //Get Browser window height
                    int contentHeight = ((Number) js.executeScript("return window.innerHeight")).intValue();
                    System.out.println("Browser window height Is " + contentHeight + " pixels");

                    //Get Browser window width
                    int contentWidth = ((Number) js.executeScript("return window.innerWidth")).intValue();
                    System.out.println("Browser window width Is " + contentWidth + " pixels");

                    Point point = el1.getLocation();

                    //Get element's X location
                    int element_X = point.getX();
                    System.out.println("Element's Position from left side is: " + point.getX() + " pixels.");

                    //Get element's Y location
                    int element_Y = point.getY();
                    System.out.println("Element's Position from top is: " + point.getY() + " pixels.");

                    //Get width of element.
                    int elementWidth = el1.getSize().getWidth();
                    System.out.println("Element width Is " + elementWidth + " pixels");

                    //Get height of element.
                    int elementHeight = el1.getSize().getHeight();
                    System.out.println("Element height Is " + elementHeight + " pixels");

                    //End coordinate of Web element on X axis
                    int total_X = element_X + contentWidth;

                    //End coordinate of Web element on Y axis
                    int total_Y = element_Y + contentHeight;

           /* if (total_Y >= contentHeight) {

                //"Add Item" table
                // WebElement table_AddItem = driver.findElement(By.xpath("//td[@class='lsCnArFaceEmph lsCnArBrdBox lsPnCntPadStd'][contains(@id, 'caCnt')]/div/table[@class='urMatrixLayout urHtmlTableReset'][contains (@ct, 'ML')]"));

                //System.out.println("COLUMN DIV DETECTED!!!");

            }*/

                    if (element_X >= contentWidth) {
                        //if element is outside of view port then calculate how many pixels are needed to bring it into the view


                        //Get offset between browser width and element location
                        offset_Value = element_X + elementWidth - contentWidth;
                        System.out.println("Offset between browser width and the outbordered element: " + offset_Value + " pixels");

                        offset_Value = (int) Math.round((offset_Value) * 0.4);
                        System.out.println("Offset for SLIDER in case of outbordered element: " + offset_Value + " pixels");

                        //js.executeScript("window.scroll(140, %s)", webElement );
                        // js.executeScript("arguments[0].scrollIntoView();",webElement);
                        action.moveToElement(webElement).clickAndHold();
                        action.moveByOffset(offset_Value, 0);
                        action.release();
                        action.perform();

                    } else {

                        //if element is partly outside of view port then calculate how many pixels are needed to bring it into the view
                        if (element_X < contentWidth && element_X + elementWidth > contentWidth) {

                            //Get offset between browser width and partly visible element
                            offset_Value = contentWidth - element_X;
                            offset_Value = elementWidth - offset_Value;
                            offset_Value = (int) Math.round((offset_Value) * 0.7);
                            System.out.println("Offset between browser width and the partly outbordered element: " + offset_Value + " pixels");

                            //js.executeScript("window.scroll(140, %s)", webElement );
                            // js.executeScript("arguments[0].scrollIntoView();",webElement);
                            action.moveToElement(webElement).clickAndHold();
                            action.moveByOffset(offset_Value, 0);
                            action.release();
                            action.perform();
                        }
                    }


                } catch (Exception e) {
                    logger.error("Error while bringing element into view horizontally on the screen: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                try {

                    //Column title in every column of the right-hand part of "All Items" table
                    WebElement el1 = driver.findElement(By.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[contains (@id, 'content')]/tr[1]/td[2]/div/div[2]/table/tbody/tr/descendant::span[@title='" + columnTitle + "']/ancestor::th[1]"));


                    //Horizontal slider in the second part of "All Items" table
                    WebElement webElement = driver.findElement(By.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/tbody[2]/tr/td[2]/div/table/tbody/tr/td[2]/descendant::span[contains (@id, 'hscroll-hdl')][contains (@acf, 'Hndl')]"));

                    System.out.println("HORIZONTAL SCROLLER DETECTED!!!");

                    int offset_Value = 0;

                    //Get Browser window height
                    int contentHeight = ((Number) js.executeScript("return window.innerHeight")).intValue();
                    System.out.println("Browser window height Is " + contentHeight + " pixels");

                    //Get Browser window width
                    int contentWidth = ((Number) js.executeScript("return window.innerWidth")).intValue();
                    System.out.println("Browser window width Is " + contentWidth + " pixels");

                    Point point = el1.getLocation();

                    //Get element's X location
                    int element_X = point.getX();
                    System.out.println("Element's Position from left side is: " + point.getX() + " pixels.");

                    //Get element's Y location
                    int element_Y = point.getY();
                    System.out.println("Element's Position from top is: " + point.getY() + " pixels.");

                    //Get width of element.
                    int elementWidth = el1.getSize().getWidth();
                    System.out.println("Element width Is " + elementWidth + " pixels");

                    //Get height of element.
                    int elementHeight = el1.getSize().getHeight();
                    System.out.println("Element height Is " + elementHeight + " pixels");

                    //End coordinate of Web element on X axis
                    int total_X = element_X + contentWidth;

                    //End coordinate of Web element on Y axis
                    int total_Y = element_Y + contentHeight;

                    if (element_X >= contentWidth) {
                        //if element is outside of view port then calculate how many pixels are needed to bring it into the view


                        //Get offset between browser width and element location
                        offset_Value = element_X + elementWidth - contentWidth;
                        System.out.println("Offset between browser width and the outbordered element: " + offset_Value + " pixels");

                        offset_Value = (int) Math.round((offset_Value) * 0.4);
                        System.out.println("Offset for SLIDER in case of outbordered element: " + offset_Value + " pixels");

                        //js.executeScript("window.scroll(140, %s)", webElement );
                        // js.executeScript("arguments[0].scrollIntoView();",webElement);
                        action.moveToElement(webElement).clickAndHold();
                        action.moveByOffset(offset_Value, 0);
                        action.release();
                        action.perform();

                    } else {

                        //if element is partly outside of view port then calculate how many pixels are needed to bring it into the view
                        if (element_X < contentWidth && element_X + elementWidth > contentWidth) {

                            //Get offset between browser width and partly visible element
                            offset_Value = contentWidth - element_X;
                            offset_Value = elementWidth - offset_Value;
                            offset_Value = (int) Math.round((offset_Value) * 0.7);
                            System.out.println("Offset between browser width and the partly outbordered element: " + offset_Value + " pixels");

                            //js.executeScript("window.scroll(140, %s)", webElement );
                            // js.executeScript("arguments[0].scrollIntoView();",webElement);
                            action.moveToElement(webElement).clickAndHold();
                            action.moveByOffset(offset_Value, 0);
                            action.release();
                            action.perform();
                        }
                    }


                } catch (Exception e) {
                    logger.error("Error while bringing element into view horizontally on the screen: " + e.getMessage());
                }

            }

        } catch (Exception e) {
            logger.error("Error while executing method 'bringElementIntoViewHorizontally': " + e.getMessage());
        }
    }


    public boolean check_NoBillToBlock_Flags_Displayed() {

        boolean flag_Green = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Locate the flag "No Bill-To Block"
                    WebElement flag_NoBillToBlock = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[2]/div/div[4]/span/span[1]/*[name()='svg']/*[name()='use']"));

                    //scroll to bring 'Item Overview' button into view
                    js.executeScript("arguments[0].scrollIntoView();", flag_NoBillToBlock);

                    String displayClass = flag_NoBillToBlock.getAttribute("class");
                    System.out.println("The class of 'No Bill-To Block' flag is: " + displayClass);

                    if (displayClass.contains("sapSvgIconPositive")) {
                        flag_Green = true;

                        logger.info("The NO BILLING BLOCK flag is displayed as EXPECTED.");

                    }

                } catch (Exception e) {
                    logger.error("Unable to detect the flag to determine the BILL-TO BLOCKED/NOT BLOCKED status: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {

                    //Locate the flag "No Bill-To Block"
                    WebElement flag_NoBillToBlock = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[1]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/div/div/div/div/div[2]/div/div/div/div/div/div[4]/div/span/label/*[name()='svg']/*[name()='use']"));

                    //scroll to bring the flag "No Bill-To Block" into view
                    js.executeScript("arguments[0].scrollIntoView();", flag_NoBillToBlock);

                    String displayClass = flag_NoBillToBlock.getAttribute("class");
                    System.out.println("The class of 'No Bill-To Block' flag is: " + displayClass);

                    if (displayClass.contains("sapSvgIconPositive")) {
                        flag_Green = true;

                        logger.info("The NO BILLING BLOCK flag is displayed as EXPECTED.");

                    }

                } catch (Exception e) {
                    logger.error("Unable to detect the flag to determine the BILL-TO BLOCKED/NOT BLOCKED status: " + e.getMessage());
                }

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while executing method 'check_NoBillToBlock_Flags_Displayed()': " + e.getMessage());
        }
        return flag_Green;
    }


    public boolean check_FooterMessageSuccessOrFailure() {

        boolean flag_Success = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Locate the text message in the footer of the page
                    WebElement text_PageFooter = driver.findElement(By.ByXPath.xpath("//div[@id='webguiPage0']/form[contains (@name, 'webguiform0')]/table/tbody/tr[3]/descendant::td[@id='msgarea-menuindicator']/div/div[1]/span[contains (@class, 'lsMessageBar__icon--Ok lsMessageBar__image--Ok')]"));

                    flag_Success = true;

                    String msg_Text = driver.findElement(By.ByXPath.xpath("//div[@id='webguiPage0']/form[contains (@name, 'webguiform0')]/table/tbody/tr[3]/descendant::td[@id='msgarea-menuindicator']/div/div[1]/span[2]")).getAttribute("innerHTML");
                    String msg_Text2 = msg_Text.replace("&nbsp;", "");
                    System.out.println("The page footer has the status of: '" + msg_Text2 + "'.");

                    logger.info("After adding a new item the page footer is displaying the message: '" + msg_Text2 + "'.");

                } catch (Exception e) {
                    logger.error("Unable to detect the message in the footer of a page after adding a new Material Item: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {

                    //Locate the text message in the footer of the page
                    WebElement text_PageFooter = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[contains (@class, 'lsMessageBar__icon--Ok lsMessageBar__image--Ok')]"));

                    flag_Success = true;

                    String msg_Text = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[2]")).getAttribute("innerHTML");
                    String msg_Text2 = msg_Text.replace("&nbsp;", "");
                    System.out.println("The page footer has the status of: '" + msg_Text2 + "'.");

                    logger.info("After adding a new item the page footer is displaying the message: '" + msg_Text2 + "'.");


                } catch (Exception e) {
                    logger.error("Unable to detect the message in the footer of a page after adding a new Material Item: " + e.getMessage());
                }

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while executing method 'check_FooterMessageSuccessOrFailure()': " + e.getMessage());
        }
        return flag_Success;
    }


    public boolean check_FooterMessageSuccessOrFailureAfterSaving() {

        boolean flag_Success = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Locate the text message in the footer of the page
                    WebElement text_PageFooter = driver.findElement(By.ByXPath.xpath("//div[@id='webguiPage0']/form[contains (@name, 'webguiform0')]/table/tbody/tr[3]/descendant::td[@id='msgarea-menuindicator']/div/div[1]/span[contains (text(), 'has been saved')]"));

                    flag_Success = true;

                    logger.info("After clicking on 'Save' button the Standard Order is SAVED SUCCESSFULLY.");

                } catch (Exception e) {
                    logger.error("Unable to detect the message in the footer of a page after clicking on SAVE button: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {

                    //Locate the text message in the footer of the page
                    WebElement text_PageFooter = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[contains (text(), 'has been saved')]"));

                    flag_Success = true;

                    logger.info("After clicking on 'Save' button the Standard Order is SAVED SUCCESSFULLY.");

                } catch (Exception e) {
                    logger.error("Unable to detect the message in the footer of a page after clicking on SAVE button: " + e.getMessage());
                }

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while executing method 'check_FooterMessageSuccessOrFailureAfterSaving()': " + e.getMessage());
        }
        return flag_Success;
    }


    public boolean check_NoShipToBlock_Flags_Displayed() {

        boolean flag_Green = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Locate the flag "No Ship-To Block"
                    WebElement flag_NoShipToBlock = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform0']/table/tbody/tr[2]/td[1]/div/div[1]/div/div/div/div[2]/div/div[1]/div/div[5]/span/span[1]/*[name()='svg']/*[name()='use']"));

                    String displayClass = flag_NoShipToBlock.getAttribute("class");
                    System.out.println("The class of 'No Ship-To Block' flag is: " + displayClass);

                    if (displayClass.contains("sapSvgIconPositive")) {
                        flag_Green = true;

                        logger.info("The NO SHIPPING BLOCK flag is displayed as EXPECTED.");

                    }

                } catch (Exception e) {
                    logger.error("Unable to detect the flag to determine the SHIP-TO BLOCKED/NOT BLOCKED status: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {

                    //Locate the flag "No Ship-To Block"
                    WebElement flag_NoShipToBlock = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[1]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/div[5]/div/span/label/*[name()='svg']/*[name()='use']"));

                    String displayClass = flag_NoShipToBlock.getAttribute("class");
                    System.out.println("The class of 'No Ship-To Block' flag is: " + displayClass);

                    if (displayClass.contains("sapSvgIconPositive")) {
                        flag_Green = true;

                        logger.info("The NO SHIPPING BLOCK flag is displayed as EXPECTED.");

                    }

                } catch (Exception e) {
                    logger.error("Unable to detect the flag to determine the SHIP-TO BLOCKED/NOT BLOCKED status: " + e.getMessage());
                }

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while executing method 'check_NoShipToBlock_Flags_Displayed()': " + e.getMessage());
        }
        return flag_Green;
    }


    public boolean check_AllItems_Table_Displayed() {

        boolean table_AllItems_Displayed = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Locate the table title "All Items"
                    WebElement table_Title = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/descendant::div[@class='lsRasterLayout'][contains (@ct, 'RL')]/div[2]/div/div[3]/descendant::span[contains (text(), 'All Items')]"));

                    table_AllItems_Displayed = true;

                    logger.info("The ALL ITEMS TABLE is displayed by default as EXPECTED.");


                } catch (Exception e) {
                    logger.error("Unable to detect the ALL ITEMS TABLE: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {

                    //Locate the table title "All Items"
                    WebElement table_Title = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/thead/tr/th/table/tbody/tr/td/table/tbody/tr/td[1]/descendant::span[contains (text(), 'All items')]"));


                    table_AllItems_Displayed = true;

                    logger.info("The ALL ITEMS TABLE is displayed by default as EXPECTED.");


                } catch (Exception e) {
                    logger.error("Unable to detect the ALL ITEMS TABLE: " + e.getMessage());
                }

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while executing method 'check_AllItems_Table_Displayed()': " + e.getMessage());
        }
        return table_AllItems_Displayed;
    }


    public void click_DisplayItems_Btn() {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Locate the "Display Items" button
                    WebElement btn_DisplayItems = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/table/tbody/tr/td/div/div/div/div/div/div[1]/div/div[1]/div/div/div/div/table/tbody/tr/td[1]/div/span[3]/div[contains (@title, 'Display Items')]"));

                    //scroll to bring 'Display Items' button into view
                    js.executeScript("arguments[0].scrollIntoView();", btn_DisplayItems);

                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_DisplayItems);
                    //btn_New.click();

                    logger.info("The DISPLAY ITEMS button was clicked SUCCESSFULLY.");

                    //wait till "All Items" table is displayed
                    waitTillElementDetected(30, "//form[@name='webguiform0']/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[3]/td/div[8]/descendant::div[@class='lsRasterLayout'][contains (@ct, 'RL')]/div[2]/div/div[3]/descendant::span[contains (@title, 'All Items')]", "ALL ITEMS table");

                } catch (Exception e) {
                    logger.error("Unable to detect the DISPLAY ITEMS button: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {

                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {

                    //Locate the "Display Items" button
                    WebElement btn_DisplayItems = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[1]/div/div/div/div/div/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td[1]/div/span[3]/div[contains (@title, 'Display Items')]"));

                    //scroll to bring 'Display Items' button into view
                    js.executeScript("arguments[0].scrollIntoView();", btn_DisplayItems);

                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", btn_DisplayItems);
                    //btn_New.click();

                    logger.info("The DISPLAY ITEMS button was clicked SUCCESSFULLY.");

                    //wait till "All Items" table is displayed
                    waitTillElementDetected(30, "//form[@name='webguiform']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/div/div/div/div[2]/div/table/tbody/tr[3]/td/div[8]/descendant::div[contains (@class, 'lsHTMLContainer')][contains (@ct, 'SC')]/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div/div/table/thead/tr/th/table/tbody/tr/td/table/tbody/tr/td[1]/descendant::span[contains (text(), 'All items')]", "ALL ITEMS table");


                } catch (Exception e) {
                    logger.error("Unable to detect the DISPLAY ITEMS button: " + e.getMessage());
                }

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while executing method 'click_DisplayItems_Btn()': " + e.getMessage());
        }

    }


    public void fillout_CustInput(String input_CustValue) {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {
                    WebElement input_Cust = driver.findElement(By.xpath("//input[contains (@title, 'Sold-To Party')]"));

                    js.executeScript("arguments[0].click();", input_Cust);
                    Thread.sleep(1000);

                    input_Cust.clear();
                    input_Cust.sendKeys(input_CustValue);
                    input_Cust.sendKeys(Keys.RETURN);
                    logger.info("CUST. input field is SUCCESSFULLY filled in.");

                    //wait till the "Sales Area for Customer" popup is displayed (the table's 1st row)
                    //waitTillElementDetected(15, "//div[@id='webguiPopups']/div/div/div[3][contains (@class, 'urPWContent')]/descendant::td[@id='userarealist1-content'][1]/div/div[30][contains (text(), 'GNSO')]", "FIRST TABLE's ROW on SALES AREA FOR CUSTOMER popup");
                    //Thread.sleep(1000);


                } catch (Exception e) {
                    logger.error("Error while providing value in the 'CUST.' input field: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().defaultContent();
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                try {

                    WebElement input_Cust = driver.findElement(By.xpath("//form[contains (@name, 'webguiform')]/table/tbody/tr[2]/descendant::div[contains (@class, ' IsITSUserAreaCntScrl urBorderBox')]/div/div[1]/descendant::div[contains (@class, 'urScrl urBorderBox')]/div[1][contains (@class, 'urBorderBox')]/div/div[1]/descendant::input[contains (@title, 'Sold-To Party')]"));

                    js.executeScript("arguments[0].click();", input_Cust);
                    Thread.sleep(1000);

                    input_Cust.clear();
                    input_Cust.sendKeys(input_CustValue);
                    input_Cust.sendKeys(Keys.RETURN);
                    logger.info("CUST. input field is SUCCESSFULLY filled in.");

                   /* driver.switchTo().defaultContent();
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'URLSPW-0')]")));

                    //wait till the "Sales Area for Customer" popup is displayed (the table's 1st row)
                    waitTillElementDetected(15, "//div[@id='webguiPage1']/div[1]/div/div[3][contains (@class, 'urPWContent')]/descendant::td[@id='userpanel']/table/tbody/tr/td[1]/div[30][contains (text(), 'GNSO')]", "FIRST TABLE's ROW on SALES AREA FOR CUSTOMER popup");
                    Thread.sleep(1000);

                    driver.switchTo().defaultContent();*/

                } catch (Exception e) {
                    logger.error("Error while providing value in the 'CUST.' input field: " + e.getMessage());
                }
                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Unable to execute method 'fillout_CustInput()': " + e.getMessage());
        }

    }


    public void fillout_PurchaseGroupInput(String value_PurchasingGroup) {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {
                    WebElement input_PurchGroup = driver.findElement(By.xpath("//table[contains (@ct, 'TS_standards')][contains (@class, 'lsTbsPanelDesign lsPanel')]/tbody/tr[3]/td/div[7]/descendant::input[contains (@title, 'Purchasing Group')]"));

                    js.executeScript("arguments[0].click();", input_PurchGroup);
                    Thread.sleep(1000);

                    input_PurchGroup.clear();
                    input_PurchGroup.sendKeys(value_PurchasingGroup);
                    input_PurchGroup.sendKeys(Keys.RETURN);
                    logger.info("PURCHASING GROUP input field is SUCCESSFULLY filled in.");

                    //wait till "Please enter supplying plant" status is displayed in the footer
                    waitTillElementDetected(15, "//form[@id='webguiform0']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/descendant::span[contains(text(), 'Please enter supplying plant')]", "FOOTER STATUS 'PLEASE ENTER SUPPLYING PLANT'");
                    Thread.sleep(1000);


                } catch (Exception e) {
                    logger.error("Error while providing value in the 'Purchasing Group' input field: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'itsframe1_')]")));

                try {

                    WebElement input_PurchGroup = driver.findElement(By.xpath("//table[contains (@ct, 'TS_ie6')][contains (@class, 'lsTbsPanelDesign lsPanel')]/tbody/tr[3]/td/div[7]/descendant::input[contains (@title, 'Purchasing group')]"));

                    js.executeScript("arguments[0].click();", input_PurchGroup);
                    Thread.sleep(1000);

                    input_PurchGroup.clear();
                    input_PurchGroup.sendKeys(value_PurchasingGroup);
                    input_PurchGroup.sendKeys(Keys.RETURN);
                    logger.info("PURCHASING GROUP input field is SUCCESSFULLY filled in.");

                    //wait till "Please enter supplying plant" status is displayed in the footer
                    waitTillElementDetected(15, "//form[@id='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div/descendant::span[contains(text(), 'Please enter supplying plant')]", "FOOTER STATUS 'PLEASE ENTER SUPPLYING PLANT'");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    logger.error("Error while providing value in the 'Purchasing Group' input field: " + e.getMessage());
                }
                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Unable to execute method 'fillout_PurchaseGroupInput()': " + e.getMessage());
        }

    }


    public void fillout_PurchaseOrganizationInput(String value_PurchasingOrganization) {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {
                    WebElement input_PurchOrg = driver.findElement(By.xpath("//table[contains (@ct, 'TS_standards')][contains (@class, 'lsTbsPanelDesign lsPanel')]/tbody/tr[3]/td/div[8]/descendant::input[contains (@title, 'Purchasing Organization')]"));

                    js.executeScript("arguments[0].click();", input_PurchOrg);
                    Thread.sleep(1000);

                    input_PurchOrg.clear();
                    input_PurchOrg.sendKeys(value_PurchasingOrganization);
                    input_PurchOrg.sendKeys(Keys.RETURN);
                    logger.info("PURCHASING ORGANIZATION input field is SUCCESSFULLY filled in.");

                    //wait till the "Company Code" field gets auto-filled with "GOIL" value
                    waitTillElementDetectedByProperty(15, "//table[contains (@ct, 'TS_standards')][contains (@class, 'lsTbsPanelDesign lsPanel')]/tbody/tr[3]/td/div[7]/descendant::input[contains (@title, 'Company Code')]", "value", "GOIL", "COMPANY CODE with value");

                    //wait till "Enter Purchasing Group" status is displayed in the footer
                    waitTillElementDetected(15, "//form[@id='webguiform0']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/descendant::span[contains(text(), 'Enter Purch. Group')]", "FOOTER STATUS 'ENTER PURCHASING GROUP'");
                    Thread.sleep(1000);


                } catch (Exception e) {
                    logger.error("Error while providing value in the 'Purchasing Organization' input field: " + e.getMessage());
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@name, 'itsframe1_')]")));

                try {

                    WebElement input_PurchOrg = driver.findElement(By.xpath("//table[contains (@ct, 'TS_ie6')][contains (@class, 'lsTbsPanelDesign lsPanel')]/tbody/tr[3]/td/div[8]/descendant::input[contains (@title, 'Purchasing Organization')]"));

                    js.executeScript("arguments[0].click();", input_PurchOrg);
                    Thread.sleep(1000);

                    input_PurchOrg.clear();
                    input_PurchOrg.sendKeys(value_PurchasingOrganization);
                    input_PurchOrg.sendKeys(Keys.RETURN);
                    logger.info("PURCHASING ORGANIZATION input field is SUCCESSFULLY filled in.");

                    //wait till the "Company Code" field gets auto-filled with "GOIL" value
                    waitTillElementDetectedByProperty(15, "//table[contains (@ct, 'TS_ie6')][contains (@class, 'lsTbsPanelDesign lsPanel')]/tbody/tr[3]/td/div[7]/descendant::input[contains (@title, 'Company Code')]", "value", "GOIL", "COMPANY CODE with value");

                    //wait till "Enter Purchasing Group" status is displayed in the footer
                    waitTillElementDetected(15, "//form[@id='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div/descendant::span[contains(text(), 'Enter Purch. Group')]", "FOOTER STATUS 'ENTER PURCHASING GROUP'");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    logger.error("Error while providing value in the 'Purchasing Organization' input field: " + e.getMessage());
                }
                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Unable to execute method 'fillout_PurchaseOrderInput()': " + e.getMessage());
        }

    }


    public boolean check_FooterEmptyStatus() {

        boolean readyToEnterInputs = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Locate the text containing element
                    WebElement text_footer = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform0']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[2][contains (@class, 'lsMessageBar__text lsMessageBar__text--overflow')]"));

                    String displayTitle = text_footer.getAttribute("title");
                    System.out.println("The text in the footer is: " + displayTitle);

                    readyToEnterInputs = true;

                    logger.warn("The footer is displaying the UNEXPECTED status of: " + displayTitle.toUpperCase() + "!");


                } catch (Exception e) {
                    logger.info("The text status on page's footer is EMPTY as EXPECTED!");
                }

            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //driver.switchTo().defaultContent();
                // driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));
                // driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
                // driver.switchTo().frame(0);


                try {

                    //Locate the button to extend/collapse Header panel
                    WebElement text_footer = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[2][contains (@class, 'lsMessageBar__text lsMessageBar__text--overflow')]"));

                    String displayTitle = text_footer.getAttribute("title");
                    System.out.println("The text in the footer is: " + displayTitle);

                    readyToEnterInputs = true;

                    logger.warn("The footer is displaying the UNEXPECTED status of: " + displayTitle.toUpperCase() + "!");


                } catch (Exception e) {
                    logger.info("The text status on page's footer is EMPTY as EXPECTED!");
                }

                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Error while executing method 'check_FooterEmptyStatus()': " + e.getMessage());
        }
        return readyToEnterInputs;
    }


    public boolean check_FooterStatus(String StatusText) {

        boolean readyToEnterInputs = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                try {

                    //Locate the text containing element
                    WebElement text_footer = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform0']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[2][contains (@class, 'lsMessageBar__text lsMessageBar__text--overflow')]"));

                    String displayTitle = text_footer.getAttribute("title");
                    System.out.println("The text in the footer is: " + displayTitle);

                    if (displayTitle.contains(StatusText)) {
                        readyToEnterInputs = true;

                        logger.info("The footer is displaying EXPECTED status of: " + StatusText.toUpperCase());
                    } else {
                        logger.warn("The footer is displaying the UNEXPECTED status of: " + displayTitle.toUpperCase() + "!");
                    }

                } catch (Exception e) {
                    logger.error("Unable to detect the text status on page's footer: " + e.getMessage());
                }

            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                //driver.switchTo().defaultContent();
                // driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));

                // driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id, 'ITSFRAME1')]")));
                // driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
                // driver.switchTo().frame(0);


                try {

                    //Locate the button to extend/collapse Header panel
                    WebElement text_footer = driver.findElement(By.ByXPath.xpath("//form[@name='webguiform']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/div/div[1]/span[2][contains (@class, 'lsMessageBar__text lsMessageBar__text--overflow')]"));

                    String displayTitle = text_footer.getAttribute("title");
                    System.out.println("The text in the footer is: " + displayTitle);

                    if (displayTitle.contains("Select a Sold-to party")) {
                        readyToEnterInputs = true;

                        logger.info("The footer is displaying EXPECTED status of: " + StatusText.toUpperCase());
                    } else {
                        logger.warn("The footer is displaying the UNEXPECTED status of: " + displayTitle.toUpperCase() + "!");
                    }

                } catch (Exception e) {
                    logger.error("Unable to detect the text status on page's footer: " + e.getMessage());
                }

                driver.switchTo().defaultContent();
            }


        } catch (Exception e) {
            logger.error("Error while executing method 'check_FooterStatus()': " + e.getMessage());
        }
        return readyToEnterInputs;
    }


    public void selectOrderType(String input_OrderType) {

        boolean popup_Displayed = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                WebElement dropdown_OrderType = driver.findElement(By.xpath("//div[@id='screenarea']/div[2]/table/tbody/tr/td/input[contains(@title, 'Sales Document Type')]"));

                //click on "Order Type" field to enter a value
                js.executeScript("arguments[0].click();", dropdown_OrderType);
                //dropdown_OrderType.click();

                Thread.sleep(2000);

                //enter "OTA" value into "Order Type" field and hit ENTER key
                dropdown_OrderType.clear();
                dropdown_OrderType.sendKeys(input_OrderType);
                //dropdown_OrderType.sendKeys(Keys.RETURN);
                logger.info("Value " + input_OrderType + " was SUCCESSFULLY entered into 'Order Type' field.");

                /*
                try {
                    //wait till "Create Sales Documents" popup is displayed
                    waitTillElementDetected(300, "//div[contains(text(), 'Create Sales Documents')][contains (@role, 'dialog')]", "'CREATE SALES DOCUMENTS' popup (after providing ORDER TYPE)");

                    popup_Displayed = true;

                } catch (Exception e) {
                    logger.info("The 'Create Sales Document' popup did not show up after providing OTA order type: " + e.getMessage());
                }


                if (popup_Displayed == false) {

                    //wait till "Cust." drop-down is displayed
                    waitTillElementDetected(300, "//div[@id='screenarea']/div[1]/div/div/div/div[1]/descendant::input[contains(@title, 'Sold-To Party')]", "'CREATE STANDARD ORDER: OVERVIEW' (after providing ORDER TYPE)");
                    Thread.sleep(2000);
                } else {
                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/descendant::div[@role='button'][contains (@title, 'No ')]")));
                    //btn_New.click();

                    logger.info("'Create Sales Documents' popup was successfully closed after clicking on 'NO' button.");

                    //wait till popup gets closed
                    waitTillElementDetected(300, "//input[contains (@title, 'Sold-To Party')]", "'CREATE STANDARD ORDER: OVERVIEW' (after providing ORDER TYPE)");
                    Thread.sleep(2000);
                }*/
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'ITSFRAME1')]")));

                WebElement dropdown_OrderType = driver.findElement(By.xpath("//div[@id='userarea-scrl']/div[1]/div/div[2]/descendant::input[contains(@title, 'Sales Document Type')]"));

                //click on "Order Type" field to enter a value
                js.executeScript("arguments[0].click();", dropdown_OrderType);
                //dropdown_OrderType.click();

                Thread.sleep(2000);

                //enter "OTA" value into "Order Type" field and hit ENTER key
                dropdown_OrderType.clear();
                dropdown_OrderType.sendKeys(input_OrderType);
                //dropdown_OrderType.sendKeys(Keys.RETURN);
                logger.info("Value " + input_OrderType + " was SUCCESSFULLY entered into 'Order Type' field.");

                driver.switchTo().defaultContent();

                /*
                try {
                    driver.switchTo().defaultContent();
                    //driver.switchTo().frame("ITSFRAME1");
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'URLSPW-0')]")));

                    //wait till "Create Sales Documents" popup is displayed
                    waitTillElementDetected(300, "//div[contains(text(), 'Create Sales Document')]", "'CREATE SALES DOCUMENTS' popup (after providing ORDER TYPE)");

                    popup_Displayed = true;
                    driver.switchTo().defaultContent();

                } catch (Exception e) {
                    driver.switchTo().defaultContent();
                    logger.info("The 'Create Sales Document' popup did not show up after providing OTA order type: " + e.getMessage());
                }


                if (popup_Displayed == false) {

                    //driver.switchTo().frame("ITSFRAME1");
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'ITSFRAME1')]")));

                    //wait till "Cust." drop-down
                    waitTillElementDetected(300, "//form[contains (@name, 'webguiform')]/table/tbody/tr[2]/descendant::div[contains (@class, ' IsITSUserAreaCntScrl urBorderBox')]/div/div[1]/descendant::div[contains (@class, 'urScrl urBorderBox')]/div[1][contains (@class, 'urBorderBox')]/div/div[1]/descendant::input[contains (@title, 'Sold-To Party')]", "'CREATE STANDARD ORDER: OVERVIEW' (after providing ORDER TYPE)");
                    Thread.sleep(2000);

                    driver.switchTo().defaultContent();

                } else {

                    //close the "Create Sales Documents" popup
                    //driver.switchTo().frame("ITSFRAME1");
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'URLSPW-0')]")));

                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains (@id, 'webguiPage1')][contains (@ct, 'PAGE')]/div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[contains (@id, 'webguiPopupWindow1-cnt')][contains (@class, 'urPWContent')]/descendant::div[contains (@title, 'No')]")));
                    //btn_New.click();

                    logger.info("'Create Sales Documents' popup was successfully closed after clicking on 'NO' button.");

                    driver.switchTo().defaultContent();

                    //driver.switchTo().frame("ITSFRAME1");
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'ITSFRAME1')]")));

                    //wait till "Cust." drop-down
                    waitTillElementDetected(300, "//input[contains(@title, 'Sold-To Party')]", "'CREATE STANDARD ORDER: OVERVIEW' (after providing ORDER TYPE)");
                    Thread.sleep(2000);

                    driver.switchTo().defaultContent();

                }*/
            }


        } catch (Exception e) {
            logger.error("Error while filling out ORDER TYPE input field: " + e.getMessage());
        }

    }


    public void selectSalesOrganization(String input_SalesOrg) {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                WebElement dropdown_SalesOrg = driver.findElement(By.xpath("//div[@id='screenarea']/div[3]/table/tbody/tr[2]/td/table/tbody/tr/td/div/div/div/div[2]/descendant::input[contains(@title, 'Sales Organization')]"));

                //click on "Sales Organization" field to enter a value
                js.executeScript("arguments[0].click();", dropdown_SalesOrg);
                //dropdown_OrderType.click();

                Thread.sleep(2000);

                //enter "GNSO" value into "Sales Organization" field
                dropdown_SalesOrg.clear();
                dropdown_SalesOrg.sendKeys(input_SalesOrg);
                logger.info("Value " + input_SalesOrg + " was SUCCESSFULLY entered into 'Sales Organization' field.");
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'ITSFRAME1')]")));

                WebElement dropdown_SalesOrg = driver.findElement(By.xpath("//form[@id='webguiform']/table/tbody/tr[2]/descendant::div[@id='userarea-scrl']/div[1]/div/div[4]/div/table/tbody/tr[2]/td/table/tbody/tr/td/div/div/div/div[2]/descendant::input[contains(@title, 'Sales Organization')]"));

                //click on "Sales Organization" field to enter a value
                js.executeScript("arguments[0].click();", dropdown_SalesOrg);
                //dropdown_OrderType.click();

                Thread.sleep(2000);

                //enter "GNSO" value into "Sales Organization" field
                dropdown_SalesOrg.clear();
                dropdown_SalesOrg.sendKeys(input_SalesOrg);
                //dropdown_OrderType.sendKeys(Keys.RETURN);
                logger.info("Value " + input_SalesOrg + " was SUCCESSFULLY entered into 'Sales Organization' field.");

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while filling out SALES ORGANIZATION input field: " + e.getMessage());
        }

    }


    public void selectDistributionChannel(String input_DistrChannel) {

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                WebElement dropdown_DistributionChnl = driver.findElement(By.xpath("//div[@id='screenarea']/div[3]/table/tbody/tr[2]/td/table/tbody/tr/td/div/div/div/div[4]/descendant::input[contains(@title, 'Distribution Channel')]"));

                //click on "Distribution Channel" field to enter a value
                js.executeScript("arguments[0].click();", dropdown_DistributionChnl);
                //dropdown_OrderType.click();

                Thread.sleep(2000);

                //enter "G1" value into "Distribution Channel" field
                dropdown_DistributionChnl.clear();
                dropdown_DistributionChnl.sendKeys(input_DistrChannel);
                logger.info("Value " + input_DistrChannel + " was SUCCESSFULLY entered into 'Distribution Channel' field.");
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'ITSFRAME1')]")));

                WebElement dropdown_DistributionChnl = driver.findElement(By.xpath("//form[@id='webguiform']/table/tbody/tr[2]/descendant::div[@id='userarea-scrl']/div[1]/div/div[4]/div/table/tbody/tr[2]/td/table/tbody/tr/td/div/div/div/div[5]/descendant::input[contains(@title, 'Distribution Channel')]"));

                //click on "Distribution Channel" field to enter a value
                js.executeScript("arguments[0].click();", dropdown_DistributionChnl);
                //dropdown_OrderType.click();

                Thread.sleep(2000);

                //enter "G1" value into "Distribution Channel" field
                dropdown_DistributionChnl.clear();
                dropdown_DistributionChnl.sendKeys(input_DistrChannel);
                //dropdown_OrderType.sendKeys(Keys.RETURN);
                logger.info("Value " + input_DistrChannel + " was SUCCESSFULLY entered into 'Distribution Channel' field.");

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            logger.error("Error while filling out DISTRIBUTION CHANNEL input field: " + e.getMessage());
        }

    }


    public void selectDivision(String input_Division) {

        boolean popup_Displayed = false;

        try {

            if (Test_Cases.conf_SystemClient.equals("OFQ_100")) {

                WebElement dropdown_Division = driver.findElement(By.xpath("//div[@id='screenarea']/div[3]/table/tbody/tr[2]/td/table/tbody/tr/td/div/div/div/div[6]/descendant::input[contains(@title, 'Division')]"));

                //click on "Division" field to enter a value
                js.executeScript("arguments[0].click();", dropdown_Division);
                //dropdown_OrderType.click();

                Thread.sleep(2000);

                //enter "Division" value into "Order Type" field and hit ENTER key
                dropdown_Division.clear();
                dropdown_Division.sendKeys(input_Division);
                dropdown_Division.sendKeys(Keys.RETURN);
                logger.info("Value " + input_Division + " was SUCCESSFULLY entered into 'Division' field.");

                try {
                    //wait till "Create Sales Documents" popup is displayed
                    waitTillElementDetected(300, "//div[contains(text(), 'Create Sales Documents')][contains (@role, 'dialog')]", "'CREATE SALES DOCUMENTS' popup (after providing DIVISION)");

                    popup_Displayed = true;

                } catch (Exception e) {
                    logger.info("The 'Create Sales Document' popup did not show up after providing Division value: " + e.getMessage());
                }


                if (popup_Displayed == false) {

                    //wait till "Cust." drop-down is displayed
                    waitTillElementDetected(300, "//div[@id='screenarea']/div[1]/div/div/div/div[1]/descendant::input[contains(@title, 'Sold-To Party')]", "'CREATE STANDARD ORDER: OVERVIEW' (after providing DIVISION)");
                    Thread.sleep(2000);
                } else {
                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains (@id, 'webguiPopups')]/descendant::div[@role='button'][contains (@title, 'No ')]")));
                    //btn_New.click();

                    logger.info("'Create Sales Documents' popup was successfully closed after clicking on 'NO' button.");

                    //wait till popup gets closed
                    waitTillElementDetected(300, "//input[contains (@title, 'Sold-To Party')]", "'CREATE STANDARD ORDER: OVERVIEW' (after providing DIVISION)");
                    Thread.sleep(2000);
                }
            }


            if (Test_Cases.conf_SystemClient.equals("OGQ_100")) {
                driver.switchTo().defaultContent();
                //driver.switchTo().frame("ITSFRAME1");
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'ITSFRAME1')]")));

                WebElement dropdown_Division = driver.findElement(By.xpath("//form[@id='webguiform']/table/tbody/tr[2]/descendant::div[@id='userarea-scrl']/div[1]/div/div[4]/div/table/tbody/tr[2]/td/table/tbody/tr/td/div/div/div/div[8]/descendant::input[contains(@title, 'Division')]"));

                //click on "Division" field to enter a value
                js.executeScript("arguments[0].click();", dropdown_Division);
                //dropdown_OrderType.click();

                Thread.sleep(2000);

                //enter "G1" value into "Division" field and hit ENTER key
                dropdown_Division.clear();
                dropdown_Division.sendKeys(input_Division);
                dropdown_Division.sendKeys(Keys.RETURN);
                logger.info("Value " + input_Division + " was SUCCESSFULLY entered into 'Division' field.");

                driver.switchTo().defaultContent();

                try {
                    driver.switchTo().defaultContent();
                    //driver.switchTo().frame("ITSFRAME1");
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'URLSPW-0')]")));

                    //wait till "Create Sales Documents" popup is displayed
                    waitTillElementDetected(300, "//div[contains(text(), 'Create Sales Document')]", "'CREATE SALES DOCUMENTS' popup (after providing DIVISION)");

                    popup_Displayed = true;
                    driver.switchTo().defaultContent();

                } catch (Exception e) {
                    driver.switchTo().defaultContent();
                    logger.info("The 'Create Sales Document' popup did not show up after providing Division value: " + e.getMessage());
                }


                if (popup_Displayed == false) {

                    //driver.switchTo().frame("ITSFRAME1");
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'ITSFRAME1')]")));

                    //wait till "Cust." drop-down
                    waitTillElementDetected(300, "//form[contains (@name, 'webguiform')]/table/tbody/tr[2]/descendant::div[contains (@class, ' IsITSUserAreaCntScrl urBorderBox')]/div/div[1]/descendant::div[contains (@class, 'urScrl urBorderBox')]/div[1][contains (@class, 'urBorderBox')]/div/div[1]/descendant::input[contains (@title, 'Sold-To Party')]", "'CREATE STANDARD ORDER: OVERVIEW' (after providing DIVISION)");
                    Thread.sleep(2000);

                    driver.switchTo().defaultContent();

                } else {

                    //close the "Create Sales Documents" popup
                    //driver.switchTo().frame("ITSFRAME1");
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'URLSPW-0')]")));

                    //action.moveToElement(btn_New).click().build().perform();
                    js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains (@id, 'webguiPage1')][contains (@ct, 'PAGE')]/div[contains (@class, 'urPWOuterBorder lsPopupWindow lsPopupWindow--dialog')][1]/div/div[contains (@id, 'webguiPopupWindow1-cnt')][contains (@class, 'urPWContent')]/descendant::div[contains (@title, 'No')]")));
                    //btn_New.click();

                    logger.info("'Create Sales Documents' popup was successfully closed after clicking on 'NO' button.");

                    driver.switchTo().defaultContent();

                    //driver.switchTo().frame("ITSFRAME1");
                    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains (@id,'ITSFRAME1')]")));

                    //wait till "Cust." drop-down
                    waitTillElementDetected(300, "//input[contains(@title, 'Sold-To Party')]", "'CREATE STANDARD ORDER: OVERVIEW' (after providing DIVISION)");
                    Thread.sleep(2000);

                    driver.switchTo().defaultContent();

                }
            }


        } catch (Exception e) {
            logger.error("Error while filling out DIVISION input field: " + e.getMessage());
        }

    }

}
