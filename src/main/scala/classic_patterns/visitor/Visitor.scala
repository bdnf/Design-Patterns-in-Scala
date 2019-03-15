package classic_patterns.visitor

abstract class ExpressionVisitor {
  def visit(value: Value): Unit

  def visit(ae: AdditionExpression): Unit

  def visit(me: MultiplicationExpression): Unit
}

abstract class Expression {
  def accept(ev: ExpressionVisitor): Unit
}

class Value(var value: Int) extends Expression {
  override def accept(ev: ExpressionVisitor): Unit = {
    ev.visit(this)
  }
}

class AdditionExpression(var lhs: Expression, var rhs: Expression) extends Expression {
  override def accept(ev: ExpressionVisitor): Unit = {
    ev.visit(this)
  }
}

class MultiplicationExpression(var lhs: Expression, var rhs: Expression) extends Expression {
  override def accept(ev: ExpressionVisitor): Unit = {
    ev.visit(this)
  }
}

class ExpressionPrinter extends ExpressionVisitor {
  private val sb = new StringBuilder

  override def visit(value: Value): Unit = {
    sb.append(value.value)
  }

  override def visit(ae: AdditionExpression): Unit = {
    sb.append("(")
    ae.lhs.accept(this)
    sb.append("+")
    ae.rhs.accept(this)
    sb.append(")")
  }

  override def visit(me: MultiplicationExpression): Unit = {
    me.lhs.accept(this)
    sb.append("*")
    me.rhs.accept(this)
  }

  override def toString: String = sb.toString
}


object Main extends App {

  val simple = new AdditionExpression(new Value(2), new Value(3))
  val ep1 = new ExpressionPrinter()
  ep1.visit(simple)
  ep1.toString() // "(2+3)"

  val expr = new MultiplicationExpression(
    new AdditionExpression(new Value(2), new Value(3)),
    new Value(4)
  )
  val ep2 = new ExpressionPrinter()
  ep2.visit(expr)
  ep2.toString() // "(2+3)*4"
}
