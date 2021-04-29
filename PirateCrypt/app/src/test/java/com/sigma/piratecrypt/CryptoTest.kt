import com.sigma.piratecrypt.encryption.Crypto
import com.sigma.piratecrypt.encryption.CryptoTools
import com.sigma.piratecrypt.encryption.Decrypt
import com.sigma.piratecrypt.encryption.Encrypt
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class CryptoTest {

    private val cryptoTools = object : CryptoTools(){}
    private val key1 = mutableListOf(41, 78, 46, 16)
    private val key2 = listOf(2, 3, 0, 1)
    val emptyString : String = ""
    private val partialString : String = "Xxa"
    private val fullString : String = "UfzXFKinC0asCEai"
    private val overFlowString : String = "Im1L4o8ndQjLNvUzN"
    private val fullTest = "aNbXR3QnOxIsO-Ii"
    private val overFlowTest = "UUyL@W np9RLZ^=zZgg�+gg�+gg�+gg�"
    private val ROT : Int = 12

    @Test
    @DisplayName("com.sigma.piratecrypt.encryption.CryptoTools Test")
    fun cryptoToolsTest(){

        val testInta = 65
        val testIntUnknown = 95
        val testChara = 'a'
        val testCharUnknown = '�'
        val testModHigh = 191
        val testModLow = -177
        val testModInRange = 75
        val testShortString = "YH8MecgYTOH"
        val testString = "Kv6ah4JJnP5hdg3R"
        val testShortList = mutableListOf(57, 40, 24, 45, 69, 67, 71, 57, 52, 47, 40, 95, 95, 95, 95, 95)
        val testList = mutableListOf(43, 86, 22, 65, 72, 20, 42, 42, 78, 48, 21, 72, 68, 71, 19, 50)


        assertEquals( testInta, cryptoTools.charToInt(testChara), "CharToInt fail on a")
        assertEquals(testIntUnknown, cryptoTools.charToInt(testCharUnknown), "CharToInt fail on unknown")
        assertEquals(testChara, cryptoTools.intToChar(testInta), "IntToChar fail on a")
        assertEquals(testCharUnknown, cryptoTools.intToChar(testIntUnknown), "IntToChar fail on unknown")
        assertEquals(95, cryptoTools.mod(testModHigh), "Mod fail on high number")
        assertEquals(75, cryptoTools.mod(testModInRange), "Mod fail on in range number")
        assertEquals(15, cryptoTools.mod(testModLow), "Mod fail on low number")
        assertEquals(testShortList, cryptoTools.strToList(testShortString), "StrToList fail on short string")
        assertEquals(testList, cryptoTools.strToList(testString), "StrToList fail on string")
        assertEquals("YH8MecgYTOH�����", cryptoTools.listToStr(testShortList), "ListToStr fail on short string")
        assertEquals(testString, cryptoTools.listToStr(testList), "ListToStr fail on string")

    }

    @Test
    @DisplayName("com.sigma.piratecrypt.encryption.Encrypt Test")
    fun encryptTest(){

        val reassignETest : MutableList<Int> = cryptoTools.strToList(emptyString)
        val reassignPTest : MutableList<Int> = cryptoTools.strToList(partialString)
        val reassignFTest : MutableList<Int> = cryptoTools.strToList(fullString)
        val swapRowsTest : MutableList<Int> = cryptoTools.strToList(fullString)
        val exchangeColumnsTest : MutableList<Int> = cryptoTools.strToList(fullString)

        val encrypt = object : Encrypt(emptyString){}
        assertEquals(mutableListOf(40, 77, 45, 15, 40, 77, 45, 15, 40, 77, 45, 15, 40, 77, 45, 15), encrypt.reassign(reassignETest, key1), "com.sigma.piratecrypt.encryption.Encrypt fail on reassign empty string")
        assertEquals(mutableListOf(1, 70, 15, 15, 40, 77, 45, 15, 40, 77, 45, 15, 40, 77, 45, 15), encrypt.reassign(reassignPTest, key1), "com.sigma.piratecrypt.encryption.Encrypt fail on reassign partial string")
        assertEquals(mutableListOf(94, 52, 40, 72, 79, 25, 23, 94, 76, 94, 15, 3, 76, 19, 15, 89), encrypt.reassign(reassignFTest, key1), "com.sigma.piratecrypt.encryption.Encrypt fail on reassign full string")
        assertEquals(mutableListOf(53, 37, 65, 78, 38, 70, 65, 83, 35, 43, 90, 73, 35, 16, 73, 56), encrypt.swapRows(swapRowsTest), "com.sigma.piratecrypt.encryption.Encrypt fail on swapRows test")
        assertEquals(mutableListOf(35, 16, 65, 83, 35, 37, 65, 73, 53, 70, 90, 56, 38, 43, 73, 78), encrypt.exchangeColumns(exchangeColumnsTest, key2), "com.sigma.piratecrypt.encryption.Encrypt fail on exchangeColumns test")
        assertEquals(fullTest, encrypt.encrypt(fullString, ROT, key1), "com.sigma.piratecrypt.encryption.Encrypt fail on full string test")
        assertEquals(overFlowTest, encrypt.encrypt(overFlowString, ROT, key1), "com.sigma.piratecrypt.encryption.Encrypt fail on over flow test")

    }

    @Test
    @DisplayName("com.sigma.piratecrypt.encryption.Decrypt Test")
    fun decryptTest(){

        val reassignETest : MutableList<Int> = mutableListOf(40, 77, 45, 15, 40, 77, 45, 15, 40, 77, 45, 15, 40, 77, 45, 15)
        val reassignPTest : MutableList<Int> = mutableListOf(1, 70, 15, 15, 40, 77, 45, 15, 40, 77, 45, 15, 40, 77, 45, 15)
        val reassignFTest : MutableList<Int> = mutableListOf(94, 52, 40, 72, 79, 25, 23, 94, 76, 94, 15, 3, 76, 19, 15, 89)
        val swapRowsTest : MutableList<Int> = mutableListOf(53, 37, 65, 78, 38, 70, 65, 83, 35, 43, 90, 73, 35, 16, 73, 56)
        val exchangeColumnsTest : MutableList<Int> = mutableListOf(35, 16, 65, 83, 35, 37, 65, 73, 53, 70, 90, 56, 38, 43, 73, 78)

        val decrypt = object : Decrypt(emptyString){}
        assertEquals(cryptoTools.strToList(emptyString), decrypt.reassign(reassignETest, key1), "com.sigma.piratecrypt.encryption.Decrypt fail on reassign empty string")
        assertEquals(cryptoTools.strToList(partialString), decrypt.reassign(reassignPTest, key1), "com.sigma.piratecrypt.encryption.Decrypt fail on reassign partial string")
        assertEquals(cryptoTools.strToList(fullString), decrypt.reassign(reassignFTest, key1), "com.sigma.piratecrypt.encryption.Decrypt fail on reassign full string")
        assertEquals(cryptoTools.strToList(fullString), decrypt.swapRows(swapRowsTest), "com.sigma.piratecrypt.encryption.Decrypt fail on swapRows test")
        assertEquals(cryptoTools.strToList(fullString), decrypt.exchangeColumns(exchangeColumnsTest, key2), "com.sigma.piratecrypt.encryption.Encrypt fail on exchangeColumns test")
        assertEquals(fullString, decrypt.decrypt(fullTest, ROT, key1), "com.sigma.piratecrypt.encryption.Decrypt fail on full string test")
        assertEquals("$overFlowString���������������", decrypt.decrypt(overFlowTest, ROT, key1), "com.sigma.piratecrypt.encryption.Decrypt fail on over flow test")

    }

    @Test
    @DisplayName("com.sigma.piratecrypt.encryption.Crypto Test")
    fun cryptoTest(){

        val key = "14, 70, 81, 52"
        val userCypher = Crypto(key)
        val eAndDCypher = Crypto()

        assertEquals(11, userCypher.getROT(), "com.sigma.piratecrypt.encryption.Crypto fail on findROT test")
        assertEquals(mutableListOf(14, 70, 81, 52), userCypher.getKey(), "com.sigma.piratecrypt.encryption.Crypto fail on generateKey test")

        print(userCypher.getKey())
        print(userCypher.getKey())

        assertEquals("}G5j}h\$o/M|e 2|T", userCypher.encrypt(fullString), "com.sigma.piratecrypt.encryption.Crypto fail on encrypt and decrypt test")
        assertEquals(fullString, userCypher.decrypt("}G5j}h\$o/M|e 2|T"), "com.sigma.piratecrypt.encryption.Crypto fail on encrypt and decrypt test")
        assertEquals(fullString, eAndDCypher.decrypt(eAndDCypher.encrypt(fullString)), "com.sigma.piratecrypt.encryption.Crypto fail on encrypt and decrypt test")

    }

}