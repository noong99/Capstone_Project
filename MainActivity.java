package com.example.bug_1128;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.example.bug_1128.ml.Model;


public class MainActivity extends AppCompatActivity {

    Button camera, gallery;
    ImageView imageView;
    TextView result;
    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.button);
        gallery = findViewById(R.id.button2);

        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);

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
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);
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
            Model.Outputs outputs = model.process(inputFeature0);
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
            String[] classes = {"경도바퀴","곰개미","곱등이","구리금파리","그리마","깔따구","나방파리","노랑초파리","먼지다듬이","독일바퀴","쥐며느리","등줄쥐",
            "거저리류","권연벌레","쌀바구미","땃쥐","먹바퀴","미국바퀴","밀가루줄명나방","벼룩파리","빈대","생쥐","쉬파리","시궁쥐","애알락수시렁이","애집개미",
            "유령개미","일본바퀴","작은빨간집모기","좀벌레","주름개미","줄알락명나방","중국얼룩날개모기","지네","지붕쥐","지하집모기","진딧물","집게벌레","토고숲모기",
            "톱가슴머리대장","화랑곡나방"};
            result.setText(classes[maxPos]); //가장 높은 확률로 예측한것을 result의 text로 display

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