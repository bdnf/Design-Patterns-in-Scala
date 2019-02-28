package adapter

abstract class Target{
  def request()
}

class Adapter extends Target {
  override def request(): Unit = ()
}

trait Adaptee {
  def request()
}

class Volt(val volts:Int)

class Socket {
  def getVolts():Volt = return new Volt(volts=120)
}

trait SocketAdapter {
  def get120volts():Volt = return new Volt(volts=120)
  def get220volts():Volt = return new Volt(volts=220)
}

class SocketAdapterConcrete extends Socket with SocketAdapter {
  override def get120volts(): Volt = {
    val v:Volt = super.get120volts()
    return convertVolt(v, 10)
  }

  override def get220volts(): Volt = {
    val v: Volt = super.get220volts()
    return convertVolt(v, 40)
  }

  def convertVolt(v:Volt, i:Int): Volt = return new Volt(v.volts/i)
}

class SocketAdapteeConcrete extends SocketAdapter {

  val socket = new Socket()
  override def get120volts(): Volt = {
    val v = socket.getVolts()
    return convertVolt(v, 10)
  }

  override def get220volts(): Volt = {
    val v = socket.getVolts()
    return convertVolt(v, 40)
  }
  def convertVolt(v:Volt, i:Int): Volt = return new Volt(v.volts/i)
}

object Main extends App {

  def getVolt(socketAdapter: SocketAdapter, i: Int):Volt = {
    i match {
      case 120 => return socketAdapter.get120volts()
      case 220 => return socketAdapter.get220volts()
      case _ => return socketAdapter.get120volts()
    }
  }

  val socketAdapter = new SocketAdapterConcrete()
  val v120 = getVolt(socketAdapter, 120)
  println(s"v120 is using Object Adapter: ${v120.volts}")
}

