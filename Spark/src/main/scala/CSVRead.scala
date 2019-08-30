import org.apache.spark.sql.SparkSession

object CSVRead {
  def main(args: Array[String]): Unit = {
     val sparkSession=SparkSession.builder().master("local").appName("Spark Application").getOrCreate()
    val rdd=sparkSession.sparkContext.parallelize(Array(1,2,23,43,24,54,90,21))
    println(rdd)
    println("Spark rdd")
    rdd.foreach(println)
    val fileRdd=sparkSession.sparkContext.textFile("file:///home/mukesh/Desktop/data/electronic.csv")
    print("Total number of record in csv"+fileRdd.count())
    println("Printing top 10 records only")
    fileRdd.take(10).foreach(println)
    val header=fileRdd.first()
    val csvWithOutHeader=fileRdd.filter(_!=header)
    val columnData=csvWithOutHeader.map( line=> {
     val colArray=line.split(",")
      Array(colArray(0),colArray(1),colArray(2),colArray(3)).mkString(",")
    })
    columnData.take(10).foreach(println)
    println("Saving file in a particular location")
    columnData.saveAsTextFile("file:///home/mukesh/Desktop/data/Output")
  }
}
