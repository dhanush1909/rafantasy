# user-service

Using KeyCloak for authentication and authorization purposes.

Steps (will be updated later, once everything is in place):

- Create a realm on KeyCloak called "Rafantasy"
- Create a new Client called "user-service"  
- Add a new role, "user". 
- Add a new user, with username "user1" and password "xsw2@WSX". This is just temporary until things are in place.
- Go to role mappings and add "user" role which was created previously.
- Hit this endpoint to get bearer token:
  * POST - http://localhost:8180/auth/realms/Rafantasy/protocol/openid-connect/token
    * x-www-form-urlencoded properties:
        * client_id: user-service
        * username: user1
        * password: xsw2@WSX
        * grant_type: xsw2@WSX
    
  * Use this endpoint to check if the bearer token works:
    * http://localhost:8081/api/users
    