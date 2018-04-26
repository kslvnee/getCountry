@file:JvmName("GetCountry")

package getcountry


@JvmOverloads
fun get(location:String):String {
    if ("D".equals(location)) {
        return "Hello"
    }
    return "Hello $location"

}
