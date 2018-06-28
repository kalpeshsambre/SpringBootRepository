

Friend Management : 

Git URL: https://github.com/kalpeshsambre/SpringBootRepository.git

Cloud Foundry URL : https://spring-boot-friend-management.cfapps.io

API Details:

1.Add Friend :

                     Method: POST
                     URL: https://spring-boot-friend-management.cfapps.io/friendRequest
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
                      URL: https://spring-boot-friend-management.cfapps.io/friendList
                      Request Body: 
                                {
                                  "email":kalpesh.sambre@gmail.com
                                }
                                
                                
3.Common Friends :
                      
                      Method: POST
                      URL: https://spring-boot-friend-management.cfapps.io/commonFriends
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
                      URL: https://spring-boot-friend-management.cfapps.io/requestToSubscribe
                      Request Body: 
                                  {
                                  "requestor":"kalpesh.sambre@gmail.com",
                                  "target":"rasika.sambre@gmail.com"
                                  }
  
5.Block Friend :
                      
                      Method: POST
                      URL: https://spring-boot-friend-management.cfapps.io/requestToBlock
                      Request Body: 
                                  {
                                  "requestor":"kalpesh.sambre@gmail.com",
                                  "target":"rasika.sambre@gmail.com"
                                  }

6.Update from recipients  :
                      
                      Method: POST
                      URL: https://spring-boot-friend-management.cfapps.io/receiveUpdateFrom
                      Request Body: 
                                  {
                                  "sender":"kalpesh.sambre@gmail.com",
                                  "text":"Hi hello tushar.sambre@gmail.com !!
                                  renuka.sambre@gmail.com     abcqqqqq
                                  rasika.sambre@gmail.com "
                                  }





