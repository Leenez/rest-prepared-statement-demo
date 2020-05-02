The server creates SQLite demo database on startup. 
If the database already exists then only it's tables are cleared.
The server opens a web-userinterface which is used to provide a customeridentifier. (For example: customer1)
The server will send a response in JSON format. The JSON contains customer information and 
information of the tasks dedicated for the customer.
