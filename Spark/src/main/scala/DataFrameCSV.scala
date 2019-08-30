import org.apache.spark.sql.SparkSession

object DataFrameCSV {
  def main(args: Array[String]): Unit = {
    println("Reading CSV file using spark")
    val spark=SparkSession.builder().appName("Reading CSV using session").master("local").getOrCreate()
    val properties=Map("header"->"true","inferSchema"->"true")
    val df=spark.read.option("header","true").option("inferSchema","true").csv("file:///home/mukesh/Desktop/data/electronic.csv")
    df.printSchema()
    df.show()
  }
}
