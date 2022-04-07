package com.elip.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebCalc extends BasePage{

    // Web elements Display ,history panel and arrowPen\close panel

    @FindBy(id="input")
    private WebElement inputDisplay;

    @FindBy(id="histFrame")
    private WebElement historyFrame;

    private By ListCollection= By.cssSelector("ul>li>p.l");

    @FindBy(how = How.CSS, using = "ul>li>p.l")
    private List<WebElement> historyItems;

    @FindBy(css=".btn.dropdown-toggle.pull-right")
    private WebElement HistoryFrameButton;


    // Web Element Digits
    @FindBy(id="Btn0")
    private WebElement digit0;

    @FindBy(id="Btn1")
    private WebElement digit1;

    @FindBy(id="Btn2")
    private WebElement digit2;

    @FindBy(id="Btn3")
    private WebElement digit3;

    @FindBy(id="Btn4")
    private WebElement digit4;

    @FindBy(id="Btn5")
    private WebElement digit5;

    @FindBy(id="Btn6")
    private WebElement digit6;

    @FindBy(id="Btn7")
    private WebElement digit7;

    @FindBy(id="Btn8")
    private WebElement digit8;

    @FindBy(id="Btn9")
    private WebElement digit9;

    // Web Element Operations
    @FindBy(id="BtnDiv")
    private WebElement operationDiv;

    @FindBy(id="BtnMulti")
    private WebElement operationMulti;

    @FindBy(id="BtnPlus")
    private WebElement operationPlus;

    private @FindBy(id="BtnMinus")
    WebElement operationMinus;

    // Web Element Operators
    private @FindBy(id="BtnSign")
    WebElement operatorSign;

    @FindBy(id="BtnDot")
    private WebElement operatorDot;

    @FindBy(id="BtnCalc")
    private WebElement operationCalc;

    @FindBy(id="BtnClear")
    private WebElement operationClear;


    // sin Math Function
    @FindBy(id="BtnSin")
    private WebElement funcSin;


    public WebCalc(WebDriver driver) {
        super(driver);
    }

    public String calculate(String Operator,String Operands) throws InterruptedException {
         WebElement selectedOperator;
         String[] opsNum=Operands.split(",");
        Map<Character,WebElement> map =new HashMap<>();
        map.put('0',digit0);
        map.put('1',digit1);
        map.put('2',digit2);
        map.put('3',digit3);
        map.put('4',digit4);
        map.put('5',digit5);
        map.put('6',digit6);
        map.put('7',digit7);
        map.put('8',digit8);
        map.put('9',digit9);

        switch (Operator) {

            case "+":
                selectedOperator=operationPlus;
                break;
            case "sin":
                selectedOperator=funcSin;
                break;
            case "-":

            default:
                selectedOperator=operationMinus;

        }
        if (opsNum.length == 1 && selectedOperator==funcSin) {
            click(selectedOperator);
                for(int j=0;j<opsNum[0].length();j++)
                {
                    char ch=opsNum[0].charAt(j);
                    click(map.get(ch));
                }


        }
        else if  (opsNum.length == 2 ) {
            boolean flag=false;
            for(int i=0;i<opsNum.length;i++) {
                for(int j=0;j<opsNum[i].length();j++)
                {
                    char ch=opsNum[i].charAt(j);
                    click(map.get(ch));
                }
                if(!flag) {
                    click(selectedOperator);
                    flag=true;
                }
            }


        }
        click(operationCalc);


        return getText(inputDisplay);

    }

    public void openHistoryFrame()  {


        click(HistoryFrameButton);


    }

    public void closeHistoryFrame() {

        click(HistoryFrameButton);

    }

    public List<WebElement> getHistoryItems() {

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ListCollection));
        System.out.println(historyItems.size());
        return historyItems;
    }



    public void ClearPanel() {
        click(operationClear);
    }




}
