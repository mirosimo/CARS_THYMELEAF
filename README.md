# Sample application - Car Showroom. 

## Preamble
In the past I was using Java FX - but this is probably little bit death branch. 
So I was rocognized that put Energy into Spring boot is much more meaningfull.
I have been engaged with Spring Boot just for a few months - for now (May 2023) about 5,6 months. 
So the applicaton is surely not perfect. I am trying step by step implement new features of Spring Boot. At the end of this document are placed some 
screenshots of application. For now is used server side template engine **Thymeleaf** - why Thymmeleaf - it just happend on beginning.... Soon I would like 
to skip to client side rendering - And practice React or Angular, RestController, etc. 

## Base params of application
+ Spring Boot 2.7.6
+ Java 17
+ DB: Oracle 19
+ Template engine: Thymeleaf

## Description of Functionality

## Description of Technology

Here appears source code of sample application in Spring Boot on which I have been working for last months. 
At this moment the applications is consist of about 30 entities and similar number of tables in database. 
For now is in progress stuff around car configuration. Entities - CarBrand, CarModel, CarEquipmentPack, CarEngine, etc.
And the other branch where I am working on is around human resources - entity Employee, and many relational entities.
I was created 3 ER diagrams 

+ ER-human-resources
+ ER-car-variant
+ ER-rest-of

Which could be usefull for understanding the logic.
Also in folder xxx-documentation you can find some view examples - scrennshots. But is still in progress so is changing in time. 

## Implemented features
For now there are implemented things like:
Inserting, Updating, Deleting records, Relation between entities, One To Many, Many To Many, Many To Many with extra columns in connection table, Exception management,  Multilanguage, 
View inputs Validation - Custom Validators, Custom annotations, Authentification, Authorization, Login, logout user, Users, Roles management
Images are saved as blob in db, Thymeleaf, Css.


## Spring Security
Spring security contains bunch of stuff - to comprehend all the main parts and principles how everithing works together is quite challenging - it takes some time. E.g. Things like:

+ SecurityFilterChain
+ ProviderManager
+ AuthenticationProvider
+ SecurityContextHolder
+ UserDetailsService
+ etc...

Just now is in development state and during the time are implementing new things and features related to Authentification and Authorization. Detail of  actual state are described below.
### Authentification
User details are fetched from database - table **Users** - username, password, email, ... . Password is hasshed by BCryptPasswordEncoder. 
For authentification is used username and password. In a few days will be add authentication possibility using SSO - OpenID (Using credentials from google account). 
And I also would like to implement **two step login** --> for practicing work with more AuthenticationProviders. 


### Authorization 
For roles was created entity Role. Relation between **User** and **Role** is M : N. Through the entity **UserRole** we can finde roles assigned to particular User. 
Relation **End Point** - **assigned ROLES** is hardcoded in config file (HttpSecurity). Later will be done more sophisticated role management - Roles hierarchy, priviledges.




## Multilanguage
+ messages.properties
+ messages_en.properties

## Error, Exception management
When occur an exception, than is user informed ( In concise form via Error page ).

After Priviledge, Role management will be done, users with needed priviledges could
also display full text of an exception - PrintStackTrace. 

## Thymeleaf

Template files are places in folder: **Resources/templates**

Templates are divided into 4 base groups.
Postfix
+ -new - view for adding new entity into system
+ -list - view where are displayed data in table form
+ -detail - view where is displayed detail for particular entity
+ -update - view for updating entity

Attribute menuItem (used for menu generating) is pass into templates through MenuInterceptor - Not from controllers.
Defining menuItem attribute in each controller had been little bit annoying.

### Fragments

Fragment files are placed in folder: **Resources/templates/fragments**

+ **top_part** - the very top part of application - contains logo and two design images
+ **info_strip** - Strip, where is placed logged user info, button for login, logout, in future will be here placed language switcher.
+ **top_nav** - contains top menu
+ **input_warning_board** - contains alert board, which is displayed, when user inserts and confirm wrong data.
+ **login_warning_board** - Alert board used by wrong login.
+ **head_tags** - Contain links for css stylesheet and other.

## CSS
+ Top Strip – logo, two images – used FLEX. 
+ Views for adding new records (label, input box) - used Grid. 
+ Views for table form data output – used Table 
+ Top Menu – resources/static/css/top_navbar.css

Main CSS file – resources/static/css/app_style.css


## TO DO
In the near future will be done :

+ User management - Privileges, Roles 
+ Error logging
+ Fundamental Change  - When I'll go through the Spring Security than will come New Version using client side rendering - probably React - Instead of server side rendering - Thymeleaf (Why I used Thymeleaf at the beginning ? - it just happend) . I would like practice using JSON object, using restController instead of Controller and creating Views on client side, authentication using JWT, etc.


# Screenshots
## Add Engine 
![Add Engine](/screenshots/add-engine.png)

## List of Engines
![Engine list](/screenshots/engine-list.png)

## Equipment pack and its available angines
![Equipment pack engines](/screenshots/eq-engines.png)

## Add new Equipment pack 
![Add new Equipment pack](/screenshots/eq-pack-new.png)

## Equipment pack List
![Equipment pack list](/screenshots/eq-pack-list.png)

## Add new User
![Add new User](/screenshots/role-user.png)

## Wrong inputs data
![Wrong inputs data](/screenshots/car-brand-new-wrong-data.png)

## Grid with all Available models of particular car brand
![car models grid](/screenshots/models-grid.png)

## Login form
![Login form](/screenshots/login-form.png)

## Login form - wrong credentials
![Login form wrong credentials](/screenshots/login-wrong-credentials.png)











