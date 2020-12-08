package com.example.pizzaorderingapp

import com.google.android.gms.maps.model.LatLng

/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Cities class
* */

class Cities {
    //hashMap of city name and city object declaration
    val cities = hashMapOf<String, City>()
    constructor()
    {
        // Store pizza store IDs (used in Places API) information in city object
        var homeAddress:LatLng = LatLng(30.4034,-97.7226)
        val austin = City("Austin", mutableListOf("ChIJO2sMhHi1RIYRFgYG7hgl-6Y", "ChIJMX5-2-m0RIYRcp3AoYaNE9w", "ChIJVaujmae1RIYRpGyZcs9VBao", "ChIJu96UVvy0RIYRCPxWa4TSr6o", "ChIJG0-Fm3-1RIYRQgWzWeY1ljQ"), homeAddress)
        // Store City object in HashMap with city name as key for efficient access
        this.cities.put("Austin", austin)

        homeAddress = LatLng(32.7755,-96.8089)
        val dallas = City("Dallas",  mutableListOf("ChIJg9LVxoWZToYROYcMz4NT_Cw", "ChIJsWWnNbGeToYRWWt5hAcv6qw", "ChIJEWJ0wZWYToYRS-2LzR8pnPo", "ChIJaUqgjNSeToYRqpjno_vCILg", "ChIJI7ICkqCeToYROukGO2SczzI"), homeAddress)
        this.cities.put("Dallas", dallas)

        homeAddress = LatLng(29.425546,-98.4923371)
        val sanAntonio = City("San Antonio", mutableListOf("ChIJ51lHiXZZXIYRHrpTMRBFhiY", "ChIJvdDWrqxYXIYRrvbNKLrCjzA", "ChIJq6oGNN71XIYRcKOIOnDFxQ8", "ChIJQ5ukQLlYXIYRJiHwC1Shxs8", "ChIJaVRYcANZXIYR6BC3KzPrJZo"),homeAddress)
        this.cities.put("San Antonio", sanAntonio)

        homeAddress = LatLng(42.3518167,-71.0712057)
        val boston = City("Boston",  mutableListOf("ChIJWSwS9nZ644kR3jrjaLDWIAY", "ChIJ5Sk27_V544kRqLuOmhg2w_g", "ChIJ5ZsSxj1644kRWieCXebjoZ4", "ChIJlQwTsw9644kRVRk86P0nRBg", "ChIJGwXlxvV544kR6qP6Pr2EENU"), homeAddress)
        this.cities.put("Boston", boston)

        homeAddress = LatLng(29.754447,-95.3690466)
        val houston = City("Houston",  mutableListOf("ChIJ6UvzkMO_QIYRUFtNOhTLO-w", "ChIJvc6mQYzAQIYRRXAx2rEukig", "ChIJ70-JyG6_QIYR7dQxVzxJKEQ", "ChIJp5v6VCW_QIYRsBulbXJDew8", "ChIJNSAhnWq_QIYRXJ32NTjrcwU"), homeAddress)
        this.cities.put("Houston", houston)
    }

    fun getCity(name:String): City?
    {
        return this.cities.get(name)
    }
}
// city class information
class City {
    var name:String? = null
    var pizzaStoreIds = mutableListOf<String>()
    var customerHomeAddress:LatLng?=null

    constructor(name:String, pizzaStoreIds:MutableList<String>, customerHomeAddress:LatLng)
    {
        this.name = name
        this.pizzaStoreIds = pizzaStoreIds
        this.customerHomeAddress = customerHomeAddress
    }
}