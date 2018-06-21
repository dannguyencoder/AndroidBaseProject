package com.example.vinhcrazyyyy.androidbaseproject.persistence.local_files;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_WORLD_WRITEABLE;

/*Local Files

Android can read/write files to internal as well as external storage.
Applications have access to an application-specific directory where preferences
and sqlite databases are also stored. Every Activity has helpers to get the
writeable directory. File I/O API is a subset of the normal Java File API.*/
public class ReadWriteFilesUtils {

    /*Writing files is as simple as getting the stream using openFileOutput method
    and writing to it using a BufferedWriter:*/
    private void writeFiles(Context context) throws IOException {
        // Use Activity method to create a file in the writeable directory
        FileOutputStream fos = context.openFileOutput("filename", MODE_WORLD_WRITEABLE);
        // Create buffered writer
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write("Hi, I'm writing stuff");
        writer.close();
    }

    /*Reading the file back is then just using a BufferedReader and then
    building the text into a StringBuffer:*/
    private String readFiles(Context context) throws IOException {
        BufferedReader input = null;
        input = new BufferedReader(
                new InputStreamReader(context.openFileInput("filename")));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = input.readLine()) != null) {
            buffer.append(line + "\n");
        }
        String text = buffer.toString();
        return text;
    }
    /*You can also inspect and transfer files to emulators or devices
    using the DDMS File Explorer perspective which allows you to access
    to filesystem on the device.*/
}
