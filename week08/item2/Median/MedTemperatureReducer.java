// cc MaxMinTemperatureReducer Reducer for max and min temperature example
// vv MaxMinTemperatureReducer
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MedTemperatureReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    ArrayList<IntWritable> list = new ArrayList<IntWritable>();
    
    for (IntWritable value : values) {
      list.add(value.get());
    }

    Collections.sort(list);
    int size = list.size();
    int med;
    if(size%2==0) med = (list.get(size/2)+list.get(size/2 - 1))/2;
    else med = list.get((size+1)/2 - 1);

    context.write(key, new IntWritable(med));
  }
}
// ^^ MaxMinTemperatureReducer
