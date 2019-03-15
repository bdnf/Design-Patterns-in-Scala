package classic_pattern_scala_way

case class Volt(val volts:Int)
class Socket(val volts:Volt = Volt(120))

class SocketAdapteeConcrete(val socket:Socket) {

  def get120volts(): Volt = {
    val v = socket.volts
    return convertVolt(v, 10)
  }

  def get220volts(): Volt = {
    val v = socket.volts
    return convertVolt(v, 40)
  }
  def convertVolt(v:Volt, i:Int): Volt = return Volt(v.volts/i)
}

class SocketAdapter(val adaptee: SocketAdapteeConcrete) extends Socket {
  def convertVolt(v:Volt, i:Int): Volt = return Volt(v.volts/i)
}

object ImplicitAdapter {

  implicit class BetterSocketAdapter(val adaptee: SocketAdapteeConcrete) extends Socket {
    def convertVolt(v: Volt, i: Int): Volt = return Volt(v.volts / i)
  }

}

object ScalaAdapter extends App {

  //example 1
  val socket = new Socket(Volt(120))
  val socketAdaptee = new SocketAdapteeConcrete(socket)
  val v120 = socketAdaptee.get120volts()
  println(s"v120 is using Object Adapter: ${v120.volts}")
  val result = new SocketAdapter(socketAdaptee).convertVolt(Volt(120), 4)
  println(s"Result is: $result")

  //example 2
  import ImplicitAdapter.BetterSocketAdapter
  val socketImpl = new Socket(Volt(120))
  val socketAdapteeImpl = new SocketAdapteeConcrete(socket)
  val v120impl = socketAdapteeImpl.get120volts()
  val resultImpl = socketAdapteeImpl.convertVolt(Volt(120), 4) //no mentioning of Adapter
  println(s"Implitic result is: $result")

}