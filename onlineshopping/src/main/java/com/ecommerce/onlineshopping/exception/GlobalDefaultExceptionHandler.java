package com.ecommerce.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView noHandlerFoundExceptionHandler() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle","The page is not constructed");
		mv.addObject("errorDescription","Something wrong !!! The page you are looking is not available");
		mv.addObject("title","404 Error Page");
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView productNotFoundExceptionHandler() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle","Product Not available");
		mv.addObject("errorDescription","Product you are looking for does not exist");
		mv.addObject("title","Product Unavailable");
		return mv;
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception ex) {
		ModelAndView mv = new ModelAndView("error");
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ex.printStackTrace(pw);
		
		mv.addObject("errorTitle","Contact your administrator");
		mv.addObject("errorDescription",sw.toString());
		mv.addObject("title","Error");
		return mv;
	}
}
