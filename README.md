# RapidChat

- A Stomp protocol based chat application backend with persistence messaging for inbox and group chatting.

## Accepted JSON formats

- Message JSON format:
    ```
  {
  "senderID":"ID of the sender",
  "roomUID":"uuid of the room", 
  "content":"content of the msg"
  }
  ```

- Room/group/inbox JSON format:

  ```
  {
  "id":"UUID of the room",
  "isGroup":"boolean",
  "chatName":"name of the chat to be displayed",
  "channelID":"UUID of the topic"
   }
  ```
- Signup JSON Format:

  ```
  {
  "username":"...",
  "password":"...",
  "confirmPassword":"..."
  } 
  ```

## Stomp Apis

- ```/group```
- ```/inbox```

The above apis have accepts message json.

## Apis

All the contents inside of ```{...}``` are url params which should be supplied by client.

- ```GET /api/v1/create/room/user/dm/{user}```: client needs to create inbox before being able to send message to
  another user.


- ```GET /api/v1/create/room/user/group/{name}```: client needs to create group before being able to send message in the
  group


- ```POST /api/v1/signup```: accepts signup JSON to create user.


- ```GET /api/v1/user/all/rooms```: gets all the rooms in which user is present in.


- ``` GET /api/v1/user/new/message```:gets all the new messages from all the rooms.


- ```GET /api/v1/user/old/messages/{dm}/{offset}```:gets the old messages of dm. Client needs to pass offset to fetch
  message.


- ```GET /api/v1/user/old/messages/{group}/{offset}```:gets old messages of group. Client needs to pass offset to fetch
  message.


## Requirements for running the project

- Need to have installed postgresql with UUID extension enabled.
- Need to have rabbitmq installed with stomp plugin enabled.

## How to run this project
- simply run `./gradle build` you can find the jar file in build/libs folder.
- To run just use `java -jar name-of-the-jar.jar`

- <sub><sup> Disclaimer you might need to change some configs to run the code if you have different username/pass/port.<sup><sub>
### Beta 1.0V