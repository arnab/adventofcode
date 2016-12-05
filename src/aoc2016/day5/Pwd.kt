package aoc2016.day5

import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex

object Pwd {
    fun calculate(doorId: String): String {
        var i = 0
        var pwd = ""
        (0..7).forEach {
            while (extractNextPwdChar("$doorId$i") == null) i += 1
            pwd += extractNextPwdChar("$doorId$i")
            i += 1
        }
        return pwd
    }

    private fun extractNextPwdChar(s: String): Char? {
        val hsh = md5(s)
        return if (hsh.startsWith("00000")) hsh[5] else null
    }

    fun md5(s: String): String {
        val messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset()
        messageDigest.update(s.toByteArray(Charset.forName("UTF8")))
        val resultByte = messageDigest.digest()
        return String(Hex.encodeHex(resultByte))
    }
}
