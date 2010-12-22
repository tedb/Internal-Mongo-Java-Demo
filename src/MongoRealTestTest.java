import org.junit.*;
import static org.junit.Assert.*;
import java.net.UnknownHostException;
import com.mongodb.*;

public class MongoRealTestTest {

  DBCollection customerCollection;

  @Before
  public void setUp() throws UnknownHostException {
    Mongo m = new Mongo();
    
    this.customerCollection = m.getDB("mydb").getCollection("customers");
    customerCollection.setObjectClass(Customer.class);
  }

  @Test
  public void insertCustomerSavesCustomerToDatabase() {
    Customer c = new Customer();
    customerCollection.insert(c);
    assertNotNull(c.get("_id"));
  }
  
  @Test
  public void insertedCustomerCanBeRetrievedByFirstName() {
    Customer c = new Customer();
    c.setFirstName("Xtest");
    customerCollection.insert(c);
    
    Customer result = (Customer) customerCollection.findOne(QueryBuilder.start("firstName").is("Xtest").get());
    assertNotNull(result.getFirstName());
  }
  
  @After
  public void tearDown() {
    customerCollection.drop();
  }
}

