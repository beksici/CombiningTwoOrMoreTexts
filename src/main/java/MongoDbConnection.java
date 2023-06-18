import java.util.ArrayList;

import org.apache.catalina.connector.ClientAbortException;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbConnection {

	/*public static void main(String[] args) {
try {
	

		MongoClient mongoClient = MongoClients.create("mongodb+srv://lifeisaloom114:114115@cluster0.vgl85lh.mongodb.net/?retryWrites=true&w=majority");

	MongoDatabase db = mongoClient.getDatabase("WordsApp");
	System.out.println("Baglantı yapildi");
	MongoCollection<Document> collection= db.getCollection("WordsApp");
	Document d1 = new Document();
	d1.append("1", "ali");
	d1.append("2", "veli");
	collection.insertOne(d1);
} 
catch (Error e) {
	// TODO: handle exception
}
	
	}*/
	
	public void insertData(ArrayList<String> allInputs , String output,long time ) {
		MongoClient mongoClient = MongoClients.create("mongodb+srv://lifeisaloom114:114115@cluster0.vgl85lh.mongodb.net/?retryWrites=true&w=majority");

		MongoDatabase db = mongoClient.getDatabase("WordsApp");
		System.out.println("Baglantı yapildi");
		MongoCollection<Document> collection= db.getCollection("WordsApp");
		Document d1 = new Document();
		for(int i=0;i<allInputs.size();i++) {
			d1.append(String.valueOf(i+1),allInputs.get(i));
		}
		d1.append("output", output);
		d1.append("time", time);
		collection.insertOne(d1);
	}

}
