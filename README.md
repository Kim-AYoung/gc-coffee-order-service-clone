# [프로젝트] React - Spring Boot 상품 관리, 주문관리 API 구현
## Table of Contents
- [프로젝트 요구 사항](#프로젝트-요구-사항)
  - [프로젝트 소개 😎](#프로젝트-소개-)
  - [이곳은 공개 Repo입니다.](#이곳은-공개-repo입니다)
- [프로젝트 설명](#프로젝트-설명)
  - [요구사항](#요구사항)
  - [시스템 구성도](#시스템-구성도)

## 프로젝트 요구 사항
### 프로젝트 소개 😎
React로 만들어진 Front End가 정해져있는 상황에서,
백엔드 개발자가 Spring Boot로 상품관리 API를 구현하여 A-Z 최종 서비스를 완성시켜봅니다.
클로닝 외에도 "본인만의 아이디어"를 추가하여 더 발전시켜 완성해봅니다. 

### 이곳은 공개 Repo입니다.
1. 이 repo를 fork한 뒤
2. 여러분의 개인 Repo에서 상품관리 API를 A-Z까지 작업하여 
3. 개발이 끝나면 이 Repo에 PR을 보내어 제출을 완료해주세요.

## 프로젝트 설명
작은 로컬 카페 `Grids & Circles(GC)`의 고객들이 Coffee Bean Package를 주문할 수 있는 온라인 웹 사이트

### 요구사항
1. **회원**
    * 별도로 회원을 관리하지 않으며, email로 고객을 구분
2. **상품**
    * 현재는 4개의 상품만 존재
3. **주문**
    * 매일 전날 오후 2시부터 오늘 오후 2시까지의 주문을 모아서 처리
    * 주문을 받을 때, email을 같이 받아서 주문을 받음
    * 고객에게 `"당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다."`를 알림

### 시스템 구성도
![image](https://user-images.githubusercontent.com/57066971/190445949-d5b4e7da-b9ad-4d4c-966a-e0d0c5d99869.png)

>**Reference**  
프로그래머스 백엔드 데브 코스
