import org.apache.spark.sql.SparkSession

object CatalogApiIntro {
  def main(args: Array[String]): Unit = {
    println("Catalog API in spark")
    val session=SparkSession.builder().appName("spark catalog api").master("local").getOrCreate()
    val catalog=session.catalog
    println("Current database name is: "+catalog.currentDatabase)
    val tables=catalog.listTables()
    println("List of tables..")
    tables.show()
    catalog.setCurrentDatabase("spark_course")
    println("current database catalog"+catalog.currentDatabase)
    val table=catalog.listTables()
    table.show()
  }
}
