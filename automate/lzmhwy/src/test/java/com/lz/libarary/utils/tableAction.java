package com.lz.libarary.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @version 1.00.00
 * @description: 操作table公共类
 * @author: songer.xing
 * @date: 2016-6-10
 * @history:
 */
public class tableAction {

	/**
	 * @description:返回表格制定单元格的内容
	 * @param
	 * @return
	 */
	public static String tableCell(WebDriver driver, int row, int column) {
		String text = null;
		// avoid get the head line of the table
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[" + column + "]";
			System.out.println("---查找后所得的行列为：" + row + column);
			WebElement table = driver.findElement(By.xpath(xpath));
			text = table.getText();
			System.out.println("---查找的名称为：" + text);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return null;
	}

	/**
	 * @description:返回所有行制定列的内容，用于判断添加一列数据是否成功，或判断查找功能的可用性
	 * @param
	 * @return
	 */
	public static String[] tableRows(WebDriver driver, int column) {
		String[] s;
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody";
			WebElement table = driver.findElement(By.xpath(xpath));
			System.out.println("---查找到的数据为：" + table.getText());
			// 首先得到所有tr的集合
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			// 打印出所有单元格的数据
			s = new String[rows.size()];
			System.out.println("----查找后，获取到的行数为：" + rows.size());
			for (int i = 0; i < rows.size(); i++) {
				s[i] = tableCell(driver, i + 1, column);
				System.out.println("---查找到的名称为：" + s[i]);
			}
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return null;

	}

	/**
	 * @description:判断数组内有且仅有1个一样的值，用于新增，不能新增重复
	 * @param
	 * @return
	 */
	public static boolean isArrLoop(String[] arr, String targetValue) {
		if (arr != null) {

			int i = 0;
			for (String s : arr) {
				System.out
						.println("---查找到的名称是：" + s + "---期望值是：" + targetValue);
				if (s.equals(targetValue)) {

					i++;
				}
			}
			if (i == 1) {

				return true;

			}
			return false;

		} else {

			return false;
		}
	}

	public static Integer returntableRow(WebDriver driver, String targetValue,
			int column) {
		int row = -1;
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody";
			WebElement table = driver.findElement(By.xpath(xpath));
			// 首先得到所有tr的集合
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			// 打印出所有单元格的数据
			for (int i = 0; i < rows.size(); i++) {
				if (tableCell(driver, i + 1, column).equals(targetValue)) {
					row = i + 1;
					System.out.println("指定单元格的内容所在的行为：" + row);
				}

			}
			return row;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return -1;
	}

	/**
	 * @description:返回表格制定单元格的内容的行
	 * @param
	 * @return
	 */

	public static Integer[] returntableRows(WebDriver driver,
			String targetValue, int column) {
		Integer[] row;
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody";
			WebElement table = driver.findElement(By.xpath(xpath));
			// 首先得到所有tr的集合
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			// 打印出所有单元格的数据
			row = new Integer[rows.size()];
			for (int i = 0; i < rows.size(); i++) {
				if (tableCell(driver, i + 1, column).equals(targetValue)) {
					row[i] = i + 1;
					System.out.println("指定单元格的内容所在的行为：" + row[i]);

				}

			}
			return row;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * @description:需要 Integer[] returntableRow配合，检查两列数据,返回唯一列
	 * @param
	 * @return
	 * */
	public static Integer returntableRow2(WebDriver driver, Integer[] arr,
			String targetValue, int column) {
		if (arr != null) {
			int row = -1;
			for (int i = 0; i < arr.length; i++) {
				if (tableCell(driver, i + 1, column).equals(targetValue)) {
					row = i + 1;
					System.out.println("指定单元格的内容所在的行为：" + row);
				}
			}
			return row;
		}
		return -1;
	}

	/**
	 * @description:需要 Integer[] returntableRow配合，检查两列数据
	 * @param
	 * @return
	 */

	public static boolean isArr2Loop(WebDriver driver, Integer[] arr,
			String targetValue, int column) {
		if (arr != null) {
		    List<Integer> row=new ArrayList<Integer>();
			for (int i = 0; i < arr.length; i++) {
				if (tableCell(driver, i + 1, column).equals(targetValue)) {
					row.add(i+1);
					System.out.println("找到符合条件的行：" + i+1);

				}
			}
			System.out.println("找到符合条件的行数：" + row.size());
			if (row.size()== 1) {

				return true;

			}
			return false;

		} else {

			return false;
		}
	}

	public static String dialogTableCell(WebDriver driver, int row, int column) {
		String text = null;
		// avoid get the head line of the table
		try {
			String xpath = "//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']/div[2]/div[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[" + column + "]";
			System.out.println("---查找后所得的行列为：" + row + column);
			WebElement table = driver.findElement(By.xpath(xpath));
			text = table.getText();
			System.out.println("---查找的名称为：" + text);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return null;
	}

	public static String[] dialogTableRows(WebDriver driver)
			throws InterruptedException {
		String[] s;
		try {
			String xpath = "//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']/div[2]/div[@class='gridTbody']/table/tbody";
			WebElement table = driver.findElement(By.xpath(xpath));
			System.out.println("---查找到的数据为：" + table.getText());
			// 首先得到所有tr的集合
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			// 打印出所有单元格的数据
			s = new String[rows.size()];
			System.out.println("----查找后，获取到的行数为：" + rows.size());
			for (int i = 0; i < rows.size(); i++) {
				s[i] = dialogTableCell(driver, i + 1, 1);
				System.out.println("---查找到的名称为：" + s[i]);
			}
			return s;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
		}

		return null;

	}

	public static Integer dialogReturntableRow(WebDriver driver,
			String targetValue) throws InterruptedException {
		int row = -1;
		try {
			String xpath = "//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']/div[2]/div[@class='gridTbody']/table/tbody";
			WebElement table = driver.findElement(By.xpath(xpath));
			// 首先得到所有tr的集合
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			// 打印出所有单元格的数据
			for (int i = 0; i < rows.size(); i++) {
				if (dialogTableCell(driver, i + 1, 1).equals(targetValue)) {
					row = i + 1;
					System.out.println("指定单元格的内容所在的行为：" + row);

				}
			}
			return row;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return -1;
	}

	public static Integer dialogResidentBox(WebDriver driver, String targetValue)
			throws InterruptedException {
		int row = -1;
		try {
			String xpath = "//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']/div[2]/div[@class='gridTbody']/table/tbody";
			WebElement table = driver.findElement(By.xpath(xpath));
			// 首先得到所有tr的集合
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			// 打印出所有单元格的数据
			for (int i = 0; i < rows.size(); i++) {
				if (dialogTableCell(driver, i + 1, 2).equals(targetValue)) {
					row = i + 1;
					System.out.println("指定单元格的内容所在的行为：" + row);
				}
			}
			return row;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return -1;
	}
}
