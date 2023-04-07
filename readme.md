# :family: GATI - 폐쇄형 가족 SNS

---

## :family: 기획 배경

---

보급화된 스마트폰에 따른 SNS 이용자 수 증가로 지인들과의 소통은 활발해졌지만 정작 가족간의 소통에는 소홀해져 있는 우리를 볼 수 있습니다.

GATI는 이러한 가족간의 소통 부재를 해결하기 위해 등장한 폐쇄형 SNS입니다. 가족 그룹을 만들고 소소한 일상 미션에 참여하고 일정을 관리해 돈독한 가족관계를 형성할 수 있습니다.

## :date: 프로젝트 진행 기간

---

2023.01.03(화) ~ 2023.02.17(금)

## :family: 주요 기능

---

- :clipboard: 게시글

  - 같은 그룹원끼리 공유할 수 있는 게시글 이미지, 태그와 함께 생성할 수 있습니다.
  - 가족에게 알리거나 공유하고 싶은 일상을 게시글로 등록하고 댓글과 좋아요로 소통할 수 있습니다.
  - 잊고 싶지 않은 게시글이라면 앨범에 추가할 수 있습니다.

- :family: 그룹

  - 그룹을 만들고 함께할 구성원을 초대할 수 있습니다.
  - 로그인시 보여질 메인 그룹을 설정할 수 있습니다.

- :couple: 가치 한장

  - 매주 가족이 함께 진행할 수 있는 미션이 주어집니다.
  - 가족 구성원 중 한 명이 인원을 설정함과 동시에 미션이 시작되고 1인당 한 장의 사진을 업로드 할 수 있습니다.
  - 설정된 인원 수 만큼 사진이 업로드 되면 여러 사진을 한 장의 사진으로 편집해 제공합니다.
  - 지난 미션 수행 목록을 열람할 수 있습니다.

- :bus: 가치 가자

  - GATI 사용자들이 올린 게시글에 포함된 태그를 분석해 가장 많이 언급된 10개의 도시를 정렬합니다.
  - 정렬된 각각의 도시에 해당하는 관광지 정보를 한국관광공사 api에서 가져와 관광지를 추천해 줍니다.
  - 이를 통해 가족들과 여행지를 선택하고 일정을 관리할 수 있습니다.

## :family: 주요 기술

---

### Backend - Spring

- IntelliJ IDE
- Springboot 2.7.8
- Spring Data JPA
- Spring Security
- Spring Web
- Swagger 2.9.2
- Mysql

### Frontend

- Visual Studio Code IDE
- React

### Infra

- AWS EC2
- Docker
- Jenkins
- NGNIX

## 시스템 아키텍처

---

![시스템아키텍처](/img/GATI_system_architecture.png)

## :family: 프로젝트 파일 구조

---

### Back

```
gati
├─ api
├─ config
├─ dto
├─ entity
├─ exception
├─ repository
├─ security
│  └─ jwt
├─ service
└─ util
```

### Front

```
src
├─ components
│  ├─ Calendar
│  ├─ GoTogether
│  ├─ Login
│  ├─ Main
│  ├─ Notification
│  ├─ PhotoBook
│  ├─ PicsTogether
│  │  ├─ Completed
│  │  └─ Inprogress
│  │     ├─ MissionComplete
│  │     ├─ OnMission
│  │     └─ OpenMission
│  └─ SideBar
├─ pages
│  ├─ Admins
│  ├─ Calendar
│  ├─ GoTogether
│  ├─ LogIn
│  ├─ Main
│  ├─ PhotoBook
│  └─ PicsTogether
├─ static
├─ store
│  ├─ Board
│  ├─ GoTogether
│  ├─ Nofitication
│  ├─ PhotoBoard
│  ├─ PicsTogether
│  ├─ Schedule
│  └─ User
└─ utils
```

## :family: 협업 툴

---

- Gitlab
- JIRA
- Notion
- MatterMost
- Webex
- Figma

## :family: 협업 환경

---

- Gitlab

  - 코드 버전 관리
  - PR에 팀원 리뷰 후 MR 진행

- JIRA

  - 매주 일정한 목표량 설정 후 스프린트 진행
  - 업무 분류에 따라 에픽 설정 후 이슈 생성
  - 작업 크기에 따라 Story Point 설정 후 진행

- Notion

  - 매일 아침 한 일, 오늘 할 일에 대한 짧은 브리핑으로 10분 스크럼 진행
  - 전파 사항 기록
  - 기능 명세서, API 설계서, ERD, BE & FE 개발환경 및 코드 컨벤션, Git flow & Git branch Convention & JIRA Convetion, Figma 프로토 타입 등 공유 문서 관리

- MatterMost

  - 공지 및 공유 사항, 자료 공유
  - 팀원간 작업 현황 소통

## :family: 역할 분배

---

![역할분배](/img/GATI_%EC%97%AD%ED%95%A0%EB%B0%B0%EB%B6%84.png)

## :family: 프로젝트 산출물

---

- [Commit 컨벤션](https://rebel-dirigible-507.notion.site/Git-commit-convention-37a77db3c39a4f2e8b104eb883baade9)
- [JIRA 컨벤션](https://rebel-dirigible-507.notion.site/Jira-Convention-888ec96a56f2468da60f020b395cd60b)
- [Git flow](https://rebel-dirigible-507.notion.site/Convention-83454fe4e7024783939a9ca1e020678b)
- [와이어프레임](https://www.figma.com/file/UxUKT1Msx59Z3Bn4VCoT3n/%EA%B0%80%ED%8B%B0?node-id=4%3A2&t=MpTc6E2zhrOuCskW-0)
- [API설계서](https://rebel-dirigible-507.notion.site/API-fd39d3e3aa8d45fa8709f61dd70a6f3d)
- [기능명세서](https://rebel-dirigible-507.notion.site/da5d8edadf3d4cae90d8a64258d48b25)
- [ERD](https://rebel-dirigible-507.notion.site/ERD-204aa98c42ac4ab997a5ec542b7c02a9)

## :family: 프로젝트 결과물

---

- [포팅메뉴얼](/exec/%EA%B0%80%ED%8B%B0_%ED%8F%AC%ED%8C%85%EB%A9%94%EB%89%B4%EC%96%BC.docx)
- [최종발표자료](</exec/%EC%84%9C%EC%9A%B8_8%EB%B0%98_A805_%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C%20(1).pptx>)

## :family: GATI 서비스 화면

---

## 로그인

<img src="./img/login.gif">

## 메인 피드

<img src="./img/main_feed.gif">

## 가치 한장

<img src="./img/가치%20한%20장.gif">

## 가치 가자

<img src="./img/가치%20가자.gif">
