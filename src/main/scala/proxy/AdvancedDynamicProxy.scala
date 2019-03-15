package proxy

import javassist.scopedpool.SoftValueHashMap
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util


class LoggingHandler(val target: Any) extends InvocationHandler {
  private val calls = new util.HashMap[String, Integer]

  @throws[Throwable]
  override def invoke(proxy: Any, method: Method, args: Array[AnyRef]): Object = {
    val name = method.getName
    if (name.contains("toString")) return calls.toString
    // add or increment number of calls
    calls.merge(name, 1, (x, y) => x + y)
    method.invoke(target, args)
  }
}

trait Human {
  def walk(): Unit

  def talk(): Unit
}


class Person extends Human {
  override def walk(): Unit = {
    System.out.println("I am walking")
  }

  override def talk(): Unit = {
    System.out.println("I am talking")
  }
}

object DynamicLoggingProxyDemo {
  @SuppressWarnings(Array("unchecked")) def withLogging[T](target: T, itf: Class[T]): T = Proxy.newProxyInstance(itf.getClassLoader, Array[Class[_]](itf), new LoggingHandler(target)).asInstanceOf[T]

  def main(args: Array[String]): Unit = {
    val person = new Person
    val logged = withLogging(person, classOf[Human])
    logged.walk()
    logged.talk()
    logged.talk()
    System.out.println(logged)
  }
}


