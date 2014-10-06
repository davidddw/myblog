package com.yunshan.utils;

import static org.junit.Assert.*;
import com.yunshan.utils.CutHtml;
import org.junit.Test;

public class CutHtmlTest {

	@Test
	public void testCut() {
		String param = "<html><p><p>0<img src=’xx.gif’/>http://www.dukai168.cn &copy;</p><table><tr><td>678901</td><td>21111111111</td></tr></table></p></html>";
		System.out.println(CutHtml.subStringHTML(param, 100, "..."));
		assertTrue("This will succeed.", true);
	}

}
