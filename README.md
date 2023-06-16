# Template framework

**To run tests with default settings (from config.properties file):**
gradle clean test

**To change environment in terminal command:**
gradle clean test -Denv=stg
gradle clean test -Denv=dev

**To change browser in terminal command:**
gradle clean test -Dbrowser=firefox
gradle clean test -Dbrowser=chrome

**Final command example:**
gradle clean test -Denv=dev -Dbrowser=chrome



Use this framework as a template for adding flexibility to your tests: 
1. Add **util** package and **PropertyLoad** file. 
In this file you can change the top lines (like private static final String CONFIG_PROPERTIES = "**config**") - these lines are settings for your properties files that stored in resources folder. This line means that you have file with name **config** and .properties. You can have more specific files (for users, for DB settings, etc.), add them here.
2. For each setting (browser, env, etc.) create enum file like in **util** package and add specific details (like for browser - all browsers) to the top of the page.
3. In resources folder create properties files. At the top of the config file you can set up default values for browser and env. Then you can see urls for different environments.
4. In BaseTest set up all variables for PropertyLoader, browser, url, env and user.
5. In build.gradle pay attention to test task, add parameters there.
6. If no parameters set up in command line - tests will run in default browser and environment.

