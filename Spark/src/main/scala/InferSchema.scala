import org.apache.spark.sql.SparkSession
case class Employee(id: Int, name: String, age:Int)
object InferSchema {
  def main(args: Array[String]): Unit = {
    println("Inferring schema in spark")
    val spark=SparkSession.builder().appName("Inferring schema").master("local").getOrCreate()
    import spark.implicits._
    val textRDD=spark.sparkContext.textFile("file:///home/mukesh/Desktop/test.txt")
    textRDD.foreach(println)
    val arrayRDD=textRDD.map(_.split(","))
    val employeeRDD=arrayRDD.map(e=>Employee(e(0).trim.toInt,e(1).trim,e(2).trim.toInt))
    val employeeDF=employeeRDD.toDF()
    println(employeeDF)
    employeeDF.show()
    employeeDF.registerTempTable("Employee")
    val records=spark.sql("SELECT *from Employee")
    println("Displaying using select query")
    records.show()
  val ageFilter=spark.sql("SELECT * from Employee where age>=25 and age<=35")
    println("showing employee records based on age")
    ageFilter.show()
    println("showing employee detail based on index")
    ageFilter.map(emp=>"id:"+emp(0)+"Name: "+emp(1)).collect().foreach(println)
  }
}
