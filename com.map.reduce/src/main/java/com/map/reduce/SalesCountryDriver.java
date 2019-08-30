package com.map.reduce;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.io.Text;

public class SalesCountryDriver {
  public static void main(String args[])
  {
	  JobClient job=new JobClient();
	  JobConf conf=new JobConf(SalesCountryDriver.class);
	  conf.setJobName("Sales Country job");
	  conf.setOutputKeyClass(Text.class);
	  conf.setOutputValueClass(IntWritable.class);
	  conf.setMapperClass(SalesMapper.class);
	  conf.setReducerClass(SalesReducer.class);
	  conf.setInputFormat(TextInputFormat.class);
	  conf.setOutputFormat(TextOutputFormat.class);
	  FileInputFormat.setInputPaths(conf,new Path(args[0]));
	  FileOutputFormat.setOutputPath(conf,new Path(args[1]));
	  try {
		job.runJob(conf);
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
  }
}
