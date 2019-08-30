package com.map.reduce;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable,Text, Text, IntWritable> {
    private final static IntWritable one=new IntWritable(1);
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		String line=value.toString();
		String []singleCountryData=line.split(",");
		output.collect(new Text(singleCountryData[7]),one);
	}
}
