package getcountry

import com.google.common.base.MoreObjects
import com.google.common.io.Resources
import com.google.gson.GsonBuilder
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.util.ArrayList

class Main {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            //println(get("St. Petersburg, Russia, Usa, Fl"))
            // println(get("location"))
//            val loader = MoreObjects.firstNonNull(Thread.currentThread().contextClassLoader, Resources::class.java.classLoader)
//
//            val urls = loader.getResources("countries/*.json")
//            val list = urls.toList()
            val countries : MutableList<Country> = ArrayList()

            //var r = resolver.getResources("classpath*:some/package/name/**/*.xml")


            println("1")
        }
    }
}