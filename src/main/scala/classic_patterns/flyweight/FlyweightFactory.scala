package classic_patterns.flyweight

import scala.collection.mutable
import scala.util.Random

class Circle(color:String, radius: Int)

class FlyweightFactory {

  private val circleStore: mutable.Map[(String,Int), Circle] = mutable.HashMap.empty[(String, Int), Circle]

  def getShape(color: String, radius: Int): Circle = {
    val circle: Option[Circle] = circleStore.get((color, radius))

    circle match {
      case Some(x) => {
        println("This circle already exist")
        return x
      }
      case None => {
        val circle = new Circle(color, radius)
        circleStore.put((color, radius), circle)
        println(s"Added new circle to the store")
        return circle
      }
    }
  }

}

object Main extends App {

  def randomBetween(start:Int, stop:Int) ={
    val r = Random
    val r1 = start + r.nextInt(( stop - start) + 1)
    r1
  }

  val store = new FlyweightFactory()
  val colors = List("red", "green", "black","blue")

  for (i <- 0 to 50){
    val r = randomBetween(1,10)
    val col = colors(r % colors.length)

    val circle = store.getShape(col, r)

  }
}
