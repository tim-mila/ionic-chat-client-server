#Ionic Chat Client API Service

[![Build Status](https://travis-ci.org/tim-mila/ionic-chat-client-server.svg?branch=master)](https://travis-ci.org/tim-mila/ionic-chat-client-server)

### Summary
API service provides the following services for the Ionic chat client implementation

* End user sign-up and login
* Create new rooms 
* Receive and persist chat messages
* Broadcast chat messages to registered websocket clients

This service is an OAuth 2.0 resource server and requires a valid JWT issues by the authorization service.