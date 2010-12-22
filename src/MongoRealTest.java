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
      
      cleanup(customerCollection);
      
      insert(customerCollection);
      
      query(customerCollection);
      query_deep(customerCollection);
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  public static void cleanup(DBCollection collection) {
    // Clear any existing customers
    collection.remove(QueryBuilder.start().get());
  }
  
  public static void insert(DBCollection collection) {
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
      collection.insert(firstCustomer);
      collection.insert(secondCustomer);
      collection.insert(thirdCustomer);

      System.out.println("Mongo object ID for new customer: " + firstCustomer.get("_id"));
  }
  
  public static void query(DBCollection collection) {
      // Set up a query
      DBObject query = QueryBuilder.start("lastName").greaterThanEquals("B").and("firstName").is("Ted").get();

      // Tell the collection to cast any retrieved customers to the Customer class
      //customerCollection.setObjectClass(Customer.class);
      DBCursor someCustomers = collection.find(query);
      
      // Print out the query results
      while (someCustomers.hasNext()) {
        Customer thisCustomer = (Customer) someCustomers.next();
        System.out.println(thisCustomer.toString());
      }
  }
  
  public static void query_deep(DBCollection collection) {
      // Set up a query
      DBObject query = QueryBuilder.start("lastName").greaterThanEquals("B").and("firstName").is("Ted").get();

      // Tell the collection to cast any retrieved customers to the Customer class
      //customerCollection.setObjectClass(Customer.class);
      DBCursor someCustomers = collection.find(query);
      
      // Print out the query results
      while (someCustomers.hasNext()) {
        Customer thisCustomer = (Customer) someCustomers.next();
        System.out.println(thisCustomer.toString());
      }
  }
}

