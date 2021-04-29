package com.sigma.piratecrypt.encryption

import kotlin.random.Random

class Crypto(val poKey: String? = null ) {

    private val key : MutableList<Int> = generateKey(poKey)
    private val ROT : Int = findROT(key)

    fun findROT(key : MutableList<Int>) : Int{

        var ROT = 0
        for (i in key){
            ROT += i
        }
        ROT = (ROT % 6) + 10
        return ROT

    }

    fun generateKey(pokey: String?) : MutableList<Int>{

        val key : MutableList<Int> = mutableListOf()

        if(poKey == null){

            val h1 = generateUserKey()
            var temp = h1/1000000
            key.add(temp)
            temp = (h1/10000) - (temp * 100)
            key.add(temp)
            val h2 = generateUserKey()
            temp = h2/1000000
            key.add(temp)
            temp = (h2/10000) - (temp * 100)
            key.add(temp)

        }
        else{

            var commaIndex : Int
            var str : String = pokey!!

            commaIndex = str.indexOf(',')
            key.add(str.substring(0, commaIndex).toInt())
            str = str.substring(commaIndex+2, str.length)
            commaIndex = str.indexOf(',')
            key.add(str.substring(0, commaIndex).toInt())
            str = str.substring(commaIndex+2, str.length)
            commaIndex = str.indexOf(',')
            key.add(str.substring(0, commaIndex).toInt())
            str = str.substring(commaIndex+2, str.length)
            commaIndex = str.indexOf(',')
            key.add(str.toInt())



        }


        return key

    }

    fun encrypt(string : String) : String {

        val encrypt = object : Encrypt(string){}

        return encrypt.encrypt(string, ROT, key)

    }

    fun decrypt(string: String) : String {

        val decrypt = object : Decrypt(string){}

        return decrypt.decrypt(string, ROT, key)

    }

    private fun generateUserKey() : Int{

        val first = Random.nextInt(-99, 100)
        val second = Random.nextInt(0, 100)
        var key = first * 1000000

        if(key < 0){

            key -= second * 10000

        }else{

            key += second * 10000
        }

        return key

    }

    @JvmName("getKey1")
    fun getKey() : MutableList<Int> {

        return key

    }

    fun getROT() : Int {

        return ROT

    }

}