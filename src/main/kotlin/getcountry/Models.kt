package getcountry

import com.google.gson.annotations.SerializedName

data class Country(@SerializedName("country_name") val name: String,
                   @SerializedName("country_synonyms") val synonims: List<String> = ArrayList(),
                   @SerializedName("regions") val regions: List<Region> = ArrayList())

data class Region(@SerializedName("region_name") val name: String,
                  @SerializedName("region_synonyms") val synonims: List<String> = ArrayList(),
                  @SerializedName("cities") val cities: List<City> = ArrayList())

data class City(@SerializedName("city_name") val name: String,
                @SerializedName("city_synonyms") val synonims: List<String> = ArrayList())

interface IResultCountry{
    val country: String
    var region: String
    var city: String
    var foundNubmer: Int

}

data class ResultCountry(override val country: String,
                         override var region: String = "",
                         override var city: String = "",
                         override var foundNubmer: Int = 0) : IResultCountry
