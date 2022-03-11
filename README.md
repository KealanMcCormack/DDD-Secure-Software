# DDD-Secure-Software
Repository for Secure Software Engineering COMP47660 

##Group Name: Dumb Dumber Dumbest

##Contributors:
- Kealan McCormack 18312236
- Lukasz Filanowski 18414616
- Gerard Colman 18327576

**Instructions for Running this Project**

**Initialise the storage**
 1. Start docker and run docker run --name DDD-mysql -p 4000:3306 -e MYSQL_ROOT_PASSWORD=password123 -d mysql in command line
 2. Open the cli of the container and type mysql -u root -p
 3. When prompted enter the password password123
 4. Once the mysql cli begins, type in CREATE DATABASE vaccines;

**Run the Application**
1. Load the project into the Intellij 
2. Run VaccineApplication 

**Once Running**

The Application will load you into the homepage.

Without logging in you can view the forum and statistics pages but cannot interact

To create a user you can click Register to Account. This will walk you through account creation. 
Once the user is created you can create posts in the forum, view last activity and book a vaccine appointment. 

To create a HSE user you can login through the HSE login using the premade admin account. 
Username - mainAdmin Password - superSecurePassword123

Once logged in as the admin you can create new admin or HSE accounts. 
Once a HSE account is created you can logout and log back in as the new HSE user. 
This allows you to access the forum with the ability to make comments. You can also view user data and update vaccination info. 



Pages
  - Homepage - Lukasz
  - Registration - Lukasz
  - Sub page for account registration(Username+Password) - Kealan
  - Login (Should this be for all or separate admin login) - Kealan
    - Decide what flags admin
  - Vaccine booking - Gerard
    - Scripts for filling tables, new vaccine table
  - Record of last activity / Current appointment - Lukasz
    - Same page, if current appointment need to be able to cancel
  - Stats page - Kealan
    - Ours
  - Forum - Kealan
  - HSE User update - Gerard



Run Docker Db
docker run --name DDD-mysql -p 4000:3306 -e MYSQL_ROOT_PASSWORD=password123 -d mysql

Need to create table in this db for now
Use mysql -u root -p
Then the password
Then CREATE DATABASE vaccines;