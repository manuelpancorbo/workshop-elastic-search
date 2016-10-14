#!/usr/bin/env bash

curl -XDELETE "http://localhost:9200/offers"

curl -XPUT "http://localhost:9200/offers"

curl -XPUT "http://localhost:9200/offers/_mapping/offer" -d '
{
    "properties": {
      "offerId": {
        "type": "string"
      },
      "city": {
        "type": "string"
      },
      "description": {
        "type": "string"
      },
      "title": {
        "type": "string"
      },
      "company": {
        "type": "string"
      },
      "country": {
        "type": "string"
      },
      "postalCode": {
        "type": "integer"
      },
      "createdAt": {
        "type" : "date",
        "format": "yyyy-MM-dd HH:mm:ss"
      },
      "minSalary": {
      	"type": "integer"
      },
      "maxSalary": {
      	"type": "integer"
      },
      "requirements": {
        "properties" : {
            "experience": {
                "type": "string"
            },
            "experienceLevel": {
                "type": "integer"
            },
            "language": {
                "type": "string",
                "index": "not_analyzed"
            },
            "languageLevel": {
                "type": "integer"
            }
        }
      }
    }
}
'

curl -XPUT "http://localhost:9200/offers/offer/1" -d '
{
	"city": "Barcelona",
	"country": "Barcelona",
	"postalCode": "08001",
	"title": "senior JAVA developer",
	"description": "JAVA developer with knoledge of TDD, DDD, Scrum",
	"company": "Schibsted",
	"createdAt": "2016-08-10 13:00:00",
	"minSalary": 30000,
	"maxSalary": 70000,
	"requirements": {
		"experience": "JAVA",
		"experienceLevel": 4,
		"language": "english",
		"languageLevel": 3
	}
}
'

curl -XPUT "http://localhost:9200/offers/offer/2" -d '
{
	"city": "Barcelona",
	"country": "Barcelona",
	"postalCode": "08001",
	"title": "junior JAVA developer",
	"description": "JAVA developer with knoledge of TDD, DDD, Scrum",
	"company": "Schibsted",
	"createdAt": "2016-10-10 13:00:00",
	"minSalary": 20000,
	"maxSalary": 40000,
	"requirements": {
		"experience": "JAVA",
		"experienceLevel": 2,
		"language": "english",
		"languageLevel": 1
	}
}
'

curl -XPUT "http://localhost:9200/offers/offer/3" -d '
{
	"city": "Barcelona",
	"country": "Barcelona",
	"postalCode": "08001",
	"title": "senior Go developer",
	"description": "hipster go developer",
	"company": "Google",
	"createdAt": "2016-10-05 13:00:00",
	"minSalary": 50000,
	"maxSalary": 90000,
	"requirements": {
		"experience": "Go",
		"experienceLevel": 5,
		"language": "english",
		"languageLevel": 4
	}
}
'

curl -XPUT "http://localhost:9200/offers/offer/4" -d '
{
	"city": "Barcelona",
	"country": "Barcelona",
	"postalCode": "08001",
	"title": "senior Python developer",
	"description": "python developer, Flask, python 3, docker",
	"company": "Google",
	"createdAt": "2016-10-05 13:00:00",
	"minSalary": 45000,
	"maxSalary": 80000,
	"requirements": {
		"experience": "Python",
		"experienceLevel": 5,
		"language": "english",
		"languageLevel": 4
	}
}
'

curl -XPUT "http://localhost:9200/offers/offer/5" -d '
{
	"city": "Barcelona",
	"country": "Barcelona",
	"postalCode": "08001",
	"title": "senior PHP developer",
	"description": "PHP developer, hack, javascript",
	"company": "Facebook",
	"createdAt": "2016-09-05 13:00:00",
	"minSalary": 60000,
	"maxSalary": 80000,
	"requirements": {
		"experience": "PHP",
		"experienceLevel": 4,
		"language": "english",
		"languageLevel": 3
	}
}
'


curl -XPUT "http://localhost:9200/offers/offer/6" -d '
{
	"city": "Madrid",
	"country": "Madrid",
	"postalCode": "28080",
	"title": "scala developer",
	"description": "scala developer, clojure, erlang",
	"company": "Amazon",
	"createdAt": "2016-09-05 13:00:00",
	"minSalary": 70000,
	"maxSalary": 100000,
	"requirements": {
		"experience": "scala",
		"experienceLevel": 4,
		"language": "english",
		"languageLevel": 3
	}
}
'