import java.net.UnknownHostException;
import com.mongodb.*;
import java.util.*;

public class MongoTest {
  public static void main(String[] args) {
    try {
      Mongo mongo = new Mongo("localhost");
    
      DB db = mongo.getDB( "mydb" );
			DBCollection collection = db.getCollection("people");
			
			String[] s = {
                    "name", "Ted",
                    "address", "123 foo st"
                   };
			
      BasicDBObject doc = BetterDBObject.newFromArray( s );
			collection.insert(doc);
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

  class BetterDBObject extends BasicDBObject {
    public static BasicDBObject newFromArray(String[] pairs) {
      BasicDBObject doc = new BasicDBObject();
      
      // TODO: throw exception if pairs.length is not even
      for (int i=0; i < pairs.length; i = i + 2) {
        doc.put(pairs[i], pairs[i+1]);
      }
      
      return doc;
    }
  }

