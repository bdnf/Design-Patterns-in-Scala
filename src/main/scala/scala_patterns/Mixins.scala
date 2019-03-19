package scala_patterns
//traits for modularization of code

/**RULES: from David Wampler's Book

  1. An abstract base class or trait
      is subclassed one level by concrete classes, including case classes
  2. Concrete classes are NEVER subclassed, except for:
      - classes that mix in other behaviours in traits
      - test-only versions
  3. When subcassing seems like the right approach,
      consider partitioning behaviours into traits
      and mix in those traits instead
  4. NEVER split logical state across parent-child boundaries
      (visible state of button - disjoint form internal state0change mechanism)
  **/

object Mixins {

  trait Observer[-T] {
    def receiveUpdate(state: T): Unit
  }

  trait Observable[T] {
    private var observers = List.empty[Observer[T]]

    def subscribe(observer: Observer[T]): Unit = observers ::= observer

    protected def notifyObservers(state: T): Unit = observers foreach (_.receiveUpdate(state))
  }

  //Example
  class Button(val label: String) {
    def click() = println("Updated UI")
  }

  class ObservableButton(name: String) extends Button(name) with Observable[Button] {
    override def click(): Unit = {
      super.click()
      notifyObservers(this)
    }
  }

  class ButtonCountObserver extends Observer[Button] {
    var count = 0
    override def receiveUpdate(state: Button): Unit = count += 1
  }

}
object Demo extends App {

  import Mixins._
  val button = new ObservableButton("Click Me!")
  val countObserver1 = new ButtonCountObserver
  val countObserver2 = new ButtonCountObserver

  button subscribe(countObserver1)
  button subscribe(countObserver2)
  (1 to 5) foreach (_ => button.click())

  //Mixing "on-the-fly"
  val buttonHot = new Button("Click Me!") with Observable[Button]{
    override def click(): Unit = {
      super.click()
      notifyObservers(this)
    }
  }
  val hotCountObserver1 = new ButtonCountObserver
  val hotCountObserver2 = new ButtonCountObserver
  buttonHot subscribe(hotCountObserver1)
  buttonHot subscribe(hotCountObserver2)
  (1 to 3) foreach (_ => buttonHot.click())

  println(s"Button countObserver1 got ${countObserver1.count} clicks")
  println(s"Button countObserver2 got ${countObserver2.count} clicks")
  println(s"Button hotCountObserver1 got ${hotCountObserver1.count} clicks")
  println(s"Button hotCountObserver2 got ${hotCountObserver2.count} clicks")

}
