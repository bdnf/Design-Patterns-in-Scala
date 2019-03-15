package memento


// similar to Command pattern but
// stores set of states
// instead set of changes

class MementoSnapshot(val balance: Int) // Rollback via Token


class BankAccount {
  var balance = 0
  def deposit(amount: Int): MementoSnapshot = {
    balance += amount
    return new MementoSnapshot(balance)
  }
  def withdraw(amount: Int): MementoSnapshot = {
    balance -= amount
    return new MementoSnapshot(balance)
  }

  def restore(m: MementoSnapshot): Unit = balance = m.balance
}

object Banking extends App {

  val account = new BankAccount()


  val m1 = account.deposit(50) // 150
  val m2 = account.deposit(25) // 175
  println(s"Now account balance is: ${account.balance}")

  // restore to m1
  account.restore(m1)
  println(s"Restored account balance is: ${account.balance}")

  // restore to m2
  account.restore(m2)
  println(s"Second restored state of account balance is: ${account.balance}")


}
