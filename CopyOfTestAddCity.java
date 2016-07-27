package com.lifecalendar.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CopyOfTestAddCity {
	AppiumDriver<WebElement> driver;
	List<String> arraylist = null;//中间变量主要用于做比较
   static int CITY_POSTION=(int)(Math.random()*32);//热门城市的坐标
	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity",
				".activity.weather.WeatherNewActivity");
		capabilities.setCapability("unicodeKeyboard", "ture");
		capabilities.setCapability("resetKeyboard", "ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@Test
	// 添加城市
	public void addCity() {
		try {

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		driver.findElementById("com.updrv.lifecalendar:id/weather_new_title_iv")
				.click();// 点击天气详情界面的添加按钮
		// 截图方法
	
		// 首次进入城市管理界面 已存在的城市
		 
	
		if(driver.findElementsByXPath("//android.widget.LinearLayout[2]/android.widget.RelativeLayout").size()>=9){
	
		driver.swipe(250, 640, 250, 320, 1);
		new AddCities().addCities();
		}else{
		new AddCities().addCities();
		}
	}

	@After
	public void teardown() {
		driver.quit();
	}
class AddCities{
	
	public void addCities(){
		List<WebElement> excistcitylist = driver
				.findElementsByXPath("//android.widget.LinearLayout[2]/android.widget.TextView[1]");

		// 一般的for循环遍历城市管理界面存在的城市
		List<String> al = new ArrayList<String>();
		for (int i = 0; i < excistcitylist.size(); i++) {
			System.out.println("进入城市管理界面已存在的城市为:"+excistcitylist.get(i).getText());

			al.add(excistcitylist.get(i).getText());

		}
		arraylist = al;
		//System.out.println("arraylist=" + arraylist.size());
		List<WebElement> cityList = driver
				.findElementsByXPath("//android.widget.GridView/android.widget.RelativeLayout");
		List<WebElement> weatherimageList = driver
				.findElementsByXPath("//android.widget.GridView/android.widget.RelativeLayout/android.widget.LinearLayout[2]");
		System.out.println("天气图片的长度为："+weatherimageList.size());	
		if (weatherimageList.size()>11) {
				System.out.println("添加的城市已达上限！");

			} else {

				// 点击添加按钮
				driver.findElementById("com.updrv.lifecalendar:id/addcity")
						.click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					System.out.println("页面可能还未跳转到热门城市界面");
				}
				String selectedcity = driver//定义一个选中的城市
						.findElementsByXPath(
								"//android.widget.GridView/android.widget.LinearLayout/android.widget.TextView")
						.get(CITY_POSTION).getText();
				System.out.println("选择城市文本的长度："+driver
						.findElementsByXPath(
								"//android.widget.GridView/android.widget.LinearLayout/android.widget.TextView").size());
				System.out.println("CITY_POSTION的值为："+CITY_POSTION);
				System.out.println("选择的城市为:" + selectedcity);
				List<WebElement> lt = driver
						.findElementsByXPath("//android.widget.GridView[@index=3]/android.widget.LinearLayout");
				System.out.println("点击的城市长度是" + lt.size());
				for (int i = 0; i < arraylist.size(); i++) {
					if (arraylist.get(i).contains(selectedcity)) {
						System.out.println(selectedcity + "已添加");
						break;
					} else if (selectedcity.contains("定位")) {
						System.out.println("点击了定位");
					} 
					else{System.out.println("城市未被添加执行添加操作");
//						List<WebElement> lt = driver
//								.findElementsByXPath("//android.widget.GridView[@index=3]/android.widget.LinearLayout");
//						System.out.println("点击的城市长度是" + lt.size());
						System.out.println("CITY_POSTION的值为："+CITY_POSTION);
//						driver.findElementsByXPath(
//								"//android.widget.GridView[@index=3]/android.widget.LinearLayout")
//								.get(CITY_POSTION).click();
						lt.get(CITY_POSTION).click();

					}

				}

			}
		
//		List<WebElement> lt = driver
//				.findElementsByXPath("//android.widget.GridView[@index=3]/android.widget.LinearLayout");
//		System.out.println("点击的城市长度是" + lt.size());
	}
}
}
