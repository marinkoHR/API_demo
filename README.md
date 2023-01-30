# API_demo
This is a simple automated API testing project written in Java.
I chose TestNG for a testing framework since it is a tool with which I have the most experience. 
And Maven is used for dependency management. 

### Setup:
1. Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows "IntelliJ IDEA")
2. Open IntelliJ IDEA
3. On the Welcome screen click the button **_Open_**
4. In the explorer window find the **_API_demo_** project and click on it 
5. Click the button **_OK_**
6. If the **_Trust project_** window is opened, click the button **_Trust project_**
7. After the project builds, click `File > Project structure`
8. In the **_Project Structure_** window click on SDK 
9. In the SDK dropdown select `+ Add SDK > Download JDK`
10. In the **_Download JDK_** window select some Java version (you can select the latest)
11. Click the button **_Apply_**
12. Click the button **_OK_**
13. After IntelliJ download and install JDK, the project setup is completed

### Run suite:
1. Open **_Urls.java_** file (location: `API_demo/src/test/java/DATA/Other/Urls.java`)
2. In the **_Urls.java_** file modify the value for `sSessionId` (take a new one from https://crudcrud.com/)
3. Open test suite (location: `API_demo/src/test/java/SUITES/Suite1.xml`)
4. Run suite with `Right click > Run`
5. After all tests from the suite are executed, a new folder should be created called **_REPORTS_** (`API_demo/src/test/java/REPORTS`)
6. A simple .txt report file is saved in the folder **_REPORTS_** and it contains the execution results (execution statuses of every test and step)

### Test cases:
* Test cases are written in the Excel sheet and they are saved in the project 
* The location of the Excel sheet with test cases is in `API_demo/src/test/java/OTHER/TestCases.xlsx`
