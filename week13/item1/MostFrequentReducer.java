// cc MaxMinTemperatureReducer Reducer for max and min temperature example
// vv MaxMinTemperatureReducer
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class MaxMinTemperatureReducer
  extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    String tempUrl;
    
    for (Text value : values) {
      tempUrl = value.get();
      if (map.containsKey(tempUrl)) {
        map.put(tempUrl,map.get(tempUrl)+1);
      } else{
        map.put(tempUrl,1);
      }
    }

    int maxCount=(Collections.max(map.values()));  // This will return max value in the Hashmap
        for (Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxCount) {
                String mfUrl = entry.getKey();     // Print the key with max value
            }
        }


    context.write(key, new Text(mfUrl));
  }
}
// ^^ MaxMinTemperatureReducer
