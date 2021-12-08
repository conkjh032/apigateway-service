# apigateway-service


해 볼 것들
Api-gateway와 first-service, second-service, eureka를 모두 구동시킵니다.

1. first-service와 second-service로 라우팅이 되는지 확인합니다.
localhost:8000/first-service/welcome
localhost:8000/second-service/welcome

2. Api-Gateway에서 헤더 추가가 되었는지 확인합니다.
Api-Gateway의 application.yaml에서 주석되어 있는 AddRequestHeader와 AddResponseHeader를 해체합니다.
이 두 개의 필터를 제외하고 주석처리 합니다.
그리고 FilterConfig.java에 주석처리된 부분을 해제하여 Bean으로 등록해줍니다.

localhost:8000/first-service/message
localhost:8000/second-service/message

3. Custom 필터의 동작을 확인합니다.
Api-Gateway의 application.yaml에서 CustomFilter를 제외하고 전부 주석처리 합니다.

localhost:8000/first-service/check
localhost:8000/second-service/check
