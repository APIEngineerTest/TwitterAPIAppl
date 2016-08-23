package com.twitter.webservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class Controller {
	
	static String AccessToken = "766783816891453440-dhc5L6Y7HjQ48Gez2uquiyAldNLxmdD";
	  static String AccessSecret = "Gqc4yxc8eZcQVmxbKiK5S8pMBFNGOKk73OhgD0i03dACT";
	  static String ConsumerKey = "klzF4qfaNqYBp73SLQyIKH6BR";
	  static String ConsumerSecret = "0H4fo2qf4HC3VWATEE0e6XXzAefSGk7AmsvNsKDzya9Mwjuif9";
	  
	  
	  static int test_back=0;

	  /**
		 * @param args
		 */
	 
	public List feature1Process(String userName, String noOfPosts) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		 
		int length = 0;
		ArrayList<String> list = new ArrayList<>();
		JSONObject obj;
		String result = null;
		InputStream instream =null;
		JSONArray myObject =null;
		
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ConsumerKey,ConsumerSecret);
		consumer.setTokenWithSecret(AccessToken, AccessSecret);

		HttpGet request = new HttpGet(
						"https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="
						+ userName
						+ "&count="
						+ noOfPosts
						+ "&trim_user=true&exclude_replies=true&contributor_details=false&include_rts=false");
		consumer.sign(request);

		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		

		if (entity != null) {

			instream = entity.getContent();
			result = convertStreamToString(instream);
			System.out.println(result);

			myObject = new JSONArray(result);
			length = myObject.length();
			System.out.println(myObject.length());

			

			for (int i = 0; i < length; i++) {
				obj = (JSONObject) myObject.get(i);
				list.add((String) obj.get("text"));
			}

			System.out.println(myObject.toString());
			instream.close();
		}
		return list;
		        	
	}
	
	public List<String> feature2Process(String firstUser, String secondUser) throws Exception {
		
		Set<String> set1 = getFollowersList(firstUser);
		Set<String> set2 = getFollowersList(secondUser);
		List<String> newSet = new ArrayList<String>();
		
		for (String string : set2) {
			if(!set1.add(string)){
				newSet.add(string);
			}
		}
		
		return newSet;
	}
	
	private Set<String> getFollowersList(String screen_name) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		
		String next_cursor = "-1";
        String cursor ="-1";
        String result = null;
        Set<String> list =new HashSet<String>();
        JSONObject myObject = null;
        InputStream instream =null;
        JSONArray jsonArray =null;
        
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer( ConsumerKey, ConsumerSecret);
        consumer.setTokenWithSecret(AccessToken, AccessSecret);
        
        
        do{
        
        	HttpGet request = new HttpGet("https://api.twitter.com/1.1/followers/ids.json?screen_name="+screen_name+"&skip_status=true&include_user_entities=false&count=200&cursor="+cursor);
        	consumer.sign(request);
 
        	HttpClient client = new DefaultHttpClient();
        	HttpResponse response = client.execute(request);
        	HttpEntity entity = response.getEntity();
        	
        	if (entity != null) {
        		instream = entity.getContent();
        		result = convertStreamToString(instream);
        		myObject = new JSONObject(result);
        		System.out.println("RESPONSE: " + result);
        		
        			if(myObject.get("next_cursor")!=null && myObject.get("previous_cursor")!=null){
            			next_cursor = (String) myObject.get("next_cursor").toString();
            			jsonArray = (JSONArray) myObject.get("ids");
            			String obj ;
            			for(int i=0;i<jsonArray.length();i++){
            				obj = (String) jsonArray.get(i).toString();
            				list.add(obj);
            			}
            		}
        		
        		try {
					instream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	cursor = next_cursor;
        	
        }while(!"0".equals(next_cursor));
        return list;
	}
	
	private String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
	
	
	public <String> List<List<String>> getPages(Collection<String> c, Integer pageSize) {
	    if (c == null)
	        return Collections.emptyList();
	    List<String> list = new ArrayList<String>(c);
	    if (pageSize == null || pageSize <= 0 || pageSize > list.size())
	        pageSize = list.size();
	    int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
	    List<List<String>> pages = new ArrayList<List<String>>(numPages);
	    for (int pageNum = 0; pageNum < numPages;)
	        pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
	    return pages;
	}
}
