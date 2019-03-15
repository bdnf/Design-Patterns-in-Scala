package classic_patterns.chain_of_responsibility

abstract class ChainOfResponsibility {

  private var successor:Option[ChainOfResponsibility] = None

  def getSuccessor():Option[ChainOfResponsibility] = return this.successor
  def setSuccessor(successor: Option[ChainOfResponsibility]) = this.successor = successor

  val BASE_APPROVE_AMOUNT = 1000

  def canApprove(amount: Int): Boolean

  def getRole(): String

  def processRequest(request: PurchaseRequest): Unit = {

   if (canApprove(request.amount) | successor.isEmpty  )
     println(this.getRole + " will approve $" + request.amount)
   else successor.get.processRequest(request)

  }

}

class CEO() extends ChainOfResponsibility {
  override def canApprove(amount: Int): Boolean =
    if (amount < BASE_APPROVE_AMOUNT * 10) {
      println(s"Amount $amount approved successfully by $getRole")
      return true
    }
    else return false

  override def getRole(): String = "CEO"
}

class DepartmentDirector() extends ChainOfResponsibility {
  override def canApprove(amount: Int): Boolean =
    if (amount < BASE_APPROVE_AMOUNT * 5) {
      println(s"Amount $amount approved successfully by $getRole")
      return true
    }
    else return false

  override def getRole(): String = "Dep.Director"
}

class Manager() extends ChainOfResponsibility {

  override def canApprove(amount: Int): Boolean =
    if (amount < BASE_APPROVE_AMOUNT * 2) {
      println(s"Amount $amount approved successfully by $getRole")
      return true
    }
    else return false

  override def getRole(): String = "CEO"
}

class PurchaseRequest(val amount: Int, val purpose: String)


object Hierarchy extends App {

  val ceo = new CEO()
  val director = new DepartmentDirector()
  val manager = new Manager()

  ceo.setSuccessor(None)
  director.setSuccessor(Some(ceo))
  manager.setSuccessor(Some(director))

  val buy = new PurchaseRequest(100000, "Buy Self-Driving Car")
  println(s"Making a Purchase request for ${buy.purpose} and amount of ${buy.amount}")
  println("Who should approve?")
  manager.processRequest(buy)


}
