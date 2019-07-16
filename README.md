# spring-cloud-pub-sub
Spring cloud stream google pub sub


Spring cloud stream with elasticsearch Java 1.8 and Maven 3.6.1
1. Setup google pub sub account and download the key file :
https://cloud.google.com/pubsub/docs/quickstart-py-mac#create_service_account_credentials
Rename the file to service-account.json and place it in the project resource folder.

2. Setup and Run elasticDB : https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html

3. Clone the git and run the: 
1. user-publisher
2. user-subscriber
3. listing-publisher
4. listing-subscriber

using:= mvn spring-boot:run

As we run these modules the required topics and subscriptions will be dynamically created.

Create some data into the elasticDB : (This is a normal HTTP call, not using sub/pub Reply) 
POST http://localhost:8081/api/user { "userId": 1, "companyId": 632321, "email": "rodeo11@gmail.com", "companyName": "Intersect", "designation": "TA" }

To test the Google pub sub Reply pattern (Get data based on the "userId"): GET http://localhost:8083/api/user/{userId}
