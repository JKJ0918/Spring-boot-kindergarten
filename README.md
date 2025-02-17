### ■ 프로젝트명
스프링부트 팀프로젝트 유치원ERP

웹페이지 접속 URL : http://mbc-webcloud.iptime.org:8002/

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

### :pencil2: 주요기능 - 자재/회계 관리(프로젝트에 기여한 내용)

- 데이터 베이스 설계(ERD)
![image](https://github.com/user-attachments/assets/51c0c088-7d79-4c99-bb8a-99f0f94e5285)
: 자재 관리, 회계 관리 테이블 분리하여 데이터 관리
