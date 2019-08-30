import org.apache.spark.sql.SparkSession

object MultipleSparkSession {
  def main(args: Array[String]): Unit = {
    println("Multiple spark session in spark")
    val session1=SparkSession.builder().appName("Spark application").master("local").getOrCreate()
    val session2=SparkSession.builder().appName("Spark application2").master("local").getOrCreate()
    val data1=session1.sparkContext.parallelize(Array(1,2,3,4,5,6))
    val data2=session2.sparkContext.parallelize(Array(6,7,8,9,10,11,12))
    data1.collect().foreach(println)
    println("Data of 2 session")
    data2.collect().foreach(println)
  }
}
