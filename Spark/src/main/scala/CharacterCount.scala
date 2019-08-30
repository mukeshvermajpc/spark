import org.apache.spark.{SparkConf, SparkContext}

object CharacterCount{
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("Character count").setMaster("local")
    val sc=new SparkContext(conf)
    val data=sc.textFile("file:///home/mukesh/Desktop/data/employee.txt")
    val flatmaprdd=data.flatMap(x=>x.split(""))
    println("******** flat map *******")
    flatmaprdd.collect().foreach(println)
    val map=flatmaprdd.map(x=>(x,1))
    println("*** map method ******")
    map.collect().foreach(println)
    println("*********reduceByKey() method *********")
    val reduce=map.reduceByKey(_+_)
    reduce.collect().foreach(println)

  }
}