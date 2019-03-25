package classic_patterns.factories

trait DatabaseConnectorFactory {
  def connect(): SimpleConnection
}

class MysqlConnection extends SimpleConnection {
  override def getName(): String = "SimpleMysqlConnection"
  override def executeQuery(query: String): Unit = println(s"Executing query '$query' with MySQL.")
}

class PgSqlConnection extends SimpleConnection {
  override def getName(): String = "SimplePgSqlConnection"
  override def executeQuery(query: String): Unit = println(s"Executing query '$query' with PgSQL")
}

class MySqlFactory() extends DatabaseConnectorFactory {
  override def connect(): SimpleConnection = new MysqlConnection
}

class PgSqlFactory() extends DatabaseConnectorFactory {
  override def connect(): SimpleConnection = new PgSqlConnection
}
//Abstract factory
class DbClient(connectorFactory: DatabaseConnectorFactory) {
  def executeQuery(query: String): Unit = {
    val connection = connectorFactory.connect()
    connection.executeQuery(query)
  }
}

object AbstractFactory extends App{
  val clientMySql = new DbClient(new MySqlFactory)
  val clientPgSql = new DbClient(new PgSqlFactory)
  clientMySql.executeQuery("SELECT * FROM users")
  clientPgSql.executeQuery("SELECT * FROM employees")
}
