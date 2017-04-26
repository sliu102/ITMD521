// cc MostFrequentMapper Mapper for most frequently url example
// vv MostFrequentMapper
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MostFrequentMapper
  extends Mapper<LongWritable, Text, Text, Text> {

  private static final int MISSING = 9999;

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    String line = value.toString();
    String[] parts = string.split(" ");
    String month = parts[0].substring(5, 7);
    String day = parts[0].substring(8,10);
    String url;
    if (parts[10].equals('200')&&!parts[4].matches(".*index.$")) { 
      url = parts[4];
    } 

    context.write(new Text(month), new Text(url));
    
  }
}
