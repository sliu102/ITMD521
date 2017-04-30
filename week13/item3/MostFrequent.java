import java.io.IOException;

import com.cloudera.sqoop.lib.RecordParser.ParseError;


import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class MostFrequent extends Configured implements Tool {

  public static class MostFrequentMapper
      extends Mapper<LongWritable, Text, Text, Logs> {

    private Logs mostFrequent_url = null;
    private String dat=null;

    public void map(LongWritable k, Text v, Context context) 
           throws IOException, InterruptedException {
      Logs logs = new Logs();
      try {
        logs.parse(v); // Auto-generated: parse all fields from text.
      } catch (ParseError pe) {
        // Got a malformed record. Ignore it.
        return;
      }

      dat = logs.get_dat();
      String url = logs.get_url();
      String status = logs.get_status(); 

      if(mostFrequent_url == null
            ||null !=dat&&dat.length()==10){
        if (status.equals("200")&&!url.matches(".*index.$")) { 
            mostFrequent_url = logs;
            context.write(new Text(dat), mostFrequent_url);
        }
      }
    }

    public void cleanup(Context context)
        throws IOException, InterruptedException {
      if (null != mostFrequent_url) {
        context.write(new Text(dat), mostFrequent_url);
      }
    }
  }

  public static class MostFrequentReducer
      extends Reducer<Text, Logs, Text, Text> {

    // There will be a single reduce call with key '0' which gets
    // the max widget from each map task. Pick the max widget from
    // this list.
    public void reduce(Text k, Iterable<Logs> vals, Context context)
        throws IOException, InterruptedException {
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      String tempUrl = null;

      for (Logs l : vals) {
         tempUrl = l.get_url();
         if (map.containsKey(tempUrl)) {
            map.put(tempUrl,map.get(tempUrl)+1);
         } else{
            map.put(tempUrl,1);
         }
      }
      String mfUrl = null;
      int maxCount=(Collections.max(map.values()));  // This will return max value in the Hashmap
        for (Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxCount) {
                mfUrl = entry.getKey();     // Print the key with max value
            }
        }


      context.write(k, new Text(mfUrl));

    }
  }

  public int run(String [] args) throws Exception {
    Job job = new Job(getConf());

    job.setJarByClass(MostFrequent.class);

    job.setMapperClass(MostFrequentMapper.class);
    job.setReducerClass(MostFrequentReducer.class);

    FileInputFormat.addInputPath(job, new Path("logs"));
    FileOutputFormat.setOutputPath(job, new Path("mostFrequent"));

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Logs.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    job.setNumReduceTasks(1);

    if (!job.waitForCompletion(true)) {
      return 1; // error.
    }

    return 0;
  }

  public static void main(String [] args) throws Exception {
    int ret = ToolRunner.run(new MostFrequent(), args);
    System.exit(ret);
  }
}