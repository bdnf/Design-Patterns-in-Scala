package classic_patterns.factories

object FactoryMethod extends App{
  val clientMySql: DatabaseClient = new MysqlClient
  val clientPgSql: DatabaseClient = new PgSqlClient
  clientMySql.executeQuery("SELECT * FROM users")
  clientPgSql.executeQuery("SELECT * FROM employees")
}

trait SimpleConnection {
  def getName(): String
  def executeQuery(query: String): Unit
}

class SimpleMysqlConnection extends SimpleConnection {
  override def getName(): String = "SimpleMysqlConnection"
  override def executeQuery(query: String): Unit = println(s"Executing query '$query' with MySQL.")
}

class SimplePgSqlConnection extends SimpleConnection {
  override def getName(): String = "SimplePgSqlConnection"
  override def executeQuery(query: String): Unit = println(s"Executing query '$query' with PgSQL")
}
//FactoryMethod connect
abstract class DatabaseClient {
  def executeQuery(query: String): Unit = {
    val connection: SimpleConnection = connect()
    connection.executeQuery(query)
  }
  protected def connect(): SimpleConnection
}

class MysqlClient extends DatabaseClient {
  override protected def connect(): SimpleConnection = new SimpleMysqlConnection
}

class PgSqlClient extends DatabaseClient {
  override protected def connect(): SimpleConnection = new SimplePgSqlConnection
}
