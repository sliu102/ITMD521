import java.io.IOException;
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

    int count = 0;
    int sum = 0;

    for (IntWritable value : values) {
      count++;
      sum += value.get();
    }

    int mean = sum/count;
    int variance = 0;
    int sum2 =0;

    for (IntWritable value : values){
      sum2 += (int) Math.pow((value.get()-mean),2);
    }
    variance = sum2/count;
    double stdD = (double) Math.sqrt(variance);


    context.write(key, new DoubleWritable(stdD));
  }
}
// ^^ MaxMinTemperatureReducer

~            