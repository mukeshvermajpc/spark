package spark.streaming
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object DataFrameSS {
  def main(args: Array[String]): Unit = {
   val spark=SparkSession.builder().appName("Data Frame using spark session").master("local").getOrCreate()
    val rdd=spark.sparkContext.parallelize(Array("1","2","3","4","5","6"))
    val schema=StructType(
      StructField("Integer as String",StringType,true)::Nil
    )
    val rowRDD=rdd.map(element=>Row(element))
    val df=spark.createDataFrame(rowRDD,schema)
    df.printSchema()
    df.show()



  }
}
