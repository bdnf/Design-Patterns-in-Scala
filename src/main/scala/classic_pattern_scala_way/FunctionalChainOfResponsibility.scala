package classic_pattern_scala_way

class FunctionalChainOfResponsibility {
  def processRequest(approvableAmount: Int): PartialFunction[Int, Int] = {
    case amount:Int if amount <= approvableAmount => {
      println("Amount approved")
      amount
    }
    case m @ nextAmount => {
      println(s"Amount $m not approved and redirected to next auhority instance")
      m
    }
  }
}

class PartialFunctionRequest extends FunctionalChainOfResponsibility {

  val approveRequest = processRequest(1000)
    .andThen(processRequest(1000*2))
    .andThen(processRequest(1000*5))
    .andThen(processRequest(1000*10))
    .andThen(processRequest(1000*1000))

  def requestMoney(amount:Int, purpose: String) = {
    if (amount < 1000) println(s"Approving your request for $purpose right away!")
    else approveRequest(amount)
  }
}

object Hierarchy extends App {
  val hierarchy = new PartialFunctionRequest
  hierarchy.requestMoney(100000, "Buy Self-Driving Car")
}
