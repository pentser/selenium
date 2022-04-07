package com.elip.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement el){

        el.click();
    }


    public void fillText(WebElement el,String text){

        el.clear();
        el.sendKeys(text);
    }

    public String getText(WebElement el) throws InterruptedException {
      Thread.sleep(2000);
      return el.getAttribute("value");
    }





}

