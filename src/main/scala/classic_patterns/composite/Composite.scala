package classic_patterns.composite

import java.util
import java.util.Collections


class GraphicObject() {
  protected var name = "Group"

  def getName: String = name

  def setName(name: String): Unit = {
    this.name = name
  }

  var color: String = null
  var children = List.empty[GraphicObject]

  private def print(stringBuilder: StringBuilder, depth: Int): Unit = {
    stringBuilder.append(String.join("", Collections.nCopies(depth, "*"))).append(if (depth > 0) " "
    else "").append(if (color == null || color.isEmpty) ""
    else color + " ").append(getName).append(System.lineSeparator)

    for (child: GraphicObject <- children) {
      child.print(stringBuilder, depth + 1)
    }
  }

  override def toString: String = {
    val sb = new StringBuilder
    print(sb, 0)
    sb.toString
  }
}

class Circle(color: String) extends GraphicObject {
  name = "Circle"
}

class Square(color: String) extends GraphicObject {
  name = "Square"
}

object GeometricShapesDemo {
  def main(args: Array[String]): Unit = {
    val drawing = new GraphicObject
    drawing.setName("My Drawing")
    drawing.children :+= new Square("Red")
    drawing.children :+= new Circle("Yellow")
    val group = new GraphicObject
    group.children :+= new Circle("Blue")
    group.children :+= new Square("Blue")
    drawing.children :+= group
    println(drawing)
  }
}

