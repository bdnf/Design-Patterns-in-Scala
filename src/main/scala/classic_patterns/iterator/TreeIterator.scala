package classic_patterns.iterator

import java.util


class Node[T](val value: T, val left: Node[T], val right: Node[T]) {
  var parent: Node[T] = this


  def this(value: T) = {
    this(value, null, null)
  }

  private def traverse(current: Node[T], acc: util.ArrayList[Node[T]]): Unit = {
    acc.add(current)
    if (current.left != null) traverse(current.left, acc)
    if (current.right != null) traverse(current.right, acc)
  }

  def preOrder: util.Iterator[Node[T]] = {
    val items = new util.ArrayList[Node[T]]
    traverse(this, items)
    items.iterator
  }
}

object Main extends App {
  val node = new Node[Character] ('a',
    new Node[Character]('b',
      new Node[Character]('c'),
      new Node[Character]('d')),
    new Node[Character]('e'))
  val sb = new StringBuilder()
  val it = node.preOrder
  while (it.hasNext())
  {
    sb.append(it.next().value)
  }

  println(sb.toString())

}