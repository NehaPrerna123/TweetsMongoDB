package co.sm.mongo;

import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterData {
	
 
	
    public static void main(String[] args) throws TwitterException, UnknownHostException, MongoException {
    
    	
    		
    	ConfigurationBuilder cofigurationBuilder =new ConfigurationBuilder();
        cofigurationBuilder.setDebugEnabled(true)
        .setOAuthConsumerKey("*********************")
        .setOAuthConsumerSecret("********************")
        .setOAuthAccessToken("*********************")
        .setOAuthAccessTokenSecret("*************************");
       // .setJSONStoreEnabled(true);
    	
     /**** Connect to MongoDB ****/
    System.out.println("Connecting to MongoDB...");
    //@SuppressWarnings("resource")
	@SuppressWarnings("resource")
	MongoClient mongo= new MongoClient("localhost", 27017);


    /**** Get database ****/
    // if database doesn't exists, MongoDB will create it for you
    MongoDatabase db = mongo.getDatabase("mytwitterdb");

    /**** Get collection / table from 'sample' ****/
    // if collection doesn't exists, MongoDB will create it for you
    //@SuppressWarnings("rawtypes")
	//MongoCollection<Document> items = db.getCollection("twittercoll");
    MongoCollection<Document> items= db.getCollection("twittercoll"); 

    /**** Getting Data From Twitter time line ****/
    TwitterFactory tf = new TwitterFactory(cofigurationBuilder.build());
    twitter4j.Twitter twitter=tf.getInstance();
    ResponseList<Status> status=twitter.getHomeTimeline();
    
    for(Status s: status) {
    	//String json= TwitterObjectFactory.getRawJSON(status);
    	//@SuppressWarnings("deprecation")
		//DBObject dbObject=(DBObject)JSON.parse(json);
    	
        BasicDBObject dbObject = new BasicDBObject();  
        dbObject.put("user_name", s.getUser().getScreenName());
        dbObject.put("tweet_ID", s.getId());  
        dbObject.put("tweet_text", s.getText()); 
    	Document doc=new Document(dbObject);
    	items.insertOne(doc);
    	System.out.println("USERNAME:  "+s.getUser().getName()+"   "+"TEXT: "+s.getText());
    }
      }

}
    

