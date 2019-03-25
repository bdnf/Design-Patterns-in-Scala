package classic_patterns.strategy

import scala.collection.mutable.ArrayBuffer
//Strategy

trait Payment {
  def pay(amount: Int)
}

class PayPalAlgorithm(email: String, password: String) extends Payment {
  override def pay(amount: Int): Unit = println(s"$amount Paid with PayPal")
}

class CreditCardAlgorithm(name: String, cardNumber: String) extends Payment {
  override def pay(amount: Int): Unit = println(s"$amount Paid with Credit Card")
}
//--Implementation

case class ProductType(val price: Int, val upcCode: String)
class ShoppingCart {
  var productList = ArrayBuffer.empty[ProductType]
  def addProduct(product: ProductType) = productList :+= product
  def removeProduct(product: ProductType) = productList.filterNot(_ == product)
  def calculateTotal() = {
    var sum = 0
    for {
      p<-productList
    } sum += p.price
    sum
  }
  def pay(paymentmethod: Payment): Unit = {
    val amount = calculateTotal()
    paymentmethod.pay(amount)
  }
}

object Strategy extends App{

  val cart = new ShoppingCart
  val shirt = new ProductType(15, "1223")
  val jeans = new ProductType(30, "2344")

  cart.addProduct(shirt)
  cart.addProduct(jeans)

  cart.pay(new CreditCardAlgorithm("CardholderName", "12345566788"))

}
