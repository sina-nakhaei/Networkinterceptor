package ir.thesinaa.networkinterceptor.encryptioninterceptor.encryptor

interface Encryptor {
    fun encrypt(data: ByteArray): ByteArray
    fun decrypt(data: ByteArray): ByteArray
}