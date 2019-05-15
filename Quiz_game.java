package com.example.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz_game extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 7;


    //sound
     private SoundPlayer soundPlayer;

    private void playFromMediaPlayer() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bgm_maoudamashii_);
        mediaPlayer.start();
    }

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();


    String quizData[][] = {
            //{"問題”,”正解”,”選択1”,"選択2","選択3","選択4" ,"選択5" ,"選択6" ,"選択7"}
            {"さすけえねえ", "問題ない", "頼りない", "サスケがない", "差し入れがない", "落ち着きがない", "自信がある", "具合が悪い", "問答無用"},
            {"かすますい", "うるさい", "苦しい", "楽しい", "うれしい", "悲しい", "静か", "忙しい", "かすまさん"},
            {"あがすけ", "お調子者", "臆病者", "人気者", "ぼっち", "有名人", "知り合い", "天真爛漫", "優しい"},
            {"ちょす", "からかう", "指す", "つまづく", "押す", "転ぶ", "無視する", "挨拶", "呼びかけ"},
            {"むがさり", "花嫁", "箱入り娘", "跡継ぎ", "人間", "探す", "鎖", "無造作", "めんどくさい"},
            {"わにる", "人見知り", "仲良し", "仲間外れ", "親戚", "てんぱる", "人懐っこい", "アンパン", "ワニがいる"},
            {"がおる", "弱る", "怒る", "威張る", "張り切る", "怒鳴る", "辛い", "苦しい", "こわばる"},
            {"おがる", "成長する", "登る", "おんぶする", "はい", "おごる", "泣く", "挨拶", "退化する"},
            {"わらわら", "急ぐ", "居酒屋", "楽しむ", "はしゃぐ", "あほ面", "笑う", "にぎやか", "藁藁"},
            {"うるがす", "水に浸す", "顔を洗う", "水を出す", "ふやかす", "振るう", "水をくむ", "干す", "乾かす"},
            {"『ゴジラ』『モスラ』等の映画監督","本多猪四郎","井上ひさし","庵野秀明","樋口真嗣","ギャレス・エドワーズ","小林武史","佐藤賢一","藤沢周平","しんがぎん"},
            {"新庄出身の有名な漫画家","冨樫義博","尾田栄一郎","鳥山明","手塚治虫","原哲夫","井上雄彦","岸本済史","久保帯人","和月伸宏"},
            {"藤沢周平の作品","武士の一分","銀河鉄道の夜","蟹工船","金閣寺","こころ","舞姫","人間失格","羅生門","雪国"},
            {"山形県出身小説家の作品","食堂かたつむり","ファミリーポートレイト","こうふくのあかの","魔女の住む家","永遠のゼロ","一億円をもらったら","図書館戦争","ZOO","Another"},
            {"山形村山地方の芋煮は？","しょうゆベースに牛肉と里芋","味噌ベースに牛肉と里芋","しょうゆベースに豚肉とジャガイモ","味噌ベースに豚肉とジャガイモ","味噌ベースに豚肉と里芋","しょうゆベースに牛肉とジャガイモ","しょうゆベースに豚肉と里芋","味噌ベースに牛肉とジャガイモ","味噌ベースに牛肉とサツマイモ"},
            {"山形の建物は","霞ヶ城","弘前城","若松城","青葉城","志波城","横手城","鶴ヶ城","松本城"},
            {"井上ひさしの作品は","ブンとフン","それから","蝉しぐれ","八日目の蝉","人間失格","走れメロス","銀河鉄道の夜","グスコーブドリの伝記","河童"},
            {"寒河江のゆるキャラ","チェリン","うさひ","はながたベニちゃん","きてけろ君","タント君","ふなっしー","ペロリン","はなぽん","やまっち"},
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game_quiz);


        soundPlayer = new SoundPlayer(this);


        Intent intent = this.getIntent();


        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        for (int i = 0; i < quizData.length; i++) {

            //quizDataからクイズ出題用のquizArrayを作成
            ArrayList<String> tmpArray = new ArrayList<>();

            //クイズデータを追加
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);
            tmpArray.add(quizData[i][5]);
            tmpArray.add(quizData[i][6]);
            tmpArray.add(quizData[i][7]);
            tmpArray.add(quizData[i][8]);


            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz(){
        //クイズカウントラベルを更新
        countLabel.setText("Q" + quizCount);

        //ランダム
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //クイズを取り出す
        ArrayList<String> quiz = quizArray.get(randomNum);

        questionLabel.setText(quiz.get(0));

        //正解をrightAnswerにセット
        rightAnswer = quiz.get(1);

        //クイズ配列から問題文と正解を削除
        quiz.remove(0);
        quiz.remove(0);

        //誤答を混ぜる
        Collections.shuffle(quiz);


        //選択肢と正解の配列をつくる
        List<String> options = new ArrayList<>();
        options.add(quiz.get(0));
        options.add(quiz.get(1));
        options.add(quiz.get(2));
        options.add(rightAnswer);


        Collections.shuffle(options);

        //回答ボタンに正解と選択を表示
        answerBtn1.setText(options.get(0));
        answerBtn2.setText(options.get(1));
        answerBtn3.setText(options.get(2));
        answerBtn4.setText(options.get(3));


        //このクイズをquizArrayから削除
        quizArray.remove(randomNum);
    }


    public void checkAnswer(View view){

        //押されたボタンを判定
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if (btnText.equals(rightAnswer)) {
            alertTitle = "正解！お前田舎者だな！？";
            rightAnswerCount++;
            soundPlayer.playHitSound();
        }else {
            alertTitle = "不正解。この都会人め！！";
            soundPlayer.playOverSound();
        }

        //ダイアログ
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え　：" + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (quizCount == QUIZ_COUNT) {
                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                    startActivity(intent);//結果画面へ移動

                }else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });

        builder.setCancelable(false);
        builder.show();

    }


}
