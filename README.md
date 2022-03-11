# DDD-Secure-Software
Repository for Secure Software Engineering COMP47660 

##Group Name: Dumb Dumber Dumbest

##Contributors:
- Kealan McCormack 18312236
- Lukasz Filanowski 18414616
- Gerard Colman 18327576



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


Other Things to look into
  - JSP vs Javascript
  - Bootstrap / Bootstrap studio#


Questions
 - How to do sessions / track user privilege
   - JSP has an inbuilt HttpSession object
 - How to run the forum backend
   - Youtube tutorials seem to cover this
 - How to make graphs and display
   - https://stackoverflow.com/questions/52704690/plot-line-graph-using-jsp-from-database-values


Possibly a good reference project - https://github.com/Han-Sim/java-jsp-forum-web

DB
 - Users
 - User Login
 - Admin/HSE
 - Vaccine centres / appointments
 - Forum

Run Docker Db
docker run --name DDD-mysql -p 4000:3306 -e MYSQL_ROOT_PASSWORD=password123 -d mysql

Need to create table in this db for now
Use mysql -u root -p
Then the password
Then CREATE DATABASE vaccines;