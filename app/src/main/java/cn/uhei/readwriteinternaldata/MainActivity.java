package cn.uhei.readwriteinternaldata;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 读写内部数据
 */
public class MainActivity extends AppCompatActivity {

    private String filename = "test";
    private TextView show;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);
        show = (TextView) findViewById(R.id.show);

        //写入方法
        findViewById(R.id.btnWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //信息输出流，arg:文件名 | 写入模式
                    //把信息输出到哪个文件,是不是用保密方式来输出内容
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);

                    //把字节流转成字符流
                    OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");

                    //把文本框里的内容写入到内部文件
                    osw.write(et.getText().toString());

                    //刷新流
                    osw.flush();
                    fos.flush();

                    //关闭流
                    osw.close();
                    fos.close();

                    //
                    Toast.makeText(MainActivity.this,"写入完成",Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //读出方法
        findViewById(R.id.btnRead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    //把信息读进来
                    FileInputStream fis = openFileInput(filename);

                    //把字节流转成字符流
                    InputStreamReader isr = new InputStreamReader(fis,"UTF-8");

                    // 字符数组，arg：字符的长度（fis的可用长度）
                    char input[] = new char[fis.available()];

                    //读取 arg: char[]
                    isr.read(input);

                    //
                    isr.close();
                    fis.close();

                    //显示读入的内容
                    String readde = new String(input);
                    show.setText(readde);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
