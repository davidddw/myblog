package org.tcloud.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class AdminControllerTest extends JUnitActionBase{

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test    
    public void testAdd() throws Exception {    
		MockHttpServletRequest request = new MockHttpServletRequest();    
        MockHttpServletResponse response = new MockHttpServletResponse();    
        request.setRequestURI("/admin");     
        request.setMethod("GET");    
        request.addParameter("command", "listArticles");
        // 执行URI对应的action    
        //final ModelAndView mav = this.excuteAction(request, response);    

        System.out.println(response.getContentAsString());    
    }    

}
