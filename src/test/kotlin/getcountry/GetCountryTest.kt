package getcountry

import org.junit.Assert.assertEquals
import org.junit.Test


internal class GetCountryTest {

    @Test
    fun getTestWithWrongLocation() {
        assertEquals(ArrayList<IResultCountry>(),get("location"))
    }

    @Test
    fun getTestWithEmptyLocation() {
        assertEquals(ArrayList<IResultCountry>(),get(""))
    }
}