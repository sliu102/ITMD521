// cc MaxTemperatureMapper Mapper for maximum temperature example
// vv MaxTemperatureMapper
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MalFormedMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  private static final int MISSING = 9999;

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    String line = value.toString();
    int stationId=0;
    String year = line.substring(15, 19);
    if (line.charAt(87) != '+'&&line.charAt(87) != '-') { // parseInt doesn't like leading plus signs
      stationId =Integer.parseInt(line.substring(4, 10));
    } 
    String quality = line.substring(92, 93);
    context.write(new Text(year), new IntWritable(stationId));
  
  }
}
