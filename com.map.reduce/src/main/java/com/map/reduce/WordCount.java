package com.map.reduce;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
public class WordCount {
	public static class Mapper extends MapReduceBase implements org.apache.hadoop.mapred.Mapper<LongWritable, Text,Text, IntWritable>
	{
		static final IntWritable one=new IntWritable(1);
		Text word=new Text();
		@Override
		public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
				throws IOException {
			String line=value.toString();
			StringTokenizer token=new StringTokenizer(line);
			while(token.hasMoreElements())
			{
				word.set(token.nextToken());
				output.collect(word, one);
			}
			
		}
		
	}
	public static class Reducer extends MapReduceBase implements org.apache.hadoop.mapred.Reducer<Text,IntWritable, Text,IntWritable>
	{

		@Override
		public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
				Reporter reporter) throws IOException {
			int sum=0;
			while(values.hasNext())
			{
				sum+=values.next().get();
			}
			output.collect(key, new IntWritable(sum));
		}
		
	}
    public static void main(String args[])
    {
    	System.out.println("Word count problem in hadoop map reduce");
    	JobConf conf=new JobConf(WordCount.class);
    	conf.setJobName("Word count program");
    	conf.setOutputKeyClass(Text.class);
    	conf.setOutputValueClass(IntWritable.class);
    	conf.setMapperClass(Mapper.class);
    	conf.setCombinerClass(Reducer.class);
    	conf.setInputFormat(TextInputFormat.class);
    	conf.setOutputFormat(TextOutputFormat.class);
    	FileInputFormat.setInputPaths(conf,new Path("file:///home/mukesh/Desktop/wordcount"));
    	FileOutputFormat.setOutputPath(conf,new Path("file:///home/mukesh/Desktop/wordcount1"));
    	try {
			JobClient.runJob(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
