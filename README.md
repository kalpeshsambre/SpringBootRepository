

Friend Management : 
Git URL: https://github.com/kalpeshsambre/SpringBootRepository.git

API Details:

1.Add Friend :

                     Method: POST
                     URL: http://localhost:8080/friendRequest
                     Request Body: 
                                  { 

                                    "friends":
                                            [
                                             "kalpesh.sambre@gmail.com",
                                             "tushar.sambre@gmail.com"
                                            ]
                                  }

2.Friend List :

                      Method: POST
                      URL: http://localhost:8080/friendList
                      Request Body: 
                                {
                                  "email":kalpesh.sambre@gmail.com
                                }
                                
                                
3.Common Friends :
                      
                      Method: POST
                      URL: http://localhost:8080/commonFriends
                      Request Body: 
                                { 
                                  "friends":
                                  [
                                    "jyo.sambre@gmail.com",
                                    " kalpesh.sambre@gmail.com"
                                  ]
                                }


4.Subscribe Friends :
                      
                      Method: POST
                      URL: http://localhost:8080/requestToSubscribe
                      Request Body: 
                                  {
                                  "requestor":"kalpesh.sambre@gmail.com",
                                  "target":"rasika.sambre@gmail.com"
                                  }
  
5.Block Friend :
                      
                      Method: POST
                      URL: http://localhost:8080/requestToBlock
                      Request Body: 
                                  {
                                  "requestor":"kalpesh.sambre@gmail.com",
                                  "target":"rasika.sambre@gmail.com"
                                  }

6.Update from recipients  :
                      
                      Method: POST
                      URL: http://localhost:8080/receiveUpdateFrom
                      Request Body: 
                                  {
                                  "sender":"kalpesh.sambre@gmail.com",
                                  "text":"Hi hello tushar.sambre@gmail.com !!
                                  renuka.sambre@gmail.com     abcqqqqq
                                  rasika.sambre@gmail.com "
                                  }





