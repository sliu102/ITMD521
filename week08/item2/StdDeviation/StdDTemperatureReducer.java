import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StdDTemperatureReducer
  extends Reducer<Text, IntWritable, Text, DoubleWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    
    ArrayList<Integer> list = new ArrayList<Integer>();
    
    for (IntWritable value : values) {
      list.add(value.get());
    }

    Collections.sort(list);
    int size = list.size();
    int sum = 0;

    for (int i=0; i<size; i++) {
      sum += list.get(i);
    }

    int mean = sum/size;
    double sum2 =0.0;

    for (int i=0; i<size; i++) {
      sum2 += (double) Math.pow((list.get(i)-mean),2);
    }

    double variance = sum2/size;
    double stdD = (double) Math.sqrt(variance);


    context.write(key, new DoubleWritable(stdD));
  }
}
// ^^ MaxMinTemperatureReducer

~            