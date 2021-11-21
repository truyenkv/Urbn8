#### Install at local
- Install JAVA and set JAVE_HOME
- Install MAVEN and set MAVEN_HOME
- Install Editor: Intellij

#### How to run the script
- Clone source code and open project via Intellij
- Open terminal tab in Intellij and run command, all the test cases will be run:    
> mvn clean test

#### Explantation about business:
- Store the test data at "src/main/java/urbn8/util/Data.java"
- @Test(priority = 1):   
  - Verify get all the Manufactures and verify the number of manuafactures is **100**. 
  - It save the information of manuafacture that has name is **Concord**
  
- @Test(priority = 2):   
  - Base one the manufacturer was saved in @Test(priority = 1), I check whether "Concord" is existed.   
  
- @Test(priority = 3):   
  - Based on the manufacturer was saved in @Test(priority = 1), I check get manufacture by name successful with 2 items.   
  
- @Test(priority = 4): 
  - Based on the manufacturer was saved in @Test(priority = 1), I verify get manufacture by ID whether successful
  - I compare the many attributes between manufacture and manufacturer detail include: Country, Mfr_CommonName, Mfr_Name, VehicleTypes
  - This test case got fail because the VehicleTypes are different. (I'm not sure is it a bug or feature.)
  
#### Note:
- If I have more information about the business, I think more test case will be created or I can make sure @Test(priority = 4) pass or fail at least.
