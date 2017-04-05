// cc MaxMinTemperatureReducer Reducer for max and min temperature example
// vv MaxMinTemperatureReducer
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MalFormedReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {

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
    int count =0;
        int temp =0;
        int stationId = 0;

        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
              if (list.get(i)!=list.get(j)){
                temp = j-i-1;
                if(temp>count){
                  stationId = list.get(i);
                }

              }
            }
          }



    context.write(key, new IntWritable(stationId));
  }
}
// ^^ MaxMinTemperatureReducer
