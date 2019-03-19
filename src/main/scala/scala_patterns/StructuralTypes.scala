package scala_patterns

//Similar to Functional state approach
// where type Observer = State => Unit

trait StructuralTypes {

  type State

  type Observer = { def receiveUpdate(state: Any): Unit} //structural type with Any as some known type parameter.
                                                          // T won't complile

  private var observers: List[Observer] = Nil

  def addObserver(observer: Observer): Unit = {
    observers ::= observer
  }

  def notifyObservers(state: State): Unit =
    observers.foreach( _.receiveUpdate(state))
}


object Observer extends App {
  // to verify type name
  import scala.language.reflectiveCalls

  object observer {
    def receiveUpdate(state: Any): Unit = println(s"Received state update: $state")
  }

  val subject = new StructuralTypes {
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

