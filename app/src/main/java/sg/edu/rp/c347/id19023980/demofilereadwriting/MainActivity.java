package sg.edu.rp.c347.id19023980.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    String folderLocation;
    Button btnWrite,btnRead;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        tv = findViewById(R.id.textView);

//        folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/folder";
        String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(MainActivity.this, permission, 0);
        File folder = new File(folderLocation);
        Log.d("folder",folderLocation);
        if (!folder.exists()){
            boolean result = folder.mkdir();
            if(result){
                Log.d("File Read/Write","Folder created");
                Toast.makeText(MainActivity.this,"Folder created",Toast.LENGTH_SHORT).show();
            }
        }

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    File folder = new File(folderLocation);
                    Log.d("folder",folderLocation);
                    if (!folder.exists()){
                        boolean result = folder.mkdir();
                        if(result){
                            Log.d("File Read/Write","Folder created");
                            Toast.makeText(MainActivity.this,"Folder created",Toast.LENGTH_SHORT).show();
                        }
                    }
                    File targetFile_I = new File(folderLocation,"data.txt");
                    Log.d("folderlocation",folderLocation);
                    try{
                        FileWriter writer_I = new FileWriter(targetFile_I,true);
                        writer_I.write("test data" + "\n");
                        writer_I.flush();
                        writer_I.close();
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,"Failed to write!",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Failed to write!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File targetFile = new File(folderLocation,"data.txt");
                if (targetFile.exists() == true){
                    String data = "";
                    try{
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while(line != null){
                            data += line +"\n";
                            line = br.readLine();

                        }
                        tv.setText(data);
                        br.close();
                        reader.close();
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,"Failed to read!",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content",data);
                }

            }
        });
    }
}