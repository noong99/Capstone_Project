# graduateproject
<h2>졸업프로젝트_BugWiki</h2>

<h5>업로드 파일 설명(2022.11.10)</h5>
<a href="https://www.youtube.com/watch?v=L2UeBBbQFx4">참고한 유튜브<a><br>
<h6>모델 설명(실질적인 부분)</h6>
▶ model_대분류_tflite: 대분류 teachable machine 모델 tflite 파일 <br>
▶ model_소분류_tflite: 소분류 teachable machine 모델 tflite 파일 <br>

<hr>
▶모델완성.html: my_model을 이용하여 웹에서 사진 업로드시 확률로 벌레 종류 표현---소분류_tensorflow.js 모델완성<br>
▶my_model: 소분류를 teachable machine 이용한 학습 모델 - tensorflow.js(41개 종 95~130개 이미지)---my_model_소분류_tensorflow.js<br>
  - teachable machine_zip파일 압축 해제한 파일<br>
  - 3개 파일 존재(metadata.json, model.json, weights.bin)<br>
▶대분류_tensorflow.js 모델완성.html: my_model_대분류_tensorflow.js을 이용하여 웹에서 사진 업로드시 확률로 벌레 종류 표현<br>
▶my_model_대분류_tensorflow.js: 대분류를 teachable machine 이용한 학습 모델 - tensorflow.js(41개 종 95~130개 이미지)<br>
  - my_model_대분류_tensorflow.js - zip파일 압축 해제한 파일<br>
  - 3개 파일 존재(metadata.json, model.json, weights.bin)<br>

<hr>
▶벌레: 구글 드라이브-벌레 다운로드(41개종 95~130개 이미지)_용량 커서 업로드x<br>
▶땃쥐.jpeg: 모델을 돌려보기 위한 랜덤이미지 <br>
  -<b>(땃쥐 사진-> 가장 높은 확률:시궁쥐로 나와서 분류모델 개선 필요성) </b><br>
▶tm_mymodel_webcam: 웹에서 카메라로 사진을 찍었을 때 벌레 종류의 결과 도출 ---소분류_tensorflow.js(tm_mymodel_webcam)<br>
▶tm_mymodel: 웹에 사진을 업로드했을 때 벌레 종류의 결과 도출 ---소분류_tensorflow.js(tm_mymodel) <br>
