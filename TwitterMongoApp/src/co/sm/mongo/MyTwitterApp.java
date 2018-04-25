package co.sm.mongo;

import java.util.ArrayList;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MyTwitterApp {

	
public static void main(String[] args) {
	
	  ConfigurationBuilder cb = new ConfigurationBuilder();
	  cb.setOAuthConsumerKey("3QcoEX2pzZTGmSzzspk2adeW3");
	  cb.setOAuthConsumerSecret("7sGEzWJsVOtfi3RtJJO1CuwnGHZx6Q5lp4U9Uma5tEtyBgxDI0");
	  cb.setOAuthAccessToken("987043937297690624-4NzSwtrIgV5x6emXlio86UdB7ivW4L1");
	  cb.setOAuthAccessTokenSecret("XDo98GNMJSKDLVeKtIxLjD7fc9hBtltyo6WkFuXD6b9wi");
	  
	     /**** Connect to MongoDB ****/
	    System.out.println("Connecting to MongoDB...");
		@SuppressWarnings("resource")
		MongoClient mongo= new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("mytweetdb");
	    MongoCollection<Document> items= db.getCollection("tweetcoll"); 

	    TwitterFactory tf = new TwitterFactory(cb.build());
	    twitter4j.Twitter twitter=tf.getInstance();
	    
	  
	  Query query = new Query("java");
	  int numberOfTweets = 10000;
	  long lastID = Long.MAX_VALUE;
	  ArrayList<Status> tweets = new ArrayList<Status>();
	  while (tweets.size () < numberOfTweets) {
	    if (numberOfTweets - tweets.size() > 100)
	      query.setCount(100);
	    else 
	      query.setCount(numberOfTweets - tweets.size());
	    try {
	      twitter4j.QueryResult result;
	      result=twitter.search(query);
	      tweets.addAll(((twitter4j.QueryResult) result).getTweets());
	      System.out.println("Gathered " + tweets.size() + " tweets");
	      for (Status t: tweets) 
	        if(t.getId() < lastID) lastID = t.getId();

	    }

	    catch (TwitterException te) {
	      System.out.println("Couldn't connect: " + te);
	    }; 
	    query.setMaxId(lastID-1);
	  }

	  for (int i = 0; i < tweets.size(); i++) {
	    Status t = (Status) tweets.get(i);


        BasicDBObject dbObject = new BasicDBObject();  
        dbObject.put("user_name", t.getUser().getScreenName());
        dbObject.put("tweet_ID", t.getId());  
        dbObject.put("tweet_text", t.getText()); 
    	Document doc=new Document(dbObject);
    	items.insertOne(doc);
	    
	      System.out.println(i + " USER : " + t.getUser().getName() + " TWEET : " +t.getText());
	   
	  }
	

	}

}



