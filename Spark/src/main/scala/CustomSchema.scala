import org.apache.spark.sql.SparkSession

object CustomSchema {
  def main(args: Array[String]): Unit = {
    println("creating custom schema in spark")
    val spark=SparkSession.builder().appName("Custom Schema").master("local").getOrCreate()
    val csvRDD=spark.read.option("header","true").option("inferSchema","true").csv("file:///home/mukesh/Desktop/data/electronic.csv")
    csvRDD.show()
  }
}
