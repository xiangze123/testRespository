package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
//context.write(new Text(deptno), new IntWritable(Integer.parseInt(sal)));
public class MyReducer extends Reducer<Text, IntWritable, Text, Text>{

	@Override
	protected void reduce(Text deptno, Iterable<IntWritable> sals,Context context)
			throws IOException, InterruptedException {
		
		
		//计算每个部门的最高工资和最低工资 平均工资
		int maxsal = 0;
		int min = Integer.MAX_VALUE;
		float sum = 0;
		int count = 0;
		float avg = 0;
		for(IntWritable intWritable_sal: sals) {
			int sal = intWritable_sal.get();
			count ++;
			sum += sal;
			if(sal > maxsal) {
				maxsal = sal;
			}
			if(sal < min) {
				min = sal;
			}
		}
		avg = sum /count;
		context .write(deptno, new Text("最高工资:"+maxsal+"  最低工资："+min+"   平均工资："+avg));
		 
		
	}
    
}
