# Question2-MMT
solution for question2



curl -X GET \
  'http://localhost:9090/read?key=MARY' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 019d87dd-d341-9c91-6a6d-1042d6830eaa'
  
 Response:
 {
    "status": 200,
    "gender": "FEMALE"
}

-----------

curl -X POST \
  'http://localhost:9090/withoutExpiry?key=JOE&value=MALE' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 43d8117e-8ea0-dbca-f92b-bb46bd038bb0'
  
  Response:
  {
    "status": 200,
    "message": "success"
}


----------

curl -X POST \
  'http://localhost:9090/withExpiry?key=MARY&value=FEMALE&expiry=5' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: bc8fa24f-4abe-89d1-27eb-d179c35ecead'
  
  Response:
  {
    "status": 200,
    "message": "success"
}
