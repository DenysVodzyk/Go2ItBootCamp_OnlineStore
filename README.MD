# Online store

This project is designed to perform basic CRUD operations for online store database using JDBC.   
 
## Overview
* Two csv files provided with information from client's CRM: items.csv and customer.csv.
* Data about customer, item and order is retrieved and stored in SQL database using JDBC connector.
* Architecture is built on Inversion of Control principles by implementing Repository-Service Pattern.
* Reports can be saved as csv files:
   :white_check_mark: Most popular items.
   :white_check_mark: Least popular items.
   :white_check_mark: Items popular among certain gender.
   :white_check_mark: Items popular during specific time interval.

## Setup
To run this project on your machine make sure you have the MySQL JDBC driver installed.   
To make sure setup is correct and JDBC is running, run the TestDatabase file from your shell.


## High-Level Architecture

<p align="center">
   <img src ="readMeSource/Architecture.png" width="600">
</p>

<p align="center">
   <i>"High level overview of the architecture"</i>
</p>









