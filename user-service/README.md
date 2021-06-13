# user-service

Using KeyCloak for authentication and authorization purposes.

This process will be dockerized later
Steps (will be updated later, once everything is in place):

- Download the keyCloak zip from http://www.keycloak.org/downloads.html
- unzip and start keycloak server using the below commands
  ```
  unzip keycloak-13.0.1.zip 
  cd keycloak-13.0.1/bin
  standalone.sh -Djboss.socket.binding.port-offset=100
  ```
  - After running ./standalone.sh, Keycloak will be starting its services in port 8180. Once we see a line containing Keycloak 13.0.1started, we'll know its start-up is complete.
- open http://localhost:8180 in browser. When visiting for first time it is necessary to create an administrative login
  - Let's create an initial admin user named ```admin``` with the password ```admin```. Upon clicking Create
- Proceed to login and signin with the above credentials
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
    