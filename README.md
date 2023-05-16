# graduateproject
<h2>졸업프로젝트_BugWiki</h2>
<h5>업로드 파일 설명(2022.12.03)</h5>

▶ MainActivity : 카메라/갤러리+이미지 분류 결과 <br>
▶ HomeActivity : main 페이지<br>
▶ MapsActivity : 방역업체 페이지(업로드된 kt(코틀린)파일은 모두 방역업체 관련)<br>

▶ activity_main.xml : 카메라/갤러리+이미지 분류 결과 layout <br>
▶ activity_home.xml : main 페이지 layout<br>
▶ activity_maps.xml : 방역업체 페이지 layout<br>

▶ AndroidManifest.xml : 페이지 만들 때마다 여기에 추가(indent-filter는 main 페이지에만<br>

(2022.12.10)<br>
▶ Bug.java, CustomAdapter.java, InfoActivity.java, activity_info.xml, list_item.xml: 정보 제공 관련<br>
  build.gradle(app)부분도 수정완료 

<h6>모델 설명</h6>
▶ model_대분류_tflite: 대분류 google teachable machine 모델 tflite 파일 <br>
▶ model_소분류_tflite: 소분류 google teachable machine 모델 tflite 파일 <br>
