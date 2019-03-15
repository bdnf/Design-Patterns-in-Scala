package classic_patterns.null_object

trait Log {
  def info(msg: String): Unit
  def warning(msg: String): Unit
}

class ConsoleLog extends Log {
  override def info(msg: String): Unit = println(msg)
  override def warning(msg: String): Unit = println("WARNING: " + msg)
}

class BankAccountWithLog(var log: Log) {
  private var balance = 0

  def deposit(amount: Int): Unit = {
    balance += amount
    // What now. Need to check for null everywhere?
    if (log != null) log.info("Deposited " + amount + ", balance is now " + balance)
  }

  def withdraw(amount: Int): Unit = {
    if (balance >= amount) {
      balance -= amount
      if (log != null) println("Withdrew " + amount + ", we have " + balance + " left")
    }
    else if (log != null) println("Could not withdraw " + amount + " because balance is only " + balance)
  }
}

// just with empty method bodies
final class NullLog extends Log {
  override def info(msg: String): Unit = {}
  override def warning(msg: String): Unit = {}
}

object NullObjectDemo extends App {

  //ConsoleLog log = new ConsoleLog();
  //Log log = null;
  //but this works better:
  val log = new NullLog
  val accountNoLog = new BankAccountWithLog(log)
  accountNoLog.deposit(100)
  accountNoLog.withdraw(200)

}
