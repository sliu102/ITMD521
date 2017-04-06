Item 3
====================
### output of MinWidget job:
 ![image](https://github.com/sliu102/ITMD521/blob/master/week11/item3/output.jpeg)

###MinWidget.java

```java  
import java.io.IOException;
import com.cloudera.sqoop.lib.RecordParser.ParseError;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;

public class MinWidgetId extends Configured implements Tool {

  public static class MinWidgetMapper
      extends Mapper<LongWritable, Text, LongWritable, Widget> {

    private Widget minWidget = null;

    public void map(LongWritable k, Text v, Context context) {
      Widget widget = new Widget();
      try {
        widget.parse(v); // Auto-generated: parse all fields from text.
      } catch (ParseError pe) {
        // Got a malformed record. Ignore it.
        return;
      }

      Integer id = widget.get_id();
      if (null == id) {
        return;
      } else {
        if (minWidget == null
            || id.intValue() < minWidget.get_id().intValue()) {
          minWidget = widget;
        }
      }
    }

    public void cleanup(Context context)
        throws IOException, InterruptedException {
      if (null != minWidget) {
        context.write(new LongWritable(0), minWidget);
      }
    }
  }

  public static class MinWidgetReducer
      extends Reducer<LongWritable, Widget, Widget, NullWritable> {

    // There will be a single reduce call with key '0' which gets
    // the max widget from each map task. Pick the max widget from
    // this list.
    public void reduce(LongWritable k, Iterable<Widget> vals, Context context)
        throws IOException, InterruptedException {
      Widget minWidget = null;

      for (Widget w : vals) {
        if (minWidget == null
            || w.get_id().intValue() < minWidget.get_id().intValue()) {
          try {
            minWidget = (Widget) w.clone();
          } catch (CloneNotSupportedException cnse) {
            // Shouldn't happen; Sqoop-generated classes support clone().
            throw new IOException(cnse);
          }
          }
      }

      if (null != minWidget) {
        context.write(minWidget, NullWritable.get());
      }
    }
  }

  public int run(String [] args) throws Exception {
    Job job = new Job(getConf());

    job.setJarByClass(MinWidgetId.class);

    job.setMapperClass(MinWidgetMapper.class);
    job.setReducerClass(MinWidgetReducer.class);

    FileInputFormat.addInputPath(job, new Path("widgets"));
    FileOutputFormat.setOutputPath(job, new Path("minwidget"));

    job.setMapOutputKeyClass(LongWritable.class);
    job.setMapOutputValueClass(Widget.class);

    job.setOutputKeyClass(Widget.class);
    job.setOutputValueClass(NullWritable.class);

    job.setNumReduceTasks(1);

    if (!job.waitForCompletion(true)) {
      return 1; // error.
    }

    return 0;
  }

  public static void main(String [] args) throws Exception {
    int ret = ToolRunner.run(new MinWidgetId(), args);
    System.exit(ret);
  }
}
```