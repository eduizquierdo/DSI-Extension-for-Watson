# DSI-Extension-for-Watson
this is a Data Provider Extension of an IBM ODM DSI solution project to call Watson Cognitive Services.
Here are the instructions to create and configure such an extension.

NOTE: A sample solution is provided. This solution features an extension that uses Watson Sentiment Analyzer
	  through Watson Alchemy client API for Java. 

NOTE: You will need a Watson Alchemy API service deployed in bluemix. This service gives you an api key. 
      This Alchemy service is used to query Watson Sentimient Analyzer. Note that other Watson services may require
	  user/password. The watson sevice in Bluemix, will give you all those details.

NOTE: The API key is defined in the solution properties. 
      The key of the sample solution correspond to a service deployed in my Bluemix account 
	  You may want to deploy your own Alchemy API service in Bluemix.
	  
NOTE: A DSI extension for Watson requires adding in the DSI extension project the client libraries of Watson.
      For this reason is not recommended to have other extensions non related to Watson in the same DSI extension project.


1. create a DSI extension project of your solution anad add to it a Data Provider Extension that, alt least, accept
   a text and provides a Sentiment and a score (numeric).

2. in your extension project add a new folder "lib" and import the latest Watson client library:
   java-sdk-3.3.1-jar-with-dependencies.jar

3. add lib/java-sdk-3.3.1-jar-with-dependencies.jar to the Java build path of your extension project.

4. edit META-INF/MANIFEST.MF

4.1. In "Dependencies" tab add packages javax.naming, javax.net, javax.net.ssl
4.2. In "Runtime" tab att to the classpath (in this order): 
		lib/java-sdk-2.9.0-jar-with-dependencies.jar,
 		.
4.3. In "Build" tab check the lib/java-sdk-3.3.1-jar-with-dependencies.jar

5. You're ready to code you Data Provider code to call Watson. The sample solution shows a sample using Alchemy API.


e d u
=====

