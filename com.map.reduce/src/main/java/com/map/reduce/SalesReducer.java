package com.map.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class SalesReducer extends MapReduceBase implements Reducer<Text,IntWritable, Text,IntWritable> {
	@Override
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
			Reporter reporter) throws IOException {
		Text key1=key;
		int frequency=0;
		while(values.hasNext())
		{
			IntWritable value=values.next();
			frequency+=value.get();
		}
		output.collect(key1, new IntWritable(frequency));
	}
}
