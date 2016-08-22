# Twitter API Application (Twitter API implementatio)
UpdatedVersionWithClient(UI)

###Description
This project is a client which is uses Twitter API and fetches desired results <br>
The purpose of the project is to learn and implement the Twitter API. <br>
The main focus in this project is on the complete front end to back-end implementation using RESTful webservices.<br>
This app has been developed using Java, JSP, Servlets, Javascript, Twitter API and RESTful Webservices.<br>

####The following are the features of this application.<br>
1.	Accepts a twitter handle and return the last X posts by that person.<br>
This supports pagination as well.<br>
2.	We enter two twitter handles and return the list of their shared friends.<br>
This feature supports pagination.<br>
3.  A basic HTML webpage that lets me interact with the client above

## CheckList before running the application.
### Compilation Instructions
1. Eclipse IDE (I have used Luna).
2. Apache Tomcat 7.0.37

##Steps to run the app:
1.	$git clone https://github.com/APIEngineerTest/TwitteAPIAppl<br>
2.	$cd TwitterAPIAppl<br>
3.	Import the project using eclipse.<br>     
    Reference: http://help.eclipse.org/kepler/index.jsp?topic=%2Forg.eclipse.platform.doc.user%2Ftasks%2Ftasks-importproject.htm<br>
4.  Add the following jars to your application using build path.<br>
    Reference: http://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse<br>
    Make sure your project in eclipse is a Dynamic Web Application.<br>
    Reference: http://stackoverflow.com/questions/20563894/importing-existing-java-dynamic-web-project-into-eclipse<br>
    Make sure you download the jars and them to the build path of your application (dependencies, to resolve the compilation errors).
    Reference : http://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse<br>
    a.	commons-io-1.3.2.jar<br>
    b.  commons-cli-1.0.jar<br>
    c.  commons-httpclient-2.0.2.jar<br>
    d.  commons-codec-1.3.jar<br>
    e.  commons-logging-1.1.1.jar<br>
    f.  jstl-1.2.jar<br>
    g.  servlet-api.jar<br>
    h.	Httpcore-4.0-beta3.jar<br>
    i.	Json-20160212.jar<br>
    j.	Oauth-consumer.jar<br>
    k.	Signpost-commonshttp4-1.2.jar<br>
    l.	Signpost-core-1.2.jar<br>
    m.	Httpclient-4.0.1.jar<br>
    Since the project is Dynamic Web Project, We need to make sure we all the jars in WebApp Libraries as well. (Please check How to set up a Web App Library...
    section in the below reference link)<br>
    Reference: http://www.javahotchocolate.com/tutorials/web-jars.html <br>
5.	Once the jar files have been added, please replace the AccessToken, AccessSecret, ConsumerKey, ConsumerSecret values with your generated API keys.<br>
6.  All the configuration of the servlet is specified in web.xml file of the WEB-INF folder.
7.  Download Apache Tomcat and add it to the application. The server is required to run the application.
    Reference: http://help.eclipse.org/kepler/index.jsp?topic=%2Forg.eclipse.jst.ws.axis.ui.doc.user%2Ftopics%2Fttomcatserv.html
8.	Once the required changes have been done, start the server.
9.  The application can be accessed at the following url.<br>
    http://localhost:8000/APIEngineerTestAppl/
    
    
##Overview of the project Structure
This application follows View-controller Model, where View is the presentation layer (Client) developed using JSP and Servlet is the controller which handles the request from the client and communicates with other class to access the Business Logic.<br>
JSP - TwitterClient.jsp <br>
Servlet  - CommonServlet.java<br>
Business Logic - Controlelr.java<br>
web.xml - Servlet configuration<br>
TwitterAPI - the data from the Restful twitter API is used to implement the project.<br>

###Instructions to check the functionality:

#### Feature 1:

1. Once the server is up and running, the application can be accessed at http://localhost:8000/APIEngineerTestAppl/ url.<br>
2. The application looks as below. It has 2 features<br>
   a. Get any number of  posts for a specified user providing Screen Name of the user.<br>
![featureselection](https://cloud.githubusercontent.com/assets/18272509/17841590/f68ce8f2-67ce-11e6-8ceb-c30f23142184.png)
   b. Proceed with Selecting the first feature.<br>
![feature1selected](https://cloud.githubusercontent.com/assets/18272509/17841592/f8e13a90-67ce-11e6-8588-0bef5f50a80b.png)
   c. Once the first feature is selected, we fill the Screen Name of the user and number of latest posts we wish to see.<br>
![entervaluesforfeature1](https://cloud.githubusercontent.com/assets/18272509/17841593/fc34bfc8-67ce-11e6-8b33-326c5c8e1b4b.png)
   d. Once the values have been provided we click on submit. The results (Tweets) will be displayed as shown below.<br>
![resultonclickingsubmit](https://cloud.githubusercontent.com/assets/18272509/17841595/ffcee64a-67ce-11e6-8718-11f2d5a7312e.png)
   e. As my application supports pagination, I have implemented back and next buttons to view the remaining results. This application displays 10 tweets/ results at a time.<br>
![secondpageresultonnextclick](https://cloud.githubusercontent.com/assets/18272509/17841596/017db3c2-67cf-11e6-9f28-662a8bd63e92.png)

#### Feature 2:
1. Please select the second feature in the application i.e. "Get shared users of any two Users" <br>
![feature2selection](https://cloud.githubusercontent.com/assets/18272509/17841707/3769a9ae-67d0-11e6-850e-411495c524fb.png)
2. Specify the First and second Users in the specified text boxes.<br>
![feature2values](https://cloud.githubusercontent.com/assets/18272509/17841747/b87fceb0-67d0-11e6-8289-04143ba8214e.png)
3. Click on Submit button to view the shared friends between the users. Each set has 10 values.<br>
![feature2result](https://cloud.githubusercontent.com/assets/18272509/17841790/3f2fdf36-67d1-11e6-96eb-9be6ce69aedf.png)
4. Since my application supports pagination, the next set of values (10) can be viewed by clicking on next button.<br>
![feature nextsetofvalues](https://cloud.githubusercontent.com/assets/18272509/17841831/8acfb056-67d1-11e6-846c-91d26838a6ca.png)
5. We can keep clicking on the next button until we reach the last set of values, where the next button is disabled.<br>
![feature2lastset](https://cloud.githubusercontent.com/assets/18272509/17841861/ed881e22-67d1-11e6-9595-f2f1b271ee80.png)
7. We can traverse in the backward direction as well by clicking on back button.<br>
![feature2backbutton](https://cloud.githubusercontent.com/assets/18272509/17841893/331ad8ee-67d2-11e6-9524-9a8b3475750f.png)
8. As we reach the first page using the Back button, we can see the back button to be disabled. <br>
![feature2firstpagebackbutton](https://cloud.githubusercontent.com/assets/18272509/17841939/9905303c-67d2-11e6-8757-ab8425c05e47.png)

In this way all the features of the requirements have been met including pagination.<br>

### Assignment
The assignment has been submitted in the same repository, a file named API Engineer Test under Assignment folder. <br>

