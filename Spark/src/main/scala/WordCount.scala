import org.apache.spark.{SparkConf, SparkContext}

object WordCount{
  def main(args: Array[String]): Unit = {
    println("it is main method")
    val conf=new SparkConf().setAppName("Word count program in spark").setMaster("local")
    val sc=new SparkContext(conf)
    val data=sc.textFile("file:///home/mukesh/Desktop/data/employee.txt")
    data.collect().foreach(println)
    val splitdata=data.flatMap(line=>line.split(" "))
    println("data after flat map")
    splitdata.collect().foreach(println)
    val map=splitdata.map(x=>(x,1))
    println("map after split")
    map.collect().foreach(println)
    val reduce=map.reduceByKey(_+_)
    println("Reduce method")
    reduce.collect().foreach(println)
  }
}