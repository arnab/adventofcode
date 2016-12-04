package aoc2016.day4

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GetARoomTest {
    @Test fun Room_isReal_RealRoomExample1() {
        assertEquals(true, Room.from("aaaaa-bbb-z-y-x-123[abxyz]")!!.isReal())
    }
    @Test fun Room_isReal_RealRoomExample2() {
        assertEquals(true, Room.from("a-b-c-d-e-f-g-h-987[abcde]")!!.isReal())
    }
    @Test fun Room_isReal_RealRoomExample3() {
        assertEquals(true, Room.from("not-a-real-room-404[oarel]")!!.isReal())
    }
    @Test fun Room_isReal_NotRealRoomExample4() {
        assertEquals(false, Room.from("totally-real-room-200[decoy]")!!.isReal())
    }

    @Test fun sumOfValidRoomSectorIds_Examples() {
        val data = """aaaaa-bbb-z-y-x-123[abxyz]
                      a-b-c-d-e-f-g-h-987[abcde]
                      not-a-real-room-404[oarel]
                      totally-real-room-200[decoy]
                   """.trimIndent()
        val sum = GetARoom.sumOfValidRoomSectorIds(data)
        assertEquals(1514, sum)
    }
    @Test fun sumOfValidRoomSectorIds_Problem1() {
        val data = TestResourceReader.readFile("resources/aoc2016/day4/input.txt")
        val sum = GetARoom.sumOfValidRoomSectorIds(data)
        assertEquals(278221, sum)
    }

    @Test fun Room_name_Example5() {
        assertEquals("very encrypted name", Room.from("qzmt-zixmtkozy-ivhz-343[zimth]")!!.name)
    }
}
