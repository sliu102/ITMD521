// cc MaxMinTemperatureReducer Reducer for max and min temperature example
// vv MaxMinTemperatureReducer
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxMinTemperatureReducer
  extends Reducer<Text, IntWritable, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    
    int maxValue = Integer.MIN_VALUE;
    int minValue = Integer.MAX_VALUE;
    for (IntWritable value : values) {
      maxValue = Math.max(maxValue, value.get());
      minValue = Math.min(minValue, value.get());
    }

    String tempvalue = Integer.toString(maxValue) + "," + Integer.toString(minValue);

    context.write(key, new Text(tempValue));
  }
}
// ^^ MaxMinTemperatureReducer
