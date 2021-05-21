View.class: Admin.class Car.class Guest.class Mail.class Message.class Model.class Person.class Seller.class User.class
	javac -g View.java

clean:
	rm *.class

run: cm.jar
	java -jar cm.jar

jar:
	jar cmf manifest.mf cm.jar View.class Admin.class Car.class Guest.class Mail.class Message.class Model.class Person.class Seller.class User.class Admin.java Car.java Guest.java Mail.java Message.java Model.java Person.java Seller.java User.java View.java
	
