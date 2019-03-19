package classic_pattern_scala_way

trait FunctionStateObserver {

  type State

  type Observer = State => Unit

  private var observers: List[Observer] = Nil

  def addObserver(observer: Observer): Unit = {
    observers ::= observer
  }

  def notifyObservers(state: State): Unit =
    observers.foreach( o => o(state))
}

object FunctionStateObserver extends App {

  val observer: Int => Unit = (state: Int) => println(s"Received state update: $state")

  val subject = new FunctionStateObserver {
    override type State = Int
    protected var count = 0

    def inc(): Unit ={
      count +=1
      notifyObservers(count)
    }
  }

  subject.inc()
  subject.inc()
  subject.addObserver(observer)
  subject.inc()
  subject.inc()
  subject.inc()
}
