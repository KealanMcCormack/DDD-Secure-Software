# DDD-Secure-Software
Secure software engineering project for COMP47660 Secure Software Engineering 


Ideas for Pages
  - Homepage
  - Registration
  - Login (Should this be for all or separate admin login)
  - Vaccine booking
  - Record of last activity / Current appointment
    - Same page, if current appointment need to be able to cancel
  - Stats page
    - Ours or HSE stats
  - Forum 
  - HSE User update


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