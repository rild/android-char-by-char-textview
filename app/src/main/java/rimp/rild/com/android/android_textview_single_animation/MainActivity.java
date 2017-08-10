package rimp.rild.com.android.android_textview_single_animation;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


// http://www.ore-memo.com/813.html
// https://sites.google.com/a/asabon.net/asabon_site/android_programming/coding/handler

public class MainActivity extends AppCompatActivity {
    public final String TAG = "TextAnim";

    private TextView textView;

    String textTarget = "文字列を１文字ずつ出力するテスト";
    String textTarget2 = "Test that displays character string one character by character";

    // Meta Data
    int i = 0;
    String putWord = "";
    String putText = "";

    private static int TIMEOUT_MESSAGE = 1;
    private static int INTERVAL = 2;

    // Custom TextView test
    CharByCharTextView mCustomTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // テキストビュー
        textView = (TextView) findViewById(R.id.textView);

        initMetaData();
        // ハンドラ実行
        handler.sendEmptyMessage(TIMEOUT_MESSAGE);

        textView.setText("test");

        // もう一度試せるようにする
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initMetaData();
                handler.sendEmptyMessage(TIMEOUT_MESSAGE);
            }
        });

        // カスタムビューを使ったバージョン
        mCustomTextView = (CharByCharTextView) findViewById(R.id.customTextView);
        mCustomTextView.setTargetText(textTarget2);
        mCustomTextView.startCharByCharAnim();

        // もう一度試せるようにする
        mCustomTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomTextView.startCharByCharAnim();
            }
        });
    }

    private void initMetaData() {
        i = 0;
        putWord = "";
        putText = "";
    }

    // 文字列を一文字ずつ出力するハンドラ
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            Log.d(TAG, "handler");
            // 文字列を配列に１文字ずつセット
            char data[] = textTarget.toCharArray();

            // 配列数を取得
            int arrNum = data.length;

            if (i < arrNum) {
                if (msg.what == TIMEOUT_MESSAGE) {
                    putWord = String.valueOf(data[i]);
                    putText = putText + putWord;

                    textView.setText(putText);
                    handler.sendEmptyMessageDelayed(TIMEOUT_MESSAGE, INTERVAL * 50);
                    i++;
                } else {
                    super.dispatchMessage(msg);
                }
            }
        }
    };
}
