package com.redteamobile.networktoolkit.data.local.util

import android.content.Context
import android.text.TextUtils
import com.redteamobile.networktoolkit.util.Logger

import java.io.*

class FileCache(private val mContext: Context) : DataCache {

    override fun get(key: String): String? {
        var fis: InputStream? = null
        try {
            try {
                fis = mContext.openFileInput(key)
            } catch (e: FileNotFoundException) {
                val rawId = mContext.resources.getIdentifier(
                    key,
                    "raw", mContext.packageName
                )
                if (rawId != 0) {
                    fis = mContext.resources.openRawResource(rawId)
                }
            }

            val bis = BufferedInputStream(fis)
            val data = readStream(bis)
            if (!TextUtils.isEmpty(data)) {
                return data
            }
        } catch (e: Exception) {
            Logger.e(TAG, "Unexpected Exception", e)
        } finally {
            closeQuietly(fis)
        }
        return null
    }

    override fun put(key: String, value: String) {
        if (TextUtils.isEmpty(value)) {
            return
        }
        var fos: FileOutputStream? = null
        try {
            fos = mContext.openFileOutput(key, Context.MODE_PRIVATE)
            val bytes = value.toByteArray()
            val bos = BufferedOutputStream(fos)
            bos.write(bytes)
            bos.flush()
        } catch (e: Exception) {
            Logger.e(TAG, "Unexpected Exception", e)
        } finally {
            closeQuietly(fos)
        }
    }

    override fun remove(key: String) {
        mContext.deleteFile(key)
    }


    private fun closeQuietly(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (ignored: Exception) {
            // Do nothing
        }
    }

    private fun readStream(inputStream: BufferedInputStream): String {
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            val buffer = ByteArray(BUFFER_SIZE)
            var count = inputStream.read(buffer)
            while (count > 0) {
                byteArrayOutputStream.write(buffer, 0, count)
                count = inputStream.read(buffer)
            }
            return String(byteArrayOutputStream.toByteArray(), Charsets.UTF_8)
        } catch (e: Exception) {
            Logger.i(TAG, "readStream error")
            return ""
        } catch (e: Error) {
            Logger.i(TAG, "readStream error")
            return ""
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                Logger.i(TAG, "readStream error")
            }
        }
    }

    companion object {

        private val TAG = "FileCache"
        private val BUFFER_SIZE = 4096

        fun getExternalCacheDir(context: Context): File? {
            return context.externalCacheDir
        }

        fun getFilesDir(context: Context): File {
            return context.filesDir
        }

    }

}
