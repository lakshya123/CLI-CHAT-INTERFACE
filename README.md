# CLI-CHAT-INTERFACE
Command Line Message Server 

Functionalities
---------------

1) Creating a CLI Chat Interface for exchanging messages on a Server.
2) Running System Commands on another machine using ProcessBuilder library.
3) Additonal functionality to return the list of online users.

Modified Commands
-----------------

1) For running system commands - "-rc  'System Command'"
2) For checking list of Users - " userlist"
3) Normal Text Messaging.


Architecture
------------

                              SERVER                                                      /
                              /  \                                                       /   -> Threading
                             /    \
          CLIENT<-------->HANDLER1   HANDLER2<------------->CLIENT                      <------> - WebSocket duplex connectivity
         /  \                                               /  \
        /    \                                             /    \
      READ     WRITE                                     READ     WRITE 
  
    
  Handler - has the information about the Username of the client.
  Server contains the functions for sending messages between clients and returning the list of online users. 
  
