package aoc2016.day4

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

}
