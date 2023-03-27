#App for fictitious Car Showroom. 

## Base params of application
+ Spring Boot 2.7.6
+ Java 11
+ DB: Oracle 19
+ Template engine: Thymeleaf

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
Relation between entities, One To Many, Many To Many, Many To Many with extra columns in connection table, Annotations, 
Images are saved as blob in db, Thymeleaf, Css

## Thymeleaf
Templates are divided into 4 base groups.
Postfix
+ -new - view for adding new entity into system
+ -list - view where are displayed data in table form
+ -detail - view where is displayed detail for particular entity
+ -update - view for updating entity

Is still in progress so source code is changing every two, three days. 
