package factories

object CoordinateSystem extends Enumeration {
  type CoordinateSystem = Value
  val CARTESIAN, POLAR = Value
}

object Point { // singleton field
  // steps to add a new system
  // 1. augment CoordinateSystem
  // 2. change ctor
  val ORIGIN = new Point(0, 0)

  // factory method
  def newCartesianPoint(x: Double, y: Double) = new Point(x, y)

  def newPolarPoint(rho: Double, theta: Double) = new Point(rho * Math.cos(theta), rho * Math.sin(theta))

  object Factory {
    def newCartesianPoint(x: Double, y: Double) = new Point(x, y)
  }

}

class Point {

  import factories.CoordinateSystem.CoordinateSystem

  private var x = .0
  private var y = .0

  def this(x: Double, y: Double) {
    this()
    this.x = x
    this.y = y
  }

  def this(a: Double, b: Double, // names do not communicate intent
           cs: CoordinateSystem) = this
}

object PointFactory { 
  def newCartesianPoint(x: Double, y: Double) = new Point(x, y)
}

object FactoryDemo {
  def main(args: Array[String]): Unit = {
    val point = new Point(10, 5, CoordinateSystem.CARTESIAN)
    val origin = Point.ORIGIN
    val point1 = Point.Factory.newCartesianPoint(1, 2)
  }
}
