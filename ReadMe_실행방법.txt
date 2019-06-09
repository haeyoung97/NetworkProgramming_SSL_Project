0. 컴파일 하기 전 코드 수정
SSL_Server.java 파일 58, 59번째 줄 root, ksName 변수를 바꿔주어야 합니다.
프로젝트 파일 내부 directory인 keystore directory 안에 MySSLServerKey 파일이 존재합니다.
(없다면 7장 실습의 인증서 생성 방법과 동일하게 진행하여 만들어 주면 됩니다.)
이 파일이 있는 경로로 각각의 변수를 바꿔주세요.
(...>NetworkProject>bin>keystore>MySSLServerKey)
더불어 68, 69번째 줄의 keyStorePass, keyPass 변수를 바꿔주어야 합니다. (해당 문자열에는 비밀번호를 설정하면 된다)

1. 컴파일 방법
   1) cmd>...(프로젝트 폴더가 저장된 경로)>NetworkProject>src
   2) javac Client_Interface.java 
   3) javac ButtonEventListener.java
   4) javac SSL_Server.java
   5) javac SSL_Client.java
   6) javac ServerThread.java
   7) javac openFilePath.java
   8) javac HandleWordTextWindow.java
   9) cd ./RMI
   10) javac HandleWordText.java
   11) javac MyHighLightPainter.java
   12) cd ..

2. 실행 방법
   1) cmd 창을 2개 열고, cmd>...(프로젝트 폴더가 저장된 경로)>NetworkProject>src로 이동한다.
   2) start rmiregistry [Enter]
   3) java SSL_Server 5533 [Enter]
   4) 다른 cmd 창에 java Client_Interface [Enter]
   5) 업로드 버튼을 누르고 ...>NetworkProject>bin>trustedcerts 파일을 연다.
   6) server address : localhost, server port : 5533, username : [임의의 이름] 을 입력한 후 접속 버튼을 누른다.
(만약, SSLHandshakeException이 발생한다면 java의 설정 파일인 java.security 파일에서 java.tls.disabled 되어 있는 부분을 비활성화 시켜주어 handshake가 수행될 수 있는 환경을 만들어 줍니다.)
   7) 접속 버튼을 누르면 새로운 창이 나타난다.
   8) upload 버튼을 누르고 임의의 txt 파일을 연다.
   9) 검색창에 임의의 단어를 입력한 후 search 버튼을 클릭한다.
   10) search 버튼을 해당 단어가 존재하는 곳에 highlight가 되며, 기록에 남는다.

3. 동작 과정
   1) SSL_Server 파일을 통해 Client가 접속 가능한 상태로 만들어 놓는다.
   2) Client_Interface 파일을 통해 사용자가 입력값을 넣을 수 있는 창이 생성된다.
   3) Interface 창에서 업로드 버튼을 통해 ...>bin>trustedcerts 파일을 업로드 한다.
   4) Interface 창에서 서버주소, port, username을 입력하고 접속 버튼을 누르면 서버와 SSL 통신을 할 수 있게 된다.
   5) 접속 버튼 이벤트가 발생하면, 새로운 창이 나타나고, upload 버튼을 통해 txt 파일을 연다.
   6) text파일을 열어 textArea에 파일 내용과 파일 정보가 입력되는 것과 단어 검색 및 기록하는 과정은 rmi를 통해 구현하였다. 
