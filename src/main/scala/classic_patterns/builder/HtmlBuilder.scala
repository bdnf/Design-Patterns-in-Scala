package classic_patterns.builder

import java.util
import java.util.Collections


class HtmlElement(var name: String, var text: String) {
  val elements = new util.ArrayList[HtmlElement](0)
  final private val indentSize = 2
  final private val newLine = System.lineSeparator

  private def toStringImpl(indent: Int): String = {
    val sb = new StringBuilder
    val i = String.join("", Collections.nCopies(indent * indentSize, " "))
    sb.append(String.format("%s<%s>%s", i, name, newLine))
    if (text != null && !text.isEmpty) sb.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " "))).append(text).append(newLine)
    import scala.collection.JavaConversions._
    for (e <- elements) {
      sb.append(e.toStringImpl(indent + 1))
    }
    sb.append(String.format("%s</%s>%s", i, name, newLine))
    sb.toString
  }

  override def toString: String = toStringImpl(0)
}

class HtmlBuilder(var rootName: String) {
  private var root = new HtmlElement(rootName, "")

  // not fluent
  def addChild(childName: String, childText: String): Unit = {
    val e = new HtmlElement(childName, childText)
    root.elements.add(e)
  }

  def addChildFluent(childName: String, childText: String): HtmlBuilder = {
    val e = new HtmlElement(childName, childText)
    root.elements.add(e)
    this
  }

  def clear(): Unit = {
    root = new HtmlElement(rootName, "")
  }

  // delegating
  override def toString: String = root.toString
}

object BuilderDemo {
  def main(args: Array[String]): Unit = {

    // building a simple HTML paragraph
    println("Testing")
    val hello = "hello"
    val sb = new StringBuilder
    sb.append("<p>").append(hello).append("</p>") // a builder!

    println(sb)
    // building a list with 2 words
    val words = Array("hello", "world")
    sb.setLength(0) // clear it

    sb.append("<ul>\n")
    for (word <- words) { // indentation management
      sb.append(String.format("  <li>%s</li>\n", word))
    }
    sb.append("</ul>")
    println(sb)

    // ordinary non-fluent builder
    val builder = new HtmlBuilder("ul")
    builder.addChild("li", "hello")
    builder.addChild("li", "world")
    println(builder)

    // fluent builder
    builder.clear()
    builder
      .addChildFluent("li", "hello")
      .addChildFluent("li", "world")
    println(builder)
  }
}