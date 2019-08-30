import org.apache.spark.{SparkConf, SparkContext}

object  First{
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("spark first application").setMaster("local")
    val sc=new SparkContext(conf)
    val data=sc.parallelize(Array(1,2,3,4,5))
    data.collect().foreach(println)
  }


}