package ir.thesinaa.networkinterceptor.compressorinterceptor.compressor

interface Compressor {
    fun compress(source: ByteArray): ByteArray
    fun decompress(source: ByteArray): ByteArray
    fun encodingName(): String
}