import org.apache.spark.{SparkConf, SparkContext}
object RDDExampleTwo{
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local").setAppName("Spark Application Two")
    val sc=new SparkContext(conf)
    println("Spark map function")
    val data=sc.parallelize(List(10,20,30,30,40,40))
    val data2=sc.parallelize(List(20,30,40))
    val map=data.map(x=>x+10)
    map.collect().foreach(println)
    val filter=data.filter(x=>x!=30)
    println("Filter in spark")
    filter.collect().foreach(println)
    println("Spark count")
    val ct=data.count()
    println("Number of element in list is: "+ct)
    val distinct=data.distinct()
    println("Distinct element in list")
    distinct.foreach(println)
    println("****union rdd*****")
    val union=data.union(data2)
    union.collect().foreach(println)
    val intersection=data.intersection(data2)
    intersection.collect().foreach(println)
    val cartisian=data.cartesian(data2)
    println("*** Cartisian in spark****")
    cartisian.collect().foreach(println)
    val data3=sc.parallelize(Seq(("C",3),("A",1),("D",4),("A",2),("B",3),("B",2),("E",5)))
    val sort=data3.sortByKey()
    println("sort by key")
    sort.collect().foreach(println)
    val groupfun=data3.groupByKey()
    println("*****Group by key ************")
    groupfun.collect().foreach(println)
    println("**** Reduced by key ****")
    val reduce=data3.reduceByKey(_+_)
    reduce.collect().foreach(println)
    println("first function")
    print(data.first())
    println("take function")
    data.take(3).foreach(println)
  }
}