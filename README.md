# OneDine - mWeb


#Prerequisite
* Appium should be up and running
* Android/iPhone Emulator/Simulator (Android Studio/Xcode must be installed in the system)
* Eclipse IDE

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

#Technology stack:
 * Eclipse - IDE
 * Appium (Mobile Automation Tool)
 * Selenium (web app testing) [Used as a wrapper for appium]
 * TestNG (Test Management library)
 * Cucumber (Reporting framework)
 * Java (Programming Language)
 * Maven (Dependency Manager)
 * GitHub - Version control
 * Page Object Model
 * Apache POI library (Test Data Management)
 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

#Note 

* Appium server must be installed through the command line via npm. (Considering nodejs is already installed in the system and system variable is already set in environmental variables).  
* Below is the command for the same.

  npm install -g appium --chromedriver_version="2.42"
(This would avoid the comptability issue with chromedriver)
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
#Steps to run the code: 
* Clone/Download the project into a local directory. Import the downloaded project in Eclipse IDE
* Once code is imported, click on 'Project' from eclipse header toolbar, then select and click 'Clean' from the drop-down
* From project explorer, right click on the OneDine project and select Maven - > Update the project 
* Now start appium server through cmd/terminal via below command:
  * For Android:
               $ appium --allow-insecure chromedriver_autodownload
  * For iOS:
               $ appium
* Open an emulator/simulator
* Go to the project directory through cmd/terminal and run the below command and it will start running.
  * mvn clean verify
  
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
#Reports 
* Cucumber HTML reports is generated in the directory "/target/cucumber-html-reports/overview-features.html"

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
