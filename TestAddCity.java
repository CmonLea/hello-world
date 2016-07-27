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

public class TestAddCity {
	AppiumDriver<WebElement> driver;
	List<String> arraylist = null;//�м������Ҫ�������Ƚ�
   static int CITY_POSTION=(int)(Math.random()*32);//���ų��е�����
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
	// ��ӳ���
	public void addCity() {
		try {

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(driver, "���������������ͼ.png");
		driver.findElementById("com.updrv.lifecalendar:id/weather_new_title_iv")
				.click();// �����������������Ӱ�ť
		// ��ͼ����
		snapshot(driver, "���ǰ���й�������ͼ.png");
		// �״ν�����й������ �Ѵ��ڵĳ���
		List<WebElement> excistcitylist = driver
				.findElementsByXPath("//android.widget.LinearLayout[2]/android.widget.TextView");
		// һ���forѭ���������й��������ڵĳ���
		List<String> al = new ArrayList<String>();
		for (int i = 0; i < excistcitylist.size(); i++) {
			System.out.println("������й�������Ѵ��ڵĳ���Ϊ:"+excistcitylist.get(i).getText());

			al.add(excistcitylist.get(i).getText());

		}
		arraylist = al;
		//System.out.println("arraylist=" + arraylist.size());
		List<WebElement> cityList = driver
				.findElementsByXPath("//android.widget.GridView/android.widget.RelativeLayout");
		
			if (cityList.size() == 12) {
				System.out.println("��ӵĳ����Ѵ����ޣ�");

			} else {

				// �����Ӱ�ť
				driver.findElementById("com.updrv.lifecalendar:id/addcity")
						.click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
					System.out.println("ҳ����ܻ�δ��ת�����ų��н���");
				}
				String selectedcity = driver//����һ��ѡ�еĳ���
						.findElementsByXPath(
								"//android.widget.GridView/android.widget.LinearLayout/android.widget.TextView")
						.get(CITY_POSTION).getText();
				System.out.println("CITY_POSTION��ֵΪ��"+CITY_POSTION);
				System.out.println("ѡ��ĳ���Ϊ:" + selectedcity);
//				List<WebElement> lt = driver
//						.findElementsByXPath("//android.widget.GridView[@index=3]/android.widget.LinearLayout");
//				System.out.println("����ĳ��г�����" + lt.size());
				for (int i = 0; i < arraylist.size(); i++) {
					if (arraylist.get(i).contains(selectedcity)) {
						System.out.println(selectedcity + "�����");
					} else if (selectedcity.contains("��λ")) {
						System.out.println("����˶�λ");
					} else{
						System.out.println("����δ�����ִ����Ӳ���");
//						List<WebElement> lt = driver
//								.findElementsByXPath("//android.widget.GridView[@index=3]/android.widget.LinearLayout");
//						System.out.println("����ĳ��г�����" + lt.size());
						System.out.println("CITY_POSTION��ֵΪ��"+CITY_POSTION);
						driver.findElementsByXPath(
								"//android.widget.GridView[@index=3]/android.widget.LinearLayout")
								.get(CITY_POSTION).click();

					}

				}

				snapshot(driver, "���й��������ӳ��к��ͼ.png");
			}
		
//		List<WebElement> lt = driver
//				.findElementsByXPath("//android.widget.GridView[@index=3]/android.widget.LinearLayout");
//		System.out.println("����ĳ��г�����" + lt.size());
	}

	@After
	public void teardown() {
		driver.quit();
	}
	public static void snapshot(TakesScreenshot drivername, String filename) {
		// ����������ڴ�����ͼ ,���������β� ,һ����driver�����ƣ�һ�����ļ�������
		String currentPath = System.getProperty("user.dir"); //��ȡ��ǰ�����ռ�Ŀ¼
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// ��ȡ����ͼ�󣬿��Կ�����ͼ������λ��
		try {
			System.out.println("��ͼ�����·��Ϊ:" + currentPath + "\\" + filename);
			FileUtils
					.copyFile(scrFile, new File(currentPath + "\\" + filename));
		} catch (IOException e) {
			System.out.println("�޷������ͼ");
			e.printStackTrace();
		} finally {
			System.out.println("��ͼ���, �ļ�λ�� " + currentPath + "�ļ���");
		}
	}

}
