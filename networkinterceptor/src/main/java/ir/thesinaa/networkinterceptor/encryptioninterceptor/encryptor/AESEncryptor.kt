package ir.thesinaa.networkinterceptor.encryptioninterceptor.encryptor

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class AESEncryptor : Encryptor {
    private val algorithm = "AES/CBC/PKCS5Padding"
    private val key: SecretKey = generateSecretKey()
    private val iv = IvParameterSpec(ByteArray(16))

    override fun encrypt(data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        return cipher.doFinal(data)
    }

    override fun decrypt(data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, key, iv)
        return cipher.doFinal(data)
    }

    private fun generateSecretKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(256)
        return keyGen.generateKey()
    }
}