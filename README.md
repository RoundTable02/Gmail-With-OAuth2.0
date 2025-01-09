# Sending Gmail using Google OAuth 2.0

From September in 2024, Google doesn't support SMTP anymore, so it should be procceeded with OAuth 2.0.

2024.09 구글의 SMTP 방식 지원 종료로 인해 메일 전송은 OAuth 2.0을 통해 진행해야 합니다.

한글 설명은 아래에 있습니다.

## Steps to Follow
### 1. Create a New Project in Google Cloud Console
### 2. Enable the Gmail API in APIs & Services
### 3. APIs & Services > OAuth Consent Screen

Select the application type:

External: Use this for testing with individual users.

Internal: Use this if the app is intended only for your Google Workspace organization.

### 4. APIs & Services > Credentials > Create Credentials
Select "OAuth Client ID".

Choose the application type:

Desktop Application: For apps running locally.

Web Application: For server-based apps.

Set the redirect URI:

Example: http://localhost:8080/callback

Save the generated Client ID and Client Secret.

### 5. Generate an Authorization URL
Use the following URL format:

```
https://accounts.google.com/o/oauth2/auth?
scope=https://www.googleapis.com/auth/gmail.send&
access_type=offline&
include_granted_scopes=true&
response_type=code&
client_id=YOUR_CLIENT_ID&
redirect_uri=YOUR_REDIRECT_URI
```

For full access, use the following scope instead:

```
https://www.googleapis.com/auth/gmail.modify
```
Open the link in a browser. If the code appears after the Redirect URI in the URL, the authentication was successful.

![image](https://github.com/user-attachments/assets/57721b35-757d-4339-99b7-96742900a192)
If you encounter an error like the one above, the Redirect URI might be incorrect. Please double-check it!

(For example, I accidentally typed locahost instead of localhost, causing the issue.)

Save the code and proceed to the next step quickly, as the code expires in about 5 minutes.

### 6. Generate Access and Refresh Tokens
Use Postman to execute the following:

```
POST https://oauth2.googleapis.com/token
Content-Type: application/x-www-form-urlencoded
```

Include the following in the body:

```
client_id=YOUR_CLIENT_ID
client_secret=YOUR_CLIENT_SECRET
code=AUTHORIZATION_CODE
grant_type=authorization_code
redirect_uri=YOUR_REDIRECT_URI
Ensure the YOUR_REDIRECT_URI matches the one used when generating the code.
```

### 7. Write Code Using the Gmail API
Why Use CustomTokenResponseDto?

The API's expires_in response was returned as an Integer, causing the following error:

![image](https://github.com/user-attachments/assets/d4464d82-3ab7-49c7-9eab-f272087b6276)


To resolve this, the response was first deserialized into a DTO. Then, it was converted into a GoogleTokenResponse to proceed with further processes.

## 진행 순서

### 1. 구글 클라우드 콘솔 새 프로젝트 생성

### 2. API 및 서비스에서 GMail API 활성화

### 3. API 및 서비스 > OAuth 동의 화면
애플리케이션 유형 선택

외부 : 개인 사용자 대상으로 테스트하는 경우

내부 : Google Workspace 조직 내에서만 사용하는 경우

### 4. API 및 서비스 > 인증 정보 > 자격 증명 만들기

"OAuth 클라이언트 ID"를 선택합니다.

애플리케이션 유형을 선택합니다:

데스크톱 애플리케이션: 로컬에서 실행되는 앱.

웹 애플리케이션: 서버 기반 앱.

리디렉션 URI를 설정합니다.

Example) http://localhost:8080/callback

생성된 클라이언트 ID와 클라이언트 비밀(Client Secret)을 저장합니다.

### 5. 인증용 URL 생성

```
https://accounts.google.com/o/oauth2/auth?
scope=https://www.googleapis.com/auth/gmail.send&
access_type=offline&
include_granted_scopes=true&
response_type=code&
client_id=YOUR_CLIENT_ID&
redirect_uri=YOUR_REDIRECT_URI
```

전체 접근을 원하시는 분은 아래의 scope를 사용해주세요.
```
https://www.googleapis.com/auth/gmail.modify
```

브라우저로 해당 링크 열어 Redirect uri 뒤에 code가 붙어 넘어오면 인증 성공입니다.

![image](https://github.com/user-attachments/assets/57721b35-757d-4339-99b7-96742900a192)

만약 위와 같은 오류가 발생하는 경우 Redirect URI가 잘못된 것이니 제대로 확인해주세요!!

(저의 경우 localhost가 아닌 locahost로 작성하여 생긴 문제였습니다..)

해당 code 저장해주시고 바로 다음 단계 넘어가주세요 (만료 기간이 5분 정도로 짧습니다)

### 6. Access Token과 Refresh Token 생성

Postman을 이용하여 다음 과정을 진행해주세요.
```
POST https://oauth2.googleapis.com/token
Content-Type: application/x-www-form-urlencoded
```

Body는 다음과 같이 넣어주세요
```
client_id=YOUR_CLIENT_ID
client_secret=YOUR_CLIENT_SECRET
code=AUTHORIZATION_CODE
grant_type=authorization_code
redirect_uri=YOUR_REDIRECT_URI
```
YOUR_REDIRECT_URI는 아까 code 만드실 때 사용한 그대로 넣어주셔야 합니다.


### 7. Gmail API를 이용해 코드 작성하기

#### CustomTokenResponseDto를 사용한 이유

API의 expires_in 응답이 Integer로 오는 바람에 다음과 같은 오류가 발생하였습니다.

![image](https://github.com/user-attachments/assets/d4464d82-3ab7-49c7-9eab-f272087b6276)

해당 오류 해결을 위해 DTO로 우선 Deserialize 과정을 거친 후, GoogleTokenResponse로 변환하여 이후 과정을 진행하였습니다.


