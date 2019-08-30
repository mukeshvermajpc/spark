import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf
object SparkCurryFunctionUDF {
  def main(args: Array[String]): Unit = {
    println("Spark curry function using UDF")
    val spark=SparkSession.builder().appName("Spark curry function using udf").master("local").getOrCreate()
    println("spark session "+spark)
    val data=spark.read.option("header","true").option("inferSchema","true").csv("file:///home/mukesh/Desktop/data/nse-stocks-data.csv")
    println("Showing top 100 rows")
    data.show(50)
    val diffData=data.withColumn("DIFFCLOSE",diffCalculation()(data("CLOSE"),data("PREVCLOSE")))
    diffData.show()
  }
  def diffCalculation():UserDefinedFunction=udf((close:Double, prevClose:Double)=>{
    BigDecimal(close - prevClose).setScale(4,BigDecimal.RoundingMode.HALF_UP).toDouble

  })
}
