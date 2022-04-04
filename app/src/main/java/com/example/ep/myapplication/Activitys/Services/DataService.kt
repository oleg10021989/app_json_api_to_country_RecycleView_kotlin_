package com.example.ep.myapplication.Activitys.Services

import android.os.NetworkOnMainThreadException
import android.os.StrictMode
import com.example.ep.myapplication.Activitys.Model.State
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class DataService {
    private val arrState = ArrayList<State>()
    private val arrState2 = ArrayList<State>()
    fun getNstateFromBstate(stateCode: List<String?>?): ArrayList<State> {
        ArrayList<String>()
        val sURL = "https://restcountries.com/v2/all?fields=name,nativeName" // gets a state by code
        val sURL2 = "https://disease.sh/v2/countries"
        val name: String? = null
        var s: State? = null

        // Connect to the URL using java's native library
        var url: URL? = null
        var url2: URL? = null
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val policy2 = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy2)
        try {
            url = URL(sURL)
            url2 = URL(sURL2)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        try {
            assert(url != null)
            val request = url!!.openConnection() as HttpURLConnection
            assert(url2 != null)
            val request2 = url2!!.openConnection() as HttpURLConnection
            request.connect()
            request2.connect()
            val jp = JsonParser() //from gson
            val root =
                jp.parse(InputStreamReader(request.content as InputStream)) //Convert the input stream to a json element
            val rootobj = root.asJsonArray
            val jp2 = JsonParser() //from gson
            val root2 = jp2.parse(InputStreamReader(request2.content as InputStream)) //Convert the input stream to a json element
            val rootobj2 = root2.asJsonArray
            for (je2 in rootobj2) {
                val obj2 = je2.asJsonObject
                val entriescountry = obj2["country"]
                val country = entriescountry.toString().replace("\"", "")
                val entriescountryInfo = obj2["countryInfo"]
                val iso3s = entriescountryInfo.toString()
                //                for(String str : stateCode) {
//                    Log.d("oleg121", entriescountryInfo.toString() + "->" + iso3s.contains(str));
//                }
                for (str in stateCode!!) {
                    if (iso3s.contains(str!!)) {
                        for (je in rootobj) {
                            val obj = je.asJsonObject //since you know it's a JsonObject
                            val entriesname = obj["name"] //will return members of your object
                            val entriesnative = obj["nativeName"]
                            if (country.contains(entriesname.toString().replace("\"", ""))) {
                                val nameR = entriesname.toString()
                                    .replace("\"", "") // convert to pure string
                                val nativen = entriesnative.toString().replace("\"", "")
                                //                                Log.d("oleg12", nativen + "  " + nameR + " = " + country);
//                                Log.d("oleg121", str);
                                arrState2.add(State(nameR, nativen))
                                s = State(nameR, nativen)
                                break
                            }
                        }
                        if (s == null) {
                            s = State("the name of country uncorect ", "  ")
                            arrState2.add(State("the name of country uncorect ", "  "))
                        }
                    }
                }
            }
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return arrState2
    }

    fun getArrState(): ArrayList<State> {
        val sURL =
            "https://restcountries.com/v2/all?fields=flag,name,borders,nativeName" // get all states

        // Connect to the URL using java's native library
        var url: URL? = null
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            url = URL(sURL)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        try {
            assert(url != null)
            val request = url!!.openConnection() as HttpURLConnection
            request.connect()

            // Convert to a JSON object to print data
            val jp = JsonParser() //from gson
            val root =
                jp.parse(InputStreamReader(request.content as InputStream)) //Convert the input stream to a json element
            //            Log.d("oleg123", root.toString());
            val rootobj = root.asJsonArray //May be an array, may be an object.
            var entriesborders: JsonArray
            for (je in rootobj) {
                val obj = je.asJsonObject //since you know it's a JsonObject
                val entriesname = obj["name"] //will return members of your object
                val entriesnative = obj["nativeName"]
                entriesborders = if (obj["borders"] != null) {
                    obj["borders"] as JsonArray
                } else {
                    JsonArray(1)
                }
                //                JsonArray  entriesborders = (JsonArray) obj.get("borders");
                val entriesflag = obj["flag"]
                val name = entriesname.toString().replace("\"", "")
                val nativen = entriesnative.toString().replace("\"", "")
                val flag = entriesflag.toString().replace("\"", "")
                val arrBorders = ArrayList<String>()
                for (j in entriesborders) {
                    val s = j.toString().replace("\"", "")
                    arrBorders.add(s)
                }
                arrState.add(State(name, arrBorders, nativen, flag))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NetworkOnMainThreadException) {
            e.printStackTrace()
        }
        return arrState
    }
}