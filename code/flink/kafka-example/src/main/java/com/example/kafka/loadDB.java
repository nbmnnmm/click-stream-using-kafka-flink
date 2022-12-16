import java.util.Properties;
import java.io.*;      
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
public class loadDB{
  
  String dbURL = "jdbc:oracle:nvl:@localhost:1521:flink-kafka";
  String username = "nvlong";
  String password = "123456789";
  Connection conn = DriverManager.getConnection(dbURL, username, password);
  byte[] ob = o.getBytes();
  String path = System.getenv("DIVOLTE_SCHEMA");

  File file = new File(path);
  Schema schema = new Schema.Parser().parse(file);
  Decoder decoder = DecoderFactory.get().binaryDecoder(ob,null);
  DatumReader reader = new SpecificDatumReader<>(schema,schema);
  String sql = "INSERT INTO Flink (eventType, time, payload,host ) VALUES (?, ?, ?, ?)";
 
PreparedStatement statement = conn.prepareStatement(sql);
statement.setString(1, reader.get('eventType'));
statement.setString(2, reader.get('time'));
statement.setString(3, reader.get('payload'));
statement.setString(4, reader.get('host'));
 int rowsInserted = statement.executeUpdate();
 
int rowsInserted = statement.executeUpdate();
if (rowsInserted > 0) {
    System.out.println("A new user was inserted successfully!");
}
  
}
