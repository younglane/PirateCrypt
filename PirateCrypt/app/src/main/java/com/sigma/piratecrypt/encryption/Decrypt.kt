package com.sigma.piratecrypt.encryption

abstract class Decrypt (val cyphertext : String ) : CryptoTools() {

    fun reassign(list: MutableList<Int>, keylist : List<Int>) : MutableList<Int> {

        val updatedList : MutableList<Int> = mutableListOf()
        var i = 0

        for(int in list){

            updatedList.add(this.mod(int - keylist[i % 4]))
            i++

        }
        return updatedList

    }

    fun swapRows(list : MutableList<Int>) : MutableList<Int>{

        var updatedList : MutableList<Int> = mutableListOf()
        val row1 : List<Int> = listOf(list[0], list[4], list[8], list[12])
        val row2 : List<Int> = listOf(list[5], list[9], list[13], list[1])
        val row3 : List<Int> = listOf(list[10], list[14], list[2], list[6])
        val row4 : List<Int> = listOf(list[15], list[3], list[7], list[11])

        for (i in 0..3){

            updatedList.add(row1[i])
            updatedList.add(row2[i])
            updatedList.add(row3[i])
            updatedList.add(row4[i])

        }

        return updatedList

    }

    fun exchangeColumns(list: MutableList<Int>, keyList : List<Int>) : MutableList<Int>{

        var updatedList : MutableList<Int> = mutableListOf()
        val column1 : MutableList<Int> = list.subList(0, 4)
        val column2 : MutableList<Int> = list.subList(4, 8)
        val column3 : MutableList<Int> = list.subList(8, 12)
        val column4 : MutableList<Int> = list.subList(12, 16)
        val tempList : MutableList<MutableList<Int>> = mutableListOf(column1, column2, column3, column4)

        var i : Int
        for (int in keyList){

            i = int % 4
            updatedList.add(tempList[i][0])
            updatedList.add(tempList[i][1])
            updatedList.add(tempList[i][2])
            updatedList.add(tempList[i][3])

        }
        return updatedList

    }

    fun decrypt(string : String, ROT : Int, key : MutableList<Int>) : String{

        val stringLength = string.length
        var updatedString : String
        var tempList : MutableList<Int> = mutableListOf()

        if(stringLength <= listSize){

            tempList = strToList(string)
            for (i in 1..ROT){

                tempList = exchangeColumns(tempList, mutableListOf(2, 3, 0, 1))
                tempList = swapRows(tempList)
                tempList = reassign(tempList, key.subList(0, 4))

            }
            updatedString = listToStr(tempList)

        } else {

            tempList = strToList(string.substring(0, 16))
            for(i in 1..ROT){

                tempList = exchangeColumns(tempList, mutableListOf(2, 3, 0, 1))
                tempList = swapRows(tempList)
                tempList = reassign(tempList, key.subList(0, 4))

            }
            updatedString = listToStr(tempList) + decrypt(string.substring(16), ROT, key)

        }
        return updatedString

    }

}