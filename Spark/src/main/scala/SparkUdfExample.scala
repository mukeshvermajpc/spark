import org.apache.spark.sql.{SparkSession, functions}

object SparkUdfExample {
  def main(args: Array[String]): Unit = {
    println("Spark UDF function example")
    val session=SparkSession.builder().appName("Spark UDF function example").master("local").getOrCreate()
    val electronic=session.read.option("header","true").option("inferSchema","true").csv("file:///home/mukesh/Desktop/data/electronic.csv")
    val toLowerUdf=functions.udf[String,String](tolower)
    electronic.select(toLowerUdf(electronic("Subject"))).show()
  }
  def tolower(s:String):String=s.toLowerCase()
}
