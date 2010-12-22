import com.mongodb.*;

public class Customer extends BasicDBObject {

  public Customer() {}
  
  public Customer(String firstName, String lastName, Order[] orders ) {
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setOrders(orders);
  }
  
  /* Methods get() and put() are DBObject methods.  Technically they are public,
     but users of the Customer class should use the getters / setters we define here,
     so that business logic can be incorporated */

  public String getFirstName() {
    return (String) this.get("firstName");
  }
  
  public void setFirstName(String firstName) {
    this.put("firstName", firstName);
  }

  public String getLastName() {
    return (String) this.get("lastName");
  }
  
  public void setLastName(String lastName) {
    this.put("lastName", lastName);
  }

  public Order[] getOrders() {
    return (Order[]) this.get("orders");
  }
  
  public void setOrders(Order[] orders) {
    this.put("orders", orders);
  }
  
  public String toString() {
    return "firstName: " + this.getFirstName() + ", lastName: " + this.getLastName();
  }
}


