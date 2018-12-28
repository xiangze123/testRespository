package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text line,Context context)
			throws IOException, InterruptedException {
		String[] splited = line.toString().split("\\s+");
		String deptno = splited[3];
		String str_sal =  splited[2];
		
		int sal = Integer.parseInt(str_sal);
		context.write(new Text(deptno), new IntWritable(Integer.parseInt(str_sal)));
	}
   
}