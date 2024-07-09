## JSP/Servlet Restful API Project Endpoints

| Method | URL                                         | Parameters    |
|--------|---------------------------------------------|---------------|
| GET    | /jspServlet/boards/list                     |               |
| GET    | /jspServlet/boards/detail?no=1              | no=1          |
| POST   | /jspServlet/boards/insert                   | BoardDTO      |
| POST   | /jspServlet/boards/update                   | BoardDTO      |
| POST   | /jspServlet/boards/delete                   | no            |
| GET    | /jspServlet/qna/list                        |               |
| GET    | /jspServlet/qna/detail?qno=1                | qno=1         |
| POST   | /jspServlet/qna/insert                      | QnaDTO        |
| POST   | /jspServlet/qna/answer                      | QnaDTO        |
| POST   | /jspServlet/qna/edit                        | QnaDTO        |
| POST   | /jspServlet/qna/delete                      | QnaDTO        |
| GET    | /jspServlet/dataroom/list                   |               |
| GET    | /jspServlet/dataroom/detail?no=1            | no=1          |
| POST   | /jspServlet/dataroom/upload                 | DataroomDTO   |
| POST   | /jspServlet/dataroom/update                 | DataroomDTO   |
| POST   | /jspServlet/dataroom/delete                 | DataroomDTO   |
| GET    | /jspServlet/products/list                   |               |
| GET    | /jspServlet/products/detail?pno=1           | pno=1         |
| POST   | /jspServlet/products/insert                 | ProductDTO    |
| POST   | /jspServlet/products/update                 | ProductDTO    |
| POST   | /jspServlet/products/delete                 | ProductDTO    |
| GET    | /jspServlet/members/getMemberList           |               |
| GET    | /jspServlet/members/getMember?id=alice      | id=alice      |
| GET    | /jspServlet/members/mypage?id=alice         | id=alice      |
| POST   | /jspServlet/members/join                    | MemberDTO     |
| POST   | /jspServlet/members/myInfoEdit              | MemberDTO     |
| POST   | /jspServlet/members/login                   | MemberDTO     |
| POST   | /jspServlet/members/logout                  |               |
| POST   | /jspServlet/email/send                      |               |
