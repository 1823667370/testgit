package com.lz.libarary.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.lz.libarary.utils.UIScreenShot;

/**
 * 
 * @version 1.00.00
 * @description: 页面初始化-框架加载的时候递归初始化pages包下所有 Page的子类
 * @author: songer.xing
 * @date: 2016-6-10
 * @history:
 */
public class Page {

	protected static UIScreenShot uiScreenShot;

	public static void initAllPages(WebDriver driver) {
		///D:/works/lzmhwy/target/test-classes/com/lz/libarary/pages/
		//file:/D:/works/lzmhwy/target/test-classes/com/lz/libarary/pages/
		File rootFile = new File(Page.class.getResource(".").getFile()); 

		// 初始化所有子类
		setSonList(rootFile, rootFile.getAbsolutePath() , Page.class,
				rootFile.getAbsolutePath() , driver);
		uiScreenShot = new UIScreenShot();
		uiScreenShot.init(driver);
	}

	public static void takeScreenShot() {
		if (uiScreenShot != null) {
			uiScreenShot.takeScreenShot();
		}
	}

	public static <T> void setSonList(File rootFile, String parentDirectory,
			Class<T> parentClass, String mainPath, WebDriver driver) {
		if (rootFile.isDirectory()) {
			File[] files = rootFile.listFiles();
			for (File file : files) {
				setSonList(file, parentDirectory, parentClass, mainPath, driver);
			}
		} else {
			String className = null;
			try {
				if (rootFile.getPath().indexOf(".class") != -1) {

					className = rootFile.getPath().replace(mainPath, "")
							.replace(".class", "").replace("\\", ".");
					System.err.println("初始化Page:"
							+ parentClass.getPackage().getName() 
							+ className);
					Class<?> classObject = Class.forName(parentClass
							.getPackage().getName() + className);
					classObject.asSubclass(parentClass);
					PageFactory.initElements(driver, (Class<?>) classObject);

				}
			} catch (ClassNotFoundException e) {
				System.err.println("找不到类 " + className);
			} catch (ClassCastException e) {
				System.err.println(className + " 不是 " + parentClass + " 的子类");
			}
		}
	}
	
	public static void main(String[] args) {
//		File rootFile = new File(Page.class.getResource(".").getFile());
//		File rootFile1 = new File("D:/works/lzmhwy/target/test-classes/com/lz/libarary/pages/accCard.class");
//		String className=rootFile1.getPath().replace(rootFile.getAbsolutePath(), "")
//				.replace(".class", "").replace("\\", ".");
//		System.out.println(className);
//		System.err.println("初始化Page:"
//				+ Page.class.getPackage().getName() + "."
//				+ className);
		File rootFile = new File(Page.class.getResource(".").getFile()); 

		// 初始化所有子类
		setSonList(rootFile, rootFile.getAbsolutePath() , Page.class,
				rootFile.getAbsolutePath() , null);
	}

}
