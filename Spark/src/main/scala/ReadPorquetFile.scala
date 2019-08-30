import org.apache.spark.sql.SparkSession

object ReadPorquetFile {
  def main(args: Array[String]): Unit ={
    val session=SparkSession.builder().appName("Reading porquet file").master("local").getOrCreate()
    val rdd=session.read.parquet("file:///home/mukesh/Downloads/userdata.parquet")
    rdd.show()
    rdd.select("id","first_name","last_name","email","gender").show()
    println("Total number of record present in parquet file is:")
    rdd.count()
  }
}
