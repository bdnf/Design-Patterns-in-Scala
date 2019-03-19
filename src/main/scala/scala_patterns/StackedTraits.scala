package scala_patterns

abstract class Queue[T] {
  var buf: List[T]
  def get(): T
  def put(x: T)
}
class BasicIntQueue extends Queue[Int] {
  var buf: List[Int] = List.empty[Int]
  def get(): Int = {
    if (buf.isEmpty) return -1
    else {
      val elem: Int = buf.head
      buf = buf.tail
      elem
    }
  }
  def put(x: Int) = buf :+= x
}

trait Incrementing extends Queue[Int] {
  abstract override def put(x: Int) { super.put(x + 1) }
}

trait Filtering extends Queue[Int] {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}

object StackedTraits extends App {

  val queue = (new BasicIntQueue with Incrementing with Filtering)
  queue.put(-2)
  queue.put(1)
  queue.put(10)
  assert(queue.get() == 2)
  assert(queue.get() == 11)
}
