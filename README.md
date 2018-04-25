# TweetsMongoDB

COLLECTION OF TWEETS AND ANALYSIS----

In this project I have fetched 10,000 tweets and saved it in mongoDB and performed some data parsing.


Getting Started-------
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.


Prerequisites-------
Software required:
•	Jdk 1.8
•	Any IDE (I used Eclipse Oxygen)
•	Twitter 4j
•	MongoDB
•	MongoDB java drivers
•	Account in apps.twitter.com for getting keys and access tokens


Installing-----
1)	Firstly, download MongoDB database and install it on your computer and set path by logging in to command prompt as Administrator.
2)	Install latest version of jdk. Set its path in the environmental variable.
3)	Download any IDE you like, I used eclipse.
4)	Download mongoDB java drivers to connect to the MongoDB database.
5)	Download Twitter4j jar files to connect with the Twitter API.
6)	Create /sign in to apps.twitter.com to get keys and access token. After signing in go to keys and access token section and generate your keys which you will be going to feed into your java code.
7)	Open eclipse. Create a new java project. Into src create a package. Into which create your class and write codes.
8)	For accessing twitter and mongo db, add the jar files Twitter4j and MongoDB java drivers to the library. (right click on project->build path->configure build path->select libraries->add external jars->select your jars->ok)
9)	Now your IDE is setup for you to write code and run your programs to fetch and access data base.

Running the tests----
I have written 3 codes. One is for fetching tweets from twitter timeline and saved it to my MongoDB database. (Maximum 20 tweets can be fetched from the timeline)
Another code is for fetching 10,000 sample tweets from twitter and storing it to the MongoDB.
The third code is for fetching 5 frequently occurring entities from mongoDB database collection for the 10k tweets. 
