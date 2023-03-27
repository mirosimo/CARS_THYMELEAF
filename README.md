# car_showroom - information system for fictive Car Showroom. 

## Base params of application
Spring Boot 2.7.6
DB: Oracle 19
Template engine: Thymeleaf

## Description

Here appears source code to my training application in Spring Boot on which I have been working for last days. 
At this time the applications is consist of about 30 entities and similar number of tables in db. 
For now is in progress stuff around car configuration. Entities - CarBrand, CarModel, CarEquipmentPack, CarEngine, etc.
And the other branch where I am working on is around human resources - entity Employee, and many relational entities.
I was created 3 ER diagrams 

+ ER-human-resources
+ ER-car-variant
+ ER-rest-of

Which could be usefull for understanding the logic - folder xxx-documentation.
Also in folder xxx-documentation you can find some view examples - scrennshots. But is still in progress so is changing in time. 

There are implemented things like:
Relation between entities, One To Many, Many To Many, Many To Many with extra columns in connection table, 
Images are saved as blob in db, Thymeleaf, Css

Oracle

list 
new 
update
detail
