import jdk.nashorn.internal.objects.NativeFunction
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.udf
object SparkUdfExample2 {
  def main(args: Array[String]): Unit = {
    println("Spakr udf example two")
    val spark = SparkSession.builder().appName("Spark Udf example").master("local").getOrCreate()
    val stock = spark.read.option("header", "true").option("inferSchema", "true").csv("file:///home/mukesh/Desktop/data/nse-stocks-data.csv")
    stock.show()
    val averageUDF=udf[Double,Double,Double](average)
    spark.sqlContext.udf.register("average_udf",averageUDF)
    stock.createOrReplaceTempView("stock_table")
    spark.sql("select SYMBOL,HIGH,LOW,average_udf(HIGH,LOW) as avg_price from stock_table").show()
  }
  def average(num1:Double,num2:Double):Double=(num1+num2)/2.0




}









































