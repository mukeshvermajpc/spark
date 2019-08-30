import org.apache.spark.{SparkConf, SparkContext}

object RDDExample{
  def main(args:Array[String]): Unit =
  {
    println("this is scala main method")
    val conf=new SparkConf().setAppName("Spark RDD example").setMaster("local")
    val sc=new SparkContext(conf)
    val data=sc.parallelize(Array(1,2,4,54,56,23,14,54,34,23,31,56,76,23,89,40))
    println("filter rdd")
    val filterrdd=data.filter(x=>x>30)
    filterrdd.collect().foreach(print)
    println("Map rdd")
    val map=data.map(x=>x+10)
    map.collect().foreach(print)


  }
}