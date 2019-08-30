import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{IntegerType, LongType, StringType, StructField, StructType}

object ProgramSchema {
  def main(args: Array[String]): Unit = {
    println("Reading text file using spark context")
    val conf=new SparkConf().setAppName("Word count program in spark").setMaster("local")
    val sc=new SparkContext(conf)
    val sqlContext=new SQLContext(sc)
    val employee=sc.textFile("file:///home/mukesh/Desktop/test.txt")
    val schemaString="id name age"
    val schema=StructType(
      StructField("Id",StringType,true)::
      StructField("Name",StringType,true)::
      StructField("Age",StringType,true)::Nil)
    println(schema)
    val rowRDD=employee.map(_.split(",")).map(e=>Row(e(0),e(1),e(2)))
    println(rowRDD)
    val employeeDf=sqlContext.createDataFrame(rowRDD,schema)
    employeeDf.registerTempTable("employee")
    val records=sqlContext.sql("select *from employee")
    records.show()
  }
}
