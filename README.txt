
Step 1:	Create a new schema named "school_mgmt"

	DROP DATABASE IF EXISTS `school_mgmt`;
	CREATE SCHEMA `school_mgmt`  DEFAULT COLLATE utf8mb4_bin ;
	USE `school_mgmt`;



Step 2: Run the main() method in SMSRunner class

	The JPA will create the tables and insert all dummy data automatically (only for the first run).