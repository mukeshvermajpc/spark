import java.util.Properties

import org.apache.spark.sql.SparkSession

object DatabaseObject {
  def main(args: Array[String]): Unit = {
    println("Reading data from MYSQL database")
    val spark=SparkSession.builder().appName("Reading data from MYSQL database").master("local").getOrCreate()
    val url="jdbc:mysql://mukesh-HP-Pavilion-15-Notebook-PC:3306"
    val table="employees.employee"
     val properties=new Properties()
    properties.put("user","root@localhost")
    properties.put("password","12345")
    Class.forName("com.mysql.jdbc.Driver")
    val mySqlDf=spark.read.jdbc(url,table,properties)
    println("Records of employee table is")
    mySqlDf.show()



  }
}
