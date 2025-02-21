package ir.thesinaa.networkinterceptor.compressorinterceptor.compressor

import okio.Buffer
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

class GzipCompressor : Compressor {
    override fun compress(source: ByteArray): ByteArray {
        return Buffer().use { buffer ->
            GZIPOutputStream(buffer.outputStream()).use { it.write(source) }
            buffer.readByteArray()
        }
    }

    override fun decompress(source: ByteArray): ByteArray {
        return GZIPInputStream(source.inputStream()).use { it.readBytes() }
    }

    override fun encodingName(): String = "gzip"
}