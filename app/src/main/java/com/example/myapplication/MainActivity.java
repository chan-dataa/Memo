package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mMemoEdit = null;
    TextFileManager mTextFileManager;
    Button btn_load;
    Button btn_save;
    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextFileManager = new TextFileManager(this);
        mMemoEdit = (EditText) findViewById(R.id.memo_edit);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            // 1. 파일에 저장된 메모 불러오기
            case R.id.btn_load: {
                String memoData = mTextFileManager.load();
                if (memoData == "") {
                    Toast.makeText(this, "저장된 메모가 없습니다.", Toast.LENGTH_LONG).show();
                    break;
                } else {
                    mMemoEdit.setText(memoData);
                    Toast.makeText(this, "불러오기 완료", Toast.LENGTH_LONG).show();
                    break;
                }
            }

            // 2. 입력된 메모를 파일에 저장하기
            case R.id.btn_save: {
                String memoData = mMemoEdit.getText().toString();
                mTextFileManager.save(memoData);
                mMemoEdit.setText("");
                Toast.makeText(this, "저장 완료", Toast.LENGTH_LONG).show();
                break;
            }

            // 3. 저장된 메모 파일 삭제하기
            case R.id.btn_delete: {
                String memoData = mTextFileManager.load();
                if (memoData == "") {
                    Toast.makeText(this, "삭제할 메모가 없습니다", Toast.LENGTH_LONG).show();
                } else {
                    mTextFileManager.delete();
                    mMemoEdit.setText("");
                    Toast.makeText(this, "삭제 완료", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}