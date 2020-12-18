# happyhouse Project [![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FKiHyeonYun%2Fhappyhouse&count_bg=%2379C83D&title_bg=%23424542&icon=pepsi.svg&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

> SSAFY project Happyhouse

![](../header.png)



## Back End 개발 환경

|환경|버전|설명|
|:---:|:---:|:---:|
|JAVA |1.8(zulu)|자바 JDK|
|SpringBoot |2.3.5|Spring Framework|
|swagger|2.9.2|Rest API|
|MySQL|8.0.21|DataBase|
|Mybatis|2.1.3|spring, MySQL 연결|


## Front End 개발 환경

|환경|버전|설명|
|:---:|:---:|:---:|
|Vue|2.6.12|SPA Framework|
|axios|0.21.0|HTTP 통신|
|quasar|1.14|Responsive Web Framework|



## 업데이트 내역
* 2020-11-20
    * 로그인
    * 자유게시판
    * 환경정보DB 구축

* 2020-11-21
    * Quasar로 front framework 변경
    * view 작업 시작
    
* 2020-11-22
    * 지도 작업 시작 naver map
    * 지도 geocode 설정 및 테스트
    * 회원관리(로그인, 로그아웃, 마이페이지) 작업 진행
    
* 2020-11-23
    * 지도 기본 기능 구현
    * 회원관리 CRUD 구현
    
* 2020-11-24
    * 지도 추가 기능 구현(환경정보)
    * 회원관리 예외처리
    * 게시판 기능 구현
    
* 2020-11-25
    * 지도 마커 아이콘 변경 및 상가정보 추가
    * 회원관리 예외처리 및 구현 완료
    * 상담기능 추가
    * 발표자료 완성
    
* 2020-11-26
    * 발표
    
## 정보
> 윤기현 - [https://github.com/KiHyeonYun] <br/>
> 이수민 - [https://github.com/leesuuuuumm] <br/>


## 실행
```
   npm install -g @quasar/cli
   npm install
   quasar dev 로 실행
```

   * 퀘이사에서 .env파일 오류가 난다면
  [https://medium.com/carbono/using-env-file-in-quasar-apps-72b56909302f]

## env 파일
   ```
   VUE_APP_SERVER_URL=http://localhost:8888/happyhouse
   MAP_DATA_KEY=맵키(KAKAO)
   ```
