import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object ReadJsonTwo {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("Reading Json file").setMaster("local")
    val sc=new SparkContext(conf)
    val sqContext=new SQLContext(sc)
    val dfs=sqContext.read.json("file:///home/mukesh/Desktop/data/employee.json")
    println(dfs)
    println("Displaying data of json")
    dfs.show()
    println("printing schema of json")
    dfs.select("name").show()
    dfs.filter(dfs("age")>23).show()
    dfs.groupBy("age").count().show()
  }
}
