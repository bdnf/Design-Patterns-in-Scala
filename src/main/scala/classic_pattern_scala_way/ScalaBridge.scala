package classic_pattern_scala_way

import java.security.MessageDigest
import org.apache.commons.codec.binary.Hex

trait Hasher {
  def hash(data: String): String

  protected def getDigest(algorithm: String, data: String) = {
    val crypt = MessageDigest.getInstance(algorithm)
    crypt.reset()
    crypt.update(data.getBytes("UTF-8"))
    crypt
  }
}

trait Sha256Hasher extends Hasher {
  override def hash(data: String): String =
    new String(Hex.encodeHex(getDigest("SHA-256", data).digest()))
}
//Self Type with Hasher mixin
abstract class PasswordConverterBase {
  self: Hasher =>
  def convert(password: String): String
}

class SimplePasswordConverterScala extends PasswordConverterBase {
  self: Hasher =>
  override def convert(password: String): String = hash(password)
}

class SaltedPasswordConverterScala(salt: String) extends PasswordConverterBase {
  self: Hasher =>
  override def convert(password: String): String =
    hash(s"${salt}:${password}")
}

object ScalaBridge extends App {

  val p1 = new SimplePasswordConverterScala
              with Sha256Hasher //mixin algorithm
  val p2 = new SaltedPasswordConverterScala("8jsdf32T^$%")
              with Sha256Hasher //mixin algorithm

  println(s"'password' in SHA-256 is: ${p1.convert("password")}")
  println(s"'password' in salted SHA-256 is: ${p2.convert("password")}")
}
