package classic_patterns.command

class BankAccount {
  var balance = 0
  def deposit(amount: Int) = balance += amount
  def withdraw(amount: Int) = balance -= amount
}

trait BankCommand {
  def call(): Unit
}

sealed class Action
case object Deposit extends Action
case object Withdraw extends Action

class BankAccountCommand(val account: BankAccount, action: Action, amount: Int) extends BankCommand {

  override def call(): Unit = action match {
    case Deposit => {
      println(s"Depositing $amount")
      account.deposit(amount)
    }
    case Withdraw => {
      println(s"Withdrawing $amount")
      account.withdraw(amount)
    }
    case _ =>
  }
}

object Banking extends App {

  val account = new BankAccount()

  val commands = Seq(
    new BankAccountCommand(account, Deposit, 100),
    new BankAccountCommand(account, Deposit, 1000),
    new BankAccountCommand(account, Withdraw, 250)
    )

  commands.map(_.call)

  print(s"Now account balance is: ${account.balance}")

}
