package com.twitter.webservice.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommonServlet extends HttpServlet{
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Controller cnt = new Controller();
		PrintWriter out= null;
		String xmlString="";
		try {
					
			  HttpSession sess = req.getSession(true);
		      String userName = req.getParameter("userOne");
		      String noOfPosts = req.getParameter("noPosts");
		      
		      String firstUser = req.getParameter("firstUser");
		      String secondUser = req.getParameter("secondUser");
		      
		      String feature = req.getParameter("feature");
		      
		      String pageNo = req.getParameter("pageNo");
		      
		      List<String> list = new ArrayList<String>();
		      List<List<String>> newList = new ArrayList<List<String>>();
		      
		      if(feature!=null  && "feat1".equals(feature)){
		    	  
		    	  list = cnt.feature1Process(userName, noOfPosts);
		    	  req.setAttribute("tweetList", list);
		    	  req.setAttribute("pageNo", pageNo);
		          newList = cnt.getPages(list,10);
		    	  sess.setAttribute("newList", newList);
		    	  list = newList.get(pageNo!=null?Integer.parseInt(pageNo):0);
		    	  
		      }else if(feature!=null && "feat2".equals(feature)){
		    	  
		    	  list = cnt.feature2Process(firstUser,secondUser);
		    	  req.setAttribute("tweetList", list);
		    	  req.setAttribute("pageNo", pageNo);
		          newList = cnt.getPages(list,10);
		    	  sess.setAttribute("newList", newList);
		    	  list = newList.get(pageNo!=null?Integer.parseInt(pageNo):0);
		    	  
		      }else if(feature!=null && "pagination".equals(feature)){
		    	  
		    	  newList = (List<List<String>>)sess.getAttribute("newList");
		    	  list = newList.get(pageNo!=null?Integer.parseInt(pageNo):0);
		      }
		      	
				out= res.getWriter();
				res.setContentType("text/xml");	
			
				xmlString = xmlString + pageNo +"~";
				xmlString = xmlString + newList.size() +"~";
				
				for(String s : list){
					xmlString = xmlString + s+"~";
				}
				System.out.println(xmlString);
				out.write(xmlString);
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				out.close();
				out=null;
			}
	}
	
	
}
