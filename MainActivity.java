package com.example.BugWiki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.example.BugWiki.ml.Model; //소분류
//import com.example.bug_1128.ml.ModelBig; //대분류


public class MainActivity extends AppCompatActivity {

    Button camera, gallery;
    ImageView imageView;
    TextView result;
    int imageSize = 224;
    // 12.14 새로 추가
    Button btn_detail;
    private static final int REQUEST_CODE = 777;
    String bug_pred = "곰개미";

    // onAttatch..?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.button);
        gallery = findViewById(R.id.button2);

        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);

        // 12.14 새로 추가
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("벌레결과", this.bug_pred); //여기 bug_pred 값이 제대로 전달이 안되는것.. 이 자리에 "곰개미" 이렇게 하면 잘 나옴..
        //System.out.println("과연"+bug_pred);
        System.out.println("onCreate에서"+bug_pred);

        btn_detail = findViewById(R.id.btn_detail);
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
//                startActivityForResult(intent, 101 );
//                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
//                intent.putExtra("벌레결과", bug_pred);
//                startActivityForResult(intent, 101 );
//                setResult(RESULT_OK, intent);
//                finish();


            }
        });


        camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 3);
                }else{
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent,1);
            }
        });
    }


    public void classifyImage(Bitmap image){
        try {
            Model model = Model.newInstance(getApplicationContext()); // 소분류
            //ModelBig model = ModelBig.newInstance(getApplicationContext()); // 대분류
            
            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0,0, image.getWidth(), image.getHeight());
            int pixel = 0;
            //iterate over each piel and extract R, G, and B values. Add those values individually to the byte buffer.
            for(int i=0; i< imageSize; i++){
                for(int j=0;j<imageSize;j++){
                    int val = intValues[pixel++]; //LGB
                    byteBuffer.putFloat(((val>>16)&0xFF)*(1.f/255)); // tflife model에서 Rescaling을 /255를 미리 했다면 (1.f/1)로 해주기
                    byteBuffer.putFloat(((val>>8)&0xFF)*(1.f/255));
                    byteBuffer.putFloat((val&0xFF)*(1.f/255));
                }
            }

            inputFeature0.loadBuffer(byteBuffer); // byteBuffer는 pixel value들을 갖게 됨

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0); //소분류
            // ModelBig.Outputs outputs = model.process(inputFeature0); //대분류
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray(); // 대분류의 종류 []의 maxPos 인덱스로 반환되는게 가장 높은 확률로 예측된 것
            //find thd index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i=0;i<confidences.length;i++){
                if (confidences[i]>maxConfidence){
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"곰개미","애집개미","유령개미","주름개미","곱등이","먼지다듬이","그리마",
            "지네","쥐며느리","거저리류","권연벌레","쌀바구미","애알락수시렁이","톱가슴머리대장","경도바퀴",
            "독일바퀴","먹바퀴","미국바퀴","일본바퀴","빈대","등줄쥐","땃쥐","생쥐","시궁쥐","지붕쥐",
            "좀벌레","진딧물","집게벌레","깔따구","밀가루줄명나방","줄알락명나방","화랑곡나방","작은빨간집모기",
            "중국얼룩날개모기","지하집모기","토고숲모기","구리금파리","나방파리","노랑초파리","벼룩파리","쉬파리"}; //소분류
            
            String[] classes2 = {"개미류","곱등이류","다듬이벌레류", "다지류", "등각류", "딱정벌레류",
            "바퀴류", "빈대류", "설치류", "좀류", "진딧물류", "집게벌레류", "깔다구류","명나방류", "모기류",
            "파리류"}; //대분류

            // 12/14 새로 추가
            this.bug_pred = classes[maxPos];
            System.out.println("Main의 classifyImage에서"+bug_pred);

            result.setText(classes[maxPos]); //가장 높은 확률로 예측한것을 result의 text로 display //소분류
            //result.setText(classes2[maxPos]); //대분류

            //12.14 - 새로추가
            btn_detail.setText("자세히");


            // Releases model resources if no longer used.
            model.close();

        } catch (IOException e) {
            // TODO Handle the exception
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == 3){ //handle taking the pictures in camera
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false); // 가로, 세로 길이 지정 , 224*224
                classifyImage(image);
            }else{ // getting the pictures in the gallery
                Uri dat = data.getData();
                Bitmap image = null;
                try{
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                }catch (IOException e){
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false); // resizing - 가로, 세로 길이 지정
                classifyImage(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
