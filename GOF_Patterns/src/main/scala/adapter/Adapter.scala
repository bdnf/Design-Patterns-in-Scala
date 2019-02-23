package adapter

abstract class Target{
  def request()
}

class Adapter extends Target {
  override def request(): Unit = ???
}

class Adaptee {
  def request()= ???
}
