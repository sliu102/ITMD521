// cc MostFrequent Application to find the most frequently url in the log dataset
// vv MostFrequent
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MostFrequent {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: MostFrequent <input path> <output path>");
      System.exit(-1);
    }

    Job job = new Job();
    job.setJarByClass(MostFrequent.class);
    job.setJobName("MostFrequent url");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setMapperClass(MostFrequentMapper.class);
    job.setReducerClass(MostFrequentReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
// ^^ MaxTemperature