# DDD-Secure-Software
Repository for Secure Software Engineering COMP47660 

#### Group Name: Dumb Dumber Dumbest

#### Contributors:
- Kealan McCormack 18312236
- Lukasz Filanowski 18414616
- Gerard Colman 18327576

**To fix**

Lukas

*To do*
2. Apply Content Security policy to avoid XSS.
- Added anti xss to security policy, still needs fix in jsp

Report writing

*In progress*

7. During user registration, force users to choose strong passwords. Ideally you should enforce 2factor authentication.
 https://www.baeldung.com/spring-security-two-factor-authentication-with-soft-token

*Done*
1. Validate the input data provided by the users during registration (e.g., wrong PPS number) and in the forum to avoid XSS.

Kealan 

*To do*

3. Limit the number of consecutive failed authentication attempts to 3.
4. If an IP address performs 3 consecutive failed authentication attempts, block it for a given amount of time (e.g., 20 mins).
5. Implement appropriate access control to only allow the user associated with a specific account to access his/her vaccination information. Also apply access control policies to regulate access to the urls that should only be accessible to healthcare staff.
6. Support synchronous session management or asynchronous session management using JWT.
- https://www.toptal.com/spring/spring-security-tutorial

*In progress*


*Done*

6. Enforce the use of https.
- Done (Once we don't need client side)
11. Use BCrypt or a similar slow hashing functionality provided by Spring Security to store the passwords.
- Done - Added Bcrypt in controller
- 


Gerard - Kate

*To do*

10. Handle the errors that are shown to the users when a wrong input is provided in order not to expose implementation information related to your applications.

*Done*
9. Encrypt sensitive information (such as PPS number, phone number, date of birth) when storing this information in the database.

General testing /  Fixes to vaccines

*In progess*
12. Perform appropriate logging using the log4j Java framework to record sensitive operations, such as logins, access to/modification of sensitive information (reservations, credit card information).
- Added logger property file, just need to add logging in controller
7. Fix an adequate session expiration time (15-30 minutes).
- Added session timeout to properties, may need more than this

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



Run Docker Db
docker run --name DDD-mysql -p 4000:3306 -e MYSQL_ROOT_PASSWORD=password123 -d mysql

Need to create table in this db for now
Use mysql -u root -p
Then the password
Then CREATE DATABASE vaccines;