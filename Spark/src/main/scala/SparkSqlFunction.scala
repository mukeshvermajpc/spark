import org.apache.spark.sql.{SparkSession, functions}

object SparkSqlFunction {
  def main(args: Array[String]): Unit = {
    val session=SparkSession.builder().appName("Spark Sql build in function").master("local").getOrCreate()
    val electronic=session.read.option("header","true").option("inferSchema","true")csv("file:///home/mukesh/Desktop/data/electronic.csv")
    electronic.show()
    electronic.select(functions.lower(electronic.col("Subject"))).show()
    electronic.select(functions.upper(electronic.col("Subject"))).show()
    println("Spark aggregate functions...............")
    electronic.select(functions.min(electronic.col("Data_Value"))).show()
    println("Spark max function")
    electronic.select(functions.max(electronic.col("Data_Value"))).show()
    electronic.select(functions.avg(electronic.col("Data_Value"))).show()
  }
}
