package com.trendyol.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

public class BrowserUtils extends Config {

    public static WebDriver driver= Driver.get();
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAITTIME_XLARGE));
    Actions actions = new Actions(Driver.get());

    public WebElement getPresentElement(By by, int waitingSeconds) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement getPresentElement(By by) {
        return getPresentElement(by, WAITTIME_XLARGE);
    }

    public List<WebElement> getPresentElements(By by, int waitingSeconds) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> getPresentElements(By by) {
        return getPresentElements(by, WAITTIME_XLARGE);
    }

    public void clickElement(By by) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        hoverElement(element);
        element.click();
    }

    public String getAttributeOfElement(By by, String attributeName) {
        WebElement element = getPresentElement(by);
        return element.getAttribute(attributeName);
    }

    public String extractProductId(String attributeValue, String substringIdentifier) {
        int startIndex = attributeValue.lastIndexOf(substringIdentifier) + substringIdentifier.length();
        return attributeValue.substring(startIndex);
    }

    private Actions withAction() {
        return new Actions(driver);
    }

    public void hoverElement(By by) {
        WebElement element = getPresentElement(by);
        withAction().moveToElement(element).build().perform();
    }

    public void hoverElement(WebElement webElement) {
        withAction().moveToElement(webElement).build().perform();
    }

    public void typeElement(By by, String text, boolean withClear) {
        WebElement element = getPresentElement(by);
        hoverElement(element);
        if (withClear)
            element.clear();
        element.sendKeys(text);
    }

    public boolean isTheElementPresent(By by, int waitingSeconds) {
        try {
            wait.withTimeout(Duration.ofSeconds(waitingSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public String getLocatorFromString(String locatorAsString, String fistVariable) {
        return String.format(locatorAsString, fistVariable);
    }

    public String getLocatorFromInt(String locatorAsString, int fistVariable) {
        return String.format(locatorAsString, fistVariable);
    }

    public void waitMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitSeconds(int seconds) {
        waitMillis(seconds * 1000);
    }

    public void takeScreenshot(WebElement element, String filePath) {
        // Get the location and size of the element
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();

        // Cast the WebDriver instance to TakesScreenshot
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

        // Capture the screenshot as a file
        File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

        try {
            // Read the captured screenshot as an image
            BufferedImage fullScreenshot = ImageIO.read(screenshotFile);

            // Create a new image for the element screenshot
            BufferedImage elementScreenshot = fullScreenshot.getSubimage(
                    elementLocation.getX(),
                    elementLocation.getY(),
                    elementSize.getWidth(),
                    elementSize.getHeight()
            );

            // Save the element screenshot as a file
            ImageIO.write(elementScreenshot, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean compareImages(String imagePath1, String imagePath2) {
        try {
            // Read the image files as byte arrays
            byte[] imageBytes1 = Files.readAllBytes(new File(imagePath1).toPath());
            byte[] imageBytes2 = Files.readAllBytes(new File(imagePath2).toPath());

            // Compare the byte arrays
            boolean byteArraysEqual = java.util.Arrays.equals(imageBytes1, imageBytes2);

            // Read the images from files
            BufferedImage image1 = ImageIO.read(new File(imagePath1));
            BufferedImage image2 = ImageIO.read(new File(imagePath2));

            // Get the dimensions of the images
            int width1 = image1.getWidth();
            int height1 = image1.getHeight();
            int width2 = image2.getWidth();
            int height2 = image2.getHeight();

            // If the dimensions are not equal, the images are considered different
            boolean dimensionsEqual = width1 == width2 && height1 == height2;

            // Compare the pixels of the images
            boolean pixelsEqual = true;
            if (dimensionsEqual) {
                for (int y = 0; y < height1; y++) {
                    for (int x = 0; x < width1; x++) {
                        int rgb1 = image1.getRGB(x, y);
                        int rgb2 = image2.getRGB(x, y);

                        // If any pixel differs, the images are considered different
                        if (rgb1 != rgb2) {
                            pixelsEqual = false;
                            break;
                        }
                    }
                    if (!pixelsEqual) {
                        break;
                    }
                }
            } else {
                pixelsEqual = false;
            }

            // All pixels and dimensions are equal, the images are considered identical
            return byteArraysEqual && dimensionsEqual && pixelsEqual;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void switchTab(int tabNo) {
       new Window().switchTab(driver, tabNo);
    }

    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }
}