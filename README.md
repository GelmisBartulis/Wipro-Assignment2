# Wipro-Assignment 2
Login and registration application based on Android


### 1st Task
Using Activity and SharedPreferences

##### Activity 
When the user opens the application, they are able then to register with the details within the activity. Within the MainActivity.java, the user can put in their details which have RegEx for Java to check all the entries. The first name and second name are checked by chekcing wheter the strings contain only letters (``` [A-Za-z] ```). This allows an antry for only capital letters followed by the lowercase letters. 

##### SharedPrefernces
The information that has been checked is then stored within local volotile memory. Which means that the strings can be accessed straight away, but cannot be used if the user deletes the application's cache. 
After the user submits the information, it then displays an activity with details that have been recorded using SharedPreferences. 


### 2nd Task

##### Fragmements 
When the user completes the sign in process using the Activity and SharedPreferences, they are then directed at another activity, which holds several fragments that allow for the same registration process, but vbased on Fragments and Local Database. Again all the inputs are checked and then loaded into a newly created SQLite database.

##### SQLite database
The information that has been checked is then stored within local database, which is created as soon as the application is launched. After all the information is checked and recorded, the user is shown a field with all their data that has been retrieved from the local database. 




### Actvity vs Fragment 
Fragments are Android's solution to creating reusable user interfaces. Therefore, saving memory and are more efficient. While activity has to be loaded first and only then initialised in order to be used. Fragments should be used for fast or small processing in order for them to show the best behaviour possible. 

### SharedPreferences vs SQLite
SharedPreferences are local and can record the value of a variable with very small amount of effort required while untouching the applications's perfomance, also SP allows to persist data between app executions. Although, the bad side is, once the application's cache is removed the variables are also removed together. 
SQLite requires time and effort to set it up, but in does remain as a fully functional database after all the setup is fully completed. Classes, interfaces and adapters have to be used in order to save the information, it also affects application's perfance and memory manegement.









