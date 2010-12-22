import java.net.UnknownHostException;
import com.mongodb.*;
import java.util.*;

public class MongoRealTest {
  public static void main(String[] args) {
    try {
      // Set up server connection pool
      Mongo mongo = new Mongo("localhost");
    
      // Set up database and collection handles
      DB db = mongo.getDB( "mydb" );
			DBCollection customerCollection = db.getCollection("customers");
      customerCollection.setObjectClass(Customer.class);
			
			// Clear any existing customers
      DBObject removeQuery = QueryBuilder.start().get();
			customerCollection.remove(removeQuery);
			
			// Build an array of 2 orders
			Order[] orders = {
			  new Order("123 foo st", new int[] { 1, 2, 3 }),
			  new Order("234 bar st", new int[] { 4, 5, 6 })
			};
      
      // Build 3 customers, which each contain the "orders" array
      Customer firstCustomer  = new Customer("Ted", "A", orders);
      Customer secondCustomer = new Customer("Ted", "B", orders);
      Customer thirdCustomer  = new Customer("John", "S", orders);
      
      // Insert the customer into the collection
			customerCollection.insert(firstCustomer);
			customerCollection.insert(secondCustomer);
			customerCollection.insert(thirdCustomer);

			System.out.println("Mongo object ID for new customer: " + firstCustomer.get("_id"));
			
      // Set up a query
      DBObject query = QueryBuilder.start("lastName").greaterThanEquals("B").and("firstName").is("Ted").get();

      // Tell the collection to cast any retrieved customers to the Customer class
      //customerCollection.setObjectClass(Customer.class);
			DBCursor someCustomers = customerCollection.find(query);
			
      // Print out the query results
			while (someCustomers.hasNext()) {
        Customer thisCustomer = (Customer) someCustomers.next();
			  System.out.println(thisCustomer.toString());
			}
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

