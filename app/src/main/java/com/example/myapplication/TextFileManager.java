package com.example.myapplication;
import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TextFileManager {
    private static final String FILE_NAME = "Memo.txt";

    // 메모 내용을 저장할 파일 이름
    Context mContext = null;
    public TextFileManager(MainActivity context) {
        mContext = context;
    }

    // 파일에 메모를 저장하는 함수
    public void save(String strData) {
        if( strData == null || strData.equals("") ) {
            return;
        }
        FileOutputStream fMemo = null;
        // 데이터 쓰기에 대한 예외처리
        try {
            // 파일에 데이터를 쓰기 위해서 output 스트림 생성
            fMemo = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            // 파일에 메모 적기
            fMemo.write(strData.getBytes());
            fMemo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 저장된 메모를 불러오는 함수
    public String load() {
        try {
            // 파일에서 데이터를 읽기 위해서 input 스트림 생성
            FileInputStream fisMemo = mContext.openFileInput(FILE_NAME);

            // 데이터를 읽어 온 뒤, String 타입 객체로 반환
            byte[] memoData = new byte[fisMemo.available()];
            while (fisMemo.read(memoData) != -1) { }
            return new String(memoData);
        } catch (IOException e){}
        return "";
    }

    // 저장된 메모를 삭제하는 함수
    public void delete() {
        mContext.deleteFile(FILE_NAME);
    }
}