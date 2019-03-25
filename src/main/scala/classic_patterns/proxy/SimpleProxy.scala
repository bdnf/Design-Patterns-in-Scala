package classic_patterns.proxy

import java.util

// Goal: changing object behaviour, but using existing interface

trait Bank{
  def withdrawMoney(client: String)
}

class ConcreteBank extends Bank {
  override def withdrawMoney(client: String): Unit = {
    println(s"Client $client withdarwed money from our bank")
  }
}

class ProxyBank extends Bank {
  private val bank = new ConcreteBank

  var clientList = List("Responsible Client1", "Responsible Client2")

  override def withdrawMoney(client: String): Unit = {
    if (clientList.contains(client)){
      println(s"$client is allowed to use services or the bank")
    } else {
      println("Access to the bank denied. Please contact your manager")
    }
  }
}

object SimpleProxy extends App {

  val bank = new ProxyBank
  bank.withdrawMoney("Unresponsible client")
}
