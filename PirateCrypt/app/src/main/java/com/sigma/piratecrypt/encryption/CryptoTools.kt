package com.sigma.piratecrypt.encryption

abstract class CryptoTools {

    open val listSize : Int = 16

    fun charToInt(character : Char) : Int{

        when(character){

            ' ' -> return 0
            '!' -> return 1
            '"' -> return 2
            '#' -> return 3
            '$' -> return 4
            '%' -> return 5
            '&' -> return 6
            '\'' -> return 7
            '(' -> return 8
            ')' -> return 9
            '*' -> return 10
            '+' -> return 11
            ',' -> return 12
            '-' -> return 13
            '.' -> return 14
            '/' -> return 15
            '0' -> return 16
            '1' -> return 17
            '2' -> return 18
            '3' -> return 19
            '4' -> return 20
            '5' -> return 21
            '6' -> return 22
            '7' -> return 23
            '8' -> return 24
            '9' -> return 25
            ':' -> return 26
            ';' -> return 27
            '<' -> return 28
            '=' -> return 29
            '>' -> return 30
            '?' -> return 31
            '@' -> return 32
            'A' -> return 33
            'B' -> return 34
            'C' -> return 35
            'D' -> return 36
            'E' -> return 37
            'F' -> return 38
            'G' -> return 39
            'H' -> return 40
            'I' -> return 41
            'J' -> return 42
            'K' -> return 43
            'L' -> return 44
            'M' -> return 45
            'N' -> return 46
            'O' -> return 47
            'P' -> return 48
            'Q' -> return 49
            'R' -> return 50
            'S' -> return 51
            'T' -> return 52
            'U' -> return 53
            'V' -> return 54
            'W' -> return 55
            'X' -> return 56
            'Y' -> return 57
            'Z' -> return 58
            '[' -> return 59
            '\\' -> return 60
            ']' -> return 61
            '^' -> return 62
            '_' -> return 63
            '`' -> return 64
            'a' -> return 65
            'b' -> return 66
            'c' -> return 67
            'd' -> return 68
            'e' -> return 69
            'f' -> return 70
            'g' -> return 71
            'h' -> return 72
            'i' -> return 73
            'j' -> return 74
            'k' -> return 75
            'l' -> return 76
            'm' -> return 77
            'n' -> return 78
            'o' -> return 79
            'p' -> return 80
            'q' -> return 81
            'r' -> return 82
            's' -> return 83
            't' -> return 84
            'u' -> return 85
            'v' -> return 86
            'w' -> return 87
            'x' -> return 88
            'y' -> return 89
            'z' -> return 90
            '{' -> return 91
            '|' -> return 92
            '}' -> return 93
            '~' -> return 94
            else -> return 95

        }

    }

    fun intToChar(integer :Int) : Char{

        when(integer) {

            0 -> return ' '
            1 -> return '!'
            2 -> return '"'
            3 -> return '#'
            4 -> return '$'
            5 -> return '%'
            6 -> return '&'
            7 -> return '\''
            8 -> return '('
            9 -> return ')'
            10 -> return '*'
            11 -> return '+'
            12 -> return ','
            13 -> return '-'
            14 -> return '.'
            15 -> return '/'
            16 -> return '0'
            17 -> return '1'
            18 -> return '2'
            19 -> return '3'
            20 -> return '4'
            21 -> return '5'
            22 -> return '6'
            23 -> return '7'
            24 -> return '8'
            25 -> return '9'
            26 -> return ':'
            27 -> return ';'
            28 -> return '<'
            29 -> return '='
            30 -> return '>'
            31 -> return '?'
            32 -> return '@'
            33 -> return 'A'
            34 -> return 'B'
            35 -> return 'C'
            36 -> return 'D'
            37 -> return 'E'
            38 -> return 'F'
            39 -> return 'G'
            40 -> return 'H'
            41 -> return 'I'
            42 -> return 'J'
            43 -> return 'K'
            44 -> return 'L'
            45 -> return 'M'
            46 -> return 'N'
            47 -> return 'O'
            48 -> return 'P'
            49 -> return 'Q'
            50 -> return 'R'
            51 -> return 'S'
            52 -> return 'T'
            53 -> return 'U'
            54 -> return 'V'
            55 -> return 'W'
            56 -> return 'X'
            57 -> return 'Y'
            58 -> return 'Z'
            59 -> return '['
            60 -> return '\\'
            61 -> return ']'
            62 -> return '^'
            63 -> return '_'
            64 -> return '`'
            65 -> return 'a'
            66 -> return 'b'
            67 -> return 'c'
            68 -> return 'd'
            69 -> return 'e'
            70 -> return 'f'
            71 -> return 'g'
            72 -> return 'h'
            73 -> return 'i'
            74 -> return 'j'
            75 -> return 'k'
            76 -> return 'l'
            77 -> return 'm'
            78 -> return 'n'
            79 -> return 'o'
            80 -> return 'p'
            81 -> return 'q'
            82 -> return 'r'
            83 -> return 's'
            84 -> return 't'
            85 -> return 'u'
            86 -> return 'v'
            87 -> return 'w'
            88 -> return 'x'
            89 -> return 'y'
            90 -> return 'z'
            91 -> return '{'
            92 -> return '|'
            93 -> return '}'
            94 -> return '~'
            95 -> return '�'
            else -> return ' '

        }

    }

    fun mod(integer: Int) : Int{

        var modded = integer

        if(integer < 0){

            while (modded < 0){

                modded += 96

            }

        }else{

            modded %= 96

        }
        return modded

    }

    fun strToList(string :String) : MutableList<Int>{

        val list : MutableList<Int> = mutableListOf()

        for(i in 0..string.length-1){

            list.add(charToInt(string[i]))

        }
        var listLoc : Int = string.length
        if(string.length < listSize){


            while(listLoc < listSize){

                list.add(95)
                listLoc++

            }

        }
        return list

    }

    fun listToStr(list : MutableList<Int>): String{

        var string = ""
        for(i in list){

            string += intToChar(i)

        }
        return string

    }

}