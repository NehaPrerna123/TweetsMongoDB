package co.sm.mongo;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import twitter4j.conf.ConfigurationBuilder;

public class ParseEntities {

	@SuppressWarnings("unchecked")
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
	
	        BasicDBObject query = new BasicDBObject();
	        query.put("tweet_mentioned_count", -1);
	        MongoIterable<Document> cursor = items.find().sort(query).limit(5);

	        if (((MongoCollection<Document>) cursor).count() > 0) {
	            while (((Iterator<DBObject>) cursor).hasNext()) {
	                System.out.println((((Iterator<DBObject>) cursor).next()));
	            }
	        } else {
	        	System.out.println("SORRY! CANNOT EXECUTE!");
	        }
	       
	    }
}
