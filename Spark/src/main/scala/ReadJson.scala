import org.apache.spark.sql.{SQLContext, SparkSession}

object ReadJson {
  def main(args: Array[String]): Unit = {
    println("it is program for reading and performing action in json")
    val session=SparkSession.builder().appName("reading json file in spark").master("local").getOrCreate()
    val employeeDF=session.read.json("file:///home/mukesh/Desktop/data/employee.json")
    println("showing all data of json file")
    employeeDF.show()
    println("printing schema of json file")
    employeeDF.printSchema()


  }
}
