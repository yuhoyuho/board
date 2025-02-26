# <h1>게시판 만들기 프로젝트</h1>

----

**간단한 게시판을 만들어 보는 프로젝트이다.**

개발의 기본적인 프로젝트이지만 처음으로 진행해보는 프로젝트이고, 기초를 확실히 하고 싶다는 생각에서 나름대로 세세하게 진행해봤다.

+ React 학습 후 프론트엔드 부분 추가 예정

----

# <h3>개발 환경</h3>

* IDE : IntelliJ IDEA Community
* Spring Boot 3.4.2
* JDK 21
* mysql
* Spring Data JPA
* Thymeleaf

----

# <h3>주요 기능</h3>

✅ 글 쓰기 (/board/save)

✅ 글 목록 (/board/)

✅ 글 조회 (/board/{id})

✅ 글 수정 (/board/update/{id})

✅ 글 삭제 (/board/delete/{id})

✅ 페이징처리(/board/paging)

    ➡️ /board/paging?page=2
    
    ➡️ 위와 같은 형태의 주소체계를 사용

✅ 파일(이미지) 첨부 기능

    ➡️ 단일 파일 첨부

    ➡️ 다중 파일 첨부

    ➡️ 파일 첨부 관련 추가해야할 부분들
        * save.html

        * BoardDTO

        * BoardService.save()

        * BoardEntity

        * BoadrFileEntity, BoardFileRepository

        * detail.html
