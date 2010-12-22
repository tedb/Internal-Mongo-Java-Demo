import com.mongodb.*;

/* We don't have getters and setters here just for simplicity;
   use get() and put() */
public class Order extends BasicDBObject {
  public Order() {}

  public Order(String shipAddress, int[] itemFks) {
    this.put("shipAddress", shipAddress);
    this.put("itemFks", itemFks);
  }
}
