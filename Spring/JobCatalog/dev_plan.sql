------- DONE -------

-- Tech topics & improvements --

+ Externalizing Bean Configurations (2-12)
+ Localization (8-4)
+ Externalizing Locale-Sensitive Text Messages (8-5)
+ Mapping Exceptions to Views (8-8)
+ Creating XSL/PDF Views (8-13)
+ Security (5)
+ Change Database from Oracle to SQLite
+ Binding Properties of Custom Types (converters used: https://www.javacodegeeks.com/2013/11/type-conversion-in-spring-2.html)
+ Exclude JS from JSP to separate file

-- Logic topics --

+ Add position entity
  + list
  + add (assign to specific company)
  + remove (check correct removing from database with all dependencies)
  + get by id
  + edit
+ Add Skill entity
  + list
  + add
  + remove
  + edit
  + liml Skills to Position



------- FIXED BUGS -------

+ Changing locale doesn't work for company/edit and company/get requests  (fixed with JavaScript - added 'lang' parameter to URI)



----- IN_PROGRESS -----

- Implement search queries for:

  + company 
    + show all positions for specific company on opening company detailed info
    + show positions count for each company (include into companies list)
    + find company by name

  - position
    - find positions by all/any criteria (title, salary, skills, company)

  - skills
    - 5 most required skills



------- TODO -------


-- Tech topics --


- Expiring a controller's Session Data
- Part2. Validating Form Data (8-11)
- Spring 2.x AOP and AspectJ Support
- Part3. Web Service
- Part3. JMS
- Part3. JMX, E-mail.
- REST



-- Corrections/improvements --

- Update hibernate configs (config.properties) with correct database location


-- Logic topics --

- Generic search by keywords
- Attaching resume and its automatic sending to E-mail
- Add details to Position: salary, schedule(full/part time), Sex (Male/Female), city/location, insdustry
- Add details to Company: e-mail
- Implement generation of XLS report for Positions
 


------- BUGS -------

- CSS styles for /login page are not recognized with <link> tag.
  WA: included CSS styles into login page directly




----- Theoretical questions -----

--- JSP ---

<@page session="false" />


--- Spring ---

1. 
<mvc: default-servlet-handler />
<mvc: annotation-driven />

2.
<property ... value="#{bean_name.order+1}"/>

3.
read chapter 2 - Advanced Spring IoC Container