# 메뉴 서비스

### 1. Github에서 소스를 클론합니다.

https://github.com/jxin19/menu-service.git

### 2. 프로젝트 디렉터리로 이동합니다.

cd [project]

### 3. Java 17 로 설정합니다.

#### Linux에서 OpenJDK 17 설치 및 설정하기
OpenJDK 17 다운로드하기
OpenJDK 17을 다운로드하기 위해 다음 명령어를 실행합니다.

```
sudo apt-get update
sudo apt-get install openjdk-17-jdk
```
위 명령어는 Ubuntu 또는 Debian 계열의 Linux에서 OpenJDK 17을 설치하는 명령어입니다.

#### JAVA_HOME 환경 변수 설정하기
OpenJDK 17 설치 경로를 알아내기 위해 다음 명령어를 실행합니다.

```
update-alternatives --config java
```
위 명령어를 실행하면 OpenJDK 17이 설치된 경로가 출력됩니다. 예를 들어, 다음과 같은 경로가 출력된다면:

```
/usr/lib/jvm/java-17-openjdk-amd64/bin/java
```
OpenJDK 17의 설치 경로는 /usr/lib/jvm/java-17-openjdk-amd64 입니다.

다음으로, JAVA_HOME 환경 변수를 설정합니다. ~/.bashrc 파일을 열어서 다음과 같은 내용을 추가합니다.

```
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```
위 내용을 추가한 후, source ~/.bashrc 명령어를 실행하여 변경된 환경 변수를 적용합니다.

#### Java 버전 확인하기
OpenJDK 17이 정상적으로 설치되었는지 확인하기 위해 다음 명령어를 실행합니다.

```
java -version
```
위 명령어를 실행하면 Java 버전 정보가 출력됩니다.


### 4. 프로젝트를 빌드합니다.

./gradlew build

### 5. 프로젝트를 실행합니다.

java -jar build/libs/menu.jar

