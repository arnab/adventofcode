package aoc2016.day5

//import org.apache.commons.codec.binary.Hex
import java.nio.charset.Charset
import java.security.MessageDigest

object Pwd {
//    fun calculate(doorId: String): String {
//        var i = 0
//        var pwd = ""
//        (0..7).forEach {
//            while (extractNextPwdChar("$doorId$i") == null) i += 1
//            pwd += extractNextPwdChar("$doorId$i")
//            i += 1
//        }
//        return pwd
//    }
//
//    fun calculatePositional(doorId: String): String {
//        var i = 0
//        var pwd = CharArray(8)
//        while (pwd.any { it == '\u0000' }) {
//            while (extractNextPwdCharPositional("$doorId$i") == null) i += 1
//            val (pos, char) = extractNextPwdCharPositional("$doorId$i")!!
//            if (pwd[pos] == '\u0000') pwd[pos] = char
//            i += 1
//        }
//        return pwd.joinToString("")
//    }
//
//    private fun extractNextPwdCharPositional(s: String): Pair<Int, Char>? {
//        val hsh = md5(s)
//        val result = if (hsh.startsWith("00000")) {
//            val pos = hsh[5]
//            if (pos.isDigit() && pos.toString().toInt() < 8)
//                Pair(pos.toString().toInt(), hsh[6])
//            else
//                null
//        } else null
//        return result
//    }
//
//    private fun extractNextPwdChar(s: String): Char? {
//        val hsh = md5(s)
//        return if (hsh.startsWith("00000")) hsh[5] else null
//    }
//
//    fun md5(s: String): String {
//        val messageDigest = MessageDigest.getInstance("MD5");
//        messageDigest.reset()
//        messageDigest.update(s.toByteArray(Charset.forName("UTF8")))
//        val resultByte = messageDigest.digest()
//        return String(Hex.encodeHex(resultByte))
//    }
}
