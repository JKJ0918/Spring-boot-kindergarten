### ■ 프로젝트명
스프링부트 팀프로젝트 유치원ERP

웹페이지 접속 URL : mbc-webcloud.iptime.org:8002/main/login

발표 PPT링크 : https://docs.google.com/presentation/d/1NkOfpaqlskkg-GLwSvNfILGFfKBoBLIhWDCPaOM8BY4/edit?usp=sharing

원본 레포지토리 : https://github.com/AHJ2616/Spring-boot-kindergarten

### :seedling: 프로젝트 소개
- 어린이집/유치원에서 사용할 수 있는 웹페이지 및 ERP 시스템 구현

### :date: 개발 기간
- 2024.10.24 ~ 2024.11.28

### :open_hands: 역할분담
- 나(작성자) : 자재/회계 관리 ERP 시스템
- 팀원1 : 인사 - 직원관리 ERP 시스템
- 팀원2 : 인사 - 회원관리 ERP 시스템
- 팀원3 : 게시판 총괄 / 일정 관리 시스템

### :computer: 개발환경
- IDE : IntelliJ
- Language : SpringBoot / HTML / CSS / Java Script / QueryDSL / Ajax
- DataBase : Maria DB

### :file_folder: 주요기능
![image](https://github.com/user-attachments/assets/4de16560-02f9-4303-b004-3f4f75bfe198)
▶ 직원관리 페이지 : 직원(등록, 상세, 수정, 삭제), 근태관리(출근/퇴근/연차 등등) 기록, 결재 요청/수락 등 기능 수행

▶ 게시판 / 일정 관리 : 유치원 회원들을 위한 게시판 기능, 공지사항 작성, 유치원 운영에 전반적인 일정관리(CRUD)를 통해 달력으로 확인 가능 

▶ 자재/회계 관리 페이지 : 유치원 내부 물자(의자, 책상, 교재 등) / 회계(수입, 지출)을 관리 하는 페이지. 물건을 구매하거나, 지출이 필요한 경우를 위한 결재 기능 구현

▶ 회원(원생, 학부모) 관리 페이지 : 유치원 원생, 학부모를 관리 하는 페이지. (원생 등록, 상세보기, 수정, 삭제), 반 배정 기능 등 원생관리에 필요한 역할을 함

### :pencil2: 프로젝트에 기여한 내용 - 자재/회계 관리

■ 데이터 베이스 설계(ERD) : 유지/보수가 용이할 수 있도록 자재 관리, 회계 관리 테이블 분리하여 데이터 관리.

![image](https://github.com/user-attachments/assets/51c0c088-7d79-4c99-bb8a-99f0f94e5285)


■ 핵심 기능
: 과거 전 직장에서 근무하며, 느꼈던 데이터 취합이나 문서 정리 시 있었으면 좋았을 부분을 아이디어로 기반하여 기능을 추가함

1. 엑셀 다운로드 기능 구현

: 회계, 자재의 내역을 엑셀 파일에 정리하여 사용자의 컴퓨터에 다운로드 시켜주는 기능으로, 회사 내에 데이터 자료를 보고 하거나 공유 할 때 대부분 엑셀파일을 요구하던 생각이나 이 기능을 구현하게 됨

![image](https://github.com/user-attachments/assets/483adc42-1d21-423e-b915-7aa5a9420202)

핵심 코드 : Service 에서 ByteArrayOutputStream 클래스를 이용하여 엑셀 파일을 바이트로 반환 후 Controller 에서 HttpHeaders 를 통해
서버에 저장 없이 클라이언트에 전송 될 수 있도록 구현됨. 
     
2. 금전 수입/지출 내역을 그래프를 이용여 도식화

: 월별 수입/지출 현황을 보여주는 그래프. 금액 데이터들을 취합하기위해 표를 만들때 표의 맨 아래 부분에 총 금액(수입/지출)을 적었던 경험이 있는데, 글 보다는 시각화 하면 더 보기 편할 것 같다는 생각에 이 기능을 추가하게 됨. 


핵심 코드 : 

