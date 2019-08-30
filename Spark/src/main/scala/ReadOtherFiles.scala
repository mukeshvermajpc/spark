import org.apache.spark.sql.SparkSession
//read json orc and parquet file
object ReadOtherFiles {
  def main(args: Array[String]): Unit = {
    val spark=SparkSession.builder().appName("Read other file in spark").master("local").getOrCreate()
    val jsonDF=spark.read.json("file:///home/mukesh/Desktop/data/employee.json")
    jsonDF.printSchema()
    jsonDF.show()
    /*
    val orcDF=spark.read.orc("///home/mukesh/Desktop/data/userdata")
    orcDF.show()*/
  }
}
